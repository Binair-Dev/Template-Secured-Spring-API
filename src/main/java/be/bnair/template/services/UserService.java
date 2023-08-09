package be.bnair.template.services;

import be.bnair.template.models.entities.security.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails insert(UserEntity entity);
}
