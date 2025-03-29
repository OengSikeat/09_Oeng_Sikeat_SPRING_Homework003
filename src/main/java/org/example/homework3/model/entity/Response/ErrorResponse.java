package org.example.homework3.model.entity.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus status;
}
