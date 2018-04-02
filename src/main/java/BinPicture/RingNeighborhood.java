package BinPicture;

public class RingNeighborhood implements INeighborhood {
    private IPhysics physics;

    private final int[][] offsets = {{-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}};

    public RingNeighborhood(IPhysics physics) {
        this.physics = physics;
    }

    public void setPhysics(IPhysics physics) {
        this.physics = physics;
    }

    public RingNeighborhood() {

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
