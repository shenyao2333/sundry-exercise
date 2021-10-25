package com.sy.ex.clickhouse.utils;

import java.util.Random;

/**
 * @Author: sy
 * @Date: Created by 2021/10/25 15:47
 * @description:
 */
public class CreatRoundChain {

    private static String[] name = {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎","他","虞","万","支","柯"};
    private static Random random=new Random();

    /**
     * 根据int 值转为汉字
     * @Date 2017年6月12日,上午11:49:24
     * @param n
     * @return chinese
     */
    public static String AscallTChina(int n) {
        return Character.toString( (char) n );
    }
    /**
     * 生成汉字
     * @Date 2017年6月12日,上午11:52:35
     * @param n 生成汉字的数量
     * @return 汉字
     */
    public static String getRandChain(int n){
        int max=40869;
        int min=19968;
        Random mRandom=new Random();
        String string=null;
        for ( int i = 0; i < n; i++ ) {
            int data=mRandom.nextInt( max )%(max-min+1)+min;
            string= string+AscallTChina( data );
        }
        return string.replaceAll( "null" , "" );
    }

    public static String getName(){
        int i = random.nextInt(53);
        return name[i]+getRandChain(1);
    }

    public static void main(String[] args) {

        System.out.println(getName());
    }
}
