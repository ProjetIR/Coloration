package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.TextArea;

public class LogText extends TextArea {

	public LogText() throws HeadlessException {
		super();
		// TODO Stub du constructeur généré automatiquement
		this.setBackground(Color.WHITE);
		this.setFont(new Font("Arial", Font.BOLD, 14));
		this.setForeground(Color.RED);
		
	}
	
	
	

}
