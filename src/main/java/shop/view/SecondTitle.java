package shop.view;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SecondTitle extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLanbel;
	
	public SecondTitle(String title) {
		this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
		
		titleLanbel = new JLabel(title);
		Font font=new Font("宋体", Font.BOLD, 24);
		titleLanbel.setFont(font);
		
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(titleLanbel)
                .addContainerGap(643, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLanbel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
		
	}
}
