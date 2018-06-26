package annregression.estimate;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;


import java.awt.*;
import java.io.File;

public class ScatterPlot {
    private  double[][] data = new double[2][];

    public ScatterPlot(double[] outPut,double[] inPut,String fileName) throws Exception {
        this.showChart(outPut,inPut,fileName);
    }

    public ScatterPlot(double[] outPut,double[] inPut)throws Exception{
        this.showChart(outPut,inPut);
    }
    public void  showChart(double[] outPut,double[] inPut) throws Exception {

        DefaultXYDataset xydataset = new DefaultXYDataset();

        //根据类别建立数据集
        data[0] = inPut;
        data[1] = outPut;



        xydataset.addSeries("IC50", data);

        JFreeChart chart = ChartFactory.createScatterPlot("Scatter", "true value", "prediction",
                xydataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        ChartFrame frame = new ChartFrame("散点图", chart, true);
        chart.setBackgroundPaint(Color.white);
        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(1.5f));






        XYPlot xyplot = (XYPlot) chart.getPlot();

        xyplot.setBackgroundPaint(new Color(255, 253, 246));




        ValueAxis va = xyplot.getDomainAxis(0);


        va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
        va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
        xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
        va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
        va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色

        //va.setAutoTickUnitSelection(true);
        va.setRange(0.0,1.0);
        va.setLowerBound(0.0);
        va.setUpperBound(1.0);


        ValueAxis axis = xyplot.getRangeAxis();
        axis.setAxisLineStroke(new BasicStroke(1.5f));

        //axis.setRange(0.0,1.0);
        axis.setLowerBound(0.0);
        axis.setUpperBound(1.0);
        //axis.setAutoTickUnitSelection(true);
        axis.setRangeWithMargins(0, 1.0);





        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
                .getRenderer();
        xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
        xylineandshaperenderer.setUseOutlinePaint(true);
        NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setTickMarkInsideLength(2.0F);
        numberaxis.setTickMarkOutsideLength(0.0F);
        numberaxis.setAxisLineStroke(new BasicStroke(1.5f));


        numberaxis.setAutoTickUnitSelection(false);
        //设置纵坐标值的间距为0.1
        numberaxis.setTickUnit(new NumberTickUnit(0.1));

        //纵坐标值只能是0-0.1之间的值
        numberaxis.setRangeWithMargins(0, 1.0);
        NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
        numberaxis1.setAutoRangeIncludesZero(false);
        numberaxis1.setTickMarkInsideLength(2.0F);
        numberaxis1.setTickMarkOutsideLength(0.0F);
        numberaxis1.setAxisLineStroke(new BasicStroke(1.5f));


        numberaxis1.setAutoTickUnitSelection(false);
        //设置纵坐标值的间距为0.1
        numberaxis1.setTickUnit(new NumberTickUnit(0.1));

        //纵坐标值只能是0-0.1之间的值
        numberaxis1.setRangeWithMargins(0, 1.0);


        frame.pack();
        frame.setVisible(true);


    }

    public void  showChart(double[] outPut,double[] inPut,String fileName) throws Exception {

        DefaultXYDataset xydataset = new DefaultXYDataset();

        //根据类别建立数据集
        data[0] = inPut;
        data[1] = outPut;



        xydataset.addSeries("IC50", data);

        JFreeChart chart = ChartFactory.createScatterPlot("Scatter", "true value", "prediction",
                xydataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);
        ChartFrame frame = new ChartFrame("散点图", chart, true);
        chart.setBackgroundPaint(Color.white);
        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(1.5f));






        XYPlot xyplot = (XYPlot) chart.getPlot();

        xyplot.setBackgroundPaint(new Color(255, 253, 246));




        ValueAxis va = xyplot.getDomainAxis(0);


        va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细
        va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色
        xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细
        va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色
        va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色


        va.setRange(0.0,1.0);
        va.setLowerBound(0.0);
        va.setUpperBound(1.0);


        ValueAxis axis = xyplot.getRangeAxis();
        axis.setAxisLineStroke(new BasicStroke(1.5f));


        axis.setLowerBound(0.0);
        axis.setUpperBound(1.0);
        //axis.setAutoTickUnitSelection(true);
        axis.setRangeWithMargins(0, 1.0);





        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot
                .getRenderer();
        xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE);
        xylineandshaperenderer.setUseOutlinePaint(true);
        NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setTickMarkInsideLength(2.0F);
        numberaxis.setTickMarkOutsideLength(0.0F);
        numberaxis.setAxisLineStroke(new BasicStroke(1.5f));


        numberaxis.setAutoTickUnitSelection(false);
        //设置横坐标值的间距为0.1
        numberaxis.setTickUnit(new NumberTickUnit(0.1));

        //横坐标值只能是0-0.1之间的值
        numberaxis.setRangeWithMargins(0, 1.0);




        NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
        numberaxis1.setAutoRangeIncludesZero(false);
        numberaxis1.setTickMarkInsideLength(2.0F);
        numberaxis1.setTickMarkOutsideLength(0.0F);
        numberaxis1.setAxisLineStroke(new BasicStroke(1.5f));


        numberaxis1.setAutoTickUnitSelection(false);
        //设置纵坐标值的间距为0.1
        numberaxis1.setTickUnit(new NumberTickUnit(0.1));

        //纵坐标值只能是0-0.1之间的值
        numberaxis1.setRangeWithMargins(0, 1.0);






        //保存图片
        String filepath = fileName;
        File file = new File(filepath);
        ChartUtilities.saveChartAsPNG(file, chart, 600, 500);
        Thread.sleep(200);


        frame.pack();
        frame.setVisible(true);


    }





}
