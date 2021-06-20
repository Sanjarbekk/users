package com.example.employee.resource;

import com.example.employee.model.Employee;
import com.example.employee.resource.errors.BadRequestAlertException;
import com.example.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "employee";

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        log.debug("REST request to save Employee : {}", employee);
        if (employee.getId() != null) {
            throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
        log.debug("REST request to save Employee : {}", employee);
        if (employee.getId() == null) {
            throw new BadRequestAlertException("Id not found", ENTITY_NAME, "idexists");
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);
        Optional<Employee> result = employeeService.findOne(id);
        if(result.isEmpty()) {
            throw  new BadRequestAlertException("Not found", ENTITY_NAME, "idexists");
        }
        return ResponseEntity.ok(result.get());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> result = employeeService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.debug("Delete by Id : {}", id);
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }


}
