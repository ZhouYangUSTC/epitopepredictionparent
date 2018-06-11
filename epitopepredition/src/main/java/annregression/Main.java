package annregression;


import annregression.dataprocessing.GetDataSetIterator;
import annregression.pearson.DataNode;
import annregression.pearson.PearsonCorrelationScore;
import annregression.pearson.ScatterPlot;
import annregression.training.CreateNeuralNet;
import annregression.training.SaveAndLoadModel;
import annregression.training.TrainAndTest;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {


    //private static Logger log = LoggerFactory.getLogger(Main.class);
    public TrainAndTest trainAndTest;
    public Double pearsonScore;
    public void SQSingleLayerModel(int epoch,double rate,int hiddenLayer1,List<String> pathList){
        int batchSize = 50;         // 批次大小
              // 训练次数

        int firstLayerInput = 180;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQ";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        //log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getSingleLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf("0")+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }

    public void SQMultilyLayerModel(int epoch,double rate,int hiddenLayer1,int hiddenLayer2,List<String> pathList){
        int batchSize = 50;         // 批次大小
        // 训练次数

        int firstLayerInput = 180;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQ";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        ///log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,hiddenLayer2,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getMultiLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf(hiddenLayer2)+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }


    public void SQLSingleLayerModel(int epoch,double rate,int hiddenLayer1,List<String> pathList){
        int batchSize = 50;         // 批次大小
        // 训练次数

        int firstLayerInput = 189;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQL";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        //log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getSingleLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf("0")+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }

    public void SQLMultilyLayerModel(int epoch,double rate,int hiddenLayer1,int hiddenLayer2,List<String> pathList){
        int batchSize = 50;         // 批次大小
        // 训练次数

        int firstLayerInput = 189;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQL";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        //log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,hiddenLayer2,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getMultiLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf(hiddenLayer2)+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }
    public void SQLTSingleLayerModel(int epoch,double rate,int hiddenLayer1,List<String> pathList){
        int batchSize = 50;         // 批次大小
        // 训练次数

        int firstLayerInput = 198;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQLT";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        //log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getSingleLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf("0")+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }

    public void SQLTMultilyLayerModel(int epoch,double rate,int hiddenLayer1,int hiddenLayer2,List<String> pathList){
        int batchSize = 50;         // 批次大小
        // 训练次数

        int firstLayerInput = 198;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出

        double threshold = 0.8;

        String trainTypes = "SQLT";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        /*
        * 获取训练集和测试集
        * */
        String traiFfileName = pathList.get(0);
        String testFileName = pathList.get(1);
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        //log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,hiddenLayer2,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getMultiLayerNetModel(rate);

        //log.info("TrainAndTest model....");







        //使用k-折交叉验证训练模型
        trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,epoch);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf(hiddenLayer2)+"_"+String.valueOf(epoch)+"_"+String.valueOf(rate);

        String filePath  = pathList.get(2)+"\\"+fileName+".txt";
        try{
            trainAndTest.writeToFile(testingdataIterator,list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,pathList.get(3)+"\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



        //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = pathList.get(4)+"\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;
    }

    /*public static void main(String [] agrs){


        int batchSize = 50;         // 批次大小
        int numEpochs = 300;       // 训练次数
        double rate = 0.0015;       // 学习速率
        int firstLayerInput = 198;  // 输入层输入
        int outLayerOutput = 1;     // 输出层输出
        int hiddenLayer1 = 8;      // 隐藏层数量
        int hiddenLayer2 = 0;
        double threshold = 0.8;

        String trainTypes = "SQLT";  // 训练内容：序列、侧链长度、两者结合

        List<double[]> list = new ArrayList<>();



        *//*
        * 获取训练集和测试集
        * *//*
        String traiFfileName = "G:\\表位预测\\数据\\reorder_v1\\traincopy\\train.txt";
        String testFileName = "G:\\表位预测\\数据\\reorder_v1\\testcopy\\test.txt";
        GetDataSetIterator trainingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        GetDataSetIterator testingdataIterator = new GetDataSetIterator(batchSize,trainTypes);
        try{
            trainingdataIterator.readDataFromFile(traiFfileName);
            testingdataIterator.readDataFromFile(testFileName);

        }catch(IOException ioEx){
            ioEx.getStackTrace();
        }




        log.info("Build model....");
        //创建神经网络
        CreateNeuralNet netconfig = new CreateNeuralNet(firstLayerInput,hiddenLayer1,hiddenLayer2,outLayerOutput);
        MultiLayerNetwork network1 = netconfig.getSingleLayerNetModel(rate);

        log.info("TrainAndTest model....");





        //初始化用户界面
       *//* UIServer uiServer = UIServer.getInstance();

        StatsStorage statsStorage = new InMemoryStatsStorage();  //设置网络信息（随时间变化的梯度分值等）的存储位置。这里将其存储于内存，或者 new FileStatsStorage(file)存入文件

        uiServer.attach(statsStorage);  //将StatsStorage实例连接至用户界面，让StatsSorage的内容能被可视化

        network1.setListeners(new StatsListener(statsStorage),new ScoreIterationListener(1));//添加StatsListeners来收集信息
*//*

        //使用k-折交叉验证训练模型
        TrainAndTest trainAndTest = new TrainAndTest(threshold);
        trainAndTest.kFoldTrain(network1,trainingdataIterator,10,numEpochs);
        //测试
        list =  trainAndTest.test(network1,testingdataIterator);







        //保存和加载模型

        String fileName = String.valueOf(hiddenLayer1)+"_"+String.valueOf(hiddenLayer2)+"_"+String.valueOf(numEpochs)+"_"+String.valueOf(rate);

        String filePath  = "G:\\表位预测\\数据\\output\\"+fileName+".txt";
        try{
            Test.writeToFile(list,filePath); //将预测结果和真实值写入文件
            SaveAndLoadModel.saveModel(network1,"G:\\ModelFile\\翻转后\\all\\"+fileName+".zip");

        }catch (IOException ex){
            ex.getStackTrace();
        }



       //计算皮尔逊相关系数
        DataNode outPut = new DataNode(list.get(0));
        DataNode lables = new DataNode(list.get(1));
        try {
            String chartfileName = "G:\\ModelFile\\翻转后\\all\\散点图\\"+fileName+".png";
            ScatterPlot plot = new ScatterPlot(list.get(0), list.get(1),chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lables);
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;



    }*/


}


