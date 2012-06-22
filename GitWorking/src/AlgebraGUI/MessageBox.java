package AlgebraGUI;

import java.awt.*;
import javax.swing.*;

public class MessageBox {
    public static int yesno(String theMessage){
        int result = JOptionPane.showConfirmDialog((Component)
                null, theMessage, "alert", JOptionPane.YES_NO_OPTION);
        return result;
    }

    public static int yesnocancel(String theMessage){
        int result = JOptionPane.showConfirmDialog((Component)
                null, theMessage, "alert", JOptionPane.YES_NO_CANCEL_OPTION);
        return result;
    }

    public static int okcancel(String theMessage){
        int result = JOptionPane.showConfirmDialog((Component)
                null, theMessage, "alert", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public static int ok(String theMessage){
        int result = JOptionPane.showConfirmDialog((Component)
                null, theMessage, "alert", JOptionPane.DEFAULT_OPTION);
        return result;
    }
}

