package ann.training;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

import java.io.File;
import java.io.IOException;

public class SaveAndLoadModel {
    public static void saveModel (MultiLayerNetwork net, String fileName)throws IOException {
        File locationToSave = new File(fileName);      //Where to save the network. Note: the file is in .zip format - can be opened externally
        boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
        ModelSerializer.writeModel(net, locationToSave, saveUpdater);
    }
    public static MultiLayerNetwork loadModel(String fileName) throws IOException{
        return ModelSerializer.restoreMultiLayerNetwork(fileName);
    }
}
