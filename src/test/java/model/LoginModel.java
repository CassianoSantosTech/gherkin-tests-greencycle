package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class LoginModel {
    @Expose
    private String role;
    @Expose
    private int idUsuario;
    @Expose
    private String emailLogin;
    @Expose
    private String senhaLogin;
}
