/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestWizard;

import javax.swing.JPanel;

/**
 *
 * @author Lorryn Picard
 */
public class ConfigWizardControl extends BaseWizardClass{

    ConfigWizardControl(DataControl dc)
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
