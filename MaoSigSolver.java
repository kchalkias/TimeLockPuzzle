import java.math.BigInteger;

/**
 * Created by Chalkias Konstantinos
 * Date: 26 Απρ 2006
 */
public class MaoSigSolver extends MaoPuzzleSolver{
    BigInteger m;
    public MaoSigSolver() {
        super();
    }
    public void solvePuzzle(int primeLength, BigInteger n, BigInteger a, BigInteger t, BigInteger z, BigInteger e, BigInteger ae, BigInteger m) {
        super.solvePuzzle(primeLength, n, a, t, z, e, ae);
        setM(m);
    }
    @Override
    public void verifySolution() {

        BigInteger ze = getZ().modPow(getE(),getN());
        BigInteger testZe = getAe().multiply(getM()).mod(getN());

        if (ze.equals(testZe)) {
            setVerify( true );
        }
        else setVerify( false );
    }
    public void printSolution() {
        if (verify) {
            System.out.println("Verify timed-release signature: OK");
        }
        else System.out.println("Unable to verify timed-release signature");

    }
    protected void setM(BigInteger m) {
        this.m = m;
    }
    public BigInteger getM() {
        return m;
    }
}
