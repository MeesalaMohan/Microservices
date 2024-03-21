package com.Student.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Student.Exception.StudentException;
import com.Student.Model.Department;
import com.Student.Model.Student;
import com.Student.Repositry.StudentRepositry;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
private StudentRepositry studentRepositry;
	//String url="http://localhost:8080/Department/get/";
	String url="http://DEPARTMENTSERVICE/department/get/";
	public Student saveStudent(Student student) {
		
		return studentRepositry.save(student);
	}
	public String getStudentWithDepartment(int id) {
		log.info("entry  the repositry by id");
		Student student=studentRepositry.findById(id).orElseThrow(()->new StudentException("did not have any student"+id+" in this the id"));
		log.info("after executing find by id"+student.toString());
		Department department=	restTemplate.getForObject(url+student.getDepartmentId(), Department.class);
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append(student.getStudentId()+"  "+student.getFirstName()+ "  "+student.getLastName()+"  "+student.getEmail()+"  "+student.getDepartmentId());
		buffer.append("\n");
		buffer.append(department.getDepartmentName()+"  "+department.getDepartmentCode()+"  "+department.getDepartmentAddress());
		log.info("Student with department details :"+buffer.toString());
		return buffer.toString();
		
	}


}
