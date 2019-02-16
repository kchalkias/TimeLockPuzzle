/**
 * Created by Chalkias Konstantinos
 * Date: 26 Απρ 2006
 */
import java.math.BigInteger;

public class MaoPuzzleSolver extends Puzzle{
    //String fullMessage;
    BigInteger e;
    BigInteger ae;
    BigInteger messageBigInteger;
    String clearMessage;
    //BigInteger seedP;
    boolean verify;

    public MaoPuzzleSolver() {
        clearMessage="";
        messageBigInteger = ZERO;
        e = ZERO;
        ae = ZERO;
        verify = false;
    }
    public void solvePuzzle(int primeLength, BigInteger n, BigInteger a, BigInteger t, BigInteger z, BigInteger e, BigInteger ae) {
        setPrimeLength(primeLength);
        setN(n);
        setA(a);
        setT(t);
        setZ(z);
        setE(e);
        setAe(ae);
        setW(DoSquarings());
        setMessageBigInteger(getZ().multiply(getW().modInverse(getN())).mod(getN()));
        setClearMessage(getStringFromBigInteger(getMessageBigInteger()));
        //System.out.println(getClearMessage());

    }
    public void verifySolution() {

        BigInteger ze = getZ().modPow(getE(),getN());
        BigInteger me = getMessageBigInteger().modPow(getE(),getN());
        BigInteger testZe = getAe().multiply(me).mod(getN());

        if (ze.equals(testZe)) {
            setVerify( true );
        }
        else setVerify( false );
    }

    public void printSolution() {
        if (verify) {
            System.out.println("Verified timed-release message: OK");
            System.out.println("Clear Message: " + getClearMessage());
        }
        else System.out.println("Unable to verify timed-release message");

    }
    //Setters
    protected void setMessageBigInteger(BigInteger mb) {
        this.messageBigInteger = mb;
    }
    protected void setClearMessage(String m) {
        this.clearMessage = m;
    }
    private void setE(BigInteger e) {
        this.e = e;
    }
    private void setAe(BigInteger ae) {
        this.ae = ae;
    }
    protected void setVerify(boolean v) {
        this.verify = v;
    }
    //Getters
    public BigInteger getMessageBigInteger() {
        return messageBigInteger;
    }
    public String getClearMessage() {
        return clearMessage;
    }
    public BigInteger getE() {
        return e;
    }
    public BigInteger getAe() {
        return ae;
    }
    public boolean getVerify() {
        return verify;
    }
}
