package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColetaExtraModel {
    @Expose(serialize = false)
    private String idColetaE;
    @Expose
    private String dataColeta;
    @Expose
    private int idUsuario;
    @Expose
    private long idQrCode;
    @Expose
    private String status;
    @Expose
    private String endereco;
    @Expose
    private String telefone;
    @Expose
    private String cpf;
    @Expose
    private String cnpj;
}
