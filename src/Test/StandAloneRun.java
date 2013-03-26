package Test;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Model.Graphe;

public class StandAloneRun implements Observer {

	private Graphe g;
	private boolean stop = false;
	private Scanner scan;
	
	public StandAloneRun(){
		
		this.g = null;
		this.scan = new Scanner(System.in);

	}
	
	public String MenuHelp(){
		String s = "algo - display all implemented algorithms"+
				   "create E V - create a random graph with E vertices and V edges "+
				   "opt - start the current algorithm"+
				   "quit - exit the application"+
				   "read path - read a serialized graph with (*.gr) extension"+
				   "readB path - read a benchmark graph xith (*.col) extension"+
				   "set A - set current algorithm ( A is le rank of the algorithm in the list which are displayed by algo";
				   
		return s;
	}
	
	public void start(){
		String input;
		while(!this.stop){
			input = this.scan.next();
			handle(input);
		}
	}

	private void handle(String input) {
		// TODO Auto-generated method stub
		String[] tab = input.split(" ");
		String command = tab[0];
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
