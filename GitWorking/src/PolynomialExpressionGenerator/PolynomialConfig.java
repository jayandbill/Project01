/**
 * This class models the configuration data for a polynomial expression generator
 */

package PolynomialExpressionGenerator;

/**
 *
 * @author Nicholson.Bill
 */
public class PolynomialConfig {
    private int maxNumberOfVariables, minNumberOfVariables;
    private int maxCoefficient, minCoefficient;
    private int maxVariablesPerAtom, minVariablesPerAtom;
    private boolean forceFactorable;
    private int maxFactorsPerExpression,  minFactorsPerExpression;
    private int maxExponent, minExponent;
    private static String variablePoolDefault = "xyzpq";
    private String variablePool;
    private PolynomialExpression.ExpressionStyle expressionStyle;
    private myRandom random;
    private boolean removeMultiplicationSymbols;
/**
 * Constructor
 */
    public PolynomialConfig() {
        maxNumberOfVariables = 1;
        maxCoefficient = 5;
        minCoefficient = 1;
        maxVariablesPerAtom = 2;
        minVariablesPerAtom = 1;
        forceFactorable = false;
        maxFactorsPerExpression = 2;
        minFactorsPerExpression = 1;
        forceFactorable = false;
        maxExponent = 5;
        minExponent = 1;
        variablePool = variablePoolDefault;
        random = new myRandom();        // Random random
        setSeed(42);
        removeMultiplicationSymbols = false;
    }
/**
 * Make a duplicate of the object.
 * A deep copy is performed.
 * @return
 */
    public PolynomialConfig clone() {
            PolynomialConfig pc = new PolynomialConfig();
        try {
            pc.setForceFactorable(forceFactorable);
            pc.setMaxCoefficient(maxCoefficient);
            pc.setMaxFactorsPerExpression(maxFactorsPerExpression);
            pc.setMaxNumberOfVariables(maxNumberOfVariables);
            pc.setMaxVariablesPerAtom(maxVariablesPerAtom);
            pc.setMinCoefficient(minCoefficient);
            pc.setMinFactorsPerExpression(minFactorsPerExpression);
            pc.setMinNumberOfVariables(minNumberOfVariables);
            pc.setMinVariablesPerAtom(minVariablesPerAtom);
            pc.setVariablePool(variablePool);
            pc.setExpressionStyle(PolynomialExpression.ExpressionStyle.trinomial);
            pc.random = random.clone();
            pc.random.setSeed(random.getSeed());
        } catch (Exception ex) {
            Util.LogError("PolynomialConfig.clone: " + ex.getMessage());
            pc = null;
        }
        return pc;
    }

    public boolean getRemoveMultiplicationSymbols() {
        return removeMultiplicationSymbols;
    }
/**
 * Configure the expression processing to ignore or enforce multiplication symbols.
 * Sometimes algebraic expressions have the * operator and sometimes they are implied.
 * @param removeMultiplicationSymbols true if multiplication symbols should be removed.
 * @return true if multiplication symbols should be removed.
 */
    public boolean setremoveMultiplicationSymbols(boolean removeMultiplicationSymbols) {
        this.removeMultiplicationSymbols = removeMultiplicationSymbols;
        return removeMultiplicationSymbols;
    }
/**
 * Set the seed for the random number generator for this object.
 * Just call this once, then call Create() as needed. That will make the
 *  problem sets repeatable.
 * @param randomNumberSeed The new random number seed.
 * @return The new random number seed.
 */
    public long setSeed(long seed) {
        random.setSeed(seed);
        return seed;
    }
/**
 * Data hiding rule is violated here but there is no clone() method in the Java
 *  random number generator class.
 * @return The reference to the myRandom object in the PolynomialConfiguration object
 */
    public myRandom getRandom() {
        return random;
    }
/**
 * Get the seed for the random number generator for this object.
 * @return The new random number seed.
 */
    public long getSeed(){
        return random.getSeed();
    }
    
    public void setExpressionStyle(PolynomialExpression.ExpressionStyle expressionStyle) {
        this.expressionStyle = expressionStyle;
    }

