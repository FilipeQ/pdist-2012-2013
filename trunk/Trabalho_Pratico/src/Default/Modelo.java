package Default;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Modelo extends Observable
{
	private int contador;
	//private List<String>usersActivos;
	//private List<String>paresActivos;
	Dados d;
	
	public Modelo()
	{
		//usersActivos=new ArrayList<>();
		//paresActivos=new ArrayList<>();
		d=new Dados();
		contador=0;
	}
	
	public void incrementarValor()
	{
		++contador;
		setChanged();
		notifyObservers(contador);
	}
	public void decrementarValor()
	{
		--contador;
		setChanged();
		notifyObservers(contador);
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
		setChanged();
		notifyObservers(contador);
	}

	public Dados getD() {
		return d;
	}

	public void setD(Dados d) {
		this.d = d;
	}

	
	/*public List<String> getUsersActivos() {
		return usersActivos;
	}

	public void setUsersActivos(List<String> usersActivos) {
		this.usersActivos = usersActivos;
	}

	public List<String> getParesActivos() {
		return paresActivos;
	}

	public void setParesActivos(List<String> paresActivos) {
		this.paresActivos = paresActivos;
	}*/
	
	
	
	//funçoes	setChanged(); notifyObservers(counter);
}
