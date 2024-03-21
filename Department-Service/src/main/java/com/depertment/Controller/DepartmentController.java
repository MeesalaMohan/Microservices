package com.depertment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.depertment.Model.Department;
import com.depertment.Service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/save")
	@ResponseStatus(code = HttpStatus.OK)
	public Department saveDepartment(@RequestBody Department department) {
		log.info("this department class for save the data");

		return departmentService.saveDepartment(department);
	}
	@GetMapping("/get/{id}")
	public Department getbyidDepartment(@PathVariable int id) {
		log.info("this department class for getting by id");
	return departmentService.getbyidDepartment(id);
	}
}
