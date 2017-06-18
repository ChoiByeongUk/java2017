package java2017;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class DateAddFrame extends JFrame implements ActionListener{
	
	
	private static int addF_W = 400;
	private static int addF_H = 200;
	private static int char_area = 5;
	
	private JTextField Y_Text = new JTextField(char_area);
	String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	JComboBox data;
	JComboBox Month = new JComboBox(month);
	public DateAddFrame(JComboBox d)
	{
		super();
		data = d;
		setLayout(new GridLayout(3,1));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(addF_W, addF_H);
		
		Y_Text.setText("");
		
		JPanel select = new JPanel();
		select.setLayout(new FlowLayout());
		select.add(Y_Text);
		select.add(Month);
		
		JPanel text = new JPanel();
		text.setLayout(new FlowLayout());
		JLabel year_text = new JLabel("year");
		JLabel month_text = new JLabel("month");
		text.add(year_text);
		text.add(month_text);
		
		
		
		
		JPanel confirm = new JPanel();
		JButton yes = new JButton("OK");
		yes.addActionListener(this);
		
		JButton no = new JButton("Cancel");
		no.addActionListener(this);
		confirm.add(yes); confirm.add(no);
		
		add(select);
		add(text);
		add(confirm);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		if(command.equals("OK"))
		{
			if(Y_Text.getText() != "")
			{
				int year = Integer.parseInt(Y_Text.getText());
				int month = Month.getSelectedIndex() + 1;
				JFrame window = new JFrame("OK.");
				window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				window.add(new JLabel("Added Succesfully. Hit the X button to close."));
				if(month < 10)
					data.addItem(year + "/" + 0 + month);
				else
					data.addItem(year + "/" + month);
				window.setSize(600, 100);
				window.setVisible(true);
				
			}
		}
		if(command.equals("Cancel"))
		{
			Y_Text.setText("");
			setVisible(false);
		}
		
	}
}
