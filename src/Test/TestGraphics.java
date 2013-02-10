package Test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;


import IO.GrapheReader;
import IO.GrapheWriter;
import IO.Benchmark.BenchMarkReader;
import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Generator.Generator;
import Model.Generator.RandomGenerator;
import Plugins.Algorithm.ColorationAlgorithm;
import Plugins.Algorithm.ForcedBasedAlgorithm;
import Plugins.Algorithm.RandomDisposition;
import UI.DoubleBuffer;
import UI.Task;
import UIGraph.GraphVisualizer;
import UI.*;


public class TestGraphics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		Frame f = new Frame("test");
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		f.setSize(new Dimension(800, 600));
		
		f.setLayout(null);
		
		DoubleBuffer p = new DoubleBuffer();
		p.setLayout(null);
		f.add(p);
		p.setSize(800, 600);
		p.setLocation(0, 0);
		try {
			Timer t  = new Timer();
			t.scheduleAtFixedRate(new Task(p), new Date(System.currentTimeMillis()),1);
			//Graphe g = new Graphe();
			//GrapheReader r = new GrapheReader("test.gr");
			//Graphe g = r.read();
			//Generator gen = new RandomGenerator(3,3);
			InfoVertex iv = new InfoVertex(Color.red, new Point(4,5),10);
			InfoEdge ie = new InfoEdge(Color.black, 1);
			//gen.generate(g, iv , ie );
			
			BenchMarkReader bReader = new BenchMarkReader("le450_25a.col",iv,ie);
			Graphe g = bReader.read();
			
			RandomDisposition dp = new RandomDisposition(p);
			dp.place(g);
			ForcedBasedAlgorithm al = new ForcedBasedAlgorithm(p);
			al.place(g);
			GraphVisualizer visu = new GraphVisualizer(g, p,iv,ie);
			//GrapheWriter w = new GrapheWriter("test.gr");
			//w.write(g);
			ColorationAlgorithm cal = new ColorationAlgorithm(g);
			cal.compute();
			System.out.println("nbVertex : "+g.getVertexNumber());
			System.out.println("nbEdge : "+g.getEdgesNumber());
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		

		
		
		f.setVisible(true);
		
		//Windows w = new Windows("Coloration de Graphes");
		//w.setVisible(true);
	}

}
