package shop.view.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import shop.beam.ProductType;
import shop.provider.ProductTypeProvider;
import shop.view.MainPanel;
import shop.view.MyDialog;
import shop.view.OptionMenu;
import shop.view.SecondTitle;
import shop.view.MyTable;

public class ProductTypeModel {

	private JFrame mainFrame;
	
	private MainPanel productTypePanel;  //主面板
	
	private String titleString = "商品类别";
	
	private SecondTitle titlePanel;  //2标题面板
	
	private OptionMenu optionMenu;   //操作按钮面板
	private JButton addButton;       //添加按钮
	private JButton alertButton;     //修改按钮
	private JButton delButton;       //删除按钮
	private JTextField queryTextField ;   //查询文字框
	private JButton queryButton;          //查询按钮
	private String queryString = "商品类别名称";
	
	private MyTable viewTable;
	
	private MyDialog dialog;
	
	private JButton saveButton;
    private JButton saveAndAddButton;
    private JButton cannelButton;
    
    private JLabel alertLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel discateLabel;
    private JRadioButton discateRadio;
	
	public void init(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		initTitlePanel();
		initOptionMenu();
		initTableView();
		
		productTypePanel = new MainPanel(mainFrame);
		GroupLayout layout = new GroupLayout(productTypePanel);
		productTypePanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(optionMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            .addComponent(optionMenu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addComponent(viewTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}
	
	private void initTitlePanel() {
		titlePanel = new SecondTitle(titleString);
	}
	
	//初始操作面板
	private void initOptionMenu() {
		addButton = new JButton();
	    addButton.setText("新增");
	    addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAction();
			}
		});
	    
		alertButton = new JButton();
		alertButton.setText("修改");
		alertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertAction();
			}
		});
		 
		delButton = new JButton();
		delButton.setText("删除");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delAction();
			}
		});
		
		queryTextField = new JTextField();
		queryTextField.setText(queryString);
		
        queryButton = new JButton();
        queryButton.setText("查询");
        queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryAction();
			}
		});
        
        optionMenu = new OptionMenu();
        optionMenu.setLayout(crateOptionMenuLayOut());
	}
	
	private void initTableView() {
		viewTable = new MyTable(ProductTypeProvider.getTitle(), ProductTypeProvider.getListValue(ProductTypeProvider.getInst().list()), 400, 300);
	}
	
	private GroupLayout crateOptionMenuLayOut() {
		GroupLayout layout = new GroupLayout(optionMenu);
		optionMenu.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(alertButton)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(delButton)
                .addGap(90, 90, 90)
                .addComponent(queryTextField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(queryButton)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(alertButton)
                    .addComponent(delButton)
                    .addComponent(queryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(queryButton))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        return layout;
	}
	
	private MyDialog crateDialog(String title) {
		dialog = new MyDialog(mainFrame, false, title, 300, 190);
		
		nameLabel = new JLabel();
        saveButton = new JButton();
        saveAndAddButton = new JButton();
        cannelButton = new JButton();

        nameLabel.setText("名称:");
        saveButton.setText("保存");
        saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
        
        saveAndAddButton.setText("保存并添加");
        
        cannelButton.setText("取消");
        cannelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cannelAction();
			}
		});
        alertLabel = new JLabel();
        nameTextField = new JTextField();
        
        discateLabel = new JLabel();
        discateLabel.setText("废弃");
        discateRadio = new JRadioButton();
        
        GroupLayout contentPanelLayout = new GroupLayout(dialog.getMainPanel());
        dialog.getMainPanel().setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
        		contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
    		    .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(alertLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(discateLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(discateRadio)
                            .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87))
        );
        contentPanelLayout.setVerticalGroup(
        		contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alertLabel)
                .addGap(8, 8, 8)
                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(discateRadio)
                        .addComponent(discateLabel)))
        );
        
        
        GroupLayout buttonPanelLayout = new GroupLayout(dialog.getButtonPanel());
        dialog.getButtonPanel().setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveAndAddButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cannelButton)
                .addGap(53, 53, 53))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(saveAndAddButton)
                    .addComponent(cannelButton))
                .addContainerGap())
        );
        dialog.setLayOut();
        return dialog;
	}
	
	//添加操作
	private void addAction() {
		MyDialog dialog = crateDialog(titleString + " - 添加");
		dialog.setVisible(true);
	}
	//修改操作
	private void alertAction() {
		
	}
	//删除操作
	private void delAction() {
		
	}
	//查新操作
	private void queryAction() {
		
	}
	//查新操作
	private void saveAction() {
		String name = nameTextField.getText().trim();
		if(name.isEmpty()) {
			alertLabel.setVisible(true);
			alertLabel.setText("商品类别名字不能为空");
			//名字格式不对
			return;
		}
		ProductType t = ProductTypeProvider.getInst().getByName(name);
		if(t != null) {
			alertLabel.setVisible(true);
			alertLabel.setText("商品类别已经存在");
			return;
		}
		ProductType newTpe = new ProductType();
		newTpe.setName(name);
		ProductType addType = ProductTypeProvider.getInst().add(newTpe);
		if(addType == null) {
			//数据库添加失败
			alertLabel.setVisible(true);
			alertLabel.setText("商品类别出错");
			return;
		}
		alertLabel.setText("");
		alertLabel.setVisible(false);
		cannelAction();
	}
	//查新操作
	private void cannelAction() {
		if(dialog != null) {
			dialog.dispose();
		}
	}

	public MainPanel getProductTypePanel() {
		return productTypePanel;
	}
}
