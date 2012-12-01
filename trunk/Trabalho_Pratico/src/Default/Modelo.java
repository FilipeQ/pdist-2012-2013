package Default;

import java.util.Observable;

public class Modelo extends Observable
{
	private int contador;
	
	public Modelo()
	{
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
	
	//funçoes	setChanged(); notifyObservers(counter);
}
