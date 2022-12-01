package com.example.demo.services.child;

import com.example.demo.models.Child;
import com.example.demo.models.PublicOrganization;
import com.example.demo.models.UnionMember;
import com.example.demo.repositories.child.ChildJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    ChildJpaRepository childJpaRepository;

    @Override
    public Child create(Child child) {
        return childJpaRepository.save(child);
    }

    @Override
    public List<Child> readAll() {
        return childJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (childJpaRepository.existsById(id)) {
            childJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Child child) {
        if (childJpaRepository.existsById(id)) {
            child.setChildId(id);
            childJpaRepository.save(child);
            return true;
        }
        return false;
    }
}
