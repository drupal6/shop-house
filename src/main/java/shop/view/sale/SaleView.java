package shop.view.sale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import shop.Setting;
import shop.Shop;
import shop.barcode.BarcodeCallBack;
import shop.barcode.BarcodeProvider;
import shop.dao.DaoFactory;
import shop.db.pool.DBPoolMgr;
import shop.provider.DataInit;
import shop.view.BaseView;

public class SaleView extends BaseView {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private JLayeredPane jlp;
	
	private JPanel mainPanel;
	
	private JPanel dialogPanel;
	
	private OrderPanel orderPanel;
	private OptionPanel optionPanel;
	private ProductSaleSelectPanel productSelectPanel;
	
	private Shop shop;
	
	public void init(Shop shop) {
		this.shop = shop;
		this.mainPanel = shop.getMainPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Dimension dimension = new Dimension((int)(screenSize.width * 0.8),(int)(screenSize.height*0.8));
		
		orderPanel = new OrderPanel(this);
		optionPanel = new OptionPanel(this);
		productSelectPanel = new ProductSaleSelectPanel(this);
		
		
		mainPanel = new JPanel();
		mainPanel.setSize(dimension);
		mainPanel.setBackground(new Color(47, 47, 47));
		
		dialogPanel = new JPanel(); 
		dialogPanel.setSize(dimension);
		dialogPanel.setOpaque(false);
		dialogPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(orderPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(layout.createParallelGroup().addComponent(optionPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE));
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(productSelectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(orderPanel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(optionPanel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(productSelectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
		jlp = shop.getLayeredPane();
		jlp.add(mainPanel, new Integer(300));
		jlp.add(dialogPanel, new Integer(200));
		shop.setVisible(true);
		
		BarcodeProvider.getInst().start(new BarcodeCallBack() {
			
			@Override
			public void scan(String barcode) {
				orderPanel.getProductOrderListPanel().addProductOrder(barcode);
			}
			
			@Override
			public void finish() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void stop() {
		BarcodeProvider.getInst().stop();
	}
	
	public void setLayerPanel(int mainLayer, int dialogLayer) {
		jlp.setLayer(mainPanel, new Integer(mainLayer)); // 重新设置组件层数
		jlp.setLayer(dialogPanel, new Integer(dialogLayer));
		if(mainLayer > dialogLayer) {
			dialogPanel.removeAll();
		}
	}

	public OrderPanel getOrderPanel() {
		return orderPanel;
	}

	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public JPanel getDialogPanel() {
		return dialogPanel;
	}
	
	public void dialogPanelAddPanel(JPanel panel) {
		dialogPanel.removeAll();
		GroupLayout layout = new GroupLayout(dialogPanel);
		dialogPanel.setLayout(layout);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap((int) ((dialogPanel.getWidth() - panel.getPreferredSize().getWidth())/2));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap((int) ((dialogPanel.getHeight() - panel.getPreferredSize().getHeight())/2));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(panel, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(vGroup);
		dialogPanel.updateUI();
	}
	
	public static void main(String[] args) throws InterruptedException {
		//初始数据
		Setting.getInst().init();
		DBPoolMgr.getInst().init(Setting.getInst(), 1, 1);
		DataInit.getInst().initData();
		Shop.getInst().loginSuccess(DaoFactory.getInst().getUserDao().getUser("admin"));
//		SaleView.getInst().init();
	}

	@Override
	public String getTitle() {
		return "销售";
	}
}
