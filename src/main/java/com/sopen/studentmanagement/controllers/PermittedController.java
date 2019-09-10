package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.message.request.LoginForm;
import com.sopen.studentmanagement.message.response.JwtResponse;
import com.sopen.studentmanagement.message.response.ResponseMessage;
import com.sopen.studentmanagement.model.ConfirmationToken;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.ConfirmationTokenRepository;
import com.sopen.studentmanagement.security.jwt.JwtProvider;
import com.sopen.studentmanagement.security.services.EmailSenderService;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class PermittedController {
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;

  @Autowired
  private EmailSenderService emailSenderService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserService userService;

//  @Autowired
//  RoleService roleService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtProvider jwtProvider;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtProvider.generateJwtToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    return ResponseEntity.ok(jwtResponse);
  }

  @GetMapping(value = "confirm-account")
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    if (token != null) {
      User user = userService.findByEmailIgnoreCase(token.getUser().getEmail());
      if (user!=null){
        user.setEnabled(true);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),
                HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new ResponseMessage("Fail -> No user with this email has been found!"),
                HttpStatus.BAD_REQUEST);
      }

    } else {
      return new ResponseEntity<>(new ResponseMessage("Fail -> User's register occurred errors!"),
              HttpStatus.BAD_REQUEST);
    }

  }
}
