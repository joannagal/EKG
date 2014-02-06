package pi.gui.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pi.shared.SharedController;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JPanel buttonPanel;
	private boolean succeeded;

	public LoginDialog(){
		super(SharedController.getInstance().getFrame());
		this.setTitle("Login");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setVisible(true);
		GridBagConstraints constraints = new GridBagConstraints();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2) - 100;
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2 - 100);
		this.setLocation(x, y);

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
		usernameField.setText("admin");

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
		passwordField.setText("admin");

		loginButton = new JButton("Login");
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Login.authenticate(getUsername(), getPassword())){
					JOptionPane.showMessageDialog(LoginDialog.this, "You have successfully log in", "Login", JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					SharedController.getInstance().setLogged(true);
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
		
		
		loginButton.setActionCommand("LOGIN");
		buttonPanel = new JPanel();

		buttonPanel.add(loginButton);
		
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