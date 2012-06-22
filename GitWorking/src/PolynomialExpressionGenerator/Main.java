package PolynomialExpressionGenerator;

import java.util.Stack;

/**
 *
 * @author Nicholson.Bill
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Test01();
        //Test02();
        //Test03();
        //Test04();
        //Test05();
        //Test06();
        //Test07();
        //Test08();
        //Test09();
    	//Test10();
    	Test11();  // TestQuadraticPolymonialExpressionGenerator();
    	
    }
    /**
     * Test the quadratic expression generator. Added 3/23/12
     */
    private static void Test11() {   //TestQuadraticPolymonialExpressionGenerator() {
    	PolynomialExpressionGenerator p = new PolynomialExpressionGenerator();
    	try {
    		p.CreateExpression(PolynomialExpressionGenerator.ExpressionStyle.quadratic);
    		Expression e = p.GetExpression();
    		System.out.println("Quadratic Expression = " + e.getNumerator());
    	} catch (Exception ex) {}
    }
    
    /**
     * Make a binomial and simplify it. 
     * This is a trivial test that I wrote because I have not used this program in 2 years.
     */
    private static void Test10() {
        try {
            PolynomialExpression p = new PolynomialExpression();
            Element e = new Element();
            e = p.CreateElement(42, "x", 5);
            Element e1 = new Element();
            e1 = p.CreateElement(42, "x", 4);
            System.out.println("Element = " + e.toString());
            p.PushOntoNumeratorStack(e);
            p.PushOntoNumeratorStack("+");
            p.PushOntoNumeratorStack(e1);
            p.setExpressionStyle(PolynomialExpression.ExpressionStyle.binomial);
            System.out.println("Expression = " + p.toString());
            p = p.Simplify();
            System.out.println("Expression = " + p.toString());
        	
        } catch (Exception ex) {}
    }
/**
 * Test 09
 * Make a factorable trinomial
 *
 */
    private static void Test09() {
        try {
            PolynomialExpression p = new PolynomialExpression();
            p.setExpressionStyle(PolynomialExpression.ExpressionStyle.trinomial);
            p.setForceFactorable(true);
            p.setMaxVariablesPerAtom(1);
            p.setVariablePool("x");
            p.setMaxCoefficient(5);
            p.setMaxExponent(1);
            p.setMinExponent(1);
            p.Create(PolynomialExpression.ExpressionStyle.trinomial);
            System.out.println("\n<< " + p.toString() + " >> simplified to \n<< " + p.Simplify() + " >>");
            System.out.println("\n reordered to \n<< " + p.reorder().toString() + " >>");
        } catch (Exception ex) {
            Util.LogError("Text09(): " + ex.getMessage());
        }
    }
    /**
     * Test 08
     * Test the toString() method in the PolynomialExpression class.
     *
     */
    private static void Test08() {
        try {
            String s1 = "5p^5";
            String s2 = "5p^5 4q^2 w";
            String s3 = "5p^5 * ( 4q^2 +  w )";
            String s4 = "a * ( b -  c )";
            String s5 = "a * ( b^3 -  c )";
            String s6 = "a * ( b^3 -  42 )";    // Special case!
            String s7 = "42 * ( 99 -  66 )";    // Very Special case!
            String s8 = "x + 2x^2 + 3x^3 + 4x^4 + 5x^5";
            System.out.println("Test08:");
            Test08a(s1, PolynomialExpression.ExpressionStyle.singleExpression, true);
            Test08a(s2, PolynomialExpression.ExpressionStyle.singleExpression, true);
            Test08a(s3, PolynomialExpression.ExpressionStyle.oneByTwo, true);
            Test08a(s4, PolynomialExpression.ExpressionStyle.oneByTwo, true);
            Test08a(s5, PolynomialExpression.ExpressionStyle.oneByTwo, true);
            Test08a(s6, PolynomialExpression.ExpressionStyle.oneByTwo, true);
            Test08a(s7, PolynomialExpression.ExpressionStyle.oneByTwo, false);
            Test08a(s8, PolynomialExpression.ExpressionStyle.freeForm, true);
        } catch (Exception ex) {
            Util.LogError("Text08(): " + ex.getMessage());
        }
    }
