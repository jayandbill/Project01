/**
 * A class that models a combination of atoms in of a polynomial expression.
 * For example, 6x^2y^3 or 24p^3
 */
package PolynomialExpressionGenerator;

import java.util.Stack;

/**
 *
 * @author Nicholson.Bill
 */
public class Element {

    private Stack Atoms;

    /**
<<<<<<< .mine
     * Scan through an element and find the variable with the maximum exponent.
     * @param variable The variable that has the max exponent.
     * @param exponent The maximum exponent.
     * @return True if there was at least one variable that had an exponent.
     */
    public boolean getMaxExponent(Atom tmpAtom) {
        boolean result = false;
        tmpAtom.setExponent(Integer.MIN_VALUE);
        tmpAtom.setVariable("");
        int i, tmp;
        try {
            for (i = 0; i < Atoms.size(); i++) {
                tmp = ((Atom) Atoms.elementAt(i)).getExponent();
                if (tmpAtom.getExponent() < tmp) {
                    result = true;      // We found something.
                    tmpAtom.setExponent(tmp);
                    tmpAtom.setVariable(((Atom) Atoms.elementAt(i)).getVariable());
                }
            }
        } catch (Exception ex) {
            Util.LogError("Element.getMaxExponent(): " + ex.getMessage());
            result = false;
        }
        return result;
    }

/**
 * Get the stack of Atoms in the element.
 * Violation of OOP Data Hiding
 * @return The stack of Atoms in the element.
 */
    public Stack getAtoms() {
        return Atoms;
    }
    /**
     * Consume a string, usually user input, and parse it into an Element object.
     * @param input = the string to be processed.
     * @return true if the parse succeeded.
     */
    public boolean fromString(String input) throws Exception {
        boolean status = true;
        Atom a;
        Stack AtomsTmp = new Stack();
        String mySplit[], tmp;
        int i, j, k, exponentSign, coefficientSign;
        char cTmp = ' ';
        try {
            mySplit = input.split(" ");
            for (i = 0; i < mySplit.length; i++) {
                exponentSign = 1; coefficientSign = 1;
                tmp = mySplit[i].trim();
                switch (tmp.length()) {
                    case 0:     // Nothing to do here.
                        break;
                    case 1:     // It must be a single variable.
                        cTmp = tmp.toCharArray()[0];
                        switch (cTmp) {
                            case '*': break;
                            default:
                                a = new Atom(1, tmp, 1);
                                AtomsTmp.push(a);
                            break;
                        }
                        break;
                    default:    // it's longer than 1 character. It must be an atom.
                        int coefficient = 0, exponent = 1; j = 0;
                        String variable = "";
//                      Check for a leading sign preceeding the coefficient.
                        if (tmp.substring(0, 1).toCharArray()[0] == '-') {
                            j = 1;
                            coefficientSign = -1;
                        } else {
                            if (tmp.substring(0, 1).toCharArray()[0] == '+') {
                                j = 1;      // Skip the + sign, it's implied anyway
                            }
                        }
                        for (;j < tmp.length(); j++) {
//                          A strange but logical way to get a char from a String
                            cTmp = tmp.substring(j, j + 1).toCharArray()[0];
                            if (Character.isDigit(cTmp)) {
                                coefficient = (coefficient * 10) + Integer.valueOf(Character.toString(cTmp));
                            } else {
                                break;
                            }
                        }
//                      if there wasn't a coefficient, default it to 1.
                        if (coefficient == 0) coefficient = 1;
                        coefficient = coefficient * coefficientSign;
//                      Next we should see the variable name. Scan until we see a carat or we run out of characters.
                        for (k = j; k < tmp.length(); k++) {
                            cTmp = tmp.substring(k, k + 1).toCharArray()[0];
                            if (cTmp == '^') {
                                break;
                            }
                            variable = variable + cTmp;
                        }
//                      If we found the carat, then there's an exponent that we need to process.
                        if (cTmp == '^') {
                            k++;        // Skip over the carat.
                            exponent = 0;
//                          Check for a leading sign.
                            if (tmp.substring(k, k+1).toCharArray()[0] == '-') {    // Stoopid substring, not lile C++ substring
                                k++;
                                exponentSign = -1;
                            } else {
                                if (tmp.substring(k, k+1).toCharArray()[0] == '+') {
                                    k++;      // Skip the + sign, it's implied anyway.
                                }
                            }
                            for (j = k; j < tmp.length(); j++) {
                                cTmp = tmp.substring(j, j + 1).toCharArray()[0];
                                exponent = (exponent * 10) + Integer.valueOf(Character.toString(cTmp));
                            }
                            exponent = exponent * exponentSign;
                        }
                        a = new Atom(coefficient, variable, exponent);
                        AtomsTmp.push(a);
                        break;
                }   // switch (tmp.length())
            }
            this.Atoms = AtomsTmp;
        } catch (Exception ex) {
            Util.LogError("Expression.fromString(): " + ex.getMessage());
            status = false;     // moot
            throw new Exception("Expression.fromString(): " + ex.getMessage());
        }
        return status;
    }

