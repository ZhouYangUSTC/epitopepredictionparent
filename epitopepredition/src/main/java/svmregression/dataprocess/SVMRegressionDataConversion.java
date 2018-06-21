package svmregression.dataprocess;

import annregression.dataprocessing.HLAProperty;
import annregression.dataprocessing.PropertyToVector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SVMRegressionDataConversion {
    public List<HLAProperty> dataList;
    double maxNum = 0;
    double k;
    String fileTypes = "";

    public SVMRegressionDataConversion(String fileTypes, double k){

        this.fileTypes = fileTypes;
        this.k = k;
    }
    public void clearParam(){
        this.dataList.clear();
        this.maxNum = 0;
}

    public static void main(String[] args){
        SVMRegressionDataConversion svmDataCon = new SVMRegressionDataConversion("SQLT",0.9);
        try{
            svmDataCon.readDataFromFile("E:\\抗原预测\\表位预测\\数据\\9000加属性\\datacopy\\train.txt");
            svmDataCon.svmDataFile("E:\\svmtraindata.txt");
            svmDataCon.clearParam();
            svmDataCon.readDataFromFile("E:\\抗原预测\\表位预测\\数据\\9000加属性\\datacopy\\test.txt");
            svmDataCon.svmDataFile("E:\\svmtestdata.txt");
        }catch(IOException ex){
            ex.getStackTrace();
        }
    }


    /*
    * 从文件中读取数据
    *
    * 输入：文件路径
    * 输出：属性类的list
    * */
    public List<HLAProperty> readDataFromFile(String fileName)
            throws IOException {
        if (fileTypes.equals("SQ")) {
            dataList = new ArrayList<>();


            FileInputStream fis = new FileInputStream(fileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = in.readLine();
            while (line != null) {

                String[] arry = line.split("\t");


                //找到IC50的最大值，将其记录下来
                double IC50 = Double.valueOf(arry[1]);
                if (IC50 > maxNum) {
                    maxNum = IC50;
                }
                HLAProperty hlaProperty = new HLAProperty();

                char[] sequenceChar = arry[2].toCharArray();


                double[] vector = PropertyToVector.sequenceToVector(sequenceChar);
                hlaProperty.setSequence(arry[2]);
                hlaProperty.setVector(vector);
                hlaProperty.setLable(IC50);
                dataList.add(hlaProperty);
                line = in.readLine();
            }
            in.close();
            fis.close();
        } else if (fileTypes.equals("SQL")) {
            dataList = new ArrayList<>();


            FileInputStream fis = new FileInputStream(fileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = in.readLine();

            while (line != null) {

                String[] arry = line.split("\t");


                //找到IC50的最大值，将其记录下来
                double IC50 = Double.valueOf(arry[1]);
                if (IC50 > maxNum) {
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
                dataList.add(hlaProperty);
                line = in.readLine();
            }
            in.close();
            fis.close();
        } else if (fileTypes.equals("SQLT")) {

            dataList = new ArrayList<HLAProperty>();
            FileInputStream fis = new FileInputStream(fileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String line = in.readLine();

            while (line != null) {

                String[] arry = line.split("\t");


                //找到IC50的最大值，将其记录下来
                double IC50 = Double.valueOf(arry[1]);
                if (IC50 > maxNum) {
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
        }


        return dataList;
    }
    public void svmDataFile(String fileName) throws IOException{
        StringBuffer svmDataString;
        List<String> svmDataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            svmDataString = new StringBuffer();
            double lable = (1-Math.log(dataList.get(i).getLable())/Math.log(maxNum));
            svmDataString.append(lable+" ");
            int index = dataList.get(i).getVector().length;
            for (int j = 0; j < index; j++) {
                if(dataList.get(i).getVector()[j] ==1){
                    svmDataString.append(j+1+":"+dataList.get(i).getVector()[j]+" ");
                }
            }
            if(fileTypes.equals("SQL")){
                svmDataString.append(index+1+":"+dataList.get(i).getAttribute_1()+" ");
                svmDataString.append(index+2+":"+dataList.get(i).getAttribute_2()+" ");
                svmDataString.append(index+3+":"+dataList.get(i).getAttribute_3()+" ");
                svmDataString.append(index+4+":"+dataList.get(i).getAttribute_4()+" ");
                svmDataString.append(index+5+":"+dataList.get(i).getAttribute_5()+" ");
                svmDataString.append(index+6+":"+dataList.get(i).getAttribute_6()+" ");
                svmDataString.append(index+7+":"+dataList.get(i).getAttribute_7()+" ");
                svmDataString.append(index+8+":"+dataList.get(i).getAttribute_8()+" ");
                svmDataString.append(index+9+":"+dataList.get(i).getAttribute_9());
            }
            else if(fileTypes.equals("SQLT")){
                svmDataString.append(index+1+":"+dataList.get(i).getAttribute_1()+" ");
                svmDataString.append(index+2+":"+dataList.get(i).getAttribute_2()+" ");
                svmDataString.append(index+3+":"+dataList.get(i).getAttribute_3()+" ");
                svmDataString.append(index+4+":"+dataList.get(i).getAttribute_4()+" ");
                svmDataString.append(index+5+":"+dataList.get(i).getAttribute_5()+" ");
                svmDataString.append(index+6+":"+dataList.get(i).getAttribute_6()+" ");
                svmDataString.append(index+7+":"+dataList.get(i).getAttribute_7()+" ");
                svmDataString.append(index+8+":"+dataList.get(i).getAttribute_8()+" ");
                svmDataString.append(index+9+":"+dataList.get(i).getAttribute_9()+" ");
                svmDataString.append(index+10+":"+dataList.get(i).getAttribute_10()+" ");
                svmDataString.append(index+11+":"+dataList.get(i).getAttribute_11()+" ");
                svmDataString.append(index+12+":"+dataList.get(i).getAttribute_12()+" ");
                svmDataString.append(index+13+":"+dataList.get(i).getAttribute_13()+" ");
                svmDataString.append(index+14+":"+dataList.get(i).getAttribute_14()+" ");
                svmDataString.append(index+15+":"+dataList.get(i).getAttribute_15()+" ");
                svmDataString.append(index+16+":"+dataList.get(i).getAttribute_16()+" ");
                svmDataString.append(index+17+":"+dataList.get(i).getAttribute_17()+" ");
                svmDataString.append(index+18+":"+dataList.get(i).getAttribute_18());

            }

            svmDataList.add(svmDataString.toString());
        }
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);

        //先写到本地，然后再用svmdataiterator读取。
        for (int i = 0; i < svmDataList.size(); i++) {
            if (i>svmDataList.size()-2){
                fw.write(svmDataList.get(i));

            }else {
                fw.write(svmDataList.get(i));
                fw.write("\r\n");
            }

        }
        fw.close();


    }

}
