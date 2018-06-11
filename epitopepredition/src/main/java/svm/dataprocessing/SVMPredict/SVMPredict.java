package svm.dataprocessing.SVMPredict;

import java.io.IOException;
public class SVMPredict {
    public static void main(String[] args) throws IOException {
        //参数依次是 train_set_file  model_file
        String[] trainArgs = {"E:\\svmtraindata1.txt","E:\\svm_test1.model"};//directory of training file

        svm_train.main(trainArgs);
        //参数依次是 test_file model_file output_file
        String[] testArgs = {"E:\\svmtestdata1.txt", "E:\\svm_test1.model", "E:\\prediction1.txt"};
        svm_predict.main(testArgs);

    }
}
