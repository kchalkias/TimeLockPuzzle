/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Utils {

    final static BigInteger ZERO = new BigInteger("0");
    final static BigInteger ONE = new BigInteger("1");
    final static BigInteger TWO = new BigInteger("2");
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    static String getString(String what) throws IOException {
        // This routine is essentially a prompted "readLine"
        StringBuffer s = new StringBuffer();
        System.out.println("Enter "+what+" followed by a carriage return:");
        for (int i = 0;i<1000;i++)
           { int c = System.in.read();
             if (c<0 || c == '\n') break;
             if (c != '\r') // note: ignore cr before newline
               s = s.append((char)c);
           }
        return(s.toString());
    }

    static BigInteger getBigIntegerFromStringBuffer(StringBuffer s) {
        // Base-256 interpretation of the given string
        BigInteger randbi = ZERO;
        for (int i = 0;i<s.length();i++)
            {   int c = s.charAt(i);
                randbi = randbi.shiftLeft(8).add(new BigInteger(Integer.toString(c)));
            }
        return randbi;
    }

    static String getStringFromBigInteger(BigInteger b) {
        // Base-256 interpretation of the given BigInteger
        String message;
        BigInteger curB = b;
        message = "";
        int counter = 0;
        //count number of characters
        while (!curB.equals(ZERO)) {
            curB = curB.shiftRight(8);
            counter++;
        }
        //add chars to message
        for (int i = 0; i<counter; i++) {
            BigInteger previousB = b.shiftRight(8*(counter - i));
            BigInteger currentB = b.shiftRight(8*(counter - 1 - i));
            BigInteger curCharValue = currentB.subtract(previousB.shiftLeft(8));
            char curChar = (char)(Integer.parseInt(curCharValue.toString()));
            message = message + String.valueOf(curChar);
        }
        return message;
    }

    static void printBigInteger (BigInteger x, PrintWriter pw) {
        String s = x.toString();
        int charsPerLine = 60;
        for (int i = 0;i<s.length();i+=charsPerLine)
        {   if (i!=0) { pw.println(); pw.print("    "); }
            pw.print(s.substring(i,java.lang.Math.min(i+charsPerLine,s.length())));
        }
        pw.println();
    }
    public BigInteger getNextPrime(BigInteger startValue)
    {
        BigInteger p = startValue;
        if (!p.and(ONE).equals(ONE))   p = p.add(ONE);
        while (!p.isProbablePrime(40)) p = p.add(TWO);
        return(p);
    }

}
