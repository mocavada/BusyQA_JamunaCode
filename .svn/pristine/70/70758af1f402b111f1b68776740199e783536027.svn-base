package com.busyqa.crm.controller;

import com.busyqa.crm.mail.MailClient;
import com.busyqa.crm.message.request.LoginForm;
import com.busyqa.crm.message.request.SignUpForm;
import com.busyqa.crm.message.response.JwtResponse;
import com.busyqa.crm.message.response.ResponseMessage;
import com.busyqa.crm.model.*;
import com.busyqa.crm.repo.*;
import com.busyqa.crm.security.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

  @Autowired AuthenticationManager authenticationManager;

  @Autowired TeamRepository teamRepository;

  @Autowired UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder encoder;

  @Autowired UserTeamRoleRepository userTeamRoleRepository;

  @Autowired JwtProvider jwtProvider;

  @Autowired PaymentRecordRepository paymentRecordRepository;

  @Autowired CourseRepository courseRepository;

  @Autowired ClientStatusRepository clientStatusRepository;

  @Autowired PaymentPlanRepository paymentPlanRepository;

  @Autowired PaymentStatusRepository paymentStatusRepository;

  @Autowired MailClient mailClient;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    User user = userRepository.findByUsername(loginRequest.getUsername()).get();
    if (user.getStatus().equals("NO")) {
      return new ResponseEntity<>(
          new ResponseMessage("User Not Registered!"), HttpStatus.BAD_REQUEST);
    } else {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtProvider.generateJwtToken(authentication);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return ResponseEntity.ok(
          new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
    String url = "http://localhost:4200/unregistered-client/";
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>(
          new ResponseMessage("User Not Registered!!!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>(
          new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
    }

    // creating user account
    User user =
        new User(
            signUpRequest.getName(),
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getStatus(),
            LocalDateTime.now().toString());
    userRepository.save(user);

    User savedUser = userRepository.findByUsername(signUpRequest.getUsername()).get();
    UserTeamRole userTeamRole = new UserTeamRole();
    Role role = roleRepository.findByName(RoleName.ROLE_CLIENT.name()).get();
    Team team = teamRepository.findByName(TeamName.TEAM_CLIENT.name()).get();

    // Saving in Usr team role Repo
    userTeamRole.setRole(role);
    userTeamRole.setTeam(team);
    userTeamRole.setUser(savedUser);
    try {
      userTeamRoleRepository.save(userTeamRole);

    } catch (Exception e) {
      return new ResponseEntity<>(
          new ResponseMessage("Fail -> User Not created!"), HttpStatus.BAD_REQUEST);
    }

    // Saving payment History
    PaymentRecord paymentRecord = new PaymentRecord();

    ClientStatus clientStatus =
        clientStatusRepository.findByname(ClientStatusName.LEADS.name()).get();
    paymentRecord.setClientStatus(clientStatus);

    PaymentPlan paymentPlan =
        paymentPlanRepository.findByname(PaymentPlanName.UNSCHEDULED.name()).get();
    paymentRecord.setPaymentPlan(paymentPlan);

    PaymentStatus paymentStatus =
        paymentStatusRepository.findByname(PaymentStatusName.UNSCHEDULED.name()).get();
    paymentRecord.setPaymentStatus(paymentStatus);

    paymentRecord.setUser(savedUser);
    paymentRecord.setTeam(team);
    paymentRecord.setIsRegisteredStudent(StatusName.NO.name());
    paymentRecord.setTrainingLocation(LocationName.Not_Decided.name());


    if (signUpRequest.getClientCourse().equals(null)) {
      CourseName course = CourseName.NOT_DECIDED;
      paymentRecord.setClientCourse(course.getCourseName());
      paymentRecord.setTotalAmount(course.courseCost);
    } else {

      Course course = courseRepository.findByname(signUpRequest.getClientCourse()).get();
      paymentRecord.setClientCourse(course.getName());
      paymentRecord.setTotalAmount(course.getAmount());
    }
    try {
      PaymentRecord savedPaymentRecord = paymentRecordRepository.save(paymentRecord);
      String courseName = savedPaymentRecord.getClientCourse();
      if (courseName.equals(CourseName.AUTOMATES_TESTING.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to Automation Testing Course",
            "automationTesting");
        System.out.println("Signup Email Sent");
      } else if (courseName.equals(CourseName.BIGDATA_DATA_SCIENCE.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to BIG DATE Course",
            "bigData");
        System.out.println("Signup Email Sent");
      } else if (courseName.equals(CourseName.BUSINESS_ANALYSIS.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to Bussiness Anaylisis Course",
            "bussinessAnalysis");
        System.out.println("Signup Email Sent");
      } else if (courseName.equals(CourseName.FULL_STACK_JAVA_DEV.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to Full Stack Dev Course",
                "fullstackDev");
        System.out.println("Signup Email Sent");
      } else if (courseName.equals(CourseName.NOT_DECIDED.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "We have a Dream for You !!!",
            "notdecided");
        System.out.println("Signup Email Sent");
      } else if (courseName.equals(CourseName.PERFORMANCE_TESTING.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to Performance Course",
            "performanceTesting");
        System.out.println("Signup Email Sent");

      } else if (courseName.equals(CourseName.SCRUM_MASTER.courseName)) {
        mailClient.prepareAndSend(
            savedUser.getEmail(),
            "Thank You for Chosing BusyQA",
            "Welcome to Scrum Master Course",
            "scrumMaster");
        System.out.println("Signup Email Sent");
      }
      else if (courseName.equals(CourseName.AUTOMATION_TESTING_ONLINE.courseName)) {
        mailClient.prepareAndSend(
                savedUser.getEmail(),
                "Thank You for Chosing BusyQA",
                "Welcome to Automation Testing Master ClassCourse",
                "automationTesting");
        System.out.println("Signup Email Sent");
      }

    } catch (Exception e) {
      return new ResponseEntity<>(
          new ResponseMessage("Fail -> User record not  created!"), HttpStatus.BAD_REQUEST);
    }
    mailClient.prepareAndSend(
            savedUser.getEmail(),url+savedUser.getId());
    System.out.println("Registration  Email Sent");
    return new ResponseEntity<>(
        new ResponseMessage("User registered successfully!"), HttpStatus.OK);
  }

  @PostMapping("/adminsignup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody String signupRequest)
      throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    UpdateUserDto signUpRequest = mapper.readValue(signupRequest, UpdateUserDto.class);
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<>(
          new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity<>(
          new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
    }

    // creating user account
    User user =
        new User(
            signUpRequest.getName(),
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getStatus(),
            LocalDateTime.now().toString());
    userRepository.save(user);

    User savedUser = userRepository.findByUsername(signUpRequest.getUsername()).get();

    if (signUpRequest.getRoles().size() == signUpRequest.getTeams().size()) {

      for (int i = 0; i < signUpRequest.getRoles().size(); i++) {
        UserTeamRole userTeamRole = new UserTeamRole();
        userTeamRole.setUser(savedUser);

        Team savedTeam =
            teamRepository
                .findByName(signUpRequest.getTeams().get(i).getName())
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team  not find."));

        Role savedRole =
            roleRepository
                .findByName(signUpRequest.getRoles().get(i).getName())
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        userTeamRole.setRole(savedRole);
        userTeamRole.setTeam(savedTeam);
        userTeamRoleRepository.save(userTeamRole);
      }

    } else {
      userRepository.deleteById(savedUser.getId());
      return new ResponseEntity<>(
          new ResponseMessage("Mismatch between roles and team assignment !"),
          HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(
        new ResponseMessage("User registered successfully!"), HttpStatus.OK);
  }
}