    public PolynomialExpression.ExpressionStyle getExpressionStyle() {
        return (this.expressionStyle);
    }
/**
 * Return the *default* set of variables that can be randomly selected when creating an expression.
 * @return variablePool The set of variables.
 */
    public static String getDefaultVariablePool() {
        return variablePoolDefault;
    }
/**
 * Establish the set of variables that can be randomly selected when creating an expression.
 * @param variablePool The set of variables.
 * @throws java.lang.Exception
 */
    public void setVariablePool(String variablePool) throws Exception {
        if (variablePool.isEmpty()) {
            throw new Exception("PolynomialConfig.setVariablePool(): variablePool is empty");
        }
        this.variablePool = variablePool;
    }
/**
 * Return the set of variables that can be randomly selected when creating an expression.
 * @returnvariablePool The set of variables.
 */
    public String getVariablePool() {
        return variablePool;
    }
/**
 * Establish the maximum number of variables that the polynomial expression can have.
 * @param maxNumberOfVariables the maximum number of variables in the polynomial expression.
 * @return the maximum number of variables (the value that was passed to the method).
 */
    public int setMaxNumberOfVariables(int maxNumberOfVariables) {
        this.maxNumberOfVariables = maxNumberOfVariables;
        return maxNumberOfVariables;
    }
/**
 * Establish the minimum number of variables that the polynomial expression can have.
 * @param maxNumberOfVariables the minimum number of variables in the polynomial expression.
 * @return the minimum number of variables (the value that was passed to the method).
 */
    public int setMinNumberOfVariables(int minNumberOfVariables) {
        this.minNumberOfVariables = minNumberOfVariables;
        return minNumberOfVariables;
    }
/**
 * Establish the maximum coefficient that the polynomial expression can have.
 * @param maxCoefficient the maximum coefficient that the polynomial expression can have.
 * @return the maximum coefficient (the value that was passed to the method).
 */
    public int setMaxCoefficient(int maxCoefficient) {
        this.maxCoefficient = maxCoefficient;
        return maxCoefficient;
    }
/**
 * Establish the minimum coefficient that the polynomial expression can have.
 * @param minCoefficient the minimum coefficient that the polynomial expression can have.
 * @return the minimum oefficient (the value that was passed to the method).
 */
    public int setMinCoefficient(int minCoefficient) {
        this.minCoefficient = minCoefficient;
        return minCoefficient;
    }

/**
 * Read the maximum number of variables that the polynomial expression can have.
 * @return the maximum number of variables (the value that was passed to the method).
 */
    public int getMaxNumberOfVariables() {
        return maxNumberOfVariables;
    }
/**
 * Read the minimum number of variables that the polynomial expression can have.
 * @param maxNumberOfVariables the minimum number of variables in the polynomial expression.
 * @return the minimum number of variables (the value that was passed to the method).
 */
    public int getMinNumberOfVariables() {
        return minNumberOfVariables;
    }
/**
 * Read the maximum coefficient that the polynomial expression can have.
 * @param maxCoefficient the maximum coefficient that the polynomial expression can have.
 * @return the maximum coefficient (the value that was passed to the method).
 */
    public int getMaxCoefficient() {
        return maxCoefficient;
    }
/**
 * Read the minimum coefficient that the polynomial expression can have.
 * @param minCoefficient the minimum coefficient that the polynomial expression can have.
 * @return the minimum oefficient (the value that was passed to the method).
 */
    public int getMinCoefficient() {
        return minCoefficient;
    }
/**
 * Read the maximum variables per Atom that the polynomial expression can have.
 * @param maxCoefficient the maximum coefficient that the polynomial expression can have.
 * @return the maximum coefficient (the value that was passed to the method).
 */
    public int getMaxVariablesPerAtom() {
        return maxVariablesPerAtom;
    }
/**
 * Read the minimum variables per Atom  that the polynomial expression can have.
 * @param minCoefficient the minimum coefficient that the polynomial expression can have.
 * @return the minimum oefficient (the value that was passed to the method).
 */
    public int getMinVariablesPerAtom() {
        return minVariablesPerAtom;
    }

/**
 * Return the setting of the forceFactorable flag.
 * @return the value of the forceFactorable flag.
 */
    public boolean getForceFactorable() {
        return forceFactorable;
    }
/**
 * Set the value of the forceFactorable flag.
 * @param forceFactorable the desired value of the forceFactorable flag.
 * @return the value of the forceFactorable flag.
 */
    public boolean setForceFactorable(boolean forceFactorable) {
        this.forceFactorable = forceFactorable;
        return forceFactorable;
    }
/**
 * Read the maximum variables per Atom that the polynomial expression can have.
 * @param maxCoefficient the maximum coefficient that the polynomial expression can have.
 * @return the maximum coefficient (the value that was passed to the method).
 */
    public int getMaxFactorsPerExpression() {
        return maxFactorsPerExpression;
    }
/**
 * Read the minimum variables per Atom  that the polynomial expression can have.
 * @param minCoefficient the minimum coefficient that the polynomial expression can have.
 * @return the minimum oefficient (the value that was passed to the method).
 */
    public int getMinFactorsPerExpression() {
        return minFactorsPerExpression;
    }
/**
 * Establish the maximum number of elements that can be in a free-form polynomial expression.
 * @param maxFactorsPerExpression the maximum number of elements in the polynomial expression.
 * @return the maximum number of variables (the value that was passed to the method).
 */
    public int setMaxFactorsPerExpression(int maxFactorsPerExpression) {
        this.maxFactorsPerExpression = maxFactorsPerExpression;
        return maxFactorsPerExpression;
    }
/**
 * Establish the minimum number of elements that can be in a free-form polynomial expression.
 * @param minFactorsPerExpression the minimum number of elements in the polynomial expression.
 * @return the minimum number of variables (the value that was passed to the method).
 */
    public int setMinFactorsPerExpression(int minFactorsPerExpression) {
        this.minFactorsPerExpression = minFactorsPerExpression;
        return minFactorsPerExpression;
    }
/**
 * Establish the maximum number of variables that can be in an atom.
 * @param maxVariablesPerAtom the maximum number of variables that can be in an atom.
 * @return the maximum number of variables that can be in an atom.
 */
    public int setMaxVariablesPerAtom(int maxVariablesPerAtom) {
        this.maxVariablesPerAtom = maxVariablesPerAtom;
        return maxVariablesPerAtom;
    }
/**
 * Establish the minimum number of variables that can be in an atom.
 * @param minVariablesPerAtom the minimum number of variables that can be in an atom.
 * @return the minimum number of variables that can be in an atom.
 */
    public int setMinVariablesPerAtom(int MinVariablesPerAtom) {
        this.minVariablesPerAtom = MinVariablesPerAtom;
        return MinVariablesPerAtom;
    }

    public int setMaxExponent(int maxExponent) {
        this.maxExponent = maxExponent;
        return maxExponent;
    }
    public int setMinExponent(int minExponent) {
        this.minExponent = minExponent;
        return minExponent;
    }
    public int getMaxExponent() {
        return maxExponent;
    }
    public int getMinExponent() {
        return minExponent;
    }
}
