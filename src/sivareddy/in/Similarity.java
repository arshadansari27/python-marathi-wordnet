package sivareddy.in;

import in.ac.iitb.cfilt.jhwnl.JHWNL;
import in.ac.iitb.cfilt.jhwnl.JHWNLException;
import in.ac.iitb.cfilt.jhwnl.data.POS;
import in.ac.iitb.cfilt.jhwnl.data.Pointer;
import in.ac.iitb.cfilt.jhwnl.data.PointerType;
import in.ac.iitb.cfilt.jhwnl.data.Synset;
import in.ac.iitb.cfilt.jhwnl.dictionary.Dictionary;

import java.util.ArrayList;


public class Similarity{
	
	public static Dictionary Dct,d;	
	public static void initialize()  	//Constructor;
	{
		JHWNL.initialize();	//"HindiWN.properties";
		Dct= Dictionary.getInstance();
		d= Dct;
	};
	
	public static myData getLCA(Long idS1, Long idS2) throws JHWNLException {
		// see if the s2 is in hypernym of s1 
		//fetch all hypernyms of s1 
		//fetch all hypernyms of s2	
		
		ArrayList<ArrayList<Long>> parentList1= new ArrayList<ArrayList<Long>> ();
		ArrayList<ArrayList<Long>> parentList2= new ArrayList<ArrayList<Long>> ();
		
		ArrayList<Long> start1= new ArrayList<Long> ();
		ArrayList<Long> start2= new ArrayList<Long> ();
		start1.add(idS1);
		start2.add(idS2);
		parentList1.add(start1);
		parentList2.add(start2);
		
		int len1= 0;
		Synset s1;
		while (len1 < parentList1.size())
		{
			ArrayList<Long> tmp= new ArrayList<Long> ();
			for (int j=0; j< parentList1.get(len1).size(); j++){
				s1= d.getSynsetAt(POS.NOUN, parentList1.get(len1).get(j));
				if (s1==null)
					continue;
				Pointer[] pointers1 = s1.getPointers();
				int i;
				for (i=0 ; i< pointers1.length;i++){
					if(pointers1[i].getType().equals(PointerType.HYPERNYM)){
						tmp.add(pointers1[i].getTargetSynset().getOffset());
					}
				}
			}
			if (tmp.size() > 0)
				parentList1.add(tmp);
			len1 ++;
		}
		
		int len2= 0;
		Synset s2;
		while (len2 < parentList2.size())
		{
			ArrayList<Long> tmp= new ArrayList<Long> ();
			for (int j=0; j< parentList2.get(len2).size(); j++){
				s2= d.getSynsetAt(POS.NOUN, parentList2.get(len2).get(j));
				if (s2==null)
					continue;
				Pointer[] pointers2 = s2.getPointers();
				int i;
				for (i=0 ; i< pointers2.length;i++){
					if(pointers2[i].getType().equals(PointerType.HYPERNYM)){
						tmp.add(pointers2[i].getTargetSynset().getOffset());
					}
				}
			}
			if (tmp.size() > 0)
				parentList2.add(tmp);
			len2 ++;
		}
		
		myData LCA= new myData ();
		
		int check= 0;
		for (int i=0; i< parentList1.size() && check==0; i++){
			for (int j=0; j< parentList1.get(i).size() && check==0; j++){
				for (int k=0; k< parentList2.size() && check==0; k++){
					for (int l=0; l< parentList2.get(k).size() && check==0; l++){
						//System.out.println("Synset1->"+parentList1.get(i).get(j));
						//System.out.println("Synset2->"+parentList2.get(k).get(l));
						if (parentList1.get(i).get(j).equals(parentList2.get(k).get(l))){
							LCA.parentList1= parentList1;
							LCA.parentList2= parentList2;
							LCA.lcaID= parentList1.get(i).get(j);
							LCA.distance= i+k;
							check= 1;
							break;
						}
					}
				}
			}
		}
		
		return LCA;
		
	}
	
	public static double  lchSimilarity(long idS1, long idS2) throws JHWNLException{
		myData LCA= getLCA(idS1, idS2);
		if (LCA.lcaID== -1)
			return 0;
		else if (LCA.distance==0)
			return 10000;
		
		int D= 16;
		return -Math.log(LCA.distance/(2.0*D));
	}
	
	
	public static double  wupSimilarity(long idS1, long idS2) throws JHWNLException{
		myData LCA= getLCA(idS1, idS2);
		int depth1= LCA.parentList1.size();
		int depth2= LCA.parentList2.size();
		if ((depth1 + depth2) ==0)
			return 0;
		double common= (depth1+depth2- LCA.distance)/2.0;
		//System.out.println(2.0*common/(depth1 + depth2));
		return  2.0*common/(depth1 + depth2);
	}
	
	public static long getLCAid(long idS1, long idS2) throws JHWNLException{
		myData LCA= getLCA(idS1, idS2);
		return LCA.lcaID;
	}
	
	public static void main(String args[]) throws Exception {
		initialize();
		System.out.println(lchSimilarity(18596, 26939));
		System.out.println(lchSimilarity(4422, 257));
//		demonstration();
	}
}