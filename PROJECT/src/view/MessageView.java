package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MessageView extends JFrame {

    private final JLabel thankYouMsg = new JLabel("Thank you for making this reservation!");
    private final JLabel thankYouMsg2 = new JLabel ( "Have a nice flight!");
    private final JButton exitBtn = new JButton("exit");
    private final JButton backBtn = new JButton("back");
    private final JLabel plane = new JLabel();
    private final JPanel panel = new JPanel();
	private int dd, mm, yy;

    public MessageView(int id, int t, int category) {
        setTitle("Thank You");
        setSize(650, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());

        addMessage();
        addBackBtn();
        addExitBtn();
        addPlaneImage();
		addText(id,t,category);
    }
    

	  public void addPlaneImage() {
		  plane.setIcon(new ImageIcon("cute_plane.png"));
		  panel.add(plane);
		  panel.setBounds(230,80, 200,118);
		  add(panel);
		  validate();
	  }

    private void addMessage() {
    	thankYouMsg.setBounds(100, 30, 600, 40);
    	thankYouMsg.setForeground(Color.blue);
    	thankYouMsg.setFont(new Font("Serif", Font.PLAIN, 28));
    	
    	thankYouMsg2.setBounds(200, 400, 600, 40);
    	thankYouMsg2.setForeground(Color.blue);
    	thankYouMsg2.setFont(new Font("Serif", Font.PLAIN, 28));
    	
        add(thankYouMsg);
        add(thankYouMsg2);
        }
    
    private void addBackBtn() {
		  backBtn.setBounds(80, 200, 100, 30);
		  backBtn.setVisible(true);
		  backBtn.setForeground(Color.blue);
		  backBtn.addActionListener(new ButtonListener());
		  backBtn.setFocusable(false);
		  add(backBtn);
	  }
    
    private void addExitBtn() {
		  exitBtn.setBounds(450, 200, 100, 30);
		  exitBtn.setVisible(true);
		  exitBtn.setForeground(Color.blue);
		  exitBtn.addActionListener(new ButtonListener());
		  exitBtn.setFocusable(false);
		  add(exitBtn);
	  }

	  private void addText(int id, int t, int category){
		  Connection conn = null;
		  JLabel label;
		  try {
			  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");
			  Statement statement = conn.createStatement();
			  String tck = "SELECT * from flights where flight_id='" + id + "'";
			  ResultSet r = statement.executeQuery(tck);

			  if (r.next()){
				  String s = "Flight date: " + r.getInt("flight_day") + "/" + r.getInt("flight_month")
						  + "/" + r.getInt("flight_year") + "   From: " + r.getString("departure_city")
						  + "   To: " + r.getString("destination_city") + "   Tickets: " + t;
				  String s1, s2 = "Depature Hour: " + r.getString("flight_hour")   ;

				  //select day mont year for the ChooseFlightView call in the next action listener
				  dd = r.getInt("flight_day");
				  mm = r.getInt("flight_month");
				  yy = r.getInt("flight_year");

				  if (category == 0) {
					  int p= t * r.getInt("price_business");
					  s1 = s2+ "  Business,  Total Price: " + p + "$";
				  }
				  else {
					  int p= t * r.getInt("price_economy");
					  s1 = s2+ "  Economy,  Total Price: "+ p + "$";
				  }

				  JLabel label1 = new JLabel(s1);
				  label1.setBounds(60, 340, 600, 30);
				  label1.setFont(new Font("Serif", Font.BOLD, 14));
				  label1.setVisible(true);
				  add(label1);

				  label = new JLabel(s);
				  label.setBounds(60, 290, 600, 30);
				  label.setFont(new Font("Serif", Font.BOLD, 14));
				  label.setVisible(true);
				  add(label);
			  }
		  } catch (SQLException ex) {
			  ex.printStackTrace();
		  }
	  }
	  
    private class ButtonListener implements ActionListener{
///will go back to the sign in window
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==backBtn) {
				ChooseFlightView FlightView = new ChooseFlightView(dd, mm, yy);
				FlightView.setVisible(true);
				MessageView.this.dispose();
			}
			else {
				if(e.getSource()== exitBtn) {
					MessageView.this.dispose();
				}
			}
			
		}
    	
    }
}
