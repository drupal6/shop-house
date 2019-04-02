package shop.view.manage;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import shop.Shop;
import shop.view.BaseView;

public class ManageFrame extends BaseView {
	
	private static ManageFrame instance = new ManageFrame();
	
	public static ManageFrame getInst() {
		return instance;
	}
	
	//菜单
	private ManageMenuBar manageMenuBar;
	//主面板
	private JPanel mainPanel;
	//副面板
	private JPanel secondPanel;
	//2级标题
	private SecondTitle secondTitle;
	private Shop shop;
	
	@Override
	public void init(Shop shop) {
		this.shop = shop;
		this.mainPanel = shop.getMainPanel();
//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
//		setSize(new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8)));
		//不能改变大小
//		setResizable(false);
		//添加主菜单
		manageMenuBar = new ManageMenuBar();
		shop.setJMenuBar(manageMenuBar);
		
		//初始主面板
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		refreshPanel(manageMenuBar.getDefaMenuText(), new ProductTypePanel(manageMenuBar.getDefaMenuText()));
	}
	
	public void refreshPanel(String title, JPanel addPanel) {
		mainPanel.removeAll();
		secondTitle = null;
		secondTitle = new SecondTitle(title);
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		secondPanel = addPanel;
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(secondTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
		shop.setContentPane(mainPanel);
		shop.setVisible(true);
	}
	
	
	public ManageMenuBar getManageMenuBar() {
		return manageMenuBar;
	}

//	public static void main(String[] args) throws InterruptedException {
//		//初始数据
//		Setting.getInst().init();
//		DBPoolMgr.getInst().init(Setting.getInst(), 10, 1);
//		DataInit.getInst().initData();
//		ManageFrame.getInst().init();
//	}

	@Override
	public String getTitle() {
		return "管理";
	}
	
	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
