package com.busyqa.crm.controller;

import com.busyqa.crm.mail.MailClient;
import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.*;
import com.busyqa.crm.repo.*;
import com.busyqa.crm.security.JwtProvider;
import com.busyqa.crm.security.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/registered-client")
public class RegisteredClientController {
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
  @Autowired UserDetailsServiceImpl userDetailsService;
  @Autowired MailClient mailClient;

  @PutMapping("/{id}")
  public ApiResponse<Void> update(@RequestBody String clientDto) throws IOException, InterruptedException {
    String url = "http://localhost:4200/registered-client/";
    ApiResponse<com.busyqa.crm.model.User> userApiResponse =
        new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Couldnt Update CLient", null);
    ObjectMapper mapper = new ObjectMapper();
    Client clientDtoUpdated = mapper.readValue(clientDto, Client.class);

    // Saving User
    User updatedUser = userRepository.findByUsername(clientDtoUpdated.getUsername()).get();
    // Saving Payment record
    PaymentRecord deletePaymentRecordList =
        paymentRecordRepository
            .findByUser(userRepository.findByUsername(clientDtoUpdated.getUsername()).get())
            .get();
    if (deletePaymentRecordList.getIsRegisteredStudent().equals(StatusName.NO.name())){
      return   new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Client Registration Incomplete", null);
    }

    if (deletePaymentRecordList.getIsRegisteredStudent().equals(null)){
      return   new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Client Registration Incomplete", null);
    }
    updatedUser.setStatus(StatusName.YES.name());
    userRepository.save(updatedUser);
    paymentRecordRepository.delete(deletePaymentRecordList);

    PaymentRecord paymentRecordList = new PaymentRecord();
    paymentRecordList.setTeam(teamRepository.findByName(clientDtoUpdated.getTeams()).get());
    paymentRecordList.setUser(userRepository.findByUsername(clientDtoUpdated.getUsername()).get());
    paymentRecordList.setClientCourse(clientDtoUpdated.getClientCourse());
    if (clientDtoUpdated.getAmountPaid() <= 300){
      paymentRecordList.setAmountPaid(300);
    }
    else {
      paymentRecordList.setAmountPaid(clientDtoUpdated.getAmountPaid());

    }

    paymentRecordList.setTotalAmount(clientDtoUpdated.getTotalAmount());
    paymentRecordList.setClientStatus(
        clientStatusRepository.findByname(ClientStatusName.STUDENT.name()).get());
    paymentRecordList.setPaymentPlan(
        paymentPlanRepository.findByname(clientDtoUpdated.getPaymentPlan()).get());
    paymentRecordList.setPaymentStatus(
        paymentStatusRepository.findByname(clientDtoUpdated.getPaymentStatus()).get());
    paymentRecordList.setLastPaidDate(LocalDate.now().toString());
    paymentRecordList.setNextPaymentDate(clientDtoUpdated.getNextPaymentDate());
    paymentRecordList.setTrainingLocation(clientDtoUpdated.getTrainingLocation());
    paymentRecordList.setIsRegisteredStudent(StatusName.YES.name());
    paymentRecordRepository.save(paymentRecordList);
    Thread.sleep(1000);
    mailClient.prepareAndSendPassword(updatedUser.getEmail(), url + updatedUser.getId());
    System.out.println("Password Email Sent");
    return new ApiResponse<>(HttpStatus.OK.value(), "You have enrolled a Student !!!", null);
  }

  @GetMapping("/{id}")
  public ApiResponse<Client> getOne(@PathVariable int id) {
    PaymentRecord paymentRecordList =
        paymentRecordRepository.findByUser(userRepository.findById(Long.valueOf(id)).get()).get();
    Client client = new Client();
    client.setId(paymentRecordList.getUser().getId());
    client.setName(paymentRecordList.getUser().getName());
    client.setUsername(paymentRecordList.getUser().getUsername());
    client.setPassword(paymentRecordList.getUser().getPassword());
    client.setEmail(paymentRecordList.getUser().getEmail());
    client.setTeams();
    client.setRoles();
    client.setStatus(paymentRecordList.getUser().getStatus());
    client.setStatusAsOfDay(paymentRecordList.getUser().getStatusAsOfDay());
    client.setClientCourse(paymentRecordList.getClientCourse());
    client.setClientStatus(paymentRecordList.getClientStatus().getName());
    client.setPaymentPlan(paymentRecordList.getPaymentPlan().getName());
    client.setPaymentStatus(paymentRecordList.getPaymentStatus().getName());
    client.setAmountPaid(paymentRecordList.getAmountPaid());
    client.setTotalAmount(
        courseRepository.findByname(paymentRecordList.getClientCourse()).get().getAmount());
    client.setLastPaidDate(paymentRecordList.getLastPaidDate());
    client.setNextPaymentDate(paymentRecordList.getNextPaymentDate());
    return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", client);
  }
}
