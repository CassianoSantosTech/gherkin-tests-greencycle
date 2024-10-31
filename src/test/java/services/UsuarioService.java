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
    String baseUrl = "http://localhost:8080";
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
            case "Edição bem-sucedida de usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "edicao-bem-sucedida-de-usuario.json");
            case "Listagem de usuários" -> jsonSchema = loadJsonFromFile(schemasPath + "listagem-de-usuarios.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        String responseBody = response.getBody().asString();

        // Verifica se a resposta é um array JSON
        JsonNode jsonResponseNode;
        if (responseBody.trim().startsWith("[")) {
            jsonResponseNode = mapper.readTree(responseBody); // Parse o array JSON diretamente
        } else {
            JSONObject jsonResponse = new JSONObject(responseBody);
            jsonResponseNode = mapper.readTree(jsonResponse.toString()); // Parse o objeto JSON
        }

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        return schema.validate(jsonResponseNode);
    }

    public void editUsuario(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(usuarioModel);
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

    public void listUsuarios(String endPoint) {
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

