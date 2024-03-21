package com.depertment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depertment.Model.Department;
import com.depertment.Repositry.DepartmentRepositry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {
	@Autowired
	private DepartmentRepositry departmentRepositry;

	public Department saveDepartment(Department department) {
		
		
		return departmentRepositry.save(department);
		
	}

	public Department getbyidDepartment(int id) {
		log.info("this department class for getting  repositry by id");
		return departmentRepositry.findById(id).get();
	}
	

}
