/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */

import java.math.BigInteger;
import java.util.Random;

public class RSAparamGenerator extends Utils{
    private BigInteger p;   //first prime
    private BigInteger q;   //second prime
    private BigInteger pm1; //(p-1)
    private BigInteger qm1; //(q-1)
    private BigInteger phi; //(p-1)*(q-1) -> Euler's Φ(n) function
    private BigInteger n;   //(p*q) -> modulo
    private BigInteger e;   //public exponent
    private BigInteger d;   //private exponent
    private int primeLength;//bit-length of primes p , q

    final BigInteger ZERO = new BigInteger("0");
    final BigInteger ONE = new BigInteger("1");
    final BigInteger TWO = new BigInteger("2");

    public RSAparamGenerator(int primelength) {
        this.primeLength = primelength;
    }

    public void RSAparamGen() {
        //Generate primes p , q
        this.pqGen(primeLength);

        // Calculate their product
        n = p.multiply(q);

        //compute (p-1) and (q-1)
        pm1 = p.subtract(ONE);
        qm1 = q.subtract(ONE);

        // And calculate phi = (p-1)(q-1)
        phi = pm1.multiply(qm1);

        // Next choose e, coprime to and less than phi
        do  {
            e = new BigInteger(2*primeLength, new Random());
        }while ((e.compareTo(phi) != -1) || (e.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));

        // Compute d, the inverse of e mod phi
        d = e.modInverse(phi);
    }
    // Generate two distinct large prime numbers p, q
    protected void pqGen (int primeLength) {
        setP(new BigInteger(primeLength, 10, new Random()));
        do  {
            setQ(new BigInteger(primeLength, 10, new Random()));
        }while (getQ().compareTo(getP()) == 0);
    }

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
    protected void setN(BigInteger nMod) {
        this.n = nMod;
    }
    protected void setE(BigInteger eExp) {
        this.e = eExp;
    }
    protected void setD(BigInteger dExp) {
        this.d = dExp;
    }
    protected void setPrimeLength(int primeLength) {
        this.primeLength = primeLength;
    }
    //Getters
    public BigInteger getP() {
        return p;
    }
    public BigInteger getQ() {
        return q;
    }
    public BigInteger getPm1() {
        return pm1;
    }
    public BigInteger getQm1() {
        return qm1;
    }
    public BigInteger getPhi() {
        return phi;
    }
    public BigInteger getE() {
        return e;
    }
    public BigInteger getD() {
        return d;
    }
    public BigInteger getN() {
        return n;
    }
    public int getPrimeLength() {
        return primeLength;
    }


}
