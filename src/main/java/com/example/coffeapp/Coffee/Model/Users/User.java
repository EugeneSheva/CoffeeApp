package com.example.coffeapp.Coffee.Model.Users;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 10, message = "Поле должно содержать 10 цифр. Пример: 0630636363.")
    private String username;

    @NotEmpty(message = "Заполните поле.")
    @Size(min = 6, message = "Минимум 6 символаов.")
    private String password;

    private boolean active;

    @NotEmpty(message = "Заполните поле.")
    @Size(min = 4, message = "Минимум 4 символа.")
    private String name;
    private String role;

    @Past(message = "Выберите актуальную дату рождения.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfRegistry;

    @NotEmpty(message = "Выберите язык.")
    private String language;

    public List getRolesList() {
        List<GrantedAuthority> authorities = Arrays.stream(this.role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }
}
