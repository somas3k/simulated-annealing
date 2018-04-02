package BinPicture;

public class BigRingNeighborhood implements INeighborhood {
    public BigRingNeighborhood() {
    }

    public void setPhysics(IPhysics physics) {
        this.physics = physics;
    }

    private IPhysics physics;

    private final int[][] offsets = {{-2,2},{-2,1},{-2,0},{-2,-1},{-2,-2},{-1,-2},{0,-2},{1,-2},
                                     {2,-2},{2,-1},{2,0},{2,1},{2,2},{1,2},{0,2},{-1,2}};

    public BigRingNeighborhood(IPhysics physics) {
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
