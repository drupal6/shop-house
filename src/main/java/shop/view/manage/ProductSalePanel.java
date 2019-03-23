package shop.view.manage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import shop.Constance;
import shop.DateChooser;
import shop.beam.OutOrder;
import shop.beam.ProductOutInfo;
import shop.beam.TreeNode;
import shop.provider.ProductOutInfoProvider;
import shop.provider.ProductOutOrderProvider;
import shop.provider.ProductProvider;
import shop.provider.ProductTypeProvider;

public class ProductSalePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel queryPanel;
	private JLabel dayLabel;
	private JTextField startTimeField;
	private JLabel splitLable;
	private JTextField endTimeField;
	private JLabel typeLabel;
	private JComboBox<TreeNode> typeNode;
	private JLabel productLabel;
	private JComboBox<TreeNode> productNode;
	private JButton queryButton;
	private JButton resetButton;
    private JRadioButton yesterdayRadioButton;
    private JRadioButton todayRadioButton;
    private JRadioButton toweekRadioButton;
    private JRadioButton tomonthRadioButton;
    private JRadioButton toyearRadioButton;
    private JLabel orderNumNameLabel;
    private JLabel orderNumLabel;
    private JLabel cashNumNameLabel;
    private JLabel cashNumLabel;
    
	private JPanel secondPanel;
	
	private MyTable table;
	
	public ProductSalePanel(String title) {
		
		queryPanel = new JPanel();
		queryPanel.setBackground(Color.WHITE);
		queryPanel.setBorder(Constance.border);
		
		Calendar c = Calendar.getInstance();
		dayLabel = new JLabel("日期:");
		dayLabel.setFont(Constance.font21);
		startTimeField = new JTextField(Constance.dateFormt1.format(c.getTime()));
		startTimeField.setFont(Constance.font21);
		splitLable = new JLabel("--");
		splitLable.setFont(Constance.font21);
		c.add(Calendar.DAY_OF_YEAR, 1);
		endTimeField = new JTextField(Constance.dateFormt1.format(c.getTime()));
		endTimeField.setFont(Constance.font21);
		DateChooser.getInstance(startTimeField, "yyyy-MM-dd");
		DateChooser.getInstance(endTimeField, "yyyy-MM-dd");
		
		typeLabel = new JLabel("类别:");
		typeLabel.setFont(Constance.font21);
		typeNode = new JComboBox<TreeNode>();
		typeNode.setFont(Constance.font21);
		List<TreeNode> typeList = ProductTypeProvider.getInst().toTreeNodeList();
		typeNode.addItem(new TreeNode(0, 1, ""));
		for(TreeNode tn : typeList) {
			typeNode.addItem(tn);
		}
		typeNode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				typeItemSelect();
			}
		});
		productLabel = new JLabel("商品:");
		productLabel.setFont(Constance.font21);
		productNode = new JComboBox();
		productNode.addItem(new TreeNode(0, 3, ""));
		productNode.setFont(Constance.font21);
		
		queryButton = new JButton("查询");
		queryButton.setFont(Constance.font21);
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryButton();
			}
		});
		resetButton = new JButton("重置");
		resetButton.setFont(Constance.font21);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		yesterdayRadioButton = new JRadioButton("昨天");
		yesterdayRadioButton.setFont(Constance.font21);
		yesterdayRadioButton.setBackground(Color.WHITE);
		yesterdayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		todayRadioButton = new JRadioButton("今天");
		todayRadioButton.setFont(Constance.font21);
		todayRadioButton.setBackground(Color.WHITE);
		todayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		toweekRadioButton = new JRadioButton("本周");
		toweekRadioButton.setFont(Constance.font21);
		toweekRadioButton.setBackground(Color.WHITE);
		toweekRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		tomonthRadioButton = new JRadioButton("本月");
		tomonthRadioButton.setFont(Constance.font21);
		tomonthRadioButton.setBackground(Color.WHITE);
		tomonthRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		toyearRadioButton = new JRadioButton("今年");
		toyearRadioButton.setFont(Constance.font21);
		toyearRadioButton.setBackground(Color.WHITE);
		toyearRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(yesterdayRadioButton);
		bg.add(todayRadioButton);
		todayRadioButton.setSelected(true);
		bg.add(toweekRadioButton);
		bg.add(tomonthRadioButton);
		bg.add(toyearRadioButton);
		
		orderNumNameLabel = new JLabel("条数:");
		orderNumNameLabel.setFont(Constance.font21);
		orderNumLabel = new JLabel("0");
		orderNumLabel.setFont(Constance.font21);
		cashNumNameLabel = new JLabel("总额:");
		cashNumNameLabel.setFont(Constance.font21);
		cashNumLabel = new JLabel("0.0");
		cashNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cashNumLabel.setFont(Constance.font21);
		
		GroupLayout buttonLayout = new GroupLayout(queryPanel);
		queryPanel.setLayout(buttonLayout);
		buttonLayout.setHorizontalGroup(
	            buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(buttonLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(typeLabel)
	                    .addComponent(dayLabel))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(buttonLayout.createSequentialGroup()
	                        .addComponent(typeNode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(productLabel)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(productNode, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(14, 14, 14)
	                        .addComponent(orderNumNameLabel)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(orderNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(cashNumNameLabel)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(cashNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        )
	                    .addGroup(buttonLayout.createSequentialGroup()
	                        .addComponent(queryButton)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(resetButton)
	                        )
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buttonLayout.createSequentialGroup()
	                        .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(splitLable)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addComponent(yesterdayRadioButton)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(todayRadioButton)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(toweekRadioButton)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(tomonthRadioButton)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(toyearRadioButton)
	                        )))
	        );
	        buttonLayout.setVerticalGroup(
	            buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(buttonLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(dayLabel)
	                    .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(splitLable)
	                    .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(yesterdayRadioButton)
	                    .addComponent(todayRadioButton)
	                    .addComponent(toweekRadioButton)
	                    .addComponent(tomonthRadioButton)
	                    .addComponent(toyearRadioButton))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(typeLabel)
	                    .addComponent(orderNumNameLabel)
	                    .addComponent(orderNumLabel)
	                    .addComponent(cashNumNameLabel)
	                    .addComponent(cashNumLabel)
	                    .addComponent(typeNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(productLabel)
	                    .addComponent(productNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(queryButton)
	                    .addComponent(resetButton))
	                .addGap(10)
	                )
	        );
	        
        secondPanel = new JPanel();
    	table = new MyTable(ProductOutInfoProvider.getTitle(), ProductOutInfoProvider.getListValue(null), 500, 300);
		table.getTable().setFont(Constance.font21);
		
		GroupLayout tablePanelLayout = new GroupLayout(secondPanel);
		secondPanel.setLayout(tablePanelLayout);
		GroupLayout.SequentialGroup tablehGroup = tablePanelLayout.createSequentialGroup();
		tablehGroup.addGroup(tablePanelLayout.createParallelGroup().addComponent(table));
		tablePanelLayout.setHorizontalGroup(tablehGroup);
		GroupLayout.SequentialGroup tablevGroup = tablePanelLayout.createSequentialGroup();
		tablevGroup.addGroup(tablePanelLayout.createParallelGroup().addComponent(table));
		tablePanelLayout.setVerticalGroup(tablevGroup);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(queryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(queryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
	}
	
	public void reset() {
		todayRadioButton.setSelected(true);
		typeNode.setSelectedIndex(0);
		productNode.setSelectedIndex(0);
		radioButtonSelect();
	}
	
	public void radioButtonSelect() {
		String startTime = "";
		String endTime = "";
		Calendar c = Calendar.getInstance();
		if(yesterdayRadioButton.isSelected()) {
			c.add(Calendar.DAY_OF_YEAR, -2);
			startTime = Constance.dateFormt1.format(c.getTime());
			c.add(Calendar.DAY_OF_YEAR, 1);
			endTime = Constance.dateFormt1.format(c.getTime());
		}else if(todayRadioButton.isSelected()) {
			startTime = Constance.dateFormt1.format(c.getTime());
			c.add(Calendar.DAY_OF_YEAR, 1);
			endTime = Constance.dateFormt1.format(c.getTime());
		}else if(toweekRadioButton.isSelected()) {
			c.set(Calendar.DAY_OF_WEEK, 1);
			startTime = Constance.dateFormt1.format(c.getTime());
			c.add(Calendar.DAY_OF_YEAR, 6);
			endTime = Constance.dateFormt1.format(c.getTime());
		}else if(tomonthRadioButton.isSelected()) {
			c.set(Calendar.DAY_OF_MONTH, 1);
			startTime = Constance.dateFormt1.format(c.getTime());
			c.add(Calendar.DAY_OF_YEAR, c.getMaximum(Calendar.DAY_OF_MONTH));
			endTime = Constance.dateFormt1.format(c.getTime());
		}else if(toyearRadioButton.isSelected()) {
			c.set(Calendar.DAY_OF_YEAR, 1);
			startTime = Constance.dateFormt1.format(c.getTime());
			c.add(Calendar.DAY_OF_YEAR, c.getMaximum(Calendar.DAY_OF_YEAR)-1);
			endTime = Constance.dateFormt1.format(c.getTime());
		}
		startTimeField.setText(startTime);
		endTimeField.setText(endTime);
	}
	
	private void typeItemSelect() {
		TreeNode treeNode = (TreeNode) typeNode.getSelectedItem();
		productNode.removeAllItems();
		productNode.addItem(new TreeNode(0, 3, ""));
		if(treeNode.getId() > 0) {
			List<TreeNode> nodeList = ProductProvider.getInst().getTreeNode(treeNode.getId());
			for(TreeNode node : nodeList) {
				productNode.addItem(node);
			}
		}
		productNode.updateUI();
	}
	
	private void queryButton() {
		TreeNode treeNodeType = (TreeNode) typeNode.getSelectedItem();
		TreeNode treeNodeProduct = (TreeNode) productNode.getSelectedItem();
		int productTypeId = treeNodeType.getId();
		int productId = treeNodeProduct.getId();
		try {
			Date startDate = Constance.dateFormt1.parse(startTimeField.getText().trim());
			Date endDate = Constance.dateFormt1.parse(endTimeField.getText().trim());
			List<ProductOutInfo> list = ProductOutInfoProvider.getInst().queryByOrder(productTypeId, productId, startDate, endDate);
			table.replace(ProductOutInfoProvider.getTitle(), ProductOutInfoProvider.getListValue(list));
			orderNumLabel.setText(list.size()+"");
			float cash = 0.0f;
			for(ProductOutInfo oo : list) {
				cash += oo.getNum() * oo.getPrice1();
			}
			cashNumLabel.setText(cash+"");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
