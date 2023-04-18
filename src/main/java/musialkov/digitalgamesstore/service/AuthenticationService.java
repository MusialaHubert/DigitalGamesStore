package musialkov.digitalgamesstore.service;

import musialkov.digitalgamesstore.dto.AuthenticationRequest;
import musialkov.digitalgamesstore.dto.AuthenticationResponse;
import musialkov.digitalgamesstore.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
