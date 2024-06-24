 /*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Login extends JFrame implements ActionListener {
    // In-memory user store
    private Map<String, String> userStore = new HashMap<String, String>();

    // Components of the Form
    private Container container;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel messageLabel;

    // Constructor
    public Login() {
        setTitle("User Authentication");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container = getContentPane();
        container.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 30, 100, 30);
        container.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(150, 30, 150, 30);
        container.add(userTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 100, 30);
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        container.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 110, 100, 30);
        loginButton.addActionListener(this);
        container.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 110, 100, 30);
        registerButton.addActionListener(this);
        container.add(registerButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(50, 150, 300, 30);
        container.add(messageLabel);

        setVisible(true);
    }

    // ActionEvent handler
    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = userTextField.getText();
        String passwordText = new String(passwordField.getPassword());

        if (e.getSource() == loginButton) {
            if (userStore.containsKey(userText) && userStore.get(userText).equals(passwordText)) {
                messageLabel.setText("Login successful");

                dispose();
            } else {
                messageLabel.setText("Invalid username or password");
            }
        } else if (e.getSource() == registerButton) {
            if (userStore.containsKey(userText)) {
                messageLabel.setText("Username already exists");
            } else {
                userStore.put(userText, passwordText);
                messageLabel.setText("Registration successful");
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class GamePage extends JFrame {
    public GamePage() {
        setTitle("Game Window");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel gameLabel = new JLabel("Welcome to the Game!");
        gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameLabel);

        setVisible(true);
    }
}*/


