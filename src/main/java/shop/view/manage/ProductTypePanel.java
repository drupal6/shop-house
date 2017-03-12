package shop.view.manage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shop.Constance;
import shop.provider.ProductTypeProvider;

public class ProductTypePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel bttonPanel;
	private JButton addButton;
	private JButton alertButton;
	private JButton delButton;
	private JTextField searchTextField;
	private JButton searchButton;
	private String title;
	
	private JPanel secondPanel;
	
	private MyTable table;
	
	public ProductTypePanel(String title) {
		this.title = title;
		
		bttonPanel = new JPanel();
		bttonPanel.setBackground(Color.WHITE);
		bttonPanel.setBorder(Constance.border);
		
		addButton = new JButton();
		addButton.setText("新增");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addButtonListener();
			}
		});
		
		alertButton = new JButton();
		alertButton.setText("修改");
		alertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alertButtonListener();
			}
		});
		
		delButton = new JButton();
		delButton.setText("删除");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delButtonListener();
			}
		});
		
		searchTextField = new JTextField();
		
		searchButton = new JButton();
		searchButton.setText("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchButtonListener();
			}
		});
		
		GroupLayout buttonLayout = new GroupLayout(bttonPanel);
		bttonPanel.setLayout(buttonLayout);
		GroupLayout.SequentialGroup buttonHGroup = buttonLayout.createSequentialGroup();
		buttonHGroup.addGap(50);
		buttonHGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(addButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		buttonHGroup.addGap(5);
		buttonHGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(alertButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		buttonHGroup.addGap(5);
		buttonHGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(delButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		buttonHGroup.addGap(100);
		buttonHGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE));
		buttonHGroup.addGap(5);
		buttonHGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		buttonLayout.setHorizontalGroup(buttonHGroup);
		
		GroupLayout.SequentialGroup buttonVGroup = buttonLayout.createSequentialGroup();
		buttonVGroup.addContainerGap(20, 20);
		buttonVGroup.addGroup(buttonLayout.createParallelGroup()
				.addComponent(addButton)
				.addComponent(alertButton)
				.addComponent(delButton)
				.addComponent(searchTextField)
				.addComponent(searchButton));
		buttonVGroup.addContainerGap(20, 20);
		buttonLayout.setVerticalGroup(buttonVGroup);
		
		secondPanel = new JPanel();
		secondPanel.setBackground(Color.red);
		
		table = new MyTable(ProductTypeProvider.getTitle(), ProductTypeProvider.getListValue(ProductTypeProvider.getInst().list()), 400, 300);
		table.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					alertButtonListener();
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
				.addComponent(bttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(bttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(layout.createParallelGroup().addComponent(secondPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addButtonListener() {
		ProductTypeDialog dialog = new ProductTypeDialog(table, 1, title + "-" + addButton.getText());
		dialog.setVisible(true);
	}
	private void alertButtonListener() {
		int selectRow = table.getTable().getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		ProductTypeDialog dialog = new ProductTypeDialog(table, 2, title + "-" + alertButton.getText());
		dialog.setVisible(true);
	}
	private void delButtonListener() {
		
	}
	private void searchButtonListener() {
		
	}
	
}
