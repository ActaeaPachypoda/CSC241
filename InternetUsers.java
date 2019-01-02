
import java.io.PrintWriter;
import java.io.File;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

    /**Sarah Shaw
     CSC 241-01 (Tuesday/Thursday)
     Dr. Fabrey
     Assignment 3
     12/06/2018
     */
public class InternetUsers {


    public static void main(String[] args) throws Exception{

        String[] line = new String[201];
        String percentString;
        String countryString;
        String [] percent = new String[201];
        String[] countries = new String[201];
        Random random = new Random();

        Scanner stdIn = new Scanner(new File("E:\\School\\2018\\3 Fall\\CSC 241 (Data Structures & Algorithms)\\InternetUsers\\CountrySortedAlpha.txt"));
        PrintWriter out = new PrintWriter("E:\\School\\2018\\3 Fall\\CSC 241 (Data Structures & Algorithms)\\InternetUsers\\CountryUnsortedAlpha.txt");

        while(stdIn.hasNextLine()){
            for(int k = 0; k<line.length;k++){
                line[k] = stdIn.nextLine();
                System.out.println(line[k]);

                percentString = line[k].substring(line[k].length()-5);
                countryString = line[k].substring(0,line[k].length()-5);
                countries[k] = countryString.trim();
                percent[k] = percentString.trim();

            }
            System.out.println("\nCountryUnsortedAlpha.txt saved\n");
            for(int j = 201;j>=1;j--){
                int where = random.nextInt(j);


                for(int i = 0;i<=where;i++){

                    if(!line[i].equals( "null")){
                        if(i == where){

                            out.println(line[i]);
                            line[i] = "null";
                        }
                    }
                    else{
                        where+=1;
                    }
                }
            }
        }
        out.close();
        
        sort(percent,countries);

        System.out.println("========== PUT INTO HASH TABLE ==========");
        
        Hashtable hashCountry = new Hashtable(250);
        Hashtable hashPercent = new Hashtable(250);
        
        int hashSum;
        for(int i = 0;i<201;i++){
            hashSum = 0;
            for(int j = 0;j<countries[i].length();j++){
                String country = countries[i];
                hashSum = hashSum + ((int)country.charAt(j));

            }
            int hashValue = hashSum % 250;
            if(!hashCountry.contains(hashValue)){

                hashCountry.put(countries[i],hashValue);
                hashPercent.put(percent[i],hashValue);

            }
            else{
                while(hashCountry.contains(hashValue)){
                    hashValue++;
                }
                hashCountry.put(countries[i], hashValue);
                hashPercent.put(percent[i],hashValue);

            }

            System.out.println(countries[i] +"   \t"+ percent[i] + "    \thash to:" + hashCountry.get(countries[i]));
        }


        Scanner kb = new Scanner(System.in);
        System.out.println("========== SEARCH HASH TABLE ==========\n");

        System.out.println("Name of country (case sensitive) - type -1 if done");
        String input = kb.next();

        while(!input.equals("-1")){
            if(hashCountry.get(input) != null){
                int index = 0;

                for(int i = 0;i<countries.length;i++){
                    if(countries[i].equals(input)){
                        index = i;
                    }
                }

                System.out.println(hashCountry.get(input) +"   "+ countries[index] +"   "+ percent[index]);
                System.out.println("Name of country (case sensitive) - type -1 if done");
                input = kb.next();
            }
            else{
                System.out.println("Does not exist in hash table!");    //error
                System.out.println("Name of country (case sensitive) - type -1 if done");
                input = kb.next();
            }
        }

    }

    public static void sort(String[] percent,String[] country){

        int j;
        System.out.println("========== SORT BY USER PERCENTAGE ==========");

        for(int gap = percent.length / 2;gap>0;gap/=2){
            for(int i = gap;i < percent.length;i++)
            {
                String countryTemp = country[i];
                int perTemp = Integer.parseInt(percent[i]);


                for(j = i; j >= gap && perTemp > Integer.parseInt(percent[j-gap]); j -= gap){
                    percent[j] = percent[j-gap];
                    country[j] = country[j-gap];
                }
                country[j] = countryTemp;
                percent[j] = Integer.toString(perTemp);

            }
        }
        for(int i = 0;i < percent.length;i++){
            System.out.println(country[i] + "     \t" + percent[i]);
        }

    }
}
