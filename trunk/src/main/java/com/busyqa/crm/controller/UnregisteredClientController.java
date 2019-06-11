package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.Client;
import com.busyqa.crm.model.PaymentRecord;
import com.busyqa.crm.model.StatusName;
import com.busyqa.crm.model.User;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/unregistered-client")
public class UnregisteredClientController {
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
        new ApiResponse<>(
            HttpStatus.BAD_REQUEST.value(), "You have already registered With Us...", null);
    PaymentRecord paymentRecordList =
        paymentRecordRepository.findByUser(userRepository.findById(Long.valueOf(id)).get()).get();
    if (paymentRecordList.getIsRegisteredStudent().equals("YES")) {
      Client client = new Client();
      return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "You have already registered With Us...", client);
    } else {
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

  @PutMapping("/{id}")
  public ApiResponse<Void> update(@RequestBody String clientDto) throws IOException {
    ApiResponse<com.busyqa.crm.model.User> userApiResponse =
        new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Registration Incomplete", null);
    ObjectMapper mapper = new ObjectMapper();
    String message = null;
    Client clientDtoUpdated = mapper.readValue(clientDto, Client.class);

    // Saving User

    User updatedUser = userRepository.findByUsername(clientDtoUpdated.getUsername()).get();
    PaymentRecord deletePaymentRecordList =
            paymentRecordRepository
                    .findByUser(userRepository.findByUsername(clientDtoUpdated.getUsername()).get())
                    .get();
    updatedUser.setPhoneNumber(clientDtoUpdated.getPhoneNumber());
    updatedUser.setAddress(clientDtoUpdated.getAddress());
    updatedUser.setCountry(clientDtoUpdated.getCountry());
    updatedUser.setCity(clientDtoUpdated.getCity());
    updatedUser.setState(clientDtoUpdated.getState());
    updatedUser.setZipCode(clientDtoUpdated.getZipCode());
    updatedUser.setEmergencyPhoneNumber(clientDtoUpdated.getEmergencyPhoneNumber());
    updatedUser.setAboutUs(clientDtoUpdated.getAboutUs());
    updatedUser.setStatus(StatusName.NO.name());
    userRepository.save(updatedUser);

    // Saving Payment record
    paymentRecordRepository.delete(deletePaymentRecordList);

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
    paymentRecordList.setTrainingLocation(clientDtoUpdated.getTrainingLocation());
    paymentRecordList.setIsRegisteredStudent(StatusName.YES.name());
    paymentRecordRepository.save(paymentRecordList);

    return new ApiResponse<>(HttpStatus.OK.value(), "Registration Successfull", null);
  }
}
