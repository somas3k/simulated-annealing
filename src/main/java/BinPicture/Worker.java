package BinPicture;

import SimulatedAnnealing.Problem;
import SimulatedAnnealing.SimulatedAnnealing;

import java.util.concurrent.Callable;

public class Worker implements Callable<Problem> {
    private INeighborhood neighborhood;
    private String path;
    private int n;
    private double delta;

    public Worker(INeighborhood neighborhood, String path, int n, double delta) {
        this.neighborhood = neighborhood;
        this.path = path;
        this.n = n;
        this.delta = delta;
    }


    @Override
    public Problem call() throws Exception {
        BinImage image = new BinImage(PictureFactory.createBinPic(n, delta), n, neighborhood);
        image.saveImage(path+"_delta_"+ delta +"_before.png");

        BinImage tmp = (BinImage)image.copy();
        double startingEnergy = tmp.getEnergy();
        double startingTemp = 0;
        for(int i = 0; i < 1000; ++i){
            tmp.swap();
            double currentEnergy = tmp.getEnergy();
            if(currentEnergy > startingEnergy){
                double delta = currentEnergy -startingEnergy;
                if(delta > startingTemp) startingTemp = delta;
            }
            else startingEnergy = currentEnergy;
        }
        SimulatedAnnealing algo = new SimulatedAnnealing();
        BinImage image2 = (BinImage)algo.simulateAnnealing(image, startingTemp,
                1000000, 1, 0.999993);

        image2.saveImage(path+"_delta_"+ delta +"_after.png");

//        XYSeriesCollection xycoll = new XYSeriesCollection();
//        xycoll.addSeries(algo.xySeries);

        return image2;
    }
}
