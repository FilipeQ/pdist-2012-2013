package Default;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Run 
{
	//static Socket s;
	public Run()
	{
		Modelo modelo;
		Vista vista;
		Socket s;
		Thread t1;
		
		try 
		{
			
			s=new Socket("127.0.0.1",5001);
			vista=new Vista();
			modelo=new Modelo(s,vista);
			modelo.addObserver(vista);
			t1=new Thread(modelo);
			t1.start();
			//vista.setVisible(true);
			Convidar con =new Convidar(modelo,vista);
			Jogar jog= new Jogar(modelo,vista);
			
			vista.setJogo(jog);//adicionar listeners aos botoes do jogo
			
			vista.setControlador(con, "Convidar");
		} 
		catch (UnknownHostException e){System.out.println(e);} 
		catch (IOException e){System.out.println(e);}

	}
}

