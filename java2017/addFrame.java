package java2017;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.util.Calendar;
import java.util.GregorianCalendar;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class addFrame extends JFrame implements ActionListener, ItemListener{
	
	private static int addF_W = 600;
	private static int addF_H = 200;
	private static int char_area = 5;
	private int M;
	private int Year;
	
	private JTextField Y_Text = new JTextField(char_area);
	private JTextField P_Text = new JTextField(char_area);
	private JTextField N_Text = new JTextField(char_area);
	private RecordTable data;
	String[] categories = {"관리비", "식비", "통신비", "교통비", "생활용품", "기타"};
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
		
		Month.addItemListener(this);
		for(int i = 1; i<=31; i++)
			Day.addItem(Integer.toString(i));
		
		select.add(Y_Text);
		select.add(Month);
		select.add(Day);
		select.add(Categories);
		select.add(P_Text);
		select.add(N_Text);
		
		JPanel s = new JPanel();
		s.setLayout(new FlowLayout());
		JLabel s_text[] = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()};
		
		s_text[0].setText("year        ");
		s_text[1].setText("month       ");
		s_text[2].setText("day         ");
		s_text[3].setText("category       ");
		s_text[4].setText("cost        ");
		s_text[5].setText("note        ");
		for(int i = 0; i<6; i++)
			s.add(s_text[i]);
		
		
		
		JPanel confirm = new JPanel();
		JButton yes = new JButton("OK");
		yes.addActionListener(this);
		
		JButton no = new JButton("Cancel");
		no.addActionListener(this);
		
		confirm.add(yes); confirm.add(no);
		add(select);
		add(s);
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
				data.addRecord(Year, M, day, Integer.parseInt(P_Text.getText()),Categories.getSelectedIndex(), N_Text.getText());
				JFrame window = new JFrame("OK.");
				window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				JButton ok = new JButton("Added Succesfully");
				window.add(new JLabel("Added Succesfully. Hit the X button to close."));
				window.setSize(600, 100);
				window.setVisible(true);
				
			}
		}
		if(command.equals("Cancel"))
		{
			Y_Text.setText("");
			P_Text.setText("");
			N_Text.setText("");
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
		if(event.getStateChange() == ItemEvent.SELECTED)
		{
			if(Y_Text.getText() != "")
			{
				Day.removeAllItems();
				Calendar d = new GregorianCalendar(Integer.parseInt(Y_Text.getText()), Month.getSelectedIndex(), 1);
				System.out.println(Month.getSelectedIndex()+1);
				
				for(int i = 1; i<=d.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
					Day.addItem(Integer.toString(i));
			}
			
		}
		
	}
	
}
