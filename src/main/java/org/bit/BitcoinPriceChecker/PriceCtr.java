package org.bit.BitcoinPriceChecker;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PriceCtr {

	JFrame mainFrame;
	JTextField _samt;
	JTextField _bamt;
	JButton _check;
	JLabel _header;
	JLabel _rd;
	JLabel _line;
	JLabel _Per_i;
	JLabel _Amt_i;
	JLabel _Pft_i;
	JLabel _By_i;
	JLabel _Sl_i;
	
	public static void main(String args[]){
		new PriceCtr().prepareGui();
	}
	
	public void prepareGui(){
		
		mainFrame = new JFrame("BTC Price Check");
		mainFrame.setSize(600,380);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("BTC Price Check");
	
		JPanel panel = new JPanel();
		GridBagLayout gbl1 = new GridBagLayout();
		GridBagConstraints gbc1 = new GridBagConstraints();
		panel.setLayout(gbl1);
				
		JPanel c0 = new JPanel();
		c0.add(new JLabel("(฿) BitCoin Price Check"));
		
		JPanel c1 = new JPanel();
		_samt = new JTextField();
		_samt.setText("Amount Sell");
		_bamt = new JTextField();
		_bamt.setText("Amount Bought");
		_check = new JButton("Check");
		c1.add(_bamt);
		c1.add(_samt);
		c1.add(_check);
		
		_check.setActionCommand("check");
		_check.addActionListener(new ClickActionListner());
		
		JPanel c2 = new JPanel();
		c2.add(new JLabel("Result Details"),BorderLayout.LINE_START);
//		c2.setSize(200, 200);
		
		JPanel c3 = new JPanel();
		GridBagLayout gbl3 = new GridBagLayout();
		GridBagConstraints gbc3 = new GridBagConstraints();
		c3.setLayout(gbl3);
		c3.setBackground(java.awt.Color.white);
		
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 1;
		gbc3.gridy = 1;
		gbc3.insets.right = 20;
		c3.add(new JLabel("Percentage Increased:"),gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 1;
		gbc3.gridy = 2;
		gbc3.insets.right = 20;
		c3.add(new JLabel("Amount Increased:"),gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 1;
		gbc3.gridy = 3;
		gbc3.insets.right = 20;
		c3.add(new JLabel("Profit:"),gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 1;
		gbc3.gridy = 4;
		gbc3.insets.right = 20;
		c3.add(new JLabel("Purchased At:"),gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 1;
		gbc3.gridy = 5;
		gbc3.insets.right = 20;
		c3.add(new JLabel("Sell At:"),gbc3);
		

		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 2;
		gbc3.gridy = 1;
		_Per_i = new JLabel("% 0");
		c3.add(_Per_i,gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 2;
		gbc3.gridy = 2;
		_Amt_i = new JLabel("$ 0");
		c3.add(_Amt_i,gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 2;
		gbc3.gridy = 3;
		_Pft_i = new JLabel("$ 0");
		c3.add(_Pft_i,gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 2;
		gbc3.gridy = 4;
		_By_i = new JLabel("$ 0");
		c3.add(_By_i,gbc3);
		c3.add(new JLabel("$ 0"),gbc3);
		gbc3.fill = GridBagConstraints.HORIZONTAL;
		gbc3.gridx = 2;
		gbc3.gridy = 5;
		_Sl_i = new JLabel("$ 0");
		c3.add(_Sl_i,gbc3);
		c3.add(new JLabel("$ 0"),gbc3);
		
		//Setting line
		JPanel c4 = new JPanel();
		c4.add(new JLabel("---------------------------------------------------------------------"));
		
		JPanel c5 = new JPanel();
		JTable table = new JTable();
		

		// Setting Master panel
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 1;
//		gbc1.insets.bottom = 30;
		panel.add(c0,gbc1);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 2;
		gbc1.insets.top = 30;
		panel.add(c1,gbc1);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 3;
		gbc1.insets.top = 5;
		panel.add(c2,gbc1);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 4;
//		gbc1.insets.top = 5;
		panel.add(c4,gbc1);
		gbc1.fill = GridBagConstraints.HORIZONTAL;
		gbc1.gridx = 1;
		gbc1.gridy = 5;
		gbc1.insets.top = 5;
		gbc1.ipadx = 10;
		gbc1.ipady = 20;
		panel.add(c3,gbc1);
		
		mainFrame.getContentPane().add(panel, BorderLayout.PAGE_START);
		mainFrame.setVisible(true);
		
	}
	
	
	private class ClickActionListner implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			String cmd = e.getActionCommand();
			if(cmd.equalsIgnoreCase("check")){
				
				try{
					
					int sell = Integer.parseInt(_samt.getText());
					int purchased = Integer.parseInt(_bamt.getText());
					int profit = sell - purchased;
					int amtposition = profit + purchased;
					int per_increased = (((profit)*100)/purchased);
				}
				catch(Exception e1){
					
				}
			
				
			}
			
		}
		
	}


}
