import java.util.*;
import java.io.*; 

public class Main
{
  static void print (String a)
  {
    System.out.print (a);
  }

  static int convertMonthToInt (String month)
  {
    switch (month.toLowerCase ())
      {
      case "january":
	    return 1;
      case "february":
	    return 2;
      case "march":
	    return 3;
      case "april":
	    return 4;
      case "may":
	    return 5;
      case "june":
	    return 6;
      case "july":
	    return 7;
      case "august":
	    return 8;
      case "september":
	    return 9;
      case "october":
	    return 10;
      case "november":
	    return 11;
      case "december":
	    return 12;
      default:
	    return 0;
      }
  }
	
  static int[] calculateAge (int birthDay, int birthMonth, int birthYear, int currentDay, int currentMonth, int currentYear)
  {
    int[] monthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (birthDay > currentDay)
    {
	currentMonth--;
	currentDay += monthLength[birthMonth - 1];
        if (birthYear % 4 == 0 && birthMonth == 2)
    	{
    	   currentDay++;
    	}
    }

    if (birthMonth > currentMonth)
    {
	currentYear--;
	currentMonth += 12;
    }

    int dayDiff = currentDay - birthDay;
    int monthDiff = currentMonth - birthMonth;
    int yearDiff = currentYear - birthYear;
    
    int[] ageDiff = {yearDiff, monthDiff, dayDiff};
    
    return ageDiff;
  }
  
  public static void main (String[]args)
  {
    Scanner keyboard = new Scanner (System.in);
    
    int currentDay = 20;
    int currentMonth = 6;
    int currentYear = 2020;

    int yourDay, yourMonthInt = 0, yourYear, theirDay, theirMonthInt = 0, theirYear;
    String yourMonth, theirName, theirMonth;

    while (yourMonthInt == 0)
    {
	print ("What month were you born in? ");
	yourMonth = keyboard.next ();
	yourMonthInt = convertMonthToInt (yourMonth);
    }

    print ("What day were you born on? ");
    yourDay = keyboard.nextInt ();

    print ("What year were you born in? ");
    yourYear = keyboard.nextInt ();

    print ("What is the name of the person with whom you'd like to compare ages? ");
    theirName = keyboard.next ();

    while (theirMonthInt == 0)
    {
    	print ("What month was " + theirName + " born in? ");
    	theirMonth = keyboard.next ();
    	theirMonthInt = convertMonthToInt (theirMonth);
    }

    print ("What day was " + theirName + " born on? ");
    theirDay = keyboard.nextInt ();

    print ("What year was " + theirName + " born in? ");
    theirYear = keyboard.nextInt ();
    
    int[] yourAge = calculateAge(yourDay, yourMonthInt, yourYear, currentDay, currentMonth, currentYear);
    int[] theirAge = calculateAge(theirDay, theirMonthInt, theirYear, currentDay, currentMonth, currentYear);
    
    boolean older = false;
    boolean exactSameAge = false;
    
    if (yourAge[2] != theirAge[2]) 
    {
        older = (yourAge[2] > theirAge[2]);
    }
    else if (yourAge[1] != theirAge[1]) 
    {
        older = (yourAge[1] > theirAge[1]);
    }
    else if (yourAge[0] != theirAge[0]) 
    {
        older = (yourAge[0] > theirAge[0]);
    }
    else 
    {
        exactSameAge = true;
    }
    
    int[] ageDiff = new int[3];
    
    if (older) 
    {
        ageDiff = calculateAge(yourDay, yourMonthInt, yourYear, theirDay, theirMonthInt, theirYear);
    }
    else 
    {
        ageDiff = calculateAge(theirDay, theirMonthInt, theirYear, yourDay, yourMonthInt, yourYear);
    }
    
    if (exactSameAge) 
    {
        print("You and " + theirName + " are the exact same age!");
    }
    else 
    {
        boolean useCommas = ageDiff[0] > 0 && ageDiff[1] > 0 && ageDiff[2] > 0;

        print("You are " + (older ? "older" : "younger") + " than " + theirName + " by ");
        
        if (ageDiff[0] > 0) 
	{
            print(ageDiff[0] + " year" + ((ageDiff[0] > 1) ? "s" : "") + (useCommas ? ", " : " "));
        }
        if (ageDiff[1] > 0) 
	{
            print(((ageDiff[0] > 0 && ageDiff[2] == 0) ? "and " : "") + ageDiff[1] + 
		  " month" + ((ageDiff[1] > 1) ? "s" : "") + (useCommas ? ", " : " "));
        }
        if (ageDiff[2] > 0) 
	{
            print(((ageDiff[0] > 0 || ageDiff[1] > 0) ? "and " : "") + ageDiff[2] + " day" + ((ageDiff[2] > 1) ? "s" : ""));
        }
        
        print(".");
    }
  }
}
