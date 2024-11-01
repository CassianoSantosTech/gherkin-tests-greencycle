package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.ColetaExtraService;
import services.ColetaFixaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColetaFixaStep {
    ColetaFixaService coletaFixaService = new ColetaFixaService();

    @Dado("que eu tenha os seguintes dados de coleta fixa:")
    public void queEuTenhaOsSeguintesDadosDeColetaFixa(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            coletaFixaService.setFieldsColetaFixa(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de cadastro de coleta fixa")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeColetaFixa(String endpoint) {
        coletaFixaService.createColetaFixa(endpoint);
    }

    @Então("o status code da resposta de coleta fixa deve ser {int}")
    public void oStatusCodeDaRespostaDeColetaFixaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, coletaFixaService.response.statusCode());
    }

    @E("que o arquivo de contrato de coleta extra esperado é o {string}")
    public void queOArquivoDeContratoDeColetaExtraEsperadoÉO(String contract) throws IOException {
        coletaFixaService.setContract(contract);
    }

    @Então("a resposta da requisicao de coleta fixa deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeColetaFixaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = coletaFixaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
