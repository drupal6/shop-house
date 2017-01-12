package shop.view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ViewTable extends JPanel {

	public ViewTable() {
		this.setBackground(new Color(120, 120, 120));
		
		javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		JTable jTable1 = new javax.swing.JTable();
	    jTable1.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null}
	            },
	            new String [] {
	                "Title 1", "Title 2", "Title 3", "Title 4"
	            }
        ));
	    jScrollPane1.setViewportView(jTable1);
		
		GroupLayout jPanel3Layout = new GroupLayout(this);
        this.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 58, Short.MAX_VALUE)
        );
	}
}
