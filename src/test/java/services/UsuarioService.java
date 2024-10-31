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
import model.UsuarioModel;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class UsuarioService {
    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
//    String baseUrl = "http://localhost:8080";
    String baseUrl = System.getenv("API_BASE_URL");
    String idUsuario;
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsUsuario(String field, String value){
        switch (field) {
            case "idUsuario" -> usuarioModel.setIdUsuario(Integer.parseInt(value));
            case "nome" -> usuarioModel.setNome(value);
            case "email" -> usuarioModel.setEmail(value);
            case "senha" -> usuarioModel.setSenha(value);
            case "role" -> usuarioModel.setRole(value);
            default -> throw new IllegalStateException("Campo invalido" + field);
        }
    }

    public void createUsuario(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(usuarioModel);
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

    public void retrieveIdUsuario() {
        idUsuario = response.jsonPath().getString("id");

        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new IllegalStateException("ID do usuário não encontrado na resposta");
        }
    }

    public  void deletesuario(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idUsuario);
        response = given()
                .when()
                .delete(url)
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
            case "Cadastro bem-sucedido de usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuario.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }


}

