package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.UpdateUserDto;
import com.busyqa.crm.model.UserDtoSingle;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.repo.UserTeamRoleRepository;
import com.busyqa.crm.security.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/user")
public class AdminManageUserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserTeamRoleRepository userTeamRoleRepository;


    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userRepository.findById(Long.valueOf(id)));
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@RequestBody String  userDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UpdateUserDto userDtoUpdated = mapper.readValue(userDto, UpdateUserDto.class);
            return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userDetailsService.update(userDtoUpdated));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Trying SingleTon User and Team ");
            UserDtoSingle userDtoUpdated = mapper.readValue(userDto, UserDtoSingle.class);
            return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userDetailsService.update(userDtoUpdated));
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userDetailsService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", null);
    }

}
