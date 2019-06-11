package com.busyqa.crm.controller;

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
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/oneclient")
public class AdminManagerOneClient {
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

  @GetMapping("/{id}")
  public ApiResponse<Client> getOne(@PathVariable int id) {
    ApiResponse<com.busyqa.crm.model.User> userApiResponse =
        new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Users Not found", null);
    PaymentRecord paymentRecordList =
        paymentRecordRepository.findByUser(userRepository.findById(Long.valueOf(id)).get()).get();
      User user = userRepository.findById(Long.valueOf(id)).get();
      if (user.getStatus().equals(StatusName.NO.name())){
          return new ApiResponse<>(
                  HttpStatus.BAD_REQUEST.value(), "Client Not Approved By Sales Team ", null);
      }
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


    client.setIsRegisteredStudent(paymentRecordList.getIsRegisteredStudent());
    client.setTrainingLocation(paymentRecordList.getTrainingLocation());
    return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", client);
  }

  @PutMapping("/{id}")
  public ApiResponse<Void> update(@RequestBody String clientDto) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String message = null;
    int differenceAmount;
    Client clientDtoUpdated = mapper.readValue(clientDto, Client.class);
    PaymentRecord deletePaymentRecordList =
        paymentRecordRepository
            .findByUser(userRepository.findByUsername(clientDtoUpdated.getUsername()).get())
            .get();

    User user = userRepository.findByUsername(clientDtoUpdated.getUsername()).get();

      if (deletePaymentRecordList.getIsRegisteredStudent().equals(StatusName.NO.name())){
          return   new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Client Registration Incomplete", null);
      }

      if (deletePaymentRecordList.getIsRegisteredStudent().equals(null)){
          return   new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Client Registration Incomplete", null);
      }

    if (user.getStatus().equals(StatusName.NO.name())){
        return new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), "Client Not Approved By Sales Team ", null);
    }
    PaymentRecord paymentRecordList = new PaymentRecord();
    paymentRecordList.setTeam(teamRepository.findByName(clientDtoUpdated.getTeams()).get());
    paymentRecordList.setUser(userRepository.findByUsername(clientDtoUpdated.getUsername()).get());
    paymentRecordList.setClientCourse(clientDtoUpdated.getClientCourse());
    paymentRecordList.setAmountPaid(clientDtoUpdated.getAmountPaid());
    paymentRecordList.setTotalAmount(clientDtoUpdated.getTotalAmount());
    paymentRecordList.setClientStatus(
        clientStatusRepository.findByname(clientDtoUpdated.getClientStatus()).get());
    paymentRecordList.setPaymentPlan(
        paymentPlanRepository.findByname(clientDtoUpdated.getPaymentPlan()).get());
    paymentRecordList.setPaymentStatus(
        paymentStatusRepository.findByname(clientDtoUpdated.getPaymentStatus()).get());
    paymentRecordList.setLastPaidDate(clientDtoUpdated.getLastPaidDate());
    paymentRecordList.setNextPaymentDate(clientDtoUpdated.getNextPaymentDate());
    paymentRecordList.setIsRegisteredStudent(StatusName.YES.name());
    paymentRecordList.setTrainingLocation(clientDtoUpdated.getTrainingLocation());

    if (paymentRecordList.getClientCourse() != CourseName.NOT_DECIDED.getCourseName()) {

      if (paymentRecordList.getClientStatus().getName().equals(ClientStatusName.STUDENT.name())) {
        if (((int) paymentRecordList.getAmountPaid()) < 300) {
          return new ApiResponse<>(
              HttpStatus.BAD_REQUEST.value(), "User not updated as initial amount not paid ", null);
        }
        else {
            paymentRecordRepository.delete(deletePaymentRecordList);
            paymentRecordRepository.save(paymentRecordList);
            message = "User updated successfully.";
            return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
        }
      } else if (paymentRecordList.getClientStatus().getName().equals(ClientStatusName.INTERN.name())) {
        differenceAmount =(int) (paymentRecordList.getTotalAmount() - paymentRecordList.getAmountPaid());
        if (differenceAmount != 0) {
          return new ApiResponse<>(
              HttpStatus.BAD_REQUEST.value(), "User not updated as amount not paid in full", null);
        }
        else {
            paymentRecordRepository.delete(deletePaymentRecordList);
            paymentRecordRepository.save(paymentRecordList);
            message = "User updated successfully.";
            return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
        }
        }

      else if (paymentRecordList.getClientStatus().getName().equals(ClientStatusName.ALUMINI.name())) {
          differenceAmount =(int) (paymentRecordList.getTotalAmount() - paymentRecordList.getAmountPaid());
          if (differenceAmount != 0) {
              return new ApiResponse<>(
                      HttpStatus.BAD_REQUEST.value(), "User not updated as amount not paid in full", null);
          }
          else {
              paymentRecordRepository.delete(deletePaymentRecordList);
              paymentRecordRepository.save(paymentRecordList);
              message = "User updated successfully.";
              return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
          }
      } else {
          paymentRecordRepository.delete(deletePaymentRecordList);
          paymentRecordRepository.save(paymentRecordList);
          message = "User updated successfully.";
          return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", null);
        }
      }else {
        paymentRecordRepository.delete(deletePaymentRecordList);
        paymentRecordRepository.save(paymentRecordList);
        message = "User updated successfully.";
    }
     return new ApiResponse<>(HttpStatus.OK.value(), message, null);
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable int id) {
    com.busyqa.crm.model.User user = userRepository.findById(Long.valueOf(id)).get();
    PaymentRecord deletePaymentRecordList = paymentRecordRepository.findByUser(user).get();
    paymentRecordRepository.delete(deletePaymentRecordList);
    List<UserTeamRole> userTeamRoleList = userTeamRoleRepository.findByUser(user);
    for (int i = 0; i < userTeamRoleList.size(); i++) {
      userTeamRoleRepository.delete(userTeamRoleList.get(i));
    }
    userRepository.delete(user);

    return new ApiResponse<>(HttpStatus.OK.value(), "User successfully Deleted.", null);
  }
}
