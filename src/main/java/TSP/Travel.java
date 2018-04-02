package TSP;

import SimulatedAnnealing.Problem;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;

public class Travel implements Problem {
    private ArrayList<City> cities = new ArrayList<>();
    public static final int CONSECUTIVE_SWAP = 1;
    public static final int ARBITRARY_SWAP = 2;
    private int swapType;
    private int index1, index2;

    public void setSwapType(int swapType) {
        this.swapType = swapType;
    }

    public void addAll(Travel cycle){
        cities.addAll(cycle.cities);
    }

    public ArrayList<City> getCities(){
        return cities;
    }

    private void add(City city){
        cities.add(city);
    }

    private int size(){
        return cities.size();
    }

    private City get(int index){
        return cities.get(index);
    }

    private Travel(ArrayList<City> cities, int swapType){
        this.cities = cities;
        this.swapType = swapType;
    }
    public Travel(int n, int swapType){
        this.swapType = swapType;
        for(int i = 0; i < n; ++i){
            this.add(new City());
        }
    }

    private void set(int index, City city){
        cities.set(index, city);
    }
    public Travel(int n, double bound, int swapType){
        this.swapType = swapType;
        for(int i = 0; i < n; ++i){
            this.add(new City(bound));
        }
    }

    public Travel(int n, int groups, double bound, double offset, int swapType){
        this.swapType = swapType;
        int groupAmount = n/groups;
        double x;
        double y;
        if(groups == 4){
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound - offset;
                y = Math.random()*2*bound - bound - offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound - offset;
                y = Math.random()*2*bound - bound + offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound + offset;
                y = Math.random()*2*bound - bound - offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound + offset;
                y = Math.random()*2*bound - bound + offset;
                add(new City(x, y));
            }
        }
        if(groups == 9){
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound - offset;
                y = Math.random()*2*bound - bound - offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound - offset;
                y = Math.random()*2*bound - bound + offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound + offset;
                y = Math.random()*2*bound - bound - offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound + offset;
                y = Math.random()*2*bound - bound + offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound;
                y = Math.random()*2*bound - bound;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound;
                y = Math.random()*2*bound - bound + offset;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound + offset;
                y = Math.random()*2*bound - bound;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound - offset;
                y = Math.random()*2*bound - bound;
                add(new City(x, y));
            }
            for(int i = 0; i < groupAmount; ++i){
                x = Math.random()*2*bound - bound;
                y = Math.random()*2*bound - bound - offset;
                add(new City(x, y));
            }

        }

    }

    public Travel(int n, double uX, double sigmaX, double uY, double sigmaY, int swapType){
        this.swapType = swapType;

        NormalDistribution distributionX = new NormalDistribution(uX, sigmaX);
        NormalDistribution distributionY = new NormalDistribution(uY, sigmaY);

        for (int i = 0; i < n; i++) {
            add(new City(distributionX.sample(), distributionY.sample()));
        }
    }

    @Override
    public void reSwap(){
        City a = this.get(index1);
        City b = this.get(index2);
        set(index1, b);
        set(index2, a);
    }

    @Override
    public double getEnergy(){
        double distance = 0;
        for(int i = 0; i < size()-1; ++i){
            distance += get(i).getDistance(get(i+1));
        }
        distance += get(size()-1).getDistance(get(0));
        return distance;
    }

    public XYSeries getData(){
        XYSeries data = new XYSeries("cycle", false);
        for(City c : cities){
            data.add(c.getX(),c.getY());
        }
        data.add(this.get(0).getX(), this.get(0).getY());
        return data;
    }

    private void swapCities(int index1, int index2){
        City a = this.get(index1);
        City b = this.get(index2);
        set(index1, b);
        set(index2, a);
        this.index1 = index1;
        this.index2 = index2;
    }

    private void consecutiveSwapCities(){
        int index1 = (int)(Math.random()*size());
        int index2 = index1+1;
        if(index2 == size()) index2 = 0;
        swapCities(index1, index2);
    }

    private void arbitrarySwapCities(){
        int index1 = (int)(Math.random()*size());
        int index2 = (int)(Math.random()*size());
        while( index1 == index2 ){
            index2 = (int)(Math.random()*size());
        }
        swapCities(index1, index2);
    }

    @Override
    public void swap(){
        switch(swapType){
            case CONSECUTIVE_SWAP:
                consecutiveSwapCities();
                break;
            case ARBITRARY_SWAP:
                arbitrarySwapCities();
                break;
        }
    }


    @Override
    public Problem copy(){
        return new Travel(cities, swapType);
    }



}
