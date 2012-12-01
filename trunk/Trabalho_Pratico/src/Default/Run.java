package Default;


public class Run 
{
	public Run()
	{
		Modelo modelo 	= new Modelo();
		Vista vista 	= new Vista();
		vista.setVisible(true);
		
		
		modelo.addObserver(vista);
		
		//Controlador controlador = new Controlador(modelo,vista);
		//controlador.setModelo(modelo);
		//controlador.setVista(vista);
		//controlador.initVal(0);
		
		Incrementar inc = new Incrementar(modelo,vista);
		Decrementar dec = new Decrementar(modelo,vista);
		
		vista.setControlador(inc,"Convidar");
		vista.setControlador(dec,"Decrementar");
	}
}
