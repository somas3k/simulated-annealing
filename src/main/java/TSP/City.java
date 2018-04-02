package TSP;

public class City {
    private double x;
    private double y;

    City(){
        double bound = 100;
        this.x = Math.random()*2*bound - bound;
        this.y = Math.random()*2*bound - bound;
    }

    City(double bound){
        this.x = Math.random()*2*bound - bound;
        this.y = Math.random()*2*bound - bound;
    }

    City(double x, double y){
        this.x = x;
        this.y = y;
    }

    double getDistance(City city){
        return Math.sqrt(Math.pow(x-city.getX(),2)+Math.pow(y-city.getY(),2));
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}
