package com.example.jqa.administrator.dao;

import com.example.jqa.administrator.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorRepositoryInterface extends JpaRepository<Administrator, Long> {
    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public List<Administrator> findById(String id);

    public List<Administrator> findByName(String name);

    //like검색도 가능
    public List<Administrator> findByNameLike(String keyword);
}
