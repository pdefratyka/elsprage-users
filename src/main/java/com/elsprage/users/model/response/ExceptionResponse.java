package com.elsprage.users.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionResponse {
    private LocalDateTime time;
    private String message;
    private HttpStatus httpStatus;

    public ExceptionResponse(String message, HttpStatus httpStatus) {
        this.time = LocalDateTime.now();
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
