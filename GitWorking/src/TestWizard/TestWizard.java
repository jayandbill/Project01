package TestWizard;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Random;

public class TestWizard extends javax.swing.JFrame {
    private DataControl dc = new DataControl();
    private ArrayList<BaseWizardClass> mcolSteps;
    private int stepsID;
    private boolean statement;
    /** Creates new form TestWizard */

    private void PreStep()
    {
        WizardInterface step;
        step = mcolSteps.get(stepsID);
        step.PreStep();

        JPanel jp = step.StepControl();
        remove(jPanel1);
        jPanel1 = jp;
        jPanel1.setVisible(true);
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 280, 190));
        pack();
        repaint();
        
        if (stepsID == 0)
        {
            BackButton.setEnabled(false);
        }
        else
        {
            BackButton.setEnabled(true);
        }
    }

    private void PostStep()
    {
//        WizardInterface step;
//        step = mcolSteps.get(stepsID);

//      One possible option: Add the new controls to the existing panel.
//      I added a button and a label...
//        jPanel1.removeAll();
//        java.awt.Button myButton = new java.awt.Button("Hello from Button");
//        int x = new Random().nextInt();
//        java.awt.Label myLabel = new java.awt.Label(String.valueOf(x));
//        myLabel.setVisible(true);
//        myLabel.setLocation(1, 1);
//        myLabel.setBackground(java.awt.Color.PINK);
//        myLabel.setForeground(java.awt.Color.white);
//        myLabel.setSize(10, 30);
//        jPanel1.add(myLabel);
//        jPanel1.add(myButton);
//        myButton.setVisible(true);
//        myButton.invalidate();
//        myButton.setLocation(50, 50);
//        myButton.setSize(30, 50);
//        myLabel.setSize(30, 50);
//        myLabel.repaint();
//        jPanel1.setBackground(java.awt.Color.green);
//        jPanel1.setBounds(10,10,200,200);
//        jPanel1.setVisible(true);
//        jPanel1.repaint();
//        jPanel1.invalidate();
//
//        pack();


//      Another option. Replace the JPanel with a new JPanel.
//      Take out the original control from the JFrame collection of controls.

//      The key here is the add(JPanel) in line 84, below.
        remove(jPanel1);

        jPanel1 = new JPanel();
//
//        jPanel1.setBackground(java.awt.Color.blue);
//        jPanel1.setLocation(10, 10);
//        jPanel1.setSize(200, 200);
//        jPanel1.setVisible(true);
//        java.awt.Label myLabelOne = new java.awt.Label("Hello from Label");
//        myLabelOne.setVisible(true);
//        myLabelOne.setLocation(50, 50);
//        myLabelOne.setSize(20, 50);
//        jPanel1.add(myLabelOne);
//        myLabelOne.repaint();

//      Add the new control to the JFrame collection of controls.
//        add(jPanel1);
//        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 280, 190));
//        pack();
//        repaint();
        //statement = step.PostStep();
    }


    public TestWizard() {
        initComponents();
        mcolSteps = new ArrayList();
        mcolSteps.add(new StartWizardControl(dc));
        mcolSteps.add(new OpenWizardControl(dc));
        mcolSteps.add(new ConfigWizardControl(dc));
        mcolSteps.add(new FinishWizardControl(dc));
        stepsID = 0;
        PreStep();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NextButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NextButton.setText("Next");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });
        getContentPane().add(NextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("HI THERE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 280, 190));

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        // TODO add your handling code here:
        PostStep();
        if(stepsID >= 0 && stepsID < mcolSteps.size() - 1)
        {
            stepsID += 1;
            PreStep();
            if (stepsID == mcolSteps.size() - 1)
            {
                NextButton.setText("Finish");
            }
            else
            {
                NextButton.setText("Next");
            }
        }
        else
        {
            this.dispose();
        }

    }//GEN-LAST:event_NextButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        PostStep();
        if (stepsID > 1)
        {
            stepsID -= 1;
            PreStep();
        }
    }//GEN-LAST:event_BackButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton NextButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
