package drawshape;

public class Rectangle extends Shape{
	String ShapeName = "Rectangle";
	int width = 0;
	int height = 0;
	
	Rectangle(int w, int h) {
		this.width = w;
		this.height = h;
	}
		
	public String getShapeName(){
		return this.ShapeName;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public String getInfo(){
		return ShapeName + ": " + width + ", " + height + "\n";
	}
	
//	public void drawn(){
//		System.out.println("This is the RECTANGLE obj!");
//	}
}
