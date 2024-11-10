package edu.icet.controller;

import edu.icet.dto.User;
import edu.icet.service.SignUpService;
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

import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SignUpFormController {

    private final SignUpService service;
    @PostMapping("/add-user")
    public  ResponseEntity<Object> registerUser(@Valid @RequestBody User user){
          service.registerUser(user);
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "User saved successfully");
        }});

           }
    @GetMapping("/log-in")
    public ResponseEntity<User> getUsers(@RequestParam("email") String email){
        User user = service.getUser(email);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user) ;
    }

    @GetMapping("/get-user-count")
    public Long userCount(){
     return service.getUserCount();
    }

    @GetMapping("/get-all")
    public List<User> getAll(){
        return service.getAll();
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
