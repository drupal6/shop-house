package shop.view.sale;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import shop.Constance;
import shop.beam.Product;

public class ProductOrderPanel extends JPanel{

	private Product product;
	private float num;
	private float outPrice;
	
	private int index;
	
	private JLabel nameLable;
	private JLabel outPriceLable;
	private JLabel numLable;
	private JLabel totalLable;
	
	
	public ProductOrderPanel(Product product, int index) {
		setBackground(new Color(240, 240, 240));
		this.product = product;
		this.index = index;
		num = 1;
		outPrice = product.getOutPrice();
		
		nameLable = new JLabel(product.getName());
		nameLable.setFont(Constance.font15);
		outPriceLable = new JLabel("￥"+outPrice);
		outPriceLable.setFont(Constance.fontB10);
		numLable = new JLabel("数量:"+num);
		numLable.setFont(Constance.fontB10);
		totalLable = new JLabel("￥"+num * outPrice);
		totalLable.setHorizontalAlignment(SwingConstants.TRAILING);
		totalLable.setFont(Constance.font24);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(nameLable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(outPriceLable)
                            .addComponent(numLable))
                        .addComponent(totalLable, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameLable)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outPriceLable)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numLable))
                    .addComponent(totalLable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                )
        );
	}
	
	public void select(boolean select) {
		if(select) {
			setBackground(new Color(240, 230, 140));
		}else {
			setBackground(new Color(253, 245, 230));
		}
	}

	public float getNum() {
		return num;
	}

	public float getOutPrice() {
		return outPrice;
	}

	public int getIndex() {
		return index;
	}
}
