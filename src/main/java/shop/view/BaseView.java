package shop.view;

import javax.swing.JPanel;

import shop.Shop;

/**
 *
 * @author Gene
 * 2019年4月2日
 *
 */
public abstract class BaseView {

	public abstract void init(Shop shop);
	
	public abstract String getTitle();
	
	public abstract JPanel getMainPanel();
	
	public abstract void remove();
}
