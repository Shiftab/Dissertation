package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import control.Control;
import extras.Stats;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalScreen extends GUITemplate {

	private JFrame frame;

	JLabel lbName1 = new JLabel("pupil"), lbName2 = new JLabel("pupil"),
			lbName3 = new JLabel("pupil"), lbName4 = new JLabel("pupil"),
			lbName5 = new JLabel("pupil"), lbName6 = new JLabel("pupil");
	List<JLabel> names = new ArrayList<JLabel>(Arrays.asList(lbName1, lbName2,
			lbName3, lbName4, lbName5, lbName6));
	JLabel lbTimeTaken = new JLabel("time"), lbPercentageCompleat = new JLabel(
			"percent");
	JLabel lbAsked1 = new JLabel("asked"), lbAsked2 = new JLabel("asked"),
			lbAsked3 = new JLabel("asked"), lbAsked4 = new JLabel("asked"),
			lbAsked5 = new JLabel("asked"), lbAsked6 = new JLabel("asked");
	List<JLabel> asked = new ArrayList<JLabel>(Arrays.asList(lbAsked1,
			lbAsked2, lbAsked3, lbAsked4, lbAsked5, lbAsked6));
	JLabel lbAnswers1 = new JLabel("answer"),
			lbAnswers2 = new JLabel("answer"),
			lbAnswers3 = new JLabel("answer"),
			lbAnswers4 = new JLabel("answer"),
			lbAnswers5 = new JLabel("answer"),
			lbAnswers6 = new JLabel("answer");
	List<JLabel> answers = new ArrayList<JLabel>(Arrays.asList(lbAnswers1,
			lbAnswers2, lbAnswers3, lbAnswers4, lbAnswers5, lbAnswers6));
	JLabel lbInput1 = new JLabel("input"), lbInput2 = new JLabel("input"),
			lbInput3 = new JLabel("input"), lbInput4 = new JLabel("input"),
			lbInput5 = new JLabel("input"), lbInput6 = new JLabel("input");
	List<JLabel> input = new ArrayList<JLabel>(Arrays.asList(lbInput1,
			lbInput2, lbInput3, lbInput4, lbInput5, lbInput6));
	JLabel lbTimeShy1 = new JLabel("shy"), lbTimeShy2 = new JLabel("shy"),
			lbTimeShy3 = new JLabel("shy"), lbTimeShy4 = new JLabel("shy"),
			lbTimeShy5 = new JLabel("shy"), lbTimeShy6 = new JLabel("shy");
	List<JLabel> shy = new ArrayList<JLabel>(Arrays.asList(lbTimeShy1,
			lbTimeShy2, lbTimeShy3, lbTimeShy4, lbTimeShy5, lbTimeShy6));
	JLabel lbTimeDistr1 = new JLabel("distracted"), lbTimeDistr2 = new JLabel(
			"distracted"), lbTimeDistr3 = new JLabel("distracted"),
			lbTimeDistr4 = new JLabel("distracted"), lbTimeDistr5 = new JLabel(
					"distracted"), lbTimeDistr6 = new JLabel("distracted");
	List<JLabel> distracted = new ArrayList<JLabel>(Arrays.asList(lbTimeDistr1,
			lbTimeDistr2, lbTimeDistr3, lbTimeDistr4, lbTimeDistr5,
			lbTimeDistr6));
	private final JLabel lbFormat = new JLabel(" ");
	private final JButton btnRestart = new JButton("Re-Start");

	/**
	 * Create the application.
	 */
	public FinalScreen(Control parent) {
		super(parent);
		initialize();
	}

	public void display(Map<String, Stats> pupils, double percentCompleat,
			long timeTaken) {

		lbTimeTaken.setText(String.valueOf(time(timeTaken)));
		lbPercentageCompleat.setText(String.valueOf(percentCompleat));
		for (int x = 0; x < 6; x++) {
			if (x + 1 > pupils.size())
				break;
			names.get(x).setVisible(true);
			asked.get(x).setVisible(true);
			answers.get(x).setVisible(true);
			input.get(x).setVisible(true);
			shy.get(x).setVisible(true);
			distracted.get(x).setVisible(true);
		}

		int count = 0;
		for (String name : pupils.keySet()) {
			names.get(count).setText(name);
			asked.get(count).setText(
					String.valueOf(pupils.get(name).getAsked()));
			answers.get(count).setText(
					String.valueOf(pupils.get(name).getAnswered()));
			input.get(count).setText(
					String.valueOf((((pupils.get(name).getAsked() + pupils.get(
							name).getAnswered()) / (pupils.get(name)
							.getQuestions() * 2)) * 100)));
			shy.get(count).setText(
					String.valueOf(time(pupils.get(name).getShyMissed())));
			distracted.get(count).setText(
					String.valueOf(time(pupils.get(name).getDistractions())));
			count++;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				parent.changeView(Control.MAIN);
			}
		});
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSummary = new JLabel("Summary");

		JLabel lblTimeTaken = new JLabel("Time Taken: ");

		JLabel lblPercentageCompleat = new JLabel("Percentage Compleat: ");

		JLabel lblPupils = new JLabel("Pupils");

		JLabel lblQuestionsAsked = new JLabel("Questions Asked: ");

		JLabel lblQuestionsAnswered = new JLabel("Questions Answered");

		JLabel lblPercentageInput = new JLabel("Percentage Input:");

		JLabel lblTimeWastedDue = new JLabel("Time wasted due to Shyness");

		JLabel lblTimeWastedDue_1 = new JLabel(
				"Time wasted due to distractions: ");

		JLabel label_1 = new JLabel("Questions Asked: ");

		JLabel label_3 = new JLabel("Questions Answered");

		JLabel label_5 = new JLabel("Percentage Input:");

		JLabel label_7 = new JLabel("Time wasted due to Shyness");

		JLabel label_9 = new JLabel("Time wasted due to distractions: ");

		JLabel label_12 = new JLabel("Questions Asked: ");

		JLabel label_14 = new JLabel("Questions Answered");

		JLabel label_16 = new JLabel("Percentage Input:");

		JLabel label_18 = new JLabel("Time wasted due to Shyness");

		JLabel label_20 = new JLabel("Time wasted due to distractions: ");

		JLabel label_23 = new JLabel("Questions Asked: ");

		JLabel label_25 = new JLabel("Questions Answered");

		JLabel label_27 = new JLabel("Percentage Input:");

		JLabel label_29 = new JLabel("Time wasted due to Shyness");

		JLabel label_31 = new JLabel("Time wasted due to distractions: ");

		JLabel label_34 = new JLabel("Questions Asked: ");

		JLabel label_36 = new JLabel("Questions Answered");

		JLabel label_38 = new JLabel("Percentage Input:");

		JLabel label_40 = new JLabel("Time wasted due to Shyness");

		JLabel label_42 = new JLabel("Time wasted due to distractions: ");

		JLabel label_45 = new JLabel("Questions Asked: ");

		JLabel label_47 = new JLabel("Questions Answered");

		JLabel label_49 = new JLabel("Percentage Input:");

		JLabel label_51 = new JLabel("Time wasted due to Shyness");

		JLabel label_53 = new JLabel("Time wasted due to distractions: ");

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_25, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbAnswers4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbInput4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_29, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeShy4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_31, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeDistr4, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTimeWastedDue_1)
									.addGap(18)
									.addComponent(lbTimeDistr1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTimeWastedDue)
									.addGap(18)
									.addComponent(lbTimeShy1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPercentageInput)
									.addGap(18)
									.addComponent(lbInput1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblQuestionsAnswered)
									.addGap(18)
									.addComponent(lbAnswers1))
								.addComponent(lblPupils)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPercentageCompleat)
									.addGap(18)
									.addComponent(lbPercentageCompleat))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTimeTaken)
									.addGap(18)
									.addComponent(lbTimeTaken))
								.addComponent(lblSummary)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbName1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblQuestionsAsked, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(lbAsked1)))
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_36, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbAnswers5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_38, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbInput5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_40, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbTimeShy5, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_42, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbTimeDistr5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbTimeDistr2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbAnswers2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbInput2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lbTimeShy2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbName2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRestart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lbAsked2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbFormat)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbName5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label_34, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(lbAsked5, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbName4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_23, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lbAsked4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_47, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbAnswers6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_49, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbInput6, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_51, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeShy6, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_53, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeDistr6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_20, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeDistr3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbAnswers3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbInput3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbTimeShy3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbName3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_12, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lbAsked3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbName6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_45, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lbAsked6, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRestart)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lbName3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_12)
								.addComponent(lbAsked3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_14)
								.addComponent(lbAnswers3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_16)
								.addComponent(lbInput3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_18)
								.addComponent(lbTimeShy3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_20)
									.addGap(40)
									.addComponent(lbName6)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_45)
										.addComponent(lbAsked6))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_47)
										.addComponent(lbAnswers6)))
								.addComponent(lbTimeDistr3))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_49)
								.addComponent(lbInput6))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_51)
								.addComponent(lbTimeShy6))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lbTimeDistr6)
								.addComponent(label_53)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSummary)
								.addComponent(lbFormat))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTimeTaken)
										.addComponent(lbTimeTaken))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPercentageCompleat)
										.addComponent(lbPercentageCompleat))
									.addGap(18)
									.addComponent(lblPupils)
									.addGap(18)
									.addComponent(lbName1)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblQuestionsAsked)
										.addComponent(lbAsked1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblQuestionsAnswered)
										.addComponent(lbAnswers1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPercentageInput)
										.addComponent(lbInput1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTimeWastedDue)
										.addComponent(lbTimeShy1))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTimeWastedDue_1)
										.addComponent(lbTimeDistr1)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(102)
									.addComponent(lbName2)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1)
										.addComponent(lbAsked2))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_3)
										.addComponent(lbAnswers2))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_5)
										.addComponent(lbInput2))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_7)
										.addComponent(lbTimeShy2))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_9)
										.addComponent(lbTimeDistr2))))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbName5)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_34)
										.addComponent(lbAsked5))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_36)
										.addComponent(lbAnswers5))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_38)
										.addComponent(lbInput5))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_40)
										.addComponent(lbTimeShy5))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbTimeDistr5)
										.addComponent(label_42)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbName4)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_23)
										.addComponent(lbAsked4))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_25)
										.addComponent(lbAnswers4))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_27)
										.addComponent(lbInput4))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_29)
										.addComponent(lbTimeShy4))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbTimeDistr4)
										.addComponent(label_31))))))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private double time(long time){
		return time/6000.0; //to minuets
	}
}
