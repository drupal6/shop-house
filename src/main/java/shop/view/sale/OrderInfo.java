package shop.view.sale;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;
import shop.beam.OutOrder;

public class OrderInfo extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutOrder outOrder;
	private JLabel dayLabel;
	private JLabel userLabel;
	
	public OrderInfo(OutOrder outOrder) {
		setBackground(new Color(253, 245, 230));
		this.outOrder = outOrder;
		dayLabel = new JLabel(Constance.dateFormt.format(outOrder.getOpTime()));
		userLabel = new JLabel("操作员:" + outOrder.getUserId());
		
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(10);
        hGroup.addGroup(layout.createParallelGroup()
        		.addComponent(dayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        		.addComponent(userLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
		);
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(3);
        vGroup.addGroup(layout.createParallelGroup().addComponent(dayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGroup(layout.createParallelGroup().addComponent(userLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGap(3);
        layout.setVerticalGroup(vGroup);
	}

	public OutOrder getOutOrder() {
		return outOrder;
	}
}
