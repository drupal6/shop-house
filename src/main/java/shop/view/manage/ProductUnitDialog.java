package shop.view.manage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.Constance;
import shop.beam.ProductType;
import shop.beam.ProductUnit;
import shop.provider.ProductTypeProvider;
import shop.provider.ProductUnitProvider;

public class ProductUnitDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int op = 0;
	private int id;
	private int selectIndex;
	
	private JPanel conentPanel;
	private JPanel buttonPanel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton saveButton;
	private JButton saveAndAddButton;
	private JButton cannelButton;
	private MyTable table;
	
	public ProductUnitDialog(MyTable table, int op, String title) { 
		this.table = table;
		this.op = op;
		this.setTitle(title);
		this.setSize(300, 190);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		Point topLeft = ManageFrame.getInst().getLocationOnScreen();
		Dimension parentSize = ManageFrame.getInst().getSize();
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
		
		conentPanel = new JPanel();
		conentPanel.setBackground(Color.WHITE);
		nameLabel = new JLabel("名称");
		nameLabel.setFont(Constance.font22);
		nameTextField = new JTextField();
		nameTextField.setFont(Constance.font22);
		
		GroupLayout conentLayout = new GroupLayout(conentPanel);
		conentPanel.setLayout(conentLayout);
		GroupLayout.SequentialGroup cHGroup = conentLayout.createSequentialGroup();
		cHGroup.addGap(20);
		cHGroup.addGroup(conentLayout.createParallelGroup().addComponent(nameLabel));
		cHGroup.addGap(10);
		cHGroup.addGroup(conentLayout.createParallelGroup().addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE));
		conentLayout.setHorizontalGroup(cHGroup);
		
		GroupLayout.SequentialGroup cVGroup = conentLayout.createSequentialGroup();
		cVGroup.addGap(20);
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nameLabel)
				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		conentLayout.setVerticalGroup(cVGroup);
		
		buttonPanel = new JPanel();
		saveButton = new JButton("保存"); 
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(save()) {
					dispose();
				}
			}
		});
		saveAndAddButton = new JButton("保存并添加"); 
		saveAndAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndAdd();
			}
		});
		cannelButton = new JButton("取消"); 
		cannelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cannel();
			}
		});
		
		GroupLayout buttonLayout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(buttonLayout);
		
		GroupLayout.SequentialGroup bHGroup = buttonLayout.createSequentialGroup();
		bHGroup.addGap(30);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveButton));
		bHGroup.addGap(10);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveAndAddButton));
		bHGroup.addGap(10);
		bHGroup.addGroup(buttonLayout.createParallelGroup().addComponent(cannelButton));
		buttonLayout.setHorizontalGroup(bHGroup);
		
		GroupLayout.SequentialGroup bVGroup = buttonLayout.createSequentialGroup();
		bVGroup.addGap(20);
		bVGroup.addGroup(buttonLayout.createParallelGroup().addComponent(saveButton)
				.addComponent(saveAndAddButton)
				.addComponent(cannelButton));
		buttonLayout.setVerticalGroup(bVGroup);
		
		GroupLayout framLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(framLayout);
		
		GroupLayout.SequentialGroup fHGroup = framLayout.createSequentialGroup();
		fHGroup.addGroup(framLayout.createParallelGroup()
				.addComponent(conentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		framLayout.setHorizontalGroup(fHGroup);
		
		GroupLayout.SequentialGroup fVGroup = framLayout.createSequentialGroup();
		fVGroup.addGroup(framLayout.createParallelGroup().addComponent(conentPanel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		fVGroup.addGroup(framLayout.createParallelGroup().addComponent(buttonPanel,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE));
		framLayout.setVerticalGroup(fVGroup);
		
		if(op == 2) {
			saveAndAddButton.setEnabled(false);
			selectIndex = table.getTable().getSelectedRow();
			id = (Integer) table.getTable().getValueAt(selectIndex, 0);
			ProductUnit productUnit = ProductUnitProvider.getInst().get(id);
			if(productUnit != null) {
				nameTextField.setText(productUnit.getName());
			}
		}
	}
	
	private boolean save() {
		String name = nameTextField.getText().trim();
		if(name.isEmpty()) {
			return false;
		}
		if(op == 1) {
			ProductUnit productType = ProductUnitProvider.getInst().getByName(name);
			if(productType != null) {
				return false;
			}
			productType = new ProductUnit();
			productType.setName(name);
			productType = ProductUnitProvider.getInst().add(productType);
			if(productType == null) {
				return false;
			}
			table.getTableModel().addRow(ProductUnitProvider.getValue(productType));
			return true;
		}else {
			ProductUnit productType = ProductUnitProvider.getInst().get(id);
			if(productType == null) {
				return false;
			}
			if(productType.getName().equals(name)) {
				return false;
			}
			ProductUnit productType1 = ProductUnitProvider.getInst().getByName(name);
			if(productType1 != null) {
				return false;
			}
			productType.setName(name);
			if(false == ProductUnitProvider.getInst().update(productType)) {
				return false;
			}
			table.getTable().setValueAt(name, selectIndex, 1);
			dispose();
			return true;
		}
	}
	
	private void saveAndAdd() {
		if(save()) {
			nameTextField.setText("");
		}
	}
	
	private void cannel() {
		this.dispose();
	}
}
