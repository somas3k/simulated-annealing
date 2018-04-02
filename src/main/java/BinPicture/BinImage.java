package BinPicture;


import SimulatedAnnealing.Problem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BinImage implements Problem{
    private int[][] img;

    private int x1,y1,x2,y2;

    private int n;

    private INeighborhood neighborhood;

    public BinImage(int[][] img, int n, INeighborhood neighborhood) {
        this.img = img;
        this.n = n;
        this.neighborhood = neighborhood;
    }

    @Override
    public double getEnergy(){
        int e = 0;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n ; ++j){
                e+= neighborhood.getPixelEnergy(img, new Coords(i, j));
            }
        }
        return e;
    }

    @Override
    public void swap(){

        int x1 = (int) (Math.random()*n);
        int y1 = (int) (Math.random()*n);
        int x2 = (int) (Math.random()*n);
        int y2 = (int) (Math.random()*n);
        while(img[x1][y1] == img[x2][y2]){
            x2 = (int) (Math.random()*n);
            y2 = (int) (Math.random()*n);
        }
        int tmp = img[x1][y1];
        img[x1][y1] = img[x2][y2];
        img[x2][y2] = tmp;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void reSwap(){
        int tmp = img[x1][y1];
        img[x1][y1] = img[x2][y2];
        img[x2][y2] = tmp;
    }

    @Override
    public Problem copy(){
        return new BinImage(img, n, neighborhood);
    }

    public void saveImage(String path){
        BufferedImage image = new BufferedImage(n, n, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(img[i][j] == 0) image.setRGB(i, j, Color.WHITE.getRGB());
                else image.setRGB(i, j, Color.BLACK.getRGB());
            }
        }

        File f;

        try {
            f = new File(path);
            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void print(){
//        for(int i = 0; i < n; ++i){
//            for(int j = 0; j < n; ++j){
//                System.out.print(img[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//    }
}


