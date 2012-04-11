package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import agent.Knowledge.Personality;

import control.Control;
import javax.swing.JTextField;

public class PupilEddit extends GUITemplate{
	
	
	//JFrame frame;	//needs to be here for the design edditor to work, can't be there when running
	
	private JTextField txtName;
	JTextPane txtpnAbility;
	JTextPane txtpnPersonal;
	
	private Personality personality;
	private String name;
	
	
	/**
	 * Create the application.
	 */
	public PupilEddit(Control parent) {
		super(parent);
		initialize();
	}
	
	public void setUp(String name, Personality personality){
		txtName.setText(name);
		this.personality = personality;
		this.name = name;
		setAbilityText(personality);
		setPersonalityText(personality);
	}
	
	private void setAbilityText(Personality personality){
		double[] ability = personality.getAbility();
		//temporary TODO: implement nlg
		txtpnAbility.setText("operational="+ability[0]+"\nnumberConceptual="+ability[1]+"\nnumberComparative="+ability[2]+
				"\nabstractSymbolic="+ability[3]+"\ngraphical="+ability[4]+"\nspatialTemporal="+ability[5]);
	}
	
	private void setPersonalityText(Personality personality){
		double[] ocean = personality.getOCEAN();
		txtpnPersonal.setText("openness="+ocean[0]+"\nconscientiousness="+ocean[1]+"\nextraversion="+ocean[2]+
				"\nagreeableness="+ocean[3]+"\nneuroticism="+ocean[4]);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblPupilView = new JLabel("Pupil View");

		JLabel lblAbility = new JLabel("Ability:");

		txtpnAbility = new JTextPane();
		txtpnAbility.setEditable(false);
		txtpnAbility.setText("this is a \nbox\n");

		JLabel lblPersonality = new JLabel("Personality:");

		txtpnPersonal = new JTextPane();
		txtpnPersonal.setEditable(false);

		JButton btnChangeAbility = new JButton("Change");
		btnChangeAbility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.changeAbility(name);
			}
		});

		JButton btnChangePers = new JButton("Change");
		btnChangePers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.changePersonality(name);
			}
		});
		
		JButton btnBack = new JButton("Save");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.newName(name, txtName.getText());
				frame.setVisible(false);
				parent.changeView(Control.MAIN);
			}
		});
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPupilView)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblAbility)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblPersonality)
										.addComponent(btnChangePers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
										.addComponent(btnBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(btnChangeAbility))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtpnPersonal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
								.addComponent(txtpnAbility, Alignment.LEADING))))
					.addContainerGap(557, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(lblPupilView)
					.addGap(23)
					.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAbility)
							.addGap(18)
							.addComponent(btnChangeAbility))
						.addComponent(txtpnAbility, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPersonality)
							.addGap(18)
							.addComponent(btnChangePers))
						.addComponent(txtpnPersonal, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnBack)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
