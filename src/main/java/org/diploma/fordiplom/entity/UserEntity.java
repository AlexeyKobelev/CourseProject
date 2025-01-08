package org.diploma.fordiplom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("id_user")
    private long id_user;
    @Column(name = "email", nullable = false,unique = true)
    @Name("email")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name="position")
    @Name("position")
    private String position;
    @Column(name="organization")
    @Name("organization")
    private String organization;
    @Transient
    private String confirmPassword;
    @ManyToMany(mappedBy = "emails")
    private Set<TeamEntity> teams = new HashSet<>();
    @ManyToMany(mappedBy = "users")
    private Set<ProjectEntity> projects = new HashSet<>();
}
