package com.beyza.cabuk.api;

import com.beyza.cabuk.dto.UserDto;
import com.beyza.cabuk.entity.User;
import com.beyza.cabuk.service.UserService;

import com.beyza.cabuk.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user") // main path
public class UserController {

    // @Autowired   --With this annotation, we inject the reference we hold in the IoC to the following property.
    // anyway the following is a better practice method

    private final UserService userService;


    public UserController(UserService userService) {        // constructor. We could do the same with the @RequiredArgsConstructor annotation.
        this.userService = userService;
    }

    /*
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){        // With the @RequestBody annotation, we map JSON objects to user classes.
        User resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }*/

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    /*
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }


    /*
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
     */

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }


    /*
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User resultUser = userService.updateUser(id,user);
        return ResponseEntity.ok(resultUser);
    }
    */

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto user){
        UserDto resultUser = userService.updateUser(id,user);
        return ResponseEntity.ok(resultUser);
    }


    @GetMapping("/pagination")                 // server side pagination v0
    public ResponseEntity<Page<User>> pagination(@RequestParam int currentPage, @RequestParam int pageSize){
        return ResponseEntity.ok(userService.pagination(currentPage,pageSize));
    }

    @GetMapping("/pagination/v1")              // server side pagination v1
    public ResponseEntity<Page<User>> pagination(Pageable pageable){
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    @GetMapping("/pagination/v2")              // server side pagination v2
    public ResponseEntity<Slice<User>> slice(Pageable pageable){
        return ResponseEntity.ok(userService.slice(pageable));
    }

    @GetMapping("/pagination/v3")              // server side pagination v3
    public ResponseEntity<CustomPage<UserDto>> customPagination(Pageable pageable){
        return ResponseEntity.ok(userService.customPagination(pageable));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){

        Boolean status = userService.deleteUser(id);
        return ResponseEntity.ok(status);
    }

}
