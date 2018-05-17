package view;

import java.awt.FlowLayout;

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
		
		pack();
	}
}
