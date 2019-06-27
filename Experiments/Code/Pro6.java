import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
class Calc extends JFrame{
	private CalcListener cl;
	public Calc() {
		this.setSize(300,400);
		this.setTitle("SIMPLE_CALC");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jpanel1 = new JPanel();
		JPanel jpanel2 = new JPanel();
		setLayout(null);
		add(jpanel1);
		add(jpanel2);
		layoutPanel1(jpanel1);
		layoutPanel2(jpanel2);
		this.setVisible(true);
	}
	void layoutPanel1(JPanel panel) {
		panel.setBounds(-5,10,300,50);
		JTextField textField = new JTextField(16);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font("Time_Roman",Font.PLAIN,20));
		textField.setEditable(false);
		textField.setText("0");
		panel.add(textField);
		cl = new CalcListener(textField);
	}
	void layoutPanel2(JPanel panel) {
		panel.setBounds(8,80,270,250);
		panel.setLayout(new GridLayout(5,4,1,1));
		String[] buttonList = {"C","CE"," ","/",
								"1","2","3","*",
								"4","5","6","-",
								"7","8","9","+",
								"0","."," ","="};
		for (int i = 0; i < buttonList.length; i++) {
			JButton button = new JButton(buttonList[i]);
			button.setBorder(BorderFactory.createRaisedBevelBorder());
			button.setFont(new Font("Time_Roman", 0, 25));
			button.addActionListener(cl);
			panel.add(button);
		}
	}

}
class CalcListener implements ActionListener{
	private JTextField textField;
	private String content = "";
	public CalcListener(JTextField textField) {
		this.textField = textField;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "1":case "2":case "3":
		case "4":case "5":case "6":
		case "7":case "8":case "9":
		case "0":case ".":
			content = textField.getText() + command;
			//System.out.println(content);
			textField.setText(content);
			break;
		case "+":case "-":case "*":case "/":
			content = textField.getText();
			String lastChar =content.substring(content.length() - 1); 
			//System.out.println(lastChar);
			if(lastChar.compareTo("+") == 0 || lastChar.compareTo("-") ==0 || 
				lastChar.compareTo("*") == 0 || lastChar.compareTo("/") == 0) {
				content = content.substring(0, content.length() - 1) + command;
				//System.out.println("!");
			}else{ 
				content = content + command;
				//System.out.println("?");
			}
			textField.setText(content);
			break;
			
		case "C":
			textField.setText("0");
			break;
		case "CE":
			content = textField.getText();
			content = content.substring(0, content.length() - 1);
			if(content.length() == 0)
				content = "0";
			textField.setText(content);
			break;
			
		case "=":
			double ans = getAns(this.textField.getText());
			textField.setText(String.valueOf(ans));
			break;
		default:
			break;
		}
	}
	private double getAns(String s) {
		System.out.println(s);
		String[] sAdd = s.split("\\+");
		if(sAdd.length != 1) {
			double sum = 0;
			for(int i = 0; i < sAdd.length; i++) {
				sum += getAns(sAdd[i]);
			}
			return sum;
		}else {
			String[] sSub = s.split("\\-");
			if(sSub.length != 1) {
				double sum = getAns(sSub[0]);
				for(int i = 1; i < sSub.length; i++) {
					sum -= getAns(sSub[i]);
				}
				return sum;
			}else {
				String[] sMul = s.split("\\*");
				if(sMul.length != 1) {
					double sum = 1;
					for(int i = 0; i < sMul.length; i++) {
						sum *= getAns(sMul[i]);
					}
					return sum;
				}else {
					String[] sDiv = s.split("\\/");
					double sum = Double.valueOf(sDiv[0]);
					for(int i = 1; i < sDiv.length; i++) {
						sum /= getAns(sDiv[i]);
					}
					return sum;					
				}
			}
		}
	}
}
public class Pro6{
	public static void main(String[] argv) {
		Calc calc = new Calc();
	}
}