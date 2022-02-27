import java.io.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
class FileFormat{
    public static String setFileType(){
        Scanner s=new Scanner(System.in);
        System.out.println("Please input format of the file:");
        String a=s.next();
        if(a.equals("xlsx")||a.equals("txt")||a.equals("tsv")||a.equals("fa")||a.equals("fasta")||a.equals("csv"))return a;
        else if(a.equals("-1")) {
        	mainProtein.submain();
        	return "";
        }
        else {
        	System.out.println("Illegal input! Please input the following supported file format:");
        	System.out.println("xlsx /txt /tsv /fa /fasta /csv");
        	 a=s.next();
             while(!(a.equals("xlsx")||a.equals("txt")||a.equals("tsv")||a.equals("fa")||a.equals("fasta")||a.equals("csv"))){
                 System.out.println("Illegal input! please input the correct file format:");
                 System.out.println("xlsx /txt /tsv /fa /fasta /csv");
                 a=s.next();
             }
             return a;
        }
    }
}
public class FileFunction{
    static String path=mainProtein.path;
    static Scanner sc=new Scanner(System.in);
    public static void generateToExcel(String filename,String sheetname,List<ProteinData>list1,List<String>list2,String title[],int file,String fileType){
        String excelPath=path+filename+"."+fileType;
        FileInputStream f=null;
        XSSFWorkbook wb=null;
        XSSFSheet sheet=null;
        if(new File(excelPath).exists()) { 
        	try {
        		f=new FileInputStream(excelPath);
        		wb=(XSSFWorkbook)WorkbookFactory.create(f);
        	}
        	catch(IOException e) {
        		e.printStackTrace();
        	}
        	XSSFSheet sheetExist=wb.getSheet(sheetname);
        	if(sheetExist==null) {
        		sheet=wb.createSheet(sheetname);  
        	}
        	else {            
        		int index=wb.getSheetIndex(sheetname);
        		System.out.println("Sheet already exists in workbook, do you want to replace the sheet in file destination? Yes, press 1; No, press 0;");
    			String a=sc.next();
    			while(!(a.equals("1")||a.equals("0"))){
    				System.out.println("Illegal input! please input number 1 or 0:");
    				a=sc.next();
    		    }
    		    if(a.equals("1")) {        
    		    	wb.removeSheetAt(index);
    		    	sheet=wb.createSheet(sheetname);
    		    }
    		    else mainProtein.submain();                    
        	}
        	
        }
        else {                          
        	wb=new XSSFWorkbook();
        	sheet=wb.createSheet(sheetname);
        }
        

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short)540); 
        cell.setCellValue(sheetname);   

        CellStyle style=wb.createCellStyle(); 
        Font font=wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)14);
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);   
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setLocked(false); 
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setWrapText(true); 
        style.getShrinkToFit();
        cell.setCellStyle(style); 
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));  
        sheet.autoSizeColumn(5200);

        row=sheet.createRow(1);     
        if(list1.size()<1 && title.length>=8)title[7]="";
        for(int i=0,b=0;i<title.length;i++){  
            if(!title[i].equals("")){
                cell=row.createCell(b);
                cell.setCellValue(title[i]);  
                cell.setCellStyle(style); 
                sheet.setColumnWidth(b,20*256); 
                b++;
            }
        }  

        row.setHeight((short) 540);  
        int flag=0;
        if(list1.size()>=1){
            int j=2;
            System.out.println("Processing data...");
            if(file==1) {                  // swissprot_exp.txt
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getAccession());
                    row.createCell(1).setCellValue(i.getAnnotation());
                    row.createCell(2).setCellValue(i.getType());
                }
            }
            if(file==2) {                  //swissprot_data.tsv
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getIndex());
                    row.createCell(1).setCellValue(i.getProtein());
                    row.createCell(2).setCellValue(i.getAccession());
                    String tmpp=i.getSequence();
                    if(i.getSequenceLength()>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(3).setCellValue(tmpp);
                    row.createCell(4).setCellValue(i.getAnnotation());
                    row.createCell(5).setCellValue(i.getInterpro());
                    row.createCell(6).setCellValue(i.getOrg());
                    row.createCell(7).setCellValue(i.getSequenceLength());
                }
            }
            if(file==3||file==4) {                  // swissprot_exp.fasta & cafa3_partial_data.fa
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getAccession());
                    String tmpp=i.getSequence();
                    int seqlen=i.getSequenceLength();
                    if(seqlen>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(1).setCellValue(tmpp);
                    row.createCell(2).setCellValue(seqlen);  
                }
            }
            if(file==5) {                    // cafa3_partial_data.tsv
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getIndex());
                    row.createCell(1).setCellValue(i.getProtein());
                    String tmpp=i.getSequence();
                    if(i.getSequenceLength()>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(2).setCellValue(tmpp);
                    row.createCell(3).setCellValue(i.getAnnotation());
                    row.createCell(4).setCellValue(i.getSequenceLength());
                }
            }
            if(file==6) {                  //swissprot_data.tsv
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getIndex());
                    row.createCell(1).setCellValue(i.getProtein());
                    row.createCell(2).setCellValue(i.getAccession());
                    String tmpp=i.getSequence();
                    if(i.getSequenceLength()>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(3).setCellValue(tmpp);
                    row.createCell(4).setCellValue(i.getAnnotation());
                    row.createCell(5).setCellValue(i.getInterpro());
                    row.createCell(6).setCellValue(i.getOrg());
                    row.createCell(7).setCellValue(i.getSequenceLength());
                    row.createCell(8).setCellValue(i.getPR_Vector());
                }
            }
            
        }  
        else{
            int j=2;
            System.out.println("Processing data...");
            for(String i:list2){
                if(i.length()>32767){
                    i=i.substring(0,32767);
                    flag=1;
                }
                row=sheet.createRow(j++);
                row.setHeight((short) 500); 
                row.createCell(0).setCellValue(i);
            }

        }
        try{
            FileOutputStream out = new FileOutputStream(new File(excelPath));
            wb.write(out);
            out.close();
            wb.close();
            if(flag==1) {
            	System.out.println("Datasets contain 'Sequence' length larger than 32768 which is incompatible in cells of excel document.");
            	System.out.println("Please choose '.txt' file format or check the original database for accurate data.");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println(filename+".xlsx written successfully on disk.");
            System.out.println("Directory path: "+excelPath+". Please check it out.\n");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void generateToTxt(String filename,List<ProteinData>list1,List<String>list2,String title[],String type){
        String Path="";
        if(type.equals("txt")) Path=path+filename+".txt";
        else if(type.equals("fa")) Path=path+filename+".fa";
        else if(type.equals("fasta")) Path=path+filename+".fasta";
        else if(type.equals("tsv")) Path=path+filename+".tsv";
        try{
            System.out.println("Processing data...");
            FileWriter fw=new FileWriter(Path);
            if(list1.size()<1)title[title.length-1]="";
            for(String i:title)if(!i.equals(""))fw.write(i+"\t");
            fw.write(System.lineSeparator());
            if(list1.size()>=1){
                for(ProteinData v:list1)fw.write(v.data()+System.lineSeparator());
            }
            else{
                for(String v:list2)fw.write(v+System.lineSeparator());
            }
            fw.close();
            System.out.println(filename+"."+type+" written successfully on disk.");
            System.out.println("Directory path: "+Path+". Please check it out.");
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void generateToClusterExcel(String filename,String sheetname,List<ProteinDataWeb>list1,String title[],int file){
        String fileType="xlsx";
        String excelPath=path+filename+"."+fileType;
        FileInputStream f=null;
        XSSFWorkbook wb=null;
        XSSFSheet sheet=null;
        if(new File(excelPath).exists()) { 
        	try {
        		f=new FileInputStream(excelPath);
        		wb=(XSSFWorkbook)WorkbookFactory.create(f);
        	}
        	catch(IOException e) {
        		e.printStackTrace();
        	}
        	XSSFSheet sheetExist=wb.getSheet(sheetname);
        	if(sheetExist==null) {
        		sheet=wb.createSheet(sheetname);  
        	}
        	else {            
        		int index=wb.getSheetIndex(sheetname);
        		System.out.println("Sheet already exists in workbook, system automtically replaces the sheet in file destination.");
    		    wb.removeSheetAt(index); 
    		    sheet=wb.createSheet(sheetname);
        	}
        	
        }
        else {                          
        	wb=new XSSFWorkbook();
        	sheet=wb.createSheet(sheetname);
        }
        

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        row.setHeight((short)540); 
        cell.setCellValue(sheetname);   

        CellStyle style=wb.createCellStyle(); 
        Font font=wb.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short)14);
        font.setBold(true);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);   
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setLocked(false); 
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setWrapText(true); 
        style.getShrinkToFit();
        cell.setCellStyle(style); 
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));  
        sheet.autoSizeColumn(5200);

        row=sheet.createRow(1);     
        if(list1.size()<1 && title.length>=8)title[7]="";
        for(int i=0,b=0;i<title.length;i++){  
            if(!title[i].equals("")){
                cell=row.createCell(b);
                cell.setCellValue(title[i]);  
                cell.setCellStyle(style); 
                sheet.setColumnWidth(b,20*256); 
                b++;
            }
        }  

        row.setHeight((short) 540);  
        int flag=0;
        if(list1.size()>=1){
            int j=2;
            if(file==1) {                  // swissprot_exp.txt
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getAccession());
                    row.createCell(1).setCellValue(i.getAnnotation());
                    row.createCell(2).setCellValue(i.getType());
                }
            }
            else if(file==2) {                 // swissprot_data.tsv
            	int df=0;
            	String organism="",org="";
            	for(ProteinDataWeb i:list1){
            		if(df==0) {
            			organism=GetProteinOrganism2.getOrganism(i.getProtein());
            			org=i.getOrg();
            			df=1;
            		}
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getAccession());
                    row.createCell(1).setCellValue(i.getProtein());
                    row.createCell(2).setCellValue(org);
                    row.createCell(3).setCellValue(organism);
                }
            }
            else if(file==3||file==4) {                  // swissprot_exp.fasta & cafa3_partial_data.fa
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getProtein());
                    String tmpp=i.getSequence();
                    int seqlen=i.getSequenceLength();
                    if(seqlen>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(1).setCellValue(tmpp);
                    row.createCell(2).setCellValue(seqlen);  
                }
            }
            else if(file==5) {                           // cafa3_partial_data.tsv
            	for(ProteinData i:list1){
                    row=sheet.createRow(j++);
                    row.setHeight((short)500); 
                    row.createCell(0).setCellValue(i.getIndex());
                    row.createCell(1).setCellValue(i.getProtein());
                    String tmpp=i.getSequence();
                    if(i.getSequenceLength()>32767){
                        tmpp=i.getSequence().substring(0,32767);
                        flag=1;
                    }
                    row.createCell(2).setCellValue(tmpp);
                    row.createCell(3).setCellValue(i.getAnnotation());
                    row.createCell(4).setCellValue(i.getSequenceLength());
                }
            }
            
        }  
        try{
            FileOutputStream out = new FileOutputStream(new File(excelPath));
            wb.write(out);
            out.close();
            wb.close();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

