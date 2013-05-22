package Plugins;

import java.awt.Window;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import UI.Windows;
import algorithm.Algorithm;

/**
 * Classe permettant de charger de nouveau plugin au format .class
 * (on pourrait faire une précompilation avec des .java)
 * @author KIEFFER
 *
 */
public class LoadPlugins {
	
	private static String packageAlgorithm = "Plugins.Algorithm.";
	private static String directoryAlgorithm = "/bin/Plugins/Algorithm/";

	
	/**
	 * Méthode statique permettant de charger des classes présententes dans des fichiers .class 
	 * et héritant de la classe Algorithm
	 * @param listeAlgorithm
	 */
	public static void startLoadingPlugins(Collection<Class> listeAlgorithm){
	       
		File f = new File(System.getProperty("user.dir")+directoryAlgorithm);
		String[] liste = f.list();
		try {
			for(String s : liste){
				if(!s.endsWith(".class")) continue;
				int posPoint = s.indexOf(".");
				String classe = s.substring(0,posPoint);
				System.out.println(classe);
				Class clazz = Class.forName(packageAlgorithm+classe);
				Class superClass = clazz.getSuperclass();
				String interfaceName = superClass.getSimpleName();
				if(interfaceName.equals(Algorithm.class.getSimpleName())){
						listeAlgorithm.add(clazz);

				}
				
				
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			Windows.log.warning(e.getMessage());
		}
		
		
		
	}
	


}
