import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.isDigit;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\jpavu\\Desktop\\SPZ.txt");

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String tmpStr = sc.nextLine().trim();

                if(tmpStr.length()==8){

                    if(isUpperCase(tmpStr.charAt(0)) && isUpperCase(tmpStr.charAt(1)) && tmpStr.charAt(2) == ' ' && isDigit(tmpStr.charAt(3))
                            && isDigit(tmpStr.charAt(4)) && isDigit(tmpStr.charAt(5)) && isUpperCase(tmpStr.charAt(6))
                            && isUpperCase(tmpStr.charAt(7)) )


                    {
                        System.out.println(tmpStr);
                    }
                }
            }
            sc.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
