package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ColetaExtraModel;
import model.ColetaFixaModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ColetaFixaService {
    final ColetaFixaModel coletaFixaModel = new ColetaFixaModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";
    private final ObjectMapper mapper = new ObjectMapper();
    String idColetaFixa;

    public void setFieldsColetaFixa(String field, String value){
        switch (field) {
            case "dataColeta" -> coletaFixaModel.setDataColeta(value);
            case "usuarioId" -> coletaFixaModel.setUsuarioId(Integer.parseInt(value));
            case "qrCodeId" -> coletaFixaModel.setQrCodeId(Long.parseLong(value));
            case "status" -> coletaFixaModel.setStatus(value);
            case "endereco" -> coletaFixaModel.setEndereco(value);
            case "telefone" -> coletaFixaModel.setTelefone(value);
            case "cpf" -> coletaFixaModel.setCpf(value);
            case "cnpj" -> coletaFixaModel.setCnpj(value);
            case "idColetaF" -> coletaFixaModel.setIdColetaF(value);
            default -> throw new IllegalStateException("Campo inválido: " + field);
        }
    }

    public void createColetaFixa(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaFixaModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodySend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de coleta fixa" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-coleta-fixa.json");
            case "Edição bem-sucedida de coleta fixa" -> jsonSchema = loadJsonFromFile(schemasPath + "edicao-bem-sucedido-de-coleta-fixa.json");
//            case "Listagem de coletas fixas" -> jsonSchema = loadJsonFromFile(schemasPath + "listagem-coletas-fixas.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        String responseBody = response.getBody().asString();
        JsonNode jsonResponseNode = mapper.readTree(responseBody);

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        return schema.validate(jsonResponseNode);
    }

    public void updateColetaFixa(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaFixaModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodySend)
                .when()
                .put(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdColetaFixa() {
        idColetaFixa = response.jsonPath().getString("idColetaE");

        if (idColetaFixa == null || idColetaFixa.isEmpty()) {
            throw new IllegalStateException("ID da coleta não encontrada na resposta");
        }
    }

    public  void deleteColetaFixa(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idColetaFixa);
        response = given()
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void listColetaFixa(String endPoint) {
        String url = baseUrl + endPoint;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public boolean isResponseList() {
        return response.jsonPath().getList("$").size() > 0;
    }

}
