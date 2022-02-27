 /*   Java Programming Assignment    *
 *   @author  Olivia Lai Yung Hwei   *
 *     @version 1.60, 10/12/2021     *
 *          @since   JDK1.7          *
 *  Due Date: Thursday, 2021 Dec.23  *
 *************************************/

//Main Function

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class mainProtein{
    static Scanner sc=new Scanner(System.in);
    static int dataCount=0,index=0,iscluster=0;
    static String filename="";
    static profather f;
    public static String path;
    
    public static void main(String args[]){
        System.out.println("Entering Protein Database System created by Olivia...");
        System.out.println("Please enter the path you would like to download your document:");
        path=sc.next();
        System.out.print("Getting protein database ");
        sleep();
        selectDocument();
        submain();
    }
    static void submain(){    
    	iscluster=0;
        System.out.println("HOME:");
        System.out.println("Press 3 to change DOCUMENT");
        System.out.println("Press 2 to enter CLUSTERING");  
        System.out.println("Press 1 to enter BASIC MAIN");     
        System.out.println("Press 0 to EXIT");
        String a=sc.next();
        while(!(a.equals("1")||a.equals("0")||a.equals("2")||a.equals("3"))){
            System.out.println("Illegal input! please input number 1 or 0:");
            a=sc.next();
        }
        if(a.equals("0"))exit();
        else if(a.equals("3")){
        	selectDocument();
        	submain();
        }
        else if(a.equals("1")){
        	System.out.println("\nMAIN: Do you want to parse the data? Yes, press 1; No, press 0;");   
            System.out.println("(Press -1 when ever you want to come back to HOME)");    
            String parseData=sc.next();
            while(!(parseData.equals("1")||parseData.equals("0")||parseData.equals("-1"))){
                System.out.println("Illegal input! please input number 1 or 0:");
                parseData=sc.next();
            }
            if(parseData.equals("0")) {   // UnsortedRawData(); 
             	f.sortMethod=null;
                 f.getData();
                 f.parseData();
                 f.outputData(); 
             } 
            else if(parseData.equals("-1")) submain();
            else {
            	if(index==1)f.mainSort1();
            	else if(index==2)f.mainSort2();
            	else if(index==3)f.mainSort3();
            	else if(index==4)f.mainSort3();
            	else if(index==5)f.mainSort4();
            }
            System.out.printf("DATASET %d COMPLETED!\n",++dataCount);
            System.out.println("REST STATION: Do you want to continue parse data? Yes, press 1; No, press 0;");
            String iscontinue=sc.next();
            while(!(iscontinue.equals("1")||iscontinue.equals("0")||iscontinue.equals("-1"))){
                System.out.println("Illegal input! please input number 1 or 0:");
                iscontinue=sc.next();
            }
            if(iscontinue.equals("1")||iscontinue.equals("-1"))submain();
            else exit();
            sc.close();
        }    
        else {
        	iscluster=1;
        	clustering.clustermain();
        }
    }
    public static void sleep(){
        for(int i=0;i<6;i++){
            System.out.print(".");
            try{
                TimeUnit.MILLISECONDS.sleep(250);
            }
            catch(Exception e){
                e.getMessage();
            }
        } 
    }
    static void exit(){
        System.out.print("EXIT: Exiting Protein Database System ");
        sleep();
        System.out.println("Thank you for using.");
        for(int i=0;i<6;i++){
            System.out.print("");
            try{
                TimeUnit.MILLISECONDS.sleep(250);
            }
            catch(Exception e){
                e.getMessage();
            }
        } 
        System.exit(1);
    }
    static void selectDocument(){
        System.out.println("\nPlease select the following documents to parse:");   
        System.out.println("1. swissprot_exp.txt");
        System.out.println("2. swissprot_data.tsv");
        System.out.println("3. swissprot_exp.fasta");
        System.out.println("4. cafa3_partial_data.fa");
        System.out.println("5. cafa3_partial_data.tsv");
        String a=sc.next();
        while(!(a.equals("1")||a.equals("5")||a.equals("2")||a.equals("3")||a.equals("4"))){
            System.out.println("Illegal input! please input number 1-5:");
            a=sc.next();
        }
        if(a.equals("1")){          //swissprot_exp.txt
            f=new proteinTest1();
            index=1;
            filename="swissprot_exp.txt";
        }
        else if(a.equals("2")){     //swissprot_data.tsv
            f=new proteinTest2();
            index=2;
            filename="swissprot_data.tsv";
        }
        else if(a.equals("3")){     //swissprot_exp.fasta
        	f=new proteinTest3();
            index=3;
            filename="swissprot_exp.fasta";
        }
        else if(a.equals("4")){    //cafa3_partial_data.fa
            f=new proteinTest3();
            index=4;
            filename="cafa3_partial_data.fa";
        }
        else if(a.equals("5")){    //cafa3_partial_data.tsv
            f=new proteinTest4();
            index=5;
            filename="cafa3_partial_data.tsv";
        }
    }
 	
}
class getRawData {
	public static List<ProteinData> datalist1=new ArrayList<>();
	public static List<ProteinDataWeb> datalist2=new ArrayList<>();
	static String head[];
	static String filename=mainProtein.filename;
	static int index=mainProtein.index;
	
