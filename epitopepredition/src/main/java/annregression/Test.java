package annregression;


import annregression.dataprocessing.GetDataSetIterator;
import annregression.estimate.DataNode;
import annregression.estimate.PearsonCorrelationScore;
import annregression.estimate.ScatterPlot;
import annregression.training.SaveAndLoadModel;
import annregression.training.TrainAndTest;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Test {
    private static Logger log = LoggerFactory.getLogger(AnnMain.class);

    public static void main(String [] agrs){

        double threshold = 0.8;
        int batchSize = 100; // batch size for each epoch

        List<double[]> list = new ArrayList<>();



        String trainTypes = "SQLT";

        //String testFileName = "G:\\表位预测\\数据\\perl\\555_traing\\train02.txt";
        String testFileName = "G:\\表位预测\\数据\\reorder_v1\\testcopy\\test.txt";

        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{

            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }


        //使用k-折交叉验证训练模型
        TrainAndTest trainAndTest = new TrainAndTest(threshold);

        //保存和加载模型

       String fileName = "6_0_500_0.0015";
        try{

            MultiLayerNetwork net = SaveAndLoadModel.loadModel("G:\\ModelFile\\翻转后\\all\\"+fileName+".zip");


            list =  trainAndTest.test(net,testingdataIterator);
            //将结果输出到文件
            //Test.writeToFile(list,fileName);
        }catch (IOException ex){

        }

        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1));

        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;



    }




}
