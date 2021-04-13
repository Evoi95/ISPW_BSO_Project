package controller;

public class ControllerRicercaPerTipo {

	private SingeltonSystemState vis=SingeltonSystemState.getIstance();
	
	public ControllerRicercaPerTipo() 
	{
		//singeltonSystemState vis=singeltonSystemState.getIstance();

	}
	
	public boolean setRicercaL()
	{
		
		if (vis.getType().equals("libro"))
			return true;
		else
			return false;
	}
	public boolean setRicercaG()
	{
		SingeltonSystemState.getIstance().setTypeAsDaily();
		if (SingeltonSystemState.getIstance().getType().equals("giornale"))
			return true;
		else
			return false;
	}
	public boolean setRicercaR()
	{
		SingeltonSystemState.getIstance().setTypeAsMagazine();
		if (SingeltonSystemState.getIstance().getType().equals("rivista"))
			return true;
		else
			return false;
	}
}
