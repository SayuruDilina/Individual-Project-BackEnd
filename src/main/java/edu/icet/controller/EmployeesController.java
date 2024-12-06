package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.EmailService;
import edu.icet.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeesController {

    private  final EmployeeService service;
    private final EmailService emailService;
    @PostMapping("/register-employee")
    public ResponseEntity<Object> registerEmployee(@Valid @RequestBody Employee employee){
        service.registerEmployee(employee);
        emailService.sendMail(employee.getEmployeeName(),employee.getEmail());
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "User saved successfully");
        }});
    }

    @GetMapping("/get-all-employees")
    public  List<Employee> getAll(){
        return service.getAllEmployees();
    }

    @GetMapping("/get-employees-count")
    public  Long getMemberCount(){
        return service.getEmployeeCount();
    }

    @PutMapping("/update-employee")
    public  void updateEmployee(@RequestBody Employee employee){
        service.updateEmployee(employee);
    }
    @DeleteMapping("/delete-employee/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        service.deleteEmployee(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }
}
