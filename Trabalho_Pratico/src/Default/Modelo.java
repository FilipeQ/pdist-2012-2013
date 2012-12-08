package Default;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;

import javax.swing.JOptionPane;

import Servidor.Dados;

public class Modelo extends Observable implements Runnable
{
	final String MSG_TIPO_1="ActUsersActivos";
	final String MSG_TIPO_2="ActParesActivos";
	final String MSG_TIPO_3="Jogar";
	
	private Dados dadosCliente,dadosServidor;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String user;
	private String login;
	
	private Vista vista;
	
	
	public Modelo(Socket s,Vista vista) throws IOException
	{
		out=new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		this.vista=vista;
		dadosCliente=new Dados();
	}
	
	
	public void efectuarLogin()
	{
		try
		{
			do
			{
				user=JOptionPane.showInputDialog("Intrdoduza o login :");
				out.writeObject(user);
				out.flush();
				login=(String)in.readObject();
				
			}while(login.equalsIgnoreCase("Nok"));
		}
		catch(IOException e){System.out.println(e);}
		catch(ClassNotFoundException e){System.out.println(e);}
	}
	
	

	
	
	@Override
	public void run() 
	{
		efectuarLogin();
		vista.setVisible(true);
		System.out.println("Vou entrar no while");
		try 
		{
			while(true)
			{
					dadosCliente=(Dados)in.readObject();
					System.out.println("Vou actualizar os users");
					System.out.println(dadosCliente);
					if(dadosCliente.getMensagem().equalsIgnoreCase(MSG_TIPO_1));
					{
						setChanged();
						notifyObservers(dadosCliente);
					}
			}
		}
		catch (ClassNotFoundException e) {System.out.println(e);}
		catch (IOException e) {System.out.println(e);}
		
	}

}
