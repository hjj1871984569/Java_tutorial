import java.util.*;
import java.io.*;
class KMeans{
	class Point{
		Point () { x = 0; y = 0;}
	    Point(int x, int y) {
	    	this.x = x;
	    	this.y = y;
	    }
	    double x, y;
	}
	private String fileName;
	private int k;
	private double eps;
	
	private int size;
	private Point point[] = new Point[100];
	private Point means[] = new Point[100];
	private int divn[] = new int[100];
	private int div[][] = new int[100][100];
	
	KMeans(String fileName) throws FileNotFoundException{
		this.fileName = fileName;
		File file = new File(fileName);
		Scanner cin = new Scanner(file);
		
		size = 0;
		while(cin.hasNextDouble()) {
			point[size] = new Point();
			point[size].x = cin.nextDouble();
			point[size].y = cin.nextDouble();
			size++;
		}
		cin.close();
	}
	public void dealDate(int k, double eps) {
		this.k = k;
		this.eps = eps;
		for(int i = 0; i < this.k; i++) {
			means[i] = new Point();
			means[i].x = point[i].x;
			means[i].y = point[i].y;
		}
		
		while(divide());
		
		System.out.printf("%s (k = %d, eps = %f)\n",fileName, k, eps);
		for (int i = 0; i < k; i++)
			System.out.printf("\t(%f, %f)\n", means[i].x, means[i].y);
	}

	private static double getDis(Point a, Point b){
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	private boolean divide() {
		boolean ret = false;
		for(int i = 0; i < k; i++)
			divn[i] = 0;
		for(int i = 0; i < size; i++) {
			double minDis = getDis(point[i], means[0]);
			int minP = 0;
			for(int j = 1;j < k; j++) {
				double dis = getDis(point[i], means[j]);
				if(dis < minDis) {
					minDis = dis;
					minP = j;
				}
			}
			div[minP][divn[minP]++] = i;
		}
		for (int i = 0; i < k; i++) {
			Point tmp = new Point(0, 0);
			for (int j = 0; j < divn[i]; j++) {
				tmp.x += point[div[i][j]].x;
				tmp.y += point[div[i][j]].y;
			}
			tmp.x = tmp.x / divn[i];
			tmp.y = tmp.y / divn[i];
			if (getDis(tmp, means[i]) > eps)
				ret = true;
			means[i].x = tmp.x;
			means[i].y = tmp.y;
		}
		return ret;
	}
}

public class Pro3{
	public static void main(String[] argv) {
		try{
			KMeans sol1 = new KMeans("KMeans_Set.txt");
			sol1.dealDate(5, 0.000001);
			sol1.dealDate(6, 0.000001);
			KMeans sol2 = new KMeans("KMeans_Set2.txt");
			sol2.dealDate(6, 0.000001);
			sol2.dealDate(7, 0.000001);
		}catch(FileNotFoundException e) {
			System.out.println("Pro3 : " + e.getMessage());
		}
	}
}
