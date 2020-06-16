package com.java.login.controller;

import com.java.login.dao.daoimpl.JDBCDaoImpl;
import com.java.login.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginController {
    JDBCDaoImpl jdbcDao = new JDBCDaoImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(){
        return jdbcDao.getPerson(1).getFirstName();
    }
}
