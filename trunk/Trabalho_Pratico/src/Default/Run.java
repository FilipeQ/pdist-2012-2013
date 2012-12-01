package Default;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class Run 
{
	public Run() 
	{
		Modelo modelo 	= new Modelo();
		Vista vista 	= new Vista();
		String login;
		Socket s=null;
		Thread t1,t2;
		BufferedReader in;
		PrintWriter out = null;
		
		try 
		{
			s=new Socket("127.0.0.1",5002);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new PrintWriter(s.getOutputStream());
		
			do
			{
				login=JOptionPane.showInputDialog("Intrdoduza o login :");
				System.out.println(login);
				
				out.println(login);
				out.flush();
				
				login=in.readLine();
				System.out.println("resposta:"+login);
				
			}while(login.equalsIgnoreCase("Nok"));
		
			
			System.out.println("Vou mostrar a janela");
			t1=new Thread(new ActualizarUsers(modelo,s));
			t1.start();
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

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
