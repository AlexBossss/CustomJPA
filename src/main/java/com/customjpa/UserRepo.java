package com.customjpa;

import com.customjpa.custonJPA.CustomJPA;

public interface UserRepo extends CustomJPA<User, String> {

    User findByName(String name);
}
