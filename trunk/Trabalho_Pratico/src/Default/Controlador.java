package Default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener
{
	protected Modelo modelo;
	protected Vista vista;
	
	
	public Controlador(Modelo modelo,Vista vista)
	{
		this.modelo=modelo;
		this.vista=vista;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		//System.out.println("Controller: acting on Model");
		//System.out.println(modelo.getContador());
		//modelo.incrementarValor();
		
	}
	
	public void initVal(int x)
	{
		modelo.setContador(x);
	}

}


