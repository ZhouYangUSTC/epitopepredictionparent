package ann.training;

import ann.dataprocessing.GetDataSetIterator;
import org.deeplearning4j.eval.RegressionEvaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainAndTest {
    public double threshold;
    public Double errRate;
    public Double missRate;
    public String regEvaluationStats;

    public TrainAndTest(double threshold){
        this.threshold = threshold;
    }
    public TrainAndTest(){

    }

    public static void kFoldTrain(MultiLayerNetwork net, GetDataSetIterator dataSet, int k, int numEpochs){
        DataSet allDataSet = dataSet.getAllDataSet();
        allDataSet.shuffle(123);
        DataSet trainSet = null;
        DataSet testSet = null;
        KFoldIterator kfIterator= new KFoldIterator(k,allDataSet);
        RegressionEvaluation regEvaluation = new RegressionEvaluation();

        for (int i = 0; i < numEpochs; i++) {


            while(kfIterator.hasNext()){
                trainSet = kfIterator.next();
                net.fit(trainSet);
                testSet = kfIterator.testFold();
                INDArray output = net.output(testSet.getFeatureMatrix(),false);
                regEvaluation.eval(testSet.getLabels(), output);
                System.out.println(regEvaluation.stats());
            }
            kfIterator.reset();

        }
        System.out.println();
        System.out.println();
        System.out.println(regEvaluation.stats());
    }


    public static void train(MultiLayerNetwork net,GetDataSetIterator trainSet,int epochs) {

        for (int i = 0; i < epochs; i++)
        {
            DataSet dataSet = null;
            while (trainSet.hasNext()) {
                dataSet = trainSet.next();
                net.fit(dataSet);

            }
            trainSet.reset();


            System.out.println();
        }

    }

    public  List<double[]> test(MultiLayerNetwork net, GetDataSetIterator testSet){

        RegressionEvaluation regEvaluation = new RegressionEvaluation();

        DataSet test = testSet.getAllDataSet();


        INDArray output = net.output(test.getFeatureMatrix());
        regEvaluation.eval(test.getLabels(), output);
        //System.out.println(output.reshape(1,output.rows()));

        System.out.println();
        System.out.println();

        //System.out.println(test.getLabels().reshape(1,test.getLabels().rows()));
        erorAndMiss(test.getLabels(),output,threshold);
        String []status = regEvaluation.stats().split("\n");

        regEvaluationStats = status[1].trim().substring(40,51);
        System.out.println(regEvaluationStats);

        List<double[]> list = new ArrayList<>();
        list.add(output.toDoubleVector());
        list.add(test.getLabels().toDoubleVector());
        return list;
        
    }
/*
* 将预测结果保存到文件
* */
    public static void writeToFile(GetDataSetIterator dataSet,List<double[]> list,String fileName)throws IOException {

        File file  = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter out = new FileWriter(file,true);
        out.write("\toutput\t\t\tlables\r\n");
        for (int i = 0; i < list.get(0).length; i++) {
            out.write(dataSet.dataList.get(i).getSequence()+"\t"+list.get(0)[i]+"\t"+list.get(1)[i]+"\r\n");
        }

        out.close();

    }



    public void erorAndMiss(INDArray input,INDArray output,double threshold){
        double err = 0.0;
        double miss = 0.0;
        double accurate = 0.0;
        int length = input.length();


            for (int i = 0; i < length; i++) {
                if(input.getDouble(i)>= threshold && output.getDouble(i) < threshold)
                {
                    miss++;
                }
                else if(input.getDouble(i)<= threshold && output.getDouble(i) >=threshold)
                {
                    err++;
                }
                else if(input.getDouble(i)>= threshold && output.getDouble(i) >=threshold)
                {
                    accurate++;
                }

            }
            errRate = err/(err+accurate);
            missRate = miss/(miss+accurate);
           System.out.println("Prediction errorRate is:"+errRate+", and missRate is:"+missRate);

    }
}
