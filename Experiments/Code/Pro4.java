import java.io.*;
public class Pro4{
	public static void sol(String fileName) throws IOException{
		BufferedReader cin = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		int num = 0;
		double sumXY = 0, sumXX = 0, aveX = 0, aveY = 0;
		while(cin.ready()) {
			String tmp = cin.readLine();
			String[] vec = tmp.split("\t");
			double x = Double.valueOf(vec[1]);
			double y = Double.valueOf(vec[2]);
			++num;
			sumXY += x * y;
			sumXX += x * x;
			aveX += x;
			aveY += y;
		}
		cin.close();
		aveX /= num;
		aveY /= num;
		double A = (sumXY - aveX * aveY) / (sumXX - aveX * aveX); 
		double B = (aveY - A * aveX);
		System.out.printf("%s:\n  A = %.7f\n  B = %.7f\n", fileName, A, B);
	}
	public static void main(String[] argv) throws IOException{
		sol("LR_ex0.txt");
		sol("LR_ex1.txt");
	}
}