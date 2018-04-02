package SimulatedAnnealing;

public interface Problem {

    double getEnergy();

    void swap();

    void reSwap();

    Problem copy();
}
