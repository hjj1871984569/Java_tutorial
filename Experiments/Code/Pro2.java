public class Pro2{
	public static void main(String[] argv) {
		final int MAXN = 10000000;
		boolean[] notPrime = new boolean[MAXN];
		int[] prime = new int[MAXN];
		int primeCnt = -1;
		System.out.print("10,000,000以内的最大素数是: ");
		for(int i = 2; i < MAXN; i++) {
			if(notPrime[i] == false) {
				prime[++primeCnt] = i;
			}
			for(int j = 0; j <= primeCnt && prime[j] * i < MAXN; j++) {
				notPrime[prime[j] * i] = true;
				if(i % prime[j] == 0) 
					break;
			}
		}
		System.out.println(prime[primeCnt]);
	}
}