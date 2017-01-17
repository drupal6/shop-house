package shop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class MyDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
    private JPanel mainPanel;
    private JPanel buttonPanel;
	
	public MyDialog(JFrame mainFrame, boolean model, String title, int weight, int height) {
		super(mainFrame, model);
		Font font=new Font("宋体", Font.BOLD, 22);
		this.setFont(font);
		this.setTitle(title);
		this.setSize(weight, height);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		//居中显示
		Point topLeft = mainFrame.getLocationOnScreen();
		Dimension parentSize = mainFrame.getSize();
		Dimension mySize = this.getSize();
		int x, y;
		if (parentSize.width > mySize.width) {
			x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
		} else {
			x = topLeft.x;
		}
		if (parentSize.height > mySize.height) {
			y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
		} else {
			y = topLeft.y;
		}
		this.setLocation(x, y);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
	}

	public void setLayOut() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}
}
