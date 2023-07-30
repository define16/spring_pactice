package com.example.jqa.parameterstore.dao;

import com.example.jqa.parameterstore.domain.ParameterStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParameterStoreRepositoryInterface extends JpaRepository<ParameterStore, Long> {
    public ParameterStore save(ParameterStore parameterStore);
    public List<ParameterStore> findAll();
    public Optional<ParameterStore> findByParameterStoreNo(Long parameterStoreNo);
    public Optional<ParameterStore> findByParameterType(String parameterType);
}