/**
 * Convert a string to a polynomialExpression object, then back to a string.
 * @param s
 */
    private static void Test08a(String s, PolynomialExpression.ExpressionStyle es, boolean reorder) {
        try {
            PolynomialExpression tmp, p, reordered;
            tmp = PolynomialExpression.fromString(s);
            tmp.setExpressionStyle(es);
            System.out.println("\n<< " + s + " >> converted to\n<< " + tmp.toString() + " >>");
            tmp = tmp.Simplify();
            System.out.println("\n<< " + s + " >> simplified to\n<< " + tmp.toString() + " >>");
            if (reorder) {
                reordered = tmp.reorder();
               System.out.println("\n<< " + tmp.toString() + " >> reordered to \n<< " + reordered.toString() + " >>");
            }
        } catch (Exception ex) {
            Util.LogError("Text08a(): " + ex.getMessage());
        }
    }

    /**
     * Test 07
     * Test the toString() method in the Element class.
     *
     */
    private static void Test07() {
        Element e1, e2, e3;
        String tmp;
        int exponent, coefficient;
        String variable;
        try {
            tmp = "27a - 24a - 73a";
            System.out.println("Test07: Starting with " + tmp);
            Atom a, b, c, d, f;
            Stack atoms1 = new Stack(), atoms2 = new Stack(), atoms3 = new Stack();
            PolynomialExpression p;
            c = new Atom();
            c.setCoefficient(-73);
            c.setVariable("a");
            c.setExponent(1);
            atoms3.push(c);
            b = new Atom();
            b.setCoefficient(-24);
            b.setVariable("a");
            b.setExponent(1);
            atoms2.push(b);
            a = new Atom();
            a.setCoefficient(27);
            a.setVariable("a");
            a.setExponent(1);
            atoms1.push(a);
            e1 = new Element(atoms1);
            e1 = e1.Simplify();
            e2 = new Element(atoms2);
            e2 = e2.Simplify();
            e3 = new Element(atoms3);
            e3 = e3.Simplify();
            p = new PolynomialExpression();
            p.setExpressionStyle(PolynomialExpression.ExpressionStyle.trinomial);
            p.PushOntoNumeratorStack(e1);
            p.PushOntoNumeratorStack("+");
            p.PushOntoNumeratorStack(e2);
            p.PushOntoNumeratorStack("+");
            p.PushOntoNumeratorStack(e3);

            System.out.println("Test 07: Start with trinomial :" + p.toString());
            p = p.Simplify();
            System.out.println("Test 07: then simplified :" + p.toString());

//          test 7b
            atoms1.clear();
            atoms2.clear();
            b = new Atom();
            b.setCoefficient(51);
            b.setVariable("a");
            b.setExponent(1);
            atoms2.push(b);
            a = new Atom();
            a.setCoefficient(81);
            a.setVariable("a");
            a.setExponent(1);
            atoms1.push(a);
            e1 = new Element(atoms1);
            e1 = e1.Simplify();
            e2 = new Element(atoms2);
            e2 = e2.Simplify();
            p = new PolynomialExpression();
            p.setExpressionStyle(PolynomialExpression.ExpressionStyle.binomial);
            p.PushOntoNumeratorStack(e1);
            p.PushOntoNumeratorStack("-");
            p.PushOntoNumeratorStack(e2);

            System.out.println("Test 07b: Start with binomial :" + p.toString());
            p = p.Simplify();
            System.out.println("Test 07b: then simplified :" + p.toString());

        } catch (Exception ex) {
            Util.LogError("main.Test07(): " + ex.getMessage());
        }
    }

    /**
     * Test 06
     * Test the toString() method in the Element class.
     *
     */
    private static void Test06() {
        Element e = new Element();
        String tmp;
        int exponent, coefficient;
        String variable;
        try {
            tmp = "34x^2";
            System.out.println("Test06: Starting with " + tmp);
            e.fromString(tmp);
            System.out.println("Test06: parsed as     " + e.toString());

            tmp = "-5x^-7";
            System.out.println("Test06: Starting with " + tmp);
            e.fromString(tmp);
            System.out.println("Test06: parsed as     " + e.toString());

            tmp = "-x * y^2";
            System.out.println("Test06: Starting with " + tmp);
            e.fromString(tmp);
            System.out.println("Test06: parsed as     " + e.toString());

        } catch (Exception ex) {
            Util.LogError("main.Test06(): " + ex.getMessage());
        }
    }

    /**
     * Test 05
     * Test the sort logic on an element, which is a stack of atoms.
     * 
     */
    private static void Test05() {
        try {
            System.out.println("Test 05: Sorting Atoms in an element:");
            Atom a, b, c, d, f;
            Element e;
            Stack atoms = new Stack();
            PolynomialExpression p;
            a = new Atom();
            a.setCoefficient(4);
            a.setVariable("x");
            a.setExponent(1);
            atoms.push(a);
            b = new Atom();
            b.setCoefficient(1);
            b.setVariable("a");
            b.setExponent(1);
            atoms.push(b);
            c = new Atom();
            c.setCoefficient(1);
            c.setVariable("z");
            c.setExponent(4);
            atoms.push(c);
            f = new Atom();
            f.setCoefficient(6);
            f.setVariable("q");
            f.setExponent(99);
            atoms.push(f);
            e = new Element(atoms);
            System.out.println("Test 05a: Start with :" + e.toString());
            e = e.Simplify();
            System.out.println("Test 05a: then simplified :" + e.toString());
            e.sort();
            System.out.println("Test05a: Then sorted: " + e.toString());

            atoms.clear();
            a = new Atom();
            a.setCoefficient(1);
            a.setVariable("x");
            a.setExponent(10);
            atoms.push(a);
            b = new Atom();
            b.setCoefficient(2);
            b.setVariable("a");
            b.setExponent(10);
            atoms.push(b);
            c = new Atom();
            c.setCoefficient(3);
            c.setVariable("z");
            c.setExponent(10);
            atoms.push(c);
            d = new Atom();
            d.setCoefficient(4);
            d.setVariable("b");
            d.setExponent(10);
            atoms.push(d);
            f = new Atom();
            f.setCoefficient(5);
            f.setVariable("q");
            f.setExponent(14);
            atoms.push(f);
            e = new Element(atoms);
            System.out.println("Test 05b: Start with :" + e.toString());
            e = e.Simplify();
            System.out.println("Test 05b: then simplified :" + e.toString());
            e.sort();
            System.out.println("Test05b: Then sorted: " + e.toString());
        } catch (Exception ex) {
            Util.LogError("main.Test05(): " + ex.getMessage());
        }
    }

    /**
     * Test 04
     * Test the multiplication of elements
     */
    private static void Test04() {
        try {
            System.out.println("Test 04: Multiply expressions...");
            Stack atoms = new Stack();
            Atom a, b, c, d;
            PolynomialExpression p;
            a = new Atom();
            a.setCoefficient(2);
            a.setVariable("x");
            a.setExponent(1);
            atoms.push(a);
            Element m1 = new Element((Stack) atoms);
            Element m2 = new Element((Stack) atoms);

            Element m3 = m1.Multiply(m2);
            System.out.println("Test 04: " + m1.toString() + " * " + m2.toString() + " = " + m3.toString());
            System.out.println("Test 04: Simplified = " + m3.Simplify().toString());

            p = new PolynomialExpression();
            p.Create(PolynomialExpression.ExpressionStyle.oneByTwo);     // a * ( b + c)
            System.out.println("test 04: Polynomial 1 by 2 generated: " + p.toString());
            System.out.println("test 04: Polynomial simplified: " + p.Simplify().toString());

            p = new PolynomialExpression();
            p.Create(PolynomialExpression.ExpressionStyle.twoByTwo);     // (a + b ) * ( c + d )
            System.out.println("test 04: Polynomial 2 by 2 generated: " + p.toString());
            System.out.println("test 04: Polynomial simplified: " + p.Simplify().toString());

        } catch (Exception ex) {
            Util.LogError("main.Test04(): " + ex.getMessage());
        }
    }

    /**
     * Test 03
     * Cobble up a relatively complex single expression and simplify it.
     */
    private static void Test03() {
        System.out.println("Test 03: Simplify an expression...");
        Stack atoms = new Stack();
        Atom a, b, c, d;
        a = new Atom();
        a.setCoefficient(1);
        a.setVariable("x");
        a.setExponent(1);
        b = new Atom();
        b.setCoefficient(3);
        b.setVariable("y");
        b.setExponent(4);
        c = new Atom();
        c.setCoefficient(6);
        c.setVariable("y");
        c.setExponent(8);
        d = new Atom();
        d.setCoefficient(10);
        d.setVariable("y");
        d.setExponent(1);

        atoms.push(a);
        Element e = new Element(atoms);
        System.out.println("Test 03: " + e.toString());
        System.out.println("Test 03 simplified: " + e.Simplify().toString());

        atoms.push(b);
        atoms.push(c);
        atoms.push(d);
        e.clear();
        e = new Element(atoms);
        System.out.println("Test 03: " + e.toString());
        System.out.println("Test 03 simplified: " + e.Simplify().toString());
    }

    /**
     * Test 02
     * Test the atom class and element class.
     *
     */
    private static void Test02() {
        Atom a1 = new Atom(), a2 = new Atom(), a3 = new Atom();
        System.out.println("Test 02: ");
        a1.setCoefficient(99);
        a1.setVariable("x");
        a1.setExponent(3);
        System.out.println("ToString(): " + a1.toString());

        a2.setCoefficient(1);
        a2.setVariable("y");
        a2.setExponent(1);
        System.out.println("ToString(): " + a2.toString());

        a3.setCoefficient(-1);
        a3.setVariable("p");
        a3.setExponent(-1);
        System.out.println("ToString(): " + a3.toString());
    }

    /**
     * Test 01
     * Generate polymonials in the available formats.
     */
    private static void Test01() {
        PolynomialConfig c = new PolynomialConfig();
        PolynomialExpression p = new PolynomialExpression(c);
        Expression e;
        try {
            System.out.println(" Free Form ********");
            for (int i = 0; i < 5; i++) {
                p.Create(PolynomialExpression.ExpressionStyle.freeForm);
                e = p.getExpression();
                System.out.println("Numerator = " + e.getNumerator());
            }
            System.out.println("One by Two ********");
            for (int i = 0; i < 5; i++) {
                p.Create(PolynomialExpression.ExpressionStyle.oneByTwo);
                e = p.getExpression();
                System.out.println("Numerator = " + e.getNumerator());
            }
            System.out.println("Two by Two ********");
            for (int i = 0; i < 5; i++) {
                p.Create(PolynomialExpression.ExpressionStyle.twoByTwo);
                e = p.getExpression();
                System.out.println("Numerator = " + e.getNumerator());
            }
        } catch (Exception ex) {
            Util.LogError("main.Test01:" + ex.getMessage());
        }
    }
}
