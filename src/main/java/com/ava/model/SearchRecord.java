package com.ava.model;


import com.ava.model.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRecord {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String country;

}
