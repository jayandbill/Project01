package AlgebraGUI;

public class ExpressionParser {

    /**
     * Call parse01 to handle superscripts, then add on the HTML suffix and prefix.
     * @param str
     * @return the parsed string.
     */
    public String parse(String str)
    {
        return "<html>"+ parseString(str) +"</html>";
    }
    /**
     * Parse superscripts in the polynomial expression.
     * @param str
     * @return the parsed string.
     */
    public String parseString(String str)
    {
        String[] split = str.split(" ");
        String temp = "";
        for(int i = 0; i < split.length; i++)
        {
            if (split[i].contains("^"))
            {
                temp += parseExp(split[i]);
            }
            else if (split[i].contains("/"))
            {
                temp += parseDiv(split[i]);
            }
            else
            {
                temp += split[i] + " ";
            }
        }
        return temp;
    }

    private String parseExp(String str)
    {
        String temp = str.substring(str.indexOf('^') + 1);
        str = str.substring(0, str.indexOf("^"));
        return str + "<sup>" + temp + "</sup> ";
    }

    private String parseDiv(String str)
    {
        return "";
    }
}
