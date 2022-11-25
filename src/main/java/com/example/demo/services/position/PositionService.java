package com.example.demo.services.position;

import com.example.demo.models.Position;

import java.util.List;

public interface PositionService {
    Position create(Position position);

    List<Position> readAll();

    Position readById(Long positionId);
}