    /**
     * Compare two elements to see of they are equivalent.
     * It's not enough to just perform a simple atom-by-atom comparison.
     * For example, x^2 * y^3 is equivalent to y^3 * x^2
     * @param e
     * @return 0 if the elements are equal.
     */
    public int compare(Element e) throws Exception {
        Element e1, e2;
        Stack a1, a2;
        Atom a;
        int result = 0;
        try {
            a1 = (Stack) this.Atoms.clone();
            a2 = (Stack) e.Atoms.clone();
            e1 = new Element(a1);
            e2 = new Element(a2);
//          Simplify, then sort, each element before comparing.
            e1 = e1.Simplify();
            e1.sort();
            e2 = e2.Simplify();
            e2.sort();
            result = e1.toString().compareTo(e2.toString());
        } catch (Exception ex) {
            Util.LogError("Element.Compare(): " + ex.getMessage());
            throw new Exception("Element.Compare(): " + ex.getMessage());
        }
        return result;
    }

    /**
     * Sort the Atoms in an element
     */
    public void sort() {
        Atom.SortAtoms(Atoms);
    }

    /** Add an Atom to the list of atoms in this element
     *
     * @param a The Atom to be added to the list of Atoms in the element.
     * @return the Atom added to the list (The same as the Atom passed to the method).
     */
    public Atom addAtom(Atom a) {
        Atoms.push(a);
        return a;
    }

    public void clear() {
        Atoms.clear();
    }

    public Element clone() {
        Element e = new Element((Stack) Atoms.clone());
        return e;
    }

    public String toString() {
        return Build();
    }
/** Constructor
 * @param Atoms The stack of atoms that will comprise the polynomial. The constructor makes a copy.
 *
 */
    public Element(Stack Atoms) {
        this.Atoms = (Stack) Atoms.clone();
    }
/** Constructor
 *
 */
    public Element() {
        Atoms = new Stack();
    }

    public String Build() {
        String tmp = "";
        Atom a;
        for (int i = 0; i < Atoms.size(); i++) {
            a = (Atom) Atoms.elementAt(i);
            tmp = tmp + a.toString().trim() + " ";
        }
        return tmp.trim();
    }

    /**
     * negate the first coefficient in the element.
     * @return the negated coefficient.
     */
    public int NegateCoefficient() {
        Atom a = (Atom) Atoms.elementAt(0);
        a.setCoefficient(0 - a.getCoefficient());
        return a.getCoefficient();
    }
    /**
     * Return the first coefficient in the element.
     * @return the first coefficient.
     */
    public int getFirstCoefficient() {
        return ((Atom)(Atoms.elementAt(0))).getCoefficient();
    }

    /**
     * Multiply two polynomial elements. No simplification is performed.
     * @param m
     * @return the result of the multiplication.
     */
    public Element Multiply(Element m) {
        Element e = (Element) this.clone();
        int i;
        for (i = 0; i < m.Atoms.size(); i++) {
            e.addAtom((Atom) m.Atoms.elementAt(i));
        }
        return e;
    }

    /**
     * Simplify the Element by combining like terms.
     *
     * @return the simplified result.
     */
    public Element Simplify() {
        int i, coefficient = 1;
        Stack AtomsTmp = (Stack) Atoms.clone();
        Stack AtomsNew = new Stack();
        Atom AtomTmp, a, b;
        boolean found;
//      The easy part: multiply all the coefficients together.
        for (i = 0; i < AtomsTmp.size(); i++) {
            AtomTmp = (Atom) (AtomsTmp.elementAt(i));
            coefficient *= AtomTmp.getCoefficient();
        }
//      The simplified expression will have one coefficient, we have just calculated it.
//      The harder part: locate and combine like variables by adding the exponents.
//      Use the first variable / exponent to create the first atom in the new simplified expression.

        AtomTmp = (Atom) AtomsTmp.pop();
        a = new Atom();
        a.setCoefficient(coefficient);
        a.setVariable(AtomTmp.getVariable());
        a.setExponent(AtomTmp.getExponent());
        AtomsNew.push(a);

        while (AtomsTmp.isEmpty() == false) {
            AtomTmp = (Atom) AtomsTmp.pop();
//         Look for this variable in the new stack of atoms.
            found = false;
            for (i = 0; i < AtomsNew.size(); i++) {
                a = (Atom) AtomsNew.elementAt(i);
                if (a.getVariable().contentEquals(AtomTmp.getVariable())) {
//                  This variable is already on the new stack. Just add the exponent to the new stack.
                    a.setExponent(a.getExponent() + AtomTmp.getExponent());
                    found = true;
                    break;
                }
            }
            if (found == false) {        // The variable wasn't in the new stack. Add it now
                b = new Atom();
                b.setVariable(AtomTmp.getVariable());
                b.setExponent(AtomTmp.getExponent());
                AtomsNew.push(b);
            }
        }
        Atom.SortAtoms(AtomsNew);
        Element e = new Element(AtomsNew);
        return e;
    }
}
