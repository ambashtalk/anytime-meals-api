package com.anytimemeals.bff.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_addresses")
public class UserAddress implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_address_id")
    private Long id;

    private String home;
    private String area;
    private String street;
    private String landmark;
    private String state;
    private String country;
    private String city;
    private Integer pin;

}
