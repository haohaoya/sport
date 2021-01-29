package com.sports.test;

import java.util.Scanner;

public class Main {


    public static void main(String[] args){

        class S{
            public long date;
            public long num;
            public double tem;
            public S(long date,long num,double tem){
                this.date = date;
                this.num = num;
                this.tem = tem;
            }
        }

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = 0;
        S[] a = new S[n];
        for(int i = 0;i<n;i++){
            long date = scanner.nextLong();
            long num = scanner.nextLong();
            double tem = scanner.nextDouble();
            if(tem>=38.0){
                S s = new S(date,num,tem);
                boolean flag = true;
                for(int j = 0;j<m;j++){
                    if(s.date == a[j].date&&s.num == a[j].num){
                        flag = false;
                        if(s.tem>a[j].tem){
                            a[j] = s;
                            break;
                        }
                    }
                }
                if(flag){
                    a[m] = s;
                    m++;
                }
            }
        }

        for(int i = 0;i<m;i++){
            int min = i;
            for(int j = i;j<m;j++){
                if(a[min].num>a[j].num){
                    min = j;
                }
            }
            if(min!=i){
                S temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
        for(int i = 0;i<m;i++){
            int max = i;
            for(int j = i;j<m;j++){
                if(a[max].tem<a[j].tem){
                    max = j;
                }
            }
            if(max!=i){
                S temp = a[max];
                a[max] = a[i];
                a[i] = temp;
            }
        }
        for(int i = 0;i<m;i++){
            int max = i;
            for(int j = i;j<m;j++){
                if(a[max].date<a[j].date){
                    max = j;
                }
            }
            if(max!=i){
                S temp = a[max];
                a[max] = a[i];
                a[i] = temp;
            }
        }
        System.out.println(m);
        for(int i = 0;i<m;i++){
            System.out.println(a[i].date+" "+a[i].num+" "+a[i].tem);
        }


    }
}
