import java.io.*;
class Student{
	final int MAXN = 100;
	int size;
	int id[], score[];
	Student(){
		size = 0;
		id = new int[MAXN];
		score = new int[MAXN];
	}
	void append(int id,int score) {
		this.id[this.size] = id;
		this.score[this.size] = score;
		++this.size;
	}
	int getMax() {
		int maxSorce = score[0];
		for(int i = 1; i < size; i++)
			if(score[i] > maxSorce)
				maxSorce = score[i];
		return maxSorce;
	}
	int getMin() {
		int minSorce = score[0];
		for(int i = 1; i < size; i++)
			if(score[i] < minSorce)
				minSorce = score[i];
		return minSorce;
	}
	double getAverage() {
		double ave = 0;
		for(int i = 0; i < size; i++)
			ave += score[i];
		ave /= size;
		return ave;
	}
	int getAToB(int a,int b) {
		int sum = 0;
		for(int i = 0; i < size; i++)
			if(a <= score[i] && score[i] <= b)
				++sum;
		return sum;
	}
}
public class Pro5{
	public static void main(String[] agrv) throws IOException{
		BufferedReader cin = new BufferedReader(new FileReader(new File("score.csv")));
		Student stu = new Student();
		while(cin.ready()){
			String s = cin.readLine();
			String[] tmp = s.split(";");
			stu.append(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]));
		}
		PrintWriter cout = new PrintWriter(new FileWriter(new File("score.txt")));
		cout.println("最高成绩：" + stu.getMax());
		cout.println("最低成绩：" + stu.getMin());
		cout.printf("平均成绩：%.5f" , stu.getAverage()); cout.println("");
		cout.println("成绩在[60,69]的人数：" + stu.getAToB(60,69));
		cout.println("成绩在[70,79]的人数：" + stu.getAToB(70,79));
		cout.println("成绩在[80,89]的人数：" + stu.getAToB(80,89));
		cout.println("成绩在[00,100]的人数：" + stu.getAToB(90,100));
		cout.flush();
		cout.close();
	}
}
