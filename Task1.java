import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Task1 {

    private static int count = 0;
    static String flag = "";
    private static final String task_1_hash = "94d9e03c11395841301a7aee967864ec";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        ZonedDateTime start = ZonedDateTime.now();

        char[] arr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                        '0', '1','2','3','4','5','6','7','8','9'};
        generate(arr,14, "", arr.length, start);
        System.out.println("no. of iterations = " + count);
    }

    private static void generate(char[] arr, int i, String s, int strlen, ZonedDateTime start) throws NoSuchAlgorithmException {

        if( i ==0){
            System.out.println(s);
            String res = checkPassword_for_task_1(s);

            if(!res.equals("Incorrect guess")) {
                System.out.println(res);
                long seconds = start.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
                System.out.println("Total time taken to crack is: " + seconds + " seconds");
                flag = "break";
            }
            return;
        }

        for(int j =0; j< strlen; j++){
            if(flag.equals("break")){
                break;
            }
            String st = s + arr[j];
            count++;
            generate(arr, i-1, st, strlen, start);
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

    private static String checkPassword_for_task_1(String guess) throws NoSuchAlgorithmException {

        String guessedHash = hashGen(guess);

        if(guessedHash.equals(task_1_hash)){
            return "You succeed and the password is: " + guess;
        }
        else {
            return "Incorrect guess";
        }

    }
}
