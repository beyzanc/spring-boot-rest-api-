package com.beyza.cabuk.repository;

import com.beyza.cabuk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// No need to say @Repository here because when we extend the JpaRepository, it realizes that it is a Repository and creates an instance and keeps it in the IoC container.
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    If the desired query is not in the JpaRepository, we can customize it in the following ways:
    User findByFirstName(String firstName);

    If we cannot make the query we want in this way, we can use the @Query annotation. ex:
    @Query("SELECT * FROM account WHERE name = :name")
    List<Account> findAccountsByName(String name);
    @Modifying                                                     ❶
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(long id, BigDecimal amount);

    */

}
