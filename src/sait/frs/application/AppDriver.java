package sait.frs.application;
import java.io.IOException;

import sait.frs.gui.*;

/**
 * Application driver.
 * 
 * @author Jonghyun Park
 * @version March 22, 2020
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
