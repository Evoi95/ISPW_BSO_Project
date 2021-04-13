package controller;

// this is singelton battona 
// know evreything about the system

public class SingeltonSystemState {
	
	 private int id;
	 private String type;
	 private boolean isLogged ;
	 private boolean isSearch;
	 private boolean isPickup;
	 private static SingeltonSystemState instance=new SingeltonSystemState() ;
	 
	 private SingeltonSystemState()
	 {
		 
	 }
	 
	 public static SingeltonSystemState getIstance()
	 {
		 if (instance == null) 
		 {
			 return new SingeltonSystemState();
		 }
	 return instance;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setTypeAsBook()
	{
		this.type = "libro";
	}
	public void setTypeAsMagazine()
	{
		this.type = "rivista";
	}
	public void setTypeAsDaily()
	{
		this.type = "giornale";
	}
	public String getType()
	{
		return type;
	}

	public boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public boolean getIsPickup() {
		return isPickup;
	}

	public void setPickup(boolean isPickup) {
		this.isPickup = isPickup;
	}
	
}
