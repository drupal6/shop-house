package shop.view.sale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import shop.Constance;
import shop.Shop;
import shop.provider.CashProvider;

public class CashPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel titlePanel;
	private JLabel titleLable;
	
	
	private JPanel contentPanel;
	private JLabel optationLable;
	private JRadioButton takeButton;
	private JRadioButton saveButton;
	private JLabel numLable;
	private JTextField numTextField;
	private JLabel markLabel;
	private JTextArea markTextArea;

	private JButton cannelButton;
	private JButton okButton;
	ButtonGroup bg = new ButtonGroup();
	
	private SaleView saleView;
	
	public CashPanel(SaleView saleView) {
		this.saleView = saleView;
		this.setBackground(new Color(64, 64, 64));

		optationLable = new javax.swing.JLabel();
		takeButton = new javax.swing.JRadioButton();
		saveButton = new javax.swing.JRadioButton();
		numLable = new javax.swing.JLabel();
		numTextField = new javax.swing.JTextField();
		markLabel = new javax.swing.JLabel();
		markTextArea = new javax.swing.JTextArea();
		cannelButton = new javax.swing.JButton();
		okButton = new javax.swing.JButton();
		titlePanel = new javax.swing.JPanel();
		titlePanel.setBackground(new Color(143, 143, 143));
		titleLable = new javax.swing.JLabel();

		optationLable.setFont(Constance.font21); // NOI18N
		optationLable.setText("操作：");

		takeButton.setFont(Constance.font21); // NOI18N
		takeButton.setText("取出");

		saveButton.setFont(Constance.font21); // NOI18N
		saveButton.setText("存进");
		
		bg.add(takeButton);
		bg.add(saveButton);
		takeButton.setSelected(true);
		
		numLable.setFont(Constance.font21); // NOI18N
		numLable.setText("数量：");

		numTextField.setFont(Constance.font21); // NOI18N
		numTextField.setText("0.0");
		numTextField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				int keyChar=arg0.getKeyChar();
				if ((keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) || keyChar == 46) {
					String str = numTextField.getText().trim();
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
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) { }
		});

		markLabel.setFont(Constance.font21); // NOI18N
		markLabel.setText("备注：");

		markTextArea.setColumns(20);
		markTextArea.setFont(Constance.font21); // NOI18N
		markTextArea.setRows(5);

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

		titleLable.setFont(Constance.font21); // NOI18N
		titleLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLable.setText("零钱");

		javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(titleLable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        contentPanel = new JPanel();
        
        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(optationLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(takeButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(markLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(cannelButton)
                                .addGap(47, 47, 47)
                                .addComponent(okButton))
                            .addComponent(markTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(numLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optationLable)
                    .addComponent(takeButton)
                    .addComponent(saveButton))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numLable)
                    .addComponent(numTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(markLabel)
                    .addComponent(markTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton)
                    .addComponent(cannelButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGap(10);
        hGroup.addGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
            )
        );
        hGroup.addGap(10);
        layout.setHorizontalGroup(hGroup);
        	
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(
    		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
        );
        vGroup.addGap(10);
        layout.setVerticalGroup(vGroup);
	}
	
	private void cannel() {
		saleView.setLayerPanel(300, 200);
	}
	private void ok() {
		int type = 2;
		if(saveButton.isSelected()) {
			type = 1;
		}
		float num = 0.0f;
		String str = numTextField.getText().trim();
		if(str != null && false == str.isEmpty()) {
			num = Float.parseFloat(Constance.df.format(Float.parseFloat(str)));
		}
		String mark = markTextArea.getText().trim();
		CashProvider.getInst().addCashLog(num, type, Shop.getInst().getUser().getId(), mark);
		cannel();
	}
}
