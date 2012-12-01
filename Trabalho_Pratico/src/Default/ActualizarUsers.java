package Default;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

public class ActualizarUsers extends Observable implements Runnable
{
	Modelo modelo;
	Socket s;
	ObjectInputStream oin;
	//List<String>usersActivos;
	Dados d;
	ActualizarUsers(Modelo modelo,Socket s)
	{
		this.modelo=modelo;
		this.s=s;
	}
	@Override
	public void run() 
	{
		try 
		{
			oin=new ObjectInputStream(s.getInputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		while(true)
		{
			try 
			{
				//usersActivos=(List<String>)oin.readObject();
				d=(Dados)oin.readObject();
				
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}

}
