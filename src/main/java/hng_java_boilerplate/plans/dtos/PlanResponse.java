package hng_java_boilerplate.plans.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import hng_java_boilerplate.plans.entity.Plan;

public record PlanResponse(
        Plan data,
        @JsonProperty("status_code")
        int statusCode,
        String message
) {
}
