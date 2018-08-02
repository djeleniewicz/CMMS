package pl.dominik.cmms.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public	class CurrentUser extends User {

    private final pl.dominik.cmms.entity.security.User user;
public CurrentUser(String username, String	password, Collection<?
        extends GrantedAuthority> authorities, pl.dominik.cmms.entity.security.User
        user) {

        super(username,	password,	authorities);
        this.user	= user;
    }

    public	pl.dominik.cmms.entity.security.User	getUser()	{return	user;}
}