package UI.ListenerUI;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import Model.LayoutAlgorithm.VisualAlgorithm;
import UI.Windows;

public class VisualAlgorithmListener implements ActionListener {

	private Windows win;
	private Class algo;
	
	
	public VisualAlgorithmListener(Windows win, Class algo) {
		super();
		this.win = win;
		this.algo = algo;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Stub de la méthode généré automatiquement

		try {
			Class[] parameterTypes = {Panel.class};
			Constructor constructor = algo.getConstructor(parameterTypes);
			Object al = constructor.newInstance(win.getDoubleBuffer());
			((VisualAlgorithm)al).place(win.getGraphe());
		} catch (SecurityException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Bloc catch généré automatiquement
			e1.printStackTrace();
		}

	}



}
