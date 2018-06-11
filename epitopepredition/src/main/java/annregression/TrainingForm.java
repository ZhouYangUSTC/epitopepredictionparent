package annregression;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TrainingForm {
    private JButton startButton;
    private JButton closeButton;
    private JTextField scatterSavePathTextField;
    private JTextField trainingPathTextField;
    private JTextField modelSavaPathTextField;
    private JTextField outputTextField;
    private JTextField testTextField;
    private JTextField epochTextField;
    private JTextField learningRateTextField;
    private JComboBox comboBox1;
    private JTextField hiddenLayer2TextField;
    private JTextField hiddenLayer1TextField;
    private JTextField MSETextField;
    private JTextField PearsonTextField;
    private JTextField falseRateTextField;
    private JTextField missRateTextField;
    private JTextField messageTextField;
    public Main main = new Main();

    public void initText(){
        MSETextField.setText("");
        PearsonTextField.setText("");
        falseRateTextField.setText("");
        missRateTextField.setText("");
        messageTextField.setText("");
        messageTextField.setText("");

    }
    public void messageText(){
        MSETextField.setText(main.trainAndTest.regEvaluationStats);
        PearsonTextField.setText(main.pearsonScore.toString());
        falseRateTextField.setText(main.trainAndTest.errRate.toString());
        missRateTextField.setText(main.trainAndTest.missRate.toString());
    }
    public TrainingForm() {

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initText();

                if(scatterSavePathTextField.getText().isEmpty()||
                        modelSavaPathTextField.getText().isEmpty()||
                        trainingPathTextField.getText().isEmpty()||
                        outputTextField.getText().isEmpty()||
                        epochTextField.getText().isEmpty()||
                        learningRateTextField.getText().isEmpty()||
                        testTextField.getText().isEmpty()||
                        hiddenLayer1TextField.getText().isEmpty())
                {
                    messageTextField.setText("信息未填完整！");
                }
                else{

                    List<String> filePathList = new ArrayList<>();
                    filePathList.add(trainingPathTextField.getText());
                    filePathList.add(testTextField.getText());
                    filePathList.add(modelSavaPathTextField.getText());
                    filePathList.add(outputTextField.getText());
                    filePathList.add(scatterSavePathTextField.getText());
                    if(comboBox1.getSelectedItem().equals("SQSinglelayer")){

                        main.SQSingleLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),filePathList);
                        messageText();
                    }
                    else if(comboBox1.getSelectedItem().equals("SQMultilayer")){

                        main.SQMultilyLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),Integer.valueOf(hiddenLayer2TextField.getText()),filePathList);
                        messageText();

                    }
                    else if(comboBox1.getSelectedItem().equals("SQLSinglelayer")){

                        main.SQLSingleLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),filePathList);
                        messageText();
                    }
                    else if(comboBox1.getSelectedItem().equals("SQLMultilayer")){

                        main.SQLMultilyLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),Integer.valueOf(hiddenLayer2TextField.getText()),filePathList);
                        messageText();
                    }
                    else if(comboBox1.getSelectedItem().equals("SQLTSinglelayer"))
                    {

                        main.SQLTSingleLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),filePathList);
                        messageText();
                    }
                    else if(comboBox1.getSelectedItem().equals("SQLTMultilayer")){

                        main.SQLMultilyLayerModel(Integer.valueOf(epochTextField.getText()),Double.valueOf(learningRateTextField.getText()),  Integer.valueOf(hiddenLayer1TextField.getText()),Integer.valueOf(hiddenLayer2TextField.getText()),filePathList);
                        messageText();
                    }
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox1.getSelectedItem().equals("SQSinglelayer")){
                    hiddenLayer2TextField.setVisible(false);
                }
                else if(comboBox1.getSelectedItem().equals("SQMultilayer")){
                    hiddenLayer2TextField.setVisible(true);
                }
                else if(comboBox1.getSelectedItem().equals("SQLSinglelayer")){
                    hiddenLayer2TextField.setVisible(false);
                }
                else if(comboBox1.getSelectedItem().equals("SQLMultilayer")){
                    hiddenLayer2TextField.setVisible(true);
                }
                else if(comboBox1.getSelectedItem().equals("SQLTSinglelayer")) {
                    hiddenLayer2TextField.setVisible(false);
                }
                else if(comboBox1.getSelectedItem().equals("SQLTMultilayer")){
                    hiddenLayer2TextField.setVisible(true);
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("TrainingForm");
        frame.setContentPane(new TrainingForm().JPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

    }

    private JPanel JPanel1;

}
