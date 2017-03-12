package shop.view.sale;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import shop.beam.ProductType;
import shop.provider.ProductTypeProvider;

public class ProductTypeScorellPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int selectIndex = 0;
	private List<ProductTypePanel> typePanel = new ArrayList<ProductTypePanel>();
	
	public ProductTypeScorellPanel() {
		FlowLayout titlePanelLayout = new FlowLayout();
		setLayout(titlePanelLayout);
		List<ProductType> list = ProductTypeProvider.getInst().list();
		for(ProductType type : list) {
			ProductTypePanel productTypePanel = new ProductTypePanel(type);
			add(productTypePanel);
			typePanel.add(productTypePanel);
		}
	}
	
	public static void select(int selectIndex) {
		
	}
}
