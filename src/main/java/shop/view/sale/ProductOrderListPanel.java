package shop.view.sale;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JPanel;

import shop.beam.Product;


public class ProductOrderListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TotalPanel totalPanel;
	
	private AtomicInteger indexAtom = new AtomicInteger(0);
	
	private Map<Integer, ProductOrderPanel> map = new HashMap<Integer, ProductOrderPanel>();

	private GroupLayout layout;
	private ParallelGroup hpGroup;
	private GroupLayout.SequentialGroup vGroup;
	
	private int select = 0;
	
	public ProductOrderListPanel(TotalPanel totalPanel) {
		setBackground(new Color(240, 240, 240));
		layout = new GroupLayout(this);
		this.setLayout(layout);
		
		this.totalPanel = totalPanel;
		
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
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(productPanel));
		updateTotal();
		select(index);
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
		productOrderPanel.updateNum(num);
		if(productOrderPanel.getNum() <= 0) {
			remove();
		}else {
			updateTotal();
		}
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
		if(productOrderPanel.getIndex() == select) {
			select = 0;
		}
		remove(productOrderPanel);
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
		totalPanel.setTotal(text);
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
}
