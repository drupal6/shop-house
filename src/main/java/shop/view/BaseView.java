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

	private String title;
	
	public BaseView(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public abstract void init(Shop shop);
}
