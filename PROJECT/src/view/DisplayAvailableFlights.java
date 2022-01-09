package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayAvailableFlights extends JFrame{

    private final JLabel title = new JLabel("These are the available flights on your desired date:");
    private final JButton reserve = new JButton("Make a reservation");
    private JTable tableF;
    private int dd, mm, yy;

    public DisplayAvailableFlights(int d,int m, int y){
        setTitle("Display Available Flights");
        setSize(750, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(75,0,130));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());
        dd=d;
        mm=m;
        yy=y;
        addComponents(d,m,y);
    }

    private void addComponents(int d,int m, int y){
        addTitle();
        addButton();
        addTable(d,m,y);
    }

    private void addTitle(){
        title.setBounds(150,30,450,30);
        title.setVisible(true);
        title.setFont(new Font("Serif", Font.BOLD, 19));
        add(title);
    }

    private void addButton(){
        reserve.setBounds(170,400,400,30);
        reserve.setVisible(true);
        reserve.setFont(new Font("Roboto", Font.BOLD, 15));
        reserve.setBackground(new Color(147,112,219));
        reserve.addActionListener(new CreateButtonListener());
        reserve.setFocusable(false);
        add(reserve);
    }

    private void addTable(int d, int m, int y){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");
            Statement statement = conn.createStatement();
            String sql= "SELECT * FROM flights WHERE flight_day='"+d+"'";
            ResultSet res= statement.executeQuery(sql);
            Object [][] row = new Object[12][1000];
            int i=0;
            String[] columns = {"flight_id", "flight_day", "flight_month", "flight_year", "departure_city", "destination_city","seats_economy", "seats_business", "price_economy", "price_business", "flight_hour"};

            while(res.next() ) {
                if (res.getInt("flight_month") == m && res.getInt("flight_year") == y) {
                    row[i][0]=res.getInt("flight_id");
                    row[i][1]=res.getInt("flight_day");
                    row[i][2]=res.getInt("flight_month");
                    row[i][3]=res.getInt("flight_year");
                    row[i][4]=res.getString("departure_city");
                    row[i][5]=res.getString("destination_city");
                    row[i][6]=res.getInt("seats_economy");
                    row[i][7]=res.getInt("seats_business");
                    row[i][8]=res.getInt("price_economy");
                    row[i][9]=res.getInt("price_business");
                    row[i][10]=res.getString("flight_hour");
                    i++;

                }
            }

            tableF = new JTable(row,columns);

            JLabel label1 = new JLabel("flight_id");
            JLabel label2 = new JLabel("flight_d");
            JLabel label3 = new JLabel("flight_m");
            JLabel label4 = new JLabel("flight_y");
            JLabel label5 = new JLabel("from");
            JLabel label6 = new JLabel("to");
            JLabel label7 = new JLabel("s_economy");
            JLabel label8 = new JLabel("s_business");
            JLabel label9 = new JLabel("e_price");
            JLabel label10 = new JLabel("b_price");
            JLabel label11 = new JLabel("f_hour");

            label1.setBounds(20, 120, 50,20);
            label1.setVisible(true);
            add(label1);

            label2.setBounds(85, 120, 50,20);
            label2.setVisible(true);
            add(label2);

            label3.setBounds(150, 120, 50,20);
            label3.setVisible(true);
            add(label3);

            label4.setBounds(215, 120, 50,20);
            label4.setVisible(true);
            add(label4);

            label5.setBounds(280, 120, 50,20);
            label5.setVisible(true);
            add(label5);

            label6.setBounds(342, 120, 50,20);
            label6.setVisible(true);
            add(label6);

            label7.setBounds(397, 120, 70,20);
            label7.setVisible(true);
            add(label7);

            label8.setBounds(465, 120, 70,20);
            label8.setVisible(true);
            add(label8);

            label9.setBounds(538, 120, 70,20);
            label9.setVisible(true);
            add(label9);

            label10.setBounds(595, 120, 60,20);
            label10.setVisible(true);
            add(label10);

            label11.setBounds(655, 120, 60,20);
            label11.setVisible(true);
            add(label11);

            tableF.setBounds(20, 145, 700, 180);
            tableF.setVisible(true);
            add(tableF);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private class CreateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==reserve) {
                ChooseFlightView chooseFlightView= new ChooseFlightView(dd,mm,yy);
                chooseFlightView.setVisible(true);
                DisplayAvailableFlights.this.dispose();
            }
        }
    }
}
