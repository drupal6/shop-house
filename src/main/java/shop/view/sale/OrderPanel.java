package shop.view.sale;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import shop.Constance;
import shop.Shop;
import shop.view.manage.ManageView;

public class OrderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel searchPanel;
	
	private TotalPanel totalPanel;
	
	private ProductOrderListPanel productOrderListPanel;

	private JScrollPane scrollPane;
	
	public OrderPanel(SaleView saleView) {
		searchPanel = new JPanel();
		searchPanel.setBackground(new Color(47, 47, 47));
		if(Shop.getInst().getUser().getAdmin() == 1) {
			JLabel manageLable = new JLabel("管理");
			manageLable.setFont(Constance.fontB35);
			manageLable.setForeground(new Color(226, 158, 67));
			searchPanel.add(manageLable);
			searchPanel.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) { 
				}
				public void mouseExited(MouseEvent e) { }
				public void mouseEntered(MouseEvent e) { }
				public void mouseClicked(MouseEvent e) {
					Shop.getInst().changeView(new ManageView());
				}
			});
		}
		
		totalPanel = new TotalPanel(Shop.getInst().getUser().getName());
		JSeparator s2 = new JSeparator();
		productOrderListPanel = new ProductOrderListPanel(saleView);
		
		scrollPane = new JScrollPane(productOrderListPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(totalPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(totalPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(vGroup);
	}

	public ProductOrderListPanel getProductOrderListPanel() {
		return productOrderListPanel;
	}

	public TotalPanel getTotalPanel() {
		return totalPanel;
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void clean() {
		totalPanel.setTotal(0.0f);
		productOrderListPanel.clean();
	}
}
