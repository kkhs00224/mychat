package com.example.mychat_project.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

@Data
@Entity
@Table(name="users")
public class UsersVo {
    @Id
    @Column
    @Cascade(CascadeType.REMOVE)
    private String username;
    @Column
    private String password;
    @Column
    @ColumnDefault("1")
    private boolean enabled=true;

}
