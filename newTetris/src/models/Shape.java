package models;

import java.util.Random;

import models.Shape.Tetrominoes;

public class Shape {
	public enum Tetrominoes {
		Empty, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
	}
	//7�N�Π��piece �� һ�N�յġ�
	
	private Tetrominoes pieceShape;
	private int[][] coords; // һ���Π�K������
	private int[][][] coordsTable; // ���˱�

	public Shape() {
		coords = new int[4][2];
		coordsTable = new int[][][] 
				{ { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, // �հ�����
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, // S�Π�����
				{ { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, // Z�Π�����
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, // I��Line���Π�����
				{ { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, // T�Π�����
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, // ��������
				{ { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, // ��L�Π�����
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } // L�Π�����
		};
		setPieceShape(Tetrominoes.Empty);
	}

	public void setPieceShape(Tetrominoes pieceShape) {
		for (int i = 0; i < 4; i++) {
			System.arraycopy(coordsTable[pieceShape.ordinal()][i], 0, coords[i], 0, 2);
		}
		this.pieceShape = pieceShape;
	}
	
	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	public Tetrominoes getPieceShape() { //�@ȡ�a�����K���Π�
		return pieceShape;
	}

	public void setRandomShape() { //�S�C���ɷ��K
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		Tetrominoes[] values = Tetrominoes.values();
		setPieceShape(values[x]);
	}

	public int minX() {
		int m = coords[0][0];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}

	public int minY() {
		int m = coords[0][1];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][1]);
		}
		return m;
	}

	public Shape rotate() {
		if (pieceShape == Tetrominoes.SquareShape)
			return this;

		Shape result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		return result;
	}
}