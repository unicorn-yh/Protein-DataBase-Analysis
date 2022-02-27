import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//DOCUMENT: swissprot_exp.fasta & cafa3_partial_data.fa
//DATA: protein,sequence

public class proteinTest3 extends profather{
	String filename="",filepath="",sheetname="Full Dataset";
	static String sortMethod=null;
    List<ProteinData> tmp1=new ArrayList<>();
    List<String> tmp2=new ArrayList<>();
    List<ProteinData>datalist=new ArrayList<>();
    String head[]={"Accession","SeqLength","Sequence"};
    Scanner tt=new Scanner(System.in);
    sort1 s=new sort1();
    proteinTest3(){}
    void getData(){
    	if(mainProtein.index==3) {
    		filepath="swissprot_exp.fasta";
    		filename="swissprot_exp.fasta";
    	}
    	else if(mainProtein.index==4) {
    		filepath="cafa3_partial_data.fa";
    		filename="cafa3_partial_data.fa";
    	}
        try{
        	getRawData.getDocument();
            datalist=getRawData.datalist1;
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
    void parseData(){
        if(sortMethod==null){
            tmp1=datalist;
            System.out.println("Size of dataset: "+tmp1.size());
        }
        else if(sortMethod.equals("accessionOnly")){
            tmp2=s.accessionOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="AccessionOnly";
            head[1]=head[2]="";
        }
        else if(sortMethod.equals("sequenceOnly")){
            tmp2=s.sequenceOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="SequenceOnly";
            head[0]=head[1]="";
        }
        else if(sortMethod.equals("parseAccession")){
            tmp1=s.parseAccession(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--ACCESSION------------------");
                tmp1=s.parseAccession(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseAccession-"+ch;
            int a=variousOutputMethod(tmp1,"parseAccession");
            if(a==1){
                head[1]="";
                tmp1.clear();
            }   
        } 
        else if(sortMethod.equals("parseSequence")){
            tmp1=s.parseSequence(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--SEQUENCE-----------------");
                tmp1=s.parseSequence(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseSequence-"+ch;
            int a=variousOutputMethod(tmp1,"parseSequence");
            if(a==1){
                head[0]="";
                tmp1.clear();
            }
        }   
    }
    int variousOutputMethod(List<ProteinData>ls,String st){
        System.out.println("1. View the column of parsed data only.");
        System.out.println("2. View whole table of parsed data.");
        String input=tt.next();
        while(!(input.equals("1")||input.equals("-1")||input.equals("2"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=tt.next();
        }
        if(input.equals("1")){
            if(st.equals("parseAccession"))tmp2=s.accessionOnly(ls);
            else if(st.equals("parseSequence"))tmp2=s.sequenceOnly(ls);
        }
        else if(input.equals("-1"))mainProtein.submain();
        return Integer.parseInt(input);
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")||filetype.equals("csv")) FileFunction.generateToExcel(filename,sheetname,tmp1,tmp2,head,mainProtein.index,filetype);
        else {
        	FileFunction.generateToTxt(filename+"-"+sheetname,tmp1,tmp2,head,filetype);
        }
    }
    void mainSort3(){
        System.out.println("Enter number below to parse data:");
        System.out.println("1. View accessions only.");
        System.out.println("2. View sequences only.");
        System.out.println("--------------------------------------");
        System.out.println("3. Parse accessions.");
        System.out.println("4. Parse sequences.");
        String input=tt.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2")||input.equals("4")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1-4:");
            input=tt.next();
        }
        if(input.equals("1")) sortMethod="accessionOnly";
        else if(input.equals("2")) sortMethod="sequenceOnly";
        else if(input.equals("3")) sortMethod="parseAccession";
        else if(input.equals("4")) sortMethod="parseSequence";
        else if(input.equals("-1")) mainProtein.submain();
        getData();
        parseData();
        outputData();
    }
}
class sort3 extends sortFather{}

class ProteinData3 extends ProteinData{
    public String data(){
        return accession+"\t"+getSequenceLength()+"\t"+sequence;
    }
}

