package com.daxin.mx;

import java.util.Arrays;
/**
 * 
 * 矩阵乘法次数最少的动态规划实现
 * 
 * @author 青春常驻Dax1n
 *
 */
public class MX {

	public static void main(String[] args) {

		int[] mx = { 30, 35, 15, 5, 10, 20 };// 11875
		/*
		 * mx = { 30, 35, 15, 5, 10, 20 } 组成了5个矩阵， 矩阵0为：[30*35] 矩阵1为：[35*15]
		 * 矩阵2为：[15*5] 矩阵3为：[5*10] 矩阵4为：[10*20]
		 */
		getMinResult(mx);

	}

	/**
	 * 
	 * 
	 * 
	 * @param mx
	 */
	public static void getMinResult(int[] mx) {

		int[][] res = new int[mx.length][mx.length];// 保存子问题结果的，索引值使用1开始
		int[][] loc = new int[mx.length][mx.length];// 保存分割位置的，索引值使用1开始
		for (int i = 0; i < mx.length; i++) {
			Arrays.fill(res[i], Integer.MAX_VALUE);
			res[i][i] = 0;
			Arrays.fill(loc[i], -1);
		}

		// 链长2-mx.length

		// i表示当前子问题的规模
		for (int i = 2; i <= mx.length - 1; i++) {

			// 确定左边界j和确定右边界
			for (int j = 1; j <= mx.length - i; j++) {

				// mx中的矩阵i是由mx[i-1]和mx[i]两个数字组成的维度
				int l = j;// l代表左边界
				int r = j + i - 1;// r代表右边界

				// 30*35*15=15750
				res[l][r] = res[l + 1][r] + mx[l - 1] * mx[l] * mx[r];// 第一个假设切分的位置的当前乘法次数

				loc[l][r] = j;// 在矩阵j位置切分是将j划分为前面一组，就是在j矩阵后面分开

				// 遍历可以切分的位置
				for (int k = j + 1; k < r; k++) {
					int currentCounter = res[j][k] + res[k + 1][r] + mx[l]
							* mx[k] * mx[r];

					if (currentCounter < res[j][r]) {
						res[j][r] = currentCounter;
						loc[j][r] = k;
					}

				}

			}

		}

		System.out.println("===================打印记录的次数数组res:=====================");
		for (int i = 1; i < mx.length; i++) {
			for (int j = 1; j < mx.length; j++) {
				if (2147483647 == res[i][j]) {
					System.out.print(-1 + "           ");
				} else {
					System.out.print(res[i][j] + "           ");
				}

			}
			System.out.println();
		}

		System.out.println("===================打印记录的分隔位置的数组loc:=====================");
		for (int i = 1; i < mx.length; i++) {
			for (int j = 1; j < mx.length; j++) {
				if (2147483647 == res[i][j]) {
					System.out.print(0 + "   ");
				} else {
					System.out.print(loc[i][j] + "   ");
				}

			}
			System.out.println();
		}

		System.out.println("根据loc矩阵寻找最优解：");
		System.out
				.println("思路：n个矩阵的求解问题，首先把n个矩阵看成一个子问题，找出最优秀的分割位置，为k。\r\n此时k将n个矩阵的问题一份为2了，再然后将两个子问题递归即可");
//		loc
		System.out.println();
		System.out.println();
		System.out.println("矩阵个数为： "+(mx.length-1));
		for(int i=0;i<mx.length-1;i++){
			System.out.println("矩阵"+i+"为：["+mx[i]+"*"+mx[i+1]+"]");
		}
		
		
		System.out.println("矩阵乘法顺序为："+getOrder(loc, 1, 5));
		
		
		
		
		
		
		
	}
	
	/**
	 * 
	 * 求矩阵的计算顺序表达式
	 * 
	 * @param mx
	 * @param start  
	 * @param end
	 * @return
	 */
	public static String getOrder(int[][] mx,int start,int end){
		
		if(start+1==end){
			return "("+start+"*"+end+")";
		}
		if(start==end){
			return start+"";
		}
		if(end==-1){
			System.out.println();
		}
		int separator=mx[start][end];
		if(separator==-1){
			System.out.println();
			
		}
		String left=getOrder(mx, start, separator);
		String right=getOrder(mx, separator+1, end);
		
		
		return  "("+left+"*"+right+")";
	}
	
	

}
