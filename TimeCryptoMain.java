import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Chalkias Konstantinos
 * Date: 26 Απρ 2006
 */
public class TimeCryptoMain {
    public static void main(String args[]) {

        RivestTLPuzzleCreator puzCreator = new RivestTLPuzzleCreator();
        puzCreator.createPuzzle("You are the king of the world $$$$", new BigInteger(16, new Random()), new BigInteger("1000"));

        RivestPuzzleSolver puzSolver = new RivestPuzzleSolver();
        puzSolver.solvePuzzle(puzCreator.getPrimeLength(), puzCreator.getN(), puzCreator.getA(), puzCreator.getT(), puzCreator.getZ());
        puzSolver.verifySolution();
        puzSolver.printSolution();
        puzCreator.SquaringsPerSec();
        puzCreator.RequiredSquarings((byte)1);
       //puzCreator.PrintPuzzlePrivateInfo("c://privatePuzzleInfo.txt");
        //puzCreator.PrintPuzzlePublicInfo("c://publicPuzzleInfo.txt");

        System.out.println("-----------------------------------------");
        /*
        MaoTLPuzzleCreator maoCreator = new MaoTLPuzzleCreator();
        maoCreator.createPuzzle("You are the king of the world $$$$", new BigInteger(16, new Random()), new BigInteger("1000"));

        MaoPuzzleSolver maoSolver = new MaoPuzzleSolver();
        maoSolver.solvePuzzle(maoCreator.getPrimeLength(), maoCreator.getN(), maoCreator.getA(), maoCreator.getT(), maoCreator.getZ(), maoCreator.getE(), maoCreator.getAe());
        maoSolver.verifySolution();
        maoSolver.printSolution();
        //maoCreator.PrintPuzzlePrivateInfo("c://privatePuzzleInfo.txt");
        //maoCreator.PrintPuzzlePublicInfo("c://publicPuzzleInfo.txt");
        
        MaoTLSigCreator maoSigCreator = new MaoTLSigCreator();
        maoSigCreator.createPuzzle("You are the king of the world $$$$", new BigInteger(16, new Random()), new BigInteger("1000"));

        MaoSigSolver maoSigSolver = new MaoSigSolver();
        maoSigSolver.solvePuzzle(maoSigCreator.getPrimeLength(), maoSigCreator.getN(), maoSigCreator.getA(), maoSigCreator.getT(), maoSigCreator.getZ(), maoSigCreator.getE(), maoSigCreator.getAe(),maoSigCreator.getM());
        maoSigSolver.verifySolution();
        maoSigSolver.printSolution();
        */
    }
}
