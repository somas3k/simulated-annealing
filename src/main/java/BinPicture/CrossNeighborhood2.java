package BinPicture;

public class CrossNeighborhood2 implements INeighborhood {
    public void setPhysics(IPhysics physics) {
        this.physics = physics;
    }

    public CrossNeighborhood2() {

    }

    private IPhysics physics;
    private final int[][] offsets = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    public CrossNeighborhood2(IPhysics physics) {
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
