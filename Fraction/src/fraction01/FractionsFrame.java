package fraction01 ;




import java.awt.Color;
import java.awt.Container ;

import java.awt.GridLayout ; 
import java.awt.FlowLayout ;
import java.awt.BorderLayout ;


import java.awt.Font ;
import java.awt.Toolkit;


import javax.swing.BorderFactory ;
 
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.awt.font.TextAttribute;
import javax.swing.JFrame ;


import javax.swing.JPanel ;//
import javax.swing.JLabel ;
import javax.swing.JButton ;
import javax.swing.JTextField  ;

import java.util.Map;
import javax.swing.JOptionPane;




@SuppressWarnings("serial")
public class FractionsFrame extends JFrame implements ActionListener
{
        
    private static final String FONT = "verdan" ;// verdana or sherif
    private static final String FRAME_NAME = "Fracciones Tapia" ;
    private static final int FRAME_GCF_WIDTH = 1200 ;
    private static final int FRAME_GCF_HEIGHT = 700 ;
    
    Fraction fractions ;//?????????
    @SuppressWarnings("unused")
	private JFrame frame, findGCFframe, reduceframe ;
    private Container contentPane ;
    private JPanel northPanel, southPanel, westPanel, westNorth, westCenter, 
                   westC1, westC2, westC3, westC4, westC5, westC6, westC7,
                   centerPanel, centerColumn1, centerColumn2, centerColumn3, 
                   numeratorPanel, denominatorPanel, numeratorGCFpanel, 
                   denominatorGCFPanel, reducedNumeratorPanel, 
                   reducedDenominatorPanel, eastPanel, eastNorth, eastCenter ;    
    JTextField smallestDigit, greatestDigit, greatestFactor, quantity,
               numeratorGCF, denominatorGCF ;
    private JButton repeatYes, repeatNo, findGCF, reduce, start, enter ;
    //JLabel practiceType = myLabel("Find the Greatest Common Factor", 60) ;
    private JLabel practiceType, numeratorPrint, denominatorPrint , reducedNumeratorPrint,
                   reducedDenominatorPrint ;
    Integer min, max, qty, gcf, okCancelOption , index; Boolean repeat ;
    
    
    
