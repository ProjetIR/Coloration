package Plugins.Algorithm;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import Model.Edge;
import Model.GraphException;
import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;
import algorithm.AResult;
import algorithm.Algorithm;

public class IteratifColoration extends Thread{

	private Graphe g;
	private Collection<Vertex> vertices;
	private Collection<Edge> edges;
	private ArrayList<Color> colors;
	private int totalConflits;
	private int oldTotalConflits;
	private int counter;
	private int nbWakeUp;
	private RandomBetween generator;
	private double temp;
	private IteratifColorationAlgorithm it;
	private Scanner scan;
	
	
	
	public IteratifColoration(IteratifColorationAlgorithm it,Graphe g) {
		super();
		this.g = g;
		vertices = g.getAllVertex();
		edges = g.getAllEdges();
		colors = new ArrayList<Color>();
		colors.add(Color.red);
		oldTotalConflits = totalConflits = g.getEdgesNumber();
		this.generator = new RandomBetween(System.currentTimeMillis());
		this.temp  = 0;
		nbWakeUp = 3;
		this.it = it;
		scan = new Scanner(System.in);
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		try {
			while(totalConflits > 0){
				totalConflits = nbConflicts();
				System.out.println("NB conflits: "+totalConflits);
				for(Vertex v : vertices){
					Color[] col = getColors();
					int[] repartition = getRepartionColor(col, g.getNeighbours(v));
					int posColor = indiceFromColor(v.getInfo().getCol(), col);
					int argmin = argMIN(repartition);
					if(posColor != argmin){
						if(repartition[posColor] != 0){
									v.getInfo().setCol(col[argmin]);
						}
						
					}
					
				}
				System.out.println("pause!!!!!!!!!!");
				String val = scan.next();
				if(val.equals("o")){
					Color c = generator.giveNewRandomColor(colors);
					colors.add(c);
				}
		
				// Partie gestion couleur
				/*if(totalConflits == oldTotalConflits ){
					
					counter++;
					if(counter == nbWakeUp){
						double val = probabilityFunction(temp++);
						if(Math.random() < val){
							int r1; 
							int r2; 
							do{
								r1 = generator.randomValue(0, vertices.size()-1); 
								r2 = generator.randomValue(0, vertices.size()-1);
							}while(r1 == r2);
							ArrayList<Vertex> vc = new ArrayList<Vertex>(vertices);
							Color tmp = vc.get(r1).getInfo().getCol();
							Color bis = vc.get(r2).getInfo().getCol();
							vc.get(r1).getInfo().setCol(bis);
							vc.get(r2).getInfo().setCol(tmp);
							System.out.println(val);
						}else{
						Color c = generator.giveNewRandomColor(colors);
						colors.add(c);
						}
						counter = 0;
					}
				}
				
				oldTotalConflits = totalConflits;
				
				*/
			}
			System.out.println("- fin de l'algorithme");
			Color[] c = getColors();
			int total = 0;
			try {
				int [] nbColor = getRepartionColor(c, this.vertices);
				for(int i = 0;i<c.length;i++){
					System.out.println(c[i]+" nombre = "+nbColor[i]);
					if(nbColor[i] != 0){
						total++;
					}
				}
				System.out.println("Nombre de couleur: "+total);
			} catch (InterruptedException e1) {
				// TODO Bloc catch généré automatiquement
				e1.printStackTrace();
			}
			this.it.NotifyEnd();
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} 
		
	}
	
	public int nbConflicts(){
		int totalConflits = 0;
		for(Edge e : edges){
			Color start = e.getStart().getInfo().getCol();
			Color end = e.getEnd().getInfo().getCol();
			if(start.equals(end)){
				totalConflits++;
			}
		}
		return totalConflits;
	}

private int[] getRepartionColor(Color[] availableColor,Collection<Vertex> neighbours ) throws InterruptedException{
		
		int[] repartition = new int[availableColor.length];
		for(int i = 0;i<availableColor.length;i++){
			for(Vertex v : neighbours){
				synchronized (v) {
					if(availableColor[i].equals(v.getInfo().getCol())){
						repartition[i]++;
					}
				}
			}

		}
		return repartition;
	}
	
	private int argMIN(int[] tab){
		int min = Integer.MAX_VALUE;
		int argmin = 0;
		for(int i = 0 ;i <tab.length;i++){
			if(tab[i]<min){
				argmin = i;
				min = tab[i];
			}

		}
		return argmin;
	}
	
	public int indiceFromColor(Color c,Color[] colors){
		
		for(int i =0;i<colors.length;i++){
			if(colors[i].equals(c)) return i;
		}
		return -1;
	}
	
	public Color[] getColors() {
		Color[] tab = new Color[colors.size()];
		for(int i =0 ;i<colors.size();i++){
			tab[i] = colors.get(i);
		}
		return tab;
	}
	
	private double probabilityFunction(double val){
		int dim = edges.size();
		return Math.exp((-1.0)*val);
	}
	


}
