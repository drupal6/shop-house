package shop.view.sale;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import shop.beam.ProductType;
import shop.provider.ProductTypeProvider;

public class ProductSelectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductTypeScorellPanel titlePanel;
	private JPanel contentPanel;
	
	public ProductSelectPanel() {
		setBackground(Color.BLACK);
		
		titlePanel = new ProductTypeScorellPanel();
		
		contentPanel = new JPanel();
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 150, GroupLayout.DEFAULT_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
	}
}
