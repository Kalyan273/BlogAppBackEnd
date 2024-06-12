package com.blogApp.blogApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLEMAPPING")
public class RoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleMapId;


    @OneToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role roleTable;


}
