package shop.view.sale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import shop.Constance;
import shop.beam.OutOrder;
import shop.provider.ProductOutOrderProvider;


public class OrderInfoListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPopupMenu jp;
	
	private GroupLayout layout;
	private ParallelGroup hpGroup;
	private GroupLayout.SequentialGroup vGroup;
	private int selectId;
	private Map<Integer, OrderInfo> map = new HashMap<Integer, OrderInfo>();
	private QueryOrderPanel queryOrderPanel;
	
	private OrderInfo delOrderInfo;
	
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
		
		jp = new JPopupMenu();
		JMenuItem delMenuItem = new JMenuItem("退单");
		delMenuItem.setFont(Constance.font22);
		delMenuItem.setBackground(new Color(226, 158, 67));
		delMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delOrder();
			}
		});
		jp.add(delMenuItem);
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
				if(e.getButton() == MouseEvent.BUTTON3) {
					jp.show(oifp, e.getX(), e.getY());
					delOrderInfo = oifp;
				}else {
					select(oifp.getOutOrder().getId());
				}
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
	
	public void select(int id) {
		selectId = id;
		for(OrderInfo oi : map.values()) {
			if(oi.getOutOrder().getId() == selectId) {
				oi.setBackground(new Color(240, 230, 140));
			}else {
				oi.setBackground(new Color(253, 245, 230));
			}
		}
		OrderInfo selectOrder = map.get(selectId);
		if(selectOrder != null) {
			queryOrderPanel.replaceProduct(selectOrder.getOutOrder());
		}else {
			queryOrderPanel.replaceProduct(null);
		}
	}
	
	public void delOrder() {
		if(delOrderInfo == null) {
			return;
		}
		if(ProductOutOrderProvider.getInst().delOrder(delOrderInfo.getOutOrder().getId(), 0)) {
			map.remove(delOrderInfo.getOutOrder().getId());
			remove(delOrderInfo);
			if(delOrderInfo.getOutOrder().getId() == selectId) {
				List<Integer> indexList = new ArrayList<Integer>(map.keySet());
				Collections.sort(indexList);
				int preIndex = 0;
				int nextIndex = 0;
				for(Integer i : indexList) {
					if(i >  selectId) {
						nextIndex = i;
						break;
					}else if(i < selectId) {
						preIndex = Math.max(preIndex, i);
					}
				}
				if(nextIndex > 0) {
					select(nextIndex);
				}else {
					select(preIndex);
				}
			}
			updateUI();
		}
	}
}
