package svm.dataprocessing;

import ann.dataprocessing.HLAProperty;
import ann.dataprocessing.PropertyToVector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataConversion {
    public List<HLAProperty> dataList;
    double maxNum = 0;
    double k;
    String fileTypes = "";

    public DataConversion(String fileTypes,double k){

        this.fileTypes = fileTypes;
        this.k = k;
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
    public void svm_data_file(String fileName) throws IOException{
        StringBuffer svmDataString;
        List<String> svmDataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            svmDataString = new StringBuffer();
            int lable = (1-Math.log(dataList.get(i).getLable())/Math.log(maxNum)) >= k ?1:-1;
            svmDataString.append(lable+" ");
            for (int j = 0; j < dataList.get(i).getVector().length; j++) {
                if(dataList.get(i).getVector()[j] ==1){
                    svmDataString.append(j+":"+dataList.get(i).getVector()[j]+" ");
                }
            }

            svmDataList.add(svmDataString.toString());
        }
        File file = new File(fileName);
        //先写到本地，然后再用svmdataiterator读取。
        for (int i = 0; i < svmDataList.size(); i++) {

        }
    }

}


