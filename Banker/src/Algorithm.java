import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {

    int row,column,r,c;

    int[][] allocation;//分配矩阵
    int[][] max;//最大需求矩阵
    int[][] need;//需求矩阵
    int[] available;//可用资源向量
    int[] allocPlusAvail;//相当于work+allocation
    int[] completed_process;  //完成的进程标志向量
    int[] unsafe; //不安全向量
    int[] request;  //需求向量
    int rpm; //指定需求向量为第几个进程

    public void run(){
        init();
        banker();
    }

    public void init(){  //处理数据输入和初始化的方法
        System.out.println("请输入进程数和可用资源数");
        Scanner sc = new Scanner(System.in);    //Java中的输入方法类
        row = sc.nextInt();
        column = sc.nextInt();
        allocation = new int[row][column];
        max = new int [row][column];
        need = new int[row][column];
        available = new int[column];
        request = new int[column];
        allocPlusAvail = new int[column];
        completed_process = new int[column];
        unsafe = new int[column];


        System.out.println("请输入分配矩阵");
        for(r=0;r<row;r++)
            for(c=0;c<column;c++)
                allocation[r][c] = sc.nextInt();

//        System.out.println("已分配矩阵");
//        System.out.println(Arrays.toString(allocated[0]));

        System.out.println("请输入最大需求矩阵");
        for(r=0;r<row;r++)
            for(c=0;c<column;c++)
                max[r][c] = sc.nextInt();

        System.out.println("请输入可用资源向量");
        for (r=0;r<column;r++)
            available[r] = sc.nextInt();

        for (r=0;r<row;r++){
            for(c=0;c<column;c++){
                need[r][c] = max[r][c]- allocation[r][c];
            }
        }   //生成need矩阵

        for(r=0;r<row;r++)
        {
            for(c=0;c<column;c++)
            {
                allocPlusAvail[r]+= allocation[c][r];
            }
            allocPlusAvail[r]+=available[r]; //available=work
        }//生成work+allocation向量
        System.out.println("请输入要请求资源的进程");
        rpm = sc.nextInt();
        rpm--;
        System.out.println("请输入请求向量");
        for(r=0;r<column;r++)
            request[r]=sc.nextInt(); //生成请求向量
    }

    public void banker(){
        for(r=0;r<column;r++){ //请求合法性检查
            if(request[r]>need[rpm][r]||request[r]>available[r]){
                System.out.println("请求不合法");
                break;
            }
        }
        for(r=0;r<column;r++){
            available[r]-=request[r];
            allocation[rpm][r]+=request[r];
            need[rpm][r]-=request[r];
        }

        System.out.println(Arrays.toString(available));
        System.out.println(Arrays.toString(allocation[0]));
        System.out.println(Arrays.toString(need[0]));
    }

}
