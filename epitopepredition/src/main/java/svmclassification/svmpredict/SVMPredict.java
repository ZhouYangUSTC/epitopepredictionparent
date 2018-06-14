package svmclassification.svmpredict;

import java.io.IOException;
public class SVMPredict {
    public static void main(String[] args) throws IOException {


        //参数依次是 train_set_file  model_file
        String[] trainArgs = {"E:\\SVMdatas\\data\\svmtraindata1.txt","E:\\SVMdatas\\model\\svm_test1.model"};//directory of training file

        svm_train.main(trainArgs);
        //参数依次是 test_file model_file output_file
        String[] testArgs = {"E:\\SVMdatas\\data\\svmtestdata1.txt", "E:\\SVMdatas\\model\\svm_test1.model", "E:\\SVMdatas\\prediction\\prediction1.txt"};
        svm_predict.main(testArgs);

    }
}
