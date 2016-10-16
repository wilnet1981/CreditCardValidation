//I am importing the class file
import java.io.File;

//I am importing the scanner class
import java.util.Scanner;

/**
 * CreditCardVerification: Verify if the credit card is a valid credit card.
 *
 * @author William Rodrigues Martins
 * @version 2.0
 *
 * Assignment: #4
 * Course: ADEV-1000
 * Section: 6
 * Date Created: 10.13.2016
 * Last Updated: 10.15.2016
 *
 */
public class CreditCardVerification
{
    public static void main(String[] args) throws Exception
    {
        String numbers = "";
        int numberOfRejecteds = 0;
        int numberOfMastercard = 0;
        int numberOfVisa = 0;
        int numberOfAmericanExpress = 0;
        int numberOfMastercardInvalid = 0;
        int numberOfVisaInvalid = 0;
        int numberOfAmericanExpressInvalid = 0;
        String cardType = "";
        String fileInputString = "";
        String firstNumber = "";
        int firstNumberConverted = 0;
        String secondNumber = "";
        int secondNumberConverted = 0;
        String firstTwoNumbers = "";
        int firstTwoNumbersConverted = 0;
        String lastNumber = "";
        int lastNumberConverted = 0;
        boolean validCard=false;
        long numberWithoutLastNumber = 0;
        long numberConverted = 0;
        long invertedNumber = 0;
        String invertedNumberString = "";
        String individualNumber = "";
        int individualNumberConverted = 0;
        int sumOfAllNumbers = 0;
        String cardTypeInvalid = "";
        boolean cardTypeInvalidBoolean=false;
        boolean RejectedSizeCard=false;
        boolean notRejected = false;
        int sumOfRejecteds = 0;
        int numberOfAllSubmissions = 0;
        
        
        File myFile = new File("creditcardnumbers.txt");
        Scanner fileInput = new Scanner(myFile);
        fileInput.useDelimiter(",|" + System.getProperty("line.separator"));
        
        while (fileInput.hasNext())
        {
                numberOfAllSubmissions++;
                numbers = fileInput.next();
                
                
                
                if (numbers.length()>2)
                {
                    firstNumber =numbers.substring(0,1);
                    firstNumberConverted = Integer.parseInt(firstNumber);
                    secondNumber =numbers.substring(1,2);
                    secondNumberConverted = Integer.parseInt(secondNumber);
                    lastNumber =numbers.substring(numbers.length()-1,numbers.length());
                    lastNumberConverted = Integer.parseInt(lastNumber);
                    firstTwoNumbers = firstNumber+secondNumber;
                    firstTwoNumbersConverted = Integer.parseInt(firstTwoNumbers);
                    if(firstNumberConverted==4 || firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37 || firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                    {
                        if(numbers.length()>12 && numbers.length()<17 && firstNumberConverted==4)
                        {
                            
                            validCard=true;
                        }
                        else if (numbers.length()==15 && firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37)
                        {
                            
                            validCard=true;
                        }
                        else if(numbers.length()>15 && numbers.length()<20 && firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                        {
                            
                            validCard=true;
                        }
                        else
                        {
                            RejectedSizeCard = true;
                            validCard=false;
                            notRejected = true;
                            numberOfRejecteds++;
                        }
                    }
                    else
                    {
                        numberOfRejecteds++;
                        validCard=false;
                        notRejected = false;
                    }
                     }
                     else
                     {
                         if(firstNumberConverted==4 || firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37 || firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                         {
                             notRejected = true;
                         }
                         validCard=false;
                         numberOfRejecteds++;
                     }
                     //System.out.println("Rejecteds: " + numberOfRejecteds);
                    if(validCard==true)
                    {
                        
                        //drop the last number
                        numberConverted = Long.parseLong(numbers);
                        numberWithoutLastNumber=numberConverted / 10;
                        
                        //invert numbers
                        while (numberWithoutLastNumber > 0)
                        {
                            invertedNumber = invertedNumber *10;
                            invertedNumber = invertedNumber + (numberWithoutLastNumber % 10);
                            numberWithoutLastNumber = numberWithoutLastNumber / 10;
                            
    
                        }
                        
                        //make the numberWithoutLastNumber with the original value
                        numberWithoutLastNumber=numberConverted / 10;
                        
                        //Make the inverted number as a string
                        invertedNumberString = Long.toString(invertedNumber);

                        for(int i=1;i<=invertedNumberString.length();++i)
                        {
                            
                            if(i%2!=0)
                            {
                                //System.out.println("1: "+i);
                                individualNumber = invertedNumberString.substring(i-1,i);
                                individualNumberConverted = Integer.parseInt(individualNumber);
                                individualNumberConverted*=2;
                                //System.out.println(individualNumberConverted);
                                if(individualNumberConverted>9)
                                {
                                    individualNumberConverted-=9;
                                    sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                    //System.out.println("individualNumberConverted1: "+numbers+"    "+individualNumberConverted);
                                }
                                else
                                {
                                    sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                    //System.out.println("individualNumberConverted2: "+numbers+"    "+individualNumberConverted);
                                }
                            }
                            else
                            {
                                individualNumber = invertedNumberString.substring(i-1,i);
                                individualNumberConverted = Integer.parseInt(individualNumber);
                                sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                //System.out.println("individual number: "+individualNumber);
                            }
                        }
                        //System.out.println("sum: "+sumOfAllNumbers);
                            if(sumOfAllNumbers%10==lastNumberConverted)
                            {
                                if (firstNumberConverted==4)
                                {
                                    cardType = "Visa";
                                    numberOfVisa++;
                                }
                                else if(firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37)
                                {
                                    cardType = "American Express";
                                    numberOfAmericanExpress++;
                                }
                                else if(firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                                {
                                    cardType = "Mastercard";
                                    numberOfMastercard++;
                                }

                            }

                            else
                            {
                               if(firstNumberConverted==4 && numbers.length()>12 && numbers.length()<17)
                               {
                                    cardTypeInvalid = "Visa";
                                    cardTypeInvalidBoolean=true;
                                    numberOfVisaInvalid++;
                               }
                               else if(firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37 && firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37)
                               {
                                    cardTypeInvalid = "American Express";
                                    cardTypeInvalidBoolean=true;
                                    numberOfAmericanExpressInvalid++;
                               }
                               else if(firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56 && firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                               {
                                    cardTypeInvalid = "Mastercard";
                                    cardTypeInvalidBoolean=true;
                                    numberOfMastercardInvalid++;
                               }

                            }


                        invertedNumber=0;
                        sumOfAllNumbers=0;
                        
                        
                        

               }
                            if(RejectedSizeCard==true && notRejected==true)
                            {
                                System.out.printf("%s has an invalid number of digits.\n",numbers);
                            }
                            if(validCard==true && cardTypeInvalidBoolean==false)
                            {
                                System.out.printf("%s is a valid %s number.\n",numbers,cardType);
                            }

                            
                            if(cardTypeInvalidBoolean==true && validCard==true)
                            {
                                System.out.printf("%s has an invalid check digit.\n",numbers);
                            }
               cardType = "";
               //System.out.println("invertedNumberString: "+invertedNumberString);
               //System.out.println("numberWithoutLastNumber: "+numbers);
               sumOfRejecteds=sumOfRejecteds+numberOfRejecteds;
               numberOfRejecteds=0;

               invertedNumberString="";
        }
            System.out.println("\n***\n");
            System.out.printf("Records processed: %3d",numberOfAllSubmissions);
            System.out.println("\n============================");
            System.out.printf("Visa: %15d (%d)\n",numberOfVisa,numberOfVisaInvalid);
            System.out.printf("American Express: %3d (%d)\n",numberOfAmericanExpress,numberOfAmericanExpressInvalid);
            System.out.printf("Mastercard: %9d (%d)\n",numberOfMastercard,numberOfMastercardInvalid);
            System.out.printf("Rejected: %12d",sumOfRejecteds);
    }
}
