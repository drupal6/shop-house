package shop.view.into;

import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;

public class SaleButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private SaleOptionEnum saleEnum;
	
	public SaleButtonPanel(SaleOptionEnum saleEnum) {
		setBackground(saleEnum.getColor());
		this.saleEnum = saleEnum;
		JLabel label = new JLabel(saleEnum.getName());
		label.setFont(Constance.fontB35);
		label.setForeground(saleEnum.getFontColor());
		add(label);
	}

	public SaleOptionEnum getSaleEnum() {
		return saleEnum;
	}
	
}
