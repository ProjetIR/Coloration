package UI;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Panneau permettant de réaliser du double buffering 
 * pour éviter l'effet de scintillement 
 * @author emmanuel
 *
 */

public class DoubleBuffer extends Panel {

    private int bufferWidth; // largeur de buffer
    private int bufferHeight; // hauteur de buffer
    private Image bufferImage; // le buffer
    private Graphics bufferGraphics; // le pinceau pour dessiner sur ce buffer
    
    
	public DoubleBuffer() {
		super();
		// TODO Stub du constructeur généré automatiquement
	}

	
	@Override
	public void update(Graphics g){
	        paint(g);
	    }
	/**
	 * méthode principale pour l'affichage sur le panel
	 * On affiche tous les composants sur le buffer puis
	 * sur l'écran
	 */
	@Override
	 public void paint(Graphics g){
		resetBuffer();
        if(bufferGraphics!=null){
            //this clears the offscreen image, not the onscreen one
            bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

            //calls the paintbuffer method with 
            //the offscreen graphics as a param
            paintBuffer(bufferGraphics);

            //we finaly paint the offscreen image onto the onscreen image
            g.drawImage(bufferImage,0,0,this);
        }
    }
	
	/**
	 * On affiche tous les composants sur le buffer pour éviter les scintillements
	 * @param g
	 */
    public void paintBuffer(Graphics g){
        //in classes extended from this one, add something to paint here!
        //always remember, g is the offscreen graphics
    	try{
    		super.paint(g);
    	}catch(Exception e){
    		
    		e.printStackTrace();
    	}
    }
    /**
     * Remise à zéro du buffer
     */
	private void resetBuffer(){
	        // always keep track of the image size
	        bufferWidth=getSize().width;
	        bufferHeight=getSize().height;

	        //    clean up the previous image
	        if(bufferGraphics!=null){
	            bufferGraphics.dispose();
	            bufferGraphics=null;
	        }
	        if(bufferImage!=null){
	            bufferImage.flush();
	            bufferImage=null;
	        }
	        System.gc();

	        //    create the new image with the size of the panel
	        bufferImage=createImage(bufferWidth,bufferHeight);
	        bufferGraphics=bufferImage.getGraphics();
	    }	
	


}
