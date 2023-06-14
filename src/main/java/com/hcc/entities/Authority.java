package com.hcc.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority")
    private String authority;
    @Column(name = "`user`")
    private User user;

    public Authority() {
    }

    public Authority(Long id, String authority, User user) {
        this.id = id;
        this.authority = authority;
        this.user = user;
    }

    public Authority(String authority) {
        this.authority = authority;
    }


    @Override
    public String getAuthority() {
        return authority;
    }
}
