package com.iteachart.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User_Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private User roleUser;

}
