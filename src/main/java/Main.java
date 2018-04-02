import SimulatedAnnealing.*;
import TSP.Travel;
import BinPicture.*;
import Sudoku.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
////        Travel travel = new Travel(20, -50, 10,-50,10, Travel.ARBITRARY_SWAP);
////        travel.addAll(new Travel(20, 50, 5,50,5, Travel.ARBITRARY_SWAP));
////        travel.addAll(new Travel(20, -50, 15,50,15, Travel.ARBITRARY_SWAP));
////        travel.addAll(new Travel(20, 50, 20,-50,20, Travel.ARBITRARY_SWAP));
//
//        //Travel travel = new Travel(90, 9, 2, 20, Travel.ARBITRARY_SWAP);
//
//        Travel travel = new Travel(50, 50, Travel.CONSECUTIVE_SWAP);
//
//
//        Collections.shuffle(travel.getCities());
//        showGraph(travel.getData());
//        System.out.println(travel.getEnergy());
//
//        Travel betterTravel = (Travel)SimulatedAnnealing.simulateAnnealing(travel, 2500, 1_000_000, 3, 0.99999);
//        System.out.println(betterTravel.getEnergy());
//        showGraph(betterTravel.getData());
//
//        XYSeriesCollection xycoll = new XYSeriesCollection();
//        xycoll.addSeries(SimulatedAnnealing.xySeries);
//        XYSeriesCollection xycoll2 = new XYSeriesCollection();
//        xycoll2.addSeries(SimulatedAnnealing.tSeries);
//
//
//        JFreeChart chart = ChartFactory.createXYLineChart("Travel Consecutive Swap","iteration", "Energy", xycoll);
//        ChartPanel panel = new ChartPanel(chart);
//        ApplicationFrame frame = new ApplicationFrame("TSP");
//        frame.setContentPane(panel);
//        frame.pack();
//        RefineryUtilities.centerFrameOnScreen(frame);
//        frame.setVisible(true);
//
//        JFreeChart chart2 = ChartFactory.createXYLineChart("Travel","iteration", "Temperature", xycoll2);
//        ChartPanel panel2 = new ChartPanel(chart2);
//        ApplicationFrame frame2 = new ApplicationFrame("TSP");
//        frame2.setContentPane(panel2);
//        frame2.pack();
//        RefineryUtilities.centerFrameOnScreen(frame2);
//        frame2.setVisible(true);
//
//        travel.setSwapType(Travel.ARBITRARY_SWAP);
//        betterTravel = (Travel)SimulatedAnnealing.simulateAnnealing(travel, 2500, 1_000_000, 3, 0.99999);
//        System.out.println(betterTravel.getEnergy());
//        showGraph(betterTravel.getData());
//
//        XYSeriesCollection xycoll_1 = new XYSeriesCollection();
//        xycoll_1.addSeries(SimulatedAnnealing.xySeries);
//        XYSeriesCollection xycoll2_1 = new XYSeriesCollection();
//        xycoll2_1.addSeries(SimulatedAnnealing.tSeries);
//
//
//        JFreeChart chart_1 = ChartFactory.createXYLineChart("Travel Arbitrary Swap","iteration", "Energy", xycoll_1);
//        ChartPanel panel_1 = new ChartPanel(chart_1);
//        ApplicationFrame frame_1 = new ApplicationFrame("TSP");
//        frame_1.setContentPane(panel_1);
//        frame_1.pack();
//        RefineryUtilities.centerFrameOnScreen(frame_1);
//        frame_1.setVisible(true);

//        JFreeChart chart2_1 = ChartFactory.createXYLineChart("Travel Arbitrary Swap","iteration", "Temperature", xycoll2_1);
//        ChartPanel panel2_1 = new ChartPanel(chart2_1);
//        ApplicationFrame frame2_1 = new ApplicationFrame("TSP");
//        frame2_1.setContentPane(panel2_1);
//        frame2_1.pack();
//        RefineryUtilities.centerFrameOnScreen(frame2_1);
//        frame2_1.setVisible(true);
        int n = 512;
        double delta1 = 0.4;
        ExecutorService service = Executors.newFixedThreadPool(1);
        ArrayList<Future<Problem>> futures = new ArrayList<>();
        //for(int i = 0; i < 3; ++i) {
//            futures.add(service.submit(new Worker(new CrossNeighborhood(new Physics1()), "crp1", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood(new Physics2()), "crp2", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood(new Physics3()), "crp3", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood(new Physics4()), "crp4", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood2(new Physics1()), "cr2p1", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood2(new Physics2()), "cr2p2", n, delta1)));
            futures.add(service.submit(new Worker(new CrossNeighborhood(new Physics2()), "crp2_512", n, delta1)));
