package com.java.login.classes;

import com.java.login.controller.LoginController;
import com.java.login.controller.ProfileController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(LoginController.class);
        h.add(ProfileController.class);
        return h;
    }
}
