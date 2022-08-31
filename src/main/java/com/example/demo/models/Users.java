package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    @Transient
    private Integer age;
    private LocalDate birthDate;
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    private String username;
    private String fullName;

    public Users() {
    }

    public Users(
            long id,
            LocalDate birthDate,
            String email,
            String username,
            String fullName
    ) {
        this.id = id;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
    }

    public Users(
            LocalDate birthDate,
            String email,
            String username,
            String fullName
    ) {
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
