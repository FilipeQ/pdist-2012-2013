package Default;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Servidor.Dados;

public class Vista extends JFrame implements Observer
{
	JTextArea tbParesActivos;
	/**
	 * 
	 */
	//Controlador controlador;
	JTextField tbConvidar;
	JTextArea tbUsersActivos;
	List<JButton> bt;
	Container c;
	Modelo modelo;
	String user;
	Box bLateral;
	Box bBaixo;
	JPanel pJogo;
	
	List<JButton> jogo;
	public Vista()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c=getContentPane();
		setSize(500,500);
		bLateral= Box.createHorizontalBox();
		bBaixo=Box.createHorizontalBox();
		pJogo= new JPanel();
		//setVisible(true);
		UIManager.getSystemLookAndFeelClassName();
		jogo=new ArrayList<>();
		for(int i=0;i<9;i++)
		{
			jogo.add(new JButton(" "));
			jogo.get(i).setPreferredSize(new Dimension(120,100));
			jogo.get(i).setMaximumSize(new Dimension(120,100));
			jogo.get(i).setMinimumSize(new Dimension(120,100));
			
		}
		
		tbUsersActivos = new JTextArea("0");
		tbUsersActivos.setEnabled(false);
		tbConvidar = new JTextField("");
		
		tbParesActivos=new JTextArea("");
		tbParesActivos.setEnabled(false);
		bt=new ArrayList<>();
		
		
		tbUsersActivos.setPreferredSize(new Dimension(250,100));
		tbUsersActivos.setMaximumSize(new Dimension(250,100));
		tbUsersActivos.setMinimumSize(new Dimension(250,100));
		
		
		tbParesActivos.setPreferredSize(new Dimension(250,100));
		tbParesActivos.setMaximumSize(new Dimension(250,100));
		tbParesActivos.setMinimumSize(new Dimension(250,100));



		
		bt.add(new JButton("Convidar"));
		bt.get(0).setPreferredSize(new Dimension(100,30));
		bt.get(0).setMaximumSize(new Dimension(100,30));
		bt.get(0).setMinimumSize(new Dimension(100,30));
		
		tbConvidar.setPreferredSize(new Dimension(400,30));
		tbConvidar.setMaximumSize(new Dimension(400,30));
		tbConvidar.setMinimumSize(new Dimension(400,30));
		
		
		c.setLayout( new BorderLayout());
		//pLateral.setLayout( new FlowLayout());
		
		c.add(bLateral, BorderLayout.PAGE_START);
		c.add(pJogo,BorderLayout.CENTER);
		pJogo.setLayout(new FlowLayout());
		
		for(int i=0;i<9;i++)
		{
			pJogo.add(jogo.get(i));
		}
		
		//bLateral.add(Box.createHorizontalBox());
		bLateral.add(tbUsersActivos);
		bLateral.add(tbParesActivos);
		//bLateral.add(Box.createHorizontalBox());
		
		c.add(bBaixo, BorderLayout.PAGE_END);
		bBaixo.add(bt.get(0));
		bBaixo.add(tbConvidar);

		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{	
		invalidate();
		Dados d=(Dados)arg1;
		String texto="";
		System.out.println("Tamanho:"+d.getUsersActivos().size());
		for(int i=0;i<d.getUsersActivos().size();i++)
		{
			System.out.println("user: "+user);
			if(!user.equalsIgnoreCase(d.getUsersActivos().get(i).toString()))
				texto+=""+d.getUsersActivos().get(i).toString()+"\n";
				//System.out.println("Texto:"+texto);
			
		}
		tbUsersActivos.setText(""+texto);
		texto=new String("");
		
		for(int i=0;i<d.getParesActivos().size();i++)
		{
			//System.out.println("user: "+user);
			//if(!user.equalsIgnoreCase(d.getUsersActivos().get(i).toString()))
				texto+=""+d.getParesActivos().get(i).toString()+"\n";
				//System.out.println("Texto:"+texto);
			
		}
		tbParesActivos.setText(""+texto);
		
	}



	//-----------------Getters & Setters------------------------
	
	public void setJogo(Jogar j)
	{
		for(int i=0;i<jogo.size();i++)
		{
			jogo.get(i).addActionListener(j);
		}
	}
	
	public <T extends ActionListener,S>void setControlador(T controlador,S texto) 
	{
		for(int i=0;i<bt.size();i++)
		{
			if(texto.equals(bt.get(i).getText()))
			{
				bt.get(i).addActionListener(controlador);
			}
		}
	}

	
	
	public JTextArea getTbParesActivos() {
		return tbParesActivos;
	}

	public void setTbParesActivos(JTextArea tbParesActivos) {
		this.tbParesActivos = tbParesActivos;
	}

	public JTextField getTbConvidar() {
		return tbConvidar;
	}

	public void setTbConvidar(JTextField tbConvidar) {
		this.tbConvidar = tbConvidar;
	}

	public JTextArea getTbUsersActivos() {
		return tbUsersActivos;
	}

	public void setTbUsersActivos(JTextArea tbUsersActivos) {
		this.tbUsersActivos = tbUsersActivos;
	}

	public List<JButton> getBt() {
		return bt;
	}

	public void setBt(List<JButton> bt) {
		this.bt = bt;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Box getbLateral() {
		return bLateral;
	}

	public void setbLateral(Box bLateral) {
		this.bLateral = bLateral;
	}

	public Box getbBaixo() {
		return bBaixo;
	}

	public void setbBaixo(Box bBaixo) {
		this.bBaixo = bBaixo;
	}

	public JPanel getpJogo() {
		return pJogo;
	}

	public void setpJogo(JPanel pJogo) {
		this.pJogo = pJogo;
	}

	public List<JButton> getJogo() {
		return jogo;
	}

	public void setJogo(List<JButton> jogo) {
		this.jogo = jogo;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	
	
	
	
	
	
}