//            futures.add(service.submit(new Worker(new CrossNeighborhood2(new Physics4()), "cr2p4", n, delta1)));
//            futures.add(service.submit(new Worker(new RingNeighborhood(new Physics1()), "rp1", n, delta1)));
//            futures.add(service.submit(new Worker(new RingNeighborhood(new Physics2()), "rp2", n, delta1)));
//            futures.add(service.submit(new Worker(new RingNeighborhood(new Physics3()), "rp3", n, delta1)));
//            futures.add(service.submit(new Worker(new RingNeighborhood(new Physics4()), "rp4", n, delta1)));
//            futures.add(service.submit(new Worker(new BigRingNeighborhood(new Physics1()), "brp1", n, delta1)));
//            futures.add(service.submit(new Worker(new BigRingNeighborhood(new Physics2()), "brp2", n, delta1)));
//            futures.add(service.submit(new Worker(new BigRingNeighborhood(new Physics3()), "brp3", n, delta1)));
//            futures.add(service.submit(new Worker(new BigRingNeighborhood(new Physics4()), "brp4", n, delta1)));
//            futures.add(service.submit(new Worker(new LeftDiagonalNeighborhood(new Physics1()), "ldp1", n, delta1)));
//            futures.add(service.submit(new Worker(new LeftDiagonalNeighborhood(new Physics2()), "ldp2", n, delta1)));
//            futures.add(service.submit(new Worker(new LeftDiagonalNeighborhood(new Physics3()), "ldp3", n, delta1)));
//            futures.add(service.submit(new Worker(new LeftDiagonalNeighborhood(new Physics4()), "ldp4", n, delta1)));
//            futures.add(service.submit(new Worker(new RightDiagonalNeighborhood(new Physics1()), "rdp1", n, delta1)));
//            futures.add(service.submit(new Worker(new RightDiagonalNeighborhood(new Physics2()), "rdp2", n, delta1)));
//            futures.add(service.submit(new Worker(new RightDiagonalNeighborhood(new Physics3()), "rdp3", n, delta1)));
//            futures.add(service.submit(new Worker(new RightDiagonalNeighborhood(new Physics4()), "rdp4", n, delta1)));
           // if(i == 1) delta1 = 0.3;
         //   else delta1 = 0.4;
        //}

        for(Future<Problem> f : futures){
            try {
                f.get();
                System.out.println("End");
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }





//        BinImage image = new BinImage(PictureFactory.createBinPic(n, delta1), n, new CrossNeighborhood2(new Physics1()));
//        image.saveImage("img1.png");
//
//        BinImage tmp = (BinImage)image.copy();
//        double startingEnergy = tmp.getEnergy();
//        double startingTemp = 0;
//        for(int i = 0; i < 1000; ++i){
//            tmp.swap();
//            double currentEnergy = tmp.getEnergy();
//            if(currentEnergy > startingEnergy){
//                double delta = currentEnergy -startingEnergy;
//                if(delta > startingTemp) startingTemp = delta;
//            }
//            else startingEnergy = currentEnergy;
//        }
//        SimulatedAnnealing algo = new SimulatedAnnealing();
//        BinImage image2 = (BinImage)algo.simulateAnnealing(image, startingTemp,
//                1000000, 1, 0.999993);
//
//        image2.saveImage("img2.png");
//
//        XYSeriesCollection xycoll = new XYSeriesCollection();
//        xycoll.addSeries(algo.xySeries);
//        XYSeriesCollection xycoll2 = new XYSeriesCollection();
//        xycoll2.addSeries(algo.tSeries);
//
//
//        JFreeChart chart = ChartFactory.createXYLineChart("Picture","iteration", "energy", xycoll);
//        ChartPanel panel = new ChartPanel(chart);
//        ApplicationFrame frame = new ApplicationFrame("Binary Picture");
//        frame.setContentPane(panel);
//        frame.pack();
//        RefineryUtilities.centerFrameOnScreen(frame);
//        frame.setVisible(true);
//
//        JFreeChart chart2 = ChartFactory.createXYLineChart("Picture","iteration", "T", xycoll2);
//        ChartPanel panel2 = new ChartPanel(chart2);
//        ApplicationFrame frame2 = new ApplicationFrame("Binary Picture");
//        frame2.setContentPane(panel2);
//        frame2.pack();
//        RefineryUtilities.centerFrameOnScreen(frame2);
//        frame2.setVisible(true);
        //BinPictureSimulatedAnnealing.xd();

//        Sudoku sudoku = new Sudoku("s6.txt");
//        System.out.println(sudoku.getEnergy());
//        SimulatedAnnealing algo = new SimulatedAnnealing();
//        Sudoku solution = (Sudoku)algo.simulateAnnealing(sudoku, 6, 200000, 1, 0.999968);
//        System.out.println(solution.getEnergy());
//        System.out.println(solution.toString());
//        XYSeriesCollection xycoll = new XYSeriesCollection();
//        xycoll.addSeries(SimulatedAnnealing.xySeries);
//        XYSeriesCollection xycoll2 = new XYSeriesCollection();
//        xycoll2.addSeries(SimulatedAnnealing.tSeries);


//        JFreeChart chart = ChartFactory.createXYLineChart("6","iteration", "energy", xycoll);
//        ChartPanel panel = new ChartPanel(chart);
//        ApplicationFrame frame = new ApplicationFrame("Sudoku");
//        frame.setContentPane(panel);
//        frame.pack();
//        RefineryUtilities.centerFrameOnScreen(frame);
//        frame.setVisible(true);
//
//        JFreeChart chart2 = ChartFactory.createXYLineChart("6","iteration", "T", xycoll2);
//        ChartPanel panel2 = new ChartPanel(chart2);
//        ApplicationFrame frame2 = new ApplicationFrame("Sudoku");
//        frame2.setContentPane(panel2);
//        frame2.pack();
//        RefineryUtilities.centerFrameOnScreen(frame2);
//        frame2.setVisible(true);



        
    }

    public static void showGraph(XYSeries data){
        XYSeriesCollection coll = new XYSeriesCollection();
        coll.addSeries(data);
        final JFreeChart chart = createChart(coll);
        final ChartPanel panel = new ChartPanel(chart);
        ApplicationFrame frame = new ApplicationFrame("TSP");
        frame.setContentPane(panel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);

    }

    private static JFreeChart createChart(final XYDataset dataset){
        final JFreeChart chart = ChartFactory.createScatterPlot("Travel", "X", "Y",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);
        return chart;
    }
}
