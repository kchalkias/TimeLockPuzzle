import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */
public class RivestTLPuzzleCreator extends TLPuzzle {
    protected RivestRSAparamGenerator RsaGen;
    public RivestTLPuzzleCreator() {}

    protected void createPuzzle(String clearMessage, BigInteger a, BigInteger t) {
        String seedP, seedQ;
        setA(a);
        setT(t);
        /*
        try {
            seedP = getString("seed for p");
            seedQ = getString("seed for q");
        } catch (IOException e) {
            e.printStackTrace();
            seedP = new BigInteger(1024, new Random()).toString();
            seedQ = new BigInteger(1024, new Random()).toString();
            System.out.println("New seed for p is:" + seedP);
            System.out.println("New seed for q is:" + seedQ);
        }*/
        seedP = new BigInteger(128, new Random()).toString();
        seedQ = new BigInteger(128, new Random()).toString();
        System.out.println("Computing Rivest's Time-Lock Puzzle. Please wait...");
        RsaGen = new RivestRSAparamGenerator(1024,seedP,seedQ);
        RsaGen.RSAparamGen();
        setP(RsaGen.getP());
        setQ(RsaGen.getQ());
        setPm1(RsaGen.getPm1());
        setQm1(RsaGen.getQm1());
        setN(RsaGen.getN());
        setPhi(RsaGen.getPhi());
        setPrimeLength(RsaGen.getPrimeLength());
        createW();
        setM(convertMessageToBigInteger(clearMessage));
        createZ(getM());
    }

    protected void createW() {
        BigInteger u = TWO.modPow(getT(),getPhi());
        setW(getA().modPow(u,getN()));
    }

    protected void createZ(BigInteger secret) {
        try {
            setZ(secret.xor(getW()));
        }catch (Exception ex) {System.out.println("Error during encryption process");}
    }

    protected BigInteger convertMessageToBigInteger(String m) {
        // Obtain and encrypt the secret message
        // Include seed for p as a check
        StringBuffer sgen = new StringBuffer(m);
        sgen.append("\n(seedP:");
        sgen.append(RsaGen.getPseed().toString());
        sgen.append(")");

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
        pw.println("Implementation from the paper titled: 'Time-lock puzzles and timed-release Crypto'");
        pw.println("Paper authors: Ronald L. Rivest, Adi Shamir, David A. Wagner >> Paper revised @ March 10 1996");
        pw.println("Program Created by Ronald L. Rivest (for LCS35 Time Capsule). April 2, 1999.");
        pw.println("Modified by Chalkias Konstantinos. April 24 2006"); pw.println();
        pw.println("Puzzle parameters (all in decimal):"); pw.println();
        pw.println("prime bit-length = " + getPrimeLength()); pw.println();
        pw.print("a = "); printBigInteger(getA(),pw); pw.println();
        pw.print("n = "); printBigInteger(getN(),pw); pw.println();
        pw.print("t = "); printBigInteger(getT(),pw); pw.println();
        pw.print("z = "); printBigInteger(getZ(),pw); pw.println();
        pw.println("To solve the puzzle, first compute w = a^(2^t) (mod n).");
        pw.println("Then exclusive-or the result with z.");
        pw.println("(Right-justify the two strings first.)");
        pw.println();
        pw.println("The result is the secret message (8 bits per character),");
        pw.println("including information that will allow you to factor n.");
        pw.println("(The extra information is a seed value b, such that ");
        pw.println("5^b (mod 2^1024) is just below a prime factor of n.)");
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
        pw.println("Implementation from the paper titled: 'Time-lock puzzles and timed-release Crypto'");
        pw.println("Paper authors: Ronald L. Rivest, Adi Shamir, David A. Wagner >> Paper revised @ March 10 1996");
        pw.println("Program Created by Ronald L. Rivest (for LCS35 Time Capsule). April 2, 1999.");
        pw.println("Modified by Chalkias Konstantinos. April 24 2006"); pw.println();
        pw.println("Puzzle private parameters (all in decimal):"); pw.println();
        pw.print("p = "); printBigInteger(getP(),pw); pw.println();
        pw.print("q = "); printBigInteger(getQ(),pw); pw.println();
        pw.print("(p-1) = "); printBigInteger(getPm1(),pw); pw.println();
        pw.print("(q-1) = "); printBigInteger(getQm1(),pw); pw.println();
        pw.print("Euler's phi = "); printBigInteger(getPhi(),pw); pw.println();
        pw.print("w = "); printBigInteger(getW(),pw); pw.println();
        pw.println("--------------------------------------------------------"); pw.println();
        pw.println("Puzzle public parameters (all in decimal):"); pw.println();
        pw.println("prime bit-length = " + getPrimeLength()); pw.println();
        pw.print("a = "); printBigInteger(getA(),pw); pw.println();
        pw.print("n = "); printBigInteger(getN(),pw); pw.println();
        pw.print("t = "); printBigInteger(getT(),pw); pw.println();
        pw.print("z = "); printBigInteger(getZ(),pw); pw.println();
        pw.println("To solve the puzzle, first compute w = a^(2^t) (mod n).");
        pw.println("Then exclusive-or the result with z.");
        pw.println("(Right-justify the two strings first.)");
        pw.println();
        pw.println("The result is the secret message (8 bits per character),");
        pw.println("including information that will allow you to factor n.");
        pw.println("(The extra information is a seed value b, such that ");
        pw.println("5^b (mod 2^1024) is just below a prime factor of n.)");
        pw.println(" ");
        pw.close();
    }
}