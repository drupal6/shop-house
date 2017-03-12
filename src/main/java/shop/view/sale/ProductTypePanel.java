package shop.view.sale;

import java.awt.Color;

import javax.swing.JTextPane;

import shop.beam.ProductType;

public class ProductTypePanel extends JTextPane{

	private static final long serialVersionUID = 1L;
	
	private ProductType type;
	
	public ProductTypePanel(ProductType type) {
		setBackground(Color.white);
		setSize(90, 60);
		this.type = type;
		setText("["+type.getId()+"]" + type.getName());
		setEditable(false);
	}
	
	public void setSelect(boolean select) {
		if(select) {
			setBackground(Color.blue);
		}else {
			setBackground(Color.white);
		}
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}
}
