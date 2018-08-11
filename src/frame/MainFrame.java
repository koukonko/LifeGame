package frame;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import listener.ButtonListener;
import listener.ClickListener;
import paint.PaintCell;
import paint.PaintLine;
import parts.SetButton;
import parts.SetLabel;

public class MainFrame extends JFrame {

	final private int WIDTH = 1800;
	final private int HEIGHT = 1000;

	private SetButton start;
	private SetButton stop;
	private SetButton reset;
	private SetButton timeReset;

	private SetLabel label;

	private JPanel upside, downside;
	private Graphics g, paintG;

	private boolean[][] cell;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		super("GAME OF LIFE");

		System.out.println("HEIGHT - CELL_SIZE : " + ((HEIGHT - 110) / 5));
		System.out.println("WIDHT  - CELL_SIZE : " + ((WIDTH - 60) / 5));
		// 現世代と次世代の二つの配列が必要になると思われる
		cell = new boolean[(HEIGHT - 110) / 5 - 1][(WIDTH - 60) / 5 - 1];
		for (int i = 0; i < cell.length; ++i) {
			for (int j = 0; j < cell[0].length; ++j) {
				cell[i][j] = false;
			}
		}

		init();
	}

	private void init() {
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createParts();
		mainPanelSet();

		this.setVisible(true);
		this.g = upside.getGraphics();
		new PaintLine(this.g, WIDTH, HEIGHT);

		ClickListener cl = new ClickListener(this.g, cell);
		this.addMouseListener(cl);
		this.addMouseMotionListener(cl);
	}

	private void createParts() {
		downside = new JPanel();
		downside.setLayout(null);
		downside.setBounds(0, HEIGHT - 75, WIDTH, 80);
		// downside.setBackground(Color.blue);

		label = new SetLabel("00 Hour 00 Min 00 Sec", 500, 28, 10, 0);

		ButtonListener bl = new ButtonListener(this, label, cell);

		// String text, int width, int height, int x, int y
		start = new SetButton("Start", 80, 28, WIDTH - 120, 0);
		start.addActionListener(bl);

		stop = new SetButton("Stop", 80, 28, WIDTH - 240, 0);
		stop.addActionListener(bl);

		reset = new SetButton("Reset", 80, 28, WIDTH - 360, 0);
		reset.addActionListener(bl);

		timeReset = new SetButton("TimeReset", 125, 28, WIDTH - 525, 0);
		timeReset.addActionListener(bl);

		downside.add(start);
		downside.add(stop);
		downside.add(reset);
		downside.add(timeReset);
		downside.add(label);

		this.add(downside);
	}

	private void mainPanelSet() {
		upside = new JPanel();
		upside.setLayout(null);
		upside.setBounds(0, 0, WIDTH, HEIGHT - 75);
		// upside.setBackground(Color.RED);

		this.add(upside);

	}

	@Override
	public void paint(Graphics g) {
		// 重要 記述でボタンなどもすべて描画される
		super.paint(g);
		this.paintG = g;
		new PaintLine(this.g, WIDTH, HEIGHT);
		new PaintCell(this.g, cell);
	}

	@Override
	public void update(Graphics g) {
		super.paint(this.paintG);
		new PaintLine(this.g, WIDTH, HEIGHT);
		new PaintCell(this.g, cell);
	}

}
