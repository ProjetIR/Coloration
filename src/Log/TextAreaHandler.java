package Log;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Handler qui va permet de simuler un log dans un TextArea
 * @author KIEFFER
 *
 */
public class TextAreaHandler extends Handler {

	private TextArea text;
	
	
	public TextAreaHandler(TextArea text) {
		super();
		this.text = text;
	}

	@Override
	public void publish(LogRecord record) {
		// TODO Stub de la méthode généré automatiquement
		text.append(new Date(record.getMillis())+" "+record.getLevel()+" "+record.getMessage()+"\n");
	}

	@Override
	public void flush() {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void close() throws SecurityException {
		// TODO Stub de la méthode généré automatiquement

	}

}
