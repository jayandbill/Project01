/**
 * This class models a polynomial expression generator.
 * 
 * 3/23/12 Added quadratic polynomial to the generator. See CreateExpression()  dwn
 */
package PolynomialExpressionGenerator;

import java.util.Stack;       // Get the stack class. The silly namespace hierarchy is case-sensitive.
import java.util.Random;

/**
 *
 * @author Nicholson.Bill
 */
public class PolynomialExpressionGenerator {

    public static enum ExpressionStyle {
        twoByTwo, oneByTwo, freeForm, quadratic
    };
    private static String variablePool = "xyzpq";
    private static String arithmeticSymbolPool = "+-*";
    private static String additiveSymbolPool = "+-";
    private int maxFactorsPerExpression,  minFactorsPerExpression;
    private int maxVariablesPerAtom, minVariablesPerAtom;
    private int maxCoefficient,  minCoefficient;
    private int maxExponent,  minExponent;
    private PolynomialConfig polynomialConfig;
    Stack numeratorStack, denominatorStack;
    Random random;
    /**
     * The polynomial expression that needs to be solved by the user
     */
    private Expression expression;
//  private Expression solution;        // Not used. Call the Solve() method instead

    /**
     * Constructor with no arguments
     */
    public PolynomialExpressionGenerator() {
        this.polynomialConfig = new PolynomialConfig();
        setDefaults();
    }

    /**
     * Constructor
     * @param polynomialConfig The configuration object to be used by the PolynomialExpressionGenerator Object
     */
    public PolynomialExpressionGenerator(PolynomialConfig polynomialConfig) {
        this.polynomialConfig = polynomialConfig;
        setDefaults();
    }

    private void setDefaults() {
        random = new Random();
        expression = new Expression();
        maxFactorsPerExpression = 5;
        minFactorsPerExpression = 2;
        maxCoefficient = 8;
        minCoefficient = 1;
        maxExponent = 4;
        minExponent = 1;
        numeratorStack = new Stack();
        denominatorStack = new Stack();
        maxVariablesPerAtom = 3;
        minVariablesPerAtom = 1;
    }

    /**
     * Establish the seed for the random number generator.
     * @param seed the seed for the random number generator.
     */
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    /**
     * Generate a new expression and return it.
     */
    public boolean CreateExpression(ExpressionStyle expressionStyle) throws Exception {
        boolean result = true;
        String tmp = "";
        String arithmeticSymbol = "";
        int i, k, idx, numFactors;
        numeratorStack.clear();
        try {
            switch (expressionStyle) {
            	case quadratic:		// ax^2 + bx + c     (e1 + e2 + e3)
                    Stack e1ExponentStack = new Stack(), e1VariableStack = new Stack(), e1CoefficientStack = new Stack();
                    Stack e2ExponentStack = new Stack(), e2VariableStack = new Stack(), e2CoefficientStack = new Stack();
                    Stack e3ExponentStack = new Stack(), e3VariableStack = new Stack(), e3CoefficientStack = new Stack();
                    e1CoefficientStack.push(getRandomNumber(minCoefficient, maxCoefficient));
                    e2CoefficientStack.push(getRandomNumber(minCoefficient, maxCoefficient));
                    e3CoefficientStack.push(getRandomNumber(minCoefficient, maxCoefficient));
                    
                    e1VariableStack.push(variablePool.substring(0, 1));
                    e2VariableStack.push(variablePool.substring(0, 1));
                    e3VariableStack.push("");

                    e1ExponentStack.push(2);
                    e2ExponentStack.push(1);
                    e3ExponentStack.push(1);
                    
            		numeratorStack.push(new Atom(e1CoefficientStack, e1VariableStack, e1ExponentStack));
                    numeratorStack.push("+");
                    numeratorStack.push(new Atom(e2CoefficientStack, e2VariableStack, e2ExponentStack));
                    numeratorStack.push("+");
                    numeratorStack.push(new Atom(e3CoefficientStack, e3VariableStack, e3ExponentStack));                
            		break;

            	case twoByTwo:  // (a + b) * (c + d)
                    numeratorStack.push("(");
                    numeratorStack.push(CreateAtom());
                    idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                    arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
                    numeratorStack.push(" " + arithmeticSymbol + " ");
                    numeratorStack.push(CreateAtom());
                    numeratorStack.push(") * (");
                    numeratorStack.push(CreateAtom());
                    idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                    arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
                    numeratorStack.push(" " + arithmeticSymbol + " ");
                    numeratorStack.push(CreateAtom());
                    numeratorStack.push(")");
                    expression.setNumerator(tmp.trim());
                    break;

                case oneByTwo:  // a*(b + c)
                    numeratorStack.push(CreateAtom());
                    numeratorStack.push(" * (");
                    numeratorStack.push(CreateAtom());
                    idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                    arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
                    numeratorStack.push(" " + arithmeticSymbol + " ");
                    numeratorStack.push(CreateAtom());
                    numeratorStack.push(")");
                    break;

                case freeForm:  // anything
                    numFactors = getRandomNumber(minFactorsPerExpression, maxFactorsPerExpression);
                    for (i = 0; i < numFactors; i++) {
                        numeratorStack.push(" " + arithmeticSymbol + " ");
                        numeratorStack.push(CreateAtom());
                        idx = getRandomNumber(0, arithmeticSymbolPool.length() - 1);
                        arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
                    }

                    break;
            }
//          expression.setNumerator(BuildFromStack(denominatorStack));
            expression.setNumerator(BuildFromStack(numeratorStack));
        } catch (Exception ex) {
            result = false;
            Util.LogError("PolynomialExpressionGenerator.CreateExpression: " + ex.getMessage());
            throw new Exception("PolynomialExpressionGenerator.CreateExpression: " + ex.getMessage());
        }
        return result;
    }

