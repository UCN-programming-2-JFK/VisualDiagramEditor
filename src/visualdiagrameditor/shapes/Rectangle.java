package visualdiagrameditor.shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import visualdiagrameditor.ComponentBase;

public class Rectangle extends ComponentBase {
	
	public Rectangle(Point position, int width, int height, boolean addHandles) {
		super(position, width, height, addHandles);
	}
	
	public Rectangle(Point position, int width, int height) {
		this(position, width, height, true);
	}
	
		
	public void draw(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect((int)getPosition().getX(), (int)getPosition().getY(), getWidth(), getHeight());
		if(getIsSelected()) {g.setColor(Color.red);}
		else {g.setColor(Color.black);}
			
		g.drawRect((int)getPosition().getX(), (int)getPosition().getY(), getWidth(), getHeight());
		g.drawRect((int)getPosition().getX()-1, (int)getPosition().getY()-1, getWidth()+2, getHeight()+2);
		
		if(getIsSelected()) {
			drawResizeHandles(g);			
		}
	}

	public boolean contains(Point point) {
		for(Rectangle b : resizeHandles) {
			if(b.contains(point)) {
				return true;
			}
		}
		return getPosition().getX() <= point.getX() && point.getX() <= getPosition().getX() + getWidth() && getPosition().getY() <= point.getY() && point.getY() <= getPosition().getY() + getHeight(); 
	}	
}