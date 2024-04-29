package org.example.kkumdoriland.member.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthMemberDTO extends User {
    private String email;
    private String name;


    public AuthMemberDTO(String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.email = username;
        this.name = name;
    }

}
