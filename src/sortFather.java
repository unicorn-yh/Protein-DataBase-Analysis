
import java.util.*;
import java.util.stream.Collectors;

public class sortFather{
	Scanner t=new Scanner(System.in);
    public List<String> indexOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getIndex).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> proteinOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getProtein).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> accessionOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getAccession).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> sequenceOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getSequence).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> annotationOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getAnnotation).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> interproOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getInterpro).collect(Collectors.toList());
        return tmp2; 
    }
    public List<String> orgOnly(List<ProteinData>tmp1){
        List<String>tmp2 = tmp1.stream().map(ProteinData::getOrg).collect(Collectors.toList());
        return tmp2; 
    }

    public List<ProteinData> parseIndex(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Index data:");
        System.out.println("1. Ascending order.");
        System.out.println("2. Descending order.");
        System.out.println("3. Find Index.");
        System.out.println("4. Find Index by certain contained digits.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1-4:");
            input=t.next();
        }
        if(input.equals("1")){
            profather.ch="ascending";
            tmp2=tmp1.stream().sorted((o1,o2)->Integer.compare(Integer.parseInt(o1.getIndex()),Integer.parseInt(o2.getIndex()))).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            profather.ch="descending";
            
            tmp2=tmp1.stream().sorted((o2,o1)->Integer.compare(Integer.parseInt(o1.getIndex()),Integer.parseInt(o2.getIndex()))).collect(Collectors.toList());
        }
        else if(input.equals("3")){
            System.out.println("Please input the index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->s.getIndex().equals(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("4")){
            System.out.println("Please input numbers contained in index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->s.getIndex().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseProtein(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Protein data:");
        System.out.println("1. Find Protein by full name.");
        System.out.println("2. Find Protein by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the name of Protein:");
            String a=t.next();
            profather.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getProtein().equals(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            System.out.println("Please input characters contained in the name of Protein:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetterOrDigit)){
                System.out.println("Illegal input! please input letters or numbers only.");
                a=t.next();
            }
            profather.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getProtein().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseAccession(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Accession data:");
        System.out.println("1. Find Accession by full name.");
        System.out.println("2. Find Accession by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")||input.equals("2")){
            System.out.println("Please input the name of Accession:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetterOrDigit)){
                System.out.println("Illegal input! please input letters or numbers only.");
                a=t.next();
            }
            profather.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getAccession().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            System.out.println("Please input characters contained in Accession:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetterOrDigit)){
                System.out.println("Illegal input! please input letters or numbers only.");
                a=t.next();
            }
            profather.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getAccession().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseSequence(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Sequence data:");
        System.out.println("1. Find Sequence by length.");
        System.out.println("2. Find Sequence by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the length of Protein Sequence:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only.");
                a=t.next();
            }
            profather.ch="seqlength"+a;
            int k=Integer.parseInt(a);
            tmp2=tmp1.stream().filter(s->s.getSequenceLength()==k).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            System.out.println("Please input characters contained in Sequence:");
            String a=t.next();
            if(!a.chars().allMatch(Character::isLetter)){
                System.out.println("Illegal input! please input letters only.");
                a=t.next();
            }
            profather.ch=a.toUpperCase();
            tmp2=tmp1.stream().filter(s->s.getSequence().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseAnnotation(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Annotation data:");
        System.out.println("1. Find Annotation by index.");
        System.out.println("2. Find Annotation by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the Annotation index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getAnnotation()).replace("GO:","").contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            System.out.println("Please input numbers contained in Annotation index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal inputplease input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getAnnotation()).replace("GO:","").contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseInterpro(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Interpro data:");
        System.out.println("1. Find Interpro by index.");
        System.out.println("2. Find Interpro by certain contained character.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1 or 2:");
            input=t.next();
        }
        if(input.equals("1")){
            System.out.println("Please input the Interpro index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            a=a.toUpperCase();
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getInterpro()).replace("IPR","").contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            System.out.println("Please input numbers contained in Interpro index:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->(s.getInterpro()).replace("IPR","").contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseOrg(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Org data:");
        System.out.println("1. Ascending order.");
        System.out.println("2. Descending order.");
        System.out.println("3. Find Org number.");
        System.out.println("4. Find Org number by certain contained digits.");
        String input=t.next();
        while(!(input.equals("1")||input.equals("2")||input.equals("3")||input.equals("4")||input.equals("-1"))){
            System.out.println("Illegal input! please input number 1-4:");
            input=t.next();
        }
        if(input.equals("1")){
            profather.ch="ascending";
            tmp2=tmp1.stream().sorted((o1,o2)->Integer.compare(Integer.parseInt(o1.getOrg()),Integer.parseInt(o2.getOrg()))).collect(Collectors.toList());
        }
        else if(input.equals("2")){
            profather.ch="descending";
            tmp2=tmp1.stream().sorted((o2,o1)->Integer.compare(Integer.parseInt(o1.getOrg()),Integer.parseInt(o2.getOrg()))).collect(Collectors.toList());
        }
        else if(input.equals("3")){
            System.out.println("Please input the Org number:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->s.getOrg().equals(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("4")){
            System.out.println("Please input numbers contained in Org number:");
            String a=t.next();
            while(!a.chars().allMatch(Character::isDigit)){
                System.out.println("Illegal input! please input numbers only:");
                a=t.next();
            }
            profather.ch=a;
            tmp2=tmp1.stream().filter(s->s.getOrg().contains(profather.ch)).collect(Collectors.toList());
        }
        else if(input.equals("-1"))mainProtein.submain();
        return tmp2;
    }
    public List<ProteinData> parseType(List<ProteinData>tmp1){
        List<ProteinData>tmp2=new ArrayList<>();
        System.out.println("Ways to parse Protein Type data:");
        System.out.println("1. P\t2. C\t3. F");
        String input=t.next();
        while(!(input.equals("3")||input.equals("1")||input.equals("2"))){
            System.out.println("Illegal input! please input number 1-3:");
            input=t.next();
        }
        if(input.equals("1")) {
            profather.ch="P";
            tmp2=tmp1.stream().filter(s->s.getType().equals("P")).collect(Collectors.toList());
        }
        else if(input.equals("2")) {
            profather.ch="C";
            tmp2=tmp1.stream().filter(s->s.getType().equals("C")).collect(Collectors.toList());
        }
        else {
            profather.ch="F";
            tmp2=tmp1.stream().filter(s->s.getType().equals("F")).collect(Collectors.toList());
        }
        return tmp2;
    }
    public final boolean containsDigit(String s){
        boolean containsDigit = false;
        if(s!=null && !s.isEmpty()){
            for(char c:s.toCharArray()){
                if (containsDigit=Character.isDigit(c))break;
            }
        }
        return containsDigit;
    }
    public final boolean containsLetter(String s){
        boolean containsletter = false;
        if(s!=null && !s.isEmpty()){
            for(char c:s.toCharArray()){
                if (containsletter=Character.isLetter(c))break;
            }
        }
        return containsletter;
    }
}