package pi.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton cancelButton;
	private JPanel buttonPanel;
	private boolean succeeded;

	public LoginDialog() {
		setTitle("Login");
		setVisible(true);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setVisible(true);
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.fill = GridBagConstraints.HORIZONTAL;

		usernameLabel = new JLabel("Username: ");
		usernameLabel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		panel.add(usernameLabel, constraints);

		usernameField = new JTextField(20);
		usernameField.setVisible(true);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		panel.add(usernameField, constraints);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setVisible(true);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		panel.add(passwordLabel, constraints);

		passwordField = new JPasswordField(20);
		passwordField.setVisible(true);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		panel.add(passwordField, constraints);

		loginButton = new JButton("Login");
		cancelButton = new JButton("Canel");

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Login.authenticate(getUsername(), getPassword())){
					JOptionPane.showMessageDialog(LoginDialog.this, "You have successfully log in", "Login", JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);
					usernameField.setText("");
					passwordField.setText("");
					succeeded = false;
				}
			}
		});
		
		buttonPanel = new JPanel();

		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel,BorderLayout.PAGE_END);
		
		pack();
		setResizable(false);
		
	}
	

	public String getUsername() {
		return usernameField.getText().trim();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public boolean isSucceeded() {
		return succeeded;
	}

}