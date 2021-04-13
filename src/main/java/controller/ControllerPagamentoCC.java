package controller;

import java.sql.Date;
import java.sql.SQLException;

import database.CartaCreditoDao;
import database.PagamentoDao;
import javafx.collections.ObservableList;
import entity.CartaCredito;
import entity.Pagamento;

public class ControllerPagamentoCC {
	private CartaCreditoDao cDao;
	private String appoggio = "";
	private Boolean state;
	private CartaCredito cc;
	private PagamentoDao pD;

	public boolean controllaPag(String d, String c,String civ) {

		appoggio = appoggio + d;
		int anno = Integer.parseInt((String) appoggio.subSequence(0, 4));
		int mese = Integer.parseInt((String) appoggio.subSequence(5, 7));
		int giorno = Integer.parseInt((String) appoggio.subSequence(8, 10));
		 int cont=0;
		
		if (anno > 2020 && (mese >= 1 && mese <= 12) && (giorno >= 1 && giorno <= 31) && c.length() <= 20 ) {
			
			if(civ.length()==3)
				{
					String verifica[]= c.split("-");
					
					for (int x=0; x<verifica.length; x++) {
							if(verifica[x].length()==4)
							{
								cont++;
							}
					}
					if (cont==4)
					{
						state=true;
					}
					else {
						state=false;
					}
				}

		} else {
			state = false;

		}
		return state;

	}

	public ControllerPagamentoCC() throws Exception {
		cDao = new CartaCreditoDao();
		cDao.daiPrivilegi();
		pD=new PagamentoDao();
		pD.daiPrivilegi();
	}

	public void aggiungiCartaDB(String n, String c, String cod, java.util.Date data, String civ, float prezzo)
			throws SQLException {
		try {
			cc = new CartaCredito(n, c, cod, (Date) data, civ, 0);
			
			System.out.println("\n\n\t CC: \n\n"+cc.getAmmontare());

			cc.setPrezzoTransazine(cDao.prendiSpesa());
			cDao.insCC(cc);
			
			Pagamento p=new Pagamento(0,"cc",0,cc.getUserNome(),cc.getPrezzoTransazine(),null,0);
			pD.aggiornaPagamentoCC(p);
			
			
			


		} catch (SQLException e) {
			e.getCause();
		}

	}

	public ObservableList<CartaCredito> ritornaElencoCC(String nomeU) throws SQLException {
		
		// System.out.println("Chiamata nel controller :"+cDao.getCarteCredito(nomeU));
		return cDao.getCarteCredito(nomeU);
	}
	
	public CartaCredito tornaDalDb(String codiceCarta) throws SQLException
	{
		cc=new CartaCredito();
		cc.setNumeroCC(codiceCarta);
		return cDao.popolaDati(cc);
	}

}
