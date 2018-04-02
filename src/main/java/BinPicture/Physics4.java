package BinPicture;

//White doesn't like black above
//Black doesn't like white below
public class Physics4 implements IPhysics {
    @Override
    public int getEnergy(int[][] img, Coords p1, Coords p2) {
        int e = 0;

        p2.correctPositions(img.length);

        if(img[p1.x][p1.y] == 0){
            if(p2.y > p1.y && img[p2.x][p2.y] == 1) e = 1;
        }
        else if(p2.y < p1.y && img[p2.x][p2.y] == 0) e = 1;

        return e;
    }
}
