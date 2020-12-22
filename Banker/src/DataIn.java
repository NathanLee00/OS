

import java.util.Scanner;

public class DataIn {   //处理数据输入的类

    int row,column,r,c;

    public void In(){
        init();
    }

    public void init(){
        System.out.println("请输入进程数和可用资源数");
        Scanner sc = new Scanner(System.in);    //Java中的输入方法类
        row = sc.nextInt();
        column = sc.nextInt();
        int[][] allocated = new int[row][column]; //分配矩阵
        int[][] max = new int [row][column]; //最大需求矩阵
        int[][] need = new int[row][column]; //需求矩阵
        int[] available = new int[column];  //可用资源向量
        int[] allocPlusAvail = new int[column]; //相当于work+allocation

        int[] completed_process = new int[column];
        int[] unsafe = new int[column];

        System.out.println("请输入分配矩阵");
        for(r=0;r<row;r++)
            for(c=0;c<column;c++)
                allocated[r][c] = sc.nextInt();

        System.out.println("请输入最大需求矩阵");
        for(r=0;r<row;r++)
            for(c=0;c<column;c++)
                max[r][c] = sc.nextInt();

        System.out.println("请输入可用资源向量");
        for (r=0;r<column;r++)
            available[r] = sc.nextInt();

        for (r=0;r<row;r++){
            for(c=0;c<column;c++){
                need[r][c] = max[r][c]-allocated[r][c];
            }
        }   //生成need矩阵

        for(r=0;r<row;r++)
        {
            for(c=0;c<column;c++)
            {
                allocPlusAvail[r]+=allocated[c][r];
            }
            allocPlusAvail[r]+=available[r]; //available=work
        }//生成work+allocation向量


    }
}
