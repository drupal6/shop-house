package shop.view.sale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shop.Constance;
import shop.Constance.ORDERSTATE;
import shop.beam.OutOrder;
import shop.beam.Product;
import shop.beam.ProductOutInfo;
import shop.provider.ProductOutInfoProvider;
import shop.provider.ProductOutOrderProvider;
import shop.provider.ProductProvider;

public class SettlePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel shouTipLable;
	private JLabel shouTotalLable;
	
	private JLabel haveTipLable;
	private JLabel haveTotalLable;
	private JTextField haveInput;
	private JButton bt100;
	private JButton bt50;
	private JButton bt20;
	private JButton bt10;
	
	
	private JLabel backTipLable;
	private JLabel backTotalLable;
	private JButton backBt;
	private JButton okBt;
	private JButton delBt;
	
	private float souldTotal = 0.0f;
	private float haveTotal = 0.0f;
	private float backTotal = 0.0f;

	public SettlePanel(float total) {
		this.souldTotal = total;
		this.backTotal = haveTotal - souldTotal;
		this.setBackground(new Color(64, 64, 64));
		
		shouTipLable = new JLabel("应收:");
		shouTipLable.setFont(Constance.fontB30);
		shouTotalLable = new JLabel(souldTotal+"");
		shouTotalLable.setForeground(Color.RED);
		shouTotalLable.setFont(Constance.fontB30);
		shouTotalLable.setHorizontalAlignment(SwingConstants.TRAILING);
		JPanel shouldPanel = new JPanel();
		shouldPanel.setBackground(new Color(240, 240, 240));
		GroupLayout shouLayout = new GroupLayout(shouldPanel);
		shouldPanel.setLayout(shouLayout);
		shouLayout.setHorizontalGroup(shouLayout.createSequentialGroup().addGap(10)
				.addGroup(shouLayout.createParallelGroup() 
						.addComponent(shouTipLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(shouLayout.createParallelGroup() 
						.addComponent(shouTotalLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)).addGap(10));
		shouLayout.setVerticalGroup(shouLayout.createSequentialGroup().addGap(20)
				.addGroup(shouLayout.createParallelGroup()
				.addComponent(shouTipLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(shouTotalLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(10));
		
		haveTipLable = new JLabel("已收:");
		haveTipLable.setFont(Constance.fontB30);
		haveTotalLable = new JLabel(haveTotal+"");
		haveTotalLable.setFont(Constance.fontB30);
		haveTotalLable.setHorizontalAlignment(SwingConstants.TRAILING);
		haveInput = new JTextField();
		haveInput.setFont(Constance.fontB30);
		haveInput.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				int keyChar=arg0.getKeyChar();
				if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) || keyChar == 46) {
					String str = haveInput.getText().trim();
					if(str == null || str.isEmpty()) {
						if(keyChar == 46) {
							arg0.consume();  
						}
					}
					if(keyChar == 46) {
						if(str.contains(".")) {
							arg0.consume();  
						}
					}
				} else {
					arg0.consume();  
				}
			}
			public void keyReleased(KeyEvent arg0) {haveInputChange();}
			public void keyPressed(KeyEvent arg0) { }
		});
		bt100 = new JButton("100");
		bt100.setFont(Constance.fontB30);
		bt100.setForeground(new Color(254, 127, 106));
		bt100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addHaveTotal(100);
			}
		});
		bt50 = new JButton("50");
		bt50.setFont(Constance.fontB30);
		bt50.setForeground(new Color(86, 185, 242));
		bt50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addHaveTotal(50);
			}
		});
		bt20 = new JButton("20");
		bt20.setFont(Constance.fontB30);
		bt20.setForeground(new Color(246, 192, 45));
		bt20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addHaveTotal(20);
			}
		});
		bt10 = new JButton("10");
		bt10.setFont(Constance.fontB30);
		bt10.setForeground(new Color(64, 64, 64));
		bt10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addHaveTotal(10);
			}
		});
		JPanel havePanel = new JPanel();
		havePanel.setBackground(new Color(240, 240, 240));
		GroupLayout haveLayout = new GroupLayout(havePanel);
		havePanel.setLayout(haveLayout);
        haveLayout.setHorizontalGroup(
            haveLayout.createParallelGroup()
	            .addGroup(haveLayout.createSequentialGroup().addGap(10)
	                .addGroup(haveLayout.createParallelGroup()
	                    .addGroup(haveLayout.createSequentialGroup()
	                        .addComponent(bt100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(bt50, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(bt20, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(bt10, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(haveLayout.createSequentialGroup()
	                        .addGroup(haveLayout.createParallelGroup()
	                            .addGroup(haveLayout.createSequentialGroup()
	                                .addComponent(haveTipLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(haveTotalLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
	                            .addComponent(haveInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
	                        )).addGap(10)
	                )
	        );
	        haveLayout.setVerticalGroup(
	            haveLayout.createParallelGroup()
	            .addGroup(haveLayout.createSequentialGroup().addGap(10)
	                .addGroup(haveLayout.createParallelGroup()
	                    .addComponent(haveTipLable)
	                    .addComponent(haveTotalLable))
	                .addComponent(haveInput)
	                .addGroup(haveLayout.createParallelGroup()
	                    .addComponent(bt100)
	                    .addComponent(bt50)
	                    .addComponent(bt20)
	                    .addComponent(bt10)).addGap(10))
	        );
		
		backTipLable = new JLabel("找零:");
		backTipLable.setFont(Constance.fontB30);
		backTotalLable = new JLabel(backTotal+"");
		backTotalLable.setForeground(new Color(255, 140, 0));
		backTotalLable.setFont(Constance.fontB30);
		backTotalLable.setHorizontalAlignment(SwingConstants.TRAILING);
		delBt = new JButton("费单");
		delBt.setFont(Constance.fontB30);
		delBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cannel();
			}
		});
		backBt = new JButton("返回");
		backBt.setFont(Constance.fontB30);
		backBt.setBackground(new Color(244, 194, 69));
		backBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleFrame.getInst().setLayerPanel(300, 200);
			}
		});
		okBt = new JButton("结算");
		okBt.setForeground(Color.WHITE);
		okBt.setBackground(new Color(117, 181, 84));
		okBt.setFont(Constance.fontB30);
		okBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settle();
			}
		});
		JPanel backPanel = new JPanel();
		backPanel.setBackground(new Color(240, 240, 240));
		GroupLayout backLayout = new GroupLayout(backPanel);
		backPanel.setLayout(backLayout);
        backLayout.setHorizontalGroup(
	            backLayout.createParallelGroup()
	            .addGroup(backLayout.createSequentialGroup().addGap(10)
	                .addGroup(backLayout.createParallelGroup()
	                    .addGroup(backLayout.createSequentialGroup()
	                        .addComponent(delBt)
	                        .addComponent(backBt)
	                        .addComponent(okBt)
                        )
	                    .addGroup(backLayout.createSequentialGroup()
	                        .addComponent(backTipLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(backTotalLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        )
                ).addGap(10)
            )
        );
        backLayout.setVerticalGroup(
        backLayout.createParallelGroup()
            .addGroup(backLayout.createSequentialGroup().addGap(10)
                .addGroup(backLayout.createParallelGroup()
                    .addComponent(backTipLable)
                    .addComponent(backTotalLable))
                .addGroup(backLayout.createParallelGroup()
                    .addComponent(delBt)
                    .addComponent(backBt)
                    .addComponent(okBt)).addGap(10)
            )
        );
        
        JSeparator s1 = new JSeparator();
        JSeparator s2 = new JSeparator();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(10);
        hGroup.addGroup(layout.createParallelGroup()
        		.addComponent(shouldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        		.addComponent(s1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        		.addComponent(havePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        		.addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        		.addComponent(backPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
		);
        hGroup.addGap(10);
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(shouldPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGroup(layout.createParallelGroup().addComponent(s1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGroup(layout.createParallelGroup().addComponent(havePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGroup(layout.createParallelGroup().addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGroup(layout.createParallelGroup().addComponent(backPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
        vGroup.addGap(10);
        layout.setVerticalGroup(vGroup);
	}
	
	private void addHaveTotal(int total) {
		this.haveTotal += total;
		haveTotalLable.setText(haveTotal +"");
		haveInput.setText(haveTotal +"");
		backTotalLable.setText((haveTotal - souldTotal) + "");
	}
	
	private void haveInputChange() {
		String str = haveInput.getText().trim();
		if(str != null && false == str.isEmpty()) {
			haveTotal = Float.parseFloat(Constance.df.format(Float.parseFloat(str)));
			haveTotalLable.setText(haveTotal +"");
			backTotalLable.setText((haveTotal - souldTotal) + "");
		}else {
			haveTotal = 0.0f;
			haveTotalLable.setText(haveTotal +"");
			backTotalLable.setText((haveTotal - souldTotal) + "");
		}
	}
	
	private void cannel() {
		SaleFrame.getInst().getOrderPanel().clean();
		SaleFrame.getInst().setLayerPanel(300, 200);
	}
	
	private void settle() {
		if(souldTotal > 0) {
			OutOrder order = new OutOrder();
			Date now = new Date();
			order.setOpTime(now);
			order.setCash(souldTotal);
			order.setState(ORDERSTATE.PAY.ordinal());
			if(null == ProductOutOrderProvider.getInst().add(order)) {
				return;
			}
			Map<Integer, ProductOrderPanel> map = SaleFrame.getInst().getOrderPanel().getProductOrderListPanel().map();
			for(ProductOrderPanel orderPanel : map.values()) {
				Product product = orderPanel.getProduct();
				if(orderPanel.getNum() <= 0) {
					continue;
				}
				ProductOutInfo outInfo = new ProductOutInfo();
				outInfo.setProductId(product.getId());
				outInfo.setNum(orderPanel.getNum());
				outInfo.setUserId(SaleFrame.getInst().getUser().getId());
				outInfo.setPrice(product.getOutPrice());
				outInfo.setPrice1(orderPanel.getOutPrice());
				outInfo.setOpTime(now);
				outInfo.setOutId(order.getId());
				outInfo.setState(ORDERSTATE.PAY.ordinal());
				ProductOutInfoProvider.getInst().add(outInfo);
				
				ProductProvider.getInst().productTotalChange(product.getId(), -orderPanel.getNum());;
			}
		}
		cannel();
	}
}
