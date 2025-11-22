package com.example.demo.Repository;

import com.example.demo.Model.Mundo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MundosRepository extends JpaRepository<Mundo, Long> {
}
