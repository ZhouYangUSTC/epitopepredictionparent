package annregression.dataprocessing;

public class PropertyToVector {
    static double a = 1;
    static double b = 0.1;
    static double c = 1;
    static double d = 0;

    public static double table[][]  = {
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//0 A
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//1 B *
            {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//2 C
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//3 D
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//4 E
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//5 F
            {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//6 G
            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},//7 H
            {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},//8 I
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//9 J *
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},//10 K
            {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},//11 L
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},//12 M
            {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},//13 N
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//14 O *
            {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},//15 P
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},//16 Q
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},//17 R
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},//18 S
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},//19 T
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//20 U *
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},//21 V
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},//22 W
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//23 X *
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},//24 Y
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}//25  Z *
    };

    public static double table1[][]  = {
            {a,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//0 A
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//1 B *
            {b,a,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//2 C
            {b,b,a,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//3 D
            {b,b,b,a,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//4 E
            {b,b,b,b,a,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//5 F
            {b,b,b,b,b,a,b,b,b,b,b,b,b,b,b,b,b,b,b,b},//6 G
            {b,b,b,b,b,b,a,b,b,b,b,b,b,b,b,b,b,b,b,b},//7 H
            {b,b,b,b,b,b,b,a,b,b,b,b,b,b,b,b,b,b,b,b},//8 I
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//9 J *
            {b,b,b,b,b,b,b,b,a,b,b,b,b,b,b,b,b,b,b,b},//10 K
            {b,b,b,b,b,b,b,b,b,a,b,b,b,b,b,b,b,b,b,b},//11 L
            {b,b,b,b,b,b,b,b,b,b,a,b,b,b,b,b,b,b,b,b},//12 M
            {b,b,b,b,b,b,b,b,b,b,b,a,b,b,b,b,b,b,b,b},//13 N
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//14 O *
            {b,b,b,b,b,b,b,b,b,b,b,b,a,b,b,b,b,b,b,b},//15 P
            {b,b,b,b,b,b,b,b,b,b,b,b,b,a,b,b,b,b,b,b},//16 Q
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,a,b,b,b,b,b},//17 R
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,a,b,b,b,b},//18 S
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,a,b,b,b},//19 T
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//20 U *
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,a,b,b},//21 V
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,a,b},//22 W
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//23 X *
            {b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,a},//24 Y
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}//25  Z *
    };


    public static double table2[][]  = {
            {c,d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//0 A
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//1 B *
            {d, c, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//2 C
            {d, d, c, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//3 D
            {d, d, d, c, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//4 E
            {d, d, d, d, c, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//5 F
            {d, d, d, d, d, c, d, d, d, d, d, d, d, d, d, d, d, d, d, d},//6 G
            {d, d, d, d, d, d, c, d, d, d, d, d, d, d, d, d, d, d, d, d},//7 H
            {d, d, d, d, d, d, d, c, d, d, d, d, d, d, d, d, d, d, d, d},//8 I
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//9 J *
            {d, d, d, d, d, d, d, d, c, d, d, d, d, d, d, d, d, d, d, d},//10 K
            {d, d, d, d, d, d, d, d, d, c, d, d, d, d, d, d, d, d, d, d},//11 L
            {d, d, d, d, d, d, d, d, d, d, c, d, d, d, d, d, d, d, d, d},//12 M
            {d, d, d, d, d, d, d, d, d, d, d, c, d, d, d, d, d, d, d, d},//13 N
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//14 O *
            {d, d, d, d, d, d, d, d, d, d, d, d, c, d, d, d, d, d, d, d},//15 P
            {d, d, d, d, d, d, d, d, d, d, d, d, d, c, d, d, d, d, d, d},//16 Q
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, c, d, d, d, d, d},//17 R
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, c, d, d, d, d},//18 S
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, c, d, d, d},//19 T
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//20 U *
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, c, d, d},//21 V
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, c, d},//22 W
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//23 X *
            {d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, d, c},//24 Y
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}//25  Z *
    };
    /*
    * 将肽段字符序列转化为向量
    * 输入：短肽字符数组
    *
    * 输出：正交编码的向量（数组）
    * */
    public static double[] sequenceToVector(char[] chr){

        double[]vec = new double[chr.length*20];

        double[][]epitopeArry = new double[chr.length][];
        for (int i = 0; i < chr.length; i++) {
            if(i==1 || i==8){
                epitopeArry[i] = table1[chr[i]-'A'];
            }
            else{
                epitopeArry[i] = table2[chr[i]-'A'];
            }
            //epitopeArry[i] = table[chr[i]-'A'];
        }
        int index = 0;
        for (double[] array : epitopeArry) {
            for (double element : array) {
                vec[index++] = element;
            }
        }

        return vec;

    }


}
