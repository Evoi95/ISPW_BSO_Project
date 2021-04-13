package controller;

import java.io.IOException;
import java.sql.SQLException;

import database.ContrassegnoDao;
import database.PagamentoDao;
import entity.Fattura;
import entity.Pagamento;

public class ControllerPagamentoCash {
	private ContrassegnoDao pD;
	private Fattura f;
	private PagamentoDao pagD;

	public void controlla(String nome, String cognome, String via, String com,float ammontare) throws IOException, SQLException {
		try {
			pD.daiPrivilegi();
			pagD.daiPrivilegi();

			System.out.println("\n\n");
			f.setNome(nome);
			f.setCognome(cognome);
			f.setVia(via);
			f.setCom(com);
			f.setAmmontare(pD.prendiSpesa());
			pD.inserisciFattura(f);
			
			Pagamento p=new Pagamento(0, "cash", 0, f.getNome(), f.getAmmontare(), null, 0);
			pagD.aggiornaPagamentoCash(p);
			
			
			//pagD.inserisciPagamento(p);
			
			
		} catch (Exception e) {
			e.getCause();
		}

	}

	public ControllerPagamentoCash()  {
		pD = new ContrassegnoDao();
		f = new Fattura();
		pagD=new PagamentoDao();
		
	}

}
