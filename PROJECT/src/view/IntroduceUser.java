package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IntroduceUser extends JFrame{

    private final JLabel title = new JLabel("Introduce Username and Password:");
    private final JLabel introduceU = new JLabel("Username:");
    private final JLabel introduceP = new JLabel("Password");
    private final JTextField username = new JTextField("");
    private final JPasswordField password = new JPasswordField("");
    private static final JLabel image = new JLabel();
    private static final ImageIcon imageIcon = new ImageIcon("ICON.png");
    private static final JButton logIn = new JButton("Log-in");

    public IntroduceUser(){
        setTitle("Introduce Username and Password");
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
        title.setBounds(120,20,440,30);
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
        introduceU.setBounds(150, 280, 100, 30);
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
        introduceP.setBounds(150, 350, 100, 30);
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
        logIn.setBounds(150,470, 200, 30);
        logIn.setFont(new Font("Roboto", Font.BOLD, 12));
        logIn.setVisible(true);
        logIn.setFocusable(false);
        logIn.addActionListener(new IntroduceUser.CreateButtonListener());
        add(logIn);

    }

    public String getUsername() {

        return username.getText();
    }

    public String getPassword() {

        return new String(password.getPassword());
    }
    private class CreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String u= username.getText();
            String p = new String(password.getPassword());

            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");

                try (Statement statement = conn.createStatement()) {
                    String sql= "select * from accounts where a_username= '" + u +"'";
                    ResultSet res= statement.executeQuery(sql);
                    boolean verif=false;

                        while(res.next() ){
                            if(res.getString("a_password").equals(p)){
                                verif=true;
                            }
                        }
                    if(verif==true){
                        SelectFlightDetails selectFlightDetails = new SelectFlightDetails();
                        selectFlightDetails.setVisible(true);
                        IntroduceUser.this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The username or password does not exist","INCORRECT PASSWORD/USERNAME" , JOptionPane.ERROR_MESSAGE);

                    }



                }
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
        }
    }

}
