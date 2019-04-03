package shop;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.bean.User;
import shop.db.pool.DBPoolMgr;
import shop.provider.DataInit;
import shop.view.BaseView;
import shop.view.LoginView;
import shop.view.sale.SaleView;

public class Shop extends JFrame{

	private static final long serialVersionUID = 1L;

	private static Shop instance = new Shop();
		
	public static Shop getInst() {
		return instance;
	}
	
	private User user;
	
	private JPanel mainPanel;
	
	private Dimension dimension;
	
	private BaseView curView;
	
	private void initConfig() {
		if(false == Setting.getInst().init()) {
			System.exit(0);
		}
		DBPoolMgr.getInst().init(Setting.getInst(), 10, 1);
		DataInit.getInst().initData();
	}
	
	private void initView() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
		dimension = new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height * 0.8));
		this.setSize(dimension);
		//不能改变大小
		this.setResizable(false);
		mainPanel = new JPanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
	}
	
	public User getUser() {
		return user;
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void loginSuccess(User user) {
		this.user = user;
		changeView(new SaleView());
	}
	
	public void changeView(BaseView baseView) {
		if(this.curView != null) {
			this.curView.remove();
		}
		setTitle(baseView.getTitle());
		baseView.init(this);
		this.mainPanel.validate();
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.curView = baseView;
	}

	public static void main(String[] args) {
		Shop.getInst().initConfig();
		Shop.getInst().initView();
		Shop.getInst().changeView(new LoginView());
	}
}
