package time;

import java.util.TimerTask;

import javax.swing.JFrame;

import logic.LifeGameLogic;

public class LifeGameTime extends TimerTask {

	private boolean start_flag;
	private boolean[][] cell;
	private LifeGameLogic lgl;
	private JFrame frame;

	public LifeGameTime(boolean[][] cell, JFrame frame){
		start_flag = false;
		this.cell = cell;
		lgl = new LifeGameLogic();
		this.frame = frame;
	}

	@Override
	public void run() {
		if(start_flag){
			calc();
		}
	}

	private void calc(){
		cell = lgl.nextGene(cell);
		frame.update(null);
	}

	public void setStartFlag(boolean start_flag){
		this.start_flag = start_flag;
	}

}
