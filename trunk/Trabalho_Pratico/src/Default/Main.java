package Default;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main 
{
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		Run n=new Run();
		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		UIManager.getCrossPlatformLookAndFeelClassName();
	}
}
