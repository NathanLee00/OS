

import java.util.Scanner;

public class DataIn {   //处理数据输入的类

    int row,column;

    public void In(){
        System.out.println("请输入进程数和可用资源数");
        Scanner sc = new Scanner(System.in);    //Java中的输入方法类
        row = sc.nextInt();
        column = sc.nextInt();
        init();
    }

    public void init(){
        int[][] allocated = new int[row][column]; //
        int[][] max = new int [row][column]; //最大需求矩阵
        int[][] need = new int[row][column]; //
        int[] available = new int[column];  //可用资源向量
        int[] allocSubAvail = new int[column];
    }
}
