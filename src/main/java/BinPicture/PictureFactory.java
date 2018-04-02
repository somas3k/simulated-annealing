package BinPicture;

public class PictureFactory {
    public static int[][] createBinPic(int n, double delta){
        int[][] picture = new int[n][n];

        int size = n*n;
        for(int i = 0; i < n ; ++i){
            for(int j = 0; j < n; ++j){
                if(delta > Math.random()){
                    picture[i][j] = 1;
                }
            }
        }
        double whiteCount = 0;
        double blackCount = 0;
        for(int i = 0; i < n ; ++i){
            for(int j = 0; j < n; ++j){
                if(picture[i][j] == 0) whiteCount++;
                else blackCount++;
            }
        }
        System.out.println(blackCount/size);
//        for(int i = 0; i < size*delta; ++i){
//            picture[i%n][i/n] = 1;
//        }
        return picture;
    }
}
