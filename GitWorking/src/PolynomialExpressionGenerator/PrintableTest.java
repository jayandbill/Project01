package PolynomialExpressionGenerator;

import java.io.*;
import AlgebraGUI.ExpressionParser;

/**
 *
 * @author Nicholson.Bill
 */
public class PrintableTest {

    /** Generate a printable test.
     * @return True if the method completed properly.
     *
     */
    public boolean GeneratePrintableTest(PolynomialConfig pc, ReportConfig rc) {
        boolean result = true;
        int i;
        PrintWriter pw;
        PolynomialExpression p[]= new PolynomialExpression[rc.getTotalProblems()];
        PolynomialExpression tmp = new PolynomialExpression(pc);
        try {
            for (i = 0; i < rc.getTotalProblems(); i++) {
//              use the same PolynomialExpression for the Create() otherwise
//                you'll get the same problem over and over: the random number
//                 will keep resetting.
                tmp.Create(pc.getExpressionStyle());
                p[i] = tmp.clone();
            }
            pw = new PrintWriter(rc.getFileNameAndPath());
            WriteHeader(pw);
            switch (rc.getWhatToPrint()) {
                case ExercisesWithSolutions:
                        WriteExercises(p, true, pw, pc, rc);
                    break;

                case exercisesOnly:
                       WriteExercises(p, false, pw, pc, rc);
                    break;

                case BothCopies:
                       WriteExercises(p, false, pw, pc, rc);
                       WritePageBreak(pw);
                       WriteExercises(p, true, pw, pc, rc);
                    break;
                    
            }
            WriteTail(pw);
            pw.close();
        } catch (Exception ex) {
            result = false;
            Util.LogError("PolynomialExpression.GeneratePrintableTest(): " + ex.getMessage());
        }
        return result;
    }

    private void WriteIndexColumn(int index, PrintWriter pw) {
        pw.write("<td height=\"30\" width=\"33\" align=\"center\">");
        pw.write(String.valueOf(index));
        pw.write("</td>");
    }

    /**
     * Write the HTML Header
     * @param pw PrintWriter object
     */
    private void WriteHeader(PrintWriter pw) {
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
        pw.println("<title>Polymonial Test</title>");
        pw.println("<style>");
        pw.println(".break { page-break-before: always; }");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");
    }

    /**
     * Write the HTML Tail
     * @param pw PrintWriter object
     */
    private void WriteTail(PrintWriter pw) {
        pw.println("</body>");
        pw.println("</html>");
    }

    private void StartTable(PrintWriter pw) {
        pw.println("<table border=\"1\" cellspacing=\"5\" cellpadding=\"9\" align=\"left\"  >");
    }

    private void StartRow(PrintWriter pw) {
        pw.println("<tr>");
    }

    private void StartLeftColumn(PrintWriter pw) {
        pw.println("<td width=\"192\" align=\"right\">");
    }

    private void StartRightColumn(PrintWriter pw) {
        pw.println("<td width=\"192\" align=\"left\" > ");
    }

    private void StopColumn(PrintWriter pw) {
        pw.println("</td>");
    }

    private void StopRow(PrintWriter pw) {
        pw.println("</tr>");
    }

    private void StopTable(PrintWriter pw) {
        pw.println("</table>");
    }

    private void WritePageBreak(PrintWriter pw) {
//      pw.println("<br>");
//      pw.println("<p>&nbsp</p>");
//      pw.println("<div style=\"page-break-before: always\">.</div>");
        pw.println("<p class=\"break\">&nbsp</p>");
    }
    private void WriteBreak(PrintWriter pw) {
//        pw.println("<p>&nbsp</p>");
        pw.println("<br>");
    }

    private void WriteKeyInTitle(PrintWriter pw) {
        pw.println("<b><font size=\"4\" face=\"Arial\">&nbsp Key</font></b>");
    }
    private void WriteMessage(String message, PrintWriter pw) {
        pw.println("<b><font size=\"3\" face=\"Arial\">" + message + "</font></b>");
    }

    private void WriteExercises(PolynomialExpression p[], boolean printSolutions, PrintWriter pw, PolynomialConfig pc, ReportConfig rc) {
            int i;
            ExpressionParser ep = new ExpressionParser();
            StartTable(pw);
            pw.write("Test # " + pc.getRandom().getSeed());
            if (printSolutions) {
                WriteKeyInTitle(pw);
            }
            switch (rc.getReportMode()) {
                case ExpandedToSimplified:
                    WriteBreak(pw);
                    WriteMessage("Simplify these polynomials.", pw);
                    break;

                case SimplifiedToExpanded:
                    WriteBreak(pw);
                    WriteMessage("Expand these polynomials.", pw);
                    break;
            }

            WriteBreak(pw);
            for (i = 0; i < rc.getTotalProblems(); i++) {
                StartRow(pw);
                WriteIndexColumn(i + 1, pw);
                StartLeftColumn(pw);
                switch (rc.getReportMode()) {
                    case ExpandedToSimplified:
                        pw.write(ep.parseString(p[i].toString()));
                        break;

                    case SimplifiedToExpanded:
                        pw.write(ep.parseString(p[i].Simplify().toString()));
                        break;
                }
                StopColumn(pw);
                StartRightColumn(pw);
                if (printSolutions == true) {
                    switch (rc.getReportMode()) {
                        case ExpandedToSimplified:
                            pw.write(ep.parseString(p[i].Simplify().toString()));
                            break;

                        case SimplifiedToExpanded:
                            pw.write(ep.parseString(p[i].toString()));
                            break;
                    }
                } else {
                    pw.write("&nbsp");
                }
                StopColumn(pw);
                StopRow(pw);
            }
            StopTable(pw);
    }
}
