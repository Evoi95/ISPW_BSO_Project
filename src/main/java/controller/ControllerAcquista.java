package controller;

import java.sql.SQLException;

import entity.Pagamento;
import database.GiornaleDao;
import database.LibroDao;
import database.PagamentoDao;
import database.RivistaDao;
import entity.factorybook.Giornale;
import entity.factorybook.Libro;
import entity.factorybook.Rivista;

public class ControllerAcquista {

	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private PagamentoDao pagD;
	private Libro l;
	private Giornale g;
	private Rivista r;
	private Pagamento p;
	private static SingeltonSystemState vis = SingeltonSystemState.getIstance() ;
	private String name;
	private int disp;
	
	public float totale(String isbn, int disp) {
		float x = (float) 0.0;
		try {
			lD.daiPrivilegi();
			// se semo fermati qua 
			l.setId(vis.getId());
			l.setDisponibilita(disp);
			x = lD.getCosto(l);

			System.out.println("ControllerAcquista");
			lD.aggiornaDisponibilita(l);
			lD.aggiornaCopieVendute(l,disp);
			
		
			
			

		} catch (SQLException e) {
		 
			
		}
		return x;
	}

	public float totaleG(String titolo, int disp) throws SQLException {
		float y = (float) 0.0;
		g.setTitolo(titolo);
		g.setDisponibilita(disp);
		gD.daiPrivilegi();
		y = gD.getCosto(g);
		gD.aggiornaDisponibilita(g);
		return y;

	}

	public float totaleR(String titolo, int disp) {
		float z = (float) 0.0;
		r.setTitolo(titolo);
		r.setCopieRim(disp);
		try {
			rD.daiPrivilegi();
			z = rD.getCosto(r);
			rD.aggiornaDisponibilita(r);
			
		} catch (SQLException e) {
		 
			
		}
		return z;

	}

	public ControllerAcquista()  {
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD = new RivistaDao();
		l = new Libro();
		g = new Giornale();
		r = new Rivista();
		pagD = new PagamentoDao();
		pagD.daiPrivilegi();

	}

	public int getIdL(String text) throws SQLException {
		l.setCodIsbn(text);
		return lD.retId( l);
		
		
	}
	
	public String getTipL(String text) throws SQLException
	{
		l.setId(Integer.parseInt(text));
		return lD.retTip(l);
	}
	
	public int getIdG(String text) throws SQLException {
		g.setTitolo(text);//l.setCodIsbn(text);
		return gD.retId( g);
		
		
	}
	
	public String getTipG(String text) throws SQLException
	{
		g.setTitolo(text);	
		return gD.retTip(g);
	}
	
	public int getIdR(String text) throws SQLException {
		r.setTitolo(text);//l.setCodIsbn(text);
		return rD.retId( r);
		
		
	}
	
	public String getTipR(String text) throws SQLException
	{
		r.setTitolo(text);	
		return rD.retTip(r);
	}
	
	public void inserisciAmmontareL(Float f) throws SQLException
	{
		p=new Pagamento(0, null, 0, null,  f, null, 0);
		p.setId(lD.retId(l));
		p.setTipo(lD.retTip(l));
		pagD.inserisciPagamento(p);

	}
	
	public void inserisciAmmontareG(Float f) throws SQLException
	{
		p=new Pagamento(0, null, 0, null,  f, null, 0);
		p.setId(gD.retId(g));
		p.setTipo(gD.retTip(g));
		pagD.inserisciPagamento(p);

	}

	public void inserisciAmmontareR(Float f) throws SQLException
	{
		p=new Pagamento(0, null, 0, null,  f, null, 0);
		p.setId(rD.retId(r));
		p.setTipo(rD.retTip(r));
		pagD.inserisciPagamento(p);

	}
	
	public String getType()
	{
		
		String S = vis.getType();
		System.out.println(S);
		return S;
	}

	public String getNomeById() throws SQLException
	{
		
		int id = vis.getId();
		String type =vis.getType();
		if(type.equals("libro"))
		{
			l.setId(id);
			name = lD.getNome(l);
		}
		else if(type.equals("giornale")) {
			g.setId(id);
			name = gD.getNome(g);
		}
		else if(type.equals("rivista"))
		{
			r.setId(id);
			name = rD.getNome(r);
			
		}
		return name ;
	}
	
	public int getDisp() throws SQLException
	{
		int id = vis.getId();
		String type =vis.getType();
		if(type.equals("libro"))
		{
		
			l.setId(id);
			disp = lD.getQuantita(l);
		}
		else if(type.equals("giornale")) {
			g.setId(id);
			disp = gD.getQuantita(g);
		}
		else if(type.equals("rivista"))
		{
			r.setId(id);
			disp = rD.getQuantita(r);
			
		}
		return disp ;
	}
	
	}
