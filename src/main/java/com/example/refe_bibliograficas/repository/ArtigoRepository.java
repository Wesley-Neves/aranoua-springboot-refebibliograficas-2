package com.example.refe_bibliograficas.repository;

import com.example.refe_bibliograficas.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
}
