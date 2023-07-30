package com.example.jqa.administrator.application.service;

import com.example.jqa.administrator.dao.AdministratorRepositoryInterface;
import com.example.jqa.administrator.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepositoryInterface administratorRepository;

    public List<Administrator> findAll() {
        List<Administrator> administrators = new ArrayList<>();
        administratorRepository.findAll().forEach(e -> administrators.add(e));
        return administrators;
    }

    public Optional<Administrator> findById(Long adminNo) {
        return administratorRepository.findById(adminNo);
    }

    public void deleteById(Long adminNo) {
        administratorRepository.deleteById(adminNo);
    }

    public Administrator save(Administrator administrator) {
        administratorRepository.save(administrator);
        return administrator;
    }

    public void updateById(Long adminNo, Administrator administrator) {
        Optional<Administrator> e = administratorRepository.findById(adminNo);

        if (e.isPresent()) {
            e.get().setAdministratorNo(administrator.getAdministratorNo());
            e.get().setId(administrator.getId());
            e.get().setName(administrator.getName());
            administratorRepository.save(administrator);
        }
    }

}
