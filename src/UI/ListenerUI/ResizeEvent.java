package UI.ListenerUI;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import UI.DoubleBuffer;
import UI.LogText;

public class ResizeEvent extends ComponentAdapter {

	private DoubleBuffer buffer;
	private LogText text;
	
	
	
	public ResizeEvent(DoubleBuffer buffer, LogText text) {
		super();
		this.buffer = buffer;
		this.text = text;
	}



	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Stub de la méthode généré automatiquement
		int height = (int)(this.buffer.getParent().getHeight()*2D/3);
		this.buffer.setSize(new Dimension(this.buffer.getParent().getWidth(),height));
		int heightLog = (int)(this.buffer.getParent().getHeight()*1D/3);
		this.text.setSize(this.buffer.getParent().getWidth(), heightLog-50);
		this.text.setLocation(0, this.buffer.getHeight()+50);
	}


}
