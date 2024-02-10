import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Task3 {

    private static int count = 0;
    static String flag = "";
    private static final String task_3_hash = "bfb2c12706757b8324368de6a365338b";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        ZonedDateTime start = ZonedDateTime.now();

        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        generate(arr,7, "", arr.length, start);
        System.out.println("no. of iterations = "+ count);
    }

    private static void generate(char[] arr, int i, String s, int strlen, ZonedDateTime start) throws NoSuchAlgorithmException {

        if( i ==0){

            String numb = "1234";

            for(int k =1; k<8; k++){
                StringBuilder sb =  new StringBuilder();
                sb.append(s, 0, k-1).append(numb).append(s.substring(k-1));
                System.out.println("check password is: "+ sb.toString());
                String res = checkPassword_for_task_3(sb.toString());

                if(!res.equals("Incorrect guess")){
                    System.out.println(res);
                    long seconds = start.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
                    System.out.println("Total time taken to crack is: " + seconds + " seconds");
                    flag = "break";
                    break;
                }
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

    private static String checkPassword_for_task_3(String guess) throws NoSuchAlgorithmException {

        String guessedHash = hashGen(guess);
        if(guessedHash.equals(task_3_hash)){
            return "You succeed and the password is: " + guess;
        }
        else {
            return "Incorrect guess";
        }
    }
}
