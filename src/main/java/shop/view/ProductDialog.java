package shop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import shop.Constance;
import shop.beam.Product;
import shop.beam.ProductType;
import shop.beam.ProductUnit;
import shop.beam.TreeNode;
import shop.provider.ProductProvider;
import shop.provider.ProductTypeProvider;
import shop.provider.ProductUnitProvider;

public class ProductDialog extends JDialog{
	
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
	
	private JLabel unitLabel;
	private JComboBox<TreeNode> unitComboBox;
	
	private JLabel typeLabel;
	private JComboBox<TreeNode> typeComboBox;
	
	private JLabel outPriceLabel;
	private JTextField outPriceTextField;
	
	private JLabel stockLabel;
	private JTextField stockTextField;
	
	private JButton saveButton;
	private JButton saveAndAddButton;
	private JButton cannelButton;
	private MyTable table;
	
	private List<TreeNode> unitNodes;
	private List<TreeNode> typeNodes;
	
	public ProductDialog(MyTable table, int op, String title, int productType) { 
		this.table = table;
		this.op = op;
		this.setTitle(title);
		this.setSize(330, 270);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		Point topLeft = ShopFrame.getInst().getLocationOnScreen();
		Dimension parentSize = ShopFrame.getInst().getSize();
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
		nameLabel.setFont(Constance.dialogLableFont);
		nameTextField = new JTextField();
		nameTextField.setFont(Constance.dialogLableFont);
		
		unitLabel = new JLabel("单位");
		unitLabel.setFont(Constance.dialogLableFont);
		unitComboBox = new JComboBox();
		unitComboBox.setFont(Constance.dialogLableFont);
		unitNodes  = ProductUnitProvider.getInst().toTreeNodeList();
		for(TreeNode unitNode : unitNodes) {
			unitComboBox.addItem(unitNode);
		}
		unitComboBox.setSelectedItem(null);
		
		typeLabel = new JLabel("类别");
		typeLabel.setFont(Constance.dialogLableFont);
		typeComboBox = new JComboBox();
		typeComboBox.setFont(Constance.dialogLableFont);
		typeNodes  = ProductTypeProvider.getInst().toTreeNodeList();
		TreeNode selectNode = null;
		for(TreeNode typeNode : typeNodes) {
			typeComboBox.addItem(typeNode);
			if(typeNode.getId() == productType) {
				selectNode = typeNode;
			}
		}
		if(typeComboBox != null) {
			typeComboBox.setSelectedItem(selectNode);
		}
		
		outPriceLabel = new JLabel("出售价格");
		outPriceLabel.setFont(Constance.dialogLableFont);
		outPriceTextField = new JTextField();
		outPriceTextField.setFont(Constance.dialogLableFont);
		
		stockLabel = new JLabel("库存");
		stockLabel.setFont(Constance.dialogLableFont);
		stockTextField = new JTextField();
		stockTextField.setFont(Constance.dialogLableFont);
		
		GroupLayout conentLayout = new GroupLayout(conentPanel);
		conentPanel.setLayout(conentLayout);
		GroupLayout.SequentialGroup cHGroup = conentLayout.createSequentialGroup();
		cHGroup.addGap(20);
		cHGroup.addGroup(conentLayout.createParallelGroup().addComponent(nameLabel)
				.addComponent(unitLabel)
				.addComponent(typeLabel)
				.addComponent(outPriceLabel)
				.addComponent(stockLabel)
				);
		cHGroup.addGap(10);
		cHGroup.addGroup(conentLayout.createParallelGroup().addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(unitComboBox, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(outPriceTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				.addComponent(stockTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
				);
		conentLayout.setHorizontalGroup(cHGroup);
		
		GroupLayout.SequentialGroup cVGroup = conentLayout.createSequentialGroup();
		cVGroup.addGap(20);
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(nameLabel)
				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(unitLabel)
				.addComponent(unitComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(typeLabel)
				.addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(outPriceLabel)
				.addComponent(outPriceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		cVGroup.addGroup(conentLayout.createParallelGroup()
				.addComponent(stockLabel)
				.addComponent(stockTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
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
		bHGroup.addGap(40);
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
			Product product = ProductProvider.getInst().get(id);
			if(product != null) {
				nameTextField.setText(product.getName());
				unitComboBox.setSelectedItem(getNode(unitNodes, product.getProductUnit()));
				typeComboBox.setSelectedItem(getNode(typeNodes, product.getProductType()));
				outPriceTextField.setText(product.getOutPrice() + "");
				stockTextField.setText(product.getStock()+"");
			}
		}
	}
	
	private TreeNode getNode(List<TreeNode> nodes, int id) {
		for(TreeNode node : nodes) {
			if(node.getId() == id) {
				return node;
			}
		}
		return null;
	}
	
	private boolean save() {
		String name = nameTextField.getText().trim();
		TreeNode unitNode = (TreeNode) unitComboBox.getSelectedItem();
		TreeNode typeNode = (TreeNode) typeComboBox.getSelectedItem();
		String outPrice = outPriceTextField.getText().trim();
		String stock = stockTextField.getText().trim();
		if(name.isEmpty()) {
			return false;
		}
		if(outPrice.isEmpty()) {
			return false;
		}
		if(stock.isEmpty()) {
			return false;
		}
		ProductUnit productUnit = ProductUnitProvider.getInst().get(unitNode.getId());
		if(productUnit == null) {
			return false;
		}
		ProductType productType = ProductTypeProvider.getInst().get(typeNode.getId());
		if(productType == null) {
			return false;
		}
		float otp = Float.parseFloat(outPrice);
		float sk = Float.parseFloat(stock);
		if(op == 1) {
			Product product = ProductProvider.getInst().getByName(name);
			if(product != null) {
				return false;
			}
			product = new Product();
			product.setName(name);
			product.setProductUnit(productUnit.getId());
			product.setProductType(productType.getId());
			product.setOutPrice(otp);
			product.setStock(sk);
			product = ProductProvider.getInst().add(product);
			if(product == null) {
				return false;
			}
			table.getTableModel().addRow(ProductProvider.getValue(product));
			return true;
		}else {
			Product product = ProductProvider.getInst().get(id);
			if(product == null) {
				return false;
			}
			product.setName(name);
			product.setProductUnit(productUnit.getId());
			product.setProductType(productType.getId());
			product.setOutPrice(otp);
			product.setStock(sk);
			if(false == ProductProvider.getInst().update(product)) {
				return false;
			}
			table.getTable().setValueAt(name, selectIndex, 1);
			table.getTable().setValueAt(product.getProductUnit(), selectIndex, 2);
			table.getTable().setValueAt(product.getOutPrice(), selectIndex, 3);
			table.getTable().setValueAt(product.getStock(), selectIndex, 4);
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
