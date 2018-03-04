package drawshape;

public class Triangle extends Shape{
	String ShapeName = "Triangle";
	int sideOne = 0;
	int sideTwo = 0;
	int sideThree = 0;
	int angleA = 0;
	int angleB = 0;
	int angleC = 0;
	
	Triangle(int s1, int s2, int s3, int a1, int a2, int a3) {
		this.sideOne = s1;
		this.sideTwo = s2;
		this.sideThree = s3;
		this.angleA = a1;
		this.angleB = a2;
		this.angleC = a3;
	}
	
	public String getShapeName(){
		return this.ShapeName;
	}
	
	public int getSideOne(){
		return this.sideOne;
	}
	
	public int getSideTwo(){
		return this.sideTwo;
	}
	
	public int getSideThree(){
		return this.sideThree;
	}
	
	public String getInfo(){
		return ShapeName + ": " + sideOne + ", " + sideTwo + ", " + sideThree + 
				" with angles: " + angleA + ", " + angleB + ", " + angleC + "\n";
	}
	
//	public void drawn(){
//		System.out.println("This is the TRIANGLE obj!");
//	}
}
