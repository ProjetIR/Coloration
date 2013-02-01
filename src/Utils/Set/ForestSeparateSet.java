package Utils.Set;

public class ForestSeparateSet<T> {


	public  Node<T>  createSet(T object){
		
		return new Node<T>(object);
	}
	
	public void link(Node<T> x ,Node<T> y){
		
		if(x.getRank() >= y.getRank()){
			y.setParent(x);
		}
		else if(x.getParent() == y){
			if(x.getRank() == y.getRank()){
				y.incrementRank(1);
			}
		}
			
	}
	
	public Node<T> findSet(Node<T> x){
		
		if(x != x.getParent())
			x.setParent(findSet(x.getParent()));
		return x.getParent();
	}

	public void union(Node<T> x, Node<T> y){
	
		link(findSet(x), findSet(y));
	}
	
}
