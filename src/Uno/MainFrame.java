package Uno;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField input1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {

		setTitle("Jeu de cartes UNO");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de joueurs :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(85, 153, 146, 14);
		getContentPane().add(lblNewLabel);
		
		input1 = new JTextField();
		input1.setBounds(241, 152, 86, 20);
		getContentPane().add(input1);
		input1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Bienvenue au jeu de cartes UNO");
		lblNewLabel_1.setFont(new Font("Gill Sans MT Condensed", Font.ITALIC, 32));
		lblNewLabel_1.setForeground(new Color(0, 255, 204));
		lblNewLabel_1.setBounds(58, 39, 343, 82);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Integer.parseInt(input1.getText()));
			}
		});
		btnNewButton.setBounds(171, 202, 96, 31);
		getContentPane().add(btnNewButton);
	
	}
}
