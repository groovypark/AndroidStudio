package kr.ac.hongik.selab.jang.customroulette;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by wscom on 2017-07-10.
 */

public class ColorMaker {

    public static int[] getColors(int count){
        int[] retarr = new int[count];
        Random rand = new Random();

        for(int i=0; i<count; i++){
            retarr[i] = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }

        return retarr;
    }

    public static int getColor(){
        Random rand = new Random();
        return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }
}
