package epitope.dataprocessing;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GetDataSetIterator implements DataSetIterator {

    public List<HLAProperty> dataList;
    //next函数取出的数据
    private int batchSize;
    //
    static int flag = 0;
    double maxNum = 0;
    String trainTypes = "";

public GetDataSetIterator(int batchSize,String trainTypes){
    this.batchSize = batchSize;
    this.trainTypes = trainTypes;
}
public GetDataSetIterator(int batchSize){
    this.batchSize = batchSize;
}





    /*
    * 从文件中读取数据
    *
    * 输入：文件路径
    * 输出：属性类的list
    * */
    public List<HLAProperty> readDataFromFile(String fileName)
            throws IOException
    {
        dataList = new ArrayList<>();


        FileInputStream fis = new FileInputStream(fileName);

        BufferedReader in = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
        String line = in.readLine();
        while(line != null)
        {
            String[] arry = line.split("\t");

            //找到IC50的最大值，将其记录下来
            double IC50 = Double.valueOf(arry[1]);
            if(IC50> maxNum){
                maxNum = IC50;
            }
                HLAProperty hlaProperty = new HLAProperty();

                char[] sequenceChar = arry[2].toCharArray();



                double[] vector = PropertyToVector.sequenceToVector(sequenceChar);
                hlaProperty.setSequence(arry[2]);
                hlaProperty.setVector(vector);
                hlaProperty.setLable(IC50);
                //dataList.add(hlaProperty);
                hlaProperty.setNum(Integer.valueOf(arry[0]));
                hlaProperty.setAttribute_1(Double.valueOf(arry[3]));
                hlaProperty.setAttribute_2(Double.valueOf(arry[4]));
                hlaProperty.setAttribute_3(Double.valueOf(arry[5]));
                hlaProperty.setAttribute_4(Double.valueOf(arry[6]));
                hlaProperty.setAttribute_5(Double.valueOf(arry[7]));
                hlaProperty.setAttribute_6(Double.valueOf(arry[8]));
                hlaProperty.setAttribute_7(Double.valueOf(arry[9]));
                hlaProperty.setAttribute_8(Double.valueOf(arry[10]));
                hlaProperty.setAttribute_9(Double.valueOf(arry[11]));

            hlaProperty.setAttribute_10(Double.valueOf(arry[12]));
            hlaProperty.setAttribute_11(Double.valueOf(arry[13]));
            hlaProperty.setAttribute_12(Double.valueOf(arry[14]));
            hlaProperty.setAttribute_13(Double.valueOf(arry[15]));
            hlaProperty.setAttribute_14(Double.valueOf(arry[16]));
            hlaProperty.setAttribute_15(Double.valueOf(arry[17]));
            hlaProperty.setAttribute_16(Double.valueOf(arry[18]));
            hlaProperty.setAttribute_17(Double.valueOf(arry[19]));
            hlaProperty.setAttribute_18(Double.valueOf(arry[20]));
                //hlaProperty.setLable(nums[7]);
                dataList.add(hlaProperty);
            line = in.readLine();
            }


        in.close();
        fis.close();

        return dataList;

    }


    public DataSet getDataSet(){
        //INDArray  = Nd4j.create();
        if (batchSize<0||dataList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        int listSize = dataList.size();

        int k = 0;
        if (flag+batchSize <= listSize){
            INDArray inArry = Nd4j.create(batchSize,dataList.get(0).getVector().length);
            INDArray outArry = Nd4j.zeros(batchSize,1);


            for (int i = flag; i < flag+batchSize; i++,k++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    INDArray inArryI = Nd4j.create(sequenceVt);
                    //INDArray rowI = inArry.getRow(i);
                    inArry.putRow(k, inArryI);
                //将label值进行归一化处理
                double lable = 1- Math.log(dataList.get(i).getLable())/Math.log(maxNum);

                    outArry.putScalar(new int[]{k, 0}, lable);



            }
            flag+=batchSize;
            return new DataSet(inArry,outArry);

        }
        else
        {
                INDArray inArry = Nd4j.create(listSize-flag,dataList.get(0).getVector().length);
                INDArray outArry = Nd4j.zeros(listSize-flag,1);
                for (int i = flag; i < listSize; i++,k++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    INDArray inArryI = Nd4j.create(sequenceVt);
                    //INDArray rowI = inArry.getRow(i);
                    inArry.putRow(k, inArryI);
                    //将label值进行归一化处理
                    double lable = 1- Math.log(dataList.get(i).getLable())/Math.log(maxNum);

                    outArry.putScalar(new int[]{k, 0}, lable);



                }
                flag+=batchSize;
                return new DataSet(inArry,outArry);
        }





    }
/*
* 获取所有的数据
*
* 返回包含所有的数据的  DataSet
*
*。
* */
    public DataSet getAllDataSet(){

        if (batchSize<0)
        {
            throw new NoSuchElementException();
        }
        INDArray inArry = null;
        INDArray outArry = null;
        int listSize = dataList.size();


            //用序列训练

            if(trainTypes.equals("SQ")){
                inArry = Nd4j.create(listSize, dataList.get(0).getVector().length);
                outArry = Nd4j.zeros(listSize, 1);
                for (int i = 0; i < listSize; i++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    INDArray inArryI = Nd4j.create(sequenceVt);
                    inArry.putRow(i, inArryI);
                    //将label值进行归一化处理
                    double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                    outArry.putScalar(new int[]{i, 0}, lable);
                }

            }

            //序列加亲疏水性
            else if(trainTypes.equals("SQT")) {
                inArry = Nd4j.create(listSize, dataList.get(0).getVector().length + 9);
                outArry = Nd4j.zeros(listSize, 1);
                for (int i = 0; i < listSize; i++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    double[] arrtribute = new double[9];
                    HLAProperty hlaProperty = dataList.get(i);
                    arrtribute[0] = hlaProperty.getAttribute_10();
                    arrtribute[1] = hlaProperty.getAttribute_11();
                    arrtribute[2] = hlaProperty.getAttribute_12();
                    arrtribute[3] = hlaProperty.getAttribute_13();
                    arrtribute[4] = hlaProperty.getAttribute_14();
                    arrtribute[5] = hlaProperty.getAttribute_15();
                    arrtribute[6] = hlaProperty.getAttribute_16();
                    arrtribute[7] = hlaProperty.getAttribute_17();
                    arrtribute[8] = hlaProperty.getAttribute_18();

                    INDArray arry = Nd4j.create(arrtribute);
                    INDArray seq = Nd4j.create(sequenceVt);
                    INDArray inArryI = Nd4j.hstack(seq, arry);

                    inArry.putRow(i, inArryI);
                    //将label值进行归一化处理
                    double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                    outArry.putScalar(new int[]{i, 0}, lable);
                }
            }
            //序列加属性
            else if(trainTypes.equals("SQL")){
                inArry = Nd4j.create(listSize, dataList.get(0).getVector().length + 9);
                outArry = Nd4j.zeros(listSize, 1);
                for (int i = 0; i < listSize; i++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    double[] arrtribute = new double[9];
                    HLAProperty hlaProperty = dataList.get(i);
                    arrtribute[0] = hlaProperty.getAttribute_1();
                    arrtribute[1] = hlaProperty.getAttribute_2();
                    arrtribute[2] = hlaProperty.getAttribute_3();
                    arrtribute[3] = hlaProperty.getAttribute_4();
                    arrtribute[4] = hlaProperty.getAttribute_5();
                    arrtribute[5] = hlaProperty.getAttribute_6();
                    arrtribute[6] = hlaProperty.getAttribute_7();
                    arrtribute[7] = hlaProperty.getAttribute_8();
                    arrtribute[8] = hlaProperty.getAttribute_9();

                    INDArray arry = Nd4j.create(arrtribute);
                    INDArray seq = Nd4j.create(sequenceVt);
                    INDArray inArryI = Nd4j.hstack(seq,arry);

                    inArry.putRow(i, inArryI);
                    //将label值进行归一化处理
                    double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);
                    //double lable = dataList.get(i).getLable();
                    outArry.putScalar(new int[]{i, 0}, lable);
                }

            }
            else if(trainTypes.equals("SQLT")) {
                inArry = Nd4j.create(listSize, 198);
                outArry = Nd4j.zeros(listSize, 1);
                for (int i = 0; i < listSize; i++) {

                    double[] sequenceVt = dataList.get(i).getVector();
                    double[] arrtribute = new double[18];
                    HLAProperty hlaProperty = dataList.get(i);
                    arrtribute[0] = hlaProperty.getAttribute_1();
                    arrtribute[1] = hlaProperty.getAttribute_2();
                    arrtribute[2] = hlaProperty.getAttribute_3();
                    arrtribute[3] = hlaProperty.getAttribute_4();
                    arrtribute[4] = hlaProperty.getAttribute_5();
                    arrtribute[5] = hlaProperty.getAttribute_6();
                    arrtribute[6] = hlaProperty.getAttribute_7();
                    arrtribute[7] = hlaProperty.getAttribute_8();
                    arrtribute[8] = hlaProperty.getAttribute_9();

                    arrtribute[9] = hlaProperty.getAttribute_10();
                    arrtribute[10] = hlaProperty.getAttribute_11();
                    arrtribute[11] = hlaProperty.getAttribute_12();
                    arrtribute[12] = hlaProperty.getAttribute_13();
                    arrtribute[13] = hlaProperty.getAttribute_14();
                    arrtribute[14] = hlaProperty.getAttribute_15();
                    arrtribute[15] = hlaProperty.getAttribute_16();
                    arrtribute[16] = hlaProperty.getAttribute_17();
                    arrtribute[17] = hlaProperty.getAttribute_18();

                    INDArray arry = Nd4j.create(arrtribute);
                    INDArray seq = Nd4j.create(sequenceVt);
                    INDArray inArryI = Nd4j.hstack(seq, arry);

                    inArry.putRow(i, inArryI);
                    //将label值进行归一化处理
                    double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                    outArry.putScalar(new int[]{i, 0}, lable);
                }
            }
            else{

                return null;
            }



        return new DataSet(inArry,outArry);

    }


/*
*       获取带有参数list的数据集
* */
    public  DataSet getAllDataSet(List<HLAProperty> dataList){

        if (batchSize<0)
        {
            throw new NoSuchElementException();
        }
        INDArray inArry = null;
        INDArray outArry = null;
        int listSize = dataList.size();


        //用序列训练

        if(trainTypes.equals("seq")){
            inArry = Nd4j.create(listSize, 180);
            outArry = Nd4j.zeros(listSize, 1);
            for (int i = 0; i < listSize; i++) {

                double[] sequenceVt = dataList.get(i).getVector();
                INDArray inArryI = Nd4j.create(sequenceVt);
                inArry.putRow(i, inArryI);
                //将label值进行归一化处理
                //double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);
                double lable = Math.log(dataList.get(i).getLable());
                outArry.putScalar(new int[]{i, 0}, lable);
            }

        }
        //用属性训练
        else if(trainTypes.equals("lenth")){
            inArry = Nd4j.create(listSize, 9);
            outArry = Nd4j.zeros(listSize, 1);
            for (int i = 0; i < listSize; i++) {

                double[] arrtribute = new double[9];
                HLAProperty hlaProperty = dataList.get(i);
                arrtribute[0] = hlaProperty.getAttribute_1();
                arrtribute[1] = hlaProperty.getAttribute_2();
                arrtribute[2] = hlaProperty.getAttribute_3();
                arrtribute[3] = hlaProperty.getAttribute_4();
                arrtribute[4] = hlaProperty.getAttribute_5();
                arrtribute[5] = hlaProperty.getAttribute_6();
                arrtribute[6] = hlaProperty.getAttribute_7();
                arrtribute[7] = hlaProperty.getAttribute_8();
                arrtribute[8] = hlaProperty.getAttribute_9();

                INDArray inArryI = Nd4j.create(arrtribute);
                inArry.putRow(i, inArryI);
                //将label值进行归一化处理
                double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                outArry.putScalar(new int[]{i, 0}, lable);
            }

        }
        //序列加亲疏水性
        else if(trainTypes.equals("sqt")) {
            inArry = Nd4j.create(listSize, 189);
            outArry = Nd4j.zeros(listSize, 1);
            for (int i = 0; i < listSize; i++) {

                double[] sequenceVt = dataList.get(i).getVector();
                double[] arrtribute = new double[9];
                HLAProperty hlaProperty = dataList.get(i);
                arrtribute[0] = hlaProperty.getAttribute_10();
                arrtribute[1] = hlaProperty.getAttribute_11();
                arrtribute[2] = hlaProperty.getAttribute_12();
                arrtribute[3] = hlaProperty.getAttribute_13();
                arrtribute[4] = hlaProperty.getAttribute_14();
                arrtribute[5] = hlaProperty.getAttribute_15();
                arrtribute[6] = hlaProperty.getAttribute_16();
                arrtribute[7] = hlaProperty.getAttribute_17();
                arrtribute[8] = hlaProperty.getAttribute_18();

                INDArray arry = Nd4j.create(arrtribute);
                INDArray seq = Nd4j.create(sequenceVt);
                INDArray inArryI = Nd4j.hstack(seq, arry);

                inArry.putRow(i, inArryI);
                //将label值进行归一化处理
                double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                outArry.putScalar(new int[]{i, 0}, lable);
            }
        }
        //序列加属性
        else if(trainTypes.equals("sql")){
            inArry = Nd4j.create(listSize, 189);
            outArry = Nd4j.zeros(listSize, 1);
            for (int i = 0; i < listSize; i++) {

                double[] sequenceVt = dataList.get(i).getVector();
                double[] arrtribute = new double[9];
                HLAProperty hlaProperty = dataList.get(i);
                arrtribute[0] = hlaProperty.getAttribute_1();
                arrtribute[1] = hlaProperty.getAttribute_2();
                arrtribute[2] = hlaProperty.getAttribute_3();
                arrtribute[3] = hlaProperty.getAttribute_4();
                arrtribute[4] = hlaProperty.getAttribute_5();
                arrtribute[5] = hlaProperty.getAttribute_6();
                arrtribute[6] = hlaProperty.getAttribute_7();
                arrtribute[7] = hlaProperty.getAttribute_8();
                arrtribute[8] = hlaProperty.getAttribute_9();

                INDArray arry = Nd4j.create(arrtribute);
                INDArray seq = Nd4j.create(sequenceVt);
                INDArray inArryI = Nd4j.hstack(seq,arry);

                inArry.putRow(i, inArryI);
                //将label值进行归一化处理
                //double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);
                double lable = dataList.get(i).getLable();
                outArry.putScalar(new int[]{i, 0}, lable);
            }

        }
        else if(trainTypes.equals("all")) {
            inArry = Nd4j.create(listSize, 198);
            outArry = Nd4j.zeros(listSize, 1);
            for (int i = 0; i < listSize; i++) {

                double[] sequenceVt = dataList.get(i).getVector();
                double[] arrtribute = new double[18];
                HLAProperty hlaProperty = dataList.get(i);
                arrtribute[0] = hlaProperty.getAttribute_1();
                arrtribute[1] = hlaProperty.getAttribute_2();
                arrtribute[2] = hlaProperty.getAttribute_3();
                arrtribute[3] = hlaProperty.getAttribute_4();
                arrtribute[4] = hlaProperty.getAttribute_5();
                arrtribute[5] = hlaProperty.getAttribute_6();
                arrtribute[6] = hlaProperty.getAttribute_7();
                arrtribute[7] = hlaProperty.getAttribute_8();
                arrtribute[8] = hlaProperty.getAttribute_9();

                arrtribute[9] = hlaProperty.getAttribute_10();
                arrtribute[10] = hlaProperty.getAttribute_11();
                arrtribute[11] = hlaProperty.getAttribute_12();
                arrtribute[12] = hlaProperty.getAttribute_13();
                arrtribute[13] = hlaProperty.getAttribute_14();
                arrtribute[14] = hlaProperty.getAttribute_15();
                arrtribute[15] = hlaProperty.getAttribute_16();
                arrtribute[16] = hlaProperty.getAttribute_17();
                arrtribute[17] = hlaProperty.getAttribute_18();

                INDArray arry = Nd4j.create(arrtribute);
                INDArray seq = Nd4j.create(sequenceVt);
                INDArray inArryI = Nd4j.hstack(seq, arry);

                inArry.putRow(i, inArryI);
                //将label值进行归一化处理
                double lable = 1 - Math.log(dataList.get(i).getLable()) / Math.log(maxNum);

                outArry.putScalar(new int[]{i, 0}, lable);
            }
        }
        else{

            return null;
        }



        return new DataSet(inArry,outArry);

    }






    @Override
    public boolean hasNext() {

        return flag < dataList.size();
    }
    @Override
    public DataSet next(int i) {
        return null;
    }
    @Override
    public DataSet next() {

        return getDataSet();
    }
    @Override
    public void reset() {

        flag = 0;
    }






    @Override
    public int totalExamples() {
        return 0;
    }

    @Override
    public int inputColumns() {
        return 0;
    }

    @Override
    public int totalOutcomes() {
        return 0;
    }

    @Override
    public boolean resetSupported() {
        return false;
    }

    @Override
    public boolean asyncSupported() {
        return false;
    }



    @Override
    public int batch() {
        return batchSize;
    }

    @Override
    public int cursor() {
        return 0;
    }

    @Override
    public int numExamples() {
        return 0;
    }

    @Override
    public void setPreProcessor(DataSetPreProcessor dataSetPreProcessor) {

    }

    @Override
    public DataSetPreProcessor getPreProcessor() {
        return null;
    }

    @Override
    public List<String> getLabels() {
        return null;
    }


}
