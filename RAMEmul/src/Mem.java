import java.awt.Component ;
import java.awt.FlowLayout ;
import java.awt.Graphics ;
import java.awt.Insets ;

import javax.swing.JLabel ;
import javax.swing.JPanel ;
import javax.swing.border.BevelBorder ;
import javax.swing.border.Border ;

public class Mem extends JPanel
{
	private int value ;
	private JLabel label = new JLabel() ;

	public Mem(String name)
	{
		label.setText(String.format("%10s ", "")) ;
		label.setBorder(new BevelBorder(1) ) ;
		JLabel l = new JLabel(String.format("%10s ", name)) ;
		setLayout(new FlowLayout()) ;
		add(l) ; 		add(label) ;
	}

	public void setValue(int value)
	{
		this.value = value ;
		this.label.setText(String.format("%10s ", value)) ;
	}

	public int getValue()
	{
		return value ;
	}

}
