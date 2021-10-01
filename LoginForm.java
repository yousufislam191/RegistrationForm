import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.regex.*;

public class LoginForm extends JFrame {

    private JPanel headerPanel, body;
    private JLabel  headerTxt, email, pass;
    private JTextField userEmail, userPass;
    private JButton registerBtn, loginBtn;

    private JLabel[] label = new JLabel[2];
    private JTextField[] textFields = new JTextField[2];
    private JButton[] btn = new JButton[2];

    Font headerFont = new Font("Courier", Font.BOLD, 25);
    Font bodyFont = new Font("Courier", Font.BOLD, 20);
    Font bodyInputFont = new Font("Courier", Font.BOLD, 15);
    
    public LoginForm() {

        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setResizable(false);
        setLayout(null);

        //header panel add
        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 70);
        headerPanel.setBackground(new Color(219,149,87));
        
        headerTxt = new JLabel("Login Form");
        headerTxt.setFont( headerFont);
        headerPanel.add(headerTxt);
        add(headerPanel);

        //body panel add
        body = new JPanel();
        body.setBounds(0, 70, 600, 630);
        body.setBackground(new Color(43, 60, 77));
        body.setLayout(null);
        add(body);

        //naming field
        email = new JLabel("Email : ");
        pass = new JLabel("Password : ");

        email.setBounds(150, 20, 100, 30);
        pass.setBounds(110, 70, 140, 30);

        label[0] = email;
        label[1] = pass;

        for(int i=0; i<2; i++) {
            label[i].setFont(bodyFont);
            label[i].setForeground(Color.WHITE);
            body.add(label[i]);
        }

        //input field
        userEmail = new JTextField();
        userPass = new JTextField();

        userEmail.setBounds(250, 20, 270, 30);
        userPass.setBounds(250, 70, 270, 30);

        textFields[0] = userEmail;
        textFields[1] = userPass;

        for(int i=0; i<2; i++) {
            textFields[i].setBackground(Color.WHITE);
            textFields[i].setForeground(Color.BLACK);
            textFields[i].setFont(bodyInputFont);
            body.add(textFields[i]);
        }

        //button add
        registerBtn = new JButton("Register");
        loginBtn = new JButton("Login");

        registerBtn.setBounds(80, 150, 150, 50);
        loginBtn.setBounds(420, 150, 100, 50);

        btn[0] = registerBtn;
        btn[1] = loginBtn;

        for(int i=0; i<2; i++) {
        btn[i].setBackground(new Color(219,149,87));
        btn[i].setForeground(Color.BLACK);
        btn[i].setFont(headerFont);
        btn[i].setBorder(new LineBorder(Color.white));
        body.add(btn[i]);
        }

        setVisible(true);

        

        registerBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrationForm();
            }
        });
        
        loginBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUserEmail =  userEmail.getText();
                String loginUserPass = userPass.getText();
                String loginQuery = "SELECT * FROM `registration`";

                String emailRegex="[a-z0-9.]+@[a-z]+.[a-z]+$";
                String passRegex= "\\S+.{9,}$";

                if (!Pattern.matches(emailRegex,loginUserEmail)){
                    JOptionPane.showMessageDialog(null,"In-valid E-mail");
                }
                else if (!Pattern.matches(passRegex,loginUserPass)){
                    JOptionPane.showMessageDialog(null,"Your password must be 10 digits.");
                }
                else {
                    DbConnect db = new DbConnect();
                    db.Login(loginQuery, loginUserEmail, loginUserPass);
                }
            }
        });
    }
}

