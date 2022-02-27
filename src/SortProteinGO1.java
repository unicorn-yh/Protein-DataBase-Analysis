import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.*;
import java.net.*;

//DOCUMENT: swissprot_exp.txt
//DATA: accession,annotation,type

public class SortProteinGO1 {
    List<ProteinDataWeb>datalist=new ArrayList<>();
    sortClusterGO s=new sortClusterGO();
    SortProteinGO1(){
    	getData();
    	parseData();
    }
	void getData(){
        try{
        	getRawData.getClusterDocument();
			datalist=getRawData.datalist2;
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
	
	void parseData(){
		s.sortByGO(datalist);
	}
	
}

class sortClusterGO extends sortFather{
	String filename="swissprot_data.tsv-cluster",sheetname="";
	String excelPath=FileFunction.path+filename+".xlsx";
	String head[]={"Accession","Annotation","Type"};
	
	void sortByGO(List<ProteinDataWeb>ls) {
		List<String> go=new ArrayList<>();
		int k=0;
		for(ProteinDataWeb i:ls)go.add((i.getAnnotation()).replace("GO:", ""));
		List<String> golist=go.stream().distinct().collect(Collectors.toList());
		int gosize=golist.size();
		System.out.println("Total numbers of GO type: "+gosize+" *(Automtically filters organism type which contains more than 1000 datasets.)");
		System.out.println("Processing data... auto generate to xlsx document...");
		for(int i=0;i<gosize;i++) {
			String o=golist.get(i);
			List<ProteinDataWeb> tmp=new ArrayList<>();
			tmp=ls.stream().filter(s->s.getAnnotation().contains(o)).collect(Collectors.toList());
			int size=tmp.size();
			if(size>1000) {
				System.out.println("Getting sheet "+(++k)+"...\tAnnotation-GO:"+o+"    size of dataset:"+size);
				FileFunction.generateToClusterExcel(filename+"sortGO",o,tmp,head,1);
			}
			
		}
		System.out.println(filename+".xlsx written successfully on disk.");
        System.out.println("Directory path: "+excelPath+". Please check it out.\n");	
	}
}
class ProteinDataGO extends ProteinDataWeb{}


	
