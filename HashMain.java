package hw7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class HashMain {
	private static JButton inputBtn;
	private static JButton inputBtn2;
	private static JButton outputBtn;
	private static JButton computeBtn;
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static File fileToRead;
	private static File fileToCheck;
	private static File fileToSave;

	static double sumOfSquares = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		constructAppWindow();
		addListenerEvents();
	}

	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// construct a panel container to store buttons, etc.
		panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
		panel.setPreferredSize(new Dimension(500, 150));
		panel.setBackground(Color.DARK_GRAY);

		// build buttons, etc. and add them to the panel
		inputBtn = new JButton("Provide Keyword file");
		inputBtn2 = new JButton("Provide file to be analyzed");
		outputBtn = new JButton("Provide file for results");
		computeBtn = new JButton("Perform Analysis");
		panel.add(inputBtn);
		panel.add(inputBtn2);
		panel.add(outputBtn);
		panel.add(computeBtn);

		// add panel to the application window
		jframeWindow.add(panel);

		// TASK 5: MAKE THE APPLICATION WINDOW VISIBLE TO THE USER
		jframeWindow.pack();
		jframeWindow.setVisible(true);
	}

	private static void addListenerEvents() {
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile();
			}
		});
		inputBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile2();
			}
		});
		outputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestSaveFile();
			}
		});
		computeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					computeSomething();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public static void requestSaveFile() {
		// parent component of the dialog
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}
	}
	public static void requestInputFile() {
		// parent component of the dialog
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		jfc.setFileFilter(filter); // ONLY SHOW TEXT FILES FOR EASE OF USE
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToRead = jfc.getSelectedFile();
			System.out.println(fileToRead.getAbsolutePath());
		}
	}
	public static void requestInputFile2() {
		// parent component of the dialog
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		jfc.setFileFilter(filter); // ONLY SHOW TEXT FILES FOR EASE OF USE
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToCheck = jfc.getSelectedFile();
			System.out.println(fileToCheck.getAbsolutePath());
		}
	}
	public static void computeSomething() throws NumberFormatException, IOException {
		long startTime = System.nanoTime();
		PrintStream out = new PrintStream(new FileOutputStream(fileToSave));
		System.setOut(out); // SET OUTPUT TO SAVE TO NEW FILE THAT IS SELECTED
		Hashtable<Integer, String> KeywordInput = new Hashtable<Integer,String>();
		KeywordInput = Keywords.createList(fileToRead);
		System.out.println("Number of lines: " + Hasher.countLines(fileToCheck));
		System.out.println("Number of keywords found: " + Hasher.countKeys(KeywordInput, fileToCheck));	
		long endTime = System.nanoTime();
		System.out.println("Program took " + ((endTime - startTime) / 1000000) + " milliseconds"); 
	}

}