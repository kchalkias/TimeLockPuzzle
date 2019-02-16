import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */
public class MaoTLPuzzleCreator extends TLPuzzle {
    protected BigInteger ae;
    protected BigInteger RsaCiphertext;
    protected RSAparamGenerator RsaGen;

    public MaoTLPuzzleCreator() {}

    protected void createPuzzle(String clearMessage, BigInteger a, BigInteger t) {
        setA(a);
        setT(t);
        System.out.println("Computing Mao's Time-Lock Puzzle. Please wait...");
        RsaGen = new RSAparamGenerator(1024);
        RsaGen.RSAparamGen();
        setP(RsaGen.getP());
        setQ(RsaGen.getQ());
        setPm1(RsaGen.getPm1());
        setQm1(RsaGen.getQm1());
        setN(RsaGen.getN());
        setPhi(RsaGen.getPhi());
        setE(RsaGen.getE());
        setD(RsaGen.getD());
        setPrimeLength(RsaGen.getPrimeLength());
        createW();
        setAe(getW().modPow(getE(),getN()));
        setM(convertMessageToBigInteger(clearMessage));
        createZ(getM());
        setRsaCiphertext(getM().modPow(getE(),getN()));
    }
    protected void createW() {
        BigInteger u = TWO.modPow(getT(),getPhi());
        setW(getA().modPow(u,getN()));
    }

    protected void createZ(BigInteger secret) {
        setZ(secret.multiply(getW()).mod(getN()));
    }

    protected BigInteger convertMessageToBigInteger(String m) {
        // Obtain and encrypt the secret message
        StringBuffer sgen = new StringBuffer(m);
        BigInteger secret = getBigIntegerFromStringBuffer(sgen);
        //System.out.println("Secret: "+ secret);
        if (secret.compareTo(getN()) > 0)
            { System.out.println("Secret too large!"); return null; }
        return secret;
    }
    public void PrintPuzzlePublicInfo(String filePath) {
        // Write output to a file
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pw != null;
        pw.println("Time Capsule Crypto-Puzzle.");
        pw.println("Implementation from the paper titled: 'Timed-release Cryptography'");
        pw.println("Paper authors: Wenbo Mao");
        pw.println("Program Created by Chalkias Konstantinos. April 24 2006.");pw.println();
        pw.println("Puzzle parameters (all in decimal):"); pw.println();
        pw.println("prime bit-length = " + getPrimeLength()); pw.println();
        pw.print("a = "); printBigInteger(getA(),pw); pw.println();
        pw.print("e = "); printBigInteger(getE(),pw); pw.println();
        pw.print("ae = "); printBigInteger(getAe(),pw); pw.println();
        pw.print("n = "); printBigInteger(getN(),pw); pw.println();
        pw.print("t = "); printBigInteger(getT(),pw); pw.println();
        pw.print("z = "); printBigInteger(getZ(),pw); pw.println();
        //pw.print("RsaCipherText = "); printBigInteger(getRsaCiphertext(),pw); pw.println();
        pw.println("To solve the puzzle, first compute w = a^(2^t) (mod n).");
        pw.println("Then exclusive-or the result with z.");
        pw.println("(Right-justify the two strings first.)");
        pw.println();
        pw.println("The result is the secret message M (8 bits per character),");
        pw.println("To verify the result you have to check that z^e = (ae)(M^e) (mod n)");
        pw.println(" ");
        pw.close();
    }

    protected void PrintPuzzlePrivateInfo(String filePath) {
        // Write output to a file
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pw != null;
        pw.println("Time Capsule Crypto-Puzzle.");
        pw.println("Implementation from the paper titled: 'Timed-release Cryptography'");
        pw.println("Paper authors: Wenbo Mao");
        pw.println("Program Created by Ronald L. Rivest (for LCS35 Time Capsule). April 2, 1999.");
        pw.println("Modified by Chalkias Konstantinos. April 24 2006"); pw.println();
        pw.println("Puzzle private parameters (all in decimal):"); pw.println();
        pw.print("p = "); printBigInteger(getP(),pw); pw.println();
        pw.print("q = "); printBigInteger(getQ(),pw); pw.println();
        pw.print("(p-1) = "); printBigInteger(getPm1(),pw); pw.println();
        pw.print("(q-1) = "); printBigInteger(getQm1(),pw); pw.println();
        pw.print("Euler's phi = "); printBigInteger(getPhi(),pw); pw.println();
        pw.print("w = "); printBigInteger(getW(),pw); pw.println();
        pw.print("d = "); printBigInteger(getD(),pw); pw.println();
        pw.print("RsaCipherText = "); printBigInteger(getRsaCiphertext(),pw); pw.println();
        pw.println("--------------------------------------------------------"); pw.println();
        pw.println("Puzzle public parameters (all in decimal):"); pw.println();
        pw.println("prime bit-length = " + getPrimeLength()); pw.println();
        pw.print("a = "); printBigInteger(getA(),pw); pw.println();
        pw.print("e = "); printBigInteger(getE(),pw); pw.println();
        pw.print("ae = "); printBigInteger(getAe(),pw); pw.println();
        pw.print("n = "); printBigInteger(getN(),pw); pw.println();
        pw.print("t = "); printBigInteger(getT(),pw); pw.println();
        pw.print("z = "); printBigInteger(getZ(),pw); pw.println();
        pw.println("To solve the puzzle, first compute w = a^(2^t) (mod n).");
        pw.println("Then multiply the  Inv of the result with z.");
        pw.println();
        pw.println("The result is the secret message M (8 bits per character),");
        pw.println("To verify the result you have to check that z^e = (a^e)(M^e) (mod n)");
        pw.println(" ");
        pw.close();
    }
    //Setters
    protected void setAe(BigInteger ae) {
        this.ae = ae;
    }
    protected void setRsaCiphertext(BigInteger c) {
        this.RsaCiphertext = c;
    }
    //Getters
    protected BigInteger getAe() {
        return ae;
    }
    protected BigInteger getRsaCiphertext() {
        return RsaCiphertext;
    }
}
