package AlgebraGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class HelpGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSingleA;
	private JTextField txtOneByTwo;
	private JTextField txtTwoByTwo;
	private JTextField txtBinomial;
	private JTextField txtTrinomial;
	private JTextField txtQuadratic;
	private JTextField txtFreeForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpGUI frame = new HelpGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelpGUI() {
		setTitle("Help");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		JLabel lblPolynomialExpressionGenerator = new JLabel("Polynomial Expression Generator");
		lblPolynomialExpressionGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolynomialExpressionGenerator.setBackground(Color.WHITE);
		lblPolynomialExpressionGenerator.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton btnNewButton = new JButton("Download Instructions");
		
		txtSingleA = new JTextField();
		txtSingleA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSingleA.setText("single a");
		txtSingleA.setColumns(10);
		
		txtOneByTwo = new JTextField();
		txtOneByTwo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtOneByTwo.setText("one by two");
		txtOneByTwo.setColumns(10);
		
		txtTwoByTwo = new JTextField();
		txtTwoByTwo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTwoByTwo.setText("two by two");
		txtTwoByTwo.setColumns(10);
		
		txtBinomial = new JTextField();
		txtBinomial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBinomial.setText("binomial");
		txtBinomial.setColumns(10);
		
		txtTrinomial = new JTextField();
		txtTrinomial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTrinomial.setText("trinomial");
		txtTrinomial.setColumns(10);
		
		txtQuadratic = new JTextField();
		txtQuadratic.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuadratic.setText("quadratic");
		txtQuadratic.setColumns(10);
		
		txtFreeForm = new JTextField();
		txtFreeForm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFreeForm.setText("free form");
		txtFreeForm.setColumns(10);
		
		JLabel lblSingleA = new JLabel("single a");
		lblSingleA.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblOneByOne = new JLabel("one by one");
		lblOneByOne.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTwoByTwo = new JLabel("two by two");
		lblTwoByTwo.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblBinomial = new JLabel("binomial");
		lblBinomial.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblTrinomial = new JLabel("trinomial");
		lblTrinomial.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblQuadratic = new JLabel("quadratic");
		lblQuadratic.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblFreeForm = new JLabel("free form");
		lblFreeForm.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton)
								.addGap(135))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblFreeForm)
									.addComponent(lblQuadratic)
									.addComponent(lblTrinomial)
									.addComponent(lblBinomial)
									.addComponent(lblTwoByTwo)
									.addComponent(lblOneByOne)
									.addComponent(lblSingleA))
								.addGap(29)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtSingleA, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtOneByTwo, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtTwoByTwo, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtBinomial, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtTrinomial, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtQuadratic, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
									.addComponent(txtFreeForm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblPolynomialExpressionGenerator)
							.addGap(57))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(lblPolynomialExpressionGenerator)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(70)
							.addComponent(lblTwoByTwo))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtSingleA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSingleA))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtOneByTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOneByOne))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(6)
								.addComponent(txtTwoByTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtBinomial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblBinomial)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTrinomial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTrinomial))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtQuadratic, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuadratic))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtFreeForm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblFreeForm)))))
					.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
