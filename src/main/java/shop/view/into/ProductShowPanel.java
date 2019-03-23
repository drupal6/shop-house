package shop.view.into;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;
import shop.beam.Product;

public class ProductShowPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Product product;
	
	public ProductShowPanel(Product product) {
		setBackground(new Color(240, 240, 240));
		this.setPreferredSize(new Dimension(150, 70));
		this.product = product;
		JLabel nameLabel = new JLabel(product.getName());
		nameLabel.setFont(Constance.font24);
		JLabel priceLabel = new JLabel("￥" + product.getOutPrice());
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel)
				.addComponent(priceLabel));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel));
		vGroup.addGroup(layout.createParallelGroup().addComponent(priceLabel));
		layout.setVerticalGroup(vGroup);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
