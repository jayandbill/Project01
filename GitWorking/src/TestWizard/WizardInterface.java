
package TestWizard;

import javax.swing.JPanel;

public interface WizardInterface {

    public void PreStep();

    public boolean PostStep();

    public JPanel StepControl();
}
