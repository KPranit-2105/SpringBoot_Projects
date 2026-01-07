package com.example.APIGatewayLMS;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gateway.fallback")
public class GatewayFallbackProperties {

    private String userMessage = "User service is currently unavailable. Please try again later.";
    private String enrollmentMessage = "Enrollment service is currently unavailable. Please try again later.";
    private String courseMessage = "Course service is currently unavailable. Please try again later.";

    public String getUserMessage() { return userMessage; }
    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }

    public String getEnrollmentMessage() { return enrollmentMessage; }
    public void setEnrollmentMessage(String enrollmentMessage) { this.enrollmentMessage = enrollmentMessage; }

    public String getCourseMessage() { return courseMessage; }
    public void setCourseMessage(String courseMessage) { this.courseMessage = courseMessage; }
}
