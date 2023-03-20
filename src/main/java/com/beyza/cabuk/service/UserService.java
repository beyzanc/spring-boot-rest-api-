package com.beyza.cabuk.service;

import com.beyza.cabuk.dto.UserDto;
import com.beyza.cabuk.entity.User;
import com.beyza.cabuk.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

public interface UserService {

    // User createUser(User user); // No need to write it as it is defined as public abstract by default.
    UserDto  createUser(UserDto user);
    //  List<User> getUsers();
    List<UserDto> getUsers();
    // User getUser(Long id);
    UserDto getUser(Long id);
    // User updateUser(Long id, User user);
    UserDto updateUser(Long id,UserDto user);
    Boolean deleteUser(Long id);
    Page<User> pagination(int currentPage, int pageSize);            // server side pagination v0
    Page<User> pagination (Pageable pageable);                       // server side pagination v1
    Slice<User> slice(Pageable pageable);                            // server side pagination v2
    CustomPage<UserDto> customPagination(Pageable pageable);         // server side pagination v3


}
