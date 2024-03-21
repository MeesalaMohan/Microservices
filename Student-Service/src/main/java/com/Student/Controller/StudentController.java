package com.Student.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Exception.StudentException;
import com.Student.Model.Student;
import com.Student.Service.StudentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j

public class StudentController {
	@Autowired
	private StudentService studentService;

	private StudentException studentException;

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.OK)
	public Student saveStudent(@RequestBody Student student) {
		log.info("enter the Student class save the data");
		return studentService.saveStudent(student);
	}

	@GetMapping("/getdetials/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CircuitBreaker(name = "studentservice", fallbackMethod = "fallbackmethod")
	@Retry(name = "studentservice")
	@TimeLimiter(name = "studentservice")
	public CompletableFuture<Object> getStudentWithDepartment(@PathVariable int id) {
		log.info("this student class for getting by id");
		return CompletableFuture.supplyAsync(() -> studentService.getStudentWithDepartment(id));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	public CompletableFuture<Object> fallbackmethod(@PathVariable int id, RuntimeException e) {
		String pattern = "did not have any student.*in this the id";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(e.getMessage());

		return CompletableFuture.supplyAsync(() -> {
			System.out.println(e.getMessage());

			if (matcher.matches()) {
				return e.getMessage();
			}

			else {
				// Your existing code here
				return "server busy right now try again after some time";

			}
		});
	}
}
