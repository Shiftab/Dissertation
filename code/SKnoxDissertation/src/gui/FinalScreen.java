package gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import control.Control;
import extras.Stats;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FinalScreen extends JPanel {

	private Control parent;
	DecimalFormat format = new DecimalFormat("#.##");

	JLabel lbName1 = new JLabel("pupil"), lbName2 = new JLabel("pupil"),
			lbName3 = new JLabel("pupil"), lbName4 = new JLabel("pupil"),
			lbName5 = new JLabel("pupil"), lbName6 = new JLabel("pupil");
	List<JLabel> names = new ArrayList<JLabel>(Arrays.asList(lbName1, lbName4,
			lbName2, lbName5, lbName3, lbName6));
	JLabel lbTimeTaken = new JLabel("time"), lbPercentageCompleat = new JLabel(
			"percent");
	JLabel lbAsked1 = new JLabel("asked"), lbAsked2 = new JLabel("asked"),
			lbAsked3 = new JLabel("asked"), lbAsked4 = new JLabel("asked"),
			lbAsked5 = new JLabel("asked"), lbAsked6 = new JLabel("asked");
	List<JLabel> asked = new ArrayList<JLabel>(Arrays.asList(lbAsked1,
			lbAsked4, lbAsked2, lbAsked5, lbAsked3, lbAsked6));
	JLabel lbAnswers1 = new JLabel("answer"),
			lbAnswers2 = new JLabel("answer"),
			lbAnswers3 = new JLabel("answer"),
			lbAnswers4 = new JLabel("answer"),
			lbAnswers5 = new JLabel("answer"),
			lbAnswers6 = new JLabel("answer");
	List<JLabel> answers = new ArrayList<JLabel>(Arrays.asList(lbAnswers1,
			lbAnswers4, lbAnswers2, lbAnswers5, lbAnswers3, lbAnswers6));
	JLabel lbInput1 = new JLabel("input"), lbInput2 = new JLabel("input"),
			lbInput3 = new JLabel("input"), lbInput4 = new JLabel("input"),
			lbInput5 = new JLabel("input"), lbInput6 = new JLabel("input");
	List<JLabel> input = new ArrayList<JLabel>(Arrays.asList(lbInput1,
			lbInput4, lbInput2, lbInput5, lbInput3, lbInput6));
	JLabel lbTimeShy1 = new JLabel("shy"), lbTimeShy2 = new JLabel("shy"),
			lbTimeShy3 = new JLabel("shy"), lbTimeShy4 = new JLabel("shy"),
			lbTimeShy5 = new JLabel("shy"), lbTimeShy6 = new JLabel("shy");
	List<JLabel> shy = new ArrayList<JLabel>(Arrays.asList(lbTimeShy1,
			lbTimeShy4, lbTimeShy2, lbTimeShy5, lbTimeShy3, lbTimeShy6));
	JLabel lbTimeDistr1 = new JLabel("distracted"), lbTimeDistr2 = new JLabel(
			"distracted"), lbTimeDistr3 = new JLabel("distracted"),
			lbTimeDistr4 = new JLabel("distracted"), lbTimeDistr5 = new JLabel(
					"distracted"), lbTimeDistr6 = new JLabel("distracted");
	List<JLabel> distracted = new ArrayList<JLabel>(Arrays.asList(lbTimeDistr1,
			lbTimeDistr4, lbTimeDistr2, lbTimeDistr5, lbTimeDistr3,
			lbTimeDistr6));

	JLabel lblQuestionsAsked = new JLabel("Questions Asked: ");

	JLabel lblQuestionsAnswered = new JLabel("Questions Answered:");

	JLabel lblPercentageInput = new JLabel("Percentage Input:");

	JLabel lblTimeWastedDue = new JLabel("Time wasted due to Shyness:");

	JLabel lblTimeWastedDue_1 = new JLabel("Time wasted due to distractions: ");

	JLabel lbQ2 = new JLabel("Questions Asked: "), lbAn2 = new JLabel(
			"Questions Answered:"), lbP2 = new JLabel("Percentage Input:"),
			lbS2 = new JLabel("Time wasted due to Shyness:"),
			lbD2 = new JLabel("Time wasted due to distractions: "),
			lbQ3 = new JLabel("Questions Asked: "), lbAn3 = new JLabel(
					"Questions Answered:"), lbP3 = new JLabel(
					"Percentage Input:"), lbS3 = new JLabel(
					"Time wasted due to Shyness:"), lbD3 = new JLabel(
					"Time wasted due to distractions: "), lbQ4 = new JLabel(
					"Questions Asked: "), lbAn4 = new JLabel(
					"Questions Answered:"), lbP4 = new JLabel(
					"Percentage Input:"), lbS4 = new JLabel(
					"Time wasted due to Shyness:"), lbD4 = new JLabel(
					"Time wasted due to distractions: "), lbQ5 = new JLabel(
					"Questions Asked: "), lbAn5 = new JLabel(
					"Questions Answered:"), lbP5 = new JLabel(
					"Percentage Input:"), lbS5 = new JLabel(
					"Time wasted due to Shyness:"), lbD5 = new JLabel(
					"Time wasted due to distractions: "), lbQ6 = new JLabel(
					"Questions Asked: "), lbAn6 = new JLabel(
					"Questions Answered:"), lbP6 = new JLabel(
					"Percentage Input:"), lbS6 = new JLabel(
					"Time wasted due to Shyness:"), lbD6 = new JLabel(
					"Time wasted due to distractions: ");

	List<List<JLabel>> labels = new ArrayList<List<JLabel>>(Arrays.asList(
			Arrays.asList(lbQ2, lbAn2, lbP2, lbS2, lbD2),
			Arrays.asList(lbQ5, lbAn5, lbP5, lbS5, lbD5),
			Arrays.asList(lbQ3, lbAn3, lbP3, lbS3, lbD3),
			Arrays.asList(lbQ6, lbAn6, lbP6, lbS6, lbD6)));

	private final JLabel lbFormat = new JLabel(" ");
	private final JButton btnRestart = new JButton("Re-Start");

	/**
	 * Create the application.
	 */
	public FinalScreen(Control parent) {
		this.parent = parent;
		initialize();
	}

	public void display(Map<String, Stats> pupils, double percentCompleat,
			long timeTaken) {

		lbTimeTaken.setText(String.valueOf(format.format(time(timeTaken))));
		if(percentCompleat==100)
		lbPercentageCompleat.setText(String.valueOf(format.format(percentCompleat)));
		else
			lbPercentageCompleat.setText(String.valueOf(format.format(100-percentCompleat)));
		for (int x = 0; x < 6; x++) {
			boolean vis = true;
			if (x +1 > pupils.size())
				vis = false;

			if(x>=2){
					for(JLabel l: labels.get(x-2)){
						l.setVisible(vis);
					}
			}
			names.get(x).setVisible(vis);
			asked.get(x).setVisible(vis);
			answers.get(x).setVisible(vis);
			input.get(x).setVisible(vis);
			shy.get(x).setVisible(vis);
			distracted.get(x).setVisible(vis);
		}

		int count = 0;
		for (String name : pupils.keySet()) {
			names.get(count).setText(name);
			asked.get(count).setText(
					String.valueOf(pupils.get(name).getAsked()));
			answers.get(count).setText(
					String.valueOf(pupils.get(name).getAnswered()));
			input.get(count).setText(
					String.valueOf(format.format(((pupils.get(name).getAsked() + pupils.get(
							name).getAnswered()) / ((pupils.get(name)
							.getQuestions()+pupils.get(name).getAsked())* 2.0)) * 100.0)));
			shy.get(count).setText(
					String.valueOf(format.format(time(pupils.get(name)
							.getShyMissed()))));
			distracted.get(count).setText(
					String.valueOf(format.format(time(pupils.get(name)
							.getDistractions()))));
			count++;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{81, 16, 14, 23, 25, 26, 63, 1, 115, 14, 48, 26, 94, 115, 14, 45, 5, 14, 79, 58, 4, 0};
		gridBagLayout.rowHeights = new int[]{29, 30, 16, 16, 12, 16, 16, 16, 16, 16, 16, 16, 40, 16, 16, 16, 16, 16, 16, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
				JLabel lblSummary = new JLabel("Summary");
				GridBagConstraints gbc_lblSummary = new GridBagConstraints();
				gbc_lblSummary.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblSummary.gridwidth = 2;
				gbc_lblSummary.anchor = GridBagConstraints.NORTH;
				gbc_lblSummary.insets = new Insets(0, 0, 5, 5);
				gbc_lblSummary.gridx = 0;
				gbc_lblSummary.gridy = 0;
				add(lblSummary, gbc_lblSummary);
				btnRestart.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						parent.changeView(Control.MAIN);
						parent.resetProblem();
					}
				});
				GridBagConstraints gbc_btnRestart = new GridBagConstraints();
				gbc_btnRestart.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnRestart.insets = new Insets(0, 0, 5, 5);
				gbc_btnRestart.gridx = 8;
				gbc_btnRestart.gridy = 0;
				add(btnRestart, gbc_btnRestart);
				GridBagConstraints gbc_lbFormat = new GridBagConstraints();
				gbc_lbFormat.anchor = GridBagConstraints.NORTHWEST;
				gbc_lbFormat.insets = new Insets(0, 0, 5, 0);
				gbc_lbFormat.gridx = 20;
				gbc_lbFormat.gridy = 0;
				add(lbFormat, gbc_lbFormat);
				
						JLabel lblTimeTaken = new JLabel("Time Taken: ");
						GridBagConstraints gbc_lblTimeTaken = new GridBagConstraints();
						gbc_lblTimeTaken.fill = GridBagConstraints.HORIZONTAL;
						gbc_lblTimeTaken.gridwidth = 2;
						gbc_lblTimeTaken.anchor = GridBagConstraints.NORTH;
						gbc_lblTimeTaken.insets = new Insets(0, 0, 5, 5);
						gbc_lblTimeTaken.gridx = 0;
						gbc_lblTimeTaken.gridy = 2;
						add(lblTimeTaken, gbc_lblTimeTaken);
				GridBagConstraints gbc_lbTimeTaken = new GridBagConstraints();
				gbc_lbTimeTaken.anchor = GridBagConstraints.NORTHWEST;
				gbc_lbTimeTaken.insets = new Insets(0, 0, 5, 5);
				gbc_lbTimeTaken.gridwidth = 2;
				gbc_lbTimeTaken.gridx = 2;
				gbc_lbTimeTaken.gridy = 2;
				add(lbTimeTaken, gbc_lbTimeTaken);
		
				JLabel lblPercentageCompleat = new JLabel("Percentage Compleat: ");
				GridBagConstraints gbc_lblPercentageCompleat = new GridBagConstraints();
				gbc_lblPercentageCompleat.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblPercentageCompleat.anchor = GridBagConstraints.NORTH;
				gbc_lblPercentageCompleat.insets = new Insets(0, 0, 5, 5);
				gbc_lblPercentageCompleat.gridwidth = 4;
				gbc_lblPercentageCompleat.gridx = 0;
				gbc_lblPercentageCompleat.gridy = 3;
				add(lblPercentageCompleat, gbc_lblPercentageCompleat);
		GridBagConstraints gbc_lbPercentageCompleat = new GridBagConstraints();
		gbc_lbPercentageCompleat.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbPercentageCompleat.insets = new Insets(0, 0, 5, 5);
		gbc_lbPercentageCompleat.gridwidth = 2;
		gbc_lbPercentageCompleat.gridx = 4;
		gbc_lbPercentageCompleat.gridy = 3;
		add(lbPercentageCompleat, gbc_lbPercentageCompleat);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 7;
		gbc_separator.gridy = 4;
		add(separator, gbc_separator);
		
				JLabel lblPupils = new JLabel("Pupils");
				GridBagConstraints gbc_lblPupils = new GridBagConstraints();
				gbc_lblPupils.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblPupils.anchor = GridBagConstraints.NORTH;
				gbc_lblPupils.insets = new Insets(0, 0, 5, 5);
				gbc_lblPupils.gridx = 0;
				gbc_lblPupils.gridy = 5;
				add(lblPupils, gbc_lblPupils);
		GridBagConstraints gbc_lbName1 = new GridBagConstraints();
		gbc_lbName1.anchor = GridBagConstraints.NORTH;
		gbc_lbName1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName1.insets = new Insets(0, 0, 5, 5);
		gbc_lbName1.gridwidth = 2;
		gbc_lbName1.gridx = 0;
		gbc_lbName1.gridy = 6;
		add(lbName1, gbc_lbName1);
		GridBagConstraints gbc_lbName2 = new GridBagConstraints();
		gbc_lbName2.anchor = GridBagConstraints.NORTH;
		gbc_lbName2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName2.insets = new Insets(0, 0, 5, 5);
		gbc_lbName2.gridwidth = 5;
		gbc_lbName2.gridx = 8;
		gbc_lbName2.gridy = 6;
		add(lbName2, gbc_lbName2);
		GridBagConstraints gbc_lbName3 = new GridBagConstraints();
		gbc_lbName3.anchor = GridBagConstraints.NORTH;
		gbc_lbName3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName3.insets = new Insets(0, 0, 5, 5);
		gbc_lbName3.gridwidth = 2;
		gbc_lbName3.gridx = 13;
		gbc_lbName3.gridy = 6;
		add(lbName3, gbc_lbName3);
		GridBagConstraints gbc_lblQuestionsAsked = new GridBagConstraints();
		gbc_lblQuestionsAsked.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuestionsAsked.anchor = GridBagConstraints.NORTH;
		gbc_lblQuestionsAsked.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuestionsAsked.gridwidth = 4;
		gbc_lblQuestionsAsked.gridx = 0;
		gbc_lblQuestionsAsked.gridy = 7;
		add(lblQuestionsAsked, gbc_lblQuestionsAsked);
		GridBagConstraints gbc_lbAsked1 = new GridBagConstraints();
		gbc_lbAsked1.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbAsked1.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked1.gridx = 4;
		gbc_lbAsked1.gridy = 7;
		add(lbAsked1, gbc_lbAsked1);
		GridBagConstraints gbc_lbQ2 = new GridBagConstraints();
		gbc_lbQ2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbQ2.insets = new Insets(0, 0, 5, 5);
		gbc_lbQ2.gridx = 8;
		gbc_lbQ2.gridy = 7;
		add(lbQ2, gbc_lbQ2);
		GridBagConstraints gbc_lbAsked2 = new GridBagConstraints();
		gbc_lbAsked2.anchor = GridBagConstraints.NORTH;
		gbc_lbAsked2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAsked2.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked2.gridwidth = 2;
		gbc_lbAsked2.gridx = 9;
		gbc_lbAsked2.gridy = 7;
		add(lbAsked2, gbc_lbAsked2);
		GridBagConstraints gbc_lbQ3 = new GridBagConstraints();
		gbc_lbQ3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbQ3.insets = new Insets(0, 0, 5, 5);
		gbc_lbQ3.gridx = 13;
		gbc_lbQ3.gridy = 7;
		add(lbQ3, gbc_lbQ3);
		GridBagConstraints gbc_lbAsked3 = new GridBagConstraints();
		gbc_lbAsked3.anchor = GridBagConstraints.NORTH;
		gbc_lbAsked3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAsked3.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked3.gridwidth = 2;
		gbc_lbAsked3.gridx = 14;
		gbc_lbAsked3.gridy = 7;
		add(lbAsked3, gbc_lbAsked3);
		GridBagConstraints gbc_lblQuestionsAnswered = new GridBagConstraints();
		gbc_lblQuestionsAnswered.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuestionsAnswered.anchor = GridBagConstraints.NORTH;
		gbc_lblQuestionsAnswered.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuestionsAnswered.gridwidth = 4;
		gbc_lblQuestionsAnswered.gridx = 0;
		gbc_lblQuestionsAnswered.gridy = 8;
		add(lblQuestionsAnswered, gbc_lblQuestionsAnswered);
		GridBagConstraints gbc_lbAnswers1 = new GridBagConstraints();
		gbc_lbAnswers1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAnswers1.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers1.gridwidth = 2;
		gbc_lbAnswers1.gridx = 4;
		gbc_lbAnswers1.gridy = 8;
		add(lbAnswers1, gbc_lbAnswers1);
		GridBagConstraints gbc_lbAn2 = new GridBagConstraints();
		gbc_lbAn2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAn2.insets = new Insets(0, 0, 5, 5);
		gbc_lbAn2.gridwidth = 2;
		gbc_lbAn2.gridx = 8;
		gbc_lbAn2.gridy = 8;
		add(lbAn2, gbc_lbAn2);
		GridBagConstraints gbc_lbAnswers2 = new GridBagConstraints();
		gbc_lbAnswers2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAnswers2.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers2.gridwidth = 2;
		gbc_lbAnswers2.gridx = 10;
		gbc_lbAnswers2.gridy = 8;
		add(lbAnswers2, gbc_lbAnswers2);
		GridBagConstraints gbc_lbAn3 = new GridBagConstraints();
		gbc_lbAn3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAn3.insets = new Insets(0, 0, 5, 5);
		gbc_lbAn3.gridwidth = 2;
		gbc_lbAn3.gridx = 13;
		gbc_lbAn3.gridy = 8;
		add(lbAn3, gbc_lbAn3);
		GridBagConstraints gbc_lbAnswers3 = new GridBagConstraints();
		gbc_lbAnswers3.anchor = GridBagConstraints.NORTH;
		gbc_lbAnswers3.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers3.gridwidth = 2;
		gbc_lbAnswers3.gridx = 15;
		gbc_lbAnswers3.gridy = 8;
		add(lbAnswers3, gbc_lbAnswers3);
		GridBagConstraints gbc_lblPercentageInput = new GridBagConstraints();
		gbc_lblPercentageInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPercentageInput.anchor = GridBagConstraints.NORTH;
		gbc_lblPercentageInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblPercentageInput.gridwidth = 4;
		gbc_lblPercentageInput.gridx = 0;
		gbc_lblPercentageInput.gridy = 9;
		add(lblPercentageInput, gbc_lblPercentageInput);
		GridBagConstraints gbc_lbInput1 = new GridBagConstraints();
		gbc_lbInput1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbInput1.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput1.gridx = 4;
		gbc_lbInput1.gridy = 9;
		add(lbInput1, gbc_lbInput1);
		GridBagConstraints gbc_lbP2 = new GridBagConstraints();
		gbc_lbP2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbP2.insets = new Insets(0, 0, 5, 5);
		gbc_lbP2.gridx = 8;
		gbc_lbP2.gridy = 9;
		add(lbP2, gbc_lbP2);
		GridBagConstraints gbc_lbInput2 = new GridBagConstraints();
		gbc_lbInput2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbInput2.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput2.gridwidth = 2;
		gbc_lbInput2.gridx = 9;
		gbc_lbInput2.gridy = 9;
		add(lbInput2, gbc_lbInput2);
		GridBagConstraints gbc_lbP3 = new GridBagConstraints();
		gbc_lbP3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbP3.insets = new Insets(0, 0, 5, 5);
		gbc_lbP3.gridx = 13;
		gbc_lbP3.gridy = 9;
		add(lbP3, gbc_lbP3);
		GridBagConstraints gbc_lbInput3 = new GridBagConstraints();
		gbc_lbInput3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbInput3.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput3.gridwidth = 2;
		gbc_lbInput3.gridx = 14;
		gbc_lbInput3.gridy = 9;
		add(lbInput3, gbc_lbInput3);
		GridBagConstraints gbc_lblTimeWastedDue = new GridBagConstraints();
		gbc_lblTimeWastedDue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTimeWastedDue.anchor = GridBagConstraints.NORTH;
		gbc_lblTimeWastedDue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeWastedDue.gridwidth = 5;
		gbc_lblTimeWastedDue.gridx = 0;
		gbc_lblTimeWastedDue.gridy = 10;
		add(lblTimeWastedDue, gbc_lblTimeWastedDue);
		GridBagConstraints gbc_lbTimeShy1 = new GridBagConstraints();
		gbc_lbTimeShy1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy1.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy1.gridx = 5;
		gbc_lbTimeShy1.gridy = 10;
		add(lbTimeShy1, gbc_lbTimeShy1);
		GridBagConstraints gbc_lbS2 = new GridBagConstraints();
		gbc_lbS2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbS2.insets = new Insets(0, 0, 5, 5);
		gbc_lbS2.gridwidth = 3;
		gbc_lbS2.gridx = 8;
		gbc_lbS2.gridy = 10;
		add(lbS2, gbc_lbS2);
		GridBagConstraints gbc_lbTimeShy2 = new GridBagConstraints();
		gbc_lbTimeShy2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy2.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy2.gridx = 11;
		gbc_lbTimeShy2.gridy = 10;
		add(lbTimeShy2, gbc_lbTimeShy2);
		GridBagConstraints gbc_lbS3 = new GridBagConstraints();
		gbc_lbS3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbS3.insets = new Insets(0, 0, 5, 5);
		gbc_lbS3.gridwidth = 3;
		gbc_lbS3.gridx = 13;
		gbc_lbS3.gridy = 10;
		add(lbS3, gbc_lbS3);
		GridBagConstraints gbc_lbTimeShy3 = new GridBagConstraints();
		gbc_lbTimeShy3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy3.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy3.gridwidth = 3;
		gbc_lbTimeShy3.gridx = 16;
		gbc_lbTimeShy3.gridy = 10;
		add(lbTimeShy3, gbc_lbTimeShy3);
		GridBagConstraints gbc_lblTimeWastedDue_1 = new GridBagConstraints();
		gbc_lblTimeWastedDue_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTimeWastedDue_1.anchor = GridBagConstraints.NORTH;
		gbc_lblTimeWastedDue_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeWastedDue_1.gridwidth = 6;
		gbc_lblTimeWastedDue_1.gridx = 0;
		gbc_lblTimeWastedDue_1.gridy = 11;
		add(lblTimeWastedDue_1, gbc_lblTimeWastedDue_1);
		GridBagConstraints gbc_lbTimeDistr1 = new GridBagConstraints();
		gbc_lbTimeDistr1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeDistr1.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeDistr1.gridx = 6;
		gbc_lbTimeDistr1.gridy = 11;
		add(lbTimeDistr1, gbc_lbTimeDistr1);
		GridBagConstraints gbc_lbD2 = new GridBagConstraints();
		gbc_lbD2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbD2.insets = new Insets(0, 0, 5, 5);
		gbc_lbD2.gridwidth = 4;
		gbc_lbD2.gridx = 8;
		gbc_lbD2.gridy = 11;
		add(lbD2, gbc_lbD2);
		GridBagConstraints gbc_lbTimeDistr2 = new GridBagConstraints();
		gbc_lbTimeDistr2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeDistr2.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeDistr2.gridx = 12;
		gbc_lbTimeDistr2.gridy = 11;
		add(lbTimeDistr2, gbc_lbTimeDistr2);
		GridBagConstraints gbc_lbD3 = new GridBagConstraints();
		gbc_lbD3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbD3.insets = new Insets(0, 0, 5, 5);
		gbc_lbD3.gridwidth = 4;
		gbc_lbD3.gridx = 13;
		gbc_lbD3.gridy = 11;
		add(lbD3, gbc_lbD3);
		GridBagConstraints gbc_lbTimeDistr3 = new GridBagConstraints();
		gbc_lbTimeDistr3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeDistr3.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeDistr3.gridx = 17;
		gbc_lbTimeDistr3.gridy = 11;
		add(lbTimeDistr3, gbc_lbTimeDistr3);
		GridBagConstraints gbc_lbName4 = new GridBagConstraints();
		gbc_lbName4.anchor = GridBagConstraints.NORTH;
		gbc_lbName4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName4.insets = new Insets(0, 0, 5, 5);
		gbc_lbName4.gridwidth = 2;
		gbc_lbName4.gridx = 0;
		gbc_lbName4.gridy = 13;
		add(lbName4, gbc_lbName4);
		GridBagConstraints gbc_lbName5 = new GridBagConstraints();
		gbc_lbName5.anchor = GridBagConstraints.NORTH;
		gbc_lbName5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName5.insets = new Insets(0, 0, 5, 5);
		gbc_lbName5.gridx = 8;
		gbc_lbName5.gridy = 13;
		add(lbName5, gbc_lbName5);
		GridBagConstraints gbc_lbName6 = new GridBagConstraints();
		gbc_lbName6.anchor = GridBagConstraints.NORTH;
		gbc_lbName6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbName6.insets = new Insets(0, 0, 5, 5);
		gbc_lbName6.gridx = 13;
		gbc_lbName6.gridy = 13;
		add(lbName6, gbc_lbName6);
		GridBagConstraints gbc_lbQ4 = new GridBagConstraints();
		gbc_lbQ4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbQ4.anchor = GridBagConstraints.NORTH;
		gbc_lbQ4.insets = new Insets(0, 0, 5, 5);
		gbc_lbQ4.gridwidth = 4;
		gbc_lbQ4.gridx = 0;
		gbc_lbQ4.gridy = 14;
		add(lbQ4, gbc_lbQ4);
		GridBagConstraints gbc_lbAsked4 = new GridBagConstraints();
		gbc_lbAsked4.anchor = GridBagConstraints.NORTH;
		gbc_lbAsked4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAsked4.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked4.gridx = 4;
		gbc_lbAsked4.gridy = 14;
		add(lbAsked4, gbc_lbAsked4);
		GridBagConstraints gbc_lbQ5 = new GridBagConstraints();
		gbc_lbQ5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbQ5.insets = new Insets(0, 0, 5, 5);
		gbc_lbQ5.gridx = 8;
		gbc_lbQ5.gridy = 14;
		add(lbQ5, gbc_lbQ5);
		GridBagConstraints gbc_lbAsked5 = new GridBagConstraints();
		gbc_lbAsked5.anchor = GridBagConstraints.NORTH;
		gbc_lbAsked5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAsked5.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked5.gridwidth = 2;
		gbc_lbAsked5.gridx = 9;
		gbc_lbAsked5.gridy = 14;
		add(lbAsked5, gbc_lbAsked5);
		GridBagConstraints gbc_lbQ6 = new GridBagConstraints();
		gbc_lbQ6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbQ6.insets = new Insets(0, 0, 5, 5);
		gbc_lbQ6.gridx = 13;
		gbc_lbQ6.gridy = 14;
		add(lbQ6, gbc_lbQ6);
		GridBagConstraints gbc_lbAsked6 = new GridBagConstraints();
		gbc_lbAsked6.anchor = GridBagConstraints.NORTH;
		gbc_lbAsked6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAsked6.insets = new Insets(0, 0, 5, 5);
		gbc_lbAsked6.gridwidth = 2;
		gbc_lbAsked6.gridx = 14;
		gbc_lbAsked6.gridy = 14;
		add(lbAsked6, gbc_lbAsked6);
		GridBagConstraints gbc_lbAn4 = new GridBagConstraints();
		gbc_lbAn4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAn4.anchor = GridBagConstraints.NORTH;
		gbc_lbAn4.insets = new Insets(0, 0, 5, 5);
		gbc_lbAn4.gridwidth = 4;
		gbc_lbAn4.gridx = 0;
		gbc_lbAn4.gridy = 15;
		add(lbAn4, gbc_lbAn4);
		GridBagConstraints gbc_lbAnswers4 = new GridBagConstraints();
		gbc_lbAnswers4.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAnswers4.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers4.gridx = 4;
		gbc_lbAnswers4.gridy = 15;
		add(lbAnswers4, gbc_lbAnswers4);
		GridBagConstraints gbc_lbAn5 = new GridBagConstraints();
		gbc_lbAn5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAn5.insets = new Insets(0, 0, 5, 5);
		gbc_lbAn5.gridwidth = 2;
		gbc_lbAn5.gridx = 8;
		gbc_lbAn5.gridy = 15;
		add(lbAn5, gbc_lbAn5);
		GridBagConstraints gbc_lbAnswers5 = new GridBagConstraints();
		gbc_lbAnswers5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAnswers5.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers5.gridwidth = 2;
		gbc_lbAnswers5.gridx = 10;
		gbc_lbAnswers5.gridy = 15;
		add(lbAnswers5, gbc_lbAnswers5);
		GridBagConstraints gbc_lbAn6 = new GridBagConstraints();
		gbc_lbAn6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbAn6.insets = new Insets(0, 0, 5, 5);
		gbc_lbAn6.gridwidth = 2;
		gbc_lbAn6.gridx = 13;
		gbc_lbAn6.gridy = 15;
		add(lbAn6, gbc_lbAn6);
		GridBagConstraints gbc_lbAnswers6 = new GridBagConstraints();
		gbc_lbAnswers6.anchor = GridBagConstraints.NORTH;
		gbc_lbAnswers6.insets = new Insets(0, 0, 5, 5);
		gbc_lbAnswers6.gridwidth = 2;
		gbc_lbAnswers6.gridx = 15;
		gbc_lbAnswers6.gridy = 15;
		add(lbAnswers6, gbc_lbAnswers6);
		GridBagConstraints gbc_lbP4 = new GridBagConstraints();
		gbc_lbP4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbP4.anchor = GridBagConstraints.NORTH;
		gbc_lbP4.insets = new Insets(0, 0, 5, 5);
		gbc_lbP4.gridwidth = 3;
		gbc_lbP4.gridx = 0;
		gbc_lbP4.gridy = 16;
		add(lbP4, gbc_lbP4);
		GridBagConstraints gbc_lbInput4 = new GridBagConstraints();
		gbc_lbInput4.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbInput4.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput4.gridx = 4;
		gbc_lbInput4.gridy = 16;
		add(lbInput4, gbc_lbInput4);
		GridBagConstraints gbc_lbP5 = new GridBagConstraints();
		gbc_lbP5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbP5.insets = new Insets(0, 0, 5, 5);
		gbc_lbP5.gridx = 8;
		gbc_lbP5.gridy = 16;
		add(lbP5, gbc_lbP5);
		GridBagConstraints gbc_lbInput5 = new GridBagConstraints();
		gbc_lbInput5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbInput5.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput5.gridwidth = 2;
		gbc_lbInput5.gridx = 9;
		gbc_lbInput5.gridy = 16;
		add(lbInput5, gbc_lbInput5);
		GridBagConstraints gbc_lbP6 = new GridBagConstraints();
		gbc_lbP6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbP6.insets = new Insets(0, 0, 5, 5);
		gbc_lbP6.gridx = 13;
		gbc_lbP6.gridy = 16;
		add(lbP6, gbc_lbP6);
		GridBagConstraints gbc_lbInput6 = new GridBagConstraints();
		gbc_lbInput6.anchor = GridBagConstraints.NORTH;
		gbc_lbInput6.insets = new Insets(0, 0, 5, 5);
		gbc_lbInput6.gridwidth = 2;
		gbc_lbInput6.gridx = 14;
		gbc_lbInput6.gridy = 16;
		add(lbInput6, gbc_lbInput6);
		GridBagConstraints gbc_lbS4 = new GridBagConstraints();
		gbc_lbS4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbS4.anchor = GridBagConstraints.NORTH;
		gbc_lbS4.insets = new Insets(0, 0, 5, 5);
		gbc_lbS4.gridwidth = 5;
		gbc_lbS4.gridx = 0;
		gbc_lbS4.gridy = 17;
		add(lbS4, gbc_lbS4);
		GridBagConstraints gbc_lbTimeShy4 = new GridBagConstraints();
		gbc_lbTimeShy4.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy4.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy4.gridwidth = 2;
		gbc_lbTimeShy4.gridx = 5;
		gbc_lbTimeShy4.gridy = 17;
		add(lbTimeShy4, gbc_lbTimeShy4);
		GridBagConstraints gbc_lbS5 = new GridBagConstraints();
		gbc_lbS5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbS5.insets = new Insets(0, 0, 5, 5);
		gbc_lbS5.gridwidth = 3;
		gbc_lbS5.gridx = 8;
		gbc_lbS5.gridy = 17;
		add(lbS5, gbc_lbS5);
		GridBagConstraints gbc_lbTimeShy5 = new GridBagConstraints();
		gbc_lbTimeShy5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy5.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy5.gridx = 11;
		gbc_lbTimeShy5.gridy = 17;
		add(lbTimeShy5, gbc_lbTimeShy5);
		GridBagConstraints gbc_lbS6 = new GridBagConstraints();
		gbc_lbS6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbS6.insets = new Insets(0, 0, 5, 5);
		gbc_lbS6.gridwidth = 3;
		gbc_lbS6.gridx = 13;
		gbc_lbS6.gridy = 17;
		add(lbS6, gbc_lbS6);
		GridBagConstraints gbc_lbTimeShy6 = new GridBagConstraints();
		gbc_lbTimeShy6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTimeShy6.insets = new Insets(0, 0, 5, 5);
		gbc_lbTimeShy6.gridwidth = 2;
		gbc_lbTimeShy6.gridx = 16;
		gbc_lbTimeShy6.gridy = 17;
		add(lbTimeShy6, gbc_lbTimeShy6);
		GridBagConstraints gbc_lbD4 = new GridBagConstraints();
		gbc_lbD4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbD4.anchor = GridBagConstraints.NORTH;
		gbc_lbD4.insets = new Insets(0, 0, 0, 5);
		gbc_lbD4.gridwidth = 6;
		gbc_lbD4.gridx = 0;
		gbc_lbD4.gridy = 18;
		add(lbD4, gbc_lbD4);
		GridBagConstraints gbc_lbTimeDistr4 = new GridBagConstraints();
		gbc_lbTimeDistr4.anchor = GridBagConstraints.NORTH;
		gbc_lbTimeDistr4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbTimeDistr4.insets = new Insets(0, 0, 0, 5);
		gbc_lbTimeDistr4.gridwidth = 2;
		gbc_lbTimeDistr4.gridx = 6;
		gbc_lbTimeDistr4.gridy = 18;
		add(lbTimeDistr4, gbc_lbTimeDistr4);
		GridBagConstraints gbc_lbD5 = new GridBagConstraints();
		gbc_lbD5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbD5.insets = new Insets(0, 0, 0, 5);
		gbc_lbD5.gridwidth = 4;
		gbc_lbD5.gridx = 8;
		gbc_lbD5.gridy = 18;
		add(lbD5, gbc_lbD5);
		GridBagConstraints gbc_lbTimeDistr5 = new GridBagConstraints();
		gbc_lbTimeDistr5.anchor = GridBagConstraints.NORTH;
		gbc_lbTimeDistr5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbTimeDistr5.insets = new Insets(0, 0, 0, 5);
		gbc_lbTimeDistr5.gridx = 12;
		gbc_lbTimeDistr5.gridy = 18;
		add(lbTimeDistr5, gbc_lbTimeDistr5);
		GridBagConstraints gbc_lbD6 = new GridBagConstraints();
		gbc_lbD6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbD6.insets = new Insets(0, 0, 0, 5);
		gbc_lbD6.gridwidth = 4;
		gbc_lbD6.gridx = 13;
		gbc_lbD6.gridy = 18;
		add(lbD6, gbc_lbD6);
		GridBagConstraints gbc_lbTimeDistr6 = new GridBagConstraints();
		gbc_lbTimeDistr6.anchor = GridBagConstraints.NORTH;
		gbc_lbTimeDistr6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbTimeDistr6.insets = new Insets(0, 0, 0, 5);
		gbc_lbTimeDistr6.gridx = 17;
		gbc_lbTimeDistr6.gridy = 18;
		add(lbTimeDistr6, gbc_lbTimeDistr6);
	}

	private double time(long time) {
		return time / 6000.0; // to minuets
	}
}
