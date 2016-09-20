package world;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import place.Forest;
import place.Place;
import place.Room;
import thing.Computer;
import thing.Food;
import thing.Plant;
import thing.Thing;
import actor.Actor;
import actor.Animal;
import actor.Person;
import actor.Robot;

/**
 * Controller for Worlds.  Observes updates in WorldView and
 * supports browsing of World. Supports some simple actions.
 * If a person and place are selected then move allows relocation.
 * A person can take or drop a thing from their current location.
 * Room's, Actor's, and Thing's can be added to World with specified properties.
 * Add/edit the size of a selected Room.
 * Selected Room's, Actor's, and Thing's can be removed from World.
 * A new or demo world can be created to take the place of the current World.
 * 
 * @author Neville
 * @author Andrew Dallow
 * @see world.World
 * @version 2.0
 *
 */
public class WorldControl implements Observer {
	/**
	 * 
	 */
	private World w;
	/**
	 * Currently selected actor.
	 */
	private Actor selectedActor;
	/**
	 * Currently selected Place.
	 */
	private Place selectedPlace;
	/**
	 * Currently selected Thing.
	 */
	private Thing selectedThing;
	/**
	 * Window containing the GUI.
	 */
	private JFrame jf = new JFrame("World Control");	
	/**
	 * List of places.
	 */
	private JList<Place> roomList = new JList<Place>();
	/**
	 * List of actors.
	 */
	private JList<Actor> actorList = new JList<Actor>();
	/**
	 * List of actor's inventory.
	 */
	private JList<Thing> stuff = new JList<Thing>();
	/**
	 * List of Room contents.
	 */
	private JList<Thing> contents = new JList<Thing>();
	/**
	 * List of actor's history.
	 */
	private JList<Place> history = new JList<Place>();
	/**
	 * Room Label Text Field.
	 */
	private JTextField roomNameField = new JTextField(4);
	/**
	 * Room Level Text Field. 
	 */
	private JTextField roomLevelField = new JTextField(2);
	/**
	 * Room Description Text Field.
	 */
	private JTextField descriptionField = new JTextField(20);
	/**
	 * Room Width Text Field.
	 */
	private JTextField roomWidthField = new JTextField(4);
	/**
	 * Room Depth Text Field.
	 */
	private JTextField roomDepthField = new JTextField(4);
	/**
	 * Forest name Text Field.
	 */
	private JTextField forestNameField = new JTextField();
	/**
	 * Forest Type Text Field.
	 */
	private JTextField forestTypeTextField = new JTextField();
	/**
	 * Forest Climate Text Field.
	 */
	private JTextField climateTextField = new JTextField();
	/**
	 * Scroll pane for GUI window.
	 */
	private JScrollPane jsp;
	/**
	 * Pane containing the GUI.
	 */
	private JPanel jp;
	
	/**
	 * First Name Text Field.
	 */
	private JTextField personFullNameField = new JTextField();
	/**
	 * Gender Text Field.
	 */
	private JTextField personGenderField = new JTextField();
	/**
	 * DOB Format.
	 */
	private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	/**
	 * DOB Formated Text Field.
	 */
	private JFormattedTextField personDOBField = new JFormattedTextField(
			format);
	
	/**
	 * Robot ID Text Field.
	 */
	private JTextField robotIDTextField = new JTextField();
	/**
	 * Robot Purpose Text Field.
	 */
	private JTextField robotPurposeTextField = new JTextField();
	
	/**
	 * Animal name Text Field.
	 */	
	private JTextField animalNameTextField = new JTextField();	
	/**
	 * Animal type Text Field.
	 */
	private JTextField animalTypeTextField =  new JTextField();	
	/**
	 * Animal Gender Text Field.
	 */
	private JTextField animalGenderTextField =  new JTextField();
	/**
	 * Animal number of legs Text Field.
	 */
	private JTextField animalNumLegsTextField = new JTextField();
	
	/**
	 * Food name Text Field.
	 */	
	private JTextField foodNameTextField = new JTextField(6);
	/**
	 * Food type Text Field.
	 */	
	private JTextField foodTypeTextField = new JTextField(5);
	/**
	 * Food description Text Field.
	 */	
	private JTextField foodDescriptionTextField = new JTextField(8);
	
	/**
	 * Check button for food cooked.
	 */	
	private JCheckBox cooked = new JCheckBox("Cooked", false);
	
	/**
	 * Plant name Text Field.
	 */
	private JTextField plantNameTextField = new JTextField(6);
	/**
	 * Plant type Text Field.
	 */
	private JTextField plantTypeTextField = new JTextField(6);
	/**
	 * Plant description Text Field.
	 */
	private JTextField plantDescriptionTextField = new JTextField(10);
	/**
	 * Plant quantity Text Field.
	 */
	private JTextField plantQuantityTextField = new JTextField(5);
	
	/**
	 * Thing name Text Field.
	 */
	private JTextField thingNameField = new JTextField(10);
	/**
	 * Thing Description Text Field.
	 */
	private JTextField thingDescriptionField = new JTextField(15);
	
