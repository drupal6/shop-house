package shop.view.sale;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.provider.DataInit;

public class SaleFrame extends JFrame {
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
	private OrderPanel orderPanel;
	private OptionPanel optionPanel;
	private ProductSelectPanel productSelectPanel;
	
	private static SaleFrame instance = new SaleFrame();
	
	public static SaleFrame getInst() {
		return instance;
	}
	
	public void init() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
		setSize(new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8)));
		//不能改变大小
		setResizable(false);
		
		orderPanel = new OrderPanel();
		optionPanel = new OptionPanel();
		productSelectPanel = new ProductSelectPanel();
		
		mainPanel = new JPanel();
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(orderPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(layout.createParallelGroup().addComponent(optionPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(layout.createParallelGroup().addComponent(productSelectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(orderPanel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(optionPanel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(productSelectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	

	public static void main(String[] args) throws InterruptedException {
		//初始数据
		DataInit.getInst().initConnect();
		DataInit.getInst().initData();
		SaleFrame.getInst().init();
	}
}
