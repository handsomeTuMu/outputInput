package com.zeus.common.tools;

import java.util.Random;

/**
 * @author:tumu
 * @date:2019/10/10
 * @ver:1.0
 **/
public class RandomNumber {
    //最大最小值给出随机数
    static long makeRandomNumberMax(long min,long max){

        long num = min+(long)(new Random().nextDouble()*(max-min));

        return num;
    }
    //根据位数给出随机数
    static long makeRandomNumberPlace(int i){
        long k=1;
      for(int j=0;j<i;j++){
          k*=10;
      }
        return makeRandomNumberMax(1,k);
    }

    //根据位数补全位数给出随机数
    static String makeRandomNumberReplenishPlace(int i){
        long num=makeRandomNumberPlace(i);
        String k="0";
        for(int j=0;j<i;j++){
            k+="0";
        }
        String a=k+num;
        int m=a.length();
        String b=a.substring(m-i,m);

        return b;
    }

    public static void main(String[] args) {
        int i=1;
        while(i!=100) {

            System.out.println(makeRandomNumberReplenishPlace(5));
            i+=1;
        }
    }
}
