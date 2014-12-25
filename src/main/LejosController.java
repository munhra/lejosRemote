package main;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Rumbler;
import net.java.games.input.Component.Identifier.Axis;

public class LejosController {

	public final static String CONTROLLER_NAME = "Generic   USB  Joystick  ";
	public final static String PS3_CONTROLLER_NAME = "PLAYSTATION(R)3 Controller";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Just a controller");

				
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		Controller[] cs = (Controller[]) ce.getControllers();


		Controller usbJoystick = null;

		for (int i = 0; i < cs.length; i++){

			if (cs[i].getName().equals(CONTROLLER_NAME) || cs[i].getName().equals(PS3_CONTROLLER_NAME)){
				usbJoystick =  cs[i];
			}
			System.out.println(i + ". " + cs[i].getName() + ", " + cs[i].getType() ); 
	
		}
			
		System.out.println("Found Joystick ! "+usbJoystick.getName());
	
		Rumbler[] rumblers =  usbJoystick.getRumblers();
		
		for (int i = 0; i < rumblers.length ; i++){
			System.out.println("Found rumbler number "+i);
			rumblers[i].rumble(10);
		}
		
		Component[] component = usbJoystick.getComponents();
		
		for (int i = 0; i < component.length ; i++){
			System.out.println("Found component name "+component[i].getName());
		}
		
		ReadController readCtrl = new ReadController(usbJoystick);
		Thread t1 = new Thread(readCtrl);
		t1.start();
		
	}

}

