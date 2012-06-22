package TestWizard;

import java.awt.Container;
import javax.swing.JPanel;

public class BaseWizardClass
        extends javax.swing.JPanel
            implements WizardInterface{
    protected DataControl dc;
    protected JPanel control;
    protected Container component;

    public void BaseWizardClass(DataControl dc, JPanel jp)
    {
        this.setVisible(true);
        this.dc = dc;
        control = jp;
    }

    public void PreStep() {
        this.setVisible(true);
        control.setVisible(false);
    }

    public boolean PostStep() {

        this.setVisible(true);
        control.setVisible(true);

        control.revalidate();
        control.repaint();

        return true;
    }

    public JPanel StepControl() {
        this.setVisible(true);
        return control;
    }


}
