import java.io.*;
import java.sql.*;
import java.util.Scanner;
class MySql{
	final static String JDBC_DRIVER  = "com.mysql.cj.jdbc.Driver"; 
	final static String DB_URL = "jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
	final static String DB_USER = "root";
	final static String DB_PASSWD = "";
	Connection con;
	Statement stm;
	public MySql() {
		init();
	}
	public void init() {
		try {
			Class.forName(JDBC_DRIVER);

			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			
			stm = con.createStatement();
			
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("class forName is Error:" + e.getMessage());
		}catch(SQLException e) {
			System.out.println("getConnection is Error: " + e.getMessage());
		}
	}
	public boolean createStudentTable() {
		boolean fg = false;
		try{
			String exec = "create table student(" + 
						"ID char(12) not null," + 
						"name char(10) not null," +
						"age int(3) not null)" +
						"engine=InnoDB default charset=latin1";
			fg = stm.execute(exec);
		}catch(SQLException e) {
			System.out.println("createStudentTable:" + e.getMessage());
			return false;
		}
		return fg;
	}
	public boolean query(String ID) {
		try {
			String exec = "select * " +
							"from student " +
							"where ID=" + ID;
			ResultSet ret = stm.executeQuery(exec);
			ret.beforeFirst();
			String name;
			int age;
			for(;ret.next();) {
				name = ret.getString("name");
				age = Integer.valueOf(ret.getString("age"));
				System.out.printf("\t name: %s\n \t age: %d\n",name,age);
			}
		}catch(SQLException e) {
			System.out.println("query is Error:" + e.getMessage());
			return false;
		}
		return true;
	}
	public boolean insert(String ID, String name, int age) {
		try {
			String exec = "insert into student values(" +
							"'" + ID +"'," + 
							"'" + name + "'," +
							"'" + age + "')";
			//System.out.println(exec+"\n");
			stm.executeUpdate(exec);
			System.out.println("query(" + exec + ")is : successful");
		}catch(SQLException e) {
			System.out.println("query is Error:" + e.getMessage());
			return false;
		}
		
		return true;		
	}
	public boolean update(String ID, String name, int age) {
		try {
			String exec = "update student set " +
							"name='" + name + "', " +
							"age='" + age + "' " +
							"where id=" + ID;
			//System.out.println(exec+"\n");
			stm.executeUpdate(exec);
			System.out.println("query(" + exec + ")is : successful");
		}catch(SQLException e) {
			System.out.println("query is Error:" + e.getMessage());
			return false;
		}
		return true;		
	}
	public boolean delete(String ID) {
		try {
			String exec = "delete from student where id=" + ID;
			stm.executeUpdate(exec);
			System.out.println("query(" + exec + ")is : successful");
		}catch(SQLException e) {
			System.out.println("query is Error:" + e.getMessage());
			return false;
		}
		return true;		
	}
}
public class Pro7{
	public static void main(String[] argv) throws IOException{
		MySql sql = new MySql();
		Scanner cin = new Scanner(System.in);
		for(;true;) {
			System.out.println("请输入要执行的操作");
			System.out.println("* 1.创建student表");
			System.out.println("* 2.根据学号查询学生姓名和年龄");
			System.out.println("* 3.给定学生学号、姓名、年龄，在表里追加一行信息");
			System.out.println("* 4.给定学生学号，在表里删除该学生信息");
			System.out.println("* 5.给定学生学号、姓名、年龄，在表里修改一行信息");
			int op = cin.nextInt();
			String ID, name; int age;
			switch(op) {
			case 1: sql.createStudentTable();break;
			case 2: 
				System.out.print("请输入学号： ");
				ID = cin.next(); 
				sql.query(ID);
				break;
			case 3:
				System.out.print("请输入学号： ");
				ID = cin.next(); 
				System.out.print("请输入姓名： ");
				name = cin.next(); 
				System.out.print("请输入年龄： ");
				age = cin.nextInt(); 
				sql.insert(ID,name,age);
				break;
			case 4:
				System.out.print("请输入学号： ");
				ID = cin.next(); 
				sql.delete(ID);
				break;
			case 5:
				System.out.print("请输入学号： ");
				ID = cin.next(); 
				System.out.print("请输入姓名： ");
				name = cin.next(); 
				System.out.print("请输入年龄： ");
				age = cin.nextInt(); 
				sql.update(ID,name,age);
				break;	
			default:
				System.out.println("操作参数错误！");
			}
		}
		
	}
}