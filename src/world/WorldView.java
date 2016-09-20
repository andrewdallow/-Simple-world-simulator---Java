package world;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



/**
 * Very simple prototype view.  Observes and (minimally) reports
 * updates in a World.  Can be run standalone or with external
 * controllers.
 * 
 * @author Neville
 *
 */
public class WorldView extends Observable implements Observer {
	/**
	 * JFrame for displaying the listener test field.
	 */
	private JFrame jf;
	/**
	 * Text area for displaying listener messages.
	 */
	private JTextArea jta;
	/**
	 * Simulated world being viewed.
	 */
	private World w;
	
	/**
	 * Make a very simple GUI. Refactor when time allows.  
	 */
	private void buildGUI() {
		jf = new JFrame("World Listener");
		jta = new JTextArea(20,50);
		JScrollPane jsp = new JScrollPane(jta);
		
		jf.add(jsp);
		jf.pack();
		jf.setVisible(true);
	}
	
	/**
	 * Set up something to view for demonstration purposes.
	 */
	private void createWorld() {
		jta.append("Hello World\n");
		w = new World();
		w.addObserver(this);
		w.demoWorld();
	}
	/**
	 * Enables stand-alone use.  
	 */
	public static void main(final String[] args) {
		WorldView me = new WorldView();
		// Take a copy of what has been happening.
		// Note to self: could save this in a file...
		System.out.println(me.jta.getText());
	}

/**
 * An external controller may attach us to a world
 * which has been set up elsewhere or we may have 
 * constructed our own.
 * @param w World ready to be viewed
 */
	public final void setWorld(final World w) {
		this.w = w;
	}
	
	/**
	 * Constructor for class.  Build our interface
	 * and attach world supplied.
	 * @param w world to view
	 */
	public WorldView(final World w) {
		buildGUI();
		setWorld(w);
		w.addObserver(this);
	}
	
	/**
	 * Constructor for class. Make a demo world
	 * to have something to show.
	 */
	public WorldView() {
		buildGUI();
		createWorld();
	}
	
	/**
	 * Report update occurrence and object provided by observable.
	 * If we did some more work we could provide more detail about
	 * what actually happened...
	 */
	@Override
	public final void update(final Observable arg0, final Object arg1) {
		jta.append("Update involving: ");
		jta.append(arg1 + "\n");
		setChanged();
		notifyObservers(arg1);
		
	}

}
