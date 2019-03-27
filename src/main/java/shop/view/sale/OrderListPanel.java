package shop.view.sale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import shop.Constance;
import shop.DateChooser;
import shop.bean.OutOrder;
import shop.provider.ProductOutOrderProvider;

public class OrderListPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel queryPanel;
	private JLabel dayLabel;
	private JTextField startTimeField;
	private JButton queryButton;
    private JRadioButton yesterdayRadioButton;
    private JRadioButton todayRadioButton;
    
	JSeparator s1 = new JSeparator();
    
	private OrderInfoListPanel secondPanel;
	private JScrollPane scrollPane;
	
	private QueryOrderPanel queryOrderPanel;
	
	public OrderListPanel(QueryOrderPanel queryOrderPanel) {
		this.queryOrderPanel = queryOrderPanel;
		
		queryPanel = new JPanel();
		queryPanel.setBackground(Color.WHITE);
		queryPanel.setBorder(Constance.border);
		
		Calendar c = Calendar.getInstance();
		dayLabel = new JLabel("日期:");
		dayLabel.setFont(Constance.font15);
		startTimeField = new JTextField(Constance.dateFormt1.format(c.getTime()));
		startTimeField.setFont(Constance.font15);
		DateChooser.getInstance(startTimeField, "yyyy-MM-dd");
		
		queryButton = new JButton("查询");
		queryButton.setFont(Constance.font15);
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryButton();
			}
		});
		
		yesterdayRadioButton = new JRadioButton("昨天");
		yesterdayRadioButton.setFont(Constance.font15);
		yesterdayRadioButton.setBackground(Color.WHITE);
		yesterdayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		todayRadioButton = new JRadioButton("今天");
		todayRadioButton.setFont(Constance.font15);
		todayRadioButton.setBackground(Color.WHITE);
		todayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButtonSelect();
			}
		});
		ButtonGroup bg = new ButtonGroup();
		bg.add(yesterdayRadioButton);
		bg.add(todayRadioButton);
		todayRadioButton.setSelected(true);
		
		GroupLayout buttonLayout = new GroupLayout(queryPanel);
		queryPanel.setLayout(buttonLayout);
        buttonLayout.setHorizontalGroup(
            buttonLayout.createParallelGroup()
            .addGroup(buttonLayout.createSequentialGroup().addGap(10)
                .addGroup(buttonLayout.createParallelGroup()
                    .addGroup(buttonLayout.createSequentialGroup()
                        .addGroup(buttonLayout.createParallelGroup()
                            .addGroup(buttonLayout.createSequentialGroup()
                                .addComponent(yesterdayRadioButton)
                                .addComponent(todayRadioButton))
                            .addGroup(buttonLayout.createSequentialGroup()
                                .addComponent(dayLabel)
                                .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(buttonLayout.createSequentialGroup()
                        .addComponent(queryButton)))
                )
        );
        buttonLayout.setVerticalGroup(
            buttonLayout.createParallelGroup()
            .addGroup(buttonLayout.createSequentialGroup()
                .addGroup(buttonLayout.createParallelGroup()
                    .addComponent(dayLabel)
                    .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(buttonLayout.createParallelGroup()
                    .addComponent(yesterdayRadioButton)
                    .addComponent(todayRadioButton))
                .addComponent(queryButton)
               ).addGap(5)
        );
		
		secondPanel = new OrderInfoListPanel(queryOrderPanel);
		scrollPane = new JScrollPane(secondPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		GroupLayout mainLayout = new GroupLayout(this);
		this.setLayout(mainLayout);
		GroupLayout.SequentialGroup mainHgroup = mainLayout.createSequentialGroup();
		mainHgroup.addGroup(mainLayout.createParallelGroup()
					.addComponent(queryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
					.addComponent(s1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				);
		mainLayout.setHorizontalGroup(mainHgroup);
		
		GroupLayout.SequentialGroup mainVgroup = mainLayout.createSequentialGroup();
		mainVgroup.addGroup(mainLayout.createParallelGroup()
				.addComponent(queryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			);
		mainVgroup.addGroup(mainLayout.createParallelGroup()
				.addComponent(s1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			);
		mainVgroup.addGroup(mainLayout.createParallelGroup()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
			);
		mainLayout.setVerticalGroup(mainVgroup);
		
	}
	
	public void radioButtonSelect() {
		String startTime = "";
		Calendar c = Calendar.getInstance();
		if(yesterdayRadioButton.isSelected()) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			startTime = Constance.dateFormt1.format(c.getTime());
		}else if(todayRadioButton.isSelected()) {
			startTime = Constance.dateFormt1.format(c.getTime());
		}
		startTimeField.setText(startTime);
	}
	
	private void queryButton() {
		try {
			Date startDate = Constance.dateFormt1.parse(startTimeField.getText().trim());
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			c.add(Calendar.DAY_OF_YEAR, 1);
			Date endDate = c.getTime();
			List<OutOrder> list = ProductOutOrderProvider.getInst().query(startDate, endDate, 0);
			secondPanel.clean();
			for(OutOrder outOrder : list) {
				if(outOrder.getState() == 2) {
					continue;
				}
				secondPanel.addOutOrder(outOrder);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
