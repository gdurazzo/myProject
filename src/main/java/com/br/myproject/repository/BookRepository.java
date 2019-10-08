package com.br.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.myproject.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}