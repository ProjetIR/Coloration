package algorithm;
/**
 * Classe enveloppe permettant de représenter un quelconque résultat
 * @author KIEFFER
 *
 */
public abstract class AResult {

	/*
	 * par défaut prend le nom de d'algorithme d'où proviennent les résultats
	 */
	private String algoName;

	public AResult(String algoName) {
		super();
		this.algoName = algoName;
	}
	
	

}
