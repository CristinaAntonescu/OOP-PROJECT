package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class ChooseFlightView extends JFrame{

	private final JLabel instructions =  new JLabel("Introduce ID of the desired flight");
	private final JTextField idField = new JTextField("");
	private final JButton nextBtn = new JButton("NEXT");
	private final JButton backBtn = new JButton("BACK");
	private int dd, yy, mm;
	
	public ChooseFlightView(int d,int m, int y) {
		setSize(500, 300);
        setLayout(null);
        setTitle("Flights");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon1 = new ImageIcon("planeI.png");
        setIconImage(icon1.getImage());

		dd=d;
		mm=m;
		yy=y;

        addButton();
        addInstructions();
        addIdField();
		addBackButton();
	}
	
	  private void addButton() {
	        nextBtn.setBounds(350, 165, 100, 40);
	        nextBtn.setFont(new Font("Serif", Font.BOLD, 20));
	        nextBtn.setForeground(Color.blue);
	        nextBtn.addActionListener(new ConfirmButtonListener());
	        nextBtn.setFocusable(false);
	        add(nextBtn);
	    }

	private void addBackButton() {
		backBtn.setBounds(40, 165, 100, 40);
		backBtn.setFont(new Font("Serif", Font.BOLD, 20));
		backBtn.setForeground(Color.blue);
		backBtn.addActionListener(new BackButtonListener());
		backBtn.setFocusable(false);
		add(backBtn);
	}
	  
	  private void addInstructions() {
		  	instructions.setBounds(100, 50, 300, 40);
		  	instructions.setVisible(true);
		 	instructions.setForeground(Color.blue);
		 	instructions.setFont(new Font("Serif", Font.BOLD, 18));
		 	add(instructions);
	  }
	  
	  private void addIdField() {
		  	idField.setBounds(200, 100, 60, 40);
		  	idField.setHorizontalAlignment(JTextField.CENTER);
		  	idField.setFont(new Font("Serif", Font.BOLD, 15));
	        add(idField);
	    }
	  
	  private class ConfirmButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Connection conn = null;
			try {
				String introducedId = idField.getText();
				int f= Integer.parseInt(introducedId);
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airline_reservation", "postgres", "dontworrybehappy");
				Statement statement = conn.createStatement();
				String sql = "select * from flights where flight_id='"+ f +"'";
				ResultSet res = statement.executeQuery(sql);

				if (res.next())  {
					if (res.getInt("flight_id") == f ) {
						TicketsReservationView TicketsView = new TicketsReservationView(f, dd, mm, yy);
						TicketsView.setVisible(true);
						ChooseFlightView.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Sorry, there is no flight with the give ID! \r\n "
								+ "Plese enter another one.", "Incorrect Flight", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry, there is no flight with the give ID! \r\n "
							+ "Plese enter another one.", "Incorrect Flight", JOptionPane.ERROR_MESSAGE);
				}
				}catch(SQLException ex){
					ex.printStackTrace();
				}

			}

		}

	private class BackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

				if(e.getSource()== backBtn) {
					DisplayAvailableFlights daf= new DisplayAvailableFlights(dd,mm,yy);
					daf.setVisible(true);
					ChooseFlightView.this.dispose();
				}

		}
	}
		 

}
