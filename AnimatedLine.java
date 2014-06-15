import ecs100.*;

public class AnimatedLine {

	private double x1,y1,x2,y2;		//4 points for the start pos of the line
	private double x3,y3,x4,y4;		//4 points for the end pos of the line
	
	public AnimatedLine(double x1, double y1, double x2, double y2){		//constructor gets starting values
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(double fraction){							//if fraction == 0, draw starting pos
		//UI.drawLine(this.x1, this.y1, this.x2, this.y2);	
		double inverseFraction = 1 - fraction;		
		
		UI.drawLine(
				(inverseFraction*this.x1) + (fraction*this.x3), 
				(inverseFraction*this.y1) + (fraction*this.y3),				
				(inverseFraction*this.x2) + (fraction*this.x4),
				(inverseFraction*this.y2) + (fraction*this.y4));	
	}
	
	public void setFinalPosition(double x3, double y3, double x4, double y4){	//setFinalpos gets the end values
		this.x3 = x3;
		this.y3 = y3;
		this.x4 = x4;
		this.y4 = y4;
		UI.printf("Added to lines: %.2f %.2f %.2f %.2f\n", x3,y3,x4,y4);
	}
}


/*
Represents one of the lines in the diagram - both the starting position and the final position.
It should have four fields to represent the end points of the line in its starting position and four fields to represent the end points of the line its final position.
It should have a draw method that has a fraction parameter that will draw the lines at a specified fraction between the start and final positions. For example, if the fraction is 0, it will draw the line at the starting position. If the fraction is 0.33, it will draw the line 1/3 of the way between the start and end. (The x position of the first end of line at that point would be 2/3 * startX1 + 1/3 * endX1 )
It will need a constructor that is passed the values of the starting position of the line,
It will need a setFinalPosition method that is passed the values of the ending position of the lines.

*/
