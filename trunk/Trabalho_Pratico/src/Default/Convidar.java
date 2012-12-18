package Default;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Convidar extends Controlador  
{

	public Convidar(Modelo modelo, Vista vista) 
	{
		super(modelo, vista);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String nome="";
		
		nome=vista.getTbConvidar().getText();
		
		System.out.println("Convidar:"+nome);
		
		try {
			modelo.getOut().writeObject(nome);
			modelo.setSimbolo("X");
		} catch (IOException e1) {System.out.println(e1);}
		
	}

	
}
