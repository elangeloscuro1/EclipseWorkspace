package fraction01 ;

import java.util.Scanner ;
public class Fraction
{    
    /*****************************************************
     *  Instance Variables
     *****************************************************/
    public static Scanner userIn = new Scanner(System.in) ;
    private static int fractionsCounter ;//static variable
    
    //-- Neccessary variables
    private boolean duplicable = false ;//========================================== Needs Test
    //-- Neccessary variables
    private Fraction[] fractionList ;
    //-- Neccessary variables
    
    
    
    private int quantity ;
    private int minValue ;
    private int maxValue ;
    
    private int maxGCF ;// Maximum Greatest Common Factor
    //-- Instance variables variables
    private int reducedNumerator ;
    private int reducedDenominator ;
    private int givenNumerator ;
    private int givenDenominator ;
    private int greatestCommonFactor ;
    
    
    public void fractionReset()
    {
        fractionsCounter = quantity = minValue = maxValue =
        maxGCF = greatestCommonFactor = 0 ;
        duplicable = false ; fractionList = null ;
    }    
    /*****************************************************
     *  Single Fraction Constructor
     *****************************************************/    
    public Fraction(int numerator, int denominator,int greatestCommonFactor)
    {        
        this.reducedNumerator = numerator ;
        this.reducedDenominator = ( ((maxValue == 0) && (minValue == 0)) ? denominator 
                         : ((denominator == 0) ? 1 : denominator)) ;
        
        
        this.greatestCommonFactor = ( ((maxValue == 0) && (minValue == 0)) 
                                  ? greatestCommonFactor : ((greatestCommonFactor 
                                 == 0) ? 1 : greatestCommonFactor))  ;
        this.givenNumerator = (this.reducedNumerator * this.greatestCommonFactor) ;
        this.givenDenominator = (this.reducedDenominator * this.greatestCommonFactor) ;
        redefiningReducedFraction() ;
        fractionsCounter++ ;
        
    }
    /*****************************************************
     *  Fraction Generator Constructor
     *****************************************************/
    public Fraction(int min, int max, int qty, int changeMax, boolean duplicable)
    {
        // Initializing Variables
        variablesInitializer(min, max, qty, changeMax, duplicable) ;
        // Generating Fractions
        for (int i = 0 ; i < quantity ; i++)
        {
            reducedNumerator = ((int) (Math.random() * maxValue)   + 1) ;
            reducedDenominator = ((int) (Math.random() * maxValue) + 1) ;
            if ((maxValue == 0) && (minValue == 0))
            {
                reducedNumerator = 0 ; reducedDenominator = 0 ;
            }            
            while (!(isValid(reducedNumerator, reducedDenominator)))
            {
                reducedNumerator   = ((int) (Math.random() * maxValue) + 1) ;
                reducedDenominator = ((int) (Math.random() * maxValue) + 1) ;
            }//System.out.println("---------------------BINGOOOOO--" + reducedNumerator + "/" +reducedDenominator) ;///////////////////////////
            fractionList[i] = new Fraction(reducedNumerator, reducedDenominator, setGCF()) ;            
        }        
    }    
    /*****************************************************
     *  Min and Max Value Checker
     *****************************************************/
    public void MinMaxCheck(int min, int max)
    {
        this.minValue = ((min > max) ? max : min) ;
        this.maxValue = ((min > max) ? min : max) ;        
    }    
    /*****************************************************
     *  Variable Initializer
     *****************************************************/
    private void variablesInitializer
    (int min, int max, int quantity, int maxGCF, boolean duplicable)
    {
        // Initiating Variables
        this.maxGCF = maxGCF ;        
        this.quantity = quantity ;
        this.duplicable = duplicable ;
        fractionList = new Fraction[quantity] ;
        minValue = ((duplicable) && (quantity == 0) ?  min 
                 : ((min == 0) && (quantity !=0)) ? 1 : min) ;
        maxValue = ((duplicable) && (quantity == 0) ? max : (!duplicable 
                && (max < maxValueChanger(quantity))) ? maxValueChanger(quantity) : max) ;
        if (this.duplicable)
        {
            MinMaxCheck(min, max) ;
        }// System.out.println(minValue +" " + maxValue +" "+quantity) ;//==================================For Test purpose
    }
    /*****************************************************
     *  Max Value Changer (max = 1 prevents 0 as a value)
     *****************************************************/
    public int maxValueChanger(int quantiry)
    {
        int test = 0 ;
        int counter = 0 ;
        int max = 1;
        
        while (test < quantiry  )
        {
            for (int num = minValue ; num < (max + 1) ; num++)
            {
                for (int denom = minValue ; denom < (max + 1) ; denom++  )
                {
                    if ((num == 1) && (denom != num))
                    {
                        counter++ ;
                    }
                    if ( (num > 1) && !((num % denom == 0) || (denom % num == 0) ) 
                    &&  !( ((num % 2 == 0)  && (denom % 2 == 0) )   
                          || ( (num % 3 == 0) && (denom % 3 == 0)) ) )
                    {
                        counter++ ;
                    }
                    int temp = counter ;
                    test =  (temp > counter ? temp : counter) ;
                }                
            }
            max++ ; counter = 0 ;
        }        
        return (max - 1) ;
    }
    //**    static method for minimum value checker  */
    public static int maxValueCheck(int min, int quantiry)
    {
        
        int test = 0 ;
        int counter = 0 ;
        int max = 1;
        min = (min < 1 ? 1 : min) ;
        while (test < quantiry  )
        {
            for (int num = min ; num < (max + 1) ; num++)
            {
                for (int denom = min ; denom < (max + 1) ; denom++  )
                {
                    if ((num == 1) && (denom != num))
                    {
                        counter++ ;
                    }
                    if ( (num > 1) && !((num % denom == 0) || (denom % num == 0) ) 
                    &&  !( ((num % 2 == 0)  && (denom % 2 == 0) )   
                          || ( (num % 3 == 0) && (denom % 3 == 0)) ) )
                    {
                        counter++ ;
                    }
                    int temp = counter ;
                    test =  (temp > counter ? temp : counter) ;
                }                
            }
            max++ ; counter = 0 ;
        }        
        return (max - 1) ;
    }
    /*****************************************************
     *  Validating Fraction
     *****************************************************/    
    public boolean isValid(int num, int denom)
    {
        boolean valid = (duplicable ? true : false) ;
        
               
        if ( ((num <= 1) && (denom != num))  ||  ((num > 1) && (denom > 1)) 
        && !((num % denom == 0) || (denom % num == 0)) &&  !(((num % 2 == 0) 
        && (denom % 2 == 0)) || ((num % 3 == 0) && (denom % 3 == 0)))  )
        {//System.out.println("-------------------------------------------1  " + reducedNumerator + "/" +reducedDenominator) ;
            valid = true ;
        }
        if ((num < minValue) || (denom < minValue) 
        ||  (num > maxValue) || (denom > maxValue))
        {//System.out.println("-------------------------------------------2  " + reducedNumerator + "/" +reducedDenominator) ;
            valid = false ;
        }
        if (!duplicable)
        {
            for (int i = 0 ; i < fractionsCounter ; i++)
            {
                if ((fractionList[i].getReducedNumerator() == num)
                &&  (fractionList[i].getReducedDenominator() == denom))
                {//System.out.println("-------------------------------------------3  " + reducedNumerator + "/" +reducedDenominator) ;
                    valid = false ;
                }
            }
        }        
        if ( !((minValue == 0) && (maxValue == 0)) && (duplicable)
        &&   !((maxValue == 1) && (maxValue - minValue == 0)) && (denom != 2) 
        &&    ((num % denom == 0) ||   (denom % num == 0)) || (((num % 2 == 0) 
        &&     (denom % 2 == 0)) ||   ((num % 3 == 0) && (denom % 3 == 0))) 
        &&    !((num <= 1) && (denom != num))  )        
        {//System.out.println("-------------------------------------------4  " + reducedNumerator + "/" +reducedDenominator) ;
            valid = false ;
        }
        if ( (duplicable) && (minValue == 0) && (maxValue == 1)
        ||   (((maxValue == minValue) && (num == denom)) 
        &&   !((num < minValue) || (denom < minValue) 
        ||     (num > maxValue) || (denom > maxValue))) )
        {//System.out.println("-------------------------------------------5  " + reducedNumerator + "/" +reducedDenominator) ;
            valid = true ;
        }
        return valid ;
    }
    /*****************************************************
     *  Generating Greatest Common Factor according to the maxValue
     *****************************************************/    
    private int setGCF()
    {
    	// Generating a Greatest Common Denominator > 0
        greatestCommonFactor = ( (minValue == 0) && ( maxValue ==0) ? 0 
                             : ((int)(Math.random() * maxGCF) + 1) ) ;
        greatestCommonFactor = (((maxGCF > 1) && (greatestCommonFactor == 1)) 
                             ?  (greatestCommonFactor + 1) : greatestCommonFactor) ;
        return greatestCommonFactor ;
    }
    /*****************************************************
     *  Generating Greatest Common Factor according to the maxValue
     *****************************************************/    
    private void redefiningReducedFraction()
    {
        if(reducedDenominator > 1 && reducedDenominator == reducedNumerator)
        {//System.out.println("-------------------------------------------") ;/////////////////////////////////////////////////
            givenNumerator = reducedNumerator ;
            givenDenominator = reducedDenominator ;
            reducedNumerator = 1 ;
            reducedDenominator = 1 ;
            greatestCommonFactor = givenNumerator ;////////////////////////
        }
    }
    /*****************************************************
     *  Fraction List Getter
     *****************************************************/    // ---------------------------
    public Fraction[] getFractionList()
    {
        Fraction[] temp = new Fraction[quantity] ;
        for (int i = 0 ; i < quantity ; i++)
        {
            temp[i] = fractionList[i] ;
        }
        return temp ;
    }
    public Fraction getFractionFromList(int index)
    {
        if (fractionList == null)
        {
            return null ;
        }
        return fractionList[index] ;       
    }
    /*****************************************************
     *  Setters and Getters
     *****************************************************/
    public int getReducedNumerator()
    {
        return reducedNumerator ;
    }
    public int getReducedDenominator()
    {
        return reducedDenominator ;
    }
    public int getGivenNumerator()
    {
        return givenNumerator ;
    }
    public int getGivenDenominator()
    {
        return givenDenominator ;
    }
    public int getGCF()
    {
    	return greatestCommonFactor ;
    }
    public int getMaxValue()
    {
        return maxValue ;
    }
    public int getMinValue()
    {
        return minValue ;
    }
    
    
    
    
    
    
    
    
    
    //-------------------------   toString()  ---------------
    public String toString()
    {
        return "" + reducedNumerator + "/" + reducedDenominator + " * " + greatestCommonFactor
                + "   =  " + givenNumerator + "/" + givenDenominator ;
    }
}