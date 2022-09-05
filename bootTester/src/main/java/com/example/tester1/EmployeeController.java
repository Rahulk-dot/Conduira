package com.example.tester1;

import com.example.tester1.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        final List<Employee> employeeList = new ArrayList<Employee>();
        Iterable<Employee> iterable= employeeRepository.findAll();
        iterable.forEach(employeeList::add);
        return employeeList;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getProductById(@PathVariable(value = "id") Long id){
            Optional<Employee> employee = employeeRepository.findById(id);

            return employee.isPresent()?new ResponseEntity<Employee>(employee.get(),HttpStatus.OK):new ResponseEntity("No data found",HttpStatus.NOT_FOUND);

    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateProduct(@PathVariable(value = "id") Long id,@RequestBody Employee newEmployee){
        Optional<Employee> employee= employeeRepository.findById(id);

        if(employee.isPresent()){
            Employee employee1=employee.get();
            employee1.setId(newEmployee.getId());
            employee1.setName(newEmployee.getName());
            employee1.setDepartment(newEmployee.getDepartment());
            employee1.setDOB(newEmployee.getDOB());

            employee1=employeeRepository.save(employee1);
            return ResponseEntity.ok().body(employee1);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long id){
        Optional<Employee> employee=employeeRepository.findById(id);

        if(employee.isPresent()){
            employeeRepository.delete(employee.get());
            return new ResponseEntity("Product has been deleted successfully.",HttpStatus.OK);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
