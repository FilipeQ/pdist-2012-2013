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
	List<String>usersActivos=null;
	List<String>paresActivos=null;
	ActualizarUsers(Modelo modelo,Socket s,ObjectInputStream oin,Dados d)
	{
		this.modelo=modelo;
		this.oin=oin;
		this.d=d;
		System.out.println("OIN_2:"+oin);
		System.out.println("S_2:"+s);
		this.s=s;
	}
	@Override
	public void run() 
	{
		//d=new Dados();
		
		while(true)
		{
			try 
			{
				oin=new ObjectInputStream(s.getInputStream());
				paresActivos=(List<String>) oin.readObject();
				usersActivos=(List<String>)oin.readObject();
				
				
				d.paresActivos=paresActivos;
				d.usersActivos=usersActivos;
				
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
