package hng_java_boilerplate.authentication.twofactor.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnableTwoFactorRequest(

        @NotBlank(message = "Password can not be blank")
        String password
) {
}
