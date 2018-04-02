package SimulatedAnnealing;

import org.jfree.data.xy.XYSeries;

public class SimulatedAnnealing {
    private double tempExp(double T0, double k, double coolingRate){ return T0 * Math.pow(coolingRate, k); }
    private double tempLog(double T0, double k, double max){ return T0*(Math.log(max)-Math.log(k)); }
    private double tempLin(double T0, double k, int max){ return T0 - k*(T0/max); }
    public XYSeries xySeries;
    public XYSeries tSeries;

    //@coolingType: 1 -> tempExp, 2 -> tempLog, 3 -> tempLin
    public Problem simulateAnnealing(Problem problem, double startingTemperature, int iterationsAmount, int coolingType, double coolingRate){
        xySeries = new XYSeries("Energy");
        tSeries = new XYSeries("Temperature");
        double T = startingTemperature;
        Problem currentSolution = problem.copy();
        double bestEnergy = problem.getEnergy();

        xySeries.add(0,bestEnergy);
        tSeries.add(0, T);

        for(int i = 1; i <= iterationsAmount; ++i){

            currentSolution.swap();

            double currentEnergy = currentSolution.getEnergy();

            if(currentEnergy <= bestEnergy){
                bestEnergy = currentEnergy;
            }
            else{
                double P = Math.exp((bestEnergy - currentEnergy)/T);

                if( P < Math.random()){
                    currentSolution.reSwap();
                }
                else{
                    bestEnergy = currentEnergy;
                }
            }

            switch(coolingType){
                case 1:
                    T=tempExp(startingTemperature, i, coolingRate);
                    break;
                case 2:
                    T= tempLog(startingTemperature, i +(iterationsAmount/Math.exp(1)), iterationsAmount);
                    break;
                case 3:
                    T= tempLin(startingTemperature, i, iterationsAmount);
                    break;
            }

            if(iterationsAmount > 1000000) {
                if ((i+1) % 1000 == 0) {
                    xySeries.add(i, bestEnergy);
                    tSeries.add(i, T);
                }
            }
            else{
                xySeries.add(i, bestEnergy);
                tSeries.add(i, T);
            }

            if(currentEnergy == 0 || T < 1e-20){
                xySeries.add(i, bestEnergy);
                tSeries.add(i, T);
                System.out.println("Iterations " + i);
                break;
            }
            if(i % 10000 == 0) System.out.println(Thread.currentThread().getId() + " obtain checkpoint " + i/10000);
        }
        return currentSolution;
    }
}
