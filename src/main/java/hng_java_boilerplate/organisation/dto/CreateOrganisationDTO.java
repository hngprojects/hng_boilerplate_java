package hng_java_boilerplate.organisation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrganisationDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email can no be blank")
    private String email;

    @NotBlank(message = "Industry is mandatory")
    private String industry;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "State is mandatory")
    private String state;
}