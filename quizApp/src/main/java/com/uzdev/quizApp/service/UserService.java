package com.uzdev.quizApp.service;

import com.uzdev.quizApp.model.Role;
import com.uzdev.quizApp.model.User;
import com.uzdev.quizApp.repository.RoleRepo;
import com.uzdev.quizApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId){
        Optional<User> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers(){
        return userRepo.findAll();
    }

    public boolean saveUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb!=null){
            return false;
        }

        user.setRoles(new HashSet<>(roleRepo.findAll()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }
    public boolean deleteUser(Long userId){
        if(userRepo.findById(userId).isPresent()){
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergetList(Long idMin){
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId",User.class)
                .setParameter("paramId",idMin).getResultList();
    }
}
