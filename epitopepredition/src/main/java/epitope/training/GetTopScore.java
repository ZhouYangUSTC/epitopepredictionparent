package epitope.training;

import epitope.dataprocessing.GetDataSetIterator;
import epitope.dataprocessing.HLAProperty;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;

import java.io.IOException;
import java.util.*;

class OutOfArayRangeException extends Exception{
    public OutOfArayRangeException(String message){
        super(message);
    }
}
public class GetTopScore {
    double threshold ;
    List<HLAProperty> list;
    double[] prediction ;
    int k ;

    public GetTopScore(double threshold)
    {
        this.threshold = threshold;
    }

    public GetTopScore(GetDataSetIterator dataSet,double[] output,int k){

        this.list = dataSet.dataList;
        this.prediction = output;
        this.k = k;

    }



    public static Integer[] getTopXIndex(double[] output,int k) throws OutOfArayRangeException{

        if(output.length<= k){
            throw new OutOfArayRangeException("K值超出预测输出长度范围");
        }
        Integer[] index = new Integer[k];
        HashMap<Double,Integer> map = new HashMap();
        for (int i = 0; i < output.length; i++) {
            try{
                map.put(output[i], i); // 将值和下标存入Map
            }catch (Exception ex){
                continue;
            }

        }

        // 排列
        List list = new ArrayList();
        Arrays.sort(output); // 升序排列
        for (int i = 0; i < output.length; i++) {
            list.add(output[i]);
        }
        Collections.reverse(list);



        // 查找原始下标
        for (int i = 0; i < k; i++) {
            index[i] = map.get((Double)list.get(i));
        }
        return index;
    }
    /*
    * 根据获取到的下标，在list中找到相应序列
    * */
    public static void getTopX(GetDataSetIterator dataSetIterator,Integer[] index)throws OutOfArayRangeException{
        List<HLAProperty> list = dataSetIterator.dataList;
        for (int i = 0; i < index.length; i++) {

        }
    }
    public static DataSet getTopXDataSet(GetDataSetIterator getDataSetIterator, INDArray output, int x)
    {
        List<HLAProperty> list = new ArrayList();
        for (int i = 0; i < output.length(); i++) {
            if(output.getDouble(i)>= x){
                list.add(getDataSetIterator.dataList.get(i));
            }
        }
        GetDataSetIterator dataSet = new GetDataSetIterator();
        return dataSet.getAllDataSet(list);
    }

    public static DataSet highScoreInPrediction(GetDataSetIterator test, INDArray output,double threshold)
    {
        List<HLAProperty> list = new ArrayList();
        for (int i = 0; i < output.length(); i++) {
            if(output.getDouble(i)>= threshold && Double.valueOf(test.getLabels().get(i)) >= threshold){
                list.add(test.dataList.get(i));
            }
        }
        GetDataSetIterator dataSet = new GetDataSetIterator();
        return dataSet.getAllDataSet(list);
    }

    public static void main(String[] agrs){
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(100,"SQLT");
        try{
            testingdataIterator.readDataFromFile("G:\\表位预测\\数据\\9000\\test1.txt");


        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }
        double[] a = {1.0,1,1,1,1};
        GetTopScore getTopScore = new GetTopScore(testingdataIterator,a);
        for (int i = 0; i < getTopScore.prediction.length; i++) {

            System.out.println(getTopScore.prediction[i]+"  "+testingdataIterator.dataList.get(i).getNum());
        }
    }
}
