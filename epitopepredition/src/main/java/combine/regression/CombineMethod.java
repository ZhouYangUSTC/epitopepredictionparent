package combine.regression;

import annregression.AnnMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static svmregression.svmregression.SVMRegPredict.svmReg;

public class CombineMethod {

    public static AnnMain annMain;
    public static int epoch=500;
    public static double rate=0.0015;
    public static int hiddenLayer1=8;
    public static int hiddenLayer2=6;
    public static void annPre(String trainTypes){


        List<String> filePathList = new ArrayList<>();
                        filePathList.add("E:\\抗原预测\\表位预测\\数据\\9000加属性\\datacopy\\train.txt");
                        filePathList.add("E:\\抗原预测\\表位预测\\数据\\9000加属性\\datacopy\\test.txt");
                        filePathList.add("E:\\combine\\annmodel");


         annMain = new AnnMain(trainTypes);


        if(annMain.trainTypes.equals("SQSinglelayer")){

            annMain.SQSingleLayerModel(epoch,rate,hiddenLayer1,filePathList);

        }
        else if(annMain.trainTypes.equals("SQMultilayer")){

            annMain.SQMultilyLayerModel(epoch,rate,hiddenLayer1,hiddenLayer2,filePathList);

        }
        else if(annMain.trainTypes.equals("SQLSinglelayer")){

            annMain.SQLSingleLayerModel(epoch,rate,hiddenLayer1,filePathList);
        }
        else if(annMain.trainTypes.equals("SQLMultilayer")){

            annMain.SQLMultilyLayerModel(epoch,rate,hiddenLayer1,hiddenLayer2,filePathList);
        }
        else if(annMain.trainTypes.equals("SQLTSinglelayer"))
        {

            annMain.SQLTSingleLayerModel(epoch,rate,hiddenLayer1,filePathList);
        }
        else if(annMain.trainTypes.equals("SQLTMultilayer")){

            annMain.SQLTMultilyLayerModel(epoch,rate,hiddenLayer1,hiddenLayer2,filePathList);
        }

    }
    public static void main(String[] agrs){
        List<Double> svmlist = new ArrayList<>();

        String[] trainArgs = {"-s","3","-t","2","E:\\SVMRegdatas\\data\\svmtraindata.txt","E:\\combine\\svmmodel\\svm_weight.model"};
        String[] testArgs = {"E:\\SVMRegdatas\\data\\svmtestdata.txt", "E:\\combine\\svmmodel\\svm_weight.model"};
        try {
           svmlist = svmReg(trainArgs,testArgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        annPre("SQLTMultilayer");
        double[] annlist = annMain.annRegList.get(0);
        if(annlist.length!=svmlist.size()){
            System.out.println("预测数据长度不一，请检查数据格式");
            System.exit(1);
        }
        RegModel regmodel;
        List<RegModel> regModelList = new ArrayList<>();
        for (int i = 0; i < annlist.length; i++) {
            regmodel = new RegModel();
            regmodel.setIndex(annMain.testingdataIterator.dataList.get(i).getNum());
            regmodel.setSeq(annMain.testingdataIterator.dataList.get(i).getSequence());
            regmodel.setLable(annMain.testingdataIterator.dataList.get(i).getLable());
            regmodel.setAnnRegPre(annlist[i]);
            regmodel.setSvmRegPre(svmlist.get(i));
            regmodel.setCombinePre((annlist[i]+svmlist.get(i)));
            regModelList.add(regmodel);
        }

        try {
            writeToFile(regModelList,"E:\\combine\\predict\\combine_svm_weight.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(List<RegModel> list,String fileName)throws IOException{

        File file  = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter out = new FileWriter(file);
        out.write("index"+"\t"+"seq"+"\tlables"+"\t"+"ann predict"+"\t"+"svm predict"+"\t"+"combine predict"+"\r\n");
        for (int i = 0; i < list.size(); i++) {
            out.write(list.get(i).getIndex()+"\t"+list.get(i).getSeq()+"\t"+list.get(i).getLable()+"\t"+
                        list.get(i).getAnnRegPre()+"\t"+ list.get(i).getSvmRegPre()+"\t"+list.get(i).getCombinePre());
            out.write("\r\n");
        }

        out.close();

    }
}
