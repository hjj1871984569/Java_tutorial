import java.io.*;
public class one {
	private static double f(double x){
		return x * x * x - 10 * x + 23;
	}
	private static double my_abs(double x) {
		return x > 0 ? x : -x;
	}
	private static double ef(double x_low, double x_high, double delta) {
		double x_c = 0;
		do{
			x_c = (x_high + x_low) / 2;
			if(f(x_c) == 0) return x_c;
			if(f(x_high) * f(x_c) < 0) x_low  = x_c;
			if(f(x_low)  * f(x_c) < 0) x_high = x_c;
		}while(my_abs(x_high - x_low) > delta);
		return x_c;
	}
	public static void main(String argv[]) throws IOException{
		PrintWriter cout = new PrintWriter(new OutputStreamWriter(System.out));
		double x_low = -10.0, x_high = 5.0, delta = 0.001;
		if(f(x_high) * f(x_low) < 0) {
			double x_c = ef(x_low, x_high, delta);
			cout.printf("%.3f",x_c);
		}
		cout.flush();
	}
}
