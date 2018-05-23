package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MapBrowser extends JFrame {

	public MapBrowser(String pathFile) {

		File file = new File(pathFile);

		try {
			final Browser browser = new Browser();
			browser.loadURL(file.toURI().toURL().toString());
			BrowserView browserView = new BrowserView(browser);

			//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			add(browserView, BorderLayout.CENTER);
			//setLocationRelativeTo(null);
			setSize(900, 800);
			setVisible(true);
			setTitle("Google Maps");
			toFront();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(dim.width/4, dim.height/8);
			
			//pack();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
