package br.univel.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.univel.repository.PessoaRepository;

/**
 *
 * @author Alexandre H. Noro
 * @data 22 de nov de 2016 as 21:40:11
 */

@Named(value = "graficoPizzaPessoaController") // anotation que transforma a classe em um bean gerenciado pelo CDI.

@RequestScoped //Especifica que um bean é um escopo de solicitação.

/**
 * Classe responsável por realizar o controle e a consulta aos dados para montar o gráfico de pizza do tipo pessoa.
 */
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public void init() {

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/***
	 * Método que realiza a montagem do gráfico na página
	 */
	private void MontaGrafico() {

		//Realiza a consulta dos dados para montar o gráfico
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();

		// Informando os valores para montar o gráfico
		hashtableRegistros.forEach((chave, valor) -> {

			pieChartModel.set(chave, valor);

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");

	}
}
