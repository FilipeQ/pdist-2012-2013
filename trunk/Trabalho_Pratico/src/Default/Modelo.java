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
	
	
	private Dados d,dd;
	private ObjectOutputStream oout;
	private ObjectInputStream inn;
	private Socket s;
	private String login;
	private String logAux;
	

	
	public Modelo(Socket s)
	{
		this.s=s;
		try 
		{
			d=new Dados();
			oout=new ObjectOutputStream(s.getOutputStream());
			inn = new ObjectInputStream(s.getInputStream());
			efectuarLogin();
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void efectuarLogin()
	{
		try 
		{
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
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void actualizarUsersActivos()
	{
		if(dd.getUsersActivos().size()!=d.getUsersActivos().size())
		{
			d.setUsersActivos(dd.getUsersActivos());
			setChanged();
			notifyObservers(d);
		}
		else
		{
			for(int i=0;i<dd.getUsersActivos().size();i++)
			{
				if(dd.getUsersActivos().get(i)!=d.getUsersActivos().get(i))
				{
					d.setUsersActivos(dd.getUsersActivos());
					setChanged();
					notifyObservers(d);
					break;
				}
			}
		}
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
				dd=(Dados) inn.readObject();
				
				if(dd.getMensagem().equalsIgnoreCase(MSG_TIPO_1));
				{
					actualizarUsersActivos();
				}
				
			}
			catch (ClassNotFoundException e) 
			{
				System.out.println("erro: "+e.getCause());
				if(d==null)
					System.out.println("Dados = a null gggg");
				
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	//--------------Getters & Setters----------------
	
	public Dados getDd() 
	{
		return dd;
	}

	public void setDd(Dados dd) 
	{
		this.dd = dd;
	}

	public ObjectOutputStream getOout() 
	{
		return oout;
	}

	public void setOout(ObjectOutputStream oout) 
	{
		this.oout = oout;
	}

	public ObjectInputStream getInn() 
	{
		return inn;
	}

	public void setInn(ObjectInputStream inn) 
	{
		this.inn = inn;
	}

	public Socket getS() 
	{
		return s;
	}

	public void setS(Socket s) 
	{
		this.s = s;
	}

	public String getLogin() 
	{
		return login;
	}

	public void setLogin(String login) 
	{
		this.login = login;
	}

	public String getLogAux() 
	{
		return logAux;
	}

	public void setLogAux(String logAux) 
	{
		this.logAux = logAux;
	}

	public String getMSG_TIPO_1() 
	{
		return MSG_TIPO_1;
	}

	public String getMSG_TIPO_2() 
	{
		return MSG_TIPO_2;
	}

	public String getMSG_TIPO_3() 
	{
		return MSG_TIPO_3;
	}

	public Dados getD() 
	{
		return d;
	}

	public void setD(Dados d) 
	{
		this.d = d;
	}

	

}
