package com.example.demo.services.child;

import com.example.demo.models.Child;
import com.example.demo.models.PublicOrganization;

import java.util.List;

public interface ChildService {
    Child create(Child child);

    List<Child> readAll();

    boolean delete(Long id);

    boolean update(Long id, Child child);
}
