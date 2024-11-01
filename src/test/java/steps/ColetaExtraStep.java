package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.ColetaExtraService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColetaExtraStep {
    ColetaExtraService coletaExtraService = new ColetaExtraService();

    @Dado("que eu tenha os seguintes dados de coleta extra:")
    public void queEuTenhaOsSeguintesDadosDeColetaExtra(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            coletaExtraService.setFieldsColetaExtra(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de cadastro de coleta extra")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeColetaExtra(String endpoint) {
        coletaExtraService.createColetaExtra(endpoint);
    }

    @Então("o status code da resposta de coleta extra deve ser {int}")
    public void oStatusCodeDaRespostaDeColetaExtraDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, coletaExtraService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoParaColetaExtraÉO(String contract) throws IOException {
        coletaExtraService.setContract(contract);
    }

    @Então("a resposta da requisicao de coleta extra deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeColetaExtraDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = coletaExtraService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Dado("que eu tenha os seguintes dados de coleta extra para atualização:")
    public void queEuTenhaOsSeguintesDadosDeColetaExtraParaAtualizacao(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            coletaExtraService.setFieldsColetaExtra(columns.get("campo"), columns.get("valor"));
        }
    }

    @Dado("que eu recupere o idColeta criado no contexto:")
    public void queEuRecupereOIdCriadoNoContexto(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            coletaExtraService.setFieldsColetaExtra(columns.get("campo"), columns.get("valor"));
        }
        coletaExtraService.retrieveIdColetaExtra();
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de atualização de coleta extra")
    public void euEnviarARequisicaoParaOEndpointDeAtualizacaoDeColetaExtra(String endpoint) {
        coletaExtraService.updateColetaExtra(endpoint);
    }

    @Então("o status code da resposta de delecao deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, coletaExtraService.response.statusCode());
    }

    @Então("o status code da resposta de atualização de coleta extra deve ser {int}")
    public void oStatusCodeDaRespostaDeAtualizacaoDeColetaExtraDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, coletaExtraService.response.statusCode());
    }

    @Quando("eu enviar a requisicao com o ID para o endpoint {string} de delecao de coleta extra")
    public void euEnviarARequisicaoComOIDParaOEndpointDeDelecaoDeColetaExtra(String endPoint) {
        coletaExtraService.deleteColetaExtra(endPoint);
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de listagem de coleta extra")
    public void euEnviarARequisicaoParaOEndpointDeListagemDeColetaExtra(String endPoint) {
        coletaExtraService.listColetaExtra(endPoint);
    }

    @E("a resposta deve conter uma lista de coletas extras")
    public void aRespostaDeveConterUmaListaDeColetasExtras() {
        Assert.assertTrue("A resposta não contém uma lista de coletas extras", coletaExtraService.isResponseList());

    }

    @E("o arquivo de contrato esperado é o {string}")
    public void oArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        coletaExtraService.setContract(contract);
    }
}
