package com.mukham.employee.repository;

import com.mukham.employee.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PositionRepository extends JpaRepository <Position, Long> {
}
