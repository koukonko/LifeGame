package logic;

public class LifeGameLogic {
	// 現世代
	private boolean[][] prevGene;
	// 次世代
	private boolean[][] nextGene;

	public boolean[][] nextGene(boolean[][] prevGene) {
		nextGene = prevGene.clone();
		boolean[][] tmpGene = new boolean[prevGene.length + 2][prevGene[0].length + 2];

		// 0 を周りに詰める
		for (int i = 0; i < tmpGene.length; ++i) {
			for (int j = 0; j < tmpGene[i].length; ++j) {
				if (i == 0 || j == 0 || i == tmpGene.length - 1 || j == tmpGene[i].length - 1) {
					tmpGene[i][j] = false;
				} else {
					tmpGene[i][j] = prevGene[i - 1][j - 1];
				}
			}
		}

		// 次世代のロジック
		for (int i = 1; i < tmpGene.length - 1; ++i) {
			for (int j = 1; j < tmpGene[i].length - 1; ++j) {
				if (tmpGene[i][j]) {
					nextGene[i - 1][j - 1] = aliveCell(tmpGene, i, j);
				} else {
					nextGene[i - 1][j - 1] = deadCell(tmpGene, i, j);
				}
			}
		}

		this.prevGene = nextGene.clone();

		return nextGene;
	}

	public boolean aliveCell(boolean[][] box, int x, int y) {
		int count = 0;

		int[][] circu = {
				{ -1, -1 }, // 左上
				{ 0, -1 }, // 上
				{ 1, -1 }, // 右上
				{ 1, 0 }, // 右
				{ 1, 1 }, // 右下
				{ 0, 1 }, // 下
				{ -1, 1 }, // 左下
				{ -1, 0 } // 左
		};

		for (int i = 0; i < circu.length; ++i) {

			if (box[x - circu[i][0]][y - circu[i][1]]) {
				count++;
			}

		}

		if (count == 2 || count == 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deadCell(boolean[][] box, int x, int y) {
		int count = 0;

		int[][] circu = {
				{ -1, -1 }, // 左上
				{ 0, -1 }, // 上
				{ 1, -1 }, // 右上
				{ 1, 0 }, // 右
				{ 1, 1 }, // 右下
				{ 0, 1 }, // 下
				{ -1, 1 }, // 左下
				{ -1, 0 } // 左
		};

		for (int i = 0; i < circu.length; ++i) {

			if (box[x - circu[i][0]][y - circu[i][1]]) {
				count++;
			}
		}

		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean[][] getPrevGene() {
		return this.prevGene;
	}
}
