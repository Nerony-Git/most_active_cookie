
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author george_amuzu
 */
 
public class ActiveCookieTest {
    
    public ActiveCookieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isSameDate method, of class ActiveCookie.
     */
    @Test
    public void testIsSameDate() {
        System.out.println("isSameDate");
        LocalDate inputDate = LocalDate.of(2022,5,26);
        LocalDate currentDate = LocalDate.of(2022,5,27);
        boolean expResult = false;
        boolean result = ActiveCookie.isSameDate(inputDate, currentDate);
        assertEquals(expResult, result);
        
        
        LocalDate inputDate2 = LocalDate.of(2022,5,26);
        LocalDate currentDate2 = LocalDate.of(2022,5,26);
        boolean expResult2 = true;
        boolean result2 = ActiveCookie.isSameDate(inputDate2, currentDate2);
        assertEquals(expResult2, result2);
        
    }

    /**
     * Test of parse_Token_Pair method, of class ActiveCookie.
     */
    @Test
    public void testParse_Token_Pair() {
        System.out.println("parse_Token_Pair");
        String[] ans = {"C", "2022-05-26"};
        TokenPair expResult = new TokenPair("C", LocalDate.of(2022,5,26));
        TokenPair result = ActiveCookie.parse_Token_Pair(ans);
        assertEquals(expResult, result);
    }

    /**
     * Test of most_active_cookie method, of class ActiveCookie.
     */
    @Test
    public void testMost_active_cookie() {
        System.out.println("most_active_cookie");
        HashMap<String, Integer> csv = new HashMap<String, Integer>();
        csv.put("1", 5);
        csv.put("2", 4);
        csv.put("3", 5);
        csv.put("4", 3);
        
        HashSet<String> expResult = new HashSet<String>();
        expResult.add("3");
        expResult.add("1");
        
        HashSet<String> result = ActiveCookie.most_active_cookie(csv);
        assertEquals(expResult, result);
     
    }
    
}
