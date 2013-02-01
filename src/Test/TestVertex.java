package Test;
import java.awt.Color;
import java.awt.Point;
import java.awt.Window;

import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Generator.Generator;
import Model.Generator.RandomGenerator;



public class TestVertex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
	
			Graphe g = new Graphe();
			try{
				Generator gen = new RandomGenerator(500,2475);
				gen.generate(g, new InfoVertex(Color.black, new Point(4,5),20), new InfoEdge(Color.black, 1));
				System.out.println(g);
				System.out.println(g.getVertexNumber());
				System.out.println(g.getEdgesNumber());
			}catch(Exception e){
				System.err.println(e.getMessage());
			}

	}

}
