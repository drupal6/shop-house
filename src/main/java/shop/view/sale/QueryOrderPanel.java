package shop.view.sale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shop.Constance;
import shop.beam.OutOrder;
import shop.beam.ProductOutInfo;
import shop.provider.ProductOutInfoProvider;
import shop.view.manage.MyTable;

public class QueryOrderPanel extends JPanel{

	private JPanel titlePanel;
	private JLabel titleLable;
	private JButton backButton;
	private OrderListPanel orderListPanel;
	private JPanel productListPnale;
	private MyTable table1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QueryOrderPanel() {
		this.setBackground(new Color(64, 64, 64));
		
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(143, 143, 143));
		titleLable = new JLabel("查单");
		titleLable.setFont(Constance.font24);
		backButton = new JButton("返回");
		backButton.setFont(Constance.font24);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaleFrame.getInst().setLayerPanel(300, 200);
			}
		});
		
		   javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(titlePanel);
		   titlePanel.setLayout(titleLayout);
	        titleLayout.setHorizontalGroup(
	            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(titleLayout.createSequentialGroup()
	            		.addGap(10)
	                .addComponent(titleLable)
	                .addComponent(backButton))
	        );
	        titleLayout.setVerticalGroup(
	            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(titleLayout.createSequentialGroup()
	            		.addGap(10)
	                .addGroup(titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(titleLable)
	                    .addComponent(backButton)
	                    )
	                .addGap(10)
	                )
	        );
				
		
		orderListPanel = new OrderListPanel(this);
		
		productListPnale = new JPanel();
		
		table1 = new MyTable(ProductOutInfoProvider.getTitle(), null, 800, 500);
		productListPnale.add(table1);
		
		GroupLayout mainLayout = new GroupLayout(this);
        this.setLayout(mainLayout);
        GroupLayout.SequentialGroup hGroup = mainLayout.createSequentialGroup();
        hGroup.addGap(10);
		hGroup.addGroup(mainLayout.createParallelGroup()
            .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(orderListPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(3)
                .addComponent(productListPnale, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );
		hGroup.addGap(10);
		mainLayout.setHorizontalGroup(hGroup);
        
		GroupLayout.SequentialGroup vGroup = mainLayout.createSequentialGroup();
		vGroup.addGap(10);
		vGroup.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addGap(3)
                .addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(orderListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productListPnale, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
        );
		vGroup.addGap(10);
        mainLayout.setVerticalGroup(vGroup);
	}
	
	public void replaceProduct(OutOrder outOrder) {
		List<ProductOutInfo> list = ProductOutInfoProvider.getInst().queryByOrderId(outOrder.getId());
		table1.replace(ProductOutInfoProvider.getTitle(), ProductOutInfoProvider.getInst().getListValue(list));
	}
}
