
package TestWizard;

import javax.swing.JPanel;

public class OpenWizardControl extends BaseWizardClass{

    OpenWizardControl(DataControl dc)
    {
        super.BaseWizardClass(dc,new ConfigWizard());
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
