package be.bnair.template.controllers;

import be.bnair.template.services.UserService;
import be.bnair.template.models.entities.security.UserEntity;
import be.bnair.template.models.AuthResponse;
import be.bnair.template.models.LoginForm;
import be.bnair.template.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    private final JwtUtil utils;
    private final PasswordEncoder passwordEncoder;
    private final UserService securityService;

    public SecurityController(JwtUtil utils, PasswordEncoder passwordEncoder, UserService securityService) {
        this.utils = utils;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
    }

    @PostMapping(path = {"/login", "/signIn"})
    public ResponseEntity<AuthResponse> signInAction(
            HttpServletRequest request,
            @RequestBody LoginForm form
    ) {
        System.out.println(request);
        UserDetails user = this.securityService.loadUserByUsername(form.username);

        if (passwordEncoder.matches(form.password, user.getPassword())) {
            return ResponseEntity.ok(new AuthResponse(utils.generateToken(user), user));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping(path = {"/register", "signUp"})
    public ResponseEntity<AuthResponse> getregisterAction(
            @RequestBody LoginForm form
    ) {
        UserEntity entity = new UserEntity();
        entity.setUsername(form.username);
        entity.setPassword(passwordEncoder.encode(form.password));

        UserDetails user = this.securityService.insert(entity);
        return ResponseEntity.ok(new AuthResponse(utils.generateToken(user), user));
    }

    @PostMapping(path = {"/register"})
    public ResponseEntity<AuthResponse> registerAction(
            @RequestBody LoginForm form
    ) {
        UserEntity entity = new UserEntity();
        entity.setUsername(form.username);
        entity.setPassword(passwordEncoder.encode(form.password));

        UserDetails user = this.securityService.insert(entity);
        return ResponseEntity.ok(new AuthResponse(utils.generateToken(user), user));
    }
}
