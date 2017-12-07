package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.models.*;

import com.example.demo.repositories.*;


import java.util.Arrays;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Autowired
    private PhotoRepository photoRepository;
    
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        
        Photo defaultProfile = new Photo();
        defaultProfile.setCreatedAt(new Date());
        defaultProfile.setFileName("person-placeholder.jpg");
        defaultProfile.setImage("<img src= 'https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png'/>");
     //defaultProfile.setImage("<img src='http://res.cloudinary.com/cloudskm/image/upload/c_scale,w_150/v1499965523/person-placeholder_bkkzw8.jpg'/>");
        photoRepository.save(defaultProfile);
        
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("bob@bob.com","bob","Bob","Bobberson", "Bob Bobberson" ,true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        user.setProfilePicture(defaultProfile);
        userRepository.save(user);

        user = new User("jim@jim.com","jim","Jim","Jimmerson", "Jim Jimmerson" , true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        user.setProfilePicture(defaultProfile);
        userRepository.save(user);

        user = new User("admin@secure.com","password","Admin","User", "Admin User" , true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
        user.setProfilePicture(defaultProfile);
        userRepository.save(user);

        user = new User("sam@every.com","password","Sam","Everyman", " Sam Everyman" , true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole, adminRole));
        user.setProfilePicture(defaultProfile);
        userRepository.save(user); 
  }
}
