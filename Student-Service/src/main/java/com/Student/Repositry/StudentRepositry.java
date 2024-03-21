package com.Student.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Student.Model.Student;

@Repository
public interface StudentRepositry  extends JpaRepository<Student, Integer>{

}
