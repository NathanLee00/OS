import java.util.Arrays;
import java.util.Scanner;

public class Algorithm {

    int row,column,r,c;

    int[][] allocation;//分配矩阵
    int[][] max;//最大需求矩阵
    int[][] need;//需求矩阵
    int[] workPlusAllocation; //工作向量加分配矩阵
    int[] available;//可用资源向量
    int[] finish;  //完成的进程标志向量
    int[] unsafe; //不安全向量
    int[] request;  //需求向量
    int[] work;//工作向量
    int[] sequence;
    boolean legal;

    int rpm; //指定需求向量为第几个进程

    public void run(){
        init();
        banker();
        if(legal)
            safe_check();
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
        sequence=new int[column];
        request = new int[column];
        finish = new int[column];
        unsafe = new int[column];
        work = new int[column];
        workPlusAllocation = new int[column];
        legal=true;

        System.out.println("请输入分配矩阵");
        for(r=0;r<row;r++)
            for(c=0;c<column;c++)
                allocation[r][c] = sc.nextInt();


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

        for(c=0;c<column;c++){
            finish[c]=0;
        }

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
                legal=false;
                return;
            }
        }
        for(r=0;r<column;r++){
            available[r]-=request[r];
            allocation[rpm][r]+=request[r];
            need[rpm][r]-=request[r];
        }

    }

    public void safe_check() {
        work = (int [])available.clone();


        boolean safe = false;
        int tempR = -1, tempC = -1, tempP = -1;
        int count=-1;
        while (!safe) {
            boolean allFinish=true;
            for (r = 0; r < row; r++) {
                for (c = 0; c < column; c++) {
                    if (need[r][c] <= work[c]) {
                        if (c == column - 1&&finish[c]!=1) {
                            tempR = r;
                            count++;
                        }
                        continue;
                    } else {
                        break;
                    }

                }
            }

            tempP = tempR;
            if(tempR==-1){
                System.out.println("无安全序列");
                return;
            }
            sequence[count]= tempP+1;
            for (c = 0; c < column; c++) {
                workPlusAllocation[c] = work[c] + allocation[tempR][c];
                finish[tempP] = 1;
            }
            work=workPlusAllocation;
            tempR=-100;
            for(r=0;r<column;r++){
                if(finish[r]==0){
                  allFinish=false;
                }
            }
            for(r=0;r<column;r++){
                if(finish[r]==1){
                    safe=true;
                }
            }
            if(safe){
                System.out.println("安全序列已找到");
                System.out.println(Arrays.toString(sequence));
                break;
            }

        }
    }
}
