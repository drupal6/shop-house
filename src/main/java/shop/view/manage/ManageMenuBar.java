package shop.view.manage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import shop.Constance;

public class ManageMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	private JMenu baseDataMenu;
	private JMenuItem productTypeMenuItem;
	private JMenuItem productUnitMenuItem;
	private JMenuItem productInfoMenuItem;
	
	private JMenu saleMenu;
	private JMenuItem querySaleOrderMenuItem;
	private JMenuItem queryPorductSaleMenuItem;
	
	
	private JMenu cashMenu;
	private JMenuItem queryCashMenuItem;
	
	public ManageMenuBar() {
		this.add(createBaseMenu());
		this.add(createSaleMenu());
		this.add(createCashMenu());
	}
	
	public String getDefaMenuText() {
		return productUnitMenuItem.getText();
	}
	
	/**
	 * 创建基础数据菜单
	 * @return
	 */
	private JMenu createBaseMenu() {
		baseDataMenu = new JMenu();
		baseDataMenu.setText("基础数据");
		baseDataMenu.setFont(Constance.font24);
		
		productTypeMenuItem = new JMenuItem();
		productTypeMenuItem.setText("商品类别");
		productTypeMenuItem.setFont(Constance.font21);
		productTypeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(productTypeMenuItem.getText(),  new ProductTypePanel(productTypeMenuItem.getText()));
			}
		});
		baseDataMenu.add(productTypeMenuItem);
		
		productUnitMenuItem = new JMenuItem();
		productUnitMenuItem.setText("商品单位");
		productUnitMenuItem.setFont(Constance.font21);
		productUnitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(productUnitMenuItem.getText(),  new ProductUnitPanel(productUnitMenuItem.getText()));
			}
		});
		baseDataMenu.add(productUnitMenuItem);
		
		productInfoMenuItem = new JMenuItem();
		productInfoMenuItem.setText("商品信息");
		productInfoMenuItem.setFont(Constance.font21);
		productInfoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(productInfoMenuItem.getText(),  new ProductPanel(productInfoMenuItem.getText()));
			}
		});
		baseDataMenu.add(productInfoMenuItem);
		
		return baseDataMenu;
	}
	
	/**
	 * 创建销售菜单
	 * @return
	 */
	private JMenu createSaleMenu() {
		saleMenu = new JMenu();
		saleMenu.setText("销售");
		saleMenu.setFont(Constance.font24);
		
		querySaleOrderMenuItem = new JMenuItem();
		querySaleOrderMenuItem.setText("销售查询");
		querySaleOrderMenuItem.setFont(Constance.font21);
		querySaleOrderMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(querySaleOrderMenuItem.getText(),  new ProductSaleOrderPanel(querySaleOrderMenuItem.getText()));
			}
		});
		saleMenu.add(querySaleOrderMenuItem);
		
		queryPorductSaleMenuItem = new JMenuItem();
		queryPorductSaleMenuItem.setText("商品销售");
		queryPorductSaleMenuItem.setFont(Constance.font21);
		queryPorductSaleMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(queryPorductSaleMenuItem.getText(),  new ProductSalePanel(queryPorductSaleMenuItem.getText()));
			}
		});
		saleMenu.add(queryPorductSaleMenuItem);
		
		return saleMenu;
	}
	
	/**
	 * 创建零钱菜单
	 * @return
	 */
	private JMenu createCashMenu() {
		cashMenu = new JMenu();
		cashMenu.setText("零钱");
		cashMenu.setFont(Constance.font24);
		
		queryCashMenuItem = new JMenuItem();
		queryCashMenuItem.setText("零钱查询");
		queryCashMenuItem.setFont(Constance.font21);
		queryCashMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageFrame.getInst().refreshPanel(queryCashMenuItem.getText(),  new CashQueryPanel(queryCashMenuItem.getText()));
			}
		});
		cashMenu.add(queryCashMenuItem);
		
		return cashMenu;
	}
}
