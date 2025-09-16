// File: src/main/java/info5153/exercises/server/employee/EmployeeController.java
package info5153.exercises.server.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // READ all employees
    @GetMapping("/api/employees")
    public ResponseEntity<Iterable<Employee>> findAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<Iterable<Employee>>(employees, HttpStatus.OK);
    }

    // UPDATE an existing employee
    @PutMapping("/api/employees")
    public ResponseEntity<Employee> updateOne(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
    }
}