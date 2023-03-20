package com.beyza.cabuk.service.impl;

import com.beyza.cabuk.advice.UserNotFound;
import com.beyza.cabuk.dto.UserDto;
import com.beyza.cabuk.entity.User;
import com.beyza.cabuk.repository.UserRepository;
import com.beyza.cabuk.service.UserService;
import com.beyza.cabuk.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor    // to create a constructor automatically
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /*
    @Override
    public User createUser(User user) {
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");

        return userRepository.save(user);
    }
     */

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");

        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    /*
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
     */

    @Override
    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return dtos;
    }
    /*

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }
     */

    @Override
    public UserDto getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        // return null;
        throw new UserNotFound("User not found!");
        //throw new IllegalArgumentException("Dikkat! Kullanıcı bulunamadı!");
        //throw new RuntimeException("Kullanıcı bulunamadı!");
    }

    /*
    @Override
    public User updateUser(Long id, User user) {
        Optional<User> resultUser = userRepository.findById(id);
        if (resultUser.isPresent()){
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdateAt(new Date());
            resultUser.get().setUpdatedBy("Admin");

            return userRepository.save(resultUser.get());
        }
        return null;
    }
    */

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> resultUser = userRepository.findById(id);
        if (resultUser.isPresent()){
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdateAt(new Date());
            resultUser.get().setUpdatedBy("Admin");

            return modelMapper.map(userRepository.save(resultUser.get()),UserDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    // server side pagination v0
    @Override
    public Page<User> pagination(int currentPage, int pageSize) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return userRepository.findAll(pageable);
    }

    // server side pagination v1
    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // server side pagination v2
    @Override
    public Slice<User> slice(Pageable pageable) {       // more performance
        return userRepository.findAll(pageable);
    }


    // server side pagination v3
    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        UserDto[] dtos = modelMapper.map(data.getContent(),UserDto[].class);
        return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }
}
