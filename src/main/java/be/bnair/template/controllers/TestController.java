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
public class TestController {
    @GetMapping(path = "/test")
    public ResponseEntity getTestPage() {
        return ResponseEntity.ok().body("Success");
    }
}
