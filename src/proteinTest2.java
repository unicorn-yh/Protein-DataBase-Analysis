import java.io.*;
import java.util.*;

//DICUMENT: swissprot_data.tsv
//DATA: index,protein,accession,sequence,annotation,interpro,org

public class proteinTest2 extends profather{
	String filename="swissprot_data.tsv",sheetname="Full Dataset";
	static String sortMethod=null;
    List<ProteinData> tmp1=new ArrayList<>();
    List<String> tmp2=new ArrayList<>();
    List<ProteinData>datalist=new ArrayList<>();
    String head[]= {"Index","Protein","Accession","Sequence","Annotation","Interpro","Org","Seqlength"};
    Scanner tt=new Scanner(System.in);
    sort2 s=new sort2();
    proteinTest2(){}
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
        else if(sortMethod.equals("accessionOnly")){
            tmp2=s.accessionOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="AccessionOnly";
            head[1]=head[0]=head[3]=head[4]=head[5]=head[6]="";
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
        else if(sortMethod.equals("interproOnly")){
            tmp2=s.interproOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="InterproOnly";
            head[1]=head[2]=head[3]=head[4]=head[0]=head[6]="";
        }  
        else if(sortMethod.equals("orgOnly")){
            tmp2=s.orgOnly(datalist);
            System.out.println("Size of dataset: "+tmp2.size());
            sheetname="OrgOnly";
            head[1]=head[2]=head[3]=head[4]=head[5]=head[0]="";
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
                head[1]=head[2]=head[3]=head[4]=head[5]=head[6]="";
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
                head[0]=head[2]=head[3]=head[4]=head[5]=head[6]="";
                tmp1.clear();
            }   
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
                head[1]=head[2]=head[0]=head[4]=head[5]=head[6]="";
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
                head[1]=head[2]=head[3]=head[0]=head[5]=head[6]="";
                tmp1.clear();
            }
        }
        else if(sortMethod.equals("parseInterpro")){
            tmp1=s.parseInterpro(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("-----------------PARSE--INTERPRO-----------------");
                tmp1=s.parseInterpro(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseInterpro-"+ch;
            int a=variousOutputMethod(tmp1,"parseInterpro");
            if(a==1){
                head[1]=head[2]=head[3]=head[4]=head[0]=head[6]="";
                tmp1.clear();
            }
        }
        else if(sortMethod.equals("parseOrg")){
            tmp1=s.parseOrg(datalist);
            while(tmp1.size()==0){
                System.out.println("No data matched! Please input again.");
                System.out.println("--------------------PARSE--ORG--------------------");
                tmp1=s.parseOrg(datalist);
            }
            System.out.println("Size of dataset: "+tmp1.size());
            sheetname="parseOrg-"+ch;
            int a=variousOutputMethod(tmp1,"parseOrg");
            if(a==1){
                head[1]=head[2]=head[3]=head[4]=head[5]=head[0]="";
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
            if(st.equals("parseIndex"))tmp2=s.indexOnly(ls);
            else if(st.equals("parseProtein"))tmp2=s.proteinOnly(ls);
            else if(st.equals("parseAccession"))tmp2=s.accessionOnly(ls);
            else if(st.equals("parseSequence"))tmp2=s.sequenceOnly(ls);
            else if(st.equals("parseAnnotation"))tmp2=s.annotationOnly(ls);
            else if(st.equals("parseInterpro"))tmp2=s.interproOnly(ls);
            else tmp2=s.orgOnly(ls);
        }
        else if(input.equals("-1"))mainProtein.submain();
        return Integer.parseInt(input);
    }
    void outputData(){
        filetype=FileFormat.setFileType();
        if(filetype.equals("xlsx")||filetype.equals("csv")) FileFunction.generateToExcel(filename,sheetname,tmp1,tmp2,head,mainProtein.index,filetype);
        else FileFunction.generateToTxt(filename+"-"+sheetname,tmp1,tmp2,head,filetype);
    }
    void mainSort2(){
        System.out.println("Enter number below to parse data:");
        System.out.println("1. View index only.");
        System.out.println("2. View proteins only.");
        System.out.println("3. View accessions only.");
        System.out.println("4. View sequences only.");
        System.out.println("5. View annotations only.");
        System.out.println("6. View interpros only.");
        System.out.println("7. View orgs only.");
        System.out.println("--------------------------------------");
        System.out.println("8. Parse index.");
        System.out.println("9. Parse proteins.");
        System.out.println("10. Parse accessions.");
        System.out.println("11. Parse sequences.");
        System.out.println("12. Parse annotations.");
        System.out.println("13. Parse interpros.");
        System.out.println("14. Parse orgs.");
        String input=tt.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2")||input.equals("4")||input.equals("5")||input.equals("6")||input.equals("7")||input.equals("8")||input.equals("9")||input.equals("10")||input.equals("11")||input.equals("12")||input.equals("13")||input.equals("14")||input.equals("-1"))){
            System.out.println("Illegal inputï¼Œplease input number 1-14:");
            input=tt.next();
        }
        if(input.equals("1")) sortMethod="indexOnly";
        else if(input.equals("2")) sortMethod="proteinOnly";
        else if(input.equals("3")) sortMethod="accessionOnly";
        else if(input.equals("4")) sortMethod="sequenceOnly";
        else if(input.equals("5")) sortMethod="annotationOnly";
        else if(input.equals("6")) sortMethod="interproOnly";
        else if(input.equals("7")) sortMethod="orgOnly";
        else if(input.equals("8")) sortMethod="parseIndex";
        else if(input.equals("9")) sortMethod="parseProtein";
        else if(input.equals("10")) sortMethod="parseAccession";
        else if(input.equals("11")) sortMethod="parseSequence";
        else if(input.equals("12")) sortMethod="parseAnnotation";
        else if(input.equals("13")) sortMethod="parseInterpro";
        else if(input.equals("14")) sortMethod="parseOrg";
        else if(input.equals("-1")) mainProtein.submain();
        getData();
        parseData();
        outputData();
    }
}
class sort2 extends sortFather{
}

class ProteinData2 extends ProteinData{
	public String data(){
		return index+"\t"+protein+"\t"+accession+"\t"+sequence+"\t"+annotation+"\t"+interpro+"\t"+org+"\t"+seqLength;
	}
}

