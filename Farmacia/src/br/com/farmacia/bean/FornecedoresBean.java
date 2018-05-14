//fazer comunicaçao parte web com banco de dados. java vx xhtml
package br.com.farmacia.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.domain.Fornecedores;

@ManagedBean(name = "MBFornecedores") //uma forma de passar referencia  usara para chamar em vez de chamar fornecedoresbean 
@ViewScoped
public class FornecedoresBean {

	private ListDataModel<Fornecedores>itens;
//acessar forma externa o itens
	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}
	
}
