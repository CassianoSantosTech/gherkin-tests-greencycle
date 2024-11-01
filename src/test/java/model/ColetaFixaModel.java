package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColetaFixaModel {
    @Expose(serialize = false)
    private String idColetaF;
    @Expose
    private String dataColeta;
    @Expose
    private int usuarioId;
    @Expose
    private long qrCodeId;
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
