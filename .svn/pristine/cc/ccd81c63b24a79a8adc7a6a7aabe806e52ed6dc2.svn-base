package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.*;
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
@RequestMapping("/admin/client")
public class AdminManageClientController {
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
  public ApiResponse<List<Client>> getAllClients(@PathVariable String userName) {
    ApiResponse<com.busyqa.crm.model.User> userApiResponse =
        new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Users Not found", null);
    boolean clientAccess = true;
    com.busyqa.crm.model.User requestorUser = userRepository.findByUsername(userName).get();
    List<UserTeamRole> requestorTeamRoles = userTeamRoleRepository.findByUser(requestorUser);
    List<Client> clientList = new ArrayList<>();

    for (int g = 0; g < requestorTeamRoles.size(); g++) {
      if (requestorTeamRoles.get(g).getTeam().getName().equals("TEAM_SALES")) {
        clientAccess = false;
      }
    }

    for (int g = 0; g < requestorTeamRoles.size(); g++) {
      if (requestorTeamRoles.get(g).getTeam().getName().equals("TEAM_ADMIN")
          && requestorTeamRoles.get(g).getRole().getName().equals("ROLE_ADMIN")) {
        clientAccess = true;
      }
    }

    if (clientAccess) {
      List<PaymentRecord> paymentRecordList = paymentRecordRepository.findAll();

      for (int i = 0; i < paymentRecordList.size(); i++) {
        Client client = new Client();
        client.setId(paymentRecordList.get(i).getUser().getId());
        client.setName(paymentRecordList.get(i).getUser().getName());
        client.setUsername(paymentRecordList.get(i).getUser().getUsername());
        client.setPassword(paymentRecordList.get(i).getUser().getPassword());
        client.setEmail(paymentRecordList.get(i).getUser().getEmail());
        client.setTeams();
        client.setRoles();
        client.setStatus(paymentRecordList.get(i).getUser().getStatus());
        client.setStatusAsOfDay(paymentRecordList.get(i).getUser().getStatusAsOfDay());
        client.setClientCourse(paymentRecordList.get(i).getClientCourse());
        client.setClientStatus(paymentRecordList.get(i).getClientStatus().getName());
        client.setPaymentPlan(paymentRecordList.get(i).getPaymentPlan().getName());
        client.setPaymentStatus(paymentRecordList.get(i).getPaymentStatus().getName());
        client.setAmountPaid(paymentRecordList.get(i).getAmountPaid());
        client.setIsRegisteredStudent(paymentRecordList.get(i).getIsRegisteredStudent());
        client.setTrainingLocation(paymentRecordList.get(i).getTrainingLocation());
        client.setTotalAmount(
            courseRepository
                .findByname(paymentRecordList.get(i).getClientCourse())
                .get()
                .getAmount());
        client.setLastPaidDate(paymentRecordList.get(i).getLastPaidDate());
        client.setNextPaymentDate(paymentRecordList.get(i).getNextPaymentDate());
        clientList.add(client);
      }
    } else {
      ClientStatus clientStatus =
          clientStatusRepository.findByname(ClientStatusName.LEADS.name()).get();
      List<PaymentRecord> paymentRecordList =
          paymentRecordRepository.findByClientStatus(clientStatus);

      for (int i = 0; i < paymentRecordList.size(); i++) {
        Client client = new Client();
        client.setId(paymentRecordList.get(i).getUser().getId());
        client.setName(paymentRecordList.get(i).getUser().getName());
        client.setUsername(paymentRecordList.get(i).getUser().getUsername());
        client.setPassword(paymentRecordList.get(i).getUser().getPassword());
        client.setEmail(paymentRecordList.get(i).getUser().getEmail());
        client.setTeams();
        client.setRoles();
        client.setStatus(paymentRecordList.get(i).getUser().getStatus());
        client.setStatusAsOfDay(paymentRecordList.get(i).getUser().getStatusAsOfDay());
        client.setClientCourse(paymentRecordList.get(i).getClientCourse());
        client.setClientStatus(paymentRecordList.get(i).getClientStatus().getName());
        client.setPaymentPlan(paymentRecordList.get(i).getPaymentPlan().getName());
        client.setPaymentStatus(paymentRecordList.get(i).getPaymentStatus().getName());
        client.setAmountPaid(paymentRecordList.get(i).getAmountPaid());
        client.setIsRegisteredStudent(paymentRecordList.get(i).getIsRegisteredStudent());
        client.setTrainingLocation(paymentRecordList.get(i).getTrainingLocation());
        client.setTotalAmount(
            courseRepository
                .findByname(paymentRecordList.get(i).getClientCourse())
                .get()
                .getAmount());
        client.setLastPaidDate(paymentRecordList.get(i).getLastPaidDate());
        client.setNextPaymentDate(paymentRecordList.get(i).getNextPaymentDate());
        clientList.add(client);
      }
    }

    return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", clientList);
  }
}
