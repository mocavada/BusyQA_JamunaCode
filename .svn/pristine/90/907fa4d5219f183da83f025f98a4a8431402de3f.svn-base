package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.Client;
import com.busyqa.crm.model.PaymentRecord;
import com.busyqa.crm.repo.*;
import com.busyqa.crm.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user/client")
public class ClientUserController {
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

  @GetMapping("/{userName}")
  public ApiResponse<List<Client>>  getAllClients(@PathVariable String userName) {
      List<Client> clientList = new ArrayList<>();

      ApiResponse<com.busyqa.crm.model.User> userApiResponse =
        new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Users Not found", null);
    PaymentRecord paymentRecordList =
        paymentRecordRepository.findByUser(userRepository.findByUsername(userName).get()).get();

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
    clientList.add(client);
    return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", clientList);
  }
}
