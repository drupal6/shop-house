package shop.view.sale;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class OrderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel searchPanel;
	
	private TotalPanel totalPanel;
	
	private ProductOrderListPanel productOrderListPanel;

	public OrderPanel() {
		
		searchPanel = new JPanel();
		searchPanel.setBackground(new Color(47, 47, 47));
		totalPanel = new TotalPanel("asdfa");
		JSeparator s2 = new JSeparator();
		productOrderListPanel = new ProductOrderListPanel(totalPanel);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(totalPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(productOrderListPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(totalPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(productOrderListPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
	}

	public ProductOrderListPanel getProductOrderListPanel() {
		return productOrderListPanel;
	}

	public TotalPanel getTotalPanel() {
		return totalPanel;
	}
	
	public void clean() {
		totalPanel.setTotal(0.0f);
		productOrderListPanel.clean();
	}
}
