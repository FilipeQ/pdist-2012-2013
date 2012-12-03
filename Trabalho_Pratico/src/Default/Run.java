package Default;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import Servidor.Dados;

public class Run 
{
	//static Socket s;
	public Run() throws ClassNotFoundException 
	{
		Modelo modelo;
		Vista vista;
		Socket s;
		Thread t1;
		
		try 
		{
			
			s=new Socket("127.0.0.1",5001);
			vista=new Vista();
			modelo=new Modelo(s);
			modelo.addObserver(vista);
			t1=new Thread(modelo);
			t1.start();
			vista.setVisible(true);
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}


		
		//Controlador controlador = new Controlador(modelo,vista);
		//controlador.setModelo(modelo);
		//controlador.setVista(vista);
		//controlador.initVal(0);
		
		/*Incrementar inc = new Incrementar(modelo,vista);
		Decrementar dec = new Decrementar(modelo,vista);
		
		vista.setControlador(inc,"Convidar");
		vista.setControlador(dec,"Decrementar");*/
	}
}
