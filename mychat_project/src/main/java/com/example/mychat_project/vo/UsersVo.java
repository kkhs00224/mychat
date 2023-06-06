package com.example.mychat_project.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UsersVo {
    @Id
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean enabled;

}
