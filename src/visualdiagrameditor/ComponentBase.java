package visualdiagrameditor;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import visualdiagrameditor.interfaces.*;
import visualdiagrameditor.shapes.Rectangle;

public abstract class ComponentBase implements Sized, Selectable, Positioned {
	
	private ComponentBase parent = null;
	private int width, height;
	private Point position;
	private boolean selected;
	private List<ComponentBase> children = new ArrayList<ComponentBase>();
	protected List<Rectangle> resizeHandles = new ArrayList<Rectangle>();

	
	protected ComponentBase(Point position, int width, int height, boolean addHandles) {
		setPosition(position);
		setWidth(width);
		setHeight(height);
		if(addHandles) {
			addResizeHandles();	
		}
	}
	
	public ComponentBase(Point position, int width, int height) {
		this(position, width, height, true);
	}
	
	private void addResizeHandles() {
	
		int resizeHandleSize = 10;
		Rectangle leftCenter = new Rectangle(new Point(0, getHeight()/2), resizeHandleSize, resizeHandleSize, false);
		Rectangle rightCenter = new Rectangle(new Point(getWidth(), getHeight()/2), resizeHandleSize, resizeHandleSize, false);
		Rectangle topCenter = new Rectangle(new Point(getWidth()/2, 0), resizeHandleSize, resizeHandleSize, false);
		Rectangle bottomCenter = new Rectangle(new Point(getWidth()/2, getHeight()), resizeHandleSize, resizeHandleSize, false);
		resizeHandles.add(leftCenter);
		resizeHandles.add(rightCenter);
		resizeHandles.add(topCenter);
		resizeHandles.add(bottomCenter);
		addChild(leftCenter);
		addChild(rightCenter);
		addChild(topCenter);
		addChild(bottomCenter);
		for(ComponentBase b : resizeHandles) {
			b.translate(-resizeHandleSize/2, -resizeHandleSize/2);
		}
	}

	public ComponentBase(int x, int y, int width, int height)  {
		this(new Point(x, y), width, height);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getPosition() {
		if(getParent()== null) {return position;}
		Point parentPosition = (Point)getParent().getPosition().clone();
		parentPosition.translate(position.x, position.y);
		return parentPosition;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public abstract void draw(Graphics g);
	protected void drawResizeHandles(Graphics g) {
		for(Rectangle b : resizeHandles) {
			b.draw(g);
		}
	}
	
	public boolean contains(Point point) {
		for(ComponentBase b : resizeHandles) {
			if(b.contains(point)) {
				return true;
			}
		}
		return getPosition().getX() <= point.getX() && point.getX() <= getPosition().getX() + getWidth() && getPosition().getY() <= point.getY() && point.getY() <= getPosition().getY() + getHeight(); 
	}


	public void translate(int xMovement, int yMovement) {
		this.setPosition(new Point(position.x + xMovement, position.y + yMovement));
	}

	@Override
	public void setIsSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public boolean getIsSelected() {
		return selected;
	}
	
	public Point getLeftCenter() {
		return new Point(getPosition().x, getPosition().y + getHeight()/2);
	}
	public Point getRightCenter() {
		return new Point(getPosition().x + getWidth(), getPosition().y + getHeight()/2);
	}
	public Point getTopCenter() {
		return new Point(getPosition().x + getWidth()/2, getPosition().y);
	}
	public Point getBottomCenter() {
		return new Point(getPosition().x + getWidth()/2, getPosition().y + getHeight());
	}

	public ComponentBase getParent() {
		return parent;
	}

	public void setParent(ComponentBase parent) {
		this.parent = parent;
		if(!getParent().containsChild(this)) {getParent().addChild(this);}
	}

	public void addChild(ComponentBase child) {
		this.children.add(child);
		if(child.getParent() != this) {
			child.setParent(this);
		}
	}
		
		public boolean containsChild(ComponentBase child) {
			return children.contains(child);
	}	
}