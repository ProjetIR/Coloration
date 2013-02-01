package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;


public class InfoEdge extends Info implements Serializable{

	private double weight;
	
	public InfoEdge(Color col,double weight) {
		super(col);
		// TODO Stub du constructeur généré automatiquement
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "InfoEdge [weight=" + weight + "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Stub de la méthode généré automatiquement
		InfoEdge info = new InfoEdge(this.col,this.weight);
		return info;
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		// TODO Stub de la méthode généré automatiquement
		
	}



}
