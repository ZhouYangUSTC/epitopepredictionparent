package combine.regression;

public class RegModel {


    private  String seq;
    private  int index;



    private  double lable;
    private  double annRegPre;
    private  double svmRegPre;
    private  double combinePre;

    public double getLable() {
        return lable;
    }

    public void setLable(double lable) {
        this.lable = lable;
    }
    public String getSeq() {
        return seq;
    }

    public  void setSeq(String seq) {
        this.seq = seq;
    }

    public  int getIndex() {
        return index;
    }

    public  void setIndex(int index) {

        this.index = index;
    }

    public  double getAnnRegPre() {
        return annRegPre;
    }

    public  void setAnnRegPre(double annRegPre) {

        this.annRegPre = annRegPre;
    }

    public  double getSvmRegPre() {
        return svmRegPre;
    }

    public  void setSvmRegPre(double svmRegPre) {

        this.svmRegPre = svmRegPre;
    }

    public  double getCombinePre() {
        return combinePre;
    }

    public  void setCombinePre(double combinePre) {

        this.combinePre = combinePre;
    }

}
