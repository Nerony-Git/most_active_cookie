import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author george_amuzu
 */
public class ActiveCookie {
    public static String F_Name = "";
    public static String Flag = "";
    public static String sDate = "";
    public static final String eIncorrectFlag = "Incorrect flag. Please use the proper flag (-d).";
    public static final String eIncorrectFormat = "Incorrect usage. \nPlease use this format: ./most_active_cookie [filename] [-d] [date]"; 
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static boolean isSameDate(LocalDate inputDate, LocalDate currentDate) {
        return inputDate.equals(currentDate);
    }

    public static TokenPair parse_Token_Pair(String[] ans) {
        LocalDate parsedDate = LocalDate.parse(ans[1].substring(0,10),format);
        TokenPair tokenPair = new TokenPair(ans[0], parsedDate);
        
        return tokenPair;
    }

    public static HashSet<String> most_active_cookie(HashMap<String, Integer> csv) {
        HashSet<String> res = new HashSet<String>();
        Integer maxValue = 0;

        for(String key: csv.keySet()) {
            if(csv.get(key) == maxValue) {
                res.add(key);
            }
            else if(csv.get(key) > maxValue){
                res.clear();
                maxValue = csv.get(key);
                res.add(key);
            }
        }

        for(String str : res) {
            System.out.println(str);
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length != 3) {
            System.out.println(eIncorrectFormat);
            return;
        }

        F_Name = args[0];
        Flag = args[1];
        sDate = args[2];

        if(!Flag.equals("-d")) {
            System.out.println(eIncorrectFlag);
            return;
        }

        HashMap<String, Integer> csv;

        try {
            csv = parseCSV(F_Name);
        }
        catch(Exception e) {
            return;
        }
        
        most_active_cookie(csv);
    }

    public static HashMap<String, Integer> parseCSV(String fName) throws FileNotFoundException {
        HashMap<String, Integer> arranged = new HashMap<String, Integer>();

        try (BufferedReader buff = new BufferedReader(new FileReader(fName))) {
            String line;
            buff.readLine();
            while ((line = buff.readLine()) != null) {
                String[] ans = line.split(",");

                if(ans.length != 2) {
                    throw new Exception();
                }


                TokenPair current_Token_Pair = parse_Token_Pair(ans);

                if(!isSameDate(LocalDate.parse(sDate,format), current_Token_Pair.getDate())) {
                    continue;
                }

                if(arranged.containsKey(current_Token_Pair.getTokenId())) {
                    arranged.put(current_Token_Pair.getTokenId(), arranged.get(current_Token_Pair.getTokenId())+1);
                }
                else {
                    arranged.put(current_Token_Pair.getTokenId(), 1);
                }
            }
        }
        catch(FileNotFoundException fn) {
            System.out.println("Error: File " + F_Name + " not found.");
        }    
        catch(Exception e) {
            System.out.println("Error: The file format is wrong.");
        }
        return arranged;
    }
}
