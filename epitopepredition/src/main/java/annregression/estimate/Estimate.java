package annregression.estimate;

import org.nd4j.linalg.api.ndarray.INDArray;

public class Estimate {
    public static Double errRate;
    public static Double missRate;
    public String regEvaluationStats;

    public static void erorAndMiss(INDArray input, INDArray output, double threshold){
        double err = 0.0;
        double miss = 0.0;
        double accurate = 0.0;
        int length = input.length();


        for (int i = 0; i < length; i++) {
            if(input.getDouble(i)>= threshold && output.getDouble(i) < threshold)
            {
                miss++;
            }
            else if(input.getDouble(i)<= threshold && output.getDouble(i) >=threshold)
            {
                err++;
            }
            else if(input.getDouble(i)>= threshold && output.getDouble(i) >=threshold)
            {
                accurate++;
            }

        }
        errRate = err/(err+accurate);
        missRate = miss/(miss+accurate);
        System.out.println("Prediction errorRate is:"+errRate+", and missRate is:"+missRate);

    }
    public static void correlationAndScatter(double[] predict,double[] lables,String filename){
        DataNode outPut = new DataNode(predict);
        DataNode lable = new DataNode(lables);
        try {
            String chartfileName = filename+".png";
            ScatterPlot plot = new ScatterPlot(predict, lables,chartfileName);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        PearsonCorrelationScore score = new PearsonCorrelationScore(outPut,lable);
        double pearsonScore =score.getPearsonCorrelationScore();
        System.out.println("Pearson Correlation Coefficient is:"+score.getPearsonCorrelationScore());;

    }

}
