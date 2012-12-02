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
	public Run() 
	{
		Modelo modelo 	= new Modelo();
		Vista vista 	= new Vista();
		String login;
		Socket s;
		Thread t1,t2;
		BufferedReader in;
		PrintWriter out = null;
		ActualizarUsers au;
		ObjectInputStream oin=null;
		ObjectOutputStream oout=null;
		Dados d=new Dados();
		
		
		try 
		{
			System.out.println("Prestes a ler o socket");
			s=new Socket("127.0.0.1",5002);
			System.out.println("Li o socket");
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			//oin=new ObjectInputStream(s.getInputStream());
			//oout=new ObjectOutputStream(s.getOutputStream());
			out=new PrintWriter(s.getOutputStream());
			System.out.println("Prestes a entrar");
			do
			{
				System.out.println("Entrei");
				login=JOptionPane.showInputDialog("Intrdoduza o login :");
				System.out.println(login);
				
				//oout.writeObject(login);
				out.println(login);
				out.flush();
				//oout.flush();
				/*try {
					login=(String) oin.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				login=in.readLine();
				System.out.println("resposta:"+login);
				
			}while(login.equalsIgnoreCase("Nok"));
		
			modelo.addObserver(vista);
			
			System.out.println("Vou mostrar a janela");
			oin=new ObjectInputStream(s.getInputStream());
			System.out.println("S_1:"+s);
			System.out.println("OIN_1:"+oin);
			au=new ActualizarUsers(modelo,s,oin,d);
			au.addObserver(vista);
			
			t1=new Thread(au);
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
