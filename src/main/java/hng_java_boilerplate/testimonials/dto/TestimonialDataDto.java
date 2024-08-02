package hng_java_boilerplate.testimonials.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class TestimonialDataDto {
    @JsonProperty("user_id")
    private String user_id;
    private String name;
    private String content;
    @JsonProperty("created_at")
    private LocalDate created_at;
    @JsonProperty("updated_at")
    private LocalDate updated_at;
}