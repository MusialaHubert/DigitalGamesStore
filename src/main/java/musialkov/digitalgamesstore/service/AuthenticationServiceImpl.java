package musialkov.digitalgamesstore.service;

import lombok.RequiredArgsConstructor;
import musialkov.digitalgamesstore.dao.AuthenticationRequest;
import musialkov.digitalgamesstore.dao.AuthenticationResponse;
import musialkov.digitalgamesstore.dao.RegisterRequest;
import musialkov.digitalgamesstore.entity.Role;
import musialkov.digitalgamesstore.entity.ShopUser;
import musialkov.digitalgamesstore.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("Can not register");
        }

        ShopUser shopUser = ShopUser.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(shopUser);
        String jwtToken = jwtService.generateToken(shopUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        ShopUser shopUser = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException(request.getEmail() + " not found"));

        String jwtToken = jwtService.generateToken(shopUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
