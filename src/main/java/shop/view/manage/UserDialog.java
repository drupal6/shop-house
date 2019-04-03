package shop.view.manage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.Constance;
import shop.Shop;
import shop.bean.User;
import shop.provider.UserProvider;

public class UserDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int op = 0;
	private int id;
	private int selectIndex;
	
	private JPanel conentPanel;
	private JPanel buttonPanel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel passwordLabel;
	private JTextField passwordTextField;
	private JLabel nickNameLabel;
	private JTextField nickNameTextField;
	private JLabel adminLabel;
	private JTextField adminTextField;
	private JLabel stateLabel;
	private JTextField stateTextField;
	private JButton saveButton;
	private JButton saveAndAddButton;
	private JButton cannelButton;
	private MyTable table;
	
	public UserDialog(MyTable table, int op, String title) { 
		this.table = table;
		this.op = op;
		this.setTitle(title);
		this.setSize(300, 270);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		Point topLeft = Shop.getInst().getLocationOnScreen();
		Dimension parentSize = Shop.getInst().getSize();
		Dimension mySize = this.getSize();
		int x, y;
		if (parentSize.width > mySize.width) {
			x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
		} else {
			x = topLeft.x;
		}
		if (parentSize.height > mySize.height) {
			y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
		} else {
			y = topLeft.y;
		}
		this.setLocation(x, y);
		
		conentPanel = new JPanel();
		conentPanel.setBackground(Color.WHITE);
		nameLabel = new JLabel("账号");
		nameLabel.setFont(Constance.font22);
		nameTextField = new JTextField();
		nameTextField.setFont(Constance.font22);
		
		passwordLabel = new JLabel("密码");
		passwordLabel.setFont(Constance.font22);
		passwordTextField = new JTextField();
		passwordTextField.setFont(Constance.font22);
		
		nickNameLabel = new JLabel("名称");
		nickNameLabel.setFont(Constance.font22);
		nickNameTextField = new JTextField();
		nickNameTextField.setFont(Constance.font22);
		
		adminLabel = new JLabel("管理员");
		adminLabel.setFont(Constance.font22);
		adminTextField = new JTextField();
		adminTextField.setFont(Constance.font22);
		
		stateLabel = new JLabel("状态");
		stateLabel.setFont(Constance.font22);
		stateTextField = new JTextField();
		stateTextField.setFont(Constance.font22);
		
		GroupLayout conentLayout = new GroupLayout(conentPanel);
		conentPanel.setLayout(conentLayout);
		GroupLayout.SequentialGroup cHGroup = conentLayout.createSequentialGroup();
		cHGroup.addGap(20);
		cHGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nameLabel)
				.addComponent(passwordLabel)
				.addComponent(nickNameLabel)
				.addComponent(adminLabel)
				.addComponent(stateLabel)
		);
		cHGroup.addGap(10);
		cHGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(nickNameTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(adminTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(stateTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
		);
		conentLayout.setHorizontalGroup(cHGroup);
		
		GroupLayout.SequentialGroup cVGroup = conentLayout.createSequentialGroup();
		cVGroup.addGap(20);
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nameLabel)
				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(passwordLabel)
				.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nickNameLabel)
				.addComponent(nickNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(adminLabel)
				.addComponent(adminTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(stateLabel)
				.addComponent(stateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		conentLayout.setVerticalGroup(cVGroup);
		
		buttonPanel = new JPanel();
		saveButton = new JButton("保存"); 
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(save()) {
					dispose();
				}
			}
		});
		saveAndAddButton = new JButton("保存并添加"); 
		saveAndAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndAdd();
			}
		});
		cannelButton = new JButton("取消"); 
		cannelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cannel();
			}
		});
		
		GroupLayout buttonLayout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(buttonLayout);
		
		GroupLayout.SequentialGroup bHGroup = buttonLayout.createSequentialGroup();
		bHGroup.addGap(30);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveButton));
		bHGroup.addGap(10);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveAndAddButton));
		bHGroup.addGap(10);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(cannelButton));
		buttonLayout.setHorizontalGroup(bHGroup);
		
		GroupLayout.SequentialGroup bVGroup = buttonLayout.createSequentialGroup();
		bVGroup.addGap(20);
		bVGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveButton)
				.addComponent(saveAndAddButton)
				.addComponent(cannelButton));
		buttonLayout.setVerticalGroup(bVGroup);
		
		GroupLayout framLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(framLayout);
		
		GroupLayout.SequentialGroup fHGroup = framLayout.createSequentialGroup();
		fHGroup.addGroup(framLayout.createParallelGroup()
				.addComponent(conentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		framLayout.setHorizontalGroup(fHGroup);
		
		GroupLayout.SequentialGroup fVGroup = framLayout.createSequentialGroup();
		fVGroup.addGroup(framLayout.createParallelGroup().addComponent(conentPanel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		fVGroup.addGroup(framLayout.createParallelGroup().addComponent(buttonPanel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		framLayout.setVerticalGroup(fVGroup);
		
		if(op == 2) {
			saveAndAddButton.setEnabled(false);
			selectIndex = table.getTable().getSelectedRow();
			id = (Integer) table.getTable().getValueAt(selectIndex, 0);
			User user = UserProvider.getInst().getUser(id);
			if(user != null) {
				nameTextField.setText(user.getName());
				passwordTextField.setText(user.getPassword());
				nickNameTextField.setText(user.getNickName());
				adminTextField.setText(String.valueOf(user.getAdmin()));
				stateTextField.setText(String.valueOf(user.getState()));
			}
		}
	}
	
	private boolean save() {
		String name = nameTextField.getText().trim();
		String password = passwordTextField.getText().trim();
		String nickName = nickNameTextField.getText().trim();
		String adminStr = adminTextField.getText().trim();
		String stateStr = stateTextField.getText().trim();
		if(name.isEmpty()) {
			return false;
		}
		if(password.isEmpty()) {
			return false;
		}
		if(nickName.isEmpty()) {
			return false;
		}
		if(adminStr.isEmpty()) {
			return false;
		}
		if(stateStr.isEmpty()) {
			return false;
		}
		int admin = Integer.parseInt(adminStr);
		int state = Integer.parseInt(stateStr);
		if(op == 1) {
			User user = UserProvider.getInst().getUser(name);
			if(user != null) {
				return false;
			}
			user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setNickName(nickName);
			user.setAdmin(admin);
			user.setState(state);
			if(UserProvider.getInst().add(user)) {
				table.getTableModel().addRow(UserProvider.getValue(user));
			}
			return true;
		}else {
			User user = UserProvider.getInst().getUser(id);
			if(user == null) {
				return false;
			}
			User user1 = UserProvider.getInst().getUser(name);
			if(user1 != null) {
				return false;
			}
			user.setName(name);
			user.setPassword(password);
			user.setNickName(nickName);
			user.setAdmin(admin);
			user.setState(state);
			UserProvider.getInst().update(user);
			table.getTable().setValueAt(name, selectIndex, 1);
			dispose();
			return true;
		}
	}
	
	private void saveAndAdd() {
		if(save()) {
			nameTextField.setText("");
		}
	}
	
	private void cannel() {
		this.dispose();
	}
}
