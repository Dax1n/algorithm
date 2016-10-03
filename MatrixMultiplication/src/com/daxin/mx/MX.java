package com.daxin.mx;

import java.util.Arrays;
/**
 * 
 * ����˷��������ٵĶ�̬�滮ʵ��
 * 
 * @author �ഺ��פDax1n
 *
 */
public class MX {

	public static void main(String[] args) {

		int[] mx = { 30, 35, 15, 5, 10, 20 };// 11875
		/*
		 * mx = { 30, 35, 15, 5, 10, 20 } �����5������ ����0Ϊ��[30*35] ����1Ϊ��[35*15]
		 * ����2Ϊ��[15*5] ����3Ϊ��[5*10] ����4Ϊ��[10*20]
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

		int[][] res = new int[mx.length][mx.length];// �������������ģ�����ֵʹ��1��ʼ
		int[][] loc = new int[mx.length][mx.length];// ����ָ�λ�õģ�����ֵʹ��1��ʼ
		for (int i = 0; i < mx.length; i++) {
			Arrays.fill(res[i], Integer.MAX_VALUE);
			res[i][i] = 0;
			Arrays.fill(loc[i], -1);
		}

		// ����2-mx.length

		// i��ʾ��ǰ������Ĺ�ģ
		for (int i = 2; i <= mx.length - 1; i++) {

			// ȷ����߽�j��ȷ���ұ߽�
			for (int j = 1; j <= mx.length - i; j++) {

				// mx�еľ���i����mx[i-1]��mx[i]����������ɵ�ά��
				int l = j;// l������߽�
				int r = j + i - 1;// r�����ұ߽�

				// 30*35*15=15750
				res[l][r] = res[l + 1][r] + mx[l - 1] * mx[l] * mx[r];// ��һ�������зֵ�λ�õĵ�ǰ�˷�����

				loc[l][r] = j;// �ھ���jλ���з��ǽ�j����Ϊǰ��һ�飬������j�������ֿ�

				// ���������зֵ�λ��
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

		System.out.println("===================��ӡ��¼�Ĵ�������res:=====================");
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

		System.out.println("===================��ӡ��¼�ķָ�λ�õ�����loc:=====================");
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

		System.out.println("����loc����Ѱ�����Ž⣺");
		System.out
				.println("˼·��n�������������⣬���Ȱ�n�����󿴳�һ�������⣬�ҳ�������ķָ�λ�ã�Ϊk��\r\n��ʱk��n�����������һ��Ϊ2�ˣ���Ȼ������������ݹ鼴��");
//		loc
		System.out.println();
		System.out.println();
		System.out.println("�������Ϊ�� "+(mx.length-1));
		for(int i=0;i<mx.length-1;i++){
			System.out.println("����"+i+"Ϊ��["+mx[i]+"*"+mx[i+1]+"]");
		}
		
		
		System.out.println("����˷�˳��Ϊ��"+getOrder(loc, 1, 5));
		
		
		
		
		
		
		
	}
	
	/**
	 * 
	 * �����ļ���˳����ʽ
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
