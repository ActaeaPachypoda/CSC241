/*
    Sarah Shaw
    CSC 241-01 (Tuesday/Thursday)
    Dr. Fabrey
    Assignment 1
    9/28/2018
 */
import java.util.*;

public class longNumbersLinkedList
{
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        LinkedList mainList = new LinkedList();
        LinkedList sumList = new LinkedList();
        LinkedList temp = null;

        //declare other variables
        int maxWidth = 0;
        int sum;
        int last = 0;

        //repeatedly input longNumbers, using -1 to indicate that you are done
        while (true) {
            System.out.println("Repeatedly input integers, use -1 to indicate that you're done");
            String number = stdIn.nextLine();
            if (number.equals("-1")) {
                break;
            }

            //add a new LinkedList at the beginning of the mainList
            mainList.addFirst(new LinkedList());

            //use get(0) to set temp to be this new LinkedList
            temp = (LinkedList) mainList.get(0);

            /*for each character in your longNumber, subtract 48 to get the digit and then add it
            at the beginning of temp */
            for (int i = 0; i < number.length(); i++)
            {
                temp.addFirst(number.charAt(i) - 48);
            }

            //keep track of maxWidth, the number of digits of the widest longNumber input so far
            maxWidth = Math.max(maxWidth, number.length());

        }

        for (int i = 0; i < maxWidth; i++)
        {
            sum = 0;
            for (int j = 0; j < mainList.size(); j++) {
                temp = (LinkedList) mainList.get(j);
                if (!temp.isEmpty()) {
                    sum += (int) temp.get(0);
                    temp.remove(0);
                }
            }
            sum += last;
            sumList.addFirst(sum % 10);
            last = sum / 10;
        }

        System.out.print("Result: ");
        if (last != 0)
        {
            System.out.print(last);
        }
        while (!sumList.isEmpty())
        {
            int num = (int) sumList.pop();
            System.out.print((char) (num + 48));
        }
        System.out.println();
    }
}