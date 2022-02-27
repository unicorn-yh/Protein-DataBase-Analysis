import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.net.*;

//DOCUMENT: swissprot_data.tsv
//DATA: index,protein,accession,sequence,annotation,interpro,org

public class GetProteinOrganism2 {
    List<ProteinDataWeb>datalist=new ArrayList<>();
    sortCluster s=new sortCluster();
    GetProteinOrganism2(){
    	getData();
    	parseData();
    }
	void getData(){
		try {
			mainProtein.index=2;
			mainProtein.filename="swissprot_data.tsv";
			getRawData.getClusterDocument();
			datalist=getRawData.datalist2;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }
	void parseData() {
		s.sortByOrg(datalist);
	}
	public static String getOrganism(String protein) {   //get organism data from web
		try {
			String site = "http://pfam.xfam.org/protein/"+protein+"?output=xml";
            InputStreamReader isr = new InputStreamReader(new URL(site).openStream(), "UTF-8");
            Thread.sleep(100);
            String s="";
            BufferedReader in = new BufferedReader(isr);
            while (in.ready()) {
                s = in.readLine();
                if(s.contains("species_name=\"")) {
                    s = s.substring(s.indexOf("species_name=\"") + 1);
                    s = s.substring(s.indexOf('"')+1);
                    s = s.substring(0, s.indexOf('"'));
                    break;
                }
           }	
           return s;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}

class sortCluster extends sortFather{
	String filename="swissprot_data.tsv-cluster",sheetname="";
	String excelPath=FileFunction.path+filename+".xlsx";
	String head[]= {"Accession","Protein","Organism","OrgID"};
	/*List<ProteinDataWeb> combineData(List<ProteinDataWeb>ls,List<ProteinDataWeb>webls){
		int k=0;
		System.out.println("start");
		for(ProteinDataWeb i:ls) {
			for(ProteinDataWeb j:webls) {
				if((i.getAccession()).equals(j.getAccession())) {
					i.setOrgName(j.getOrgName());
					i.setProFam(j.getProFam());
					System.out.print(k++);
					continue;
				}
			}
		}
		return ls;
	}*/
	void sortByOrg(List<ProteinDataWeb>ls) {
		List<String> orgID=new ArrayList<>();
		int k=0;
		for(ProteinDataWeb i:ls)orgID.add(i.getOrg());
		List<String> orgType=orgID.stream().distinct().collect(Collectors.toList());
		System.out.println("Total numbers of organism type: "+orgType.size()+" *(Automtically filters organism type which contains more than 100 datasets.)");
		System.out.println("Processing data... auto generate to xlsx document...");
		for(int i=0;i<orgType.size();i++) {
			String o=orgType.get(i);
			List<ProteinDataWeb> tmp=new ArrayList<>();
			tmp=ls.stream().filter(s->s.getOrg().equals(o)).collect(Collectors.toList());
			int size=tmp.size();
			if(size>100) {
				System.out.println("Getting sheet "+(++k)+"...\tOrganismID-"+o+"    size of dataset:"+size);
				FileFunction.generateToClusterExcel(filename+"-OrganismType",o,tmp,head,2);
			}
			
		}
		System.out.println(filename+".xlsx written successfully on disk.");
        System.out.println("Directory path: "+excelPath+". Please check it out.\n");	
	}
}

class ProteinDataWeb extends ProteinData{
	String orgName;
	void setOrgName(String orgName){
		this.orgName=orgName;
	}
	String getOrgName() {
		return orgName;
	}
}
	
