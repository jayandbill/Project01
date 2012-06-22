/**
 * A wrapper for the random number class.
 * A myRandom object can be created for each problem set and paper test.
 * If we use it correctly, then the problem sets will be repeatable.
 */
package PolynomialExpressionGenerator;

import java.util.Random;

/**
 *
 * @author Nicholson.Bill
 */
public class myRandom {

    /**
     * The default seed is randomly chosen.
     * The default can be overridden by calling setSeed() or using the appropriate constructor
     */
    private Random random;
    private long seed;

    public myRandom(long seed) {
        random = new Random(seed);
    }

    public myRandom() {
        random = new Random();
    }

//    This won't work correctly because there is no clone method in the Java random class.
//      You can call getSeed() to get the starting point of the object, but
//       the current state of a random number generator object is not available.
    public myRandom clone() {
        myRandom newMyRandom = new myRandom();
        newMyRandom.setSeed(seed);
//      newMyRandom.random = random.clone();        // There is no clone()
        return newMyRandom;
    }

    /**
     * Override the current seed and start a new stream of random numbers.
     * @param mySeed the new seed for the random number generator.
     */
    public void setSeed(long seed) {
        this.seed = seed;
        random.setSeed(seed);
    }

    public long getSeed() {
        return seed;
    }

    /**
     * Generate the next random number in the sequence.
     * @param n
     * @return The next random number in the sequence.
     */
    public int nextInt(int n) {
        return random.nextInt(n);
    }
}
