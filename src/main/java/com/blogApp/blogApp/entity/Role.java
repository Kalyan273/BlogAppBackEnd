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
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String role;
    private String roleDesc;
    private String roleGroup;


    @OneToOne(mappedBy = "roleTable",cascade = CascadeType.ALL)
    private RoleMapping roleMapping;




}
