package hng_java_boilerplate.notification.dto.response;

import lombok.Builder;

@Builder
public record NotificationDtoRes(String status, String message, int status_code, NotificationDto data) {
}
