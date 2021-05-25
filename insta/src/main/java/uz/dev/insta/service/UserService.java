package uz.dev.insta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.dev.insta.dto.UserDTO;
import uz.dev.insta.exceptions.UserExistException;
import uz.dev.insta.model.User;
import uz.dev.insta.model.enums.Role;
import uz.dev.insta.payload.request.SignUpRequest;
import uz.dev.insta.repository.UserRepository;
import uz.dev.insta.security.JWTTokenProvider;

import java.security.Principal;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(SignUpRequest userIn){
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        user.getRole().add(Role.ROLE_USER);

        try {
            LOG.info("Sqving user {} ", userIn.getEmail());
            return userRepository.save(user);
        }catch (Exception ex){
            LOG.error("Error during registration {} ", ex.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal){

        User user = getUserByPrincipal(principal);
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setBio(userDTO.getBio());

        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal){
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }


}
