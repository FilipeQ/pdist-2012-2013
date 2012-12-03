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


public class Run 
{
	//static Socket s;
	public Run() throws ClassNotFoundException 
	{
		Dados d = new Dados();
		Modelo modelo 	= new Modelo();
		Vista vista 	= new Vista();
		String login,logAux;
		Socket s;
		Thread t1,t2;
		//BufferedReader in;
		//PrintWriter out = null;
		ObjectOutputStream oout;
		ObjectInputStream inn;
		ActualizarUsers au;
		
		try 
		{
			
			s=new Socket("127.0.0.1",5001);
			//in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			//out=new PrintWriter(s.getOutputStream());
			oout=new ObjectOutputStream(s.getOutputStream());
			inn = new ObjectInputStream(s.getInputStream());
			
			do
			{
				login=JOptionPane.showInputDialog("Intrdoduza o login :");
				logAux=login;
				System.out.println(login);
				
				//out.println(login);
				//out.flush();
				d.setLogin(login);
				System.out.println("nome:"+d.getLogin());
				oout.writeObject(d.getLogin());
				oout.flush();
				
				//login=in.readLine();
				d.setLogin((String)inn.readObject());
				login=d.getLogin();
				System.out.println("resposta:"+d.getLogin());
				
			}while(login.equalsIgnoreCase("Nok"));
		
			d.setLogin(logAux);
			System.out.println("Vou mostrar a janela");
			au=new ActualizarUsers(modelo,s,d);
			au.addObserver(vista);
			//au.run();
			t1=new Thread(au);
			t1.start();
			
			vista.setVisible(true);
			
			
			modelo.addObserver(vista);
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
