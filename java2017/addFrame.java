package java2017;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class addFrame extends JFrame implements ActionListener, ItemListener{
	
	private static int addF_W = 400;
	private static int addF_H = 200;
	private static int char_area = 5;
	private int M;
	private int Year;
	
	private JTextField Y_Text = new JTextField(char_area);
	private JTextField P_Text = new JTextField(char_area);
	private RecordTable data;
	String[] categories = {"Management", "Food", "Phone", "Move", "Life", "etc."};
	String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	JComboBox Categories = new JComboBox(categories);
	JComboBox Month = new JComboBox(month);
	JComboBox Day = new JComboBox();
	public addFrame(RecordTable d)
	{
		super();
		data = d;
		setSize(addF_W, addF_H);
		setLayout(new GridLayout(3,1));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel select = new JPanel();
		Y_Text.setText("");
		P_Text.setText("");
		
		
		select.add(Y_Text);
		select.add(Month);
		select.add(Day);
		select.add(Categories);
		select.add(P_Text);
		
		JPanel confirm = new JPanel();
		JButton yes = new JButton("OK");
		yes.addActionListener(this);
		
		JButton no = new JButton("Cancel");
		no.addActionListener(this);
		
		confirm.add(yes); confirm.add(no);
		add(select);
		add(confirm);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		
		if(command.equals("OK"))
		{
			if(Y_Text.getText() != "")
			{
				Year = Integer.parseInt(Y_Text.getText());
				M = Integer.parseInt(Month.getSelectedItem().toString());
				int day = Integer.parseInt(Day.getSelectedItem().toString());
				System.out.println(Categories.getSelectedIndex());
				data.addRecord(Year, M, day, Integer.parseInt(P_Text.getText()),Categories.getSelectedIndex(), "");
			}
				
			
			
		}
		if(command.equals("Cancel"))
		{
			Y_Text.setText("");
			P_Text.setText("");
			setVisible(false);
		}
	}
	
	public static void main(String args[])
	{
		RecordTable d = new RecordTable();
		addFrame window = new addFrame(d);
		window.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		
		
	}
	
}
