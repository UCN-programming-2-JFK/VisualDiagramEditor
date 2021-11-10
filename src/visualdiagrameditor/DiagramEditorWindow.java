package visualdiagrameditor;

import javax.swing.JFrame;

public class DiagramEditorWindow extends JFrame {

		private static final long serialVersionUID = 1L;
		
		public static void main(String[] args) { 
	
		EditorPanel editorPanel = new EditorPanel(); 	// create our panel
		editorPanel.setVisible(true);
		JFrame frame = new JFrame("Visual editor - right click to add box"); 	// create a Frame (window)
		
		frame.setResizable(false); 									// lock its size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// set the X button click to close the window
		frame.setSize(1024, 768); 									// set the size
		
		frame.getContentPane().add(editorPanel); 					// add our panel
		frame.setVisible(true); 									// show the window	
	
	}
}