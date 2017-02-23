package shop.view;

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
	
	
	public ManageMenuBar() {
		this.add(createBaseMenu());
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
		baseDataMenu.setFont(Constance.mainMenuFont);
		
		productTypeMenuItem = new JMenuItem();
		productTypeMenuItem.setText("商品类别");
		productTypeMenuItem.setFont(Constance.menuFont);
		baseDataMenu.add(productTypeMenuItem);
		
		productUnitMenuItem = new JMenuItem();
		productUnitMenuItem.setText("商品单位");
		productUnitMenuItem.setFont(Constance.menuFont);
		baseDataMenu.add(productUnitMenuItem);
		
		productInfoMenuItem = new JMenuItem();
		productInfoMenuItem.setText("商品信息");
		productInfoMenuItem.setFont(Constance.menuFont);
		baseDataMenu.add(productInfoMenuItem);
		return baseDataMenu;
	}
	
}
