package com.depertment.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depertment.Model.Department;

public interface DepartmentRepositry extends JpaRepository<Department, Integer> {

}
