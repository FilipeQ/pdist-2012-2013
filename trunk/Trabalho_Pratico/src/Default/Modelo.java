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
	private String simbolo;
	
	
	public Modelo(Socket s,Vista vista) throws IOException
	{
		out=new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		this.vista=vista;
		dadosCliente=new Dados();
		jogo = new Jogo(dadosCliente);
		
	}
	
	
	public void enableButtons(boolean bool)
	{
		for(int i=0;i<vista.getJogo().size();i++)
		{
			vista.getJogo().get(i).setEnabled(bool);		
		}
		
		notifyObservers(dadosCliente);	
	}
	
	public void enableConvidar(boolean bool){
		for(int i=0;i<vista.getBt().size();i++)
		{
			if(vista.getBt().get(i).getText().equalsIgnoreCase("convidar"))
			{
				vista.getBt().get(i).setEnabled(bool);
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
						
						mensagem=new String(""+resposta);
						System.out.println("Mensagem Resp:"+mensagem);
						out.writeObject(mensagem);
						out.flush();
						out.reset();
						if(mensagem.equals("0"))
						{
							enableButtons(true);
							enableConvidar(false);
							simbolo="O";
							preencheBotoes();//inicializa botoes com ""
							actualizaVista();
							enableButtons(false);//coloca botoes bloqueados pk n e a vez dele jogar
							jogar();
						}
						
					}else if(mensagem.equals(MSG_TIPO_5))
					{
						JOptionPane.showMessageDialog(vista, "Jogo Aceite");
						enableButtons(true);
						enableConvidar(false);
						preencheBotoes();//inicializa botoes com ""
						actualizaVista();
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
		System.out.println("ta a jogar");
		while(true)
		{
			try {
				System.out.println("espera jogada...");
				jogo=(Jogo)in.readObject();
				System.out.println("ToString: "+jogo.getD().toString());
				dadosCliente=jogo.getD();
				enableButtons(true);
				preencheBotoes();
				actualizaVista();
				
				if(verificaFimjogo()==0)//caso termine o jogo volta po ciclo de espera acima
				{
					enableButtons(false);
					enableConvidar(true);
					break;
				}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void preencheBotoes()
	{
		int cont=-1;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				cont++;
				if(jogo.getPosicao(i, j)==1)
				{
					vista.getJogo().get(cont).setText("X");
				}else if(jogo.getPosicao(i, j)==2){
					vista.getJogo().get(cont).setText("O");
				}else if(jogo.getPosicao(i, j)==-1)
					vista.getJogo().get(cont).setText("");
			}
		}
	}
	
	public int verificaFimjogo(){
		
		if(jogo.getFimJogo()!=-1)
		{
			if(jogo.getFimJogo()==1 && simbolo.equalsIgnoreCase("X"))
			{
				JOptionPane.showMessageDialog(vista, "Ganhou o jogo");
				
			}
			else if(jogo.getFimJogo()==1 && simbolo.equalsIgnoreCase("O"))
			{
				JOptionPane.showMessageDialog(vista, "Perdeu o jogo");
			}
			else if(jogo.getFimJogo()==2 && simbolo.equalsIgnoreCase("X"))
			{
				JOptionPane.showMessageDialog(vista, "Perdeu o jogo");
			}
			else if(jogo.getFimJogo()==2 && simbolo.equalsIgnoreCase("O"))
			{
				JOptionPane.showMessageDialog(vista, "Ganhou o jogo");
			}
			else if(jogo.getFimJogo()==3)
			{
				JOptionPane.showMessageDialog(vista, "Empatou o jogo");
			}
			jogo.colocaMatrizInicializada();
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


	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}


	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}


	/**
	 * @return the jogo
	 */
	public Jogo getJogo() {
		return jogo;
	}


	/**
	 * @param jogo the jogo to set
	 */
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	

}
