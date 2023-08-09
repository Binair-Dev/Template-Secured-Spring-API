package be.bnair.template.models;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;


@RequiredArgsConstructor
public class AuthResponse {
    public final String token;
    public final UserDetails user;
}
