package Plugins;

import java.awt.Window;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import Model.LayoutAlgorithm.VisualAlgorithm;
import UI.Windows;
import algorithm.Algorithm;


public class LoadPlugins {
	
	private static String packageAlgorithm = "Plugins.Algorithm.";
	private static String directoryAlgorithm = "/bin/Plugins/Algorithm/";

	
	
	public static void startLoadingPlugins(Collection<Class> listeAlgorithm,Collection<Class> listeVisualAlgorithm){
	       
		File f = new File(System.getProperty("user.dir")+directoryAlgorithm);
		String[] liste = f.list();
		try {
			for(String s : liste){
				if(!s.endsWith(".class")) continue;
				int posPoint = s.indexOf(".");
				String classe = s.substring(0,posPoint);
				System.out.println(classe);
				Class clazz = Class.forName(packageAlgorithm+classe);
				Class [] interfaces = clazz.getInterfaces();
				for(Class e : interfaces){
					String interfaceName = e.getSimpleName();
					if(interfaceName.equals(Algorithm.class.getSimpleName())){
						listeAlgorithm.add(clazz);
					}else if(interfaceName.equals(VisualAlgorithm.class.getSimpleName())){
						listeVisualAlgorithm.add(clazz);
					}
				}
				
			  
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			Windows.log.warning(e.getMessage());
		}
		
		
		
	}
	


}
