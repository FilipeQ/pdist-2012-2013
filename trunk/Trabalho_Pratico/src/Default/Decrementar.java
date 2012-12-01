package Default;

import java.awt.event.ActionEvent;

public class Decrementar extends Controlador
{
	public Decrementar(Modelo modelo,Vista vista)
	{
		super(modelo,vista);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		System.out.println("Controller: acting on Model"+modelo.getContador());
		//System.out.println();
		modelo.decrementarValor();
		
	}


}
