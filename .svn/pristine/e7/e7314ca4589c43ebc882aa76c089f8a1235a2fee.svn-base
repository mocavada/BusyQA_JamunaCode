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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/save-client")
public class SaveClientController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserTeamRoleRepository userTeamRoleRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PaymentRecordRepository paymentRecordRepository;

    @Autowired CourseRepository courseRepository;

    @Autowired ClientStatusRepository clientStatusRepository;

    @Autowired PaymentPlanRepository paymentPlanRepository;

    @Autowired PaymentStatusRepository paymentStatusRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @PutMapping("/{id}")
    public ApiResponse<Void> update(@RequestBody String clientDto) throws IOException {
        String url = "http://localhost:4200/registered-client/";
        ApiResponse<com.busyqa.crm.model.User> userApiResponse =
                new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Couldnt Update CLient", null);
        ObjectMapper mapper = new ObjectMapper();
        Client clientDtoUpdated = mapper.readValue(clientDto, Client.class);

        // Saving User
        User updatedUser = userRepository.findByUsername(clientDtoUpdated.getUsername()).get();
        updatedUser.setPassword(encoder.encode(clientDtoUpdated.getPassword()));
        userRepository.save(updatedUser);
        return new ApiResponse<>(HttpStatus.OK.value(), String.format("Your Password Has been Set !!! And Your Username is %s", updatedUser.getUsername()), null);
    }
}
