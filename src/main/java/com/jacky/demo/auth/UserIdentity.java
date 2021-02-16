package com.jacky.demo.auth;

import com.jacky.demo.entity.app_user.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserIdentity {

    private final SpringUser EMPTY_USER = new SpringUser(new AppUser());

    private SpringUser getSpringUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        AppUser appUser = new AppUser();
        appUser.setName(authentication.getName());
        SpringUser springUser = new SpringUser(appUser);
        return principal.equals("anonymousUser")
                ? EMPTY_USER
                : springUser;
    }

    public boolean isAnonymous() {
        return !EMPTY_USER.equals(getSpringUser());
    }

    public String getId() {
        return getSpringUser().getId();
    }

    public String getName() {
        return getSpringUser().getName();
    }

    public String getEmail() {
        return getSpringUser().getUsername();
    }
}
