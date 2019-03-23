package shop.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame{

	private JPanel mainPanel;
	
	private JLabel usernameLabel;
	private JTextField usernameText;
	
	private JLabel passwordLabel;
	private JTextField passwordText;
	
	private JButton jButton;
	
	public void init() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
		setSize(new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8)));
		//不能改变大小
		setResizable(false);
		
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		usernameLabel = new JLabel("用户");
		usernameText = new JTextField();
		
		passwordLabel = new JLabel("密码");
		passwordText = new JTextField();
		
		jButton = new JButton("登录");
		
		
		this.setVisible(true);
	}
}
