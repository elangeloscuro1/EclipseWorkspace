import java.awt.FlowLayout ;
import java.awt.GridLayout ;
import java.awt.TextArea ;
import java.awt.TextField ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;

import javax.swing.JButton ;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JPanel ;

public class Memory extends JFrame
{
	public static void main(String[] args)
	{
		new Memory() ;
	}
	private Mem memA = new Mem("A") ;
	private Mem memD = new Mem("D") ;
	private Mem memSP = new Mem("SP") ;
	private Mem stack0 = new Mem("S0") ;
	private Mem stack1 = new Mem("S1") ;
	private Mem stack2 = new Mem("S2") ;
	private Mem stack3 = new Mem("S3") ;
	
	
	public Memory()
	{
		super("RAM") ;
		setLocationRelativeTo(null) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setLayout(new GridLayout(0, 4)) ;
		
		

		
		//add(new TextField("This is a text area")) ;
		
		
		JButton button = new JButton() ;
//		button.addActionListener(new ActionListener()
//		{
//			
//			@Override
//			public void actionPerformed(ActionEvent e)
//			{
//				if (t<10)
//				{
//					ram[t++].setValue(44) ;
//					pack() ;
//					repaint() ;
//				}
//				
//			}
//		}) ;
		
		add(button) ;
		
		
		
		
		pack() ; setVisible(true) ;	    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
