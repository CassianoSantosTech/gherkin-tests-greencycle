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
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ColetaExtraService {
    final ColetaExtraModel coletaExtraModel = new ColetaExtraModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";
    private final ObjectMapper mapper = new ObjectMapper();
    String idColetaExtra;

    public void setFieldsColetaExtra(String field, String value){
        switch (field) {
            case "dataColeta" -> coletaExtraModel.setDataColeta(value);
            case "idUsuario" -> coletaExtraModel.setIdUsuario(Integer.parseInt(value));
            case "idQrCode" -> coletaExtraModel.setIdQrCode(Long.parseLong(value));
            case "status" -> coletaExtraModel.setStatus(value);
            case "endereco" -> coletaExtraModel.setEndereco(value);
            case "telefone" -> coletaExtraModel.setTelefone(value);
            case "cpf" -> coletaExtraModel.setCpf(value);
            case "cnpj" -> coletaExtraModel.setCnpj(value);
            case "idColetaE" -> coletaExtraModel.setIdColetaE(value);
            default -> throw new IllegalStateException("Campo inválido: " + field);
        }
    }

    public void createColetaExtra(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaExtraModel);
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
            case "Cadastro bem-sucedido de coleta extra" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-coleta-extra.json");
            case "Edição bem-sucedida de coleta extra" -> jsonSchema = loadJsonFromFile(schemasPath + "edicao-bem-sucedido-de-coleta-extra.json");
            case "Listagem de coletas extras" -> jsonSchema = loadJsonFromFile(schemasPath + "listagem-coletas-extras.json");
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

    public void updateColetaExtra(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaExtraModel);
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

    public void retrieveIdColetaExtra() {
        idColetaExtra = response.jsonPath().getString("idColetaE");

        if (idColetaExtra == null || idColetaExtra.isEmpty()) {
            throw new IllegalStateException("ID da coleta não encontrada na resposta");
        }
    }

    public  void deleteColetaExtra(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idColetaExtra);
        response = given()
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void listColetaExtra(String endPoint) {
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
