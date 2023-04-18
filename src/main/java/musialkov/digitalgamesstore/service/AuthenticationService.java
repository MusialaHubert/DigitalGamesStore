package musialkov.digitalgamesstore.service;

import musialkov.digitalgamesstore.dao.AuthenticationRequest;
import musialkov.digitalgamesstore.dao.AuthenticationResponse;
import musialkov.digitalgamesstore.dao.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
