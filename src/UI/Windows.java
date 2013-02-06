package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Timer;



import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import UI.ListenerUI.BenchmarkLoad;
import UI.ListenerUI.CloseWindows;
import UI.ListenerUI.GenerateGraph;
import UI.ListenerUI.ResizeEvent;
import UIGraph.GraphVisualizer;

public class Windows extends Frame {
	
	private Graphe graphe;
	private DoubleBuffer doubleBuffer;
	private GraphVisualizer visu; 
	private InfoVertex iv; 
	private InfoEdge ie; 
	private Timer t;
	private MenuBar mbar;
	private LogText text;
	
	public Windows(String title) throws HeadlessException {
		super(title);
		try {
			// TODO Stub du constructeur généré automatiquement
			addWindowListener(new WindowAdapter(){
				public void windowClosing (WindowEvent e){
					System.exit(0);
				}
			});
			
			this.setLayout(null);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize(); 	// l'objet d contient les dimensions de l'écran
			int he, le,		// hauteur et largeur de l’écran 
			hf,lf;		// hauteur et largeur de la fenêtre
			int Ox, Oy;		//coordonnées du coin haut gauche de la fenêtre

			he=(int)(d.getHeight());		//récupère la hauteur de l'écran
			le=(int)(d.getWidth());		//récupère la largeur de l'écran
			hf = (he/2)+(he/4); lf = (le/2)+(le/4);		// la fenêtre prend 1/4 de l'écran
			Ox = le/2; Oy = he/2;		// elle est placée à 1/3 du coin haut gauche

			setLocation(Ox-lf/2,Oy-hf/2);	//fixe les coordonnées du coin haut gauche du cadre en pixels
			this.setSize(new Dimension(800, 600));
			this.doubleBuffer = new DoubleBuffer();
			int height = (int)(this.getHeight()*2D/3);
			this.doubleBuffer.setSize(new Dimension(this.getWidth(),height));
			doubleBuffer.setLayout(null);
			this.add(this.doubleBuffer);
			
			this.setBackground(Color.LIGHT_GRAY);
			this.doubleBuffer.setLocation(0, 50);
			this.graphe = new Graphe();
			this.iv = new InfoVertex(Color.red, new Point(4,5),10);
			this.ie = new InfoEdge(Color.black, 1);
			this.visu =  new GraphVisualizer(this.graphe, this.doubleBuffer,this.iv,this.ie);
			InitializeMenus();
			this.text = new LogText();
			this.add(this.text);
			int heightLog = (int)(this.getHeight()*1D/3);
			this.text.setSize(this.getWidth(), heightLog-50);
			this.text.setLocation(0, this.doubleBuffer.getHeight()+50);
			this.addComponentListener(new ResizeEvent(this.doubleBuffer, this.text));
			this.t  = new Timer();
			t.scheduleAtFixedRate(new Task(this.doubleBuffer), new Date(System.currentTimeMillis()),1);
		} catch (Exception e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	
	
	private void InitializeMenus()
	{
		mbar = new MenuBar();
		Menu m = new Menu("File");
		MenuItem it = new MenuItem("New Graphe");
		it.addActionListener(new GenerateGraph(this, this.graphe));
		m.add(it);
		MenuItem itemBen = new MenuItem("Open Benchmark");
		itemBen.addActionListener(new BenchmarkLoad(this));
		m.add(itemBen);
		m.add(new MenuItem("Save"));
		m.add(new MenuItem("Save As"));
		m.add(new MenuItem("Print"));
		m.addSeparator();
		MenuItem quitter = new MenuItem("Quit");
		quitter.addActionListener(new CloseWindows());
		m.add(quitter);		
		mbar.add(m);
			
		m = new Menu("Help");
		m.add(new MenuItem("Help!!!!"));
		m.addSeparator();
		m.add(new MenuItem("About..."));
		mbar.add(m);

		// Recent change
		setMenuBar(mbar);
	}



	public Graphe getGraphe() {
		return graphe;
	}



	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}



	public DoubleBuffer getDoubleBuffer() {
		return doubleBuffer;
	}



	public void setDoubleBuffer(DoubleBuffer doubleBuffer) {
		this.doubleBuffer = doubleBuffer;
	}



	public InfoVertex getDefautInfoVertex() {
		return iv;
	}



	public void setDefautInfoVertex(InfoVertex iv) {
		this.iv = iv;
	}



	public InfoEdge getDefautInfoEdge() {
		return ie;
	}



	public void setDefautInfoEdge(InfoEdge ie) {
		this.ie = ie;
	}



	public GraphVisualizer getVisu() {
		return visu;
	}



	public void setVisu(GraphVisualizer visu) {
		this.visu = visu;
	}
	
	
	
}
