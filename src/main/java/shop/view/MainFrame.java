package shop.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	/**
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame(String title) {
		super(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//适应屏幕大小
		setSize(new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8)));
		//不能改变大小
		setResizable(false);
		//不要标题栏
//		setUndecorated(true);
		//全屏
//		getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		
		//设置菜单
		
		this.setVisible(true);
	}
	
	/**
	 * 添加panel
	 * @param panel
	 */
	public void addPanel(JPanel panel) {
		this.getContentPane().add(panel);
		this.validate();
	}
	
	/**
	 * 移除panel
	 * @param panel
	 */
	public void removePanel(JPanel panel) {
		this.getContentPane().remove(panel);
		this.validate();
	}
	
	public void addMenuBar(JMenuBar bar) {
		setJMenuBar(bar);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MainFrame mainFrame = new MainFrame("title");
		mainFrame.addMenuBar(new ManageMenuBar());
		MainPanel panel = new MainPanel();
		mainFrame.addPanel(panel);
	}
}
