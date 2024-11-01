package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.LoginService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginStep {
    LoginService loginService = new LoginService();

    @Dado("que eu tenha os seguintes dados de login:")
    public void queEuTenhaOsSeguintesDadosDeLogin(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            loginService.setFieldsLogin(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de cadastro de login")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeLogin(String endpoint) {
        loginService.createLogin(endpoint);
    }

    @Então("o status code da resposta de login deve ser {int}")
    public void oStatusCodeDaRespostaDeLoginDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, loginService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado para login é o {string}")
    public void queOArquivoDeContratoEsperadoParaLoginÉO(String contract) throws IOException {
        loginService.setContract(contract);
    }

    @Então("a resposta da requisicao de login deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeLoginDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = loginService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
