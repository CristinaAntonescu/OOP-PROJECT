package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame {

    private final JButton signIn = new JButton("Sign-In");
    private final JButton signUp = new JButton("Sign-Up");
    private static final JLabel title = new JLabel("Welcome to our Airline Reservations System");
    private static final JLabel image = new JLabel();
    private static final ImageIcon imageAirline = new ImageIcon("AIRLINE2.jpg");

    public WelcomeWindow(){
        setTitle("Airline Reservations System");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(255,182,187));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());
        addComponents();
    }

    private void addComponents() {
        addTitle();
        addImage();
        addSignInButton();
        addSignUpButton();
    }

    private void addTitle(){
        title.setBounds(80,20,440,30);
        title.setVisible(true);
        title.setFont(new Font("Roboto", Font.BOLD, 15));
        add(title);
    }

    private void addImage(){
        image.setBounds(30, 60, 440, 200);
        image.setVisible(true);
        image.setText("");
        image.setIcon(imageAirline);
        add(image);
    }

    private void addSignInButton(){
        signIn.setBounds(50, 300, 150, 30);
        signIn.setVisible(true);
        signIn.setFont(new Font("Roboto", Font.PLAIN, 15));
        signIn.setBackground(new Color(255,182,187));
        signIn.setFocusable(false);
        signIn.addActionListener(new SignInButtonListener());
        add(signIn);
    }

    private void addSignUpButton(){
        signUp.setBounds(280, 300, 150, 30);
        signUp.setVisible(true);
        signUp.setFont(new Font("Roboto", Font.PLAIN, 15));
        signUp.setBackground(new Color(255,182,187));
        signUp.setFocusable(false);
        signUp.addActionListener(new SignUpButtonListener());
        add(signUp);
    }

    private class SignInButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            IntroduceUser introduceUser = new IntroduceUser();
            introduceUser.setVisible(true);
            WelcomeWindow.this.dispose();
        }
    }

    private class SignUpButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            CreateAccount createAccount = new CreateAccount();
            createAccount.setVisible(true);
            WelcomeWindow.this.dispose();
        }
    }
}