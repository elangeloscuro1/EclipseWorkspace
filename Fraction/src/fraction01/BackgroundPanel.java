package fraction01 ;







import java.net.URL;
import java.awt.Image ;

import java.awt.Graphics ;
import java.awt.Toolkit ; 
import java.awt.Container ;

import javax.swing.JFrame ;//
import javax.swing.ImageIcon ;//
import javax.swing.JTextField  ;
import javax.swing.text.PlainDocument ;
import javax.swing.JPanel ;//
import javax.swing.text.BadLocationException ;
import javax.swing.text.AttributeSet ;








@SuppressWarnings("serial")
public class BackgroundPanel extends JFrame
{    
    public Image background ;
    public URL directory ;
    
    public BackgroundPanel(JFrame frame)// might work better 
    {
        setBounds(0,0,800,700) ;
        setTitle("Ventana") ;
        setLocationRelativeTo(null) ;
        
        directory = this.getClass().getResource("/images/Bandera.jpg") ;        
        background = new ImageIcon(directory).getImage() ;
        
        Container contenedor = getContentPane() ;
        
        contenedor.add(panel) ;
        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
        setVisible(true) ;
    }
    
    public JPanel panel = new JPanel()
    {
        public void paintComponent(Graphics graphic)
        {
            graphic.drawImage(background, 0, 0, 
                    getWidth(), getHeight(), this) ;
        }
    } ;    
}
/*****************************************************
*  class ValidInput // validates JTextField integers only
*****************************************************/
@SuppressWarnings("serial")
class ValidInput extends PlainDocument
{
    private JTextField validInteger ;
    private int numberOfInt ;
    
    public ValidInput(JTextField validInteger, int numberOfInt)
    {
        this.validInteger = validInteger ;
        this.numberOfInt = numberOfInt ;        
    }
    public void insertString(int n, String s, AttributeSet a) throws BadLocationException
    {
        if (((validInteger.getText().length() + s.length())> this.numberOfInt) 
        || (((int)s.charAt(0) < 48) ||   ((int)s.charAt(0) >57)))
        {
            Toolkit.getDefaultToolkit().beep();
            return ;
        }
        super.insertString(n, s, a);
    }    
}
/*****************************************************
*  class Button // manages various button functionality
*****************************************************/
//class Button extends JButton implements ActionListener
//{
//    private boolean isPressed = false ;
//    private int buttonID ;
//    Button(String name)
//    {
//        setText(name) ;
//        buttonID++ ;
//    }
//    public void actionPerformed(ActionEvent e)
//    {
//        //JButton button = (JButton) e.getSource() ;
//        //JButton button = (JButton) e.getSource() ;
//        //button.setEnabled(false) ;
//        
//        if (buttonID == 1)
//        {
//            
//        }
//        
//        
//        
//        
//    }
//    
//    public void setIsPressed(boolean isPressed)
//    {
//        this.isPressed = isPressed ;
//    }
//    
//    
//    
//}