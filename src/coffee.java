
import javafx.scene.input.DataFormat;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class coffee {

    static final int CAPACITY = 1000000;
    static final int broze = 30;
    static final int silver = 100;
    static final int gold = 200;
    static final int unCat = 0;
    static final double coffeeCost = 2.50;
    static final int pointPerEuro = 5;
    static String[] id = new String[CAPACITY];
    static String[] name = new String[CAPACITY];
    static String[] email = new String[CAPACITY];
    static String[] amountSpentOndate = new String[CAPACITY];
    static String[] pointBalance = new String[CAPACITY];
    static String[] goldA = new String[CAPACITY];
    static String[] silverA = new String[CAPACITY];
    static String[] brozeA = new String[CAPACITY];
    static Scanner input = new Scanner(System.in);
    static boolean pass = true;
    static int number = 0;
    static File myfile = new File("customers.txt");
    static File goldf = new File("gold.txt");
    static File silverf = new File("silver.txt");
    static File brozef = new File("broze.txt");
    static Locale currentLocale = new Locale("En", "En");
    static Date todayDate = new Date();
    static DateFormat dateFormat  = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
    static DateFormat timeFormat  = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);
    static String timeOut = timeFormat.format(todayDate);
    static String dateout = dateFormat.format(todayDate);
    static ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);


    static boolean isAValidNumber  = true ;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        readfile(); // calling readfiles() so all method can use the arrays
        menu();

    }

    public static void menu () throws IOException{

        int choice = 0;
        while(pass){
            System.out.println("1.English 2.中文 3.日文");
            int options=0;
            System.out.println(messages.getObject("String5"));
            options = input.nextInt();
            changeLocale (options);
            System.out.println("\n\n\t\t===========================================================================");
            System.out.println("\t\t" + messages.getObject("menuMessage1"));
            System.out.println("\t\t" + messages.getObject("menuOption1"));
            System.out.println("\t\t" + messages.getObject("menuOption2"));
            System.out.println("\t\t" + messages.getObject("menuOption3"));
            System.out.println("\t\t" + messages.getObject("menuOption4"));
            System.out.println("\t\t" + messages.getObject("menuOption5"));
            System.out.println("\t\t" + messages.getObject("menuOption6"));
            System.out.println("\t\t===========================================================================");
            System.out.println("\t\t" + messages.getObject("menuMessage2"));

            isAValidNumber=true;
            while(isAValidNumber){ //validation
                checknumber();

                choice = input.nextInt();
                input.nextLine();
                if(choice>0 && choice <8)
                {
                    isAValidNumber = false;
                }
                else
                {
                    System.out.println("Input between 1 - 6");
                }

            }
            switch (choice) {
                case 1:  regCard();
                    break;
                case 2:  displayCust();
                    break;
                case 3:  purchaseCof();
                    break;
                case 4:  reportVal();
                    break;
                case 5:  reportCat();
                    break;
                case 6:  exit ();
                    pass = false;
                    break;

            }
        }
    }

    public static void regCard () throws IOException{

        String fName = "";
        String sName = "";
        String newEmail = "";
        String basicPoint = "20";
        String BasicamountSpent = "0.0";
        String newId = "";
        String fchar = "";
        String schar = "";
        String custnumber = Integer.toString(findNumberOfCust());

        System.out.print("Customer first name:");
        fName = input.nextLine();
        System.out.print("Customer surname:");
        sName = input.nextLine();
        System.out.print("Customer E-Mail:");
        newEmail = input.nextLine();
        schar = Character.toString(sName.charAt(0));
        fchar = Character.toString(fName.charAt(0));
        String addName = "";
        String addName2 = "";
        for(int i =0 ; i <= (fName.length()+2); i++ ){   //making the first name with cap start and adding the space in the middle

            if(i == 0){
                addName = fchar.toUpperCase();
            }
            else if (i == fName.length()+2)
            {
                addName += schar.toUpperCase();
            }
            else if (i < fName.length())
            {
                addName += fName.charAt(i);
            }
            else if(i == fName.length()+1)
            {
                addName += " ";
            }

        }
        for(int t = 1 ;  t < sName.length(); t++) //making the surname with cap start
        {
            addName2 += sName.charAt(t);

        }
        String finalName = addName + addName2;
        newId = fchar + schar +  custnumber;

        int currentArrayPoint = findNumberOfCust() - 1;			//editing the arrays value
        id[currentArrayPoint] = newId.toUpperCase();
        name[currentArrayPoint] = finalName;
        email[currentArrayPoint] = newEmail;
        amountSpentOndate[currentArrayPoint  ] = BasicamountSpent;
        pointBalance[currentArrayPoint  ] = basicPoint;

        System.out.println("Issued Card Details\n------------------------");
        System.out.println("Name:\t\t" + name[currentArrayPoint ]);
        System.out.println("Loyalty Card Number:\t\t" + id[currentArrayPoint ]);

    }

    public static void displayCust () throws FileNotFoundException{

        int point = findCard(); //using the method to know where is the point
        System.out.println("\t\t===========================================");
        System.out.println("\t\tID:\t" +id[point] );
        System.out.println("\t\tName:\t" +name[point]);
        System.out.println("\t\tEmail:\t" +email[point]);
        System.out.println("\t\tPoints:\t" +pointBalance[point] );
        System.out.println("\t\tSpend to Date:\t" +amountSpentOndate[point] );
        System.out.println("\t\tCat:\t" + Cat(point));
        System.out.println("\t\t===========================================");


    }

    public static void purchaseCof () throws FileNotFoundException{

        int amoutOfCoffee = 0;
        double totalCostOfCoffee = 0;
        String loyalt = "";
        int freecoffee = 50;

        System.out.println("How many coffees");
        isAValidNumber = true ;
        while(isAValidNumber){ //validation
            checknumber();
            amoutOfCoffee = input.nextInt();
            input.nextLine();


            if(amoutOfCoffee > 0) // prevent entering below 1
                isAValidNumber = false;
            else
                System.out.println("Please enter greater than 1");

        }


        totalCostOfCoffee = coffeeCost*amoutOfCoffee;
        System.out.println((messages.getObject("String4") ));
        System.out.print( totalCostOfCoffee);
                isAValidNumber = true;
        while(isAValidNumber){ //validation
            System.out.print("Do you have a loyalty card?");
            System.out.println("Yes/No");
            loyalt = input.nextLine();
            int point = 0 ;
            int pointsEarned = 0;
            pointsEarned = (int)totalCostOfCoffee * pointPerEuro;

            if (loyalt.toUpperCase().equals("Y")|| loyalt.toUpperCase().equals("YES"))
            {isAValidNumber = false;
                point = findCard();
                System.out.println( messages.getObject("String1")+ pointBalance[point]);
                input.nextLine();

                isAValidNumber = true;
                while(isAValidNumber){ //validation
                    System.out.print("Do you want to use your available points:  ");
                    String useCard = input.nextLine();
                    int pointInInt = Integer.parseInt(pointBalance[point]);
                    if (useCard.toUpperCase().equals("Y")&& pointInInt>=50|| useCard.toUpperCase().equals("YES")&& pointInInt>=50 ) //
                    {isAValidNumber = false;
                        int amountOfFree = pointInInt/50;
                        long amountOfpay = amoutOfCoffee-amountOfFree;
                        double amountdue = amountOfpay*coffeeCost;
                        int newpoints = 0;
                        double spend = Double.parseDouble(amountSpentOndate[point]); //changing from String to double
                        double newspend = 0;

                        if(amountOfFree>amoutOfCoffee) // scanner for Points more than purchase
                        {	newpoints = pointInInt-(amoutOfCoffee*freecoffee);
                            System.out.println("Date:" + dateout);
                            System.out.println("Time:" + timeOut);
                            System.out.println("You have received " + amoutOfCoffee + " free coffee costing "+ amoutOfCoffee*freecoffee + " points.");
                            System.out.println("Amount due: 0");
                            System.out.println("You have " +  newpoints + " points on your card.");
                            String newpointString = Integer.toString(newpoints);
                            pointBalance[point] = newpointString;
                        }
                        else{	// request more pay after free coffee
                            int newpointsEarned = (int)amountdue * pointPerEuro;
                            newpoints = pointInInt-(amountOfFree*freecoffee)+ newpointsEarned;
                            System.out.println("Date:" + dateout);
                            System.out.println("Time:" + timeOut);
                            System.out.println("You have received " + amountOfFree + " free coffee costing "+ amountOfFree*freecoffee + " points.");
                            System.out.println("Amount due: " + amountdue);
                            System.out.println("You have received " +  newpointsEarned + " points in this transaction. ");
                            System.out.println("You have " +  newpoints + " points on your card.");
                            pointBalance[point] = Integer.toString(newpoints);
                            newspend = spend + amountdue ;
                            String newspendS = Double.toString(newspend);
                            amountSpentOndate[point] = newspendS;

                        }
                    }
                    else if (useCard.toUpperCase().equals("N")|| useCard.toUpperCase().equals("NO"))
                    {isAValidNumber = false;
                        System.out.println("Date:" + dateout);
                        System.out.println("Time:" + timeOut);
                        System.out.println("Amount Due: " + totalCostOfCoffee);
                        System.out.println("You have received " +pointsEarned + " points.");
                        int newpoints = Integer.parseInt(pointBalance[point]) + pointsEarned;
                        String newpointstring = Integer.toString(newpoints);
                        pointBalance[point]= newpointstring;
                        double currentspendI = Double.parseDouble(amountSpentOndate[point])+ totalCostOfCoffee;
                        amountSpentOndate[point]= Double.toString(currentspendI);

                    }
                    else if (useCard.toUpperCase().equals("Y")&& pointInInt<=50|| useCard.toUpperCase().equals("YES")&& pointInInt<=50)
                    {isAValidNumber = false;
                        System.out.println("Date:" + dateout);
                        System.out.println("Time:" + timeOut);
                        System.out.println("!!!!!!!!!!!!!!!not enough points!!!!!!!!!!!!!");
                        System.out.println("Amount Due: " + totalCostOfCoffee);
                        System.out.println("You could have recieved " + pointsEarned);
                        System.out.println("Your total is : " + pointBalance[point]);
                        pointBalance[point]= Integer.toString(Integer.parseInt(pointBalance[point]) + pointsEarned);
                        amountSpentOndate[point]= Double.toString(Double.parseDouble(amountSpentOndate[point])+ totalCostOfCoffee);

                    }
                    else
                    {
                        isAValidNumber = true;
                        System.out.println("Enter Yes / No");
                    }
                }
            }
            else if (loyalt.toUpperCase().equals("N")|| loyalt.toUpperCase().equals("NO"))
            {isAValidNumber = false;


                System.out.println("Date:" + dateout);
                System.out.println("Time:" + timeOut);
                System.out.println("Amount Due: " + totalCostOfCoffee);
                System.out.println("You could have recieved " + pointsEarned);
                isAValidNumber = false;
            }
            else
            {
                isAValidNumber = true;
                System.out.println("Enter Yes / No");
            }
        }
    }

    public static void reportVal () throws FileNotFoundException{
        System.out.println("Please Enter a value: " );
        checknumber();
        int value = input.nextInt();

        System.out.println("Card Number\t\tCustomer Name\t\t\tCustomer Email");
        System.out.println("=========================================================================================");
        for(int i =0 ; i< findNumberOfCust()-1 ; i++ )
        {
            if(Integer.parseInt(pointBalance[i]) >= value) //anything greater than value enter will show
            {
                System.out.println(id[i] + "\t\t\t" + name[i] + "\t\t\t" + email[i] );
            }
        }
    }

    public static void reportCat () throws FileNotFoundException{
        int bcount = 0;
        int scount = 0;
        int gcount = 0;
        for(int i= 0 ; i < findNumberOfCust()-1 ; i++ ) // cating the customers into cats
        {
            if(Double.parseDouble(amountSpentOndate[i]) < broze)
            {
            }
            else if(Double.parseDouble(amountSpentOndate[i]) < silver)
            {
                brozeA[bcount] = name[i] + " " + email[i];
                bcount++;
            }
            else if(Double.parseDouble(amountSpentOndate[i]) < gold)
            {
                silverA[scount] = name[i] + " " + email[i];
                scount++;
            }
            else if(Double.parseDouble(amountSpentOndate[i]) > gold)
            {
                goldA[gcount] = name[i] + " " + email[i];
                gcount++;
            }
        }
    }
    public static void exit () throws FileNotFoundException{
        PrintWriter outputcustomer = new PrintWriter("customers.txt"); //open all the files for overwrite
        PrintWriter outputgold = new PrintWriter("gold.txt");
        PrintWriter outputsilver = new PrintWriter("silver.txt");
        PrintWriter outputbroze = new PrintWriter("broze.txt");

        for(int i =0 ; i <findNumberOfCust()-1 ; i++) //overwrite the new edit arrays to the old text
        {
            outputcustomer.println(id[i]);
            outputcustomer.println(name[i]);
            outputcustomer.println(email[i]);
            outputcustomer.println(amountSpentOndate[i]);
            outputcustomer.println(pointBalance[i]);
        }
        outputcustomer.close(); //saving the file
        reportCat (); // making sure the customers is cated
        boolean flip = true;
        int t = 0;
        while(flip) //printing the name and email to the cat's texts
        {
            if(goldA[t] == null)
            {
                flip = false;
            }
            else{
                outputgold.println(goldA[t]);
            }
            t++;
        }
        flip = true;
        t = 0;
        while(flip)
        {
            if(silverA[t] == null)
            {
                flip = false;
            }
            else{
                outputsilver.println(silverA[t]);
            }
            t++;
        }
        flip = true;
        t = 0;
        while(flip)
        {
            if(brozeA[t] == null)
            {
                flip = false;
            }
            else{
                outputbroze.println(brozeA[t]);
            }
            t++;
        }
        outputbroze.close(); //saving the file
        outputsilver.close();
        outputgold.close();
    }

    public static void readfile () throws FileNotFoundException{



        Scanner infile = new Scanner(myfile); //opening the customer file
        if (!myfile.exists()) {
            System.out.println("Error - could not find file.");
            System.exit(0);
        }
        Scanner fileScanner = new Scanner(myfile);
        Scanner goldScanner = new Scanner(goldf);
        Scanner silverScanner = new Scanner(silverf);
        Scanner brozeScanner = new Scanner(brozef);
        int CAPACITY = 10;
        int index=0;

        while(fileScanner.hasNext()){ // scanning the text file to the arrays

            id[index] = fileScanner.nextLine();
            name[index] = fileScanner.nextLine();
            email[index] = fileScanner.nextLine();
            amountSpentOndate[index] = fileScanner.nextLine();
            pointBalance[index] = fileScanner.nextLine();
			/*
			System.out.println(id[index] + "\t\t1");
			System.out.println(name[index] + "\t\t2");
			System.out.println(email[index] + "\t\t3");
			System.out.println(amountSpentOndate[index] + "\t\t4");
			System.out.println(pointBalance[index] + "\t\t5");
			*/
            index++;
        }
        int goldcount = 0;
        while(goldScanner.hasNext()){ // scanning the text file to the arrays
            goldA[goldcount] = goldScanner.nextLine();
            goldcount++;
        }
        int silvercount = 0;
        while(silverScanner.hasNext()){ // scanning the text file to the arrays

            silverA[silvercount] = silverScanner.nextLine();
            silvercount++;
        }
        int brozecount = 0;
        while(brozeScanner.hasNext()){ // scanning the text file to the arrays
            brozeA[brozecount] = brozeScanner.nextLine();
            brozecount++;
        }


    }
    public static void displayAccounts () throws FileNotFoundException{ // for testing use to show all the arrays detail

        int custnumber = findNumberOfCust();

        for (int i = 0 ; i <= custnumber  ; i ++) {
            System.out.println("\t\t===========================================");
            System.out.println("\t\t" + id[i] + "\t\t1");
            System.out.println("\t\t" + name[i] + "\t\t2");
            System.out.println("\t\t" + email[i] + "\t\t3");
            System.out.println("\t\t" + amountSpentOndate[i] + "\t\t4");
            System.out.println("\t\t" + pointBalance[i] + "\t\t5");
            System.out.println("\t\t===========================================");

        }

    }
    public static int findNumberOfCust() throws FileNotFoundException{ // to check how many customer is there in the array

        boolean click = true;
        int count =0;
        while(click)
        {
            if (id[count] == null)
            {
                number = count+1;
                click = false;
            }
            count++;
        }
        return number;
    }
    public static int findCard() throws FileNotFoundException{ //finding the pointAt in the array for an ID


        int point = -1;
        findNumberOfCust();
        String searchId = "";
        boolean click = true;
        while(click){ //scanning the ID name from the arrays
            System.out.print("Please enter ID Number:");
            searchId = input.next();
            String compare = searchId.toUpperCase();
            int i =0;
            while(i < findNumberOfCust()-1)
            {
                if(id[i].equals(compare))
                {
                    point = i;
                    click = false;
                    System.out.println("Customer Found");
                }
                i++;
            }
            if(point == -1){
                System.out.println("Account Not Found ");
            }
        }
        return point;

    }
    public static String Cat (int point){
        // cating the customer for display which do not edit the arrays at all
        String Cat ="";
        String find = name[point] + " " + email[point];
        if(Double.parseDouble(amountSpentOndate[point]) <broze)
        {

        }
        else if(Double.parseDouble(amountSpentOndate[point])<silver)
        {
            Cat = "Broze";
        }
        else if(Double.parseDouble(amountSpentOndate[point])<gold)
        {
            Cat = "Silver";
        }
        else if(Double.parseDouble(amountSpentOndate[point])>=gold)
        {
            Cat = "Gold";
        }

        if(Double.parseDouble(amountSpentOndate[point])< 30){
            Cat = "Uncat";
        }
        return Cat;
    }
    public static void checknumber() // this is for locking the input for numbers
    {
        while (! input.hasNextFloat() ) {
            input.nextLine();
            System.out.print("Numbers only please ");
        }
    }
    public static void changeLocale(int a)
    {


        if(a == 1)
        {
            currentLocale = new Locale("En","En");
            dateFormat  = DateFormat.getDateInstance(DateFormat.FULL, currentLocale.ENGLISH);
            timeFormat  = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale.ENGLISH);
        }
        else if(a == 2)
        {
            currentLocale = new Locale("Cn","Cn");
            dateFormat  = DateFormat.getDateInstance(DateFormat.FULL, currentLocale.TRADITIONAL_CHINESE);
            timeFormat  = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale.TRADITIONAL_CHINESE);
        }
        else if(a == 3)
        {
            currentLocale = new Locale("Jp","Jp");
            dateFormat  = DateFormat.getDateInstance(DateFormat.FULL, currentLocale.JAPAN);
            timeFormat  = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale.JAPAN);
        }
        dateout = dateFormat.format(todayDate);
        timeOut = timeFormat.format(todayDate);
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);


}






}
