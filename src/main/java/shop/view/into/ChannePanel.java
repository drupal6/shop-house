package shop.view.into;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shop.Constance;

public class ChannePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nameTipLable;
	private JLabel numTotalLable;
	private JTextField chanceInput;
	private JButton delBtn;
	private JButton canBtn;
	private JButton okBtn;

	private float num;
	//1数量 2价格
	private int type;
	
	public ChannePanel(float num, int type) {
		this.type = type;
		this.setBackground(new Color(64, 64, 64));
		this.num = num;
		if(type == 1) {
			nameTipLable = new JLabel("数量:");
		}else {
			nameTipLable = new JLabel("单价:");
		}
		nameTipLable.setFont(Constance.fontB30);
		numTotalLable = new JLabel("" + num);
		numTotalLable.setFont(Constance.fontB30);
		numTotalLable.setHorizontalAlignment(SwingConstants.TRAILING);
		chanceInput = new JTextField();
		chanceInput.setFont(Constance.fontB30);
		chanceInput.setText("" + num);
		chanceInput.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				int keyChar=arg0.getKeyChar();
				if(keyChar == KeyEvent.VK_ENTER) {
					ok();
					return;
				}
				if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) || keyChar == 46) {
					String str = chanceInput.getText().trim();
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
		if(type == 1) {
			delBtn = new JButton("删除");
		}else {
			delBtn = new JButton("还原");
		}
		delBtn.setFont(Constance.fontB30);
		delBtn.setForeground(new Color(254, 127, 106));
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				del();
			}
		});
		canBtn = new JButton("返回");
		canBtn.setFont(Constance.fontB30);
		canBtn.setForeground(new Color(86, 185, 242));
		canBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IntoFrame.getInst().setLayerPanel(300, 200);
			}
		});
		okBtn = new JButton("确定");
		okBtn.setFont(Constance.fontB30);
		okBtn.setForeground(new Color(246, 192, 45));
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ok();
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
	                        .addComponent(delBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(canBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(okBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
	                    .addGroup(haveLayout.createSequentialGroup()
	                        .addGroup(haveLayout.createParallelGroup()
	                            .addGroup(haveLayout.createSequentialGroup()
	                                .addComponent(nameTipLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(numTotalLable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
	                            .addComponent(chanceInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
	                        )).addGap(10)
	                )
        );
        haveLayout.setVerticalGroup(
            haveLayout.createParallelGroup()
            .addGroup(haveLayout.createSequentialGroup().addGap(10)
                .addGroup(haveLayout.createParallelGroup()
                    .addComponent(nameTipLable)
                    .addComponent(numTotalLable))
                .addComponent(chanceInput)
                .addGroup(haveLayout.createParallelGroup()
                    .addComponent(delBtn)
                    .addComponent(canBtn)
                    .addComponent(okBtn)).addGap(10))
        );
		
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(10);
        hGroup.addGroup(layout.createParallelGroup()
        		.addComponent(havePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
    		);
        hGroup.addGap(10);
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup()
        		.addComponent(havePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
    		);
        vGroup.addGap(10);
        layout.setVerticalGroup(vGroup);
	}
	
	private void del() {
		if(type == 1) {
			IntoFrame.getInst().getOrderPanel().getProductOrderListPanel().remove();
		}else {
			IntoFrame.getInst().getOrderPanel().getProductOrderListPanel().resetPrice();
		}
		IntoFrame.getInst().setLayerPanel(300, 200);
	}
	private void ok() {
		if(type == 1) {
			IntoFrame.getInst().getOrderPanel().getProductOrderListPanel().alertNum(num);
		}else {
			IntoFrame.getInst().getOrderPanel().getProductOrderListPanel().alertPrice(num);
		}
		IntoFrame.getInst().setLayerPanel(300, 200);
	}
	
	private void changeInputChange() {
		String str = chanceInput.getText().trim();
		if(str != null && false == str.isEmpty()) {
			num = Float.parseFloat(Constance.df.format(Float.parseFloat(str)));
			numTotalLable.setText(num +"");
		}else {
			num = 0.0f;
			numTotalLable.setText(num +"");
		}
	}
}