	static void getDocument()throws FileNotFoundException {
    	InputStream ir=getRawData.class.getClassLoader().getResourceAsStream(filename);
    	if(ir==null) throw new FileNotFoundException("Resource not found: "+filename);
		BufferedReader br=new BufferedReader(new InputStreamReader(ir));
		System.out.print("Getting data from database ");
		mainProtein.sleep();
		System.out.println("");
		String n=null,s="";
        int i=1,flag=0;
		
		try {
			if(index==1) {  
	            while((n=br.readLine())!=null){
	                ProteinData p=new ProteinData1();
	                StringTokenizer parse=new StringTokenizer(n,"\t");
	                while(parse.hasMoreTokens()){
	                    s=parse.nextToken();
	                    if(i==1){p.setAccession(s);;i++;}
	                    else if(i==2){p.setAnnotation(s);;i++;}
	                    else{
	                        p.setType(s);
	                        datalist1.add(p);
	                        i=1;
	                    }    
	                } 
	            }
			}
			if(index==2) {
				head=new String[8];
	            while((n=br.readLine())!=null){
	                ProteinData v=new ProteinData2();
	                StringTokenizer parse=new StringTokenizer(n,"\t");
	                while(parse.hasMoreTokens()){
	                    s=parse.nextToken();
	                    if(flag==0){
	                        head[i-1]=s;i++;
	                        if(i==8){
	                            head[7]="seqlength";
	                            flag=1;
	                            i=1;
	                        }
	                    } //header
	                    else{
	                        if(i==1){v.setIndex(s);i++;}
	                        else if(i==2){v.setProtein(s);i++;}
	                        else if(i==3){v.setAccession(s);i++;}
	                        else if(i==4){v.setSequence(s);i++;}
	                        else if(i==5){v.setAnnotation(s);i++;}
	                        else if(i==6){v.setInterpro(s);i++;}
	                        else{
	                            v.setOrg(s);
	                            datalist1.add(v);
	                            i=1;
	                        }
	                    }     
	                } //while
	            }//while
			}
			if(index==3||index==4) {
	            while((n=br.readLine())!=null){
	                ProteinData p=new ProteinData3();
	                if(n.startsWith(">")){
	                    if(s.length()!=0){
	                        p.setSequence(s);
	                        s="";
	                        datalist1.add(p);
	                    }
	                    p.setAccession(n.replace(">",""));
	                }
	                else{
	                    s=s+n+"\n";
	                }
	            }
	            br.close();
			}
			if(index==5) {
				head=new String[5];
				head[0]="index";
	            head[4]="seqlength";
	            while((n=br.readLine())!=null){
	                ProteinData v=new ProteinData4();
	                StringTokenizer parse=new StringTokenizer(n,"\t");
	                while(parse.hasMoreTokens()){
	                    s=parse.nextToken();
	                    if(flag==0){
	                        head[i]=s;i++;
	                        if(i==4){
	                            flag=1;
	                            i=1;
	                        }
	                    } //header
	                    else{
	                        if(i==1){v.setIndex(s);i++;}
	                        else if(i==2){v.setProtein(s);i++;}
	                        else if(i==3){v.setSequence(s);i++;}
	                        else{
	                            v.setAnnotation(s);
	                            datalist1.add(v);
	                            i=1;
	                        }
	                    }     
	                } 
	            }
	            br.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
    }
	static void getClusterDocument() throws FileNotFoundException {  
		filename=mainProtein.filename;
		InputStream ir=mainProtein.class.getClassLoader().getResourceAsStream(filename);
    	if(ir==null) throw new FileNotFoundException("Resource not found: "+filename);
		BufferedReader br=new BufferedReader(new InputStreamReader(ir));
		System.out.print("Getting data from database ");
		mainProtein.sleep();
		System.out.println("");
		String n=null,s="";
        int i=1,flag=0;
        
		try {
			if(index==1) {
				while((n=br.readLine())!=null){
	                ProteinDataWeb p=new ProteinDataGO();
	                StringTokenizer parse=new StringTokenizer(n,"\t");
	                while(parse.hasMoreTokens()){
	                    s=parse.nextToken();
	                    if(i==1){p.setAccession(s);;i++;}
	                    else if(i==2){p.setAnnotation(s);;i++;}
	                    else{
	                        p.setType(s);
	                        datalist2.add(p);
	                        i=1;
	                    }    
	                } 
	            }
	            br.close();
			}
			if(index==2) {
	            while((n=br.readLine())!=null){
	                ProteinDataWeb v=new ProteinDataWeb();
	                StringTokenizer parse=new StringTokenizer(n,"\t");
	                while(parse.hasMoreTokens()){
	                    s=parse.nextToken();
	                    if(flag==0){   //header
	                        i++;
	                        if(i==8){flag=1;i=1;}
	                    } 
	                    else{
	                        if(i==1){v.setIndex(s);i++;}
	                        else if(i==2){v.setProtein(s);i++;}
	                        else if(i==3){v.setAccession(s);i++;}
	                        else if(i==4){v.setSequence(s);i++;}
	                        else if(i==5){v.setAnnotation(s);i++;}
	                        else if(i==6){v.setInterpro(s);i++;}
	                        else{
	                            v.setOrg(s);
	                            datalist2.add(v);
	                            i=1;
	                        }
	                    }     
	                } //while
	            }//while
			}//if
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}



