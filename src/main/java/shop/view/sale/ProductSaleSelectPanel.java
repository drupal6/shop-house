package shop.view.sale;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import shop.Constance;

public class ProductSaleSelectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductTypeSaleScorellPanel titlePanel;
	private JPanel contentPanel;
	private JScrollPane titleScrollPane; 
	private JButton preButton;
	private JButton nextButton;
	private Timer timer;
	
	public ProductSaleSelectPanel() {
		setBackground(new Color(47, 47, 47));
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(47, 47, 47));
		GridLayout glayout = new GridLayout(0, 7);
		glayout.setHgap(5);
		glayout.setVgap(5);
		contentPanel.setLayout(glayout);
		
		titlePanel = new ProductTypeSaleScorellPanel(contentPanel);
		titlePanel.setBackground(new Color(47, 47, 47));
		
		titleScrollPane = new JScrollPane(titlePanel);
		titleScrollPane.setBorder(null);
		titleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		titleScrollPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				scrollPaneScroll(e.getWheelRotation() * 20);
			}
		});
		
		preButton = new JButton("<");
		preButton.setFont(Constance.fontB35);
		preButton.setForeground(Color.WHITE);
		preButton.setBackground(new Color(47, 47, 47));
		preButton.setBorder(null);
		preButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");
				if(timer != null) {
					timer.cancel();
					timer = null;
				}
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed");
				if(timer != null) {
					timer.cancel();
				}
				timer = new Timer();
				timer.schedule(new MoveTask(-1), 200, 1);
			}
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) {
				if(timer == null) {
					scrollPaneScroll(-20);
				}
			}
		});
		nextButton = new JButton(">");
		nextButton.setFont(Constance.fontB35);
		nextButton.setForeground(Color.WHITE);
		nextButton.setBackground(new Color(47, 47, 47));
		nextButton.setBorder(null);
		nextButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				if(timer != null) {
					timer.cancel();
					timer = null;
				}
			}
			
			public void mousePressed(MouseEvent e) {
				if(timer != null) {
					timer.cancel();
				}
				timer = new Timer();
				timer.schedule(new MoveTask(1), 200, 1);
			}
			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) {
				if(timer == null) {
					scrollPaneScroll(20);
				}
			}
		});
		
		GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(preButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleScrollPane, GroupLayout.PREFERRED_SIZE, 1000,GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE))
                ))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleScrollPane, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(preButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nextButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                .addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
        );
	}

	
	private void scrollPaneScroll(int value) {
		Point p = titleScrollPane.getViewport().getViewPosition();
		int o = p.x;
		p.x = Math.max(0, p.x + value);
		p.x = Math.min(p.x, titleScrollPane.getHorizontalScrollBar().getMaximum() - titleScrollPane.getWidth());
		if(p.x == o) {
			return;
		}
		titleScrollPane.getViewport().setViewPosition(p);
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	class MoveTask extends TimerTask{

		private int value;
		public MoveTask(int value) {
			this.value = value;
		}
		
		@Override
		public void run() {
			scrollPaneScroll(value);
		}
		
	}
}