	/**
	 * Computer ID Text Field.
	 */
	private JTextField computerIDTextField = new JTextField(10);
	/**
	 * Computer description Text Field.
	 */
	private JTextField computerDescriptionTextField = new JTextField(10);
	/**
	 * JPanel containing World lists and add/edit fields.
	 */
	private JPanel jpLists;
	/**
	 * Constraints for main GridBagLayout.
	 */
	private GridBagConstraints constraints;
	
	
	
	
	/**
	 * Build's the entire Graphical User Interface and displays it.
	 */
	private void buildGui() {
		
		
		
		jp = new JPanel();
		
		jp.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		
		jf.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(final WindowEvent we) {
				System.exit(0);
			}
		});
		
		jpLists = new JPanel();
		jpLists.setLayout(new GridLayout(2,2));
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		jp.add(jpLists, constraints);
		
	
		jsp = new JScrollPane(jp);
		
		buildRooms();
		buildPeople();
		buildContents();
		buildStuff();
		buildControls();
		
		jf.add(jsp);
		jf.pack();
		jf.setVisible(true);
	}
	/**
	 * Build GUI for the Contents pane containing the list of contents in a 
	 * selected Room and includes a text field to add new items to the world.
	 */
	private void buildContents() {
		JPanel jpc = new JPanel();
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.PAGE_AXIS));
		TitledBorder b = BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Place Contents");
		jpc.setBorder(b);
		if (selectedPlace != null) {
			b.setTitle(b.getTitle() + "[" + selectedPlace.label());
		}
		JScrollPane contentsPane = new JScrollPane(contents, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jpc.add(contentsPane);		
		/*
		 * Add a Thing to the world and selected Room.
		 *
		 */
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new GridLayout(1, 4));
		JLabel thingNameLabel = new JLabel("Name: ");		
		JLabel thingDescriptionLabel = new JLabel("Description: ");
		
		JButton addThing = new JButton("Add Thing");		
		
		addThing.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				addThingButton();						
			}
		});	
		
		JPanel addThings = new JPanel();
		addThings.setLayout(new BoxLayout(addThings, BoxLayout.PAGE_AXIS));
		addThings.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Thing"));
		
		thingNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				thingNameField.getPreferredSize().height));
		thingDescriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				thingDescriptionField.getPreferredSize().height));
		addThing.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				addThing.getPreferredSize().height));
		
		addThings.add(thingNameLabel);
		addThings.add(thingNameField);
		addThings.add(thingDescriptionLabel);
		addThings.add(thingDescriptionField);
		addThings.add(addThing);						
		/*
		 * Add a Food item to the world.
		 */		
		JPanel addFood = new JPanel();
		addFood.setLayout(new BoxLayout(addFood, BoxLayout.PAGE_AXIS));
		addFood.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Food"));
		
		JLabel foodNameLabel = new JLabel("Name: ");			
		JLabel foodTypeLabel = new JLabel("Type (e.g. fruit): ");		
		JLabel foodDescriptionLabel = new JLabel("Description: ");		

		JButton addFoodButton = new JButton("Add Food");
		
		addFoodButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {			
				addFoodButton();									
			}
		});	
		
		foodNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				foodNameTextField.getPreferredSize().height));
		
		foodTypeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				foodTypeTextField.getPreferredSize().height));
		foodDescriptionTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				foodDescriptionTextField.getPreferredSize().height));
		addFoodButton.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				addFoodButton.getPreferredSize().height));
		
		addFood.add(foodNameLabel);
		addFood.add(foodNameTextField);
		addFood.add(foodTypeLabel);
		addFood.add(foodTypeTextField);
		addFood.add(foodDescriptionLabel);
		addFood.add(foodDescriptionTextField);
		addFood.add(cooked);
		addFood.add(addFoodButton);		
		/*
		 * Add a Computer to the world.
		 */
		JPanel addComp = new JPanel();
		addComp.setLayout(new BoxLayout(addComp, BoxLayout.PAGE_AXIS));
		addComp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Computer"));
		
		JLabel computerIDLabel = new JLabel("Computer ID: ");		
		JLabel computerDescriptionLabel = new JLabel("Description: ");		
		
		JButton addComputerButton = new JButton("Add Computer");

		addComputerButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {					
				addComputerButton();
			}
		});	
		
		JButton controlRobotButton = new JButton("Control Robot");
		
		controlRobotButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {	
				controlRobotButton();
				
			}		
		});	
		computerIDTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				computerIDTextField.getPreferredSize().height));
		computerDescriptionTextField.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				computerDescriptionTextField.getPreferredSize().height));
		
		addComputerButton.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				addComputerButton.getPreferredSize().height));
		controlRobotButton.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				controlRobotButton.getPreferredSize().height));
		
		addComp.add(computerIDLabel);
		addComp.add(computerIDTextField);
		addComp.add(computerDescriptionLabel);
		addComp.add(computerDescriptionTextField);
		addComp.add(addComputerButton);
		addComp.add(controlRobotButton);		
		/*
		 * Add a plant to the world.
		 */
		JPanel addPlant = new JPanel();		
		addPlant.setLayout(new BoxLayout(addPlant, BoxLayout.PAGE_AXIS));
		addPlant.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Plant"));
		JLabel plantNameLabel = new JLabel("Plant Name: ");		
		JLabel plantTypeLabel = new JLabel("Type (e.g. Tree): ");		
		JLabel plantDescriptionLabel = new JLabel("Description: ");			
		JLabel plantQuantityLabel = new JLabel("Quantity: ");
		
		JButton addPlantButton = new JButton("Add Plant");
		
		addPlantButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {	
				addPlantButton();				
			}		
		});	
		
		plantNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				plantNameTextField.getPreferredSize().height));
		plantTypeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				plantTypeTextField.getPreferredSize().height));
		plantDescriptionTextField.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				plantDescriptionTextField.getPreferredSize().height));
		plantQuantityTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				plantQuantityTextField.getPreferredSize().height));
		addPlantButton.setMaximumSize(new Dimension(
				Integer.MAX_VALUE, 
				addPlantButton.getPreferredSize().height));

		addPlant.add(plantNameLabel);
		addPlant.add(plantNameTextField);
		addPlant.add(plantTypeLabel);
		addPlant.add(plantTypeTextField);
		addPlant.add(plantDescriptionLabel);
		addPlant.add(plantDescriptionTextField);
		addPlant.add(plantQuantityLabel);
		addPlant.add(plantQuantityTextField);
		addPlant.add(addPlantButton);
				
		addButtons.add(addThings);
		addButtons.add(addFood);
		addButtons.add(addComp);
		addButtons.add(addPlant);
		jpc.add(addButtons);
		
		jpLists.add(jpc);
	}
	/**
	 * Button to add Computer to world in selected Place.
	 */
	private void addComputerButton() {
		String computerID = computerIDTextField.getText();
		String computerDescription = 
				computerDescriptionTextField.getText();
		
		if (computerID.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please enter Computer ID.",
					"Add Computer Error", 
					JOptionPane.ERROR_MESSAGE);
			
		} else if (computerDescription.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please enter Description.",
					"Add Computer Error", 
					JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			if (selectedPlace == null) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please select a Room.",
						"Add Computer Error", 
						JOptionPane.ERROR_MESSAGE);
				
			} else {
				try {
					Computer newComp = new Computer(
							Integer.parseInt(computerID), 
							computerDescription);
					w.items().add(newComp);
					w.items();
					selectedPlace.addThing(newComp);
					computerIDTextField.setText("");
					computerDescriptionTextField.setText("");
					refreshRooms();
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(jf, 
							"Error: Computer ID should be an integer.",
							"Add Computer Error", 
							JOptionPane.ERROR_MESSAGE);
					
				}						
			}
		}
	}
	
	/**
	 * Add a thing to the world in the selected Place.
	 */
	private void addThingButton() {
		String thingName = thingNameField.getText();
		
		String thingDescription = thingDescriptionField.getText();
		
		
		if (thingName.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter a Thing Name.",
					"Add Thing Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (thingName.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter a First Description.",
					"Add Thing Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			if (selectedPlace == null) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please Please select a room.",
						"Add Thing Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				Thing newThing = new Thing(thingName, thingDescription);
				w.items().add(newThing);
				w.items();
				selectedPlace.addThing(newThing);	
				thingNameField.setText("");
				thingDescriptionField.setText("");
				
				refreshRooms();
			}
		}
	}
	
	
	/**
	 * Add a plant to the selected place.
	 */
	private void addPlantButton() {
		String plantName = plantNameTextField.getText();
		String plantType = plantTypeTextField.getText();
		String plantDescription = plantDescriptionTextField.getText();
		String plantQuantity = plantQuantityTextField.getText();
		
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please select a Robot.",
					"Add Plant Error", 
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (plantName.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Plant Name.",
						"Add Plant Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (plantType.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Plant Type.",
						"Add Plant Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (plantDescription.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Plant Description.",
						"Add Plant Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (plantQuantity.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Plant Quantity.",
						"Add Plant Error", JOptionPane.ERROR_MESSAGE);
				
			} else {
				try {
					Plant newPlant = new Plant(plantName, plantType, 
							plantDescription, 
							Integer.parseInt(plantQuantity));
					
					w.items().add(newPlant);
					w.items();
					selectedPlace.addThing(newPlant);
					plantNameTextField.setText("");
					plantTypeTextField.setText("");
					plantDescriptionTextField.setText("");
					plantQuantityTextField.setText("");
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(jf, 
							"Error: Quantity should be an integer.",
							"Add Plant Error", JOptionPane.ERROR_MESSAGE);
					
					}
			}
		}
	}
	/**
	 * Button to set computer controller of Robot.
	 */
	private void controlRobotButton() {
		selectedThing = (Thing) contents.getSelectedValue();
		
		
		if (selectedThing instanceof Computer) {
			if (selectedActor == null) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please select a Robot.",
						"Robot Controller Error", 
						JOptionPane.ERROR_MESSAGE);
				
				
			} else if (selectedActor instanceof Robot) {
				Robot robot = (Robot) selectedActor;
				((Computer) selectedThing).setRobotControl(
						(Robot) selectedActor);
				robot.powerOn();
				
			} else {
				JOptionPane.showMessageDialog(jf, 
						"Error: Not a Robot. Please select a Robot.",
						"Robot Controller Error", 
						JOptionPane.ERROR_MESSAGE);
				
			}
		} else {
			JOptionPane.showMessageDialog(jf, 
					"Error: Not a Computer. Please select a Computer.",
					"Robot Controller Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Button for adding food items to the contents of a selected Place.
	 */
	private void addFoodButton() {
		String foodName = foodNameTextField.getText();
		String foodType = foodTypeTextField.getText();
		String foodDescription = foodDescriptionTextField.getText();
		
		if (foodName.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Please enter a Name.",
					"Add Food Error", JOptionPane.ERROR_MESSAGE);
		} else if (foodType.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Please enter a Food Type.",
					"Add Food Error", JOptionPane.ERROR_MESSAGE);
		} else if (foodDescription.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Please enter a Food Description.",
					"Add Food Error", JOptionPane.ERROR_MESSAGE);
		} else {
			if (selectedPlace == null) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please Please select a room.",
						"Add Food Error", JOptionPane.ERROR_MESSAGE);
			} else {
				Food newFood = new Food(foodName, foodType, 
						foodDescription, cooked.isSelected());
				w.items().add(newFood);
				w.items();
				selectedPlace.addThing(newFood);
				refreshRooms();
				foodNameTextField.setText("");
				foodTypeTextField.setText("");
				foodDescriptionTextField.setText("");	
				}			
		}	
		foodNameTextField.setText("");
		foodTypeTextField.setText("");
		foodDescriptionTextField.setText("");		
	}
	/**
	 * Builds GUI for the selected actor's history and inventory pane 
	 * containing a list of visited Room's and the contents of their inventory.
	 */
	private void buildStuff() {
		JPanel jpa = new JPanel();
		JPanel jph = new JPanel();
		JPanel jps = new JPanel();
		
		jpa.setLayout(new BoxLayout(jpa, BoxLayout.PAGE_AXIS));
		jph.setLayout(new BoxLayout(jph, BoxLayout.PAGE_AXIS));
		jps.setLayout(new BoxLayout(jps, BoxLayout.PAGE_AXIS));
		
		jph.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Actor History"));
		JScrollPane historyPane = new JScrollPane(history, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		jps.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Actor Inventory"));
		JScrollPane stuffPane = new JScrollPane(stuff, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		jph.add(historyPane);
		jps.add(stuffPane);
		jpa.add(jph);
		jpa.add(jps);
		
		jpLists.add(jpa);
		
	}
	
	/**
	 * Add an Actor of type Person to the world.
	 */
	private void addPersonButton() {
		String personFullName = personFullNameField.getText();
		String personGender = personGenderField.getText();
		
		if (personFullName.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter a Full Name.",
					"Add Person Error", JOptionPane.ERROR_MESSAGE);
			
		}  else if (personGender.equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter a Gender.",
					"Add Person Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (personDOBField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter a Date of Birth.",
					"Add Person Error", JOptionPane.ERROR_MESSAGE);
						
		} else {
			Date personDOB = null;
			try {
				personDOB = format.parse(personDOBField.getText());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Invalid date format (format: dd-mm-yyyy).",
						"Add Person Error", JOptionPane.ERROR_MESSAGE);
			}
			
			Calendar calDOB	= new GregorianCalendar();
			calDOB.setTime(personDOB);
			Calendar currentDate = new GregorianCalendar();
			//check date not in future								
			if (calDOB.compareTo(currentDate) <= 0) {
				personFullNameField.setText("");
				personGenderField.setText("");
				personDOBField.setValue(null);
				
				Person newPerson = new Person(personFullName, 
						personGender, calDOB);
				
				w.actors().add(newPerson);
				refreshActors();	
			} else {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter past or current date.",
						"Add Person Error", JOptionPane.ERROR_MESSAGE);
			}			
		}		
	}
	/**
	 * Add an Actor of type robot to the world. An initial location must also
	 * be selected.
	 */
	private void addRobotButton() {
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please select a start Place.",
					"Add Robot Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			if (robotPurposeTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter robot ID.",
						"Add Robot Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (robotPurposeTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter robot Purpose.",
						"Add Robot Error", JOptionPane.ERROR_MESSAGE);
				
			} else {
				try {
					int robotID = Integer.parseInt(robotIDTextField.getText());
					String robotPurpose = robotPurposeTextField.getText();
					
					Robot newRobot = new Robot(robotID, robotPurpose, 
							selectedPlace);
					
					w.actors().add(newRobot);
					
					robotIDTextField.setText("");
					robotPurposeTextField.setText("");
					
					refreshActors();
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(jf, 
							"Error: Robot ID should be an integer.",
							"Add Robot Error", JOptionPane.ERROR_MESSAGE);
					
					}	
			}						
		}		
	}
	
	/**
	 * Add an Animal to the world.
	 */
	private void addAnimalButton() {
		if (animalNameTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter Animal Name.",
					"Add Animal Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (animalTypeTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter Animal Type (e.g. Bird, Mammal etc.).",
					"Add Animal Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (animalGenderTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter Animal Gender.",
					"Add Animal Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (animalNumLegsTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please Enter Animal's Number of Legs.",
					"Add Animal Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			try {
				String animalName = animalNameTextField.getText();
				String animalType = animalTypeTextField.getText();
				String animalGender = animalGenderTextField.getText();			
				int animalNumLegs = Integer.parseInt(
						animalNumLegsTextField.getText());
				
				Animal newAnimal = new Animal(animalName, animalType, 
						animalGender, animalNumLegs);
				w.actors().add(newAnimal);
				animalNameTextField.setText("");
				animalTypeTextField.setText("");
				animalGenderTextField.setText("");
				animalNumLegsTextField.setText("");
				refreshActors();
					
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Num. Legs should be an integer",
						"Add Animal Error", JOptionPane.ERROR_MESSAGE);	
					}		
		}		
	}
	
	/**
	 * Builds GUI for the actors pane containing a list of Actor's and a text
	 * field for adding a new actor to the World.
	 */
	private void buildPeople() {
		actorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actorList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(final ListSelectionEvent lse) {
				selectedActor = (Actor) actorList.getSelectedValue();
				if (selectedActor != null) {
					stuff.setListData(
							selectedActor.inventory().toArray(new Thing[0]));
					history.setListData(
							selectedActor.history().toArray(new Place[0]));
					roomList.setSelectedValue(selectedActor.location(), true);
				}
			}
		});
		
		JPanel jpp = new JPanel();
		jpp.setLayout(new BoxLayout(jpp, BoxLayout.PAGE_AXIS));
		jpp.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Actors"));
		
		JScrollPane actorPane = new JScrollPane(actorList, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
				
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new GridLayout(1, 3));
		/*
		 * Add an Person to the world.
		 *
		 */
		JLabel personFirstNameLabel = new JLabel("Full Name: ");			
		JLabel personGenderLabel = new JLabel("Gender: ");			
		JLabel personDOBLabel = new JLabel("DOB (dd-mm-yyyy): ");		
		JButton addActor = new JButton("Add Person");		
		addActor.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				addPersonButton();
			}
		});				
		JPanel addActors = new JPanel();
		addActors.setLayout(new BoxLayout(addActors, BoxLayout.PAGE_AXIS));
		addActors.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Person"));
		
		personFullNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				personFullNameField.getPreferredSize().height));
		
		personGenderField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				personGenderField.getPreferredSize().height));
		
		personDOBField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				personDOBField.getPreferredSize().height));
		
				addActor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
						addActor.getPreferredSize().height));		
				
		addActors.add(personFirstNameLabel);
		addActors.add(personFullNameField);
		addActors.add(personGenderLabel);
		addActors.add(personGenderField);
		addActors.add(personDOBLabel);
		addActors.add(personDOBField);
		addActors.add(addActor);
		addButtons.add(addActors);
		/*
		 * Add a Robot to the World
		 */
		JPanel addRobot = new JPanel();
		addRobot.setLayout(new BoxLayout(addRobot, BoxLayout.PAGE_AXIS));
		addRobot.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Robot"));
		
		JLabel robotIDtextLabel = new JLabel("Robot ID: ");
		JLabel robotPurposeTextLabel = new JLabel("Purpose: ");
		JLabel selectionNote = new JLabel("(Note: please select "
				+ "starting place)");
		
		JButton addRobotButton = new JButton("Add Robot");
		
		addRobotButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				addRobotButton();
			}
		});
		
		robotIDTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				robotIDTextField.getPreferredSize().height));
		robotPurposeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				robotPurposeTextField.getPreferredSize().height));
		addRobotButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				addRobotButton.getPreferredSize().height));
		
		
		addRobot.add(robotIDtextLabel);
		addRobot.add(robotIDTextField);
		addRobot.add(robotPurposeTextLabel);
		addRobot.add(robotPurposeTextField);
		addRobot.add(addRobotButton);
		addRobot.add(selectionNote);
		addButtons.add(addRobot);		
		/*
		 * Add an Animal to the world.
		 */
		JPanel addAnimal = new JPanel();
		addAnimal.setLayout(new BoxLayout(addAnimal, BoxLayout.PAGE_AXIS));
		addAnimal.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Animal"));		
		
		JLabel animalNameLabel = new JLabel("Name: ");
		JLabel animalTypeLabel = new JLabel("Type (e.g. Bird): ");
		JLabel animalGenderLabel = new JLabel("Gender: ");
		JLabel animalNumLegsLabel = new JLabel("Num. Legs: ");		
		JButton addAnimalButton = new JButton("Add Animal");		
		addAnimalButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				addAnimalButton();
			}
		});
		
		animalNameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				animalNameTextField.getPreferredSize().height));
		animalTypeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				animalTypeTextField.getPreferredSize().height));
		animalGenderTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				animalGenderTextField.getPreferredSize().height));
		animalNumLegsTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				animalNumLegsTextField.getPreferredSize().height));
		addAnimalButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				addAnimalButton.getPreferredSize().height));
		
		addAnimal.add(animalNameLabel);
		addAnimal.add(animalNameTextField);
		addAnimal.add(animalTypeLabel);
		addAnimal.add(animalTypeTextField);
		addAnimal.add(animalGenderLabel);
		addAnimal.add(animalGenderTextField);
		addAnimal.add(animalNumLegsLabel);
		addAnimal.add(animalNumLegsTextField);
		addAnimal.add(addAnimalButton);
		addButtons.add(addAnimal);
		
		jpp.add(actorPane);
		jpp.add(addButtons);
		
		jpLists.add(jpp);
	}

	
	/**
	 * Builds GUI for the room's pane containing a list of Room's and text 
	 * fields to add/edit Room in the World and set the size of the selected 
	 * Room.
	 */
	private void buildRooms() {
		roomList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(final ListSelectionEvent lse) {
				Place r = (Place) roomList.getSelectedValue();
				selectedPlace = r;
				if (r != null) {
					contents.setListData(r.contents().toArray(new Thing[0]));
					//display properties for selected room in text fields
					showSelectedPlaceDeatals();					
				}
			}
		});		
		/*
		 * Add a Room to the world.
		 *
		 */
		JLabel roomNameLabel = new JLabel("Label: ");
		JLabel roomLevelLabel = new JLabel("Level: ");
		JLabel roomDescription = new JLabel("Description: ");
		JButton addRoom = new JButton("Edit/Add Room");		
		
		addRoom.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				addRoom();
			}
		});			
		/*
		 * Edit the size of the selected Room.
		 *
		 */
		JLabel roomWidthLabel = new JLabel("Width (m): ");
		JLabel roomDepthLabel = new JLabel("Depth (m): ");
		JButton setSize = new JButton("Set Size");		
		
		setSize.addActionListener(new ActionListener() {
			
			public void actionPerformed(final ActionEvent ae) {
				editPlaceSize();			
			}
		});
		
		JPanel jpr = new JPanel();
		jpr.setLayout(new BoxLayout(jpr, BoxLayout.PAGE_AXIS));
		jpr.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Places"));
		
		JScrollPane roomPane = new JScrollPane(roomList, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new GridLayout(1, 3));	
		
		JPanel addRooms = new JPanel();
		addRooms.setLayout(new BoxLayout(addRooms, BoxLayout.PAGE_AXIS));
		addRooms.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Room"));
		
		roomNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				roomNameField.getPreferredSize().height));
		roomLevelField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				roomLevelField.getPreferredSize().height));
		descriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				descriptionField.getPreferredSize().height));
		addRoom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				addRoom.getPreferredSize().height));
		
		addRooms.add(roomNameLabel);
		addRooms.add(roomNameField);		
		addRooms.add(roomLevelLabel);
		addRooms.add(roomLevelField);		
		addRooms.add(roomDescription);
		addRooms.add(descriptionField);		
		addRooms.add(addRoom);
		addButtons.add(addRooms);		
		
		JPanel setRoomSize = new JPanel();
		setRoomSize.setLayout(new BoxLayout(setRoomSize, BoxLayout.PAGE_AXIS));
		setRoomSize.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Place Size"));
		
		roomWidthField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				roomWidthField.getPreferredSize().height));
		roomDepthField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				roomDepthField.getPreferredSize().height));
		setSize.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				setSize.getPreferredSize().height));
		
		setRoomSize.add(roomWidthLabel);
		setRoomSize.add(roomWidthField);
		setRoomSize.add(roomDepthLabel);
		setRoomSize.add(roomDepthField);
		setRoomSize.add(setSize);				
		/*
		 * Add or edit a forest Place in world.
		 */
		JPanel addForest = new JPanel();
		addForest.setLayout(new BoxLayout(addForest, BoxLayout.PAGE_AXIS));
		addForest.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(), "Forest"));
		JLabel nameLabelField = new JLabel("Forest Name:");
		addForest.add(nameLabelField);
				
		addForest.add(forestNameField);
				JLabel forestTypeLabelField = new JLabel("Forest Type: ");
		addForest.add(forestTypeLabelField);
		addForest.add(forestTypeTextField);
		JLabel climateLabelField = new JLabel("Climate: ");
		addForest.add(climateLabelField);
		addForest.add(climateTextField);
		
		JButton addForestButton = new JButton("Add/Edit Forest");
		addForest.add(addForestButton);
		
		forestNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				forestNameField.getPreferredSize().height));
		forestTypeTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				forestTypeTextField.getPreferredSize().height));
		climateTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				climateTextField.getPreferredSize().height));
		addForestButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 
				addForestButton.getPreferredSize().height));
		addForestButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);	
		
		addForestButton.addActionListener(new ActionListener() {			
			public void actionPerformed(final ActionEvent ae) {
				addForestButton();
			}
		});	
		
		addButtons.add(addRooms);
		addButtons.add(addForest);
		addButtons.add(setRoomSize);
		
		jpr.add(roomPane);
		jpr.add(addButtons);
		
		jpLists.add(jpr);		
	}
	
	/**
	 * Show the selected Place's details in the appropriate text field.
	 */
	private void showSelectedPlaceDeatals() {
		if (selectedPlace instanceof Room) {
			
			forestNameField.setText("");
			forestTypeTextField.setText("");
			climateTextField.setText("");
			
			roomNameField.setText(selectedPlace.label());
			roomLevelField.setText(Integer.toString((
					(Room) selectedPlace).level()));
			descriptionField.setText(selectedPlace.description());
			
			roomWidthField.setText(Double.toString(
					selectedPlace.width()));
			roomDepthField.setText(Double.toString(
					selectedPlace.depth()));
		} else if (selectedPlace instanceof Forest) {
			
			roomNameField.setText("");
			roomLevelField.setText("");
			descriptionField.setText("");
			
			forestNameField.setText(selectedPlace.label());
			forestTypeTextField.setText((
					(Forest) selectedPlace).forestType());
			climateTextField.setText((
					(Forest) selectedPlace).climate());
			roomWidthField.setText(Double.toString(
					selectedPlace.width()));
			roomDepthField.setText(Double.toString(
					selectedPlace.depth()));						
		}
	}
	
	/**
	 * Add or edit a Place of Type Room in the world.
	 */
	private void addRoom() {
		if (selectedPlace == null) {	
			if (roomNameField.getText().equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter room label.",
						"Add Room Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (roomLevelField.getText().equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter room level.",
						"Add Room Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (descriptionField.getText().equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter room description.",
						"Add Room Error", JOptionPane.ERROR_MESSAGE);
				
			} else {
				try {
					String roomName = roomNameField.getText();
					int roomLevel = Integer.parseInt(
							roomLevelField.getText());
					String description = descriptionField.getText();
					roomNameField.setText("");
					roomLevelField.setText("");
					descriptionField.setText("");

					Room newRoom = new Room(roomName, 
							roomLevel, description);
					w.places().add(newRoom);
					refreshRooms();
				
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(jf, 
							"Error: level should be an integer.",
							"Add Room Error", JOptionPane.ERROR_MESSAGE);
					
				}						
			}
		} else if (selectedPlace instanceof Room) {
			try {
				String roomName = roomNameField.getText();
				int roomLevel = Integer.parseInt(
						roomLevelField.getText());
				String description = descriptionField.getText();
				roomNameField.setText("");
				roomLevelField.setText("");
				descriptionField.setText("");						
				Room room = (Room) selectedPlace;
				room.setLabel(roomName);
				room.setLevel(roomLevel);
				room.setDescription(description);
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(jf, 
						"Error: level should be an integer.",
						"Add Room Error", JOptionPane.ERROR_MESSAGE);
				}					
		} else if (!(selectedPlace instanceof Room)) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please select a Room or clear selection.",
					"Edit Room Error", JOptionPane.ERROR_MESSAGE); 	
			
		}
	}
	
	/**
	 * Edit the dimensions of a Place.
	 */
	private void editPlaceSize() {
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please select a Place.",
					"Set Size Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (roomWidthField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please enter Place width.",
					"Set Size Error", JOptionPane.ERROR_MESSAGE);
			
		} else if (roomDepthField.getText().equals("")) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please enter Place depth.",
					"Set Size Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			try {
				double roomWidth = Double.parseDouble(
					roomWidthField.getText());
				double roomDepth = Double.parseDouble(
					roomDepthField.getText());
			
				roomWidthField.setText("");
				roomDepthField.setText("");
			
				selectedPlace.setSize(roomWidth, roomDepth);
			
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(jf, 
						"Error: not a number",
						"Set Size Error", JOptionPane.ERROR_MESSAGE);
		}						
	}
	}
	
	/**
	 * Add or edit a Place of type Forest.
	 */
	private void addForestButton() {
		String forestName = forestNameField.getText();
		String forestType = forestTypeTextField.getText();
		String forestClimate = climateTextField.getText();
		//Nothing selected - create new forest.
		if (selectedPlace == null) {	
			if (forestName.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Forest Name.",
						"Add Forest Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (forestType.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Forest Type.",
						"Add Forest Error", JOptionPane.ERROR_MESSAGE);
				
			} else if (forestClimate.equals("")) {
				JOptionPane.showMessageDialog(jf, 
						"Error: Please enter Forest Climate.",
						"Add Forest Error", JOptionPane.ERROR_MESSAGE);
				
			} else {
				Forest newForest = new Forest(forestName, forestType,
						forestClimate);
				w.places().add(newForest);
				
				forestNameField.setText("");
				forestTypeTextField.setText("");
				climateTextField.setText("");
				refreshRooms();				
			}			
			
		//Forest selected - edit selected forest	
		} else if (selectedPlace instanceof Forest) {
			Forest selectedForest = (Forest) selectedPlace;
			selectedForest.setLabel(forestName);
			((Forest) selectedForest).setForestType(forestType);
			((Forest) selectedForest).setClimate(forestClimate);
			
			forestNameField.setText("");
			forestTypeTextField.setText("");
			climateTextField.setText("");
			refreshRooms();
		} else if (!(selectedPlace instanceof Forest)) {
			JOptionPane.showMessageDialog(jf, 
					"Error: Please select a Forest or clear selection.",
					"Edit Forest Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	/**
	 * Create an empty world and WorldView to be notified of changes in the
	 * model.
	 */
	private void buildWorld() {
		w = new World();
		//w.demoWorld();
		roomList.setListData(w.places().toArray(new Place[0]));
		actorList.setListData(w.actors().toArray(new Actor[0]));
		
		// A separate view
		WorldView wv = new WorldView(w);
		wv.addObserver(this);
	}
	
	/**
	 * Button which moves an Actor from one Place to another.
	 */
	private void gotoButton() {
		Object o = roomList.getSelectedValue();
		if (o instanceof Place) {
			if (selectedActor == null) {
				JOptionPane.showMessageDialog(jf, "No actor selected",
						"Got To Error", JOptionPane.ERROR_MESSAGE);
				System.err.println("No actor selected");
			} else {
				selectedPlace = (Place) o;
				if (selectedPlace.equals(selectedActor.location())) {
					
					JOptionPane.showMessageDialog(jf,
							"Actor already at selected location.",
					    "Got To Warning",
					    JOptionPane.WARNING_MESSAGE);
				} else {
					 if (selectedActor instanceof Animal) {
						 if (selectedPlace instanceof Forest) {
							 System.out.println("moving " + selectedActor.name()
										+ " from " + selectedActor.location() 
										+ " to " 
										+ ((Place) 
												roomList.getSelectedValue()));
								selectedActor.moveTo(
										(Place) roomList.getSelectedValue());
						 } else {
							 JOptionPane.showMessageDialog(jf,
										"Animals can only move to Forests.",
								    "Got To Warning",
								    JOptionPane.WARNING_MESSAGE);
							 }
							
						} else {
							System.out.println("moving " + selectedActor.name()
									+ " from " + selectedActor.location() 
									+ " to " 
									+ ((Place) roomList.getSelectedValue()));
							selectedActor.moveTo(
									(Place) roomList.getSelectedValue());
						}
					
				}					
			}
		} else {
			JOptionPane.showMessageDialog(jf, "Not a Room---you can't go there",
					"Got To Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Button for Actor's to take selected items from their current Place.
	 */
	private void takeButton() {
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, "Can't take: no Room selected.",
					"Take Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (selectedActor == null)  {
			JOptionPane.showMessageDialog(jf, "Can't take: no Actor selected.",
					"Take Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (selectedActor.location() != selectedPlace) {
			JOptionPane.showMessageDialog(jf, "Must be in " 
					+ selectedPlace.label() + " to take this item.", 
					"Take Error", JOptionPane.ERROR_MESSAGE);			
			return;
		} else {	
			selectedThing = (Thing) contents.getSelectedValue();
			if (selectedThing != null) {
				selectedActor.take(selectedThing);					
			}
		}
	}
	/**
	 * Button for an Actor to drop a Thing in the current Room.
	 */
	private void dropButton() {
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, "Can't drop: no Room selected",
					"Drop Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (selectedActor == null)  {
			JOptionPane.showMessageDialog(jf, "Can't drop: no Actor selected",
					"Take Error", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if (selectedActor.location() != selectedPlace) {
			// will drop it where we are, irrespective of list state
			roomList.setSelectedValue(selectedPlace, true);
		}	
		selectedThing = (Thing) stuff.getSelectedValue();
		if (selectedThing != null) {
			selectedActor.drop(selectedThing);
		}
	}
	/**
	 * Log the selected person onto the selected computer in the same room.
	 */
	private void logonButton() {
		selectedThing = (Thing) contents.getSelectedValue();
		if (selectedPlace == null) {
			JOptionPane.showMessageDialog(jf, 
					"Please select a location.",
					"Computer Log On Error", JOptionPane.ERROR_MESSAGE);
		} else if (selectedThing == null) {
			JOptionPane.showMessageDialog(jf, 
					"Please select a Computer.",
					"Computer Log On Error", JOptionPane.ERROR_MESSAGE);
		} else if (selectedActor == null) {
			JOptionPane.showMessageDialog(jf, 
					"Please select a Person.",
					"Computer Log On Error", JOptionPane.ERROR_MESSAGE);
		} else if (selectedActor.location() != selectedPlace) {
			JOptionPane.showMessageDialog(jf, 
					"Person should be in the same location as Computer.",
					"Computer Log On Error", JOptionPane.ERROR_MESSAGE);
		} else if (selectedThing instanceof Computer
				&& selectedActor instanceof Person) {
			if (((Computer) selectedThing).loggedOn() == null) {
				((Computer) selectedThing).logon((Person) selectedActor);
			} else {
				Computer selectedComp = (Computer) selectedThing;
				if (((Computer) selectedComp).robotControlled() != null) {
					((Computer) selectedComp).robotControlled().powerOff();
				}
				
				((Computer) selectedComp).logOff();
			}		
			
			
			
		} else { 
			JOptionPane.showMessageDialog(jf, 
					"Please select a Person and Computer.",
					"Computer Log On Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	/**
	 * Button which allows selected actors to consume food items.
	 */
	 private void eatButton() {
		 if (selectedActor == null) {
			 JOptionPane.showMessageDialog(jf, 
						"Please select a Person.",
						"Eat Error", JOptionPane.ERROR_MESSAGE);
		 } else if (selectedActor instanceof Person) {
			 selectedThing = stuff.getSelectedValue();
			 if (selectedThing instanceof Food) {
				 ((Person) selectedActor).eat((Food) selectedThing);
			 } else {
				 JOptionPane.showMessageDialog(jf, 
							"Please select a Food item.",
							"Eat Error", JOptionPane.ERROR_MESSAGE);
			 }
		 } else if (selectedActor instanceof Animal) {
			 selectedThing = contents.getSelectedValue();
			 if (selectedActor.location().contents().contains(selectedThing)) {
				 if (selectedThing instanceof Food) {
					 ((Animal) selectedActor).eat((Food) selectedThing);
				 } else {
					 JOptionPane.showMessageDialog(jf, 
								"Please select a Food item.",
								"Eat Error", JOptionPane.ERROR_MESSAGE);
				 }
			 } else {
				 JOptionPane.showMessageDialog(jf, 
						 "Cannot Eat: Item not in Animals current location.",
								"Eat Error", JOptionPane.ERROR_MESSAGE);
				 
			 }
		 } else {			 
			 JOptionPane.showMessageDialog(jf, 
					 "This Actor cannot Eat.",
							"Eat Error", JOptionPane.ERROR_MESSAGE);
		 }
		 
	 }
	
	/**
	 * Build general controls for manipulating Room's, Actor's, and Thing's in 
	 * the World. 
	 */
	private void buildControls() {
		/*
		 * Moves selected person to selected room.
		 */
		JButton go = new JButton("Go To");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				 gotoButton();
			}
		});		
		/*
		 * Selected person can take selected thing from selected room
		 *
		 */
		JButton take = new JButton("Take");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				takeButton();
			}
		});		
		/*
		 * Selected person can drop selected thing for inventory
		 */
		JButton drop = new JButton("Drop");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				dropButton();
			}
		});
		
		/*
		 * Log a persons onto a computer in the same room.
		 */
		JButton logonButton = new JButton("Log On/Off");
		logonButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				logonButton();
			}
		});
		
		/*
		 * Button for selected Person to Eat the selected Food item.
		 */
		JButton eatButton = new JButton("Eat Food");
		eatButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				eatButton();
			}
		});
		
		/*
		 * Create an new empty world.
		 */
		JButton newWorld = new JButton("New World");
		newWorld.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				w.actors().clear();
				w.places().clear();
				w.items().clear();				
				refreshRooms();
				refreshActors();
			}
		});
		/*
		 * Create an new demo world.
		 */
		JButton demoWorld = new JButton("Demo World");
		demoWorld.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				w.demoWorld();
				refreshRooms();
				refreshActors();
			}
		});
		/*
		 * Remove selected object.
		 */
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {	
				removeObjectButton();
			}
		});		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				actorList.clearSelection();
				roomList.clearSelection();
				contents.clearSelection();
				stuff.clearSelection();
				roomNameField.setText("");
				roomLevelField.setText("");
				descriptionField.setText("");
				roomWidthField.setText("");
				roomDepthField.setText("");
				forestNameField.setText("");
				forestTypeTextField.setText("");
				climateTextField.setText("");
				animalNameTextField.setText("");
				animalTypeTextField.setText("");
				animalGenderTextField.setText("");
				animalNumLegsTextField.setText("");
				thingNameField.setText("");
				thingDescriptionField.setText("");
				computerIDTextField.setText("");
				computerDescriptionTextField.setText("");
				foodNameTextField.setText("");
				foodTypeTextField.setText("");
				foodDescriptionTextField.setText("");
				plantNameTextField.setText("");
				plantTypeTextField.setText("");
				plantDescriptionTextField.setText("");
				plantQuantityTextField.setText("");
				robotIDTextField.setText("");
				robotPurposeTextField.setText("");
				personFullNameField.setText("");
				personGenderField.setText("");
				personDOBField.setText("");				
			}
		});
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ae) {
				jf.setVisible(false);
				jf.dispose();
				System.exit(0);
			}
		});
		
		JPanel buttons = new JPanel();
		JPanel otherButtons = new JPanel();
				
		buttons.add(go);
		buttons.add(take);
		buttons.add(drop);
		buttons.add(logonButton);
		buttons.add(eatButton);
				
		otherButtons.add(demoWorld);
		otherButtons.add(newWorld);
		otherButtons.add(remove);
		otherButtons.add(clear);
		otherButtons.add(quit);
		
		JPanel buttonsPane = new JPanel();
		buttonsPane.setLayout(new GridLayout(1, 2));
		
		
		buttonsPane.add(buttons);
		buttonsPane.add(otherButtons);
	
		constraints.gridx = 0;
		constraints.gridy = 1;
		jp.add(buttonsPane, constraints);
		
	}	
	/**
	 * Remove selected object from World.
	 */
	private void removeObjectButton() {
		//check if more than one Thing is selected.
		if (contents.getSelectedValue() != null 
				&& stuff.getSelectedValue() != null) {
			JOptionPane.showMessageDialog(jf, "Too many items selected",
					"Remove Error", JOptionPane.ERROR_MESSAGE);
			contents.clearSelection();
			stuff.clearSelection();
		//check if Thing in contents is selected.	
		} else if (contents.getSelectedValue() != null 
				&& stuff.getSelectedValue() == null) {
			selectedPlace.contents().remove(contents.getSelectedValue());
			w.items().remove(contents.getSelectedValue());
			refreshRooms();
		//check if room is selected and if and actor occupies it.	
		} else if (roomList.getSelectedValue() != null 
				&& actorList.getSelectedValue() == null) {
			boolean isOccupied = false;
			for (Actor actor : w.actors()) {
				if (actor.location() == selectedPlace) {
					isOccupied = true;
				}
			}
			if (isOccupied) {
				JOptionPane.showMessageDialog(jf, "Cannot remove if occupied",
						"Remove Error", JOptionPane.ERROR_MESSAGE);
			} else if (selectedPlace.contents().isEmpty()) {
				w.places().remove(selectedPlace);						
				refreshActors();
				refreshRooms();						
			} else {
				JOptionPane.showMessageDialog(jf, 
						"Please remove contents first",
						"Remove Error", JOptionPane.ERROR_MESSAGE);
			}					
		//check if item in actor inventory is selected.	
		} else if (stuff.getSelectedValue() != null 
				&& contents.getSelectedValue() == null) {
			selectedActor.inventory().remove(stuff.getSelectedValue());
			w.items().remove(stuff.getSelectedValue());
			refreshActors();
		//check if only actor is selected.	
		} else if (actorList.getSelectedValue() != null) {
			if (selectedActor.inventory().isEmpty()) {
				w.actors().remove(selectedActor);
				refreshActors();
			} else {
				JOptionPane.showMessageDialog(jf, "Please remove items from "
						+ "inventory", "Remove Error", 
						JOptionPane.ERROR_MESSAGE);
			}					
		} else {
			JOptionPane.showMessageDialog(jf, "Please select an object.", 
					"Remove Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Update actors list after possible room change. Do we really 
	 * need this if everything else is working properly?
	 */
	private void refreshActors() {
		actorList.setListData(w.actors().toArray(new Actor[0]));
		actorList.clearSelection();
		stuff.clearSelection();
		history.clearSelection();
		stuff.setListData(new Thing[0]);
		history.setListData(new Place[0]);
	}
	/**
	 * Update rooms list after possible room change.
	 * Do we really need this if everything else is
	 * working properly?
	 */
	private void refreshRooms() {
		roomList.setListData(w.places().toArray(new Place[0]));
		roomList.clearSelection();
		contents.setListData(new Thing[0]);
	}

	/**
	 * Assemble a new empty world.
	 * GUI includes separate WorldView viewer.
	 */
	public static void main(final String[] args) {
		WorldControl me = new WorldControl();
		me.buildGui();
		me.buildWorld();
		
	}


	/**
	 * Something in the world has changed. Update GUI content accordingly.
	 */
	@Override
	public final void update(final Observable arg0, final Object arg1) {
		// Something in the world changed
		System.out.println("Arg0: " + arg0);
		System.out.println("Arg1: " + arg1);
		
		refreshActors();
		refreshRooms();		
	}

}
