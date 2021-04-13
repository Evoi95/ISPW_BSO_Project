package entity.factorybook;

enum MagazineCategory 
{	
	settimanale,Bisettimanale,mensile,
	bimestrale,trimestrale,annuale,
	estivo,invernale,sportivo,cinematografica,
	gossip,televisivo,militare,informatica
}

public class CategorieRivista {
	
	private MagazineCategory type;
	private Boolean isValid;

}
