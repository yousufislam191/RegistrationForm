import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.border.*;

public class RegistrationForm extends JFrame {

    private JPanel headerPanel, body;
    private JLabel  headerTxt, name, email, pass, ConPass, mobile, address;
    private JButton registerBtn, loginBtn;
    private JTextField userName, userEmail, userPass, userConPass, userMobile, userAddress;

    private JLabel[] label = new JLabel[6];
    private JTextField[] textFields = new JTextField[6];
    private JButton[] btn = new JButton[2];

    Font headerFont = new Font("Courier", Font.BOLD, 25);
    Font bodyFont = new Font("Courier", Font.BOLD, 20);
    Font bodyInputFont = new Font("Courier", Font.BOLD, 15);

    public RegistrationForm() {
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setResizable(false);
        setLayout(null);

        //header panel add
        headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 600, 70);
        headerPanel.setBackground(new Color(219,149,87));
        
        headerTxt = new JLabel("Registration Form");
        headerTxt.setFont( headerFont);
        headerPanel.add(headerTxt);
        add(headerPanel);

        //body panel add
        body = new JPanel();
        body.setBounds(0, 70, 600, 630);
        body.setBackground(new Color(43, 60, 77));
        body.setLayout(null);
        add(body);

        //txt add
        name = new JLabel("Name : ");
        email = new JLabel("Email : ");
        pass = new JLabel("Password : ");
        ConPass = new JLabel("Confirm Password : ");
        mobile = new JLabel("Mobile : ");
        address = new JLabel("Address : ");

        name.setBounds(160, 20, 90, 30);
        email.setBounds(150, 70, 100, 30);
        pass.setBounds(110, 120, 140, 30);
        ConPass.setBounds(20, 170, 230, 30);
        mobile.setBounds(140, 220, 110, 30);
        address.setBounds(120, 270, 130, 30);

        label[0] = name;
        label[1] = email;
        label[2] = pass;
        label[3] = ConPass;
        label[4] = mobile;
        label[5] = address;

        for(int i=0; i<6; i++) {
            label[i].setFont(bodyFont);
            label[i].setForeground(Color.WHITE);
            body.add(label[i]);
        }   

        //input field add
        userName = new JTextField();
        userEmail = new JTextField();
        userPass = new JTextField();
        userConPass = new JTextField();
        userMobile = new JTextField();
        userAddress = new JTextField();

        userName.setBounds(250, 20, 270, 30);
        userEmail.setBounds(250, 70, 270, 30);
        userPass.setBounds(250, 120, 270, 30);
        userConPass.setBounds(250, 170, 270, 30);
        userMobile.setBounds(250, 220, 270, 30);
        userAddress.setBounds(250, 270, 270, 30);

        textFields[0] = userName;
        textFields[1] = userEmail;
        textFields[2] = userPass;
        textFields[3] = userConPass;
        textFields[4] = userMobile;
        textFields[5] = userAddress;

        for(int i=0; i<6; i++) {
            textFields[i].setBackground(Color.WHITE);
            textFields[i].setForeground(Color.BLACK);
            textFields[i].setFont(bodyInputFont);
            body.add(textFields[i]);
        }

        //button add
        registerBtn = new JButton("Register");
        loginBtn = new JButton("Login");

        loginBtn.setBounds(120, 340, 100, 50);
        registerBtn.setBounds(370, 340, 150, 50);

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

                //get input text
                String uname = userName.getText();
                String uemail = userEmail.getText();
                String upass = userPass.getText();
                String uConPass = userConPass.getText();
                String umobile = userMobile.getText();
                String uaddress = userAddress.getText();

                //validation input field
                String nameRegex="^[a-zA-Z. ]+$";
                String emailRegex="[a-z0-9.]+@[a-z]+.[a-z]+$";
                String passRegex= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,}$";
                String mobileRegex="(\\+88)?01[3-9]\\d{8}"; 

                //condition
                if (!Pattern.matches(nameRegex,uname)){
                    JOptionPane.showMessageDialog(null,"In-valid Name");
                }

                else if (!Pattern.matches(emailRegex,uemail)){
                    JOptionPane.showMessageDialog(null,"In-valid E-mail");
                }

                else if(!upass.equals(uConPass)) {
                    JOptionPane.showConfirmDialog(null, "Password & confirm password is not matching");
                }

                else if (!Pattern.matches(passRegex,upass)){
                    JOptionPane.showMessageDialog(null,"Your password is not secure. Please set a strong password \r\n" 
                        +"with 10 digits using uppercase, lowercase, special character");
                }
  
                else if (!Pattern.matches(mobileRegex,umobile)){
                    JOptionPane.showMessageDialog(null,"In-valid Phone Number");
                }

                else {
                    try {
                        DbConnect db = new DbConnect();
                        String queryInsert = "INSERT INTO `registration`(`username`, `email`, `pass`, `mobile`, `address`) VALUES ('"+uname+"','"+uemail+"', '"+upass+"', '"+umobile+"', '"+uaddress+"')";
                        db.RegisterInsert(queryInsert);
                    } 
                    catch (Exception e1) {
                        System.out.println("Error"+e1);
                    }
                }
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });
    }
}
