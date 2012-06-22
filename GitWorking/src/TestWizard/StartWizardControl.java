
package TestWizard;

import javax.swing.JPanel;



public class StartWizardControl extends BaseWizardClass{

    StartWizardControl(DataControl dc)
    {
        super.BaseWizardClass(dc,new StartWizard());
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
