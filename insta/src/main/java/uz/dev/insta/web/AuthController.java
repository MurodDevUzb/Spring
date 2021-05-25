package uz.dev.insta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.dev.insta.payload.request.LoginRequest;
import uz.dev.insta.payload.request.SignUpRequest;
import uz.dev.insta.payload.response.JWTTokenSuccessResponse;
import uz.dev.insta.payload.response.MessageResponse;
import uz.dev.insta.security.JWTTokenProvider;
import uz.dev.insta.security.SecurityConstants;
import uz.dev.insta.service.ResponseErrorValidation;
import uz.dev.insta.service.UserService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

     @Autowired
     private JWTTokenProvider jwtTokenProvider;
     @Autowired
     private AuthenticationManager authenticationManager;
     @Autowired
     private ResponseErrorValidation responseErrorValidation;
     @Autowired
     private UserService userService;

     @PostMapping("/signin")
     public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
         ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
         if(!ObjectUtils.isEmpty(errors)) return errors;

         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                 ));

         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
         return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));
     }

    @PostMapping("/signup")
     public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult){
         ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
         if(!ObjectUtils.isEmpty(errors)) return errors;

         userService.createUser(signUpRequest);
         return ResponseEntity.ok(new MessageResponse("User registered successfully"));

     }



}
