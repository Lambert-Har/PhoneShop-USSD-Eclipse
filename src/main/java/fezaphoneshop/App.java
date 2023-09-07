package fezaphoneshop;

import java.sql.*;

import javax.servlet.annotation.WebServlet;
import hms.sdp.ussd.MchoiceUssdMessage;
import hms.sdp.ussd.MchoiceUssdTerminateMessage;
import hms.sdp.ussd.client.MchoiceUssdReceiver;
import hms.sdp.ussd.client.MchoiceUssdSender;

@WebServlet("/fezashop")
public class App extends MchoiceUssdReceiver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int root = 0;
	String response ="";

	@Override
	public void onMessage(MchoiceUssdMessage arg0) {
		String msg = arg0.getMessage();
		
		switch (root) {
		case 0:
			if (msg.equals("*223#")) {
				response = "Welcome to FEZA PHONE CHOP,\nFind phone U need,\n1. XIAOMI\n2. APPLE\n3. SAMSUNG\n4.LG";
				root = 1;
			} else {
				response = "UKNOWNn APPLICATION";
				root = 0;
			}
			
			break;
			
		case 1:
			if (msg.equals("1")) {
				response = ("List phone of brand XIAOMI and their specs");
				
				try {
					Connection connection = Database.getConnection();
					PreparedStatement st = connection.prepareStatement("SELECT `p_title` FROM `tbl_phone` WHERE `b_id`=1");
					ResultSet rs = st.executeQuery();
					int i= 1;
					while (rs.next()) {
//						response += rs.getInt("p_id")+") "+ rs.getString("p_title") + "\nSecs:\n" + rs.getString("p_network") +"\n"+ rs.getString("p_memory") +"\n"+ rs.getString("p_main_camera") +"\n"+ rs.getString("p_selfie_camera");
						response += i + ") "+rs.getString("p_title")+"\n";

						i++;
						
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else if(msg.equals("2")) {
				response = ("List phone of brand APPLE and their specs");
				
				try {
					Connection connection = Database.getConnection();
					PreparedStatement st = connection.prepareStatement("SELECT `p_title` FROM `tbl_phone` WHERE `b_id`=2");
					ResultSet rs = st.executeQuery();
					int i= 1;
					while (rs.next()) {
						response += i + ") "+rs.getString("p_title")+"\n";
						i++;
						
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else if(msg.equals("3")) {
				response = ("List phone of brand SAMSUNG and their specs");
				
				try {
					Connection connection = Database.getConnection();
					PreparedStatement st = connection.prepareStatement("SELECT `p_title` FROM `tbl_phone` WHERE `b_id`=3");
					ResultSet rs = st.executeQuery();
					int i= 1;
					while (rs.next()) {
						response += i + ") "+rs.getString("p_title")+"\n";
						i++;
						
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else if(msg.equals("2")) {
				response = ("List phone of brand LG and their specs");
				
				try {
					Connection connection = Database.getConnection();
					PreparedStatement st = connection.prepareStatement("SELECT `p_title` FROM `tbl_phone` WHERE `b_id`=4");
					ResultSet rs = st.executeQuery();
					int i= 1;
					while (rs.next()) {
						response += i + ") "+rs.getString("p_title")+"\n";
						i++;
						
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		default:
			break;
		}
		
		
		try {
			MchoiceUssdSender sender = new MchoiceUssdSender("http://localhost:8000/ussd/","fezashop","123");
			sender.sendMessage(response, arg0.getAddress(), arg0.getConversationId(), true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void onSessionTerminate(MchoiceUssdTerminateMessage arg0) {
		// TODO Auto-generated method stub

	}

}