    /*****************************************************
     *  Constructor
     *****************************************************/ 
    public FractionsFrame()
    {     
        setGame() ;
        frameIsVisible(true) ;
    }
    /*****************************************************
     *  Constructor Frame Setter
     *****************************************************/
    public void setFrame()
    {
        frame = new JFrame(FRAME_NAME) ;
        frame.setBounds(0,0,FRAME_GCF_WIDTH, FRAME_GCF_HEIGHT) ;        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;        
        frame.setLocationRelativeTo(null) ;
    }
    /*****************************************************
     *  Frame Visibility Setter
     *****************************************************/     
    public void frameIsVisible(boolean visible)
    {
        frame.setVisible(visible) ;
    }
    /*****************************************************
     *  Constructor Frame Filling
     *****************************************************/     
    public void setGame()
    {
        setFrame() ;
        contentPane = getContentPane(); //.setLayout(new Border) ;
        contentPane.setLayout(new BorderLayout());        
        frame.add(contentPane) ;        
        setNorthPanel() ; setSouthPanel() ; 
        setWestPanel() ; setCenterPanel() ; setEastPanel() ;
        contentPane.add(northPanel, BorderLayout.NORTH) ;
        contentPane.add(southPanel, BorderLayout.SOUTH) ;
        contentPane.add(centerPanel, BorderLayout.CENTER) ;
        contentPane.add(westPanel, BorderLayout.WEST) ;
        contentPane.add(eastPanel, BorderLayout.EAST) ;
    }
    /*****************************************************
     *  North Panel Setter
     *****************************************************/  
    public void setNorthPanel()
    {
        northPanel = new JPanel() ;
        northPanel.setLayout(new BorderLayout()) ;
        northPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        northPanel.setBackground(Color.GREEN.darker()) ;        
        practiceType = myLabel("Find the GCF (Greatest Common Factor) or "
                     + "reduce each fraction",30) ;//myLabel("Find the Greatest Common Factor", 60) ;/////////////
        practiceType.setForeground(Color.white) ;
        practiceType.setHorizontalAlignment(JLabel.CENTER) ;
        northPanel.add(practiceType) ;                
    }
    /*****************************************************
     *  South Panel Setter
     *****************************************************/ 
    public void setSouthPanel()
    {
        southPanel = new JPanel() ;
        southPanel.setLayout(new FlowLayout()) ;
        southPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        southPanel.setBackground(Color.RED) ;
        JLabel label = myLabel(" ", 70) ;///  THIS IS THE SOUTH PANEL WHICH IS COLOR RED
        label.setForeground(Color.white) ;        
        southPanel.add(label) ;                
    }
    /*****************************************************
     *  West Panel Setter
     *****************************************************/ 
    public void setWestPanel()
    {
        setWestNorth() ; setWestCenter() ;        
        westPanel = new JPanel() ;
        westPanel.setLayout(new BorderLayout()) ;
        westPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        westPanel.add(westNorth, BorderLayout.NORTH) ;
        westPanel.add(westCenter, BorderLayout.CENTER) ;     
    }
    //   West Panel(North) Setter      
    public void setWestNorth()
    {        
        westNorth = new JPanel() ;        
        JLabel label = myLabel("  Answers Level!  ", 35) ;
        westNorth.setBackground(Color.LIGHT_GRAY) ;
        westNorth.setOpaque(true) ;
        westNorth.add(label) ;   
    }
    //   West Panel(Center) Setter 
    public void setWestCenter()
    {
        westCenter = new JPanel() ;
        setWestC1() ;setWestC2() ;setWestC3();
        setWestC4() ;setWestC5() ;setWestC6() ;setWestC7() ;
        westCenter.setLayout(new GridLayout(7,2)) ; 
        westCenter.add(westC1) ; westCenter.add(westC2) ;
        westCenter.add(westC3) ; westCenter.add(westC4) ;
        westCenter.add(westC5) ; westCenter.add(westC6) ;
        westCenter.add(westC7) ;
    }
    //  West Panel (Center 1-7) Setters
    public void setWestC1()
    {
        westC1 = new JPanel() ;
        westC1.setLayout(new BorderLayout()) ;// Border ok
        westC1.setBorder(BorderFactory.createLineBorder(Color.black)) ;        
        westC1.add(myLabel("Smallest Digit: ", 25), BorderLayout.CENTER) ;
        smallestDigit = new JTextField(2) ;
        smallestDigit.setDocument(new ValidInput(smallestDigit, 3)) ;
        smallestDigit.setFont(new Font(FONT, Font.BOLD , 40)) ;
        westC1.add(smallestDigit, BorderLayout.EAST) ;
    }
    public void setWestC2()
    {
        westC2 = new JPanel() ;
        westC2.setLayout(new BorderLayout()) ;// Border ok
        westC2.setBorder(BorderFactory.createLineBorder(Color.black)); 
        westC2.add(myLabel("Greatest Digit: ",25), BorderLayout.WEST) ;
        greatestDigit = new JTextField(2) ;
        greatestDigit.setDocument(new ValidInput(greatestDigit, 3)) ;
        greatestDigit.setFont(new Font(FONT, Font.BOLD , 40)) ;
        westC2.add(greatestDigit, BorderLayout.EAST) ;
    }
    public void setWestC3()
    {
        westC3 = new JPanel() ;
        westC3.setLayout(new BorderLayout()) ;// Border ok
        westC3.setBorder(BorderFactory.createLineBorder(Color.black)); 
        westC3.add(myLabel("Greatest Factor: ", 25), BorderLayout.WEST) ;        
        greatestFactor = new JTextField(2) ;
        greatestFactor.setDocument(new ValidInput(greatestFactor, 3)) ;        
        greatestFactor.setFont(new Font(FONT, Font.BOLD , 40)) ;
        westC3.add(greatestFactor, BorderLayout.EAST) ;
    }
    public void setWestC4()
    {
        westC4 = new JPanel() ;
        westC4.setLayout(new BorderLayout()) ;// Border ok
        westC4.setBorder(BorderFactory.createLineBorder(Color.black)); 
        westC4.add(myLabel("Quantity: ", 25), BorderLayout.WEST) ;        
        quantity = new JTextField(2) ;
        quantity.setDocument(new ValidInput(quantity, 3)) ;        
        quantity.setFont(new Font(FONT, Font.BOLD , 40)) ;
        westC4.add(quantity, BorderLayout.EAST) ;
    }
    public void setWestC5()
    {
        westC5 = new JPanel() ;
        westC5.setLayout(new BorderLayout()) ;// Border ok
        westC5.setBorder(BorderFactory.createLineBorder(Color.black)); 
        westC5.add(myLabel("Repeat Answer: ", 25), BorderLayout.WEST) ;
        repeatNo = myButton("NO", 15) ;
        repeatYes = myButton("Yes", 15) ;
        repeatNo.addActionListener(this) ;
        repeatYes.addActionListener(this);  
        westC5.add(repeatNo, BorderLayout.CENTER) ;
        westC5.add(repeatYes, BorderLayout.EAST) ;
    }    
    public void setWestC6()
    {
        westC6 = new JPanel() ;
        westC6.setLayout(new GridLayout()) ;// Border ok
        westC6.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        findGCF = myButton("Find GCF", 25) ;
        reduce = myButton("Reduce", 25) ;
        findGCF.addActionListener(this) ;
        reduce.addActionListener(this) ;
        westC6.add(findGCF) ;
        westC6.add(reduce) ;
    }
    public void setWestC7()
    {
        westC7 = new JPanel() ;
        westC7.setLayout(new BorderLayout()) ;// Border ok
        westC7.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        start = myButton("START", 40) ;
        start.addActionListener(this) ;
        westC7.add(start) ;        
    }    
    /*****************************************************
     *  Center Panel displays the chosen type of practice
     *****************************************************/
    public void setCenterPanel()// need to be set after the game
    {
        centerPanel = new JPanel() ;
        centerPanel.setLayout(new GridLayout()) ;
        //centerPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;///////
        setCenterColumn1() ; setCenterColumn2() ; setCenterColumn3() ;
        centerPanel.add(centerColumn1) ;
        centerPanel.add(centerColumn2) ;
        centerPanel.add(centerColumn3) ;
    }   
    //  center first column panel (1)
    public void setCenterColumn1()
    {
        centerColumn1 = new JPanel() ;
        centerColumn1.setLayout(new GridLayout(2,2)) ;
        setNumeratorPanel() ; setDenominatorPanel() ;
        centerColumn1.add(numeratorPanel) ;        
        centerColumn1.add(denominatorPanel) ;
    }    
    public void setNumeratorPanel()
    {        
        numeratorPanel = new JPanel() ;
        numeratorPanel.setLayout(new BorderLayout()) ;       
        numeratorPrint = myLabel("1", 150) ;
        myUnderlinedLabel(numeratorPrint) ;////////////////////////////// TESTING ////////////////
        numeratorPrint.setHorizontalAlignment(JLabel.CENTER) ;
        JLabel givenForm = myLabel("Given Form:", 25) ;
        numeratorPanel.add(givenForm, BorderLayout.NORTH) ;
        numeratorPanel.add(numeratorPrint, BorderLayout.SOUTH) ;
    }
    public void setDenominatorPanel()
    {
        denominatorPanel = new JPanel() ;
        denominatorPanel.setLayout(new BorderLayout()) ;
        denominatorPrint = myLabel("21", 150) ;
        denominatorPrint.setHorizontalAlignment(JLabel.CENTER) ;
        denominatorPanel.add(denominatorPrint, BorderLayout.NORTH) ;        
    }
    //  center second column panel (2)
    public void setCenterColumn2()
    {
        centerColumn2 = new JPanel() ;
        centerColumn2.setLayout(new GridLayout(3,2)) ;
        //centerColumn2.setBorder(BorderFactory.createLineBorder(Color.black)) ;/////////////
        setNumeratorGCFPanel() ; setDenominatorGCFPanel() ;
        JLabel equals = myLabel("=", 250) ;
        equals.setHorizontalAlignment(JLabel.CENTER) ;        
        centerColumn2.add(numeratorGCFpanel) ;
        centerColumn2.add(equals) ;
        centerColumn2.add(denominatorGCFPanel) ;// also has the enter button     
    }
    public void setNumeratorGCFPanel()
    {
        numeratorGCFpanel = new JPanel() ;
        numeratorGCFpanel.setLayout(new BorderLayout()) ;
        JPanel numSubPanel = new JPanel() ; //JPanel numSubPanel = new JPanel() ;
        numSubPanel.setLayout(new GridLayout()) ;        
        //numSubPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;//////////////////////
        numeratorGCF = new JTextField(2) ;
        numeratorGCF.setDocument(new ValidInput(numeratorGCF, 3)) ;       
        numeratorGCF.setFont(new Font(FONT, Font.BOLD , 50)) ;
        JLabel gcf = myLabel("GCF:", 25) ;
        gcf.setHorizontalAlignment(JLabel.CENTER) ;
        numSubPanel.add(gcf) ;
        numSubPanel.add(numeratorGCF) ;
        numSubPanel.add(myLabel("", 25)) ;        
        numeratorGCFpanel.add(numSubPanel, BorderLayout.SOUTH) ;
    }
    public void setDenominatorGCFPanel()
    {
        denominatorGCFPanel = new JPanel() ;
        denominatorGCFPanel.setLayout(new BorderLayout()) ;
        JPanel denomSubPanel = new JPanel() ;
        denomSubPanel.setLayout(new GridLayout()) ;       
        //denomSubPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;//////////////////////
        denominatorGCF = new JTextField(2) ;
        denominatorGCF.setDocument(new ValidInput(denominatorGCF, 3)) ;        
        denominatorGCF.setFont(new Font(FONT, Font.BOLD , 50)) ;
        JLabel gcf = myLabel("GCF:", 25) ;        
        gcf.setHorizontalAlignment(JLabel.CENTER) ;
        enter = myButton("ENTER", 40) ;
        enter.addActionListener(this)  ;////========================TESTIING    ==========TESTIING    ==========TESTIING
        denomSubPanel.add(gcf) ;
        denomSubPanel.add(denominatorGCF) ;
        denomSubPanel.add(myLabel("", 25)) ;
        denominatorGCFPanel.add(denomSubPanel, BorderLayout.NORTH) ;
        denominatorGCFPanel.add(enter, BorderLayout.SOUTH) ;
    }
    //  center third column panel (3)
    public void setCenterColumn3()
    {
        centerColumn3 = new JPanel() ;
        centerColumn3.setLayout(new GridLayout(2,2)) ;
        setReducedNumeratorPanel() ; setReducedDenominatorPanel() ;
        centerColumn3.add(reducedNumeratorPanel) ;        
        centerColumn3.add(reducedDenominatorPanel) ;
    }    
    public void setReducedNumeratorPanel()
    {        
        reducedNumeratorPanel = new JPanel() ;
        reducedNumeratorPanel.setLayout(new BorderLayout()) ;       
        reducedNumeratorPrint = myLabel("3", 150) ;
        myUnderlinedLabel(reducedNumeratorPrint) ;////////////////////////////// TESTING ////////////////
        reducedNumeratorPrint.setHorizontalAlignment(JLabel.CENTER) ;
        JLabel reducedForm = myLabel("Reduced Form:", 25) ;
        reducedForm.setHorizontalAlignment(JLabel.CENTER) ;
        reducedNumeratorPanel.add(reducedForm, BorderLayout.NORTH) ;
        reducedNumeratorPanel.add(reducedNumeratorPrint, BorderLayout.SOUTH) ;
    }
    public void setReducedDenominatorPanel()
    {
        reducedDenominatorPanel = new JPanel() ;
        reducedDenominatorPanel.setLayout(new BorderLayout()) ;
        reducedDenominatorPrint = myLabel("23", 150) ;
        reducedDenominatorPrint.setHorizontalAlignment(JLabel.CENTER) ;
        reducedDenominatorPanel.add(reducedDenominatorPrint, BorderLayout.NORTH) ;        
    }
    /*****************************************************
     *  East panel displays the process of the game
     *****************************************************/    
    public void setEastPanel()// need to be set after the game
    {
        setEastNorth() ; setEastCenter() ;        
        eastPanel = new JPanel() ;
        eastPanel.setLayout(new BorderLayout()) ;
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        eastPanel.add(eastNorth, BorderLayout.NORTH) ;
        eastPanel.add(eastCenter, BorderLayout.CENTER) ;
    }
    //   West Panel(North) Setter      
    public void setEastNorth()
    {        
        eastNorth = new JPanel() ;        
        JLabel label = myLabel("  Process  ", 35) ;
        eastNorth.setBackground(Color.GREEN) ;
        eastNorth.setOpaque(true) ;
        eastNorth.add(label) ;   
    }
    //   West Panel(Center) Setter 
    public void setEastCenter()
    {
        eastCenter = new JPanel() ;
        eastCenter.setLayout(new GridLayout(6,1)) ;
        eastCenter.setBorder(BorderFactory.createLineBorder(Color.black)) ;
        
        eastCenter.add(myLabel("  Level!  ", 35)) ;
        eastCenter.add(myLabel("  Level!  ", 35)) ;
        //eastCenter.add(myLabel("  Level!  ", 35)) ;
        //eastCenter.add(myLabel("  Level!  ", 35)) ;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*****************************************************
     *  Other Methods: myButton, myLabel
     *****************************************************/
    public JButton myButton(String name, int size)
    {
        JButton button = new JButton(name) ;
        button.setFont(new Font(FONT, Font.BOLD , size)) ;
        return button ;
    }
    public JLabel myLabel(String name, int size)
    {
        JLabel label = new JLabel(name) ;
        label.setFont(new Font(FONT, Font.BOLD , size)) ;
        return label ;
    }
    @SuppressWarnings("unchecked")
	public JLabel myUnderlinedLabel(JLabel label)
    {
        label.setText(" " + label.getText() + " ") ;
        Font font = label.getFont() ;
        @SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes() ;
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON) ;
        label.setFont(font.deriveFont(attributes)) ;
        return label ;
    }
    public int stringToInt(String aString)
    {
        if (aString.equals(""))
        {
            return 0 ;
        }
        return (Integer.parseInt(aString)) ;
    }
    public void smallestGreatestDigitSwap(int min, int max)
    {
        int tempMin = min , tempMax = max ;
        this.min = ((tempMin > tempMax) ? tempMax : tempMin) ;
        this.max = ((tempMin > tempMax) ? tempMin : tempMax) ;
        smallestDigit.setText(Integer.toString(this.min)) ;
        greatestDigit.setText(Integer.toString(this.max)) ;
    } 
    /*****************************************************
     *  ??????
     *****************************************************/
    public void actionPerformed(ActionEvent e)
    {//System.out.println(new JButton()) ;
        yesOrNoSwitch(e) ;        
        reduceOrGCF(e) ;
        veryfySetUp(e) ;
        setStartPractice(e) ;
        enterLimit(e) ;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    public void yesOrNoSwitch(ActionEvent e)
    {
        Color color = (new JButton().getBackground()) ;
        if (e.getSource() == repeatYes)
        {
            repeatYes.setEnabled(false) ;
            repeatYes.setBackground(Color.GRAY) ;
            repeatNo.setEnabled(true) ;
            repeatNo.setBackground(color) ;
        }
        if (e.getSource() == repeatNo)
        {
            repeatNo.setEnabled(false) ;
            repeatNo.setBackground(Color.GRAY) ;
            repeatYes.setEnabled(true) ;
            repeatYes.setBackground(color) ;
        }        
    }
    public void reduceOrGCF(ActionEvent e)
    {
        Color color = (new JButton().getBackground()) ;
        if (e.getSource() == findGCF)
        {
            findGCF.setEnabled(false) ;
            findGCF.setBackground(Color.GRAY) ;
            reduce.setEnabled(true) ;
            reduce.setBackground(color) ;
        }
        if (e.getSource() == reduce)
        {
            reduce.setEnabled(false) ;
            reduce.setBackground(Color.GRAY) ;
            findGCF.setEnabled(true) ;
            findGCF.setBackground(color) ;
        }         
    }    
    public boolean isCompleteSetUp(ActionEvent e)
    {
        boolean isCompleteSetUp = true ;        
        if ( (e.getSource() == start)
        &&  ((smallestDigit.getText().equals(""))
            ||  (greatestDigit.getText().equals(""))
            ||  (greatestFactor.getText().equals(""))
            ||  (quantity.getText().equals(""))
            ||  (repeatYes.isEnabled() && repeatNo.isEnabled())
            ||  (findGCF.isEnabled() && reduce.isEnabled())) )
        {
            isCompleteSetUp = false ;
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Your set up is incomplete!", 
                        "Set Up Warning", JOptionPane.INFORMATION_MESSAGE) ;            
        }
        return isCompleteSetUp ;
    }    
    public void veryfySetUp(ActionEvent e)
    {        
        if ((e.getSource() == start) && isCompleteSetUp(e))
        {
            /** min, max, qty, and gcf have to be declare here*/
            min = stringToInt(smallestDigit.getText()) ;
            max = stringToInt(greatestDigit.getText()) ;
            qty = stringToInt(quantity.getText()) ;
            gcf = stringToInt(greatestFactor.getText()) ;
            Fraction temp = null ;
            //** This if statement prevents values of zeros            
            if (min == 0 || max == 0 || qty == 0 || gcf == 0)
            {
                Toolkit.getDefaultToolkit().beep();
                int ok = JOptionPane.showConfirmDialog(null, "0 is not a valid "
                                   + "entry \npress \"ok\" to substitute by 1\n", 
                                     "Invalid set up ", JOptionPane.OK_CANCEL_OPTION,
                                     JOptionPane.PLAIN_MESSAGE) ;
                if (ok == JOptionPane.YES_OPTION)
                {
                    zeroToOneConverter(min ,max, qty, gcf) ;
                }
            }
            /** repeat and test have to be declare here first part of the...*/
            repeat = !repeatYes.isEnabled() ;//... variavble set are declared a.... 
            int test = Fraction.maxValueCheck(min,qty) ;// very top of this method
            String message = messegeLogicString(min, max, qty, gcf, test, repeat ) ;
            //***     LOGIC  ****   ***     LOGIC  **** ***     LOGIC  ****
            if ((repeat && ((min > max) || (max == min) || (gcf < 2) 
            ||  (min > max && gcf < 2) || (min == max && gcf < 2)))
            ||  (  !repeat && ((max < test) || (gcf < 2) ||(max < test && gcf <2))))
            {
                Toolkit.getDefaultToolkit().beep();
                okCancelOption = JOptionPane.showConfirmDialog(null, message, 
                               "Invalid set up ", JOptionPane.OK_CANCEL_OPTION,
                               JOptionPane.PLAIN_MESSAGE) ;
                if ( repeat && okCancelOption == JOptionPane.YES_OPTION 
                &&  ((min > max) || (min > max && gcf < 2)))
                {System.out.println("=========  1   =========") ;
                    smallestGreatestDigitSwap(min, max);                    
                    temp = new Fraction(min, max, qty, gcf, repeat) ;//for (Fraction show: temp.getFractionList())System.out.println(show) ;
                    //disableTo() ;// enables and disable for Testing
                    
                }                
                else if ( !repeat && okCancelOption == JOptionPane.YES_OPTION 
                &&  ((max < test) || (max < test && gcf < 2)))
                {System.out.println("=======     2  ===========") ;
                    greatestDigit.setText(Integer.toString(test)) ;
                    temp = new Fraction(min, max, qty, gcf, repeat) ;//for (Fraction show: temp.getFractionList())System.out.println(show) ;
                    //disableTo() ;// enables and disable for Testing
                    
                }
                else if ( (repeat && okCancelOption == JOptionPane.YES_OPTION 
                &&       ((min == max) || (gcf < 2) || ((min == max && gcf < 2)))) 
                ||        (!repeat  && gcf < 2))
                {System.out.println("========   3   ==========") ;
                    temp = new Fraction(min, max, qty, gcf, repeat) ;//for (Fraction show: temp.getFractionList())System.out.println(show) ;
                    //disableTo() ;// enables and disable for Testing
                }   
            }
            else System.out.println("min   "+min+"  max     "+ max) ;//============================
            {System.out.println("========   4   ==========") ;
                if (temp == null && okCancelOption == null)
                {System.out.println("========   4.1   ==========") ;
                    fractions = new Fraction(min, max, qty, gcf, repeat) ;for (Fraction show: fractions.getFractionList())System.out.println(show) ;
                    disableTo() ;// enables and disable for Testing
                }
                else if (okCancelOption != JOptionPane.CANCEL_OPTION)
                {System.out.println("========   4.2   ==========") ;
                    fractions = temp ;               for (Fraction show: fractions.getFractionList())System.out.println(show) ;
                    disableTo() ;// enables and disable for Testing              
                        
                }
                else System.out.println("========   4.3  ==========") ;
                {
                    temp = null ;
                }
            }
        }
    }
    
