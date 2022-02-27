import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//DOCUMENT: swissprot_exp.txt
//DATA: accession,annotation,type

public class proteinTest1 extends profather{
	String filename="swissprot_exp.txt",sheetname="Full Dataset";
	static String sortMethod=null;
    List<ProteinData> tmp1=new ArrayList<>();
    List<String> tmp2=new ArrayList<>();
    List<ProteinData>datalist=new ArrayList<>();
    String head[]={"Accession","Annotation","Type"};
    Scanner tt=new Scanner(System.in);
    sort1 s=new sort1();
    proteinTest1(){}
    void getData(){
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
        else if(sortMethod.equals("annotationOnly")){
            tmp2=s.annotationOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="AnnotationOnly";
            head[0]=head[2]="";
        }  
        else if(sortMethod.equals("typeOnly")){
            tmp2=s.typeOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="TypeOnly";
            head[0]=head[1]="";
        }
        else if(sortMethod.equals("parseAccession")){
            tmp1=s.parseAccession(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--ACCESSION-----------------");
                tmp1=s.parseAccession(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseAccession-"+ch;
            int a=variousOutputMethod(tmp1,"parseAccesion");
            if(a==1){
                head[1]=head[0]=head[3]=head[4]=head[5]=head[6]="";
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
            	head[0]=head[2]="";
                tmp1.clear();
            }
        }
        else if(sortMethod.equals("parseType")){
            tmp1=s.parseType(datalist);
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseType-"+ch;
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
            else if(st.equals("parseAnnotation"))tmp2=s.annotationOnly(ls);
            else if(st.equals("parseType"))tmp2=s.typeOnly(ls);
        }
        else if(input.equals("-1"))mainProtein.submain();
        return Integer.parseInt(input);
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")||filetype.equals("csv")) FileFunction.generateToExcel(filename,sheetname,tmp1,tmp2,head,mainProtein.index,filetype);
        else FileFunction.generateToTxt(filename+"-"+sheetname,tmp1,tmp2,head,filetype);
    }
    void mainSort1(){
        System.out.println("Enter number below to parse data:");
        System.out.println("1. View accessions only.");
        System.out.println("2. View annotations only.");
        System.out.println("3. View type only.");
        System.out.println("--------------------------------------");
        System.out.println("4. Parse accessions.");
        System.out.println("5. Parse annotations.");
        System.out.println("6. Parse type.");
        String input=tt.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2")||input.equals("4")||input.equals("5")||input.equals("6")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1-14:");
            input=tt.next();
        }
        if(input.equals("1")) sortMethod="accessionOnly";
        else if(input.equals("2")) sortMethod="annotationOnly";
        else if(input.equals("3")) sortMethod="typeOnly";
        else if(input.equals("4")) sortMethod="parseAccession";
        else if(input.equals("5")) sortMethod="parseAnnotation";
        else if(input.equals("6")) sortMethod="parseType";
        else if(input.equals("-1")) mainProtein.submain();
        getData();
        parseData();
        outputData();
    }
}
class sort1 extends sortFather{
	public List<String> typeOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getType).collect(Collectors.toList());
        return tmp2; 
    }
}

class ProteinData1 extends ProteinData{
    public String data(){
        return accession+"\t"+annotation+"\t"+type;
    }
}

