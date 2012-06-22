package AlgebraGUI;
import PolynomialExpressionGenerator.Expression;
import PolynomialExpressionGenerator.PolynomialExpression;
import TestWizard.TestWizard;

public class AlgebraGUI extends javax.swing.JFrame {
    private PolynomialExpression poly = new PolynomialExpression();
    private ExpressionParser exp = null;
    private boolean StandardToSimplify;
    public AlgebraGUI() {
        initComponents();        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        AnswerField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ExpressionCmb = new javax.swing.JComboBox();
        Answer = new javax.swing.JButton();
        MethodToAnswer = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        RunMenu = new javax.swing.JMenuItem();
        ExitMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ConfigPoly = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("School Subject Tester : Math - Algebra");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To Start Configure the polynomial and then press F1 or go to File then hit Run!");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel2.setText("Type of Expression?: ");

        ExpressionCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single : a", "One by Two : a * ( b + c )", "Two by Two : ( a + b)  * ( c + d )", "Binomial : a + b", "Trinomial : a + b + c", "Quadratic : ", "// Free Form : a + b + c + d" }));

        Answer.setText("Check Answer");
        Answer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerActionPerformed(evt);
            }
        });

        MethodToAnswer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Expanded => Simplified", "Simplified => Expanded" }));

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Test Creator Wizard");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        RunMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        RunMenu.setText("Run!");
        RunMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunMenuActionPerformed(evt);
            }
        });
        jMenu1.add(RunMenu);

        ExitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        ExitMenu.setText("Exit");
        ExitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMenu);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Configure");

        ConfigPoly.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        ConfigPoly.setText("Configure Polynomial");
        ConfigPoly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfigPolyActionPerformed(evt);
            }
        });
        jMenu3.add(ConfigPoly);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnswerField, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Answer)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExpressionCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(MethodToAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ExpressionCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MethodToAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnswerField, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(Answer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void RunMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunMenuActionPerformed
        // TODO add your handling code here:
        try {
        Expression ex ;
//      The enum starts at one, the list box index starts at 0
        int tmpExpressionStyle = ExpressionCmb.getSelectedIndex() + 1;
        //p.Create(PolynomialExpression.ExpressionStyle.twoByTwo);
        switch (tmpExpressionStyle) {
            case 1:                                 // Single element
                poly.Create(PolynomialExpression.ExpressionStyle.singleExpression);
                break;
            case 2:
                poly.Create(PolynomialExpression.ExpressionStyle.oneByTwo);
                break;
            case 3:
                poly.Create(PolynomialExpression.ExpressionStyle.twoByTwo);
                break;
            case 4:
                poly.Create(PolynomialExpression.ExpressionStyle.binomial);
                break;
            case 5:
                poly.Create(PolynomialExpression.ExpressionStyle.trinomial);
                break;
            case 6:
                poly.Create(PolynomialExpression.ExpressionStyle.quadratic);
                break;
            case 7:
                poly.Create(PolynomialExpression.ExpressionStyle.freeForm);
                break;
        }
        exp = new ExpressionParser();
        if(MethodToAnswer.getSelectedIndex() == 0)
        {
            jLabel1.setText(exp.parse(poly.toString()));
            StandardToSimplify = true;
        }
        else
        {
            jLabel1.setText(exp.parse(poly.Simplify().toString()));
            StandardToSimplify = false;
        }


    } catch (Exception ex) {

    }
}//GEN-LAST:event_RunMenuActionPerformed

    private void ConfigPolyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigPolyActionPerformed
        ConfigGUI config = new ConfigGUI(this, poly);
        config.setVisible(true);
}//GEN-LAST:event_ConfigPolyActionPerformed

    private void ExitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitMenuActionPerformed

    private void AnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerActionPerformed
        if (StandardToSimplify)
        {
            MessageBoxAnswer(poly.ValidateSimplifiedPolymonial(AnswerField.getText()), evt);
        }
        else
        {
            MessageBoxAnswer(poly.ValidateExpandedPolymonial(AnswerField.getText()), evt);
        }
    }    

     private void MessageBoxAnswer(boolean t, java.awt.event.ActionEvent evt)
     {
        if (t)
        {
            MessageBox.ok("You are Correct!");
            this.RunMenuActionPerformed(evt);
        }
        else
        {
            MessageBox.ok("Sorry, try Again.");
        }
    }//GEN-LAST:event_AnswerActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        TestWizard tw = new TestWizard();
        tw.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlgebraGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Answer;
    private javax.swing.JTextField AnswerField;
    private javax.swing.JMenuItem ConfigPoly;
    private javax.swing.JMenuItem ExitMenu;
    private javax.swing.JComboBox ExpressionCmb;
    private javax.swing.JComboBox MethodToAnswer;
    private javax.swing.JMenuItem RunMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

}
