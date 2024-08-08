package hng_java_boilerplate.user.controller;

import hng_java_boilerplate.user.dto.request.*;
import hng_java_boilerplate.user.dto.response.ApiResponse;
import hng_java_boilerplate.user.dto.response.OAuthBaseResponse;
import hng_java_boilerplate.user.exception.UnAuthorizedUserException;
import hng_java_boilerplate.user.service.UserService;
import hng_java_boilerplate.util.FacebookJwtUtils;
import hng_java_boilerplate.util.GoogleJwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/request/token")
    public ResponseEntity<?> requestToken(@RequestBody EmailSenderDto emailSenderDto, HttpServletRequest request){
        userService.requestToken(emailSenderDto, request);
        return new ResponseEntity<>("Verification email sent successfully", HttpStatus.OK);
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
        } catch (UnAuthorizedUserException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/google")
    public ResponseEntity<OAuthBaseResponse> handleGoogleAuth(@RequestBody OAuthDto payload) {
        try {
            return ResponseEntity.ok(googleJwtUtils.googleOauthUserJWT(payload));
        } catch (UnAuthorizedUserException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}