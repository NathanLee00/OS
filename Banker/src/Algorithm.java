

import java.util.Scanner;

public class Algorithm {

    int row,column,r,c;

    int[][] allocated;//分配矩阵
    int[][] max;//最大需求矩阵
    int[][] need;//需求矩阵
    int[] available;//可用资源向量
    int[] allocPlusAvail;//相当于work+allocation
    int[] completed_process;  //完成的进程标志向量
    int[] unsafe; //不安全向量

    int tc,ip;
    boolean safe; //安不安全的标志

    public void in(){
        init();
    }

    public void init(){  //处理数据输入和初始化的方法
        System.out.println("请输入进程数和可用资源数");
        Scanner sc = new Scanner(System.in);    //Java中的输入方法类
        row = sc.nextInt();
        column = sc.nextInt();
        allocated = new int[row][column];
        max = new int [row][column];
        need = new int[row][column];
        available = new int[column];
        allocPlusAvail = new int[column];
        completed_process = new int[column];
        unsafe = new int[column];

        tc=column;
        ip=0;
        safe=false;

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
