package com.busyqa.crm.security.services;

import com.busyqa.crm.message.response.ResponseMessage;
import com.busyqa.crm.model.*;
import com.busyqa.crm.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTeamRoleRepository userTeamRoleRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        List<UserTeamRole> userTeamRoleList = userTeamRoleRepository.findByUser(user);

        return UserPrinciple.build(user, userTeamRoleList);
    }

    public ResponseEntity<?> update(UpdateUserDto userDto) {
        Optional<User> user = userRepository.findById(Long.valueOf(userDto.getId()));
        List<UserTeamRole>  updatingUserRoleTeam = userTeamRoleRepository.findByUser(user.get());

        for (int i =0 ; i < updatingUserRoleTeam.size(); i++){
            userTeamRoleRepository.delete(updatingUserRoleTeam.get(i));
        }

        User updatedUser = new User();
        user.get().setEmail(userDto.getEmail());
        user.get().setName(userDto.getName());
        user.get().setUsername(userDto.getUsername());
        if (!userDto.getStatus().equals(user.get().getStatus())) {
            user.get().setStatus(userDto.getStatus());
            user.get().setStatusAsOfDay(LocalDateTime.now().toString());
        }

        if (user.get() != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            updatedUser = userRepository.save(user.get());

        }

        if (userDto.getRoles().size() == userDto.getTeams().size()) {

            for (int i = 0; i < userDto.getRoles().size(); i++) {
                UserTeamRole userTeamRole = new UserTeamRole();
                userTeamRole.setUser(updatedUser);

                Team savedTeam = teamRepository.findByName(userDto.getTeams().get(i).getName())
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team  not find."));

                Role savedRole = roleRepository.findByName(userDto.getRoles().get(i).getName())
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

                userTeamRole.setRole(savedRole);
                userTeamRole.setTeam(savedTeam);
                userTeamRoleRepository.save(userTeamRole);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("Mismatch between roles and team assignment !"),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ResponseMessage("User Updated successfully!"), HttpStatus.OK);
    }

    public ResponseEntity<?> update(UserDtoSingle userDto) {
        Optional<User> user = userRepository.findById(Long.valueOf(userDto.getId()));
        User updatedUser = new User();
        user.get().setEmail(userDto.getEmail());
        user.get().setName(userDto.getName());
        user.get().setUsername(userDto.getUsername());
        if (!userDto.getStatus().equals(user.get().getStatus())) {
            user.get().setStatus(userDto.getStatus());
            user.get().setStatusAsOfDay(LocalDateTime.now().toString());
        }

        if (user.get() != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            updatedUser = userRepository.save(user.get());

        }

        UserTeamRole userTeamRole = new UserTeamRole();
        userTeamRole.setUser(updatedUser);
        Team savedTeam = teamRepository.findByName(userDto.getTeams())
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team  not find."));

        Role savedRole = roleRepository.findByName(userDto.getRoles())
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));

        userTeamRole.setRole(savedRole);
        userTeamRole.setTeam(savedTeam);
        userTeamRoleRepository.save(userTeamRole);

        return new ResponseEntity<>(new ResponseMessage("User Updated successfully!"), HttpStatus.OK);
    }

    public void delete(int id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        List<UserTeamRole>  updatingUserRoleTeam = userTeamRoleRepository.findByUser(user.get());
        for (int i =0 ; i < updatingUserRoleTeam.size(); i++){
            userTeamRoleRepository.delete(updatingUserRoleTeam.get(i));
        }
        userRepository.deleteById(Long.valueOf(id));
    }

}
