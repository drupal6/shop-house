package shop.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class OptionMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	public OptionMenu(String queryString) {
		this.setBackground(new Color(250, 250, 250));
		
		JButton jButton1 = new JButton();
		JButton jButton2 = new JButton();
		JButton jButton3 = new JButton();
		JTextField jTextField1 = new JTextField();
        JButton jButton4 = new JButton();

        setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jButton1.setText("新增");

        jButton2.setText("修改");

        jButton3.setText("删除");

        jTextField1.setText(queryString);

        jButton4.setText("查询");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(90, 90, 90)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(27, Short.MAX_VALUE))
        );
	}
}
