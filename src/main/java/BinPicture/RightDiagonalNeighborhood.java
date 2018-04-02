package BinPicture;

public class RightDiagonalNeighborhood implements INeighborhood {
    private IPhysics physics;

    private final int[][] offsets = {{-2,2},{-1,1},{1,-1},{2,-2}};

    public void setPhysics(IPhysics physics) {
        this.physics = physics;
    }

    public RightDiagonalNeighborhood() {

    }

    public RightDiagonalNeighborhood(IPhysics physics) {
        this.physics = physics;
    }

    @Override
    public int getPixelEnergy(int[][] img, Coords pixel) {
        int e = 0;
        for(int[] offset : offsets){
            e+=physics.getEnergy(img, pixel, new Coords(pixel.x+offset[0], pixel.y+offset[1]));
        }
        return e;
    }
}
