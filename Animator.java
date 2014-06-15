import java.awt.Color;
import java.util.*;

import ecs100.*;

public class Animator implements UIMouseListener, UIButtonListener, UISliderListener {
	
	private List <AnimatedLine> lines;
	private int rate = 10;					
	private double pressedX,pressedY;
	private double fraction = 0;
	private int lineCount = 0;			
	private double step = 0.05;
	private boolean settingTargets = false;
	
	public static void main(String[] args) {new Animator();}

	public Animator(){
		restart();
		UI.setMouseListener(this);
		UI.addButton("Restart",this);
		UI.addButton("Target",this);
		UI.addButton("Run",this);
		UI.addSlider("Speed",1, 99, 50, this);
	}

	public void buttonPerformed(String name) {
		if(name.equals("Restart")){
			restart();
		} else if(name.equals("Target")){
			target();
		} else if(name.equals("Run")){
			run();
		}
	}
	
	public void sliderPerformed(String name, double value) {
		if(name.equals("Speed")){
			this.rate = (int)(100-value)*10;		//so the bigger the number. so val=70 is 300ms (100-70=30,*10 = 300)
			UI.printf("Rate changed to %dms\n",this.rate);
		}
	}
	
	public void mousePerformed(String action, double x, double y) {
		UI.setImmediateRepaint(false);
		if(action.equals("pressed")){
			this.pressedX = x;
			this.pressedY = y;
		
		} else if (action.equals("released")){
			double x1 = pressedX;
			double y1 = pressedY;
			double x2 = x;
			double y2 = y;
			
			if(!settingTargets){
				lines.add(new AnimatedLine(x1,y1,x2,y2));
				//UI.printf("Added to lines: %.2f %.2f %.2f %.2f\n", x1,y1,x2,y2);
			} else {
				if(lineCount < lines.size() && lines.size() != 0){		
					lines.get(this.lineCount).setFinalPosition(x1,y1,x2,y2);
					++this.lineCount;
					//UI.printf("Linecount = %d \n", lineCount);
				} else {
					UI.println("Please add a line, or click the run button to see it in action.");
				}
			}
		}
		redraw();
		UI.repaintGraphics();
	}	

	private void restart() {
		UI.clearGraphics();							//clear the graphics
		lines = new ArrayList <AnimatedLine>();		//new arraylist
		UI.setColor(Color.green);
		settingTargets = false;
		this.fraction = 0;
		UI.repaintGraphics();
	}

	private void target() {
		UI.println("Setting final position");
		settingTargets = true;
	}

	/* maybe make this into an loop, using sine so it loops back and forth a few times  */
	private void run() {
		while(fraction<=1){ 
			UI.clearGraphics();
			redraw();
			UI.sleep(rate);
			this.fraction += step;
			UI.repaintGraphics();
		}
	}
	
	private void redraw(){		
		for(AnimatedLine a : lines){	
			a.draw(this.fraction);
		}
	}

	

	
	
	
}

/*
Has a field that contains an ArrayList of AnimatedLines.
Sets up the user interface with mouse and buttons.
After the user has clicked the "Restart" button, dragging with the mouse will create a new AnimatedLine object and add it to the list.
After the user has clicked the "Target" button, dragging with the mouse will set the final position of the next AnimatedLine in the list. (The program will need to store the index of the line that it is currently up to.)
When the user clicks the "Run" button, the program will perform a loop that steps the value of fraction from 0.0 through to 1.0, redrawing all the lines with the given fraction at each step, and pausing briefly each step.
*/
