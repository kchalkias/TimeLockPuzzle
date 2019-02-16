/**
 * Created by Chalkias Konstantinos
 * Date: 25 Απρ 2006
 */
import java.math.BigInteger;

public class Puzzle extends Utils {
    private BigInteger t;   //squarings
    private BigInteger a;   //base
    private BigInteger n;   //(p*q) -> modulo
    private BigInteger w;   //the a^(2^t)modN
    private BigInteger z;   //the cryptogram
    private int primeLength;//the prime bit-length

    //computes a^(2^t) mod n
    protected BigInteger DoSquarings() {
        BigInteger result = this.getA();
        BigInteger numOfSquarings = this.t;
        while(!numOfSquarings.equals(ZERO)) {
            result = result.modPow(TWO, this.n);
            numOfSquarings = numOfSquarings.subtract(ONE);
        }
        return result;
    }
    //computes a^(2^t) mod n where t->int (just needed for squarings per second tests)
    protected BigInteger DoSquarings(int numOfSquarings, BigInteger mod) {
        BigInteger result = this.getA();
        for (long i = 0; i < numOfSquarings; i++) {
            result = result.modPow(TWO,mod);
        }
        return result;
    }
    protected BigInteger DoSquarings(BigInteger base, BigInteger t, BigInteger mod) {
        BigInteger result = base;
        while(!t.equals(ZERO)) {
            result = result.modPow(TWO, mod);
            t = t.subtract(ONE);
        }
        return result;
    }

    //computes squarings per second for current machine
    protected int SquaringsPerSec() {

        int squaringsPerSec;
        final int numOfTestSquarings = 20000;
        long t=System.currentTimeMillis();

        DoSquarings(numOfTestSquarings, this.n);
        //DoSquarings(numOfTestSquarings,new BigInteger(2048,new Random()));
        t = System.currentTimeMillis() - t;

        squaringsPerSec = numOfTestSquarings*2000/(int)t;
        System.out.println("Assumed number of squarings/second (for current CPU) = " + squaringsPerSec);
        return squaringsPerSec;
    }
    //calculates the approximated number of squarings needed (for the current CPU) to decrypt a message after 'x' days
    protected BigInteger RequiredSquarings(int numOfDays) {
        final BigInteger secondsPerDay = new BigInteger("86400");
        final BigInteger daySquarings = new BigInteger(Integer.toString(SquaringsPerSec())).multiply(secondsPerDay);
        final BigInteger totalSquarings = daySquarings.multiply(new BigInteger(Integer.toString(numOfDays)));
        System.out.println("Desired squarings for " + numOfDays + " days:" + totalSquarings);
        return totalSquarings;

    }
    //calculates the approximated number of squarings needed (for the current CPU) to decrypt a message after 'x' hours
    protected BigInteger RequiredSquarings(byte numOfHours) {
        final BigInteger secondsPerHour = new BigInteger("3600");
        final BigInteger daySquarings = new BigInteger(Integer.toString(SquaringsPerSec())).multiply(secondsPerHour);
        final BigInteger totalSquarings = daySquarings.multiply(new BigInteger(Integer.toString(numOfHours)));
        System.out.println("Desired squarings for " + numOfHours + " hours:" + totalSquarings);
        return totalSquarings;

    }
    //calculates the approximated number of squarings needed to decrypt a message after 'x' years
    //apply Moore's Law using a constant rate eg. 'x10' or 'x3'
    protected BigInteger RequiredSquarings(int years, int rate) {
        final BigInteger secondsPerYear = new BigInteger("31536000");
        final BigInteger firstYearSquarings = new BigInteger(Integer.toString(SquaringsPerSec())).multiply(secondsPerYear);

        BigInteger totalSquarings = firstYearSquarings;

        for (int i = 1; i < years; i++)
            totalSquarings = totalSquarings.add(firstYearSquarings.multiply(new BigInteger(Integer.toString(rate^i))));
        System.out.println("Desired squarings for " + years + " years and " + rate + " constant rate:" + totalSquarings);
        return totalSquarings;
    }

    //Setters
    protected void setT(BigInteger tSquarings) {
        this.t = tSquarings;
    }
    protected void setA(BigInteger aBase) {
        this.a = aBase;
    }
    protected void setN(BigInteger nMod) {
        this.n = nMod;
    }
    protected void setW(BigInteger w) {
        this.w = w;
    }
    protected void setZ(BigInteger zCrypt) {
        this.z = zCrypt;
    }
    protected void setPrimeLength(int len) {
        this.primeLength = len;
    }
    //Getters
    public BigInteger getT() {
        return t;
    }
    public BigInteger getA() {
        return a;
    }
    public BigInteger getN() {
        return n;
    }
    public BigInteger getW() {
        return w;
    }
    public BigInteger getZ() {
        return z;
    }
    public int getPrimeLength() {
        return primeLength;
    }

}
