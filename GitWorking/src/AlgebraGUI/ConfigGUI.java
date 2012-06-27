package AlgebraGUI;
import PolynomialExpressionGenerator.PolynomialExpression;
import java.awt.Color;

public class ConfigGUI extends javax.swing.JFrame {
     PolynomialExpression p = null;
     AlgebraGUI GUI = null;
    /** Creates new form ConfigGUI */
    public ConfigGUI(AlgebraGUI g, PolynomialExpression p) {
    	setTitle("Settings");
        initComponents();
        GUI = g;
        this.p = p;
        jLabel10.setText("");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ForceFactoringCmb = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        MaxCoText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        MaxFacText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        MaxVarText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        MaxAtomText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MinFacText = new javax.swing.JTextField();
        MinCoText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        MinAtomText = new javax.swing.JTextField();
        MinVarText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        MinExpText = new javax.swing.JTextField();
        MaxExpText = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        RandomSeed = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Force Factoring?");

        ForceFactoringCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes" }));

        jLabel2.setText("Maximum Coefficient?");

        MaxCoText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MaxCoText.setText("5");

        jLabel3.setText("Max Factors Per Expression?");

        MaxFacText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MaxFacText.setText("2");

        jLabel4.setText("Max number of Variables?");

        MaxVarText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MaxVarText.setText("1");

        jLabel5.setText("Max Variables per Atom?");

        MaxAtomText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MaxAtomText.setText("2");

        jLabel6.setText("Minimum Coefficient?");

        jLabel7.setText("Min Factors Per Expression?");

        MinFacText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MinFacText.setText("1");

        MinCoText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MinCoText.setText("1");

        jLabel8.setText("Min number of Variables?");

        jLabel9.setText("Min Variables per Atom?");

        MinAtomText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MinAtomText.setText("1");

        MinVarText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MinVarText.setText("1");

        jLabel10.setText("jLabel10");

        jLabel11.setText("Maximum Exponent");

        jLabel12.setText("Minimum Exponent");

        MinExpText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MinExpText.setText("1");

        MaxExpText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        MaxExpText.setText("5");

        jLabel13.setText("Random Seed");

        RandomSeed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        RandomSeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RandomSeedActionPerformed(evt);
            }
        });

        jLabel14.setText("(leave seed blank for random seed)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RandomSeed, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(MinCoText, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(MinFacText, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(MaxVarText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(MaxFacText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(MaxCoText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(ForceFactoringCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, 81, Short.MAX_VALUE)
                                    .addComponent(MaxAtomText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(MinAtomText)
                                        .addComponent(MaxExpText, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(MinExpText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                    .addComponent(MinVarText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ForceFactoringCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(MaxCoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(MinCoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MaxFacText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(MinFacText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(MaxVarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(MinVarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(MaxAtomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(MinAtomText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(MaxExpText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(MinExpText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(RandomSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(CancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        jLabel1.setText("");
        try {
            p.setForceFactorable(String.valueOf(ForceFactoringCmb.getSelectedItem()).compareTo("Yes") == 0 ? true:false);
            p.setMaxCoefficient(Integer.parseInt(MaxCoText.getText()));
            p.setMinCoefficient(Integer.parseInt(MinCoText.getText()));
            p.setMaxFactorsPerExpression(Integer.parseInt(MaxFacText.getText()));
            p.setMinFactorsPerExpression(Integer.parseInt(MinFacText.getText()));
            p.setMaxNumberOfVariables(Integer.parseInt(MaxVarText.getText()));
            p.setMinNumberOfVariables(Integer.parseInt(MinVarText.getText()));
            p.setMaxVariablesPerAtom(Integer.parseInt(MaxAtomText.getText()));
            p.setMinVariablesPerAtom(Integer.parseInt(MinAtomText.getText()));
            p.setMaxExponent(Integer.parseInt(MaxExpText.getText()));
            p.setMinExponent(Integer.parseInt(MinExpText.getText()));
            if (RandomSeed.getText().compareTo("") != 0){
                p.setRandomSeed(Long.parseLong(RandomSeed.getText()));
            }
            this.dispose();
        } catch (Exception e) {
            jLabel10.setForeground(Color.RED);
            jLabel10.setText("Please fill out all the options");
        }
}//GEN-LAST:event_SaveButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.dispose();        
}//GEN-LAST:event_CancelButtonActionPerformed

    private void RandomSeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RandomSeedActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_RandomSeedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JComboBox ForceFactoringCmb;
    private javax.swing.JTextField MaxAtomText;
    private javax.swing.JTextField MaxCoText;
    private javax.swing.JTextField MaxExpText;
    private javax.swing.JTextField MaxFacText;
    private javax.swing.JTextField MaxVarText;
    private javax.swing.JTextField MinAtomText;
    private javax.swing.JTextField MinCoText;
    private javax.swing.JTextField MinExpText;
    private javax.swing.JTextField MinFacText;
    private javax.swing.JTextField MinVarText;
    private javax.swing.JTextField RandomSeed;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
