package BinPicture;

//white doesn't like black & vice versa
public class Physics1 implements IPhysics {
    @Override
    public int getEnergy(int[][] img, Coords p1, Coords p2) {
        int e = 0;

        p2.correctPositions(img.length);

        //white
        if(img[p1.x][p1.y] == 0){
            if(img[p2.x][p2.y] == 1) e = 1;
        }
        //black
        else{
            if(img[p2.x][p2.y] == 0) e = 1;
        }
        return e;
    }
}
