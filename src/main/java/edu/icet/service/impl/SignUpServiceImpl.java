package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.User;

import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;

import edu.icet.service.JWTService;
import edu.icet.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository repository;
    private final ObjectMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    @Override
    public void registerUser(User user) {
        repository.save(mapper.convertValue(user, UserEntity.class));
    }

    @Override
    public User getUser(String email) {
        Optional<UserEntity> userEntity = repository.checkEmail(email);
        return mapper.convertValue(userEntity, User.class);
    }

    @Override
    public Long getUserCount() {
        return repository.count();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<UserEntity> all = repository.findAll();
        all.forEach(userEntity -> users.add(mapper.convertValue(userEntity, User.class)));
        return users;
    }

    @Override
    @Transactional
    public boolean checkEmailExists(String email) {
        return repository.checkEmail(email).isPresent();
    }


    public String verify(User user,String rawPassword) {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),rawPassword));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            return "fail";
        }

    }

}
