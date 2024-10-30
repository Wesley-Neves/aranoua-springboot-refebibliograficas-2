package com.example.refe_bibliograficas.repository;

import com.example.refe_bibliograficas.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
