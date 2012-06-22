/**
 * This class models a polynomial expression generator.
 */
package PolynomialExpressionGenerator;

import java.util.Stack;       // Get the stack class. The silly namespace hierarchy is case-sensitive.
//import java.util.Random;

/**
 *
 * @author Nicholson.Bill
 */
public class PolynomialExpression {

    public static enum ExpressionStyle {
        singleExpression, twoByTwo, oneByTwo, freeForm, binomial, trinomial, quadratic
    };
    private static String arithmeticSymbolPool = "+-*";
    private static String additiveSymbolPool = "+-";
    private PolynomialConfig pc;
    private Stack numeratorStack,  denominatorStack;
    //ExpressionStyle expressionStyle;      // moved to PolynomialConfig
    /**
     * The polynomial expression that needs to be solved by the user
     */
    private Expression expression;
//  private Expression solution;        // Not used. Call the Solve() method instead

    /**
     * Constructor with no arguments
     */
    public PolynomialExpression() {
        this.pc = new PolynomialConfig();
        init();
        setDefaults();
    }

    /**
     * Constructor
     * @param pc The configuration object to be used by the PolynomialExpression Object
     */
    public PolynomialExpression(PolynomialConfig polynomialConfig) {
        this.pc = polynomialConfig;     // todo: make this a clone operation.
        init();
    }

