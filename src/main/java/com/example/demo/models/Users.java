package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
public class Users implements UserDetails {

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
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 255, message = "Username has to be between 4 and 255 characters")
    private String username;
    @Column(nullable = false)
    @Size(min = 8, message = "Full name has to be greater than or equal to 8 characters")
    private String fullName;
    @Size(min = 8, message = "Password has to be greater than or equal to 8 characters")
    private String password;
    private boolean isEnabled=true;
    private boolean isAccountNonExpired=true;
    private boolean isAccountNonLocked=true;
    private boolean isCredentialsNonExpired=true;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public Users() {
    }

    public Users(
            LocalDate birthDate,
            String email,
            String username,
            String fullName,
            String password,
            boolean isEnabled,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            Collection<Role> roles
    ) {
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.roles = roles;
    }

    public Users(
            LocalDate birthDate,
            String email,
            String username,
            String fullName,
            String password,
            Collection<Role> roles
    ) {
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.roles = roles;
    }

    public Users(
            LocalDate birthDate,
            String email,
            String username,
            String fullName,
            String password
    ) {
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public Users(
            LocalDate birthDate,
            String email,
            String username,
            String fullName,
            String password,
            boolean isEnabled,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired
    ) {
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public Users(Users users) {
    }

    public Collection<Role> getRoles() {
        return roles;
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

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
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
                ", passowrd='" + password + '\'' +
                '}';
    }
}
