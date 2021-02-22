package com.customjpa;

import com.customjpa.custonJPA.RepoService;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        UserRepo userRepo = RepoService.getRepo(UserRepo.class);
        userRepo.findByName("alex");
    }

}
