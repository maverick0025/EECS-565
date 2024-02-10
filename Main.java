import java.io.*;
import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    private static final String task_1_hash = "94d9e03c11395841301a7aee967864ec";
    private static final String task_2_hash = "f593def02f37f3a6d57bcbc9480a3316";
    private static final String task_3_hash = "bfb2c12706757b8324368de6a365338b";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        ZonedDateTime start = ZonedDateTime.now();

        //comment one of the two lines below to run the other
        tryTask2(start);
        //tryTask3(start);
    }

    private static String hashGen(String message) throws NoSuchAlgorithmException{

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes());
        byte[] messageDigest = md.digest();
        BigInteger no = new BigInteger(1, messageDigest);

        StringBuilder hashtext = new StringBuilder(no.toString(16));
        while(hashtext.length() < 32){
            hashtext.insert(0, "0");
        }
        return hashtext.toString();
    }

    private static String checkPassword_for_tasks(String guess, int task) throws NoSuchAlgorithmException {

        String guessedHash = hashGen(guess);

        switch (task){
            case 1:
                if(guessedHash.equals(task_1_hash)){
                    return "You succeed and the password is: " + guess;
                }
                break;
            case 2:
                if(guessedHash.equals(task_2_hash)){
                    return "You succeed and the password is: "+ guess;
                }
                break;
            case 3:
                if(guessedHash.equals(task_3_hash)){
                    return "You succeed and the password is: "+ guess;
                }
                break;
        }

        return "Incorrect guess";
    }

    private static void tryTask2(ZonedDateTime start) throws IOException, NoSuchAlgorithmException{

        BufferedReader br = new BufferedReader(new FileReader("/Users/ashok/Library/CloudStorage/OneDrive-UniversityofKansas/eecs565/task2_upper7.txt"));
        String line = null;
        long i = 0;
        while((line = br.readLine()) != null){

            BufferedReader brl = new BufferedReader(new FileReader("/Users/ashok/Library/CloudStorage/OneDrive-UniversityofKansas/eecs565/task2_lower4.txt"));
            String linel = null;

            while((linel = brl.readLine()) != null){

                StringBuilder sb = new StringBuilder();
                sb.append(line).append(linel);
                System.out.println( " " + i++ + " check password: "+ sb.toString());
                String res = checkPassword_for_tasks(sb.toString(), 2);
            }
            brl.close();
        }
        br.close();
    }

    private static void tryTask3(ZonedDateTime start) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("/Users/ashok/Library/CloudStorage/OneDrive-UniversityofKansas/eecs565/task3_upper7.txt"));
        String numb = "1234";
        String line = null;
        int i =0;

        while((line = br.readLine()) != null){

            while(i < 8) {
                StringBuilder sb = new StringBuilder();
                sb.append(line, 0, i).append(numb).append(line.substring(i));

                System.out.println("check password is: "+ sb.toString());
                String res = checkPassword_for_tasks(sb.toString(), 3);

                if(!res.equals("Incorrect guess")){

                    System.out.println(res);
                    long seconds = start.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
                    System.out.println("Total time taken to crack is: " + seconds);
                    br.close();
                    break;
                }
                i++;
            }
            i = 0;
        }
        br.close();
    }
}