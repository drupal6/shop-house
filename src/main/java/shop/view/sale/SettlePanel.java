package shop.view.sale;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SettlePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SettlePanel() {
		setSize(300, 400);
		setBackground(new Color(240, 240, 240));
		setBorder(BorderFactory.createLineBorder(new Color(64, 64, 64), 6));
	}
}
