package com.example.demo.services.position;

import com.example.demo.models.Child;
import com.example.demo.models.Position;

import java.util.List;

public interface PositionService {
    Position create(Position position);

    List<Position> readAll();

    Position readById(Long positionId);

    Position readByTitle(String title);

    boolean delete(Long id);

    boolean update(Long id, Position position);

    Position readByPositionTitle(String title);
}
