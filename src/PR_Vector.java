import java.util.*;
import java.util.stream.Collectors;
//DOCUMENT: 2 
//DATA: index,protein,accession,sequence,annotation,interpro,org
//PR: page rank

public class PR_Vector {
	String filename="swissprot_data.tsv-cluster-PRVector";
	static String sheetname="",filetype="xlsx";
	List<ProteinData>tmp=new ArrayList<>();
	static List<ProteinData>datalist=new ArrayList<>();
	static List<String> GOList=new ArrayList<>();
	static String size;
	String count="0";
	String head[]= {"Index","Protein","Accession","Sequence","Annotation","Interpro","Org","Seqlength","PR_Vector"};
	PR_Vector(){}
	PR_Vector(int a){
		getData();
		parseData();
		sortPR.parsePR(datalist);
	}
	void getData() {
		try {
			mainProtein.index=2;
			System.out.println("Please enter size of dataset you would like to parse: 100 / 500 / 1000");
			System.out.println("*(Kindly remind that the larger dataset the longer time would be taken.)");
			Scanner sc=new Scanner(System.in);
			size=sc.next();
			while(!(size.equals("100")||size.equals("500")||size.equals("1000"))){
	            System.out.println("Illegal input! please input 100 / 500 / 1000:");
	            size=sc.next();
	        }
			if(size.equals("500"))count="17";
			else if(size.equals("1000"))count="55";
			getRawData.getDocument();
			tmp=getRawData.datalist1;
			datalist=tmp.stream().limit(Integer.parseInt(size)).collect(Collectors.toList());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		for(ProteinData p:datalist) {
			//GOList.add(trimGO(p.getAnnotation()));
			GOList.add(p.getAnnotation());
		}
	}
	void parseData() {
		System.out.print("Getting Page Rank Vector ");
		mainProtein.sleep();
		System.out.print(" (Estimated time: "+count+" seconds)\n");
		class Th2 implements Runnable {
			int seconds=0;
		    @Override
		    public void run() {
		        fucn2();
		    }
		    void fucn2() {	
		    	System.out.print("Counting seconds:");
		    	System.out.print("\t");
		    	while (true) {
		            try {
		            	String s=String.valueOf(seconds);
		                System.out.print(s);
		                seconds+=1;
		                Thread.sleep(1000);
		                int length = s.length();
		                while (length-->0) {
		                    System.out.print('\b');
		                }
		                
		            } catch (InterruptedException e1) {
		                e1.printStackTrace();
		            }
		    	}
		    	//seconds=0;
		    }
		}
		Thread t2=new Thread(new Th2());
        t2.start();
		getGOsimilarity g=new getGOsimilarity();
		double pr_vector[][]=g.analyse(GOList);
		int len=pr_vector[0].length;
		for(int i=0;i<len;i++) {
			datalist.get(i).setPR_Vector(pr_vector[0][i]);	
		}
	    t2.stop();
		System.out.println("\nGetting PR Vector done 100%.");
	}
	void outputData(List<ProteinData>ls){
		FileFunction.generateToExcel(filename,sheetname,ls,new ArrayList<>(),head,6,filetype);
    }
	String trimGO(String s){
		return s.replace("[","").replace("]","").replace("'", "").replace("GO:","").replace(","," ");
	}
}


class getGOsimilarity {
	double damping=0.85;
	double min_diff=1e-5;
	int steps=100;
	String text_str,sentences[];
	double pr_vector[][];
	static double cosineSimilarity(double[] vectorA, double[] vectorB) {
	    double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    for (int i = 0; i < vectorA.length; i++) {
	        dotProduct += vectorA[i] * vectorB[i];
	        normA += Math.pow(vectorA[i], 2);
	        normB += Math.pow(vectorB[i], 2);
	    }   
	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	double sentenceSimilarity(String s1[],String s2[]) {
		//String stopwords[];
		Set<String>s=new HashSet<>();
		//String s1[]=sentence1.split(" ");
		//String s2[]=sentence2.split(" ");
		for(String o:s1)s.add(o);
		for(String o:s2)s.add(o);
		List<String>allwords=new ArrayList<>(s);
		double vector1[]=new double[allwords.size()];
		double vector2[]=new double[allwords.size()];
		for(String o:s1)vector1[allwords.indexOf(o)]+=1;
		for(String o:s2)vector2[allwords.indexOf(o)]+=1;
		return cosineSimilarity(vector1,vector2);
	}
	double[][] similarityMatrix(List<String[]>lst){
		int len=lst.size();
		//Matrix m=new Matrix(len,len);
		double similar[][]=new double[len][len];
		initialize(similar,0);
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				if(i==j)continue;
				similar[i][j]=sentenceSimilarity(lst.get(i),lst.get(j));
			}
		}
		similar=get_symmetric_matrix(similar);
		double normaliseCol[]=new double[len];
		for(int j=0;j<len;j++) {
			for(int i=0;i<len;i++) {
				normaliseCol[j]+=similar[i][j];   //normalize matrix by column
			}
		}
		double normaliseSim[][]=new double[len][len];
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				if(normaliseCol[j]!=0)normaliseSim[i][j]=similar[i][j]/normaliseCol[j];
			}
		}
		return normaliseSim;
	}
	double [][] textRank(double similarmatrix[][]){
		int m=similarmatrix.length;
		pr_vector=new double[1][m];
		initialize(pr_vector,1);
		double previous_pr=0; //iteration	
		double mul[][]=multiply(pr_vector,similarmatrix);
		//System.out.println("multiply: "); printM(mul);
		int len=mul[0].length;
		pr_vector=new double[1][len];
		for(int k=0;k<steps;k++) {
			for(int i=0;i<len;i++) {
				pr_vector[0][i]=(1-damping)+damping*mul[0][i];
				
			}
			if (Math.abs(previous_pr-elemSum(pr_vector))<min_diff) break;
			else previous_pr=elemSum(pr_vector);	
		}
		//System.out.println("pr_vector: "); printM(pr_vector);
		return pr_vector;
	}
	double[][] analyse(List<String> ls) {
		List<String[]>lst=new ArrayList<>();
		for(String s:ls) {
			lst.add(s.split(" "));	
		}
		//String tokenized_word[]=text_str.split(" ");
		double simMatrix[][]=similarityMatrix(lst);
		pr_vector=textRank(simMatrix);
		return pr_vector;
	}
	void printM(double m[][]) {
		int m1=m.length;
		int m2=m[0].length;
		for(int i=0;i<m1;i++) {
			for(int j=0;j<m2;j++) {
				System.out.print(m[i][j]+" ");
			}	
			System.out.println("");
		}
		System.out.println("");
	}
	void printM1(double m[]) {
		int m1=m.length;
		for(int i=0;i<m1;i++) {
			System.out.print(m[i]+" ");
		}
		System.out.println("\n");
	}
	double [][] get_symmetric_matrix(double matrix[][]){
		int m=matrix.length;
		double a[][]=new double[m][m];
		initialize(a,0);
		double transpose[][]=transpose(matrix);
		double diagonal[][]=diagonal(matrix);
		for(int i=0;i<m;i++) {
			for(int j=0;j<m;j++)
				a[i][j]=a[i][j]+transpose[i][j]-diagonal[i][j];
		}
		return a;
	}
	double[][] transpose(double matrix[][]) {
		int m1=matrix.length;
		int m2=matrix[0].length;
		double a[][]=new double[m1][m2];
		initialize(a,0);
		for(int i=0;i<m1;i++) {
			for(int j=0;j<m2;j++) {
				if(i==j)continue;
				a[j][i]=matrix[i][j];
			}		
		}
		return a;
	}
	double[][] diagonal(double matrix[][]) {
		int m=matrix.length;
		double a[][]=new double[m][m];
		initialize(a,0);
		for(int i=0;i<m;i++) {
			a[i][i]=matrix[i][i];
		}
		return a;
		
	}
	double[][] multiply(double a[][],double b[][]){
		int row1=a.length;
		int col1=a[0].length;
		int row2=b.length;
		int col2=b[0].length;
		double c[][]=new double[row1][col2];
		initialize(c,0);
		for(int i=0;i<row1;i++) {
			for(int j=0;j<col2;j++) {
				for(int k=0;k<col1;k++) {
					c[i][j]+=a[i][k]*b[k][j];
				}
			}
		}
		return c;
	}
	double elemSum(double matrix[][]) {
		int m1=matrix.length;
		int m2=matrix[0].length;
		double sum=0;
		for(int i=0;i<m1;i++) {
			for(int j=0;j<m2;j++) {
				sum+=matrix[i][j];
			}
		}
		return sum;
	}
	void initialize(double matrix[][],int n){
		int m1=matrix.length;
		int m2=matrix[0].length;
		for(int i=0;i<m1;i++) {
			for(int j=0;j<m2;j++) {
				matrix[i][j]=n;
			}
		}
	}
}
class sortPR extends sortFather{
	public static void parsePR(List<ProteinData>tmp1){
        List<ProteinData>acs=new ArrayList<>();
        List<ProteinData>dcs=new ArrayList<>();
        List<ProteinData>tmp2=new ArrayList<>();
        PR_Vector p=new PR_Vector();
        
        PR_Vector.sheetname=PR_Vector.size+"-ascendingPR";
        System.out.println("Getting PR_Vector in ascending order...");
        acs=tmp1.stream().sorted((o1,o2)->Double.compare(o1.getPR_Vector(),o2.getPR_Vector())).collect(Collectors.toList());
        System.out.println("Size of dataset: "+acs.size());
        p.outputData(acs);
        
        PR_Vector.sheetname=PR_Vector.size+"-descendingPR";
        System.out.println("Getting PR_Vector in descending order...");
        dcs=tmp1.stream().sorted((o2,o1)->Double.compare(o1.getPR_Vector(),o2.getPR_Vector())).collect(Collectors.toList());
        System.out.println("Size of dataset: "+dcs.size());
        p.outputData(dcs);
        
        PR_Vector.sheetname=PR_Vector.size+"-Top-20-Similar";
        System.out.println("Getting Top 20 similar PR_Vector ...");
        tmp2=dcs.stream().limit(20).collect(Collectors.toList());
        System.out.println("Size of dataset: "+tmp2.size());
        p.outputData(tmp2);   
    }
}
class ProteinData6 extends ProteinData{
    public String data(){
    	return index+"\t"+protein+"\t"+accession+"\t"+sequence+"\t"+annotation+"\t"+interpro+"\t"+org+"\t"+seqLength+"\t"+String.valueOf(pr_vector);
    }
}


