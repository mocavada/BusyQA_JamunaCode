package com.busyqa.crm.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDtoSingle {
    private Long id;

    @NotBlank
    @Size(min = 6, max = 40)
    private String name;

    @NotBlank
    @Size(min =  6, max = 40)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    private String teams;

    private String roles;

    private String status;

    private String statusAsOfDay;


    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals(StatusName.NO.name())){
            this.status = status;
        }

        else
        {
            this.status = StatusName.YES.name();
        }

    }

    public String getStatusAsOfDay() {
        return statusAsOfDay;
    }

    public void setStatusAsOfDay(String statusAsOfDay) {
        this.statusAsOfDay = LocalDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }
}
