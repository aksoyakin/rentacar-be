package dev.akinaksoy.rentacar.services;

import dev.akinaksoy.rentacar.business.dtos.SignupRequest;
import dev.akinaksoy.rentacar.business.dtos.UserDto;
import dev.akinaksoy.rentacar.dataaccess.abstracts.UserRepository;
import dev.akinaksoy.rentacar.entities.concretes.User;
import dev.akinaksoy.rentacar.entities.concretes.enums.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null){
            User newAdminAccount = new User();
            newAdminAccount.setName("Admin");
            newAdminAccount.setUserRole(UserRole.ADMIN);
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(newAdminAccount);
        }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());

        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        user.setUserRole(UserRole.CUSTOMER);

        User createdCustomer = userRepository.save(user);

        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdCustomer.getId());
        createdUserDto.setName(createdCustomer.getName());
        createdUserDto.setEmail(createdCustomer.getEmail());
        createdUserDto.setUserRole(createdCustomer.getUserRole());
        return createdUserDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
