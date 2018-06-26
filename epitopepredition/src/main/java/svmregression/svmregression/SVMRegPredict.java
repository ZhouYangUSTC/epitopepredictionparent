package svmregression.svmregression;

import svmclassification.svmpredict.svm_predict;
import svmclassification.svmpredict.svm_train;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SVMRegPredict {
    public static List<Double> svmReg(String[] inAgrs,String[] preAgrs)throws IOException{
        List<Double> svmRegPreList = new ArrayList<>();
        svm_train.main(inAgrs);
        svm_predict.main(preAgrs,svmRegPreList);
        return  svmRegPreList;
    }
    public static void main(String[] agrs)throws IOException {
        String[] trainArgs = {"-s","3","-t","3","E:\\SVMRegdatas\\data\\svmtraindata.txt","E:\\SVMRegdatas\\model\\svm_sigmoidkernel.model"};//directory of training file

        svm_train.main(trainArgs);
        //参数依次是 test_file model_file output_file
        String[] testArgs = {"E:\\SVMRegdatas\\data\\svmtestdata.txt", "E:\\SVMRegdatas\\model\\svm_sigmoidkernel.model", "E:\\SVMRegdatas\\output\\sigmoidkernel.txt"};
        svm_predict.main(testArgs);
        /*String[] trainArgs = {"-s","3","-t","2","E:\\SVMRegdatas\\data\\svmtraindata.txt","E:\\SVMRegdatas\\model\\svm_test.model"};
        String[] testArgs = {"E:\\SVMRegdatas\\data\\svmtestdata.txt", "E:\\SVMRegdatas\\model\\svm_test.model"};
        List<Double> list = svmReg(trainArgs,testArgs);
*/


    }
}
