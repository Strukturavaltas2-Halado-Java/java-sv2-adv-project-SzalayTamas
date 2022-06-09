package com.example.javasv2advprojectszalaytamas.command.create;

import com.example.javasv2advprojectszalaytamas.entity.Contact;
import com.example.javasv2advprojectszalaytamas.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserCommand {
    private String email;
    private String ZipCode;
    private String Address;
    private String town;
    private int phoneNumbers;

//    private Contact contact;
}
