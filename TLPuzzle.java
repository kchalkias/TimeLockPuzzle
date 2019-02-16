/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */

import java.math.BigInteger;
import java.io.IOException;

public abstract class TLPuzzle extends Puzzle {

    private BigInteger p;   //first prime
    private BigInteger q;   //second prime
    private BigInteger pm1; //(p-1)
    private BigInteger qm1; //(q-1)
    private BigInteger phi; //(p-1)*(q-1) -> Euler's phi function
    private BigInteger e;   //public exponent
    private BigInteger d;   //private exponent
    private BigInteger m; //the clear message

    protected abstract void createPuzzle(String clearMessage, BigInteger a, BigInteger t) throws IOException;
    protected abstract void createW();
    protected abstract void createZ(BigInteger message);
    protected abstract BigInteger convertMessageToBigInteger(String message);
    public abstract void PrintPuzzlePublicInfo(String filePath);
    protected abstract void PrintPuzzlePrivateInfo(String filePath);

    //Setters
    protected void setP(BigInteger p) {
        this.p = p;
    }
    protected void setQ(BigInteger q) {
        this.q = q;
    }
    protected void setPm1(BigInteger pm1) {
        this.pm1 = pm1;
    }
    protected void setQm1(BigInteger qm1) {
        this.qm1 = qm1;
    }
    protected void setPhi(BigInteger phi) {
        this.phi = phi;
    }
    protected void setE(BigInteger e) {
        this.e = e;
    }
    protected void setD(BigInteger d) {
        this.d = d;
    }
    protected void setM(BigInteger m) {
        this.m = m;
    }
    //Getters
    protected BigInteger getP() {
        return p;
    }
    protected BigInteger getQ() {
        return q;
    }
    protected BigInteger getPm1() {
        return pm1;
    }
    protected BigInteger getQm1() {
        return qm1;
    }
    protected BigInteger getPhi() {
        return phi;
    }
    protected BigInteger getE() {
        return e;
    }
    protected BigInteger getD() {
        return d;
    }
    public BigInteger getM() {
        return m;
    }


}
