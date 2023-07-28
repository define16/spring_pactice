package com.example.jqa.administrator.application;

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

    public Optional<Administrator> findById(Long mbrNo) {
        return administratorRepository.findById(mbrNo);
    }

    public void deleteById(Long mbrNo) {
        administratorRepository.deleteById(mbrNo);
    }

    public Administrator save(Administrator administrator) {
        administratorRepository.save(administrator);
        return administrator;
    }

    public void updateById(Long mbrNo, Administrator administrator) {
        Optional<Administrator> e = administratorRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setAdministratorNo(administrator.getAdministratorNo());
            e.get().setId(administrator.getId());
            e.get().setName(administrator.getName());
            administratorRepository.save(administrator);
        }
    }

}
