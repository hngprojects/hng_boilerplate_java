package hng_java_boilerplate.user.controller;

import hng_java_boilerplate.user.dto.request.LoginDto;
import hng_java_boilerplate.user.dto.request.OAuthDto;
import hng_java_boilerplate.user.dto.request.SignupDto;
import hng_java_boilerplate.user.dto.response.ApiResponse;
import hng_java_boilerplate.user.exception.UnAuthorizedUserException;
import hng_java_boilerplate.user.service.UserService;
import hng_java_boilerplate.util.GoogleJwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthController {

    private final UserService userService;
    private final GoogleJwtUtils googleJwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupDto signupDto){
            return userService.registerUser(signupDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse> handleGoogleAuth(@RequestBody OAuthDto payload) {
        try {
            return ResponseEntity.ok(googleJwtUtils.googleOauthUserJWT(payload));
        } catch (UnAuthorizedUserException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
