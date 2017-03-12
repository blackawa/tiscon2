package net.unit8.sigcolle.form;

import lombok.Data;
import net.unit8.sigcolle.validator.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Created by Shigeo KANEDA on 2017/03/12.
 */
@Data
public class UpdateForm {
    @NotNull
    @Length(min = 1, max = 50)
    @Email
    private String email;

    @NotNull
    @Length(min = 4, max = 20)
    @Password
    private String pass;
}