    public void setStartPractice(ActionEvent e)
    {
        if (fractions != null && (e.getSource() == start || e.getSource() == enter))
        {
            
                                    //System.out.println(fractions.getFractionFromList(index));//////////////////////
            nextFraction(e) ;        System.out.println("========   ?????  ==========") ;//==========
            
        }
        
    }    
    public void disableTo()
    {
        smallestDigit.setEnabled(false);
        greatestDigit.setEnabled(false);
        greatestFactor.setEnabled(false);
        quantity.setEnabled(false);
        repeatYes.setEnabled(false);
        repeatNo.setEnabled(false);
        findGCF.setEnabled(false);
        reduce.setEnabled(false);
        start.setEnabled(false);
    }
    public void zeroToOneConverter(int min, int max, int qty, int gcf)
    {
        if (min == 0)
        {
            this.min = 1 ;
            smallestDigit.setText(Integer.toString(this.min)) ;
        }
        if (max == 0)
        {
            this.max = 1 ;
            greatestDigit.setText(Integer.toString(this.max)) ;
        }
        if (qty == 0)
        {
            this.qty = 1 ;
            quantity.setText(Integer.toString(this.qty)) ;
        }
        if (gcf == 0)
        {
            this.gcf = 1 ;
            greatestFactor.setText(Integer.toString(this.gcf)) ;
        }
        
    }
    //String messege = messegeLogicString(min, max, qty, gcf, test, repeat ) ;
    public String messegeLogicString(int min, int max, int qty, int gcf, int test, boolean repeat)
    {
        String message  = "" ;
        String message1 = "*1 There is no credit for: Greatest Factor < 2\n" ;
        String message2 = "*2 In order to generate " + qty + " fraction(s) "
                        + "with unique answers, the greatest digit will be changed to " + test +"\n";
        String message3 = "*3 There is no credit for: Smallest Digit = Greatest Digit\n" ;
        String message4 = "*4 The Smallest Digit Cannot be greater than the Greatest Digit\n"
                        + "         press \"ok\" to swap them out\n" ;
        if (repeat && (min > max))// A
        {
            message = message4 ;
        }        
        if (repeat && (max == min))// B
        {
            message = message3 ;
        }
        if (repeat && (gcf < 2) )// C
        {
            message = message1 ;
        }
        if (repeat && (min > max) && (gcf < 2))// D
        {
            message = message4 + "\n" + message1 ;
        }
        if (repeat && (max == min) && (gcf < 2))// E
        {
            message = message3 + "\n" + message1 ;
        }
        if (!repeat && (max < test))// F
        {    message = message2 ;
        
        }
        if (!repeat && (gcf < 2))// G
        {
            message = message1 ;
        }
        if (!repeat && (max < test) && (gcf < 2))// H
        {
            message = message2 + "\n" + message1 ;
        }
        return message ;
    }
    public void nextFraction(ActionEvent e)
    {
        if (index == null)
        {
            index = 0 ;
        }        
        if (fractions != null && (e.getSource() == start || e.getSource() == enter))
        {
            fractions.getFractionFromList(index) ;
            numeratorPrint.setText(Integer.toString(fractions.getFractionFromList(index).getGivenNumerator())) ;
            denominatorPrint.setText(Integer.toString(fractions.getFractionFromList(index).getGivenDenominator())) ;
            reducedNumeratorPrint.setText(Integer.toString(fractions.getFractionFromList(index).getReducedNumerator())) ;
            reducedDenominatorPrint.setText(Integer.toString(fractions.getFractionFromList(index).getReducedDenominator())) ;
            
        }
        index++ ;
    }
    public void enterLimit(ActionEvent e)
    {
        if (e.getSource() == enter && index >= fractions.getFractionList().length)
        {
            enter.setEnabled(false) ;
        }
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
}