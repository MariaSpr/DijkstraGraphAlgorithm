public class Heap {
	private int hs;
	private Node<String>[] A;
	
	public Node<String>[] getA() {
		return A;
	}

	public void setA(Node<String>[] a) {
		A = a;
	}

	@SuppressWarnings("unchecked")
	public Heap(int size) {
		hs = -1;
		A = new Node[size];
	}
	
	public int min() {
		return A[0].getDistance();
	}
	
	public boolean isEmpty() {
		return (hs == -1);
	}
	
	public Node<String> popMin() {
		if(isEmpty()) {
			return null;
		}
		Node<String> min = A[0];
		A[0] = A[hs--];
		combine(0);
		return min;
	}
	
	public void insert(Node<String> k) {
		A[++hs] = k;
		int i = hs;
		int p = i/2;
		while ((i > 0) && (A[p].getDistance() > A[i].getDistance())) {
			Node<String> temp = A[p];
			A[p] = A[i];
			A[i] = temp;
			i = p; 
			p = i / 2;
		} 
	}
	
	public void decreaseKey(int position, int newDistance) {
		A[position].setDistance(newDistance);
		int i = position;
		int p = i/2;
		while((i>0) && A[p].getDistance() > A[i].getDistance()) {
			Node<String> temp = A[p];
			A[p] = A[i];
			A[i] = temp;
			i = p;
			p = i/2;
		}
	}
	
	private void combine(int i) {
		int l = 2*i;
		int r = 2*i+1;
		int mp = i;
		
		if ((l <= hs) && (A[l].getDistance() < A[mp].getDistance()))  mp = l; 
		
		if ((r <= hs) && (A[r].getDistance() < A[mp].getDistance()))  mp = r; 
		
		if (mp != i) {
			Node<String> temp = A[i];
			A[i] = A[mp];
			A[mp] = temp;  
			combine(mp); 
		}
	}
}
