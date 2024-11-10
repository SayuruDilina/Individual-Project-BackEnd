package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final  UserRepository repository;
    private final  ObjectMapper mapper;

    @Override
    public void registerUser(User user) {
        repository.save(mapper.convertValue(user,UserEntity.class));
    }

    @Override
    public User getUser(String email) {
       Optional<UserEntity> userEntity = repository.checkEmail(email);
        return mapper.convertValue(userEntity,User.class);
    }

    @Override
    public Long getUserCount() {
        return repository.count();
    }

    @Override
    public List<User> getAll() {
        List<User> users=new ArrayList<>();
        List<UserEntity> all = repository.findAll();
        all.forEach(userEntity -> {
            users.add(mapper.convertValue(userEntity, User.class));
        });
        return users;
    }
}
