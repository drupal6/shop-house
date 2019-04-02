package shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import shop.Shop;
import shop.bean.User;
import shop.provider.UserProvider;

public class LoginView extends BaseView{
	
	private JLabel usernameLabel;
	private JTextField usernameText;
	private JLabel namealertAlertLabel;
	
	private JLabel passwordLabel;
	private JTextField passwordText;
	private JLabel passwordAlertLabel;
	
	private JButton jButton;
	private Shop shop;
	
	@Override
	public void init(Shop shop) {
		this.shop = shop;
		usernameLabel = new JLabel("用户:");
		usernameText = new JTextField();
		usernameText.setText("admin");
		namealertAlertLabel = new JLabel();
		
		passwordLabel = new JLabel("密码:");
		passwordText = new JPasswordField();
		passwordText.setText("admin");
		passwordAlertLabel = new JLabel();
		
		jButton = new JButton("登录");
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login(shop);
			}
		});
		
		GroupLayout layout = (GroupLayout) shop.getMainPanel().getLayout();
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(jButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(namealertAlertLabel, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordAlertLabel, 0, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE,  30, GroupLayout.PREFERRED_SIZE)
				.addComponent(usernameText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addComponent(namealertAlertLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE,  30, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordAlertLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(jButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				);
		layout.setVerticalGroup(vGroup);
	}
	
	
	private User login(Shop shop) {
		String userName = usernameText.getText().trim();
		String password = passwordText.getText().trim();
		if(userName == null || userName.isEmpty()) {
			namealertAlertLabel.setText("用户名不能为空");
			passwordAlertLabel.setText("");
			return null;
		}
		if(password == null || password.isEmpty()) {
			namealertAlertLabel.setText("");
			passwordAlertLabel.setText("密码不能为空");
			return null;
		}
		User user = UserProvider.getInst().getUser(userName);
		if(user == null) {
			namealertAlertLabel.setText("用户不存在");
			passwordAlertLabel.setText("");
			return null;
		}
		if(false == user.getPassword().equals(password)) {
			namealertAlertLabel.setText("");
			passwordAlertLabel.setText("密码不正确");
			return null;
		}
		shop.loginSuccess(user);
		return user;
	}


	@Override
	public String getTitle() {
		return "登录";
	}
	
	@Override
	public JPanel getMainPanel() {
		return null;
	}
}
