package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import time.CountTime;
import time.LifeGameTime;

public class ButtonListener implements ActionListener {

	private boolean[][] cell;
	private JFrame frame;
	private Timer countTimer, lifeGameTimer;
	private CountTime countTime;
	private LifeGameTime lifeGameTime;

	public ButtonListener(JFrame frame, JLabel label, boolean[][] cell) {
		this.cell = cell;
		this.frame = frame;

		countTimer = new Timer();
		countTime = new CountTime(label);
		countTime.setStartFlag(false);
		countTimer.schedule(countTime, 1000, 100);

		lifeGameTimer = new Timer();
		lifeGameTime = new LifeGameTime(cell, frame);
		lifeGameTime.setStartFlag(false);
		lifeGameTimer.schedule(lifeGameTime, 1000, 100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = ((JButton) e.getSource()).getText();
		// System.out.println(code);

		if (code.equals("Start")) {

			countTime.setStartFlag(true);
			lifeGameTime.setStartFlag(true);

		} else if (code.equals("Stop")) {

			countTime.setStartFlag(false);
			lifeGameTime.setStartFlag(false);

		} else if (code.equals("Reset")) {

			for (int i = 0; i < cell.length; ++i) {
				for (int j = 0; j < cell[i].length; ++j) {
					cell[i][j] = false;
				}
			}

			frame.update(null);
		} else if (code.equals("TimeReset")) {

			countTime.reset();

		}

	}

}
