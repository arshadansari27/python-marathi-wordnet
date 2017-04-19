package sivareddy.in;

import java.util.ArrayList;

public class myData {

	ArrayList<ArrayList<Long>> parentList1;
	ArrayList<ArrayList<Long>> parentList2;
	public long lcaID;
	public long distance;
	
	public myData() {
		lcaID= -1;
		parentList1= new ArrayList<ArrayList<Long>> ();
		parentList2= new ArrayList<ArrayList<Long>> ();
		distance= 10000; 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Hello");
	}

}
