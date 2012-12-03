package Default;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Observable;

import Servidor.Dados;
public class ActualizarUsers extends Observable implements Runnable
{
	Modelo modelo;
	Socket s;
	ObjectInputStream inn;
	//List<String>usersActivos;
	Dados d;
	ActualizarUsers(Modelo modelo,Socket s,Dados d) throws UnknownHostException, IOException
	{
		if(s==null)
			System.out.println("erro está null");
		
		this.modelo=modelo;
		this.s=s;
		this.d=d;
		
		System.out.println("socket servidor: "+s.getInetAddress()+" : "+s.getPort());
		System.out.println("espera ...");
		
	}
	
	@Override
	public void run() 
	{
		
		while(true)
		{
			try 
			{
				inn = new ObjectInputStream(s.getInputStream());
				
				System.out.println("espera cliente");
				//usersActivos=(List<String>)oin.readObject();
				d=(Dados) inn.readObject();
				/*List<String> paresActivos = (List<String>) inn.readObject();
				d.setParesActivos(paresActivos);
				List<String> usersActivos = (List<String>) inn.readObject();
				d.setUsersActivos(usersActivos);*/
				
				
				System.out.println("Cliente"+d.getUsersActivos().get(0));
				
				if(d.getUsersActivos().size()!=modelo.getD().getUsersActivos().size())
				{
					modelo.getD().setUsersActivos(d.getUsersActivos());
					setChanged();
					notifyObservers(d);
				}
				else
				{
					for(int i=0;i<d.getUsersActivos().size();i++)
					{
						if(d.getUsersActivos().get(i)!=modelo.getD().getUsersActivos().get(i))
						{
							modelo.getD().setUsersActivos(d.getUsersActivos());
							setChanged();
							notifyObservers(d);
							break;
						}
					}
				}
			} catch (ClassNotFoundException e) 
			{
				System.out.println("erro: "+e.getCause());
				if(d==null)
					System.out.println("Dados = a null gggg");
				
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}

}
