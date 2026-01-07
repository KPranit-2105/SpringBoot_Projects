package com.example.APIGatewayLMS;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Centralized fallback endpoints for Spring Cloud Gateway circuit breakers.
 *
 * Configure Gateway routes with:
 *  filters:
 *    - name: CircuitBreaker
 *      args:
 *        name: userServiceCB
 *        fallbackUri: forward:/fallback/user
 *
 * Similar for enrollment and course.
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FallbackResponse> userFallback(
            @RequestParam(name = "cause", required = false) String cause) {
        FallbackResponse body = FallbackResponse.of(
                "user-microservice",
                "User service is currently unavailable. Please try again later.",
                cause
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @GetMapping(value = "/enrollment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FallbackResponse> enrollmentFallback(
            @RequestParam(name = "cause", required = false) String cause) {
        FallbackResponse body = FallbackResponse.of(
                "enrollment-microservice",
                "Enrollment service is currently unavailable. Please try again later.",
                cause
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @GetMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FallbackResponse> courseFallback(
            @RequestParam(name = "cause", required = false) String cause) {
        FallbackResponse body = FallbackResponse.of(
                "course-microservice",
                "Course service is currently unavailable. Please try again later.",
                cause
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    /**
     * Generic DTO for fallback payloads.
     */
    public static class FallbackResponse {
        private String service;
        private String message;
        private String cause;
        private int status = HttpStatus.SERVICE_UNAVAILABLE.value();

        public FallbackResponse() {}

        public FallbackResponse(String service, String message, String cause) {
            this.service = service;
            this.message = message;
            this.cause = cause;
        }

        public static FallbackResponse of(String service, String message, String cause) {
            return new FallbackResponse(service, message, cause);
        }

        public String getService() { return service; }
        public void setService(String service) { this.service = service; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public String getCause() { return cause; }
        public void setCause(String cause) { this.cause = cause; }

        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
    }
}



