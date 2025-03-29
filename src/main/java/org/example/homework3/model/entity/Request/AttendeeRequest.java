package org.example.homework3.model.entity.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {


    @NotNull
    @NotBlank
    private String attendeeName;

    @NotNull
    @NotBlank
    @Email
    private String email;
}
