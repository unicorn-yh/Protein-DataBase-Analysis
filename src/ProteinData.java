
abstract public class ProteinData{
    String index,protein,accession,sequence,annotation,interpro,org,type;
    double pr_vector;
    String data(){
        return "";
    }
    public void setIndex(String index){
        this.index=index;
    }
    public void setProtein(String protein){
        this.protein=protein;
    }
    public void setAccession(String accession){
        this.accession=accession;
    }
    public void setSequence(String sequence){
        this.sequence=sequence;
    }
    public void setAnnotation(String annotation){
        this.annotation=annotation;
    }
    public void setInterpro(String interpro){
        this.interpro=interpro;
    }
    public void setOrg(String org){
        this.org=org;
    }
    public void setType(String type) {
    	this.type=type;
    }
    
    public String getIndex(){
        return index;
    }
    public String getProtein(){
        return protein;
    }
    public String getAccession(){
        return accession;
    }
    public String getSequence(){
        return sequence;
    }
    public String getAnnotation(){
        return annotation;
    }
    public String getInterpro(){
        return interpro;
    }
    public String getOrg(){
        return org;
    }
    public String getType(){
    	return type;
    }
    int seqLength;
    public int getSequenceLength(){
        seqLength=getSequence().length();
        return seqLength;
    }
    public void setPR_Vector(double pr_vector) {
    	this.pr_vector=pr_vector;
    }
    public double getPR_Vector() {
    	return pr_vector;
    }

}



