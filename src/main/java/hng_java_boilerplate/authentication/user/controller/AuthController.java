package hng_java_boilerplate.authentication.user.controller;

import hng_java_boilerplate.exception.exception_class.UnauthorizedException;
import hng_java_boilerplate.authentication.user.dto.request.LoginDto;
import hng_java_boilerplate.authentication.user.dto.request.OAuthDto;
import hng_java_boilerplate.authentication.user.dto.request.SignupDto;
import hng_java_boilerplate.authentication.user.dto.response.ApiResponse;
import hng_java_boilerplate.authentication.user.dto.response.OAuthBaseResponse;
import hng_java_boilerplate.authentication.user.service.UserService;
import hng_java_boilerplate.util.FacebookJwtUtils;
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
    private final FacebookJwtUtils facebookJwtUtils;
    private final GoogleJwtUtils googleJwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupDto signupDto){
        return userService.registerUser(signupDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        return userService.loginUser(loginDto);
    }

    @PostMapping("/facebook")
    public ResponseEntity<ApiResponse> handleFacebookAuth(@RequestBody OAuthDto payload) {
        try {
            ApiResponse savedPayload = facebookJwtUtils.facebookOauthUserJWT(payload);
            return new ResponseEntity<>(savedPayload, HttpStatus.CREATED);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/google")
    public ResponseEntity<OAuthBaseResponse> handleGoogleAuth(@RequestBody OAuthDto payload) {
        try {
            return ResponseEntity.ok(googleJwtUtils.googleOauthUserJWT(payload));
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}