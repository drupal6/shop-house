package shop.view.manage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import shop.Constance;
import shop.DateChooser;
import shop.beam.OutOrder;
import shop.provider.ProductOutOrderProvider;

public class ProductSaleOrderPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel queryPanel;
	private JLabel dayLabel;
	private JTextField startTimeField;
	private JLabel splitLable;
	private JTextField endTimeField;
	private JLabel userLabel;
	private JTextField userField;
	private JButton queryButton;
	private JButton resetButton;
    private JRadioButton yesterdayRadioButton;
    private JRadioButton todayRadioButton;
    private JRadioButton toweekRadioButton;
    private JRadioButton tomonthRadioButton;
    private JRadioButton toyearRadioButton;
    
	private JPanel secondPanel;
	
	private MyTable table;
	
	public ProductSaleOrderPanel(String title) {
		
		queryPanel = new JPanel();
		queryPanel.setBackground(Color.WHITE);
		queryPanel.setBorder(Constance.border);
		
		Calendar c = Calendar.getInstance();
		dayLabel = new JLabel("日期:");
		startTimeField = new JTextField(Constance.dateFormt1.format(c.getTime()));
		splitLable = new JLabel("--");
		c.add(Calendar.DAY_OF_YEAR, 1);
		endTimeField = new JTextField(Constance.dateFormt1.format(c.getTime()));
		DateChooser.getInstance(startTimeField, "yyyy-MM-dd");
		DateChooser.getInstance(endTimeField, "yyyy-MM-dd");
		
		userLabel = new JLabel("操作员:");
		userField = new JTextField();
		
		queryButton = new JButton("查询");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryButton();
			}
		});
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		yesterdayRadioButton = new JRadioButton("昨天");
		yesterdayRadioButton.setBackground(Color.WHITE);
		yesterdayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		todayRadioButton = new JRadioButton("今天");
		todayRadioButton.setBackground(Color.WHITE);
		todayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		toweekRadioButton = new JRadioButton("本周");
		toweekRadioButton.setBackground(Color.WHITE);
		toweekRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		tomonthRadioButton = new JRadioButton("本月");
		tomonthRadioButton.setBackground(Color.WHITE);
		tomonthRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		toyearRadioButton = new JRadioButton("今年");
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
		
		GroupLayout buttonLayout = new GroupLayout(queryPanel);
		queryPanel.setLayout(buttonLayout);
		 buttonLayout.setHorizontalGroup(
		            buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(buttonLayout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(dayLabel)
		                    .addComponent(userLabel))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, buttonLayout.createSequentialGroup()
		                        .addComponent(queryButton)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addComponent(resetButton))
		                    .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                        .addGroup(buttonLayout.createSequentialGroup()
		                            .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(splitLable)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addGap(12, 12, 12)
		                            .addComponent(yesterdayRadioButton)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(todayRadioButton)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(toweekRadioButton)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(tomonthRadioButton)
		                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                            .addComponent(toyearRadioButton))
		                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                )
		        );
		        buttonLayout.setVerticalGroup(
		            buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(buttonLayout.createSequentialGroup()
		                .addGap(27, 27, 27)
		                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(dayLabel)
		                    .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(splitLable)
		                    .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(toyearRadioButton)
		                    .addComponent(todayRadioButton)
		                    .addComponent(toweekRadioButton)
		                    .addComponent(tomonthRadioButton)
		                    .addComponent(yesterdayRadioButton))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(userLabel)
		                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(queryButton)
		                    .addComponent(resetButton))
		                .addContainerGap(10, 10))
		        );
		
		secondPanel = new JPanel();
		
		table = new MyTable(ProductOutOrderProvider.getTitle(), ProductOutOrderProvider.getListValue(null), 500, 300);
		table.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					tableSelectListener();
				}
			}
		});
		
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
		radioButtonSelect();
		todayRadioButton.setSelected(true);
		userField.setText("");
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
	
	private void tableSelectListener() {
		int selectRow = table.getTable().getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		int orderId = (Integer) table.getTable().getValueAt(selectRow, 0);
		OutOrder outOrder = ProductOutOrderProvider.getInst().query(orderId);
		if(outOrder != null) {
			ProductOutInfoDialog dialog = new ProductOutInfoDialog(orderId, "销售单详细");
			dialog.setVisible(true);
		}
	}
	
	private void queryButton() {
		String ustr = userField.getText().trim();
		int uid = 0;
		if(ustr != null && false == ustr.isEmpty()) {
			uid = Integer.parseInt(ustr);
		}
		try {
			Date startDate = Constance.dateFormt1.parse(startTimeField.getText().trim());
			Date endDate = Constance.dateFormt1.parse(endTimeField.getText().trim());
			List<OutOrder> list = ProductOutOrderProvider.getInst().query(startDate, endDate, uid);
			table.replace(ProductOutOrderProvider.getTitle(), ProductOutOrderProvider.getListValue(list));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
