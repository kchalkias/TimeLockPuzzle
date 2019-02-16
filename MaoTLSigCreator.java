import java.math.BigInteger;

/**
 * Created by Chalkias Konstantinos
 * Date: 26 Απρ 2006
 */
public class MaoTLSigCreator extends MaoTLPuzzleCreator{ 
    public MaoTLSigCreator() {
        super();
    }

    @Override
    protected void createZ(BigInteger secret) {
        BigInteger Md = secret.modPow(getD(),getN());
        setZ(Md.multiply(getW()).mod(getN()));
    }
}
