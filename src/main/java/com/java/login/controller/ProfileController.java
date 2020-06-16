package com.java.login.controller;

import com.java.login.dao.daoimpl.JDBCDaoImpl;
import com.java.login.entity.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profile")
public class ProfileController {
    JDBCDaoImpl jdbcDao = new JDBCDaoImpl();

    @GET
    @Produces("text/plain")
    public String getPersons(){
        String str = "Hello World";
        Person p = new Person();

        p = jdbcDao.getPerson(1);
        //return jdbcDao.getPerson(1).getFirstName();
        return p.toString();
    }
}
