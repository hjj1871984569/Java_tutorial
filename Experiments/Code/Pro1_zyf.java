public class fg {
	public static void main(String argv[]){
		double xl = -10.0 , xh = 5.0 ,n=0,yn,yl,yh;
		for(int i=1;i<=100;i++)
		{
			if(xh-xl<0.0001){break;}
			n=(xh+xl)/2;
			yn=n*n*n-10*n+23;
			yh=xh*xh*xh-10*xh+23;
			yl=xl*xl*xl-10*xl+23;
			if(yn==0){break;}
			if(yn*yh<0){xl=n;}
			if(yl*yn<0){xh=n;}
		}	
		System.out.print(n+""+"\t");
	}
}


public class fg {
	public static void main(String[] args) {
		int m, n,i=2;
		A: for (n = 2; n <= 10000; n++) {
			for (m = 2; m < n / 2; m++) {
				if (n % m == 0)
					continue A;
			}
			i=n;
		}System.out.print(i);
	}
}
