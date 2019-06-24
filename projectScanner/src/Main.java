
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter an integer first number from 0-6000");
        String enteredNum = keyboard.next();

        System.out.println("Enter an integer second number from 0-6000");
        String enteredNum2 = keyboard.next();


        while(true){
            if(enteredNum.matches("^[0-9]+(\\.[0-9]{1,2})?$")
                    ||  enteredNum2.matches("^[0-9]+(\\.[0-9]{1,2})?$")
            ){
                int num1 = Integer.parseInt(enteredNum);
                int num2 = Integer.parseInt(enteredNum2);
                System.out.println(num1);
                System.out.println(num2);

                int rslt = num1+num2;
                System.out.println("Result is = "+rslt);
                break;
            }
            else
            {
                //System.out.println("boha");
                System.out.println("Enter an integer first number from 0-6000");
                enteredNum = keyboard.next();

                System.out.println("Enter an integer second number from 0-6000");
                enteredNum2 = keyboard.next();

            }

        }

        /*System.out.println(enteredNum);
        System.out.println(enteredNum2);*/

    }

        }


