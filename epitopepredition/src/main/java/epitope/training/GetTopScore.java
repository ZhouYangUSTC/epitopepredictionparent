package epitope.training;

import epitope.dataprocessing.GetDataSetIterator;
import epitope.dataprocessing.HLAProperty;

import java.io.IOException;
import java.util.*;

class OutOfArayRangeException extends Exception{
    public OutOfArayRangeException(String message){
        super(message);
    }
}

public class GetTopScore {
    double threshold ;
    List<HLAProperty> propertyList;
    double[] prediction ;
    double[] output;
    int k ;

    public GetTopScore(double threshold)
    {
        this.threshold = threshold;
    }

    public GetTopScore(GetDataSetIterator dataSet,double[] output,int k){

        this.propertyList = dataSet.dataList;
        this.prediction = output;
        this.output = output.clone();

        this.k = k;

    }



    public  Integer[] getTopXIndex() throws OutOfArayRangeException{

        if(prediction.length<= k){
            throw new OutOfArayRangeException("K值超出预测输出长度范围");
        }
        Integer[] index = new Integer[k];
        HashMap<Double,Integer> map = new HashMap();
        for (int i = 0; i < prediction.length; i++) {
            try{
                map.put(prediction[i], i); // 将值和下标存入Map
            }catch (Exception ex){
                continue;
            }

        }

        // 排列
        List indexList = new ArrayList();
        Arrays.sort(prediction); // 升序排列
        for (int i = 0; i < prediction.length; i++) {
            indexList.add(prediction[i]);
        }
        Collections.reverse(indexList);



        // 查找原始下标
        for (int i = 0; i < k; i++) {
            index[i] = map.get((Double)indexList.get(i));
        }
        return index;
    }
    /*
    * 根据获取到的下标，在list中找到相应序列
    * */
    public List<String> getTopXSeq(Integer[] index)throws OutOfArayRangeException{

        List<String> seqList = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            String seq = propertyList.get(index[i]).getSequence();
            seqList.add(seq);

        }
        return seqList;
    }
    /*
    * 通过调用getTopXIndex（）和getTopX（）返回前k个序列和预测值
    * */
    public void getTopX ()throws OutOfArayRangeException{
        Integer[] index = getTopXIndex();
        List<String> seqList = getTopXSeq(index);


        for (int i = 0; i < index.length; i++) {
            System.out.println(seqList.get(i)+" "+output[index[i]]);

        }

    }
   /* public static DataSet getTopXDataSet(GetDataSetIterator getDataSetIterator, INDArray output, int x)
    {
        List<HLAProperty> propertyList = new ArrayList();
        for (int i = 0; i < output.length(); i++) {
            if(output.getDouble(i)>= x){
                propertyList.add(getDataSetIterator.dataList.get(i));
            }
        }
        GetDataSetIterator dataSet = new GetDataSetIterator();
        return dataSet.getAllDataSet(propertyList);
    }

    public static DataSet highScoreInPrediction(GetDataSetIterator test, INDArray output,double threshold)
    {
        List<HLAProperty> propertyList = new ArrayList();
        for (int i = 0; i < output.length(); i++) {
            if(output.getDouble(i)>= threshold && Double.valueOf(test.getLabels().get(i)) >= threshold){
                propertyList.add(test.dataList.get(i));
            }
        }
        GetDataSetIterator dataSet = new GetDataSetIterator();
        return dataSet.getAllDataSet(propertyList);
    }*/

    /*public static void main(String[] agrs){
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(100,"SQLT");
        try{
            testingdataIterator.readDataFromFile("E:\\抗原预测\\表位预测\\数据\\9000加属性\\datacopy\\test.txt");


        }catch(IOException ioEx){
            ioEx.getStackTrace();
            System.out.println(ioEx);
        }
        double[] a = {1.0,0.2,0.5,0.9,0.8};
        GetTopScore getTopScore = new GetTopScore(testingdataIterator,a,3);
        try{
            getTopScore.getTopX();
        }catch(OutOfArayRangeException outRang)
        {
            outRang.getStackTrace();
        }

    }*/
}
