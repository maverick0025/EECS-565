import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRulesException;

public class Task2 {
    private static int count = 0;
    static String flag = "";
    private static final String task_2_hash = "f593def02f37f3a6d57bcbc9480a3316";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        ZonedDateTime start = ZonedDateTime.now();

        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        generate(arr,4, "", arr.length, start);
        System.out.println("no. of iterations = "+ count);

    }

    private static void generate(char[] arr, int i, String s, int strlen, ZonedDateTime start) throws NoSuchAlgorithmException {

        if( i ==0){
            char[]  arr2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            generateSecond(arr2, 3, "", arr2.length, start, s);

            return;
        }

        for(int j =0; j< strlen; j++){
            if(flag.equals("break")){
                break;
            }
            String st = s + arr[j];
            generate(arr, i-1, st, strlen, start);
        }
    }

    private static void generateSecond(char[] arr2, int i, String s2, int strlen, ZonedDateTime start, String prefixPrefix) throws NoSuchAlgorithmException {

        if( i == 0){
            char[] arr3 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            generateThird(arr3, 4, "", arr3.length, start, prefixPrefix+s2);
            return;
        }

        for(int j = 0; j<strlen; j++){
            if(flag.equals("break")){
                break;
            }
            String st = s2 + arr2[j];
            generateSecond(arr2, i-1, st, strlen, start, prefixPrefix);
        }

    }

    private static void generateThird(char[] arr3, int i, String s3, int strlen, ZonedDateTime start, String prefix) throws NoSuchAlgorithmException {

        if(i == 0){
            String password = prefix + s3;
            System.out.println("check password is: "+ password);
            String res = checkPassword_for_task_2(password);
            if(!res.equals("Incorrect guess")) {
                System.out.println(res);
                long seconds = start.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
                System.out.println("Total time taken to crack is: " + seconds + " seconds");
                flag = "break";
            }
            return;
        }

        for(int j = 0; j < strlen; j++){
            if(flag.equals("break")){
                break;
            }
            String st = s3 + arr3[j];
            count++;
            generateThird(arr3, i-1, st, strlen, start, prefix);
        }
    }

    private static String hashGen(String message) throws NoSuchAlgorithmException {

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

    private static String checkPassword_for_task_2(String guess) throws NoSuchAlgorithmException {

        String guessedHash = hashGen(guess);
        if(guessedHash.equals(task_2_hash)){
            return "You succeed and the password is: " + guess;
        }
        else {
            return "Incorrect guess";
        }
    }
}
