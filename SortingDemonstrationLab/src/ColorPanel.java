import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class ColorPanel extends JPanel{
	private int NumberOfBoxes;
	public Rectangle[] arrRect;
	public void GenerateRandomBoxes(int FrameWidth, int FrameHeight, int Width) {
		int W = Width;
		System.out.println(W);
		Rectangle[] arrRectTemp = new Rectangle[NumberOfBoxes];
		int Xcoordinate = 0;
		for (int i = 0; i < NumberOfBoxes; i++) {
			int Height = (int) (Math.random()*(FrameHeight-50)) + 50;
			int Ycoordinate = FrameHeight - Height;
			arrRectTemp[i] = new Rectangle(Xcoordinate,Ycoordinate,  W, Height, Color.RED);
			Xcoordinate += W;
		}
		System.out.println("Last X " + (Xcoordinate + W));
		arrRect = arrRectTemp;
	}
	public int GetNumberOfBoxes() {
		return NumberOfBoxes;
	}
	public void SetNumberOfBoxes(int numberOfBoxes) {
		NumberOfBoxes = numberOfBoxes;
	}
	public void PaintRectArray(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		int X = 0;
		for (int i = 0; i < arrRect.length; i++) {
			arrRect[i].setXcoordinate(X);
			arrRect[i].DrawRectangle(g);
			X += arrRect[i].getWidth();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		PaintRectArray(g);
		//BubbleSort(arrRect, g);
		//PaintRectArray(g);
	}

	private void BubbleSort(Rectangle[] arr, Graphics g)
	{
		boolean Match = true;
		while (Match) {
			Match = false;
			for (int j = 0; j < arr.length-1; j++) {
				if (arr[j].getHeight() > arr[j+1].getHeight()) {
					Match = true;
					Rectangle Temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = Temp;
				}
			}
		}
	}
}
