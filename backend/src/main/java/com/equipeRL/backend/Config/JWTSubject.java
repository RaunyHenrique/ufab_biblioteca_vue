package com.equipeRL.backend.Config;

import java.util.Collection;

public class JWTSubject {

    public String email;
    public Collection auth;

    public JWTSubject(String email, Collection auth) {
        this.email = email;
        this.auth = auth;
    }

}
