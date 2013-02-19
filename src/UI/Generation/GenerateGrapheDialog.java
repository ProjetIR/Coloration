package UI.Generation;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Model.Graphe;
import UI.Windows;
import Utils.NewComposantAWT.NumericUpDown;

public class GenerateGrapheDialog extends Dialog {
	
	private Windows parent;
	private Button generer,annuler;
	private Panel panneau;
	private NumericUpDown vertexNumberField,edgeNumberField;
	private Label vertexNumberLabel,edgeNumberLabel;
	private Choice listVisualAlgorithm;
	
	
	
	 public GenerateGrapheDialog(Windows parent,Graphe g) throws HeadlessException{
		super(parent);
		// TODO Stub du constructeur généré automatiquement
		this.parent = parent;
		initializeFrame();
		initializeComponent();
		this.setModal(true);
		this.setResizable(false);
		
	
		
	 }
	 
	 public void showDialog(){
		this.setVisible(true); 
	 }

	 
	 public NumericUpDown getEdgeNumberField() {
		return edgeNumberField;
	}

	public void setEdgeNumberField(NumericUpDown edgeNumberField) {
		this.edgeNumberField = edgeNumberField;
	}

	
	public NumericUpDown getVertexNumberField() {
		return vertexNumberField;
	}

	public void setVertexNumberField(NumericUpDown vertexNumberField) {
		this.vertexNumberField = vertexNumberField;
	}

	private void initializeFrame(){
			this.setSize(new Dimension(400, 300));
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
	 }
	 
	 private void initializeComponent(){
		 panneau = new Panel();
		 panneau.setSize(400, 300);
		 panneau.setLayout(null);
		 this.add(panneau);
		 Dimension dimButton = new Dimension(100,50);
		 generer = new Button("Génération");
		 generer.setLocation(100, 240);
		 generer.setSize(dimButton);
		 generer.addMouseListener(new GenerationListener(this, parent));
		 annuler = new Button("Annuler");
		 annuler.setLocation(200, 240);
		 annuler.setSize(dimButton);
		 annuler.addMouseListener(new CancelListener(this));
		 
		 
		 vertexNumberLabel = new Label("Number of Vertices : ");
		 vertexNumberLabel.setSize(150,10);
		 vertexNumberLabel.setLocation(50, 50);
		 edgeNumberLabel = new Label("Number of Edges : ");
		 edgeNumberLabel.setSize(150,10);
		 edgeNumberLabel.setLocation(250, 50);
		 
		 vertexNumberField = new NumericUpDown(10, 1, 1000);
		 vertexNumberField.setSize(50,20);
		 vertexNumberField.setLocation(100, 70);
		 
		 edgeNumberField = new NumericUpDown(10, 1, 100000);
		 edgeNumberField.setSize(60,20);
		 edgeNumberField.setLocation(280, 70);
		
		 panneau.add(generer);
		 panneau.add(annuler);
		 panneau.add(vertexNumberLabel);
		 panneau.add(edgeNumberLabel);
		 panneau.add(vertexNumberField);
		 panneau.add(edgeNumberField);

		 
		
	 }
	 

	 

}
