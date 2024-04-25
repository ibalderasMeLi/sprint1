package com.example.sprint1.repository;

import com.example.sprint1.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository{

    private static List<User> listOfUsers;

    public UserRepositoryImpl() throws IOException {
        loadDatabase();
    }

    private void loadDatabase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users;

        file= ResourceUtils.getFile("classpath:users.json");
        users = objectMapper.readValue(file,new TypeReference<List<User>>(){});

        listOfUsers = users;
    }

    public List<User> findAll(){
        return listOfUsers;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return listOfUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
