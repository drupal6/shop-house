package shop.view;

import java.awt.Color;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import shop.provider.ProductTypeProvider;

public class ViewTable extends JPanel {

	private static final long serialVersionUID = 1L;

	public ViewTable(Vector<String> title, Vector tableValueV, int weight, int height) {
		this.setBackground(new Color(120, 120, 120));
		
		MyTable jtable1 = new MyTable(title, tableValueV);
		
		JScrollPane jScrollPane1 = new JScrollPane();
	    jScrollPane1.setViewportView(jtable1);
		
		GroupLayout jPanel3Layout = new GroupLayout(this);
		
        this.setLayout(jPanel3Layout);
        
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, weight, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, height, GroupLayout.PREFERRED_SIZE)
            .addGap(0, 58, Short.MAX_VALUE)
        );
	}
}
