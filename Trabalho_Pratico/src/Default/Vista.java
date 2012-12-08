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
	/**
	 * 
	 */
	//Controlador controlador;
	JTextField tbParesActivos,tbConvidar;
	JTextArea tbUsersActivos;
	List<JButton> bt;
	Container c;
	Modelo modelo;

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
		tbConvidar = new JTextField("");
		tbParesActivos=new JTextField("fdgdfgdshs");
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
		// TODO Auto-generated method stub
		//tb.setText("" + modelo.getContador());
		//System.out.println(((Integer)arg1).intValue());
		//tbUsersActivos.setText("" + ((Integer)arg1).intValue());
		
		Dados d=(Dados)arg1;
		String texto="";
		System.out.println("Tamanho:"+d.getUsersActivos().size());
		for(int i=0;i<d.getUsersActivos().size();i++)
		{
			//System.out.println("userActivos: "+d.getUsersActivos().get(i).toString());
			//if(!d.getLogin().equalsIgnoreCase(d.getUsersActivos().get(i).toString()))
				texto+=""+d.getUsersActivos().get(i).toString()+"\n";
				System.out.println("Texto:"+texto);
			
		}
		tbUsersActivos.setText(""+texto);
		
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

	
}

