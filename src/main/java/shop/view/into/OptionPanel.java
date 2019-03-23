package shop.view.into;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public OptionPanel() {
		setBackground(new Color(47, 47, 47));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		hGroup.addContainerGap(5, 5);
		vGroup.addContainerGap(60, 60);
		
		ParallelGroup pg = layout.createParallelGroup();
		for(SaleOptionEnum oe : SaleOptionEnum.values()) {
			SaleButtonPanel sbp = new SaleButtonPanel(oe);
			sbp.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					SaleButtonPanel sbp1 = (SaleButtonPanel) e.getSource();
					sbp1.setBackground(sbp1.getSaleEnum().getColor());
				}
				public void mousePressed(MouseEvent e) { 
					SaleButtonPanel sbp1 = (SaleButtonPanel) e.getSource();
					sbp1.setBackground(new Color(107, 137, 213));
				}
				public void mouseExited(MouseEvent e) { }
				public void mouseEntered(MouseEvent e) { }
				public void mouseClicked(MouseEvent e) {
					SaleButtonPanel sbp1 = (SaleButtonPanel) e.getSource();
					SaleOptionEnum soe = sbp1.getSaleEnum();
					soe.clickHandle();
				}
			});
			pg.addComponent(sbp, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
			vGroup.addGap(5);
			vGroup.addGroup(layout.createParallelGroup().addComponent(sbp, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		}
		hGroup.addGroup(pg);
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
	}
}
