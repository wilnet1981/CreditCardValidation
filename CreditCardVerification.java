//I am importing the class file
import java.io.File;

//I am importing the scanner class
import java.util.Scanner;

/**
 * CreditCardVerification: Verify if the credit card is a valid credit card.
 *
 * @author William Rodrigues Martins
 * @version 3.0
 *
 * Assignment: #4
 * Course: ADEV-1000
 * Section: 6
 * Date Created: 10.13.2016
 * Last Updated: 10.17.2016
 *
 */
public class CreditCardVerification
{
    public static void main(String[] args) throws Exception
    {
        
        //I am creating all variables for my program
        final short MINIMUM_NUMBER_LENGTH_ACCEPTABLE = 2;
        final short MINIMUM_NUMBER_VISA_CARD_ACCEPTED = 13;
        final short MAXIMUM_NUMBER_VISA_CARD_ACCEPTED = 16;
        final short START_NUMBER_VISA_CARD_ACCEPTED = 4;
        final short NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED = 15;
        final short FIRST_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED = 34;
        final short SECOND_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED = 37;
        final short MINIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED = 16;
        final short MAXIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED = 19;
        final short MINIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED = 51;
        final short MAXIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED = 55;
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
        
        //I am creating the object myFile
        File myFile = new File("creditcardnumbers.txt");
        
        //This if will analyze if creditcardnumbers.txt
        if (myFile.exists()==true)
        {
        
            //I am creating the object fileInput to read from the file
            Scanner fileInput = new Scanner(myFile);

            //I am saying to the code that everyhing on the file will be separeted by comma, pipe, or the line separator
            fileInput.useDelimiter(",|" + System.getProperty("line.separator"));
        

        
            //This loop will validate each number on the file creditcardnumbers.txt
            while (fileInput.hasNext())
            {
                   
                   //I am start the counting of requests
                   numberOfAllSubmissions++;
                   
                   //I am saying that the string numbers is equal one correct token on the file
                   numbers = fileInput.next();
                    
                   //This if will check if the number is greater than the acceptable length
                   if (numbers.length()>MINIMUM_NUMBER_LENGTH_ACCEPTABLE)
                   {
                        
                        //If the length is greater than the acceptable length, then the code below will find the first, second, and last number of ach string. It also concatenet the first two number and create an integer for each string
                        firstNumber =numbers.substring(0,1);
                        firstNumberConverted = Integer.parseInt(firstNumber);
                        secondNumber =numbers.substring(1,2);
                        secondNumberConverted = Integer.parseInt(secondNumber);
                        lastNumber =numbers.substring(numbers.length()-1,numbers.length());
                        lastNumberConverted = Integer.parseInt(lastNumber);
                        firstTwoNumbers = firstNumber+secondNumber;
                        firstTwoNumbersConverted = Integer.parseInt(firstTwoNumbers);
                        
                        //This if will verify if the number will be rejected because it does not start with the numbers required for each type of card
                        if(firstNumberConverted==4 || firstTwoNumbersConverted==34 || firstTwoNumbersConverted==37 || firstTwoNumbersConverted>50 && firstTwoNumbersConverted< 56)
                        {
                            
                            //This if will analyze if the number has the minimum requirement to be a credit card valid
                            if(numbers.length()>=MINIMUM_NUMBER_VISA_CARD_ACCEPTED && numbers.length()<=MAXIMUM_NUMBER_VISA_CARD_ACCEPTED && firstNumberConverted==START_NUMBER_VISA_CARD_ACCEPTED)
                            {
                                validCard=true;
                            }
                            else if (numbers.length()==NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED && firstTwoNumbersConverted==FIRST_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED || firstTwoNumbersConverted==SECOND_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED)
                            {
                                validCard=true;
                            }
                            else if(numbers.length()>=MINIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED && numbers.length()<=MAXIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted>=MINIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted<=MAXIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED)
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
                             
                             //This if will make the number that start with the acceptable credit card number be not rejected
                             if(firstNumberConverted==START_NUMBER_VISA_CARD_ACCEPTED || firstTwoNumbersConverted==FIRST_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED || firstTwoNumbersConverted==SECOND_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED || firstTwoNumbersConverted>=MINIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted<=MAXIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED)
                             {
                                 notRejected = true;
                             }
                             validCard=false;
                             numberOfRejecteds++;
                        }
                        
                        //This if will start the Luhn algorithm only in valid cards
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
    
                            //This loop will multiply the odd digits and sum the numbers
                            for(int i=1;i<=invertedNumberString.length();++i)
                            {
                                
                                //This if will verify if it is a odd number and if yes, multiply for 2
                                if(i%2!=0)
                                {
                                    individualNumber = invertedNumberString.substring(i-1,i);
                                    individualNumberConverted = Integer.parseInt(individualNumber);
                                    individualNumberConverted*=2;
                                    
                                    //This if will subtract 9 from numbers greater than 9
                                    if(individualNumberConverted>9)
                                    {
                                        individualNumberConverted-=9;
                                        sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                    }
                                    else
                                    {
                                        sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                     }
                                }
                                else
                                {
                                    individualNumber = invertedNumberString.substring(i-1,i);
                                    individualNumberConverted = Integer.parseInt(individualNumber);
                                    sumOfAllNumbers = sumOfAllNumbers+individualNumberConverted;
                                }
                            }
                            
                            //This if will categorize the valid cards that have the correct last number
                            if(sumOfAllNumbers%10==lastNumberConverted)
                            {
                                    
                                //This if will categorize each card type
                                if (firstNumberConverted==START_NUMBER_VISA_CARD_ACCEPTED)
                                {
                                    cardType = "Visa";
                                    numberOfVisa++;
                                }
                                else if(firstTwoNumbersConverted==FIRST_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED || firstTwoNumbersConverted==SECOND_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED)
                                {
                                    cardType = "American Express";
                                    numberOfAmericanExpress++;
                                }
                                else if(firstTwoNumbersConverted>=MINIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted<=MAXIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED)
                                {
                                    cardType = "Mastercard";
                                    numberOfMastercard++;
                                }
                            }
                            else
                            {
                                    
                                //This if will analyze the cards that has the last digit wrong and categorize in each type of card. It is necessary to count how many type of card was rejected
                                if(numbers.length()>=MINIMUM_NUMBER_VISA_CARD_ACCEPTED && numbers.length()<=MAXIMUM_NUMBER_VISA_CARD_ACCEPTED && firstNumberConverted==START_NUMBER_VISA_CARD_ACCEPTED)
                                {
                                    cardTypeInvalid = "Visa";
                                    cardTypeInvalidBoolean=true;
                                    numberOfVisaInvalid++;
                                }
                                else if(numbers.length()==NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED && firstTwoNumbersConverted==FIRST_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED || firstTwoNumbersConverted==SECOND_START_NUMBER_AMERICAN_EXPRESS_CARD_ACCEPTED)
                                {
                                    cardTypeInvalid = "American Express";
                                    cardTypeInvalidBoolean=true;
                                    numberOfAmericanExpressInvalid++;
                                }
                                else if(numbers.length()>=MINIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED && numbers.length()<=MAXIMUM_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted>=MINIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED && firstTwoNumbersConverted<=MAXIMUM_START_NUMBER_MASTERCARD_CARD_ACCEPTED)
                                {
                                    cardTypeInvalid = "Mastercard";
                                    cardTypeInvalidBoolean=true;
                                    numberOfMastercardInvalid++;
                                }
                            }
                                
                                //I am reseting the inverted number and the sum of all numbers
                                invertedNumber=0;
                                sumOfAllNumbers=0;
                   }
                       
                       //This if will show on the screen if the number of digits of the credit card is incorret
                       if(RejectedSizeCard==true && notRejected==true)
                       {
                            System.out.printf("%s has an invalid number of digits.\n",numbers);
                       }
                       
                       //This if will show on the screen if the number has an invalid check digit
                       if(cardTypeInvalidBoolean==true && validCard==true)
                       {
                            System.out.printf("%s has an invalid check digit.\n",numbers);
                       }
                       
                       //This if will show on the screen if the number is a valid number of credit card
                       if(validCard==true && cardTypeInvalidBoolean==false)
                       {
                            System.out.printf("%s is a valid %s number.\n",numbers,cardType);
                       }
                       
                       //I am reseting the card type. Then, I am saying that the sum of rejecteds is the sum of all rejecteds until now plus the number of rejecteds in this time that the loop worked. Then, I am reseting the number of rejecteds and the string inverted number
                       cardType = "";
                       sumOfRejecteds=sumOfRejecteds+numberOfRejecteds;
                       numberOfRejecteds=0;
                       invertedNumberString="";
                       
            }
            
            //The lines below will report how many cards were processed in total, for each type of card, and how many were rejected
            System.out.println("\n***\n");
            System.out.printf("Records processed: %3d",numberOfAllSubmissions);
            System.out.println("\n======================");
            System.out.printf("Visa: %16d (%d)\n",numberOfVisa,numberOfVisaInvalid);
            System.out.printf("American Express: %4d (%d)\n",numberOfAmericanExpress,numberOfAmericanExpressInvalid);
            System.out.printf("Mastercard: %10d (%d)\n",numberOfMastercard,numberOfMastercardInvalid);
            System.out.printf("Rejected: %12d",sumOfRejecteds);
            
        }
        else
        {
            System.out.println("*** creditcardnumbers.txt does not exist. ***");
        }
    }
}
