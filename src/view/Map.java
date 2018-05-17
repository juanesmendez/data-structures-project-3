package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Map extends JFrame{
	private ImageIcon image;
	private JLabel label;
	
	public Map(ImageIcon imageIcon) {
		setLayout(new FlowLayout());
		image = imageIcon;
		label = new JLabel(image);
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Google Static Map");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/4-this.getSize().width/2, dim.height/5-this.getSize().height/2);
		pack();
	}
}
