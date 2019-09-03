package lysis.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import lysis.Lysis;

public class GUIMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain frame = new GUIMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIMain() {
		setResizable(false);
		setTitle("Lysis Decompiler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnDragDrop = new JTextPane();
		txtpnDragDrop.setText("Drag & Drop smx Files");
		txtpnDragDrop.setForeground(new Color(51, 153, 255));
		txtpnDragDrop.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnDragDrop.setEditable(false);
		txtpnDragDrop.setBackground(Color.LIGHT_GRAY);
		txtpnDragDrop.setBounds(68, 100, 205, 28);
		contentPane.add(txtpnDragDrop);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(5, 5, 335, 225);
		contentPane.add(scrollPane);
		
		JTextArea txtrQweqwe = new JTextArea();
		txtrQweqwe.setLineWrap(true);
		txtrQweqwe.setBorder(null);
		scrollPane.setViewportView(txtrQweqwe);
		txtrQweqwe.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtrQweqwe.setEditable(false);
		txtrQweqwe.setBackground(Color.LIGHT_GRAY);
		txtrQweqwe.setMargin(new Insets(25, 5, 5, 5));
		
		JTextPane txtpnLysisVersionjan = new JTextPane();
		txtpnLysisVersionjan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnLysisVersionjan.setForeground(Color.BLACK);
		txtpnLysisVersionjan.setBackground(Color.LIGHT_GRAY);
		txtpnLysisVersionjan.setEditable(false);
		txtpnLysisVersionjan.setBounds(62, 232, 218, 20);
		txtpnLysisVersionjan.setText("Lysis version: 22-JUL-2019 commit 9cd470f");
		contentPane.add(txtpnLysisVersionjan);
		
		JTextPane txtpnGuiMenuBy = new JTextPane();
		txtpnGuiMenuBy.setEditable(false);
		txtpnGuiMenuBy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnGuiMenuBy.setForeground(Color.BLACK);
		txtpnGuiMenuBy.setBackground(Color.LIGHT_GRAY);
		txtpnGuiMenuBy.setText("GUI Menu version: 1.1 by raziEiL [disawar1]");
		txtpnGuiMenuBy.setBounds(64, 249, 216, 20);
		contentPane.add(txtpnGuiMenuBy);
		
        new FileDrop( System.out, txtrQweqwe, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   
        		txtpnDragDrop.setVisible(false);
        		txtrQweqwe.append( files.length + " file(s) added to queue" + "\n" );
        		for( int i = 0; i < files.length; i++ )
                {   try
                    {   txtrQweqwe.append( files[i].getCanonicalPath() + "\n" );
        				Lysis lysis = new Lysis();
        				lysis.DecompileIt(files[i].getPath(), files[i].getName(), txtrQweqwe);
        				txtrQweqwe.append("\n");
        				
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
        
		//for (int i = 0; i < 10; i++)
		//	txtrQweqwe.append(i + " hellooooooooooooooooooooooooooooooooooooooooooooo!!!" + "\n");
	}
}
