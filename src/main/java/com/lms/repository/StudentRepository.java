package com.lms.repository;

import com.lms.model.Book;
import com.lms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> getByFirstName(String name);
}
