package com.study.garibi.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "SignUp")
public class Member {
    @Id
    @Column
    private String id;
    @Column
    private String pw;
}
