package visualdiagrameditor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.JPanel;

import visualdiagrameditor.shapes.Oval;
import visualdiagrameditor.shapes.Rectangle;


public class EditorPanel extends JPanel implements MouseMotionListener, MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<ComponentBase> elements = new ArrayList<ComponentBase>(); 
	private ComponentBase selectedItem = null;
	private Point lastMousePosition = new Point();
	private int mouseState;
	
	public EditorPanel() {
		addMouseMotionListener(this); 			// let the panel listen to its own mouse movements
		addMouseListener(this); 				// let the panel listen to its own mouse clicks and drags
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0,0,getWidth(), getHeight());
		for(ComponentBase b : elements){
			b.draw(g);
		}
	}
	
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		//remember that this was the last place the mouse was clicked
		lastMousePosition = arg0.getPoint();
		
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			addElement(new Rectangle(new Point(lastMousePosition.x, lastMousePosition.y), 200, 100));
		}
		
		if(arg0.getButton() == MouseEvent.BUTTON2) {
			addElement(new Oval(new Point(lastMousePosition.x, lastMousePosition.y), 200, 100));
		}
		setSelectedItem(findElementAt(arg0.getPoint()));	
		if(getSelectedItem() != null)
		{
			//we found one! Move it to the top of the list, so it appears to have been picked up
			selectItemAndMoveToTop(getSelectedItem());
			//as the GUI to repaint
			repaint();
		}
	}

	private ComponentBase findElementAt(Point point) {
		//look through all items (from top to bottom) and see if we hit one of them
		for (int i = elements.size() -1; i >= 0 ; i--) {
			if(elements.get(i).contains(lastMousePosition)) {			
				return elements.get(i);
			}
		}
		return null;
	}
	
	//moves selected item to top of ArrayList, to ensure it is drawn last and therefore appears over all the other cards
	private void selectItemAndMoveToTop(ComponentBase item){
		elements.remove(item);	//remove the item from the list
		elements.add(item);		//move it to the top
		setSelectedItem(item); 	//remember that this is the item we've got selected now	
	}
	
	public ComponentBase getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(ComponentBase selectedItem) {
		if(this.getSelectedItem() != null) {
			this.getSelectedItem().setIsSelected(false);
		}
		this.selectedItem = selectedItem;
		if(getSelectedItem() != null) {
			getSelectedItem().setIsSelected(true);
		}
	}
	

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		//find out how far we've dragged on both axes
		int mouseMovementX = arg0.getX() - lastMousePosition.x;
		int mouseMovementY = arg0.getY() - lastMousePosition.y;

		//if there is something currently selected - move it
		if(getSelectedItem() != null) {
			getSelectedItem().translate(mouseMovementX, mouseMovementY);
			repaint();
		}	
		lastMousePosition = arg0.getPoint();	//remember where the mouse is at
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		lastMousePosition = arg0.getPoint();
		ComponentBase boxUnderCursor = findElementAt(lastMousePosition);
		
		if(boxUnderCursor != null) {
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		repaint();
		}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}


	public void addElement(ComponentBase box) {
		elements.add(box);
	}
	
	public int getMouseState() {
		return mouseState;
	}


	public void setMouseState(int mouseState) {
		this.mouseState = mouseState;
	}

	
}
