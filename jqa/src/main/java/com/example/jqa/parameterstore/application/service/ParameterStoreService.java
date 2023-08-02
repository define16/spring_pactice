package com.example.jqa.parameterstore.application.service;

import com.example.jqa.parameterstore.dao.ParameterStoreRepositoryInterface;
import com.example.jqa.parameterstore.domain.ParameterStore;
import com.example.jqa.parameterstore.dto.ParameterStoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParameterStoreService {
    @Autowired
    private ParameterStoreRepositoryInterface parameterStoreRepository;

    public ParameterStore save(ParameterStore parameterStore) {
        parameterStoreRepository.save(parameterStore);
        return parameterStore;
    }

    public List<ParameterStore> findAll() {
        List<ParameterStore> parameterStores = new ArrayList<>();
        parameterStoreRepository.findAll().forEach(e -> parameterStores.add(e));
        return parameterStores;
    }

    public Optional<ParameterStore> findByParameterStoreNo(Long parameterStoreNo) {
        Optional<ParameterStore> parameterStore = parameterStoreRepository.findByParameterStoreNo(parameterStoreNo);
        return parameterStore;
    }

    public Optional<ParameterStore> findByParameterType(String parameterType) {
        Optional<ParameterStore> parameterStore = parameterStoreRepository.findByParameterType(parameterType);
        return parameterStore;
    }


    public void updateByParameterStoreNo(Long parameterStoreNo, ParameterStore parameterStore) {
        Optional<ParameterStore> e = parameterStoreRepository.findByParameterStoreNo(parameterStoreNo);

        if (e.isPresent()) {
            e.get().setParameterType(parameterStore.getParameterType());
            e.get().setParameterValue(parameterStore.getParameterValue());
            parameterStoreRepository.save(parameterStore);
        }
    }

    public void updateByParameterType(String parameterStoreType, ParameterStoreDto.Parameter parameter) {
        Optional<ParameterStore> e = parameterStoreRepository.findByParameterType(parameterStoreType);

        if (e.isPresent()) {
            e.get().setParameterType(parameter.getParameterType());
            e.get().setParameterValue(parameter.getParameterValue());
            parameterStoreRepository.save(e.get());
        }
    }

}
