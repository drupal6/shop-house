package shop.view.sale;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JPanel;

import shop.beam.OutOrder;


public class OrderInfoListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	

	private GroupLayout layout;
	private ParallelGroup hpGroup;
	private GroupLayout.SequentialGroup vGroup;
	private int selectId;
	private Map<Integer, OrderInfo> map = new HashMap<Integer, OrderInfo>();
	private QueryOrderPanel queryOrderPanel;
	public OrderInfoListPanel(QueryOrderPanel queryOrderPanel) {
		this.queryOrderPanel = queryOrderPanel;
		setBackground(new Color(240, 240, 240));
		layout = new GroupLayout(this);
		this.setLayout(layout);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hpGroup = layout.createParallelGroup();
		hGroup.addGroup(hpGroup);
		layout.setHorizontalGroup(hGroup);
		
		vGroup = layout.createSequentialGroup();
		layout.setVerticalGroup(vGroup);
	}
	
	public void addOutOrder(OutOrder outOrder) {
		OrderInfo orderInfoPanel = new OrderInfo(outOrder);
		orderInfoPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) {
				OrderInfo oifp = (OrderInfo) e.getSource();
				select(oifp.getOutOrder());
				queryOrderPanel.replaceProduct(oifp.getOutOrder());
			}
		});
		hpGroup.addComponent(orderInfoPanel);
		vGroup.addGroup(layout.createParallelGroup().addComponent(orderInfoPanel));
		map.put(outOrder.getId(), orderInfoPanel);
		updateUI();
	}
	public void clean() {
		removeAll();
		updateUI();
	}
	
	public void select(OutOrder outOrder) {
		selectId = outOrder.getId();
		for(OrderInfo oi : map.values()) {
			if(oi.getOutOrder().getId() == selectId) {
				oi.setBackground(new Color(240, 230, 140));
			}else {
				oi.setBackground(new Color(253, 245, 230));
			}
		}
	}
	
}
