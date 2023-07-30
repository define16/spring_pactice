package com.example.jqa.member.application.api;

import com.example.jqa.administrator.dao.AdministratorRepositoryInterface;
import com.example.jqa.administrator.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NaverLoginService {

    public NaverLoginService() {

    }

    public List<Administrator> findAll() {
        List<Administrator> administrators = new ArrayList<>();
        return administrators;
    }
}
