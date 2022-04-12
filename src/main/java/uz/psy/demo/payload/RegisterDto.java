package uz.psy.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @Size(min = 3, max = 200)
    @NotNull
    private String firstName;

    @Size(min = 3, max = 200)
    @NotNull
    private String lastName ;

    @NotNull
    private String password;

    @NotNull
    private int age;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String role;


}
