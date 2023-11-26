package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJDBCRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {
    private final UserJDBCRepository userJDBCRepository;

    public UserServiceV1(UserJDBCRepository userJDBCRepository) {
        this.userJDBCRepository = userJDBCRepository;
    }

    public void saveUser(UserCreateRequest request){
        userJDBCRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJDBCRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request){
        boolean isUserNotExist = userJDBCRepository.isUserNotExist(request.getId());
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        userJDBCRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){
        if(userJDBCRepository.isUserNotExist(name))
        {
            throw new IllegalArgumentException();
        }
        userJDBCRepository.deleteUser(name);
    }
}
