package shop.view.sale;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;

import shop.bean.Product;

import javax.swing.JPanel;


public class ProductOrderListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private AtomicInteger indexAtom = new AtomicInteger(0);
	
	private Map<Integer, ProductOrderPanel> map = new HashMap<Integer, ProductOrderPanel>();

	private GroupLayout layout;
	private ParallelGroup hpGroup;
	private GroupLayout.SequentialGroup vGroup;
	
	private int select = 0;
	
	public ProductOrderListPanel() {
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
	
	public void addProductOrder(Product product) {
		int index = indexAtom.incrementAndGet();
		ProductOrderPanel productPanel = new ProductOrderPanel(product, index);
		productPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { 
				ProductOrderPanel productOrderPanel = (ProductOrderPanel) e.getSource();
				select(productOrderPanel.getIndex());
			}
		});
		map.put(index, productPanel);
		hpGroup.addComponent(productPanel);
		vGroup.addGroup(layout.createParallelGroup().addComponent(productPanel));
		updateTotal();
		select(index);
//		JScrollBar jScrollBar = SaleFrame.getInst().getOrderPanel().getScrollPane().getVerticalScrollBar();//获得垂直滚动条  
//        jScrollBar.setValue(jScrollBar.getMaximum());//设置垂直滚动条位置  
		Point p = new Point();
		p.setLocation(0, SaleFrame.getInst().getOrderPanel().getProductOrderListPanel().getPreferredSize().getHeight());
		SaleFrame.getInst().getOrderPanel().getScrollPane().getViewport().setViewPosition(p);
	}
	
	public void add() {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		productOrderPanel.updateNum(1);
		updateTotal();
		updateUI();
	}
	
	public void del() {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		productOrderPanel.updateNum(-1);
		if(productOrderPanel.getNum() <= 0) {
			remove();
		}else {
			updateTotal();
		}
	}
	public void alertNum(float num) {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		productOrderPanel.setNum(num);
		if(productOrderPanel.getNum() <= 0) {
			remove();
		}else {
			updateTotal();
		}
	}
	
	public void resetPrice() {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		productOrderPanel.updatePrice(productOrderPanel.getProduct().getOutPrice());
		updateTotal();
	}
	public void alertPrice(float price) {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		productOrderPanel.updatePrice(price);
		updateTotal();
	}
	
	public void remove() {
		ProductOrderPanel productOrderPanel = getProductPanel(select);
		if(productOrderPanel == null) {
			return;
		}
		map.remove(productOrderPanel.getIndex());
		remove(productOrderPanel);
		if(productOrderPanel.getIndex() == select) {
			List<Integer> indexList = new ArrayList<Integer>(map.keySet());
			Collections.sort(indexList);
			int preIndex = 0;
			int nextIndex = 0;
			for(Integer i : indexList) {
				if(i >  select) {
					nextIndex = i;
					break;
				}else if(i < select) {
					preIndex = Math.max(preIndex, i);
				}
			}
			if(nextIndex > 0) {
				select(nextIndex);
			}else {
				select(preIndex);
			}
		}
		updateTotal();
		updateUI();
	}
	
	public ProductOrderPanel getProductPanel(int index) {
		return map.get(index);
	}
	
	public void select(int newSelect) {
		if(select == newSelect) {
			return;
		}
		if(select != 0) {
			ProductOrderPanel productOrderPanel = getProductPanel(select);
			if(productOrderPanel != null) {
				productOrderPanel.select(false);
			}
		}
		if(newSelect != 0) {
			ProductOrderPanel productOrderPanel = getProductPanel(newSelect);
			if(productOrderPanel != null) {
				productOrderPanel.select(true);
			}
		}
		select = newSelect;
	}
	
	public void updateTotal() {
		float text = 0.0f;
		for(ProductOrderPanel productOrderPanel : map.values()) {
			text += productOrderPanel.getNum() * productOrderPanel.getOutPrice();
		}
		SaleFrame.getInst().getOrderPanel().getTotalPanel().setTotal(text);
	}
	
	public void clean() {
		select = 0;
		map.clear();
		indexAtom.set(0);
		removeAll();
		updateUI();
	}
	
	public Map<Integer, ProductOrderPanel> map() {
		return map;
	}
	
	public ProductOrderPanel getSelectProductOrderPanel() {
		return map.get(select);
	}
}
