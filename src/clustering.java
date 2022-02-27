import java.util.*;
public class clustering {
	public static void clustermain(){
		//System.out.println("Select the following method to cluster data:");
		int k=mainProtein.index;
		Scanner sc=new Scanner(System.in);
		if(k==1) {
			System.out.println("--> Sort by Annotation Type");
			SortProteinGO1 g=new SortProteinGO1();
			mainProtein.submain();
		}
		else if(k==2) {
			System.out.println("1. --> Sort by Page Rank Vector");
        	PR_Vector p=new PR_Vector(1);
        	System.out.println("2. --> Sort by Organism Type");
        	GetProteinOrganism2 g=new GetProteinOrganism2();
			mainProtein.submain();
		}
		else {
			System.out.println("The selected document is not applicable in clustering. Please choose another document.");
			mainProtein.selectDocument();
			mainProtein.submain();
		}
		
		
	}
}
