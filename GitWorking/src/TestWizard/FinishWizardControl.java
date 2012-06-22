
package TestWizard;

import java.awt.Container;
import javax.swing.JPanel;

public class FinishWizardControl extends BaseWizardClass{

    FinishWizardControl(DataControl dc)
    {
        super.BaseWizardClass(dc, new FinishWizard());
    }

    @Override
    public void PreStep()
    {
        super.PreStep();
    }

    @Override
    public boolean PostStep()
    {
        JPanel oCtrl = super.control;
        return true;
    }

    @Override
    public JPanel StepControl()
    {
        return super.StepControl();
    }

}
