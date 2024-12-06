package edu.icet.controller;

import edu.icet.dto.Response;
import edu.icet.dto.User;
import edu.icet.service.EmailService;
import edu.icet.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class SignUpFormController {

    private final SignUpService service;
    private final EmailService emailService;
    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    @PostMapping("/add-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        service.registerUser(user);
        emailService.sendMailToNewUser(user.getUserName(), user.getEmail());
        return ResponseEntity.ok().body(new HashMap<String, String>() {{
            put("message", "User saved successfully");
        }});

    }

    @GetMapping("/log-in")
    public ResponseEntity<Response> getUsers(@RequestParam("email") String email
            ,@RequestParam("password") String password) {
        User user = service.getUser(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            String verify = service.verify(user,password);
            Response response=new Response(user,verify);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @GetMapping("/get-user-count")
    public Long userCount() {
        return service.getUserCount();
    }

    @GetMapping("/get-all-users")
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/email-exists-check/{email}")
    public ResponseEntity<String> checkExistsEmail(@PathVariable String email) {
        boolean emailExists = service.checkEmailExists(email);
        if (emailExists) {
            return ResponseEntity.ok("Your email Already Registered");
        } else {
            return ResponseEntity.ok("Ok");
        }
    }

    @GetMapping("/send-otp/{email}")
    public ResponseEntity<Object> sendOtp(@PathVariable String email) {
        emailService.sendOtp(email);
        return ResponseEntity.ok().body("otp send successfully");
    }

    @PutMapping("/reset-password")
    public Boolean resetPassword(@RequestParam("otp") String otp,
                                 @RequestParam("password") String password
    ) {

        return emailService.verifyOtp(otp, password);
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
