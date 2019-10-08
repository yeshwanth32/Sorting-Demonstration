import java.awt.Color;
import java.awt.Graphics;


public class Rectangle {
	private int Xcoordinate, Ycoordinate;
	private int Width, Height;
	Color RectColor;
	public int getXcoordinate() {
		return Xcoordinate;
	}
	public void setXcoordinate(int xcoordinate) {
		Xcoordinate = xcoordinate;
	}
	public int getYcoordinate() {
		return Ycoordinate;
	}
	public void setYcoordinate(int ycoordinate) {
		Ycoordinate = ycoordinate;
	}
	public int getWidth() {
		return Width;
	}
	public void setWidth(int width) {
		Width = width;
	}
	public int getHeight() {
		return Height;
	}
	public void setHeight(int height) {
		Height = height;
	}
	public Color getColor() {
		return RectColor;
	}
	public void setColor(Color color) {
		this.RectColor = color;
	}
	public Rectangle(int xcoordinate, int ycoordinate, int width, int height, Color color) {
		super();
		Xcoordinate = xcoordinate;
		Ycoordinate = ycoordinate;
		Width = width;
		Height = height;
		this.RectColor = color;
	}
	
	public Rectangle() {
		RectColor = Color.RED;
		Xcoordinate = 0;
		Ycoordinate = 0;
		Width = 10;
		Height = 10;
	}
	public void DrawRectangle(Graphics g) {
		g.setColor(RectColor);
		g.fill3DRect(Xcoordinate, Ycoordinate, Width, Height, true);
	}
}
