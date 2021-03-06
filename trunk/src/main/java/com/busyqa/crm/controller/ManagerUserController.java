package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.model.Team;
import com.busyqa.crm.model.TeamName;
import com.busyqa.crm.model.UserDto;
import com.busyqa.crm.model.UserTeamRole;
import com.busyqa.crm.repo.TeamRepository;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.repo.UserTeamRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pm/users")
public class ManagerUserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;


    @Autowired
    UserTeamRoleRepository userTeamRoleRepository;

    @GetMapping
    public ApiResponse<List<User>> listUser() {
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", userRepository.findAll());
    }

    @GetMapping("/{userName}")
    public ApiResponse<UserDto> getTeamMembers(@PathVariable String userName) {
        ApiResponse<com.busyqa.crm.model.User> userApiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
                "Users Not found", null);

        com.busyqa.crm.model.User requestorUser = userRepository.findByUsername(userName).get();
        List<UserTeamRole> requestorTeamRoles = userTeamRoleRepository.findByUser(requestorUser);
        List<Team> requestorTeams = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();
        List<UserDto> updatedUserDtoList = new ArrayList<>();
        Boolean checkAdminTeam = false;
        for (int j = 0; j < requestorTeamRoles.size(); j++) {
            requestorTeams.add(requestorTeamRoles.get(j).getTeam());
        }

        for (int g = 0; g < requestorTeamRoles.size(); g++) {
            if (requestorTeamRoles.get(g).getTeam().getName().equals("TEAM_ADMIN") && requestorTeamRoles.get(g)
                    .getRole().getName().equals("ROLE_ADMIN")) {
                checkAdminTeam = true;
            }
            else if (requestorTeamRoles.get(g).getTeam().getName().equals("TEAM_ADMIN") && requestorTeamRoles.get(g)
                    .getRole().getName().equals("ROLE_PM")){
                checkAdminTeam = true;
            }
        }
        if (checkAdminTeam) {
            List<UserTeamRole> searchUser = userTeamRoleRepository.findAll();
            for (int i = 0; i < searchUser.size(); i++) {
                UserDto updatingList = new UserDto();
                updatingList.setId(searchUser.get(i).getUser().getId());
                updatingList.setName(searchUser.get(i).getUser().getName());
                updatingList.setUsername(searchUser.get(i).getUser().getUsername());
                updatingList.setEmail(searchUser.get(i).getUser().getEmail());
                updatingList.setRoles(Collections.singletonList(searchUser.get(i).getRole().getName()));
                updatingList.setTeams(Collections.singletonList(searchUser.get(i).getTeam().getName()));
                updatingList.setStatus(searchUser.get(i).getUser().getStatus());
                updatingList.setStatusAsOfDay(searchUser.get(i).getUser().getStatusAsOfDay());
                userDtoList.add(updatingList);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", userDtoList);
        } else {
            for (int k = 0; k < requestorTeams.size(); k++) {
                Team savedTeam = teamRepository.findByName(requestorTeams.get(k).getName())
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team  not find."));
                List<UserTeamRole> searchUser = userTeamRoleRepository.findByTeam(savedTeam);
                for (int i = 0; i < searchUser.size(); i++) {
                    UserDto updatingList = new UserDto();
                    updatingList.setId(searchUser.get(i).getUser().getId());
                    updatingList.setName(searchUser.get(i).getUser().getName());
                    updatingList.setUsername(searchUser.get(i).getUser().getUsername());
                    updatingList.setEmail(searchUser.get(i).getUser().getEmail());
                    updatingList.setRoles(Collections.singletonList(searchUser.get(i).getRole().getName()));
                    updatingList.setTeams(Collections.singletonList(searchUser.get(i).getTeam().getName()));
                    updatingList.setStatus(searchUser.get(i).getUser().getStatus());
                    updatingList.setStatusAsOfDay(searchUser.get(i).getUser().getStatusAsOfDay());
                    userDtoList.add(updatingList);
                }
            }

            List<String> requiredTeam = new ArrayList<>();

            for (int k = 0; k < userDtoList.size(); k++) {
                if (userDtoList.get(k).getUsername().equals(userName)) {
                    for (int j = 0; j < userDtoList.get(k).getRoles().size(); j++) {
                        if (!(userDtoList.get(k).getRoles().get(j).equals("ROLE_USER"))) {
                            if (!(userDtoList.get(k).getRoles().get(j).equals("ROLE_ADMIN"))) {
                                requiredTeam.add(userDtoList.get(k).getTeams().get(j));
                            }

                        }
                    }
                }
            }

            for (int k = 0; k < requiredTeam.size(); k++) {
                Team savedTeam = teamRepository.findByName(requiredTeam.get(k))
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team  not find."));
                List<UserTeamRole> searchUser2 = userTeamRoleRepository.findByTeam(savedTeam);
                for (int i = 0; i < searchUser2.size(); i++) {
                    UserDto updatingList = new UserDto();
                    updatingList.setId(searchUser2.get(i).getUser().getId());
                    updatingList.setName(searchUser2.get(i).getUser().getName());
                    updatingList.setUsername(searchUser2.get(i).getUser().getUsername());
                    updatingList.setEmail(searchUser2.get(i).getUser().getEmail());
                    updatingList.setRoles(Collections.singletonList(searchUser2.get(i).getRole().getName()));
                    updatingList.setTeams(Collections.singletonList(searchUser2.get(i).getTeam().getName()));
                    updatingList.setStatus(searchUser2.get(i).getUser().getStatus());
                    updatingList.setStatusAsOfDay(searchUser2.get(i).getUser().getStatusAsOfDay());
                    updatedUserDtoList.add(updatingList);
                }
            }

            return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", updatedUserDtoList);
        }
    }
}
