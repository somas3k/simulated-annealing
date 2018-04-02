package BinPicture;

public class Coords {
    int x;
    int y;
    Coords(int x, int y){
        this.x = x;
        this.y = y;
    }

    void correctPositions(int n){
        if(x < 0) x += n;
        if(y < 0) y += n;
        if(x >= n) x -= n;
        if(y >= n) y -= n;
    }
}
