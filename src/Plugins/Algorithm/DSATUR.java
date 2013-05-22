package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import Model.GraphException;
import Model.Graphe;
import Model.Vertex;
import Utils.RandomBetween;
import algorithm.Algorithm;
public class DSATUR extends Algorithm {

	/**
	 * Comparateur selon le critère DSATUR
	 * @author KIEFFER
	 *
	 */
	public class DSATComparator implements Comparator<Vertex>{

		private Graphe g;
		
		public DSATComparator(Graphe g){
			this.g = g;
		}
		@Override
		public int compare(Vertex o1, Vertex o2) {
			// TODO Auto-generated method stub
			
				int dsat1 = DSAT(o1);
				int dsat2 = DSAT(o2);
				return (-1)*(dsat1-dsat2);

		}

		private int DSAT(Vertex o1) {
			// TODO Auto-generated method stub
			Collection<Vertex> voisin  = null;
			try {
				voisin = g.getNeighbours(o1);
			} catch (GraphException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashSet<Color> set = new HashSet<Color>();
			for(Vertex v : voisin){
				if(!(v.getInfo().getCol().equals(NOCOLOR))){
					set.add(v.getInfo().getCol());
				}
			}
			int nb = set.size();
			if(set.isEmpty()){
				return o1.getDegree();
			}else{
				return nb;
			}
		}
		
		
	}
	private Graphe g;
	private ArrayList<Color> mapColor;
	private RandomBetween generator;
	public static Color NOCOLOR = Color.red;
	
	public DSATUR(Graphe g){
		this.g = g;
		this.generator = new RandomBetween(System.currentTimeMillis());
	}
	
	@Override
	public void compute() {
		try {
			this.setNoColor();
			this.mapColor = this.genereTabColor(200);
			ArrayList<Vertex> all = new ArrayList<Vertex>(g.getAllVertex());
			Collections.sort(all,new DSATComparator(g));
			for(int i =0;i<all.size();i++){
				System.out.println(all.get(i).getDegree());
			}
			for(int i = 0;i<g.getVertexNumber();i++){
				int j = -1;
				Vertex v ;
				do{
					j++;
				v = all.get(j);
				}while(v.getInfo().getCol() != NOCOLOR);
				Color colMin = this.findMinColor(v);
				v.getInfo().setCol(colMin);
				Collections.sort(all,new DSATComparator(g));
			}
			this.end();
			this.sendAResult();
		} catch (GraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Méthode pour déterminer la plus petite couleur a utilisé pour un sommet v
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	private Color findMinColor(Vertex v) throws GraphException{
		Collection<Vertex> voisin = g.getNeighbours(v);
		int min = Integer.MAX_VALUE;
		Color colMin = null;
		for(int i = 0;i<mapColor.size();i++){
			int cpt = 0;
			Color c = mapColor.get(i);
			for(Vertex u : voisin){
				if(u.getInfo().getCol().equals(c)){
					cpt++;
				}
			}
			if(cpt < min){
				min = cpt;
				colMin = c;
			}
		}
		return colMin;
	}

	private ArrayList<Color> genereTabColor(int nb) {
		// TODO Auto-generated method stub
		ArrayList<Color> res = new ArrayList<Color>();
		for(int i = 0 ; i < nb ;i++){
			Color c = generator.giveNewRandomColor(res);
			res.add(c);
			
		}
		return res;
	}

	public void setNoColor(){
		for(Vertex v : g.getAllVertex()){
			v.getInfo().setCol(NOCOLOR);
		}
	}
	
	@Override
	protected String sendAResult() {
		// TODO Auto-generated method stub
		return "Fin d'algorithme : durée = "+this.duration+"ms. Nombre de couleurs : "+this.getNumberColors();
	}

	private int getNumberColors() {
		// TODO Auto-generated method stub
		Collection<Vertex> vertices = g.getAllVertex();
		HashSet<Color> liste = new HashSet<Color>();
		for(Vertex v : vertices){
			liste.add(v.getInfo().getCol());
		}
		return liste.size();
	}

}
