import java.math.BigInteger;

/**
 * Created by Chalkias Konstantinos
 * Date: 26 Απρ 2006
 */
public class RivestRSAparamGenerator extends RSAparamGenerator{
    private BigInteger pseed;
    private BigInteger qseed;

    public RivestRSAparamGenerator(int primelength, String pseed, String qseed) {
        super(primelength);
        this.pseed = new BigInteger(pseed);
        this.qseed = new BigInteger(qseed);
    }

    // Generate two distinct large prime numbers p, q using a seed
    @Override
    protected void pqGen (int primeLength) {
        setP();
        setQ();
    }

    protected void setP() {
        BigInteger twoPower = (ONE).shiftLeft(getPrimeLength());
        BigInteger p = new BigInteger("5");
        // Note that 5 has maximal order modulo 2^k (See Knuth)
        setP(getNextPrime(p.modPow(pseed,twoPower)));
    }
    protected void setQ() {
        BigInteger twoPower = (ONE).shiftLeft(getPrimeLength());
        BigInteger q = new BigInteger("5");
        setQ(getNextPrime(q.modPow(qseed,twoPower)));
    }

    //Setters
    public void setPseed(BigInteger pseed) {
        this.pseed = pseed;
    }
    public void setQseed(BigInteger qseed) {
        this.qseed = qseed;
    }
    //Getters
    public BigInteger getPseed() {
        return pseed;
    }
    public BigInteger getQseed() {
        return qseed;
    }


}
