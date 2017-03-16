package shop.view.sale;

import java.awt.Color;

import javax.swing.JTextPane;

import shop.Constance;
import shop.beam.ProductType;

public class ProductTypeShowPanel extends JTextPane{

	private static final long serialVersionUID = 1L;
	
	private ProductType type;
	private int index;
	
	public ProductTypeShowPanel(ProductType type, int index) {
		setBackground(new Color(240, 240, 240));
		setSize(90, 70);
		setFont(Constance.fontB35);
		this.type = type;
		this.index = index;
		setText(type.getName());
		setEditable(false);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSelect(boolean select) {
		if(select) {
			setBackground(new Color(107, 137, 213));
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
