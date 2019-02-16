/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */
import java.math.BigInteger;

public class RivestPuzzleSolver extends Puzzle {
    String fullMessage;
    BigInteger messageBigInteger;
    String clearMessage;
    BigInteger seedP;
    boolean verify;

    public RivestPuzzleSolver() {
        fullMessage="";
        clearMessage="";
        messageBigInteger = ZERO;
        seedP = ZERO;
        verify = false;
    }
    public void solvePuzzle(int primeLength, BigInteger n, BigInteger a, BigInteger t, BigInteger z) {
        setPrimeLength(primeLength);
        setN(n);
        setA(a);
        setT(t);
        setZ(z);
        setW(DoSquarings());
        setMessageBigInteger(getW().xor(getZ()));
        setFullMessage(getStringFromBigInteger(getMessageBigInteger()));
        setClearMessage(getClearFromMessage());
        setSeedP(getSeedPFromMessage());
        //System.out.println(getFullMessage());

    }
    public void verifySolution() {

        BigInteger twoPower = (ONE).shiftLeft(getPrimeLength());
        BigInteger p = new BigInteger("5");
        BigInteger testP, testQ;
        // Note that 5 has maximal order modulo 2^k (See Knuth)
        testP = getNextPrime(p.modPow(seedP,twoPower));
        testQ = getN().divide(testP);
        if (getN().mod(testP).equals(ZERO) && testQ.isProbablePrime(40)) {
            setVerify( true );
        }
        else setVerify( false );
    }
    public BigInteger getSeedPFromMessage() {
        try {
            return new BigInteger(getFullMessage().substring(getFullMessage().indexOf("(seedP:") + 7, getFullMessage().length() - 1));
            }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public String getClearFromMessage() {
        try {
            return getFullMessage().substring(0,getFullMessage().indexOf("(seedP:")-1);
            }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public void printSolution() {
        if (verify) {
            System.out.println("Verified timed-release message: OK");
            System.out.println("Clear Message: " + getClearMessage());
        }
        else System.out.println("Unable to verify timed-release message");

    }
    //Setters
    protected void setFullMessage(String m) {
        this.fullMessage = m;
    }
    protected void setMessageBigInteger(BigInteger mb) {
        this.messageBigInteger = mb;
    }
    protected void setClearMessage(String m) {
        this.clearMessage = m;
    }
    protected void setSeedP(BigInteger seed) {
        this.seedP = seed;
    }
    protected void setVerify(boolean v) {
        this.verify = v;
    }
    //Getters
    public String getFullMessage() {
        return fullMessage;
    }
    public BigInteger getMessageBigInteger() {
        return messageBigInteger;
    }
    public String getClearMessage() {
        return clearMessage;
    }
    public BigInteger getSeedP() {
        return seedP;
    }
    public boolean getVerify() {
        return verify;
    }
}
