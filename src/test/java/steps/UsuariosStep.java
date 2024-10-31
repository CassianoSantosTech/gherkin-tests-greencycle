package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsuariosStep {
    UsuarioService usuariosService = new UsuarioService();

    @Dado("que eu tenha os seguintes dados de usuario:")
    public void queEuTenhaOsSeguintesDadosDeUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            usuariosService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de cadastro de usuario")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeUsuario(String endpoint) {
        usuariosService.createUsuario(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, usuariosService.response.statusCode());
    }

    @Dado("que eu recupere o id criado no contexto:")
    public void queEuRecupereOIdCriadoNoContexto(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            usuariosService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
        usuariosService.retrieveIdUsuario();
    }

    @Quando("eu enviar a requisicao com o ID para o endpoint {string} de delecao de usuario")
    public void euEnviarARequisicaoComOIDParaOEndpointDeDelecaoDeUsuario(String endPoint) {
        usuariosService.deletesuario(endPoint);
    }

    @E("que o que o arquivo de contrato esperado é o {string}")
    public void queOQueOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        usuariosService.setContract(contract);
    }

    @Então("a resposta da requisicao deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = usuariosService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());

    }

    @Dado("que eu tenha os seguintes dados de usuario para edição:")
    public void queEuTenhaOsSeguintesDadosDeUsuarioParaEdicao(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            usuariosService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de edição de usuario")
    public void euEnviarARequisicaoParaOEndpointDeEdicaoDeUsuario(String endpoint) {
        usuariosService.editUsuario(endpoint);
    }

    @Quando("eu enviar a requisição para o endpoint {string} de listagem de usuários")
    public void euEnviarARequisicaoParaOEndpointDeListagemDeUsuarios(String endpoint) {
        usuariosService.listUsuarios(endpoint);
    }

    @Então("a resposta deve conter uma lista de usuários")
    public void aRespostaDeveConterUmaListaDeUsuarios() {
        Assert.assertTrue("A resposta não contém uma lista de usuários", usuariosService.isResponseList());
    }

}
