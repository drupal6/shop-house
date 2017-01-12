package shop.view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class OptionMenu extends JPanel {

	public OptionMenu() {
		this.setBackground(new Color(250, 250, 250));
		
		GroupLayout jPanel3Layout = new GroupLayout(this);
        this.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );
	}
}
