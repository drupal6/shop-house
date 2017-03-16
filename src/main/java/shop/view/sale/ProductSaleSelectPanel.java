package shop.view.sale;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class ProductSaleSelectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductTypeSaleScorellPanel titlePanel;
	private JPanel contentPanel;
	
	public ProductSaleSelectPanel() {
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPanel.setBackground(new Color(47, 47, 47));
		
		titlePanel = new ProductTypeSaleScorellPanel(contentPanel);
		titlePanel.setBackground(new Color(47, 47, 47));
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
	}
}
