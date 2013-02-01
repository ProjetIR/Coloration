package Utils.Set;



public class Node<T> {

	private int rank;
	private T object;
	private Node<T> parent;
	
	public Node(T object){
		
		this.object = object;
		this.parent = this;
		this.rank = 0;
	}
	
	public int getRank(){
		
		return this.rank;
	}
	
	public void incrementRank(int i){
		
		rank+=i;
	}
	
	public Node<T> getParent(){
	
		return this.parent;
	}
	
	public void setParent(Node<T> parent){
	
		this.parent = parent;
	}
	
	public T getObject(){
	
		return this.object;
	}
	
	public String toString(){
		
		if(this.parent != this){
			
			return this.parent.toString() +"\n"+this.object.toString()+"\n";
		}else{
			
			return this.object.toString();
		}
		
	}
	

}