    /**
     * Reorder the elements in a polynomial.
     * Be careful: the order of operations MUST be all at the same level of precedence.
     * Throws exception if a problem is found during the processing.
     * @return The new sorted polynomial.
     */
    public PolynomialExpression reorder() throws Exception {
        PolynomialExpression p = this.clone();
        PolynomialExpression result = this.clone();
        result.numeratorStack.clear();
        Atom tmpAtom = new Atom();
        Element e;
        Object o;
        int maxExponent = Integer.MIN_VALUE, tmpExponent = 0, elementIdx = -1;
        String maxVariable = "", tmpVariable = "", operatorSign = "+";
        int i;
        try {
            while (p.numeratorStack.isEmpty() == false) {
                elementIdx = -1;
                maxExponent = Integer.MIN_VALUE;
                tmpExponent = 0;
                maxVariable = "";
                tmpVariable = "";
//              Find the element with the highest exponent.
                for (i = 0; i < p.numeratorStack.size(); i++) {
                    o = p.numeratorStack.elementAt(i);
                    try {
                        e = (Element) p.numeratorStack.elementAt(i);
                        if (e.getMaxExponent(tmpAtom) == true) {
                            if (maxExponent < tmpAtom.getExponent()) {
                                maxExponent = tmpAtom.getExponent();
                                maxVariable = tmpAtom.getVariable();
                                elementIdx = i;
                            }
                        }
//                      Capture the + or - that might be preceeding the element.
                        operatorSign = (i > 0) ? (String) p.numeratorStack.elementAt(i - 1) : "+";
                        if (!(operatorSign.contentEquals("+") || operatorSign.contentEquals("-"))) {
                            operatorSign = "+";
                        }
                    } catch (Exception ex) {
//                  If we get here, then the thing on the numerator stack
//                      could not be converted to an element. 
//                      No worries; just keep looking through the stack.
                    }
                }
//          At this point we should have the index of the element with the highest exponent.
                //System.out.println("op sign: "  + operatorSign.contentEquals("-"));
                //System.out.println("Stack size:  " + result.numeratorStack.size());
                if ((operatorSign.contentEquals("-")) || (result.numeratorStack.size() > 0)) {
                    result.numeratorStack.push(operatorSign);
                }
                //Util.Debug("PolynomialExpression reorder(): element with highest exponent = " + operatorSign + " " +  ((Element) p.numeratorStack.elementAt(elementIdx)).toString());
                result.numeratorStack.push((Element) p.numeratorStack.elementAt(elementIdx));
                if (elementIdx > 0) {
                    p.numeratorStack.remove(elementIdx - 1); // Remove the operator preceeding the operand.
                    p.numeratorStack.remove(elementIdx - 1); // Remove the operator. It's in a different index now.
                } else {
                    p.numeratorStack.remove(elementIdx); // Remove the operator
                }
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.reorder(): " + ex.getMessage());
            throw new Exception("PolynomialExpression.reorder(): " + ex.getMessage());
        }
        return result;
    }

    /**
     * Parse a string into a PolynomialExpression object.
     * This is a little dicey because the configuration object may not match.
     * Hopefully it's delimited by spaces so split(" ") works properly.
     *
     * @param exp The string to be processed.
     * @return The new polynomial expression.
     */
    public static PolynomialExpression fromString(String exp) {
        int i;
        String tmp;
        PolynomialExpression p = new PolynomialExpression();       // = this.clone();
        try {
            p.numeratorStack.clear();
            p.denominatorStack.clear();
            Element e;
            String list[] = exp.split(" ");
            for (i = 0; i < list.length; i++) {
                tmp = list[i].trim();
                if (!tmp.isEmpty()) {
                    if (list[i].trim().contentEquals("*")) {
                        p.PushOntoNumeratorStack("*");
                    } else if (list[i].trim().contentEquals("+")) {
                        p.PushOntoNumeratorStack("+");
                    } else if (list[i].trim().contentEquals("-")) {
                        p.PushOntoNumeratorStack("-");
                    } else if (list[i].trim().contentEquals(")")) {
                        p.PushOntoNumeratorStack(")");
                    } else if (list[i].trim().contentEquals("(")) {
                        p.PushOntoNumeratorStack("(");
                    } else {
//                      It's an element.
                        e = new Element();
                        e.fromString(list[i]);
                        p.PushOntoNumeratorStack(e);
                    }
                }
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.fromString(): " + ex.getMessage());
        }
        return p;
    }

    /**
     * Validate a simplified polymonial that the user has typed in.
     * This should be called when the user has asked for an expanded polymonial and
     *  then typed in the simplified version.
     * @param answer
     * @return true if the answer is correct.
     */
    public boolean ValidateSimplifiedPolymonial(String answer) {
        boolean result = true;
        String tmp;
        tmp = this.Simplify().toString().trim();
        if (tmp.contentEquals(answer.trim())) {
            result = true;

        }
        return result;
    }

    /**
     * Validate an expanded polymonial that the user has typed in.
     * This should be called when the user has asked for a simplified polymonial and
     *  then typed in the expanded version.
     * @param answer
     * @return true if the answer is correct.
     */
    public boolean ValidateExpandedPolymonial(String answer) {
        boolean result = false;
        String tmp;
        PolynomialExpression p = this.fromString(answer);
        tmp = p.Simplify().toString().trim();
        if (pc.getRemoveMultiplicationSymbols() == true) {
            tmp = tmp.replace("*", " ");
        }
        if (tmp.contentEquals(answer.trim())) {
            result = true;
        }
        return result;
    }

    public int setMaxNumberOfVariables(int maxNumberOfVariables) {
        pc.setMaxNumberOfVariables(maxNumberOfVariables);
        return maxNumberOfVariables;
    }

    public int setMinNumberOfVariables(int minNumberOfVariables) {
        pc.setMaxNumberOfVariables(minNumberOfVariables);
        return minNumberOfVariables;
    }

    public boolean setForceFactorable(boolean forceFactorable) {
        pc.setForceFactorable(forceFactorable);
        return forceFactorable;
    }

    private void init() {
        try {
            expression = new Expression();
            numeratorStack = new Stack();
            denominatorStack = new Stack();
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.setDefaults: " + ex.getMessage());
        }
    }

    private void setDefaults() {
        try {
            pc.setMaxFactorsPerExpression(5);
            pc.setMinFactorsPerExpression(2);
            pc.setMaxCoefficient(8);
            pc.setMinCoefficient(1);
            pc.setMaxExponent(4);
            pc.setMinExponent(1);
            pc.setMaxVariablesPerAtom(3);
            pc.setMinVariablesPerAtom(1);
            pc.setVariablePool(pc.getDefaultVariablePool());
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.setDefaults: " + ex.getMessage());
        }
    }

    public void setExpressionStyle(PolynomialExpression.ExpressionStyle expressionStyle) {
        pc.setExpressionStyle(expressionStyle);
    }

    public PolynomialExpression.ExpressionStyle getExpressionStyle() {
        return pc.getExpressionStyle();
    }

    public void PushOntoNumeratorStack(Object o) {
        numeratorStack.push(o);
    }

    public void PushOntoDenomimatorStack(Object o) {
        denominatorStack.push(o);
    }

    public PolynomialExpression clone() {
        PolynomialExpression p = new PolynomialExpression();
        p.pc = this.pc.clone();
//      p.expression = this.expression.clone();
        p.numeratorStack = (Stack) this.numeratorStack.clone();
        p.denominatorStack = (Stack) this.denominatorStack.clone();
        return p;
    }

    public void setVariablePool(String variablePool) {
        try {
            pc.setVariablePool(variablePool);
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.setVariablePool: " + ex.getMessage());
        }
    }

    public void setMaxFactorsPerExpression(int maxFactorsPerExpression) {
        pc.setMaxFactorsPerExpression(maxFactorsPerExpression);
    }

    public void setMinFactorsPerExpression(int minFactorsPerExpression) {
        pc.setMinFactorsPerExpression(minFactorsPerExpression);
    }

    public void setMaxCoefficient(int maxCoefficient) {
        pc.setMaxCoefficient(maxCoefficient);
    }

    public void setMinCoefficient(int minCoefficient) {
        pc.setMinCoefficient(minCoefficient);
    }

    public void setMaxVariablesPerAtom(int maxVariablesPerAtom) {
        pc.setMaxVariablesPerAtom(maxVariablesPerAtom);
    }

    public void setMinVariablesPerAtom(int minVariablesPerAtom) {
        pc.setMinVariablesPerAtom(minVariablesPerAtom);
    }

    public void setMinExponent(int minExponent) {
        pc.setMinExponent(minExponent);
    }

    public void setMaxExponent(int maxExponent) {
        pc.setMaxExponent(maxExponent);
    }

    /**
     * Generate a new expression and return it.
     */
    public boolean Create(ExpressionStyle expressionStyle) throws Exception {
        boolean result = true;
        String tmp = "";
        String arithmeticSymbol = "";
        int i, k, idx, numFactors;
        numeratorStack.clear();
        pc.setExpressionStyle(expressionStyle);
        try {
            switch (expressionStyle) {
//				ToDo: This code is duplicated in PolymonialExpressionGenerator, except with atoms not elememts, and I don't know why. 
                case quadratic: // ax^2 + bx + c
                    Stack e1Atoms = new Stack();
                    Stack e2Atoms = new Stack();
                    Stack e3Atoms = new Stack();
                    
                    Stack e1ExponentStack = new Stack(), e1VariableStack = new Stack(), e1CoefficientStack = new Stack();
                    Stack e2ExponentStack = new Stack(), e2VariableStack = new Stack(), e2CoefficientStack = new Stack();
                    Stack e3ExponentStack = new Stack(), e3VariableStack = new Stack(), e3CoefficientStack = new Stack();
                    e1CoefficientStack.push(getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient()));
                    e2CoefficientStack.push(getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient()));
                    e3CoefficientStack.push(getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient()));
                    
                    e1VariableStack.push(pc.getVariablePool().substring(0, 1));
                    e2VariableStack.push(pc.getVariablePool().substring(0, 1));
                    e3VariableStack.push("");

                    e1ExponentStack.push(2);
                    e2ExponentStack.push(1);
                    e3ExponentStack.push(1);
                    
                    e1Atoms.push(new Atom(e1CoefficientStack, e1VariableStack, e1ExponentStack));
                    e2Atoms.push(new Atom(e2CoefficientStack, e2VariableStack, e2ExponentStack));
                    e3Atoms.push(new Atom(e3CoefficientStack, e3VariableStack, e3ExponentStack));
                    
                    Element e1 = new Element(e1Atoms);
                    Element e2 = new Element(e2Atoms);
                    Element e3 = new Element(e3Atoms);
                    
            		numeratorStack.push(e1);
                    numeratorStack.push("+");
                    numeratorStack.push(e2);
                    numeratorStack.push("+");
                    numeratorStack.push(e3);                

                    break;

                case singleExpression:  // a
                    numeratorStack.push(CreateElement());
                    break;

                case twoByTwo:  // (a + b) * (c + d)
                    result = createTwoByTwo(numeratorStack);
                    break;

                case oneByTwo:  // a*(b + c)
                    numeratorStack.push(CreateElement());
                    numeratorStack.push("*");
                    numeratorStack.push("(");
                    numeratorStack.push(CreateElement());
                    idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                    arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
                    numeratorStack.push(" " + arithmeticSymbol + " ");
                    numeratorStack.push(CreateElement());
                    numeratorStack.push(")");
                    break;

                case binomial:  // a + b
                    numeratorStack.push(CreateElement());
                    idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                    arithmeticSymbol = additiveSymbolPool.substring(idx, idx + 1);
                    numeratorStack.push(" " + arithmeticSymbol + " ");
                    numeratorStack.push(CreateElement());
                    break;

                case trinomial:  // a + b + c
                    if (pc.getForceFactorable()) {
//                      The poly has to be factorable to (a + b) * c + d).
//                      Start with a 2 by 2 poly and simplify it.
                        result = createTwoByTwoReducableToTrinomial(numeratorStack);
                        if (result) {
                            this.pc.setExpressionStyle(ExpressionStyle.twoByTwo);
                            this.Simplify();
                            this.pc.setExpressionStyle(ExpressionStyle.trinomial);
                        } else {
                            throw new Exception("PolynomialExpression.Create(): Unable to create a two by two ");
                        }
                    } else {
                        numeratorStack.push(CreateElement());
                        idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                        arithmeticSymbol = additiveSymbolPool.substring(idx, idx + 1);
                        numeratorStack.push(" " + arithmeticSymbol + " ");
                        numeratorStack.push(CreateElement());
                        idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
                        arithmeticSymbol = additiveSymbolPool.substring(idx, idx + 1);
                        numeratorStack.push(" " + arithmeticSymbol + " ");
                        numeratorStack.push(CreateElement());
                    }
                    break;

                case freeForm:  // anything
                    numFactors = getRandomNumber(pc.getMinFactorsPerExpression(), pc.getMaxFactorsPerExpression());
                    for (i = 0; i < numFactors; i++) {
                        numeratorStack.push(" " + arithmeticSymbol + " ");
                        numeratorStack.push(CreateElement());
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
        String result = "", tmp, buff;
        Object obj;
        Class myClass;
        Element e;
        int i;
        try {
            for (i = 0; i < st.size(); i++) {
                obj = st.elementAt(i);
                myClass = obj.getClass();
                //System.out.println(myClass.getName());
                if (myClass.getName().contentEquals("java.lang.String")) {
                    tmp = obj.toString();
                    //System.out.println("tmp = <" + tmp +">");
                    buff = result + " " + tmp.trim();
                    //System.out.println("buff = <" + buff +">");
                    result = buff;
                } else {
                    e = (Element) obj;
                    tmp = e.Build().trim();
                    //System.out.println("1 tmp = <" + tmp +">");
                    buff = result + " " + tmp.trim();
                    //System.out.println("1 buff = <" + buff +">");
                    result = buff;
                }
            }
        } catch (Exception ex) {
            Util.LogError("BuildFromStack: " + ex.getMessage());
        }
        return result.trim();
    }
/**
 * Create an element that has one simple atom in it.
 * The three components of the atom can be overridden in the arguments.
 * @param coefficient The coefficient of the variable in the atom.
 * @param variable The variable name in the atom.
 * @param exponent The exponent of the variable in the atom.
 * @return the Element that was created.
 * @throws java.lang.Exception
 */
    public Element CreateElement(int coefficient, String variable, int exponent) throws Exception {
        Atom a;
        Element e;
        Stack Atoms = new Stack();
        try {
            a = new Atom(coefficient, variable, exponent);
            Atoms.push(a);
        } catch (Exception ex) {
            Util.LogError("PolynomialExpressionGenerator.CreateElement: " + ex.getMessage());
            throw new Exception("PolynomialExpressionGenerator.CreateElement: " + ex.getMessage());
        }
        e = new Element(Atoms);
        return e;
    }

    /**
     * Create a single element that will be part of a polynomial expression.
     * The element is composed of atoms.
     * @return the element that was created.
     */
    public Element CreateElement() throws Exception {
        String factor = "", variable;
        int i, k, idx, variableCount, coefficient, exponent;
        Atom a;
        Element e;
        Stack Atoms = new Stack();
        try {
            variableCount = getRandomNumber(pc.getMinVariablesPerAtom(), pc.getMaxVariablesPerAtom());
            for (i = 0; i < variableCount; i++) {
                coefficient = getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient());
                idx = getRandomNumber(0, pc.getVariablePool().length() - 1);
                variable = pc.getVariablePool().substring(idx, idx + 1);
                exponent = getRandomNumber(pc.getMinExponent(), pc.getMaxExponent());
                a = new Atom(coefficient, variable, exponent);
                Atoms.push(a);
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpressionGenerator.CreateElement: " + ex.getMessage());
            throw new Exception("PolynomialExpressionGenerator.CreateElement: " + ex.getMessage());
        }
        e = new Element(Atoms);
        return e;
    }

    /**
     *
     * @return The expression stored in the object. If there isn't one, generate one.
     */
    public Expression getExpression() {
        expression.setNumerator(BuildFromStack(numeratorStack));
//        expression.setDenominator(BuildFromStack(denominatorStack));
        return expression;
    }

    public String toString() {
        String tmp = "";
        tmp = getExpression().getNumerator();
        return tmp;
    }

    /**
     * Calculate the simplest form for the polynomial expression.
     * @return The simplest form for the polynomial expression.
     */
    public PolynomialExpression Simplify() {
        PolynomialExpression solution = (PolynomialExpression) this.clone();
        Stack stackTmp = (Stack) numeratorStack.clone();
        Element ea, eb, ec, ed, er1, er2, er3, er4, eResult, eResult1;
        String sign;
        try {
            switch (pc.getExpressionStyle()) {
                case quadratic:
//						ToDo: Add this logic
                    break;

                case singleExpression:  // a
//                  Nothing to do except simplify the Elements.
                    int i;
                    solution.numeratorStack.clear();
                    for (i = 0; i < stackTmp.size(); i++) {
                        ea = (Element) stackTmp.elementAt(i);
                        ea = ea.Simplify();
                        solution.numeratorStack.push(ea);
                    }
                    break;
//                                          0 1 2 3 4 5 6 7 8 9 0
                case twoByTwo:           // ( a + b ) * ( c + d )
//                  FOIL: First / Outer / Inner Last
                    ea = (Element) stackTmp.elementAt(1);
                    eb = (Element) stackTmp.elementAt(3);
                    sign = (String) (stackTmp.elementAt(2));
                    if (sign.trim().contentEquals("-")) {
                        eb.NegateCoefficient();
                    }
                    ec = (Element) stackTmp.elementAt(7);
                    ed = (Element) stackTmp.elementAt(9);
                    sign = (String) stackTmp.elementAt(8);
                    if (sign.trim().contentEquals("-")) {
                        ed.NegateCoefficient();
                    }
                    er1 = ea.Multiply(ec);      // First
                    er2 = eb.Multiply(ec);      // Inner
                    er3 = ea.Multiply(ed);      // Outer
                    er4 = eb.Multiply(ed);      // Last
                    solution.numeratorStack.clear();
                    solution.numeratorStack.push(er1.Simplify());
                    AdjustSignAndPush(solution.numeratorStack, "+", er2.Simplify());
                    AdjustSignAndPush(solution.numeratorStack, "+", er3.Simplify());
                    AdjustSignAndPush(solution.numeratorStack, "+", er4.Simplify());
                    break;

//                                         0 1 2 3 4 5
                case oneByTwo:          // a * ( b + c )
//                  Calculate a * b and a * c
                    //System.out.println("1>" + stackTmp.elementAt(0).toString() + "<");
                    //System.out.println("2>" + stackTmp.elementAt(1).toString() + "<");
                    //System.out.println("3>" + stackTmp.elementAt(2).toString() + "<");
                    //System.out.println("4>" + stackTmp.elementAt(3).toString() + "<");
                    ea = (Element) stackTmp.elementAt(0);
                    //System.out.println(">" + stackTmp.elementAt(3).toString() + "<");
                    eb = (Element) stackTmp.elementAt(3);
                    ec = (Element) stackTmp.elementAt(5);
                    er1 = ea.Multiply(eb);
                    sign = (String) stackTmp.elementAt(4);
                    //System.out.println("**>" + sign + "<**");
                    if (sign.trim().contentEquals("-")) {
                        ec.NegateCoefficient();
                    }
                    er2 = ea.Multiply(ec);
                    solution.numeratorStack.clear();
                    solution.numeratorStack.push(er1.Simplify());
                    er2 = er2.Simplify();
                    if (((Atom) (er2.getAtoms().elementAt(0))).getCoefficient() < 0) {
//                      We do this so we don't end up with (a + -b). Instead we want (a - b)
                        ((Atom) (er2.getAtoms().elementAt(0))).invertCoefficient();
                        solution.numeratorStack.push("-");
                    } else {
                        solution.numeratorStack.push("+");
                    }
                    solution.numeratorStack.push(er2);
                    break;

//                                 0 1 2
                case binomial:  // a + b
                    eResult = new Element();
                    ea = (Element) stackTmp.elementAt(0);
                    eb = (Element) stackTmp.elementAt(2);
//                  We might be able to combine like terms.
                    System.out.println("ea = " + ea.toString());
                    System.out.println("(" + stackTmp.elementAt(1).toString() + ")");
                    System.out.println("eb = " + eb.toString());
                    if (CombineLikeTerms("+", ea, (String) stackTmp.elementAt(1), eb, eResult) == true) {
//                      The terms were able to be combined. Put the result in the solution object.
                        solution.numeratorStack.clear();
                        AdjustSignAndPushSingleElement(solution.numeratorStack, eResult);
                    }
                    break;
//                                 0 1 2 3 4
                case trinomial: // a + b + c
                    eResult = new Element();
                    ea = (Element) stackTmp.elementAt(0);
                    eb = (Element) stackTmp.elementAt(2);
                    ec = (Element) stackTmp.elementAt(4);
//                  We need to check a+b, a+c, b+c
                    if (CombineLikeTerms("+", ea, (String) stackTmp.elementAt(1), eb, eResult) == true) {
//                      a+b were combinable. Try a + b + c
                        eResult1 = new Element();
                        if (CombineLikeTerms("+", eResult, (String) stackTmp.elementAt(3), ec, eResult1) == true) {
//                          a+b+c all combined into one term
                            System.out.println("PolynomialExpression.Simplify(): a+b+c all combined into one term.");
                            solution.numeratorStack.clear();
                            push(solution.numeratorStack, "", eResult1);
                        } else { // a+b combined, c did not.
                            System.out.println("PolynomialExpression.Simplify(): a+b combined, c did not.");
                            solution.numeratorStack.clear();
                            push(solution.numeratorStack, "", eResult);
                            AdjustSignAndPush(solution.numeratorStack, (String) stackTmp.elementAt(3), ec);
                        }
                    } else {
//                      try b+c
                        if (CombineLikeTerms((String) stackTmp.elementAt(1), eb, (String) stackTmp.elementAt(3), ec, eResult) == true) {
                            // b+c combined, a did not.
                            System.out.println("PolynomialExpression.Simplify(): b+c combined, a did not.");
                            solution.numeratorStack.clear();
                            push(solution.numeratorStack, "", ea);
//                            solution.numeratorStack.push((String) stackTmp.elementAt(1));
                            AdjustSignAndPushSingleElement(solution.numeratorStack, eResult);
                        } else {
//                          try a+c
                            if (CombineLikeTerms("+", ea, (String) stackTmp.elementAt(3), ec, eResult) == true) {
                                // a+c combined, b did not.
                                System.out.println("PolynomialExpression.Simplify(): a+c combined, b did not.");
                                solution.numeratorStack.clear();
                                push(solution.numeratorStack, "", eResult);
//                              solution.numeratorStack.push((String) stackTmp.elementAt(1));
                                AdjustSignAndPush(solution.numeratorStack, (String) stackTmp.elementAt(1), eb);
                            }
                        }
                    }
                    break;

                case freeForm:

                    break;
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.Simplify(): " + ex.getMessage());
            solution.expression.setNumerator("Error");
            solution.expression.setDenominator("Error");
        }
        return solution;
    }

    /**
     * If the (first and only) coefficient of the element is non-zero,
     *    push it on the stack.
     * @param s The stack to be pushed onto.
     * @param e The element to be pushed.
     */
    private void push(Stack s, String operation, Element e) {
        if (e.getFirstCoefficient() != 0) {
            if (!operation.isEmpty()) {
                s.push(operation);
            }
            s.push(e);
        }
    }

    /**
     * This only works if the element has been simplified and the coefficient is in the first atom.
     * @param s
     * @param sign
     * @param e
     */
    private void AdjustSignAndPushSingleElement(Stack s, Element e) {
        try {
            int signOfElement = ((Atom) (e.getAtoms().elementAt(0))).getCoefficient();
            Util.Debug("Sign of SignOfElement = " + (signOfElement > 0));
            if (signOfElement >= 0) {
                push(s, "+", e);
            } else {
//              Sign is negative. Push a minus sign and force the coefficient to be positive.
                Element elementTmp = e.clone();
                Atom atomTmp = ((Atom) (elementTmp.getAtoms().elementAt(0)));
                atomTmp.setCoefficient(Math.abs(atomTmp.getCoefficient()));
                push(s, "-", elementTmp);
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.AdjustSignAndPush()" + ex.getMessage());
        }
    }

    private void AdjustSignAndPush(Stack s, String sign, Element e) {
        try {
            int signOfElement = ((Atom) (e.getAtoms().elementAt(0))).getCoefficient();
            Util.Debug("Sign of SignOfElement = " + (signOfElement > 0));
            Util.Debug("sign = *" + sign + "*");
            Util.Debug("sign.contentEquals() = " + sign.trim().contentEquals("+"));
            if (((signOfElement < 0) && sign.trim().contentEquals("-")) ||
                    ((signOfElement > 0) && sign.trim().contentEquals("+"))) {
                push(s, "+", e);
            } else {
//          Signs are different. Push a minus sign and force the coefficient to be positive.
                Element elementTmp = e.clone();
                Atom atomTmp = ((Atom) (elementTmp.getAtoms().elementAt(0)));
                atomTmp.setCoefficient(Math.abs(atomTmp.getCoefficient()));
                push(s, "-", elementTmp);
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.AdjustSignAndPush()" + ex.getMessage());
        }
    }

    /**
     * Combine like terms in an additve polynomial expression.
     * The expressions must have already been through Simplify().
     * @param ea The first element to be combined
     * @param eb The second element to be combined.
     * @param CombinationOperator  The way the operands are being combined: + or -.
     * @param eResult The result of the combination, if the combination was possible.
     * @return True if the terms were able to be combined.
     */
    private boolean CombineLikeTerms(String combinationOperatorA, Element ea, String combinationOperatorB, Element eb, Element eResult) {
        int i;
        boolean result = true;      // Hope for the best.
        int coefficient1, coefficient2;
//      String variableList1 = "", variableList2 = "";
        Atom atomTmp, atomea, atomeb;
        Stack eaAtoms = ea.getAtoms(), ebAtoms = eb.getAtoms();
        try {
            atomTmp = (Atom) (eaAtoms.elementAt(0));
            coefficient1 = atomTmp.getCoefficient();
            if (combinationOperatorA.trim().contentEquals("-")) {
                coefficient1 = 0 - coefficient1;

            }
            atomTmp = (Atom) (ebAtoms.elementAt(0));
            coefficient2 = atomTmp.getCoefficient();
            if (combinationOperatorB.trim().contentEquals("-")) {
                coefficient2 = 0 - coefficient2;
//          Compose the variable lists to see if the terms can be combined.

            }
            for (i = 0; i < eaAtoms.size(); i++) {
                atomea = (Atom) (eaAtoms.elementAt(i));
                atomeb = (Atom) (ebAtoms.elementAt(i));
                if ((atomea.getExponent() != atomeb.getExponent()) ||
                        (atomea.getVariable().contentEquals(atomeb.getVariable()) == false)) {
                    result = false;
                    break;
                }
            }
            if (result == true) {
//              If we get this far, the elements can be combined.
                for (i = 0; i < eaAtoms.size(); i++) {
                    atomea = (Atom) (eaAtoms.elementAt(i));
                    if (i == 0) {
                        atomea.setCoefficient(coefficient1 + coefficient2);
                    //System.out.println("CombineLikeTerms: " + coefficient1 + " + " + coefficient2);
                    }
                    eResult.addAtom(atomea);
                }
            }
        } catch (Exception ex) {
            Util.LogError("PolynomialExpression.CombineLikeTerms(): " + ex.getMessage());
            result = false;
        }
        return result;
    }

    /**
     * Calculate a random integer within a specific range.
     * @param lowerLimit
     * @param upperLimit
     * @return The desired random number.
     */
    private int getRandomNumber(int lowerLimit, int upperLimit) {
        int randomNumber, range;
        range = Math.abs(upperLimit - lowerLimit);
        //while (true) {
        randomNumber = pc.getRandom().nextInt(range + 1);
        randomNumber += lowerLimit;
        //}
        return randomNumber;
    }

    /**
     * Establish a seed for the random number generator.
     * This affects the entire program because the random number generator is a singleton.
     * @param seed The value to be used as the seed.
     */
    public void setRandomSeed(long seed) {
        pc.getRandom().setSeed(seed);
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

    /**
     * Create a 2 by 2 polynomial expression.
     * @param numeratorStack
     * @return
     */
    private boolean createTwoByTwo(Stack numeratorStack) {
        boolean result = true;
        String tmp = "";
        String arithmeticSymbol = "";
        int i, k, idx, numFactors;
        numeratorStack.clear();
        try {
            numeratorStack.push("(");
            numeratorStack.push(CreateElement());
            idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
            arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
            numeratorStack.push(arithmeticSymbol);
            numeratorStack.push(CreateElement());
            numeratorStack.push(")");
            numeratorStack.push("*");
            numeratorStack.push("(");
            numeratorStack.push(CreateElement());
            idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
            arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
            numeratorStack.push(arithmeticSymbol);
            numeratorStack.push(CreateElement());
            numeratorStack.push(")");
            expression.setNumerator(tmp.trim());
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    /**
     * The 2 by 2 that is created will be in the form (x + a) * (x + b)
     *  where a and b are constants, and x is a single variable that may or may not have a
     * coefficient or an non-one exponent.
     * @param numeratorStack
     * @return
     */
    private boolean createTwoByTwoReducableToTrinomial(Stack numeratorStack) {
        boolean result = true;
        String tmp = "";
        String arithmeticSymbol = "", variable;
        int i, k, idx, numFactors, coefficient, exponent;
        numeratorStack.clear();
        try {
            numeratorStack.push("(");
//          Assemble the coefficient, variable, and exponent for the element.
            coefficient = getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient());
            idx = getRandomNumber(0, pc.getVariablePool().length() - 1);
            variable = pc.getVariablePool().substring(idx, idx + 1);
//          The most common use of this method will probably use 1 as both min and max exponent.
            exponent = getRandomNumber(pc.getMinExponent(), pc.getMaxExponent());
            numeratorStack.push(CreateElement(coefficient, variable, exponent));

            idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
            arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
            numeratorStack.push(arithmeticSymbol);
            coefficient = getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient());
            Element eNew = CreateElement(coefficient, "", 1);
            numeratorStack.push(eNew);
            numeratorStack.push(")");
            numeratorStack.push("*");
            numeratorStack.push("(");
//          We have to use the same variable that we used above, else the expression
//            will not reduce to a trinomial.
            coefficient = getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient());
//          The most common use of this method will probably use 1 as both min and max exponent.
            exponent = getRandomNumber(pc.getMinExponent(), pc.getMaxExponent());
            numeratorStack.push(CreateElement(coefficient, variable, exponent));
            idx = getRandomNumber(0, additiveSymbolPool.length() - 1);
            arithmeticSymbol = arithmeticSymbolPool.substring(idx, idx + 1);
            numeratorStack.push(arithmeticSymbol);
            coefficient = getRandomNumber(pc.getMinCoefficient(), pc.getMaxCoefficient());
            numeratorStack.push(CreateElement(coefficient, "", 1));
            numeratorStack.push(")");
            expression.setNumerator(tmp.trim());
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }
}
