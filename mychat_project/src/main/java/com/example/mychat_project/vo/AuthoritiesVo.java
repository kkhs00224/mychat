package com.example.mychat_project.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class AuthoritiesVo {
    @Id
    @Column
    private String username;

    @Id
    @Column
    private String authority;

}
