package Default;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Jogar extends Controlador
{

	public Jogar(Modelo modelo, Vista vista) 
	{
		super(modelo, vista);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		for(int i=0;i<vista.getJogo().size();i++)
		{
			if(vista.getJogo().get(i)==e.getSource())
			{
				vista.getJogo().get(i).setText(modelo.getSimbolo());
				modelo.getJogo().setPosicao(devolveLinha(i), devolveColuna( i), devolveSimbolo(modelo.getSimbolo()));//coloca jogada na matriz
				try {
					modelo.getOut().writeObject(modelo.getJogo());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	private int devolveSimbolo(String sim){
		if(modelo.getSimbolo().equalsIgnoreCase("X"))
			return 1;
		else
			return 2;
	}
	
	private int devolveLinha(int i){
		if(i==0 || i==1 || i==2)
			return 0;
		if(i==3 || i==4 || i==5)
			return 1;
		if(i==6 || i==7 || i==8)
			return 2;
		return -1;
	}
	
	private int devolveColuna(int i){
		if(i==0 || i==3 || i==6)
			return 0;
		if(i==1 || i==4 || i==7)
			return 1;
		if(i==2 || i==5 || i==8)
			return 2;
		return -1;
	}

}
