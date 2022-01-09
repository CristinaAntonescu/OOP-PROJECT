package view;
import start.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateAccount extends JFrame{

    private final JLabel title = new JLabel("Create New Account");
    private final JLabel introduceU = new JLabel("Introduce username:");
    private final JLabel introduceP = new JLabel("Introduce password:");
    private final JTextField username = new JTextField("");
    private final JPasswordField password = new JPasswordField("");
    private static final JLabel image = new JLabel();
    private static final ImageIcon imageIcon = new ImageIcon("ICON.png");
    private static final JButton createAccount= new JButton("Create Account");

    public CreateAccount(){

        setTitle("Create Account");
        setSize(500, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());
        addComponents();
    }

    private void addComponents(){
        addTitle();
        addImage();
        addUsernameText();
        addUsername();
        addPasswordText();
        addPassword();
        addButton();
    }

    private void addTitle(){
        title.setBounds(170,30,440,30);
        title.setVisible(true);
        title.setFont(new Font("Roboto", Font.BOLD, 15));
        add(title);
    }

    private void addImage(){
        image.setBounds(130, 60, 440, 220);
        image.setVisible(true);
        image.setText("");
        image.setIcon(imageIcon);
        add(image);
    }
    private void addUsernameText(){
        introduceU.setBounds(150, 280, 300, 30);
        introduceU.setVisible(true);
        introduceU.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceU);
    }

    private void addUsername(){
        username.setBounds(150,320, 200, 30);
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setFont(new Font("Roboto", Font.BOLD, 15));
        add(username);
    }

    private void addPasswordText(){
        introduceP.setBounds(150, 350, 300, 30);
        introduceP.setVisible(true);
        introduceP.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceP);
    }

    private void addPassword(){
        password.setBounds(150,390, 200, 30);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Roboto", Font.BOLD, 15));
        add(password);
    }

    private void addButton(){
        createAccount.setBounds(150, 470, 200, 30);
        createAccount.setFont(new Font("Roboto", Font.BOLD, 12));
        createAccount.setVisible(true);
        createAccount.addActionListener(new CreateAccount.CreateAccountButtonListener());
        add(createAccount);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {

        return new String(password.getPassword());
    }

    private class CreateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String pass = new String(password.getPassword());
            if(username.getText().length()!=0 && pass.length()!=0){
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");
                    Statement statement = conn.createStatement();
                    String s1=username.getText();
                    String s2 = "INSERT INTO accounts(a_username,a_password) VALUES('";
                    String s3=s2.concat(s1);
                    String ss= s3.concat("','");
                    String s4 = ss.concat(pass);
                    String sqlAcc= s4.concat("');");

                    statement.executeUpdate(sqlAcc);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                IntroduceUser introduceUser = new IntroduceUser();
                introduceUser.setVisible(true);
                CreateAccount.this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "The username or password are null","NULL PASSWORD/USERNAME" , JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
