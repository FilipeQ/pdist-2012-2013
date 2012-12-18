package Default;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Servidor.Dados;
import Servidor.Jogo;

public class Modelo extends Observable implements Runnable
{
	final String MSG_TIPO_1="ActUsersActivos";
	final String MSG_TIPO_2="ActParesActivos";
	final String MSG_TIPO_3="Jogar";
	final String MSG_TIPO_4="Convidar";
	final String MSG_TIPO_5="JogoAceite";
	final String MSG_TIPO_6="JogoRegeitado";
	
	private Dados dadosCliente,dadosServidor;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String user;
	private String login;
	private String mensagem;
	private Jogo jogo;
	private Vista vista;
	
	
	public Modelo(Socket s,Vista vista) throws IOException
	{
		out=new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		this.vista=vista;
		dadosCliente=new Dados();
		jogo = new Jogo();
	}
	
	
	public void enableButtons(boolean bool)
	{
		for(int i=0;i<vista.getJogo().size();i++)
		{
			vista.getJogo().get(i).setEnabled(bool);		
		}
		
		for(int i=0;i<vista.getBt().size();i++)
		{
			if(vista.getBt().get(i).getText().equalsIgnoreCase("convidar"))
			{
				vista.getBt().get(i).setEnabled(!bool);
				vista.getTbConvidar().setText("");
			}
		}
		notifyObservers(dadosCliente);	
	}
	
	public void actualizaEstadoButtons(List<JButton> jogo)
	{
		vista.setJogo(jogo);
		notifyObservers(dadosCliente);
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
		enableButtons(false);
		efectuarLogin();
		vista.setTitle(user);
		vista.setUser(user);
		vista.setVisible(true);
		
		System.out.println("Vou entrar no while");
		try 
		{
			while(true)
			{
					System.out.println("espera cliente");
					mensagem=(String)in.readObject();
					
					
					if(mensagem.equals(MSG_TIPO_1))
					{
						dadosCliente=(Dados)in.readObject();
						System.out.println(dadosCliente);
						//setChanged();
						//notifyObservers(dadosCliente);
						actualizaVista();
						continue;
					}
					else if(mensagem.equals(MSG_TIPO_4))
					{
						
						System.out.println("Vou mostrar JOptionpane");
						int resposta;
						mensagem=(String)in.readObject();
						resposta=JOptionPane.showConfirmDialog(vista, "Deseja jogar com "+mensagem+"?");
						System.out.println("Resposta:"+resposta);
						
						//mensagem=""+resposta;
						mensagem=new String(""+resposta);
						System.out.println("Mensagem Resp:"+mensagem);
						out.writeObject(mensagem);
						out.flush();
						out.reset();
						if(mensagem.equals("0"))
							enableButtons(true);
						
					}else if(mensagem.equals(MSG_TIPO_5))
					{
						JOptionPane.showMessageDialog(vista, "Jogo Aceite");
						enableButtons(true);
						jogar();
					}else if(mensagem.equals(MSG_TIPO_6))
					{
						JOptionPane.showMessageDialog(vista, "Jogo Regeitado");
					}
					
					

					
			}
		}
		catch (ClassNotFoundException e) {System.out.println(e);}
		catch (IOException e) {System.out.println(e);}
		
	}

	public void jogar()
	{
		
		while(true)
		{
			try {
				jogo=(Jogo)in.readObject();
				
				//actualiza vista
				
				if(verificaFimjogo()==-1)//caso termine o jogo volta po ciclo de espera acima
					break;
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int verificaFimjogo(){
		
		if(jogo.getFimJogo()!=-1)
		{
			if(jogo.getFimJogo()==1)
			{
				//acaba
				
			}
			else if(jogo.getFimJogo()==2)
			{
				
			}
			else if(jogo.getFimJogo()==3)
			{
				
			}
			return 0;
		}
		return -1;
	}
	
	public void actualizaVista(){
		setChanged();
		notifyObservers(dadosCliente);
	}
	//----------------Getters and Setters------------------

	public Dados getDadosCliente() {
		return dadosCliente;
	}


	public void setDadosCliente(Dados dadosCliente) {
		this.dadosCliente = dadosCliente;
	}


	public Dados getDadosServidor() {
		return dadosServidor;
	}


	public void setDadosServidor(Dados dadosServidor) {
		this.dadosServidor = dadosServidor;
	}


	public ObjectOutputStream getOut() {
		return out;
	}


	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}


	public ObjectInputStream getIn() {
		return in;
	}


	public void setIn(ObjectInputStream in) {
		this.in = in;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public Vista getVista() {
		return vista;
	}


	public void setVista(Vista vista) {
		this.vista = vista;
	}


	public String getMSG_TIPO_1() {
		return MSG_TIPO_1;
	}


	public String getMSG_TIPO_2() {
		return MSG_TIPO_2;
	}


	public String getMSG_TIPO_3() {
		return MSG_TIPO_3;
	}


	public String getMSG_TIPO_4() {
		return MSG_TIPO_4;
	}
	
	

}
