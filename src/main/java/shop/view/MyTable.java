package shop.view;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTable extends JTable {

	
	public MyTable(Vector<String> columnNameV, Vector tableValueV) {
		super(tableValueV, columnNameV);
		
		
		this.getTableHeader().setReorderingAllowed(false);//设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)this.getTableHeader().getDefaultRenderer();  //获得表格头的单元格对象
        hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        DefaultTableCellRenderer cr =(DefaultTableCellRenderer)this.getDefaultRenderer(String.class);  //获得表格头的单元格对象
        cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列的自动调整功能。
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   //单选
        this.setSelectionBackground(Color.YELLOW);
        this.setSelectionForeground(Color.RED);
        this.setRowHeight(30);
	}
}
