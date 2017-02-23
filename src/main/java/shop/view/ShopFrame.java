package shop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.provider.DataInit;

public class ShopFrame extends JFrame {
	
	private static ShopFrame instance = new ShopFrame();
	
	public static ShopFrame getInst() {
		return instance;
	}
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	//菜单
	private ManageMenuBar manageMenuBar;
	//主面板
	private JPanel mainPanel;
	//副面板
	private JPanel secondPanel;
	//2级标题
	private SecondTitle secondTitle;
	
	public void init() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
		setSize(new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8)));
		//不能改变大小
		setResizable(false);
		//添加主菜单
		manageMenuBar = new ManageMenuBar();
		setJMenuBar(manageMenuBar);
		
		//初始主面板
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		refreshPanel(manageMenuBar.getDefaMenuText(), new ProductTypePanel());
	}
	
	public void refreshPanel(String title, JPanel addPanel) {
		secondTitle = null;
		secondTitle = new SecondTitle(title);
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		secondPanel = addPanel;
		secondPanel.setBackground(Color.RED);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(secondTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		//初始数据
		DataInit.getInst().initConnect();
		DataInit.getInst().initData();
		ShopFrame.getInst().init();
	}
}
