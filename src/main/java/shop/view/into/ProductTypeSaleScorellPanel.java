package shop.view.into;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import shop.bean.Product;
import shop.bean.ProductType;
import shop.provider.ProductProvider;
import shop.provider.ProductTypeProvider;

public class ProductTypeSaleScorellPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<ProductTypeShowPanel> typePanel = new ArrayList<ProductTypeShowPanel>();
	
	private JPanel contentPanel;
	
	
	public ProductTypeSaleScorellPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
		FlowLayout titlePanelLayout = new FlowLayout();
		setLayout(titlePanelLayout);
		List<ProductType> list = ProductTypeProvider.getInst().list();
		int index = 0;
		for(ProductType type : list) {
			ProductTypeShowPanel productTypePanel = new ProductTypeShowPanel(type, index);
			productTypePanel.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) { }
				public void mousePressed(MouseEvent e) {
					ProductTypeShowPanel ptp = (ProductTypeShowPanel) e.getSource();
					select(ptp.getIndex());
				}
				public void mouseExited(MouseEvent e) { }
				public void mouseEntered(MouseEvent e) { }
				public void mouseClicked(MouseEvent e) { }
			});
			add(productTypePanel);
			typePanel.add(productTypePanel);
			index++;
		}
		select(0);
	}
	
	public void select(int selectIndex) {
		contentPanel.removeAll();
		for(int i = 0; i < typePanel.size(); i++) {
			ProductTypeShowPanel type  = typePanel.get(i);
			if(type.getIndex() == selectIndex) {
				
				type.setSelect(true);
				List<Product> list = ProductProvider.getInst().list(type.getType().getId());
				for(Product product : list) {
					ProductShowPanel productShowPanel = new ProductShowPanel(product);
					productShowPanel.addMouseListener(new MouseListener() {
						public void mouseReleased(MouseEvent e) {
							ProductShowPanel psp = (ProductShowPanel) e.getSource();
							psp.setBackground(new Color(240, 240, 240));
						}
						public void mousePressed(MouseEvent e) { 
							ProductShowPanel psp = (ProductShowPanel) e.getSource();
							psp.setBackground(new Color(107, 137, 213));
						}
						public void mouseExited(MouseEvent e) { }
						public void mouseEntered(MouseEvent e) { }
						public void mouseClicked(MouseEvent e) {
							ProductShowPanel psp = (ProductShowPanel) e.getSource();
							IntoFrame.getInst().getOrderPanel().getProductOrderListPanel().addProductOrder(psp.getProduct());
						}
					});
					contentPanel.add(productShowPanel);
				}
			}else {
				type.setSelect(false);
			}
		}
		contentPanel.updateUI();
	}
}
