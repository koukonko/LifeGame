package paint;

import java.awt.Color;
import java.awt.Graphics;

public class PaintCell {

	private Graphics g;
	private boolean[][] cell;

	public PaintCell(Graphics g, boolean[][] cell) {
		this.g = g;
		this.cell = cell;

		paint();
	}

	public void paint() {

		for (int i = 0; i < cell.length; ++i) {
			for (int j = 0; j < cell[i].length; ++j) {
				if (!cell[i][j]) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.black);
				}
				g.fillRect(j * 5 + 25 + 1, i * 5 + 25 + 1, 3, 3);
			}
		}
	}

}
