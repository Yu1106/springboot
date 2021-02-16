package com.jacky.demo.auth;

import com.jacky.demo.entity.app_user.AppUser;
import com.jacky.demo.entity.app_user.UserAuthority;
import com.jacky.demo.exception.NotFoundException;
import com.jacky.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AppUser appUser = appUserService.getUserByEmail(username);
            List<SimpleGrantedAuthority> authorities = convertToSimpleAuthorities(appUser.getAuthorities());

            return new User(appUser.getEmailAddress(), appUser.getPassword(), authorities);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }

    private List<SimpleGrantedAuthority> convertToSimpleAuthorities(List<UserAuthority> authorities) {
        return authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.name()))
                .collect(Collectors.toList());
    }

}