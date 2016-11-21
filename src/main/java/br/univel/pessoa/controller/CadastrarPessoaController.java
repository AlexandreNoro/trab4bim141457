package br.univel.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.univel.model.PessoaModel;
import br.univel.repository.PessoaRepository;
import br.univel.usuario.controller.UsuarioController;
import br.univel.uteis.Uteis;
/**
 *
 * @author Alexandre H. Noro
 * @data 17 de nov de 2016 as 21:21:18
 */
@Named(value = "cadastrarPessoaController") //anotation que transforma a classe em um bean gerenciado pelo CDI.
@RequestScoped //Especifica que um bean é um escopo de solicitação.

/**
 * Classe com métodos de controle da pessoa e injeção de dependencias nos objetos da classe.
 */
public class CadastrarPessoaController {

	@Inject //Realiza a injeção de depêndencia dos beans.
	PessoaModel pessoaModel;

	@Inject //Realiza a injeção de depêndencia dos beans.
	UsuarioController usuarioController;

	@Inject //Realiza a injeção de depêndencia dos beans.
	PessoaRepository pessoaRepository;

	private UploadedFile file;


	/**
	 * Retorna um objeto do tipo file
	 * @return
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Passado o valor de "file" por parâmetro para o atributo da classe.
	 * @param file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Retorna um objeto do tipo pessoaModel
	 * @return
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Passado o valor de "pessoaModel" por parâmetro para o atributo da classe.
	 * @param pessoaModel
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Realiza a gravação de um novo registro via input
	 */
	public void SalvarNovaPessoa() {

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		// Aqui está informando que o cadastro foi via input
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");

	}


	/**
	 * Realiza o upload de arquivos
	 */
	 public void UploadRegistros() {

		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		 try {


			 if(this.file.getFileName().equals("")){
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }

			 DocumentBuilder builder = factory.newDocumentBuilder();

	                 Document doc = builder.parse(this.file.getInputstream());

	                 Element element = doc.getDocumentElement();

	                 NodeList nodes = element.getChildNodes();

	                for (int i = 0; i < nodes.getLength(); i++) {

	        	     Node node  = nodes.item(i);

	        	    if(node.getNodeType() == Node.ELEMENT_NODE){

	        		 Element elementPessoa =(Element) node;

	        		 //Pegando os valores dos arquivos XML
	        		 String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

	        		 PessoaModel newPessoaModel = new PessoaModel();

	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);

	        		 //Salvando um registro que veio do arquivo XML
	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel);
	        	   }
	              }



		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}


	 }



}