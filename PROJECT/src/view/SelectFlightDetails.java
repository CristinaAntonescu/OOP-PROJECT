package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectFlightDetails extends JFrame{

    private final JLabel title = new JLabel("Please select the details of your flight:");
    private final JLabel introduceDate = new JLabel("Introduce the date:");
    private final JLabel introduceMonth = new JLabel("Introduce the month:");
    private final JLabel introduceYear = new JLabel ("Introduce the year:");
    private final JTextField date = new JTextField("");
    private final JTextField month = new JTextField("");
    private final JTextField year = new JTextField("");
    private final JButton search = new JButton("SEARCH");
    private final JLabel note= new JLabel("Please introduce the date, month, year in integer format");

    public SelectFlightDetails(){
        setTitle("Select Flight Details");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());
        addComponents();
    }

    private void addComponents(){
       addTitle();
       addIntroduceDate();
       addDate();
       addIntroduceMonth();
       addMonth();
       addIntroduceYear();
       addYear();
       addSearchButton();
       addNote();
    }

    private void addTitle(){
        title.setBounds(100,30,440,30);
        title.setVisible(true);
        title.setFont(new Font("Roboto", Font.BOLD, 15));
        add(title);
    }

    private void addIntroduceDate(){
        introduceDate.setBounds(170, 80, 200,30);
        introduceDate.setVisible(true);
        introduceDate.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceDate);
    }

    private void addDate(){
        date.setBounds(140,120, 200, 30);
        date.setHorizontalAlignment(JTextField.CENTER);
        date.setFont(new Font("Roboto", Font.BOLD, 15));
        add(date);
    }

    private void addIntroduceMonth(){
        introduceMonth.setBounds(170, 160, 200,30);
        introduceMonth.setVisible(true);
        introduceMonth.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceMonth);
    }

    private void addMonth(){
        month.setBounds(140,200, 200, 30);
        month.setHorizontalAlignment(JTextField.CENTER);
        month.setFont(new Font("Roboto", Font.BOLD, 15));
        add(month);
    }

    private void addIntroduceYear(){
        introduceYear.setBounds(170, 240, 200,30);
        introduceYear.setVisible(true);
        introduceYear.setFont(new Font("Roboto", Font.BOLD, 15));
        add(introduceYear);
    }

    private void addYear(){
        year.setBounds(140,280, 200, 30);
        year.setHorizontalAlignment(JTextField.CENTER);
        year.setFont(new Font("Roboto", Font.BOLD, 15));
        add(year);
    }

    private void addSearchButton(){
        search.setBounds(170, 330, 150, 30);
        search.setVisible(true);
        search.setFont(new Font("Roboto", Font.PLAIN, 15));
        search.setBackground(new Color(255,255,0));
        search.addActionListener(new CreateButtonListener());
        add(search);
    }

    private void addNote(){
        note.setBounds(50, 380, 400, 30);
        note.setVisible(true);
        note.setFont(new Font("Roboto", Font.PLAIN, 15));
        add(note);
    }

    public int getDate() {
        return Integer.parseInt(date.getText());
    }

    public int getMonth() {
        return Integer.parseInt(month.getText());
    }

    public int getYear() {
        return Integer.parseInt(year.getText());
    }

    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection conn = null;
            int d= Integer.parseInt(date.getText());
            int m = Integer.parseInt(month.getText());
            int y = Integer.parseInt(year.getText());

            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");
                Statement statement = conn.createStatement();
                String sql= "select flight_month, flight_year from flights where flight_day='"+d+"'";
                ResultSet res= statement.executeQuery(sql);
                boolean verif=false;

                while(res.next() ){
                    if(res.getInt("flight_month")== m && res.getInt("flight_year")== y ){
                        verif=true;
                    }
                }

                if(verif==true){
                    DisplayAvailableFlights selectAflight = new  DisplayAvailableFlights(d, m, y);
                    selectAflight.setVisible(true);
                    SelectFlightDetails.this.dispose();

                }
                else{               //check the date to be correct
                    if(y%4 != 0){
                        if(d<0 || m <0 || m > 12 || y<2022 || (d>31 && m%2==1 && m <=7) || (d>31 && m%2==0 && m >=8) || (d >30 &&m%2==0 &&m<7)|| (d>30 && m%2==1 && m>=8) || (m==2 && d >28) ){
                            JOptionPane.showMessageDialog(null, "The day/month/year is incorrect","INCORRECT DATE" , JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No flight available, select another date","INCORRECT DATE" , JOptionPane.ERROR_MESSAGE);

                        }
                    }
                    else{
                        if(d<0 || m <0 || m > 12 || y<2022 || (d>31 && m%2==1 && m <=7) || (d>31 && m%2==0 && m >=8) || (d >30 &&m%2==0 &&m<7)|| (d>30 && m%2==1 && m>=8) || (m==2 && d >29) ){
                            JOptionPane.showMessageDialog(null, "The day/month/year is incorrect","INCORRECT DATE" , JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No flight available, select another date","INCORRECT DATE" , JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            } catch (SQLException ex) {
            ex.printStackTrace();
             }
        }
    }

}




