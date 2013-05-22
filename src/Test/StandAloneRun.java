package Test;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import algorithm.Algorithm;
import algorithm.StateHandler;

import IO.GrapheReader;
import IO.GrapheWriter;
import IO.Benchmark.BenchMarkReader;
import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Generator.RandomGenerator;
import Plugins.LoadPlugins;
import UIGraph.GraphVisualizer;

/**
 * Mode console pour le logiciel
 * @author KIEFFER
 *
 */
public class StandAloneRun implements Observer {

	private Graphe g;
	private ArrayList<Class> listeAlgorithme;
	private boolean stop = false;
	private Scanner scan;
	private Class setAlgo;
	private InfoVertex iv;
	private InfoEdge ie;
	private boolean running = false;
	
	public StandAloneRun(){
		
		this.g = null;
		this.listeAlgorithme = new ArrayList<Class>();
		LoadPlugins.startLoadingPlugins(this.listeAlgorithme);
		this.scan = new Scanner(System.in);
		this.setAlgo=null;
		this.iv = new InfoVertex(Color.red, new Point(4,5),10);
		this.ie = new InfoEdge(Color.black, 1);
	}
	

	public String MenuHelp(){
		String s = "algo - display all implemented algorithms\n"+
				   "create E V - create a random graph with E vertices and V edges\n"+
				   "opt - start the current algorithm\n"+
				   "quit - exit the application\n"+
				   "read path - read a serialized graph with (*.gr) extension\n"+
				   "readB path - read a benchmark graph with (*.col) extension\n"+
				   "save path - save the current graph with (*.gr) extension\n"+
				   "set A - set current algorithm ( A is le rank of the algorithm in the list which are displayed by algo\n";
				   
		return s;
	}
	
	public String showAlgorithmListing(){
		String s = "";
		int cpt = 0;
		for(Class c : this.listeAlgorithme){
			s += cpt+" : "+c.getSimpleName()+"\n";
			cpt++;
		}
		return s;
	}
	
	
	public void start(){
		String input;
		while(!this.stop){
			System.out.print(">");
			input = this.scan.nextLine();
			handle(input);
		}
	}

	private void handle(String input) {
		// TODO Auto-generated method stub
		String[] tab = input.split(" ");
		String command = tab[0];
		if(command.equals("help")){
			System.out.println(this.MenuHelp());
		}else if(command.equals("algo")){
			System.out.println(this.showAlgorithmListing());
		}else if(command.equals("set")){
			int arg = Integer.parseInt(tab[1]);
			System.out.println("Vous avez choisi l'algoritme "+this.listeAlgorithme.get(arg).getSimpleName());
			this.setAlgo = this.listeAlgorithme.get(arg);
		}else if(command.equals("opt")){
			if(!running){
				if(this.g != null && this.setAlgo != null){
					this.startAlgorithm(this.setAlgo);
				}else{
					System.out.println("Pas de graphe en mémoire ou choix algorithme manquant");
				}

			}else{
				System.out.println("Algoritme "+this.setAlgo.getSimpleName()+" est en fonctionnement");
			}
		}else if(command.equals("create")){
		      int V = Integer.parseInt(tab[1]);  
		      int E = Integer.parseInt(tab[2]); 
		      this.generateGraph(V, E);
		      System.out.println("Nouveau Graphe |V| = "+this.g.getVertexNumber()+" |E| = "+this.g.getEdgesNumber());
		}else if(command.equals("read")){
			String path = tab[1];
			GrapheReader r = new GrapheReader(path);
			this.g = r.read();
		}else if(command.equals("readB")){
			String path = tab[1];
			BenchMarkReader ben = new BenchMarkReader(path, this.iv, this.ie);
			this.g = ben.read();
		}else if(command.equals("quit")){
			System.exit(0);
		}else if(command.equals("save")){
			String path = tab[1];
			GrapheWriter w = new GrapheWriter(path);
			w.write(this.g);
		}
	}

	public void startAlgorithm(Class algo){
		try {
			Class[] parameterTypes = {Graphe.class};
			Constructor constructor = algo.getConstructor(parameterTypes);
			Object al = constructor.newInstance(g);
			Algorithm current = (Algorithm)al;
			current.addObserver(this);
			this.running = true;
			current.compute();
		} catch (SecurityException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		}
		
		
	}
	
	public void generateGraph(int V,int E){
		try {
			RandomGenerator g = new RandomGenerator(V, E);
			this.g = new Graphe();
			g.generate(this.g, this.iv, this.ie);
		} catch (GraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if( arg1 instanceof String){ // Réception résultats de l'algorithme
			String arg2 = (String)arg1;
			System.err.println(arg2);
			this.running = false;
		}
	}
}
