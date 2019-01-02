/*
    Sarah Shaw
    CSC 241-01 (Tuesday/Thursday)
    Dr. Fabrey
    Assignment 2
    10/29/2018
 */

import java.util.Arrays;
import java.util.Scanner;

public class Cashiers{

    private final int COUNTERS = 3;
    private final int MAX_QUEUE_SIZE = 6;

    private int[][] queues;

    private int[] front;
    private int[] back;

    private int[] currentSize;

    private int customer;

    Cashiers(){
        queues = new int[COUNTERS][MAX_QUEUE_SIZE];

        front = new int[COUNTERS];
        back = new int[COUNTERS];

        currentSize = new int[COUNTERS];
        customer = 1;
    }

    public void clear(){
        for( int i=0; i < COUNTERS; i++){
            Arrays.fill(queues[i],0);
            front[i] = 0;
            back[i] = 0;
            currentSize[i] = 0;
        }
    }

    public void fill(){

        int totalCustomers = 0;
        for( int i=0; i<COUNTERS; i++)
            totalCustomers += currentSize[i];

        int []presentCustomers = new int[totalCustomers];

        int k=0;
        for( int i=0; i < COUNTERS; i++){
            for( int j=0; j < MAX_QUEUE_SIZE; j++){
                if( queues[i][j] != 0 )
                    presentCustomers[k++] = queues[i][j];
            }
        }

        Arrays.sort(presentCustomers);

        this.clear();

        for( int j=0; j < totalCustomers; j++){
            int smallestQueue = 0;
            for( int i=0; i < COUNTERS; i++){
                if( currentSize[i] < currentSize[smallestQueue] ){
                    smallestQueue = i;
                }
            }

            queues[smallestQueue][back[smallestQueue]] = presentCustomers[j];
            currentSize[smallestQueue]++;
            back[smallestQueue] = (back[smallestQueue] + 1) % MAX_QUEUE_SIZE;

        }

    }

    public void display(){
        for( int i=0; i < COUNTERS; i++ ){
            for(int j=0; j < MAX_QUEUE_SIZE; j++){
                System.out.print(queues[i][j]);
                if( currentSize[i] > 0){
                    if( front[i] == j )
                        System.out.print("F");
                    if( back[i] == (j + 1) % MAX_QUEUE_SIZE )
                        System.out.print("B");
                }

                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int dequeue(int queueNum){

        if( currentSize[queueNum] == 0) {
            System.out.println("Queue is Empty. Cannot dequeue.");
            return 0;
        }

        int custNum = queues[queueNum][front[queueNum]];
        queues[queueNum][front[queueNum]] = 0;

        front[queueNum] = (front[queueNum]+1) % MAX_QUEUE_SIZE;
        currentSize[queueNum]--;

        if( currentSize[queueNum] == 0 ){
            front[queueNum] = 0;
            back[queueNum] = 0;
        }

        return custNum;
    }

    public boolean enqueue(){
        int smallestQueue = 0;
        for( int i=0; i < COUNTERS; i++){
            if( currentSize[i] < currentSize[smallestQueue] ){
                smallestQueue = i;
            }
        }

        if( currentSize[smallestQueue] >= MAX_QUEUE_SIZE ){
            System.out.println("Queues Full. Cannot enqueue any more customers");
            return false;
        }

        queues[smallestQueue][back[smallestQueue]] = customer++;
        currentSize[smallestQueue]++;
        back[smallestQueue] = (back[smallestQueue] + 1) % MAX_QUEUE_SIZE;

        return true;

    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Cashiers cashiers = new Cashiers();

        boolean promptUser = true;
        char choice;
        String input;

        while (promptUser){
            System.out.println("Input Command E or 1 or 2 or 3 or F or C or Q");
            input = sc.next();
            choice = input.length() > 1 ? '~' : input.toLowerCase().charAt(0);

            switch (choice){
                case 'e' : cashiers.enqueue();
                    cashiers.display();
                    break;
                case '1' : cashiers.dequeue(0);
                    cashiers.display();
                    break;
                case '2' : cashiers.dequeue(1);
                    cashiers.display();
                    break;
                case '3' : cashiers.dequeue(2);
                    cashiers.display();
                    break;
                case 'f' : cashiers.fill();
                    cashiers.display();
                    break;
                case 'c' : cashiers.clear();
                    cashiers.display();
                    break;
                case 'q' : promptUser = false;
                    cashiers.display();
                    break;
                default:
                    System.out.println("Invalid Input!");
                    cashiers.display();
            }
        }


    }
}