    private String BuildFromStack(Stack st) {
        String result = "";
        Object obj;
        Class myClass;
        Atom a;
        int i;
        try {
            for (i = 0; i < st.size();  i++) {
                obj = st.elementAt(i);
                myClass = obj.getClass();
                //System.out.println(myClass.getName());
                if (myClass.getName().contentEquals("java.lang.String")) {
                    result = result + obj.toString();
                } else {
                    a = (Atom)obj;
                    result = result + a.Build();
                }
            }
        } catch (Exception ex) {
            Util.LogError("BuildFromStack: " + ex.getMessage());
        }
        return result;
    }

    /**
     * Create a single element that will be part of a polynomial expression.
     * @return the element that was created.
     */
    public Atom CreateAtom() throws Exception {
        String factor = "";
        int i, k, idx, variableCount;
        Stack exponentStack = new Stack(), variableStack = new Stack(), coefficientStack = new Stack();
        try {
            variableCount = getRandomNumber(minVariablesPerAtom, maxVariablesPerAtom);
            for (i = 0; i < variableCount; i++) {
                coefficientStack.push(getRandomNumber(minCoefficient, maxCoefficient));
                idx = getRandomNumber(0, variablePool.length() - 1);
                variableStack.push(variablePool.substring(idx, idx + 1));
                exponentStack.push(getRandomNumber(minExponent, maxExponent));
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpressionGenerator.CreateFactor: " + ex.getMessage());
            throw new Exception("PolynomialExpressionGenerator.CreateFactor: " + ex.getMessage());
        }
        Atom atom = new Atom(coefficientStack, variableStack, exponentStack);
        return atom;
    }

    /**
     *
     * @return The expression stored in the object. If there isn't one, generate one.
     */
    public Expression GetExpression() {
        expression.setNumerator(BuildFromStack(numeratorStack));
//        expression.setDenominator(BuildFromStack(denominatorStack));
        return expression;
    }

    /**
     * Calculate the simplest form for the polynomial expression.
     * @return The simplest form for the polynomial expression.
     */
    public Expression Simplify() {
        Expression solution = new Expression();
        Stack stack = new Stack();

        try {
//			todo: write this logic. ;)        	
        } catch (Exception ex) {
            solution.setNumerator("Error");
            solution.setDenominator("Error");
        }

        return solution;
    }

    private int getRandomNumber(int lowerLimit, int upperLimit) {
        int randomNumber;
        while (true) {
            randomNumber = random.nextInt(upperLimit + 1);
            if (randomNumber >= lowerLimit) {
                break;
            }

        }
        return randomNumber;
    }

    /**
     * Test Case 01
     * Creates a simple expression: x/y
     * Call this method, then call Simplify().
     *
     */
    public void Test01() {
        expression.setNumerator("x");
        expression.setDenominator("y");
    }
}
