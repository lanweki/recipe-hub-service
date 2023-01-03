package com.recipehub.lanweki.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterRequest {

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @Nullable
    @NotNull
    private String firstName;

    @Nullable
    @NotBlank
    private String lastName;
}
