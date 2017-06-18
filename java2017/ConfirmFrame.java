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

public class ConfirmFrame extends JFrame implements ActionListener{
	
	JComboBox data;
	public ConfirmFrame(JComboBox d)
	{
		super("Are you sure?");
		data = d;
		setSize(600,200);
		setLayout(new GridLayout(2,1));
		JLabel text = new JLabel("Are you sure you want to delete?");
		
		JPanel confirm = new JPanel();
		JButton yes = new JButton("OK");
		yes.addActionListener(this);
		
		JButton no = new JButton("Cancel");
		no.addActionListener(this);
		
		confirm.add(yes); confirm.add(no);
		add(text);
		add(confirm);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command.equals("OK"))
		{
			data.removeItemAt(data.getSelectedIndex());
			JFrame window = new JFrame("OK.");
			window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			window.add(new JLabel("Deleted Succesfully. Hit the X button to close."));
			window.setSize(600, 100);
			window.setVisible(true);
			setVisible(false);
		}
		else if(command.equals("Cancel"))
		{
			setVisible(false);
		}
	}
}
