package com.example.mychat_project.vo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "authorities")
public class AuthoritiesVo implements Serializable {
    @EmbeddedId
    private AuthorityId authorityId;

}

// pk가 여러 개일 경우 Serialinable을 구현하고, @Embeddable을 통해 여러 아이디를 클래스로 만들고,  @EmbeddedID를 통해 가져온다.

