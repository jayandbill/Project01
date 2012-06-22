// Testing the SVS -- dwn 3/23/12
/*
 * This class models a subdivision of the element class:
 *   a coefficient / variable / exponent combination.
 * For example: 2y^3 or x or p^4 or x
 * 
 */
package PolynomialExpressionGenerator;

import java.lang.Math.*;        // for abs()
import java.util.Stack;

/**
 *
 * @author Nicholson.Bill
 */
public class Atom {

    private int coefficient;
    private String variable;
    private int exponent;

    public Atom(int coefficient, String variable, int exponent) {
        setCoefficient(coefficient);
        setVariable(variable);
        setExponent(exponent);
    }

    public Atom() {
        setCoefficient(1);      // Default to 1
        setExponent(0);         // Default to 0
        setVariable("");        // It's OK to have no variable
    }
/**
 * Create an atom based on the information in the stacks
 * Only works for the first coefficient/exponent/variable in the stack
 * @param coefficientStack
 * @param variableStack
 * @param exponentStack
 */
    Atom(Stack coefficientStack, Stack variableStack, Stack exponentStack) throws Exception {
    	try {
	    	for (int i = 0; i < coefficientStack.size(); i++) {
	    		//String x = (String) coefficientStack.elementAt(i).toString();
	    		//int y = Integer.parseInt(x);
	    		setCoefficient(Integer.parseInt(coefficientStack.elementAt(i).toString()));
	    		setExponent(Integer.parseInt(exponentStack.elementAt(i).toString()));
	    		setVariable((String) variableStack.elementAt(i));
	    	}
	    	if (coefficientStack.size() > 1) {
	    		throw new UnsupportedOperationException("Atom(Stack coefficientStack, Stack variableStack, Stack exponentStack): i cannot be > 1");
	    	}
    	} catch (Exception ex) {throw new Exception("Atom(Stack coefficientStack, Stack variableStack, Stack exponentStack): " + ex.getMessage());}
    }
    

/** 
 * Invert the sign of the coefficient.
 */
    public void invertCoefficient() {
        coefficient = 0 - coefficient;
    }
/** Sort in place. *The stack should already have been through Simplify().*
 *  The sorting is by descending exponent and then by ascending variable name.
 * @param Atoms The stack of Atoms that should be sorted.
 */
    public static void SortAtoms(Stack Atoms) {
        Stack tmpAtoms = (Stack)Atoms.clone();
        Atom tmp;
        int i, j, compareResult;
//      Assume the first coefficient is the only coefficient that could be != 1, and
//       all the other coefficients are exactly 1.
        int coefficientTmp = ((Atom) (tmpAtoms.elementAt(0))).getCoefficient();
        try {
            for (i = 0; i < Atoms.size(); i++) {
                ((Atom)Atoms.elementAt(i)).setCoefficient(1);
                for (j = i + 1; j < Atoms.size(); j++) {
                    compareResult = Atom.CompareAtoms((Atom)Atoms.elementAt(i), (Atom)Atoms.elementAt(j));
                    if (compareResult < 0) {
                        Atom.swap((Atom)Atoms.elementAt(i), (Atom)Atoms.elementAt(j));
                    }
                }
            }
//          Put the coefficient back at the front of the first element.
            ((Atom)Atoms.elementAt(0)).setCoefficient(coefficientTmp);
        } catch (Exception ex) {

        }
    }
    /** Compare 2 atoms
     *
     * @param a1 The first atom to compare
     * @param a2 The second atom to compare
     * @return 1 if atom 1 is greater, -1 if atom2 is greater, 0 if they are equal.
     */
    public static int CompareAtoms(Atom a1, Atom a2) {
        int result = 0;
        if (a1.getExponent() > a2.getExponent()) {
            result = 1;
        } else {
            if (a1.getExponent() < a2.getExponent()) {
                result = -1;
            } else {
//              The exponents are equal, compare the variable names.
                if (a1.getVariable().compareTo(a2.getVariable()) < 0) {
                    result = 1;
                } else {
                    result = -1;
                }
            }
        }
        return result;
    }

    /**
     * Exchange the values in two atoms.
     * This is useful when a list of atoms is being sorted.
     * @param a1
     * @param a2
     */
    public static void swap(Atom a1, Atom a2) {
        int coefficientTmp, exponentTmp;
        String variableTmp;
        coefficientTmp = a1.getCoefficient();
        a1.setCoefficient(a2.getCoefficient());
        a2.setCoefficient(coefficientTmp);

        exponentTmp = a1.getExponent();
        a1.setExponent(a2.getExponent());
        a2.setExponent(exponentTmp);

        variableTmp = a1.getVariable();
        a1.setVariable(a2.getVariable());
        a2.setVariable(variableTmp);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public String getVariable() {
        return variable;
    }

    public int getExponent() {
        return exponent;
    }

    public int setCoefficient(int coefficient) {
        this.coefficient = coefficient;
        return coefficient;
    }

    public String setVariable(String variable) {
        this.variable = variable.trim();
        return variable;
    }

    public int setExponent(int exponent) {
        this.exponent = exponent;
        return exponent;
    }

    /**
     * Generate a string representation of the Atom.
     *
     * @return the string representation of the Atom.
     */
    public String toString() {
        String tmp = "";
        try {
            tmp = Build();
        } catch (Exception ex) {
            System.out.println("Atom.Build(): " + ex.getMessage());
//          throw new Exception("Atom.Build(): " + ex.getMessage());
        }
        return tmp;
    }

    /**
     * Generate a string representation of the Atom.
     * The toString() method calls this.
     * 
     * @return the string representation of the Atom.
     */
    public String Build() throws Exception {
        String tmp = "";
        try {
            switch (coefficient) {
                case -1:
                    tmp = "-";
                    break;
                case 1:
//                  If there's no variable, the coefficient can still be 1
                    if (variable.trim().length() == 0) {
                        tmp = "1";
                    }
                    break;
                default:
                    tmp = String.valueOf(coefficient);
                    break;
            }
            tmp = tmp + variable;
            if (exponent != 1) {
                tmp = tmp + "^" + String.valueOf(exponent);
            }
        } catch (Exception ex) {
            Util.LogError("Atom.Build(): " + ex.getMessage());
            tmp = "ERROR";
            throw new Exception("Atom.Build(): " + ex.getMessage());
        }
        return tmp;
    }
}
