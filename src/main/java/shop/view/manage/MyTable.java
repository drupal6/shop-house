package shop.view.manage;

import java.awt.Color;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MyTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table; 
	private DefaultTableModel tableModel;

	public MyTable(Vector<String> title, Vector tableValueV, int weight, int height) {
		this.setBackground(new Color(120, 120, 120));
		
		tableModel = new DefaultTableModel(tableValueV, title);
		table = new DefaultTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);//设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer();  //获得表格头的单元格对象
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer cr =(DefaultTableCellRenderer)table.getDefaultRenderer(String.class);  //获得表格头的单元格对象
        cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列的自动调整功能。
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
        table.setSelectionBackground(Color.YELLOW);
        table.setSelectionForeground(Color.RED);
        table.setRowHeight(30);
		
		JScrollPane tableScorllPanel = new JScrollPane();
	    tableScorllPanel.setViewportView(table);
	    
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(tableScorllPanel, GroupLayout.DEFAULT_SIZE, weight, Short.MAX_VALUE));
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().addComponent(tableScorllPanel, GroupLayout.PREFERRED_SIZE, height, Short.MAX_VALUE));
        layout.setVerticalGroup(vGroup);
	}

	
	public void replace(Vector<String> title, Vector tableValueV) {
		tableModel = new DefaultTableModel(tableValueV, title);
		table.setModel(tableModel);
		table.updateUI();
	}
	
	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	class DefaultTable extends JTable {
		
		private static final long serialVersionUID = 1L;
		public DefaultTable(TableModel dm) {
			super(dm);
		}
		@Override
		public boolean isCellEditable(int row,int col){
			return false;
		}
	}
}