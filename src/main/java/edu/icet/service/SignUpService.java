package edu.icet.service;

import edu.icet.dto.User;

import java.util.List;

public interface SignUpService {
    void registerUser(User user);
    User getUser(String email);

    Long getUserCount();

    List<User> getAll();

    boolean checkEmailExists(String email);
}
