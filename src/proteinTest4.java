import java.io.*;
import java.util.*;

//DOCUMENT: cafa3_partial_data.tsv
//DATA: index,protein,sequence,annotation

public class proteinTest4 extends profather{
	String filename="cafa3_partial_data.tsv",sheetname="Full Dataset";
	static String sortMethod=null;
    List<ProteinData> tmp1=new ArrayList<>();
    List<String> tmp2=new ArrayList<>();
    List<ProteinData>datalist=new ArrayList<>();
    String head[]=new String[5];
    Scanner tt=new Scanner(System.in);
    sort4 s=new sort4();
    proteinTest4(){}
    void getData(){
        try{
        	getRawData.getDocument();
            datalist=getRawData.datalist1;
            head=getRawData.head;
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
        else if(sortMethod.equals("indexOnly")){
            tmp2=s.indexOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="IndexOnly";
            head[1]=head[2]=head[3]=head[4]=head[5]=head[6]="";
        }
        else if(sortMethod.equals("proteinOnly")){
            tmp2=s.proteinOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="ProteinOnly";
            head[0]=head[2]=head[3]=head[4]=head[5]=head[6]="";
        }
        else if(sortMethod.equals("sequenceOnly")){
            tmp2=s.sequenceOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="SequenceOnly";
            head[1]=head[2]=head[0]=head[4]=head[5]=head[6]="";
        }
        else if(sortMethod.equals("annotationOnly")){
            tmp2=s.annotationOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="AnnotationOnly";
            head[1]=head[2]=head[3]=head[0]=head[5]=head[6]="";
        }  
        else if(sortMethod.equals("parseIndex")){
            tmp1=s.parseIndex(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("------------------PARSE--INDEX------------------");
                tmp1=s.parseIndex(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseIndex-"+ch;
            int a=variousOutputMethod(tmp1,"parseIndex");
            if(a==1){
                head[1]=head[2]=head[3]="";
                tmp1.clear();
            }   
        } 
        else if(sortMethod.equals("parseProtein")){
            tmp1=s.parseProtein(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--PROTEIN------------------");
                tmp1=s.parseProtein(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseProtein-"+ch;
            int a=variousOutputMethod(tmp1,"parseProtein");
            if(a==1){
                head[0]=head[2]=head[3]="";
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
                head[1]=head[3]=head[0]="";
                tmp1.clear();
            }
        }
        else if(sortMethod.equals("parseAnnotation")){
            tmp1=s.parseAnnotation(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--ANNOTATION-----------------");
                tmp1=s.parseAnnotation(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseAnnotation-"+ch;
            int a=variousOutputMethod(tmp1,"parseAnnotation");
            if(a==1){
                head[1]=head[2]=head[0]="";
                tmp1.clear();
            }
        }
    }
    int variousOutputMethod(List<ProteinData>ls,String st){
        System.out.println("1. View the column of parsed data only.");
        System.out.println("2. View whole table of parsed data.");
        String input=tt.next();
        while(!(input.equals("1")||input.equals("-1")||input.equals("2"))){
            System.out.println("Illegal inputï¼Œplease input number 1 or 2:");
            input=tt.next();
        }
        if(input.equals("1")){
            if(st.equals("parseIndex"))tmp2=s.indexOnly(ls);
            else if(st.equals("parseProtein"))tmp2=s.proteinOnly(ls);
            else if(st.equals("parseSequence"))tmp2=s.sequenceOnly(ls);
            else if(st.equals("parseAnnotation"))tmp2=s.annotationOnly(ls);
        }
        else if(input.equals("-1"))mainProtein.submain();
        return Integer.parseInt(input);
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")||filetype.equals("csv")) FileFunction.generateToExcel(filename,sheetname,tmp1,tmp2,head,mainProtein.index,filetype);
        else FileFunction.generateToTxt(filename+"-"+sheetname,tmp1,tmp2,head,filetype);
    }
    void mainSort4(){
        System.out.println("Enter number below to parse data:");
        System.out.println("1. View index only.");
        System.out.println("2. View proteins only.");
        System.out.println("3. View sequences only.");
        System.out.println("4. View annotations only.");
        System.out.println("--------------------------------------");
        System.out.println("5. Parse index.");
        System.out.println("6. Parse proteins.");
        System.out.println("7. Parse sequences.");
        System.out.println("8. Parse annotations.");
        String input=tt.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2")||input.equals("4")||input.equals("5")||input.equals("6")||input.equals("7")||input.equals("8")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1-8:");
            input=tt.next();
        }
        if(input.equals("1")) sortMethod="indexOnly";
        else if(input.equals("2")) sortMethod="proteinOnly";
        else if(input.equals("3")) sortMethod="sequenceOnly";
        else if(input.equals("4")) sortMethod="annotationOnly";
        else if(input.equals("5")) sortMethod="parseIndex";
        else if(input.equals("6")) sortMethod="parseProtein";
        else if(input.equals("7")) sortMethod="parseSequence";
        else if(input.equals("8")) sortMethod="parseAnnotation";
        else if(input.equals("-1")) mainProtein.submain();
        getData();
        parseData();
        outputData();
        
    }
}
class sort4 extends sortFather{
}

class ProteinData4 extends ProteinData{
	 public String data(){
	    	return index+"\t"+protein+"\t"+sequence+"\t"+annotation;
	 }
}

