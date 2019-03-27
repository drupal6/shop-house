package shop.view.into;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.Constance;
import shop.bean.OutOrder;
import shop.bean.ProductOutInfo;
import shop.provider.ProductOutInfoProvider;
import shop.provider.ProductOutOrderProvider;
import shop.provider.ProductProvider;
import shop.view.manage.MyTable;

public class ReturnProductDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel idLabel1;
	private JLabel idLabel2;
	private JLabel productLable1;
	private JLabel productLable2;
	private JLabel priceLabel3;
	private JLabel priceLabel4;
	private JLabel priceLable1;
	private JLabel priceLable2;
	private JLabel numberLabel1;
	private JLabel numberLabel2;
	private JLabel cashLable1;
	private JLabel cashLable2;
	
	private JLabel returnNumLabel;
	private JTextField eturnNumTextFile;
	
	private JLabel returnCashLable1;
	private JLabel returnCashLable2;
	
	private JButton cannelButton;
	private JButton okButton;
	
	private ProductOutInfo productOutInfo;
	private MyTable table;
	
	public ReturnProductDialog(String title, ProductOutInfo productOutInfo,  MyTable table) {
		this.productOutInfo = productOutInfo;
		this.table = table;
		this.setTitle(title);
		this.setSize(430, 310);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		Point topLeft = IntoFrame.getInst().getLocationOnScreen();
		Dimension parentSize = IntoFrame.getInst().getSize();
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
		
		  idLabel1 = new javax.swing.JLabel();
	        idLabel2 = new javax.swing.JLabel();
	        productLable1 = new javax.swing.JLabel();
	        productLable2 = new javax.swing.JLabel();
	        priceLabel3 = new javax.swing.JLabel();
	        priceLabel4 = new javax.swing.JLabel();
	        priceLable1 = new javax.swing.JLabel();
	        priceLable2 = new javax.swing.JLabel();
	        numberLabel1 = new javax.swing.JLabel();
	        numberLabel2 = new javax.swing.JLabel();
	        cashLable1 = new javax.swing.JLabel();
	        cashLable2 = new javax.swing.JLabel();
	        returnNumLabel = new javax.swing.JLabel();
	        eturnNumTextFile = new javax.swing.JTextField();
	        returnCashLable1 = new javax.swing.JLabel();
	        returnCashLable2 = new javax.swing.JLabel();
	        cannelButton = new javax.swing.JButton();
	        okButton = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        idLabel1.setFont(Constance.font21); // NOI18N
	        idLabel1.setText("编号:");

	        idLabel2.setFont(Constance.font21); // NOI18N
	        idLabel2.setText(productOutInfo.getId()+"");

	        productLable1.setFont(Constance.font21); // NOI18N
	        productLable1.setText("名称:");

	        productLable2.setFont(Constance.font21); // NOI18N
	        productLable2.setText(ProductProvider.getInst().getNameById(productOutInfo.getProductId()));

	        priceLabel3.setFont(Constance.font21); // NOI18N
	        priceLabel3.setText("原价:");

	        priceLabel4.setFont(Constance.font21); // NOI18N
	        priceLabel4.setText(productOutInfo.getPrice() + "");

	        priceLable1.setFont(Constance.font21); // NOI18N
	        priceLable1.setText("售价:");

	        priceLable2.setFont(Constance.font21); // NOI18N
	        priceLable2.setText(productOutInfo.getPrice1()+"");

	        numberLabel1.setFont(Constance.font21); // NOI18N
	        numberLabel1.setText("数量:");

	        numberLabel2.setFont(Constance.font21); // NOI18N
	        numberLabel2.setText(productOutInfo.getNum()+"");

	        cashLable1.setFont(Constance.font21); // NOI18N
	        cashLable1.setText("金额:");

	        cashLable2.setFont(Constance.font21); // NOI18N
	        cashLable2.setText(productOutInfo.getNum() * productOutInfo.getPrice1() + "");

	        returnNumLabel.setFont(Constance.font21); // NOI18N
	        returnNumLabel.setText("退货数量:");

	        eturnNumTextFile.setFont(Constance.font21); // NOI18N
	        eturnNumTextFile.setText(productOutInfo.getNum()+"");
	        eturnNumTextFile.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent arg0) {
					int keyChar=arg0.getKeyChar();
					if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) || keyChar == 46) {
						String str = eturnNumTextFile.getText().trim();
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
				public void keyReleased(KeyEvent arg0) {changeInputChange();}
				public void keyPressed(KeyEvent arg0) { }
			});

	        returnCashLable1.setFont(Constance.font21); // NOI18N
	        returnCashLable1.setText("退还金额:");

	        returnCashLable2.setFont(Constance.font21); // NOI18N
	        returnCashLable2.setText(productOutInfo.getNum() * productOutInfo.getPrice1() + "");

	        cannelButton.setFont(Constance.font21); // NOI18N
	        cannelButton.setText("取消");
	        cannelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cannel();
				}
			});

	        okButton.setFont(Constance.font21); // NOI18N
	        okButton.setText("确定");
	        okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ok();
				}
			});

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(numberLabel1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(numberLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(priceLabel3)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(priceLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(idLabel1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(idLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addGap(0, 18, Short.MAX_VALUE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(productLable1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(productLable2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(priceLable1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(priceLable2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(cashLable1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(cashLable2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(returnNumLabel)
	                            .addComponent(returnCashLable1))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(cannelButton)
	                                .addGap(29, 29, 29)
	                                .addComponent(okButton)
	                                .addGap(0, 0, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                                    .addComponent(eturnNumTextFile, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
	                                    .addComponent(returnCashLable2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
	                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(26, 26, 26)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(idLabel1)
	                    .addComponent(idLabel2)
	                    .addComponent(productLable1)
	                    .addComponent(productLable2))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(priceLabel3)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(priceLabel4)
	                        .addComponent(priceLable1)
	                        .addComponent(priceLable2)))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(numberLabel1)
	                    .addComponent(numberLabel2)
	                    .addComponent(cashLable1)
	                    .addComponent(cashLable2))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(returnNumLabel)
	                    .addComponent(eturnNumTextFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(returnCashLable1)
	                    .addComponent(returnCashLable2))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(cannelButton)
	                    .addComponent(okButton))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	}
	
	private void changeInputChange() {
		String str = eturnNumTextFile.getText().trim();
		if(str != null && false == str.isEmpty()) {
			float num = Float.parseFloat(Constance.df.format(Float.parseFloat(str)));
			if(num > productOutInfo.getNum()) {
				num =  productOutInfo.getNum();
				eturnNumTextFile.setText(num +"");
			}
			returnCashLable2.setText(num*productOutInfo.getPrice1() +"");
		}else {
			returnCashLable2.setText("0.0");
		}
	}
	
	private void cannel() {
		this.dispose();
	}
	
	private void ok() {
		String str = eturnNumTextFile.getText().trim();
		if(str != null && false == str.isEmpty()) {
			float num = Float.parseFloat(Constance.df.format(Float.parseFloat(str)));
			if(num > productOutInfo.getNum()) {
				num =  productOutInfo.getNum();
			}
			if(num == productOutInfo.getNum()) {
				productOutInfo.setState(2);
			}
			productOutInfo.setUserId(IntoFrame.getInst().getUser().getId());
			productOutInfo.setOpTime(new Date());
			productOutInfo.setNum(productOutInfo.getNum() - num);
			productOutInfo.setReturnNum(productOutInfo.getReturnNum() + num);
			ProductProvider.getInst().productTotalChange(productOutInfo.getProductId(), num);
			ProductOutInfoProvider.getInst().update(productOutInfo);
			OutOrder outOrder = ProductOutOrderProvider.getInst().query(productOutInfo.getOutId());
			outOrder.setCash(outOrder.getCash() - num * productOutInfo.getPrice1());
			if(outOrder.getCash() <= 0) {
				outOrder.setState(2);
			}
			ProductOutOrderProvider.getInst().update(outOrder);
			List<ProductOutInfo> list = ProductOutInfoProvider.getInst().queryByOrderId(productOutInfo.getOutId());
			table.replace(ProductOutInfoProvider.getTitle(), ProductOutInfoProvider.getInst().getListValue(list));
		}
		cannel();
	}
}
