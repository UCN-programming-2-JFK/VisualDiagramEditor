# Visual Diagram Editor
This is a stub implementation of an editor which lets you create, move (using drag and drop) and delete shapes in Java Swing.  
<img width="758" alt="image" src="https://github.com/UCN-programming-2-JFK/VisualDiagramEditor/assets/3811290/9bae2964-efb3-4264-9081-3370c32afa3b">

## Model classes  
These extend an abstract ComponentBase class which implements three interfaces which allows shapes to have distinct characteristics based on Size, Positioning and whether they can be selected.  
The two implemented visual classes Oval and Rectangle implement a custom draw() and contains() method, as they have a distinct shape  

  
<img width="693" alt="image" src="https://github.com/UCN-programming-2-JFK/VisualDiagramEditor/assets/3811290/1feb0400-e2de-4e95-90b7-2fcc9b080b8a">

## Editor panel
The EditorPanel class extends JPanel to make for easy editing using the mouse.   
Important methods on the EditorPanel:

- +paint(Graphics g): void
- +deleteSelectedItem(): void
- -findElementAt(Point point): ComponentBase
- -selectItemAndMoveToTop(ComponentBase item): void
- +addElement(ComponentBase element): void
- +removeElement(ComponentBase element): void
- +getSelectedItem(): ComponentBase
- +setSelectedItem(ComponentBase selectedItem): void
