package shop.view.sale;

import java.awt.Color;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;

public class TotalPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel salorLabel1;
	private JLabel salorLabel2;
	private JLabel totalLabel1;
	private JLabel totalLabel2;
	
	public TotalPanel(String userName) {
		setBackground(new Color(240, 240, 240));
		salorLabel1 = new JLabel("操作员:" + userName);
		salorLabel1.setFont(Constance.font15);
		salorLabel2 = new JLabel(Constance.dateFormt.format(new Date()));
		salorLabel2.setFont(Constance.font15);
		totalLabel1 = new JLabel("合计:");
		totalLabel1.setFont(Constance.fontB30);
		totalLabel2 = new JLabel("￥0.0");
		totalLabel2.setFont(Constance.fontB30);
		totalLabel2.setForeground(new Color(255, 69, 0));
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(10);
		hGroup.addGroup(layout.createParallelGroup().addComponent(salorLabel2).addComponent(salorLabel1).addComponent(totalLabel1));
		hGroup.addGroup(layout.createParallelGroup().addComponent(totalLabel2).addGap(10));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(3);
		vGroup.addGroup(layout.createParallelGroup().addComponent(salorLabel2));
		vGroup.addGap(3);
		vGroup.addGroup(layout.createParallelGroup().addComponent(salorLabel1));
		vGroup.addGap(3);
		vGroup.addGroup(layout.createParallelGroup().addComponent(totalLabel1).addComponent(totalLabel2));
		layout.setVerticalGroup(vGroup);
	}

	public JLabel getTotalLabel2() {
		return totalLabel2;
	}
}
