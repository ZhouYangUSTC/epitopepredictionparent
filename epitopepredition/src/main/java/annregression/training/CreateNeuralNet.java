package annregression.training;




import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;

import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;

import org.nd4j.linalg.activations.Activation;

import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class CreateNeuralNet
{
    static int firstLayerInput;   //输入层输入
    static int outLayerOutput;    //输出层输出
    static int hiddenLayer1;       //隐藏层数量
    static int hiddenLayer2;



    /*
    * 初始化参数
    * */
   public  CreateNeuralNet(int Input,int hiddenLayer1,int hiddenLayer2,int outPut){
       this.firstLayerInput = Input;
       this.hiddenLayer1 = hiddenLayer1;
       this.hiddenLayer2 = hiddenLayer2;
       this.outLayerOutput = outPut;

   }

    public  CreateNeuralNet(int Input,int hiddenLayer1,int outPut){
        this.firstLayerInput = Input;
        this.hiddenLayer1 = hiddenLayer1;
        this.hiddenLayer2 = hiddenLayer2;
        this.outLayerOutput = outPut;

    }
   /*
   * 功能：返回一个创建好，初始化的神经网络
   * 参数：1、一个再现重复性的参数。
   *      2、学习速率
   * */
    public  MultiLayerNetwork getSingleLayerNetModel(double rate)
    {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(123) //include a random seed for reproducibility
            .weightInit(WeightInit.RELU)
            .updater(new Nesterovs(rate, 0.98))
            .l2(0.0001) // regularize learning model
            .list()
            .layer(0, new DenseLayer.Builder() //create the first input layer.
                    .nIn(firstLayerInput)
                    .nOut(hiddenLayer1)
                    .activation(Activation.RELU)
                    .build())
            .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.MSE) //create hidden layer
                    .activation(Activation.IDENTITY)
                    .nIn(hiddenLayer1)
                    .nOut(outLayerOutput)
                    .build())
            .pretrain(false).backprop(true) //use backpropagation to adjust weights
            .build();


      MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        return model;
    }

    public  MultiLayerNetwork getMultiLayerNetModel(double rate) {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123) //include a random seed for reproducibility
                .weightInit(WeightInit.RELU)
                .updater(new Nesterovs(rate, 0.98))
                .l2(0.0001) // regularize learning model
                .list()
                .layer(0, new DenseLayer.Builder() //create the first input layer.
                        .nIn(firstLayerInput)
                        .nOut(hiddenLayer1)
                        .activation(Activation.RELU)
                        .build())
                .layer(1, new DenseLayer.Builder() //create the first input layer.
                        .nIn(hiddenLayer1)
                        .nOut(hiddenLayer2)
                        .activation(Activation.RELU)
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE) //create hidden layer
                        .activation(Activation.IDENTITY)
                        .nIn(hiddenLayer2)
                        .nOut(outLayerOutput)
                        .build())
                .pretrain(false).backprop(true) //use backpropagation to adjust weights
                .build();


        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        return model;
    }
}
