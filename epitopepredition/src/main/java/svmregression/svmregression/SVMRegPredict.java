package svmregression.svmregression;

import svmclassification.svmpredict.svm_predict;
import svmclassification.svmpredict.svm_train;

import java.io.IOException;

public class SVMRegPredict {
    public static void main(String[] agrs)throws IOException {
        String[] trainArgs = {"-s","3","-t","2","E:\\SVMRegdatas\\data\\svmtraindata.txt","E:\\SVMRegdatas\\model\\svm_test.model"};//directory of training file

        svm_train.main(trainArgs);
        //参数依次是 test_file model_file output_file
        String[] testArgs = {"E:\\SVMRegdatas\\data\\svmtestdata.txt", "E:\\SVMRegdatas\\model\\svm_test.model", "E:\\SVMRegdatas\\output\\prediction1.txt"};
        svm_predict.main(testArgs);
    }
}
