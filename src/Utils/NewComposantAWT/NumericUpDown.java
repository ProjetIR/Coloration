package Utils.NewComposantAWT;

import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class NumericUpDown extends TextField implements TextListener{

	private int value;
	private int down,up;

	public NumericUpDown(int value,int down,int up) throws HeadlessException {
		super();
		this.value = value;
		this.setText(""+value);
		this.down = down;
		this.up = up;
		this.addTextListener(this);
		
	}
	
	public int getValue(){
		
		return value;
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Stub de la méthode généré automatiquement
		String txt = this.getText();
		System.out.println(txt.equals(""));
		if(txt.equals("")) return;
		try{
			value = Integer.parseInt(txt);
			value = Math.max(down, Math.min(up, value));
			this.setText(""+value);
			
		}catch(NumberFormatException except){
			value = down;
			this.setText(""+value);
		}
		this.setCaretPosition(txt.length());
	}
	
	
	
	

}
