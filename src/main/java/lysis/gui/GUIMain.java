package lysis.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import lysis.Lysis;

public class GUIMain {

	private JFrame frame;
	private Lysis lysis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain window = new GUIMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lysis = new Lysis();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setMinimumSize(new Dimension(450, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Lysis Decompiler");
		frame.setBackground(new Color(65, 84, 99));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 84, 99));
		panel.setForeground(Color.WHITE);
		// panel.setBorder(null);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		JTextPane text1 = new JTextPane();
		text1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text1.setBackground(new Color(65, 84, 99));
		text1.setForeground(Color.WHITE);
		text1.setEditable(false);
		text1.setText("Lysis 09-JUN-2020 commit e8901c4");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		panel.add(text1);

		JTextPane text2 = new JTextPane();
		text2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text2.setBackground(new Color(65, 84, 99));
		text2.setForeground(Color.WHITE);
		text2.setEditable(false);
		text2.setText("GUI v1.2 by raziEiL [disawar1]");
		panel.add(text2);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(new Color(65, 84, 99));
		progressBar.setForeground(new Color(71, 205, 196));
		progressBar.setBorder(null);
		progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		frame.getContentPane().add(progressBar, BorderLayout.NORTH);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(65, 84, 99));
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		panel2.setLayout(gbl_panel_2);

		JTextPane text3 = new JTextPane();
		text3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		text3.setBackground(new Color(65, 84, 99));
		text3.setForeground(new Color(71, 205, 196));
		text3.setEditable(false);
		text3.setText("Drag & Drop smx Files");
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = 0;
		grid.gridy = 0;
		panel2.add(text3, grid);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea.setBackground(new Color(65, 84, 99));
		textArea.setForeground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setMargin(new Insets(5, 5, 5, 5));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);

		frame.pack();

		new FileDrop(System.out, panel2, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				progressBar.setVisible(true);
				frame.getContentPane().remove(panel2);
				panel2.setVisible(false);
				frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
				decompile(textArea, progressBar, files);

			}
		});
		new FileDrop(System.out, textArea, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				decompile(textArea, progressBar, files);
			}
		});
	}

	private void decompile(javax.swing.JTextArea textArea, javax.swing.JProgressBar progressBar, java.io.File[] files) {
		Runnable task = () -> {
			textArea.append(files.length + " file(s) added to queue" + "\n");

			for (int i = 0; i < files.length; i++) {
				try {
					textArea.append(files[i].getCanonicalPath() + "\n");
					// Lysis lysis = new Lysis();
					lysis.DecompileIt(files[i].getPath(), files[i].getName(), textArea);
					textArea.append("\n");
					progressBar.setValue(Math.round((float) ((float) (i + 1) / (float) files.length * 100.0)));
				} // end try
				catch (Exception e) {
				}
			} // end for: through each dropped file
		};
		Thread thread = new Thread(task);
		thread.start();
	}
}
