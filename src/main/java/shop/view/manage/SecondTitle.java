package shop.view.manage;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;

public class SecondTitle extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLanbel;
	
	public SecondTitle(String title) {
		this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
		
		titleLanbel = new JLabel(title);
		titleLanbel.setFont(Constance.secondTitleFont);
		
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(10);
        hGroup.addGroup(layout.createParallelGroup().addComponent(titleLanbel));
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(titleLanbel));
        vGroup.addGap(10);
        layout.setVerticalGroup(vGroup);
	}
}
