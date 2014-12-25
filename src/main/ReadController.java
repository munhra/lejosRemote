package main;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;

public class ReadController implements Runnable{

	private Controller usbController;
	private boolean stop;
	
	
	public ReadController(Controller controller){
		super();
		this.usbController = controller;
	}
	
	
	@Override
	public void run() {
		
		// create connection to NXT
		
		BlueConnect blConnect = BlueConnect.getInstance();
		
		
		while (!stop){
			//System.out.println("This come from inside the thread !!!");
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			usbController.poll();
			
			Component[] component = this.usbController.getComponents();
			
			for (int i = 0; i < component.length ; i++){
			
				//0 - Select
				//2 - RightStickDown
				//3 - Start
				//4 - PadUp
				//5 - PadRight
				//6 - PadDown
				//7 - PadLeft
				//8 - L2 digital
				//9 - R2 digital
				//10 - L1 digital
				//11 - R1 digital
				

				if (component[i].getIdentifier() == Component.Identifier.Button._11){
					if	(component[i].getPollData() == 1.0 ){
						blConnect.sendBeep();
					}
				}
				
				if (component[i].getIdentifier() == Component.Identifier.Button._10){
					if	(component[i].getPollData() == 1.0 ){
						blConnect.sendBeep2();
					}
				}
				
				if (component[i].getIdentifier() == Component.Identifier.Button._0){
					if	(component[i].getPollData() == 1.0 ){
						blConnect.endConnectio();
						stop = true;
					}
				}
				
				if (component[i].getIdentifier() == Component.Identifier.Axis.X){
					if (Math.abs(component[i].getPollData() * 1000)  >=  200){
						System.out.println("Value of y stick "+component[i].getPollData() * 1000);
						blConnect.moveMotorB((int)(component[i].getPollData() * 1000));
					}else{
						blConnect.moveMotorB(3000);
					}
				}

				if (component[i].getIdentifier() == Component.Identifier.Axis.Y){
					if (Math.abs(component[i].getPollData() * 100)  >=  2){
						//System.out.println("Value of y stick "+component[i].getPollData() * 100);
						blConnect.moveMotorA((int)(component[i].getPollData() * 100));
					}else{
						blConnect.moveMotorA(0);
					}
				}
			}
			
		}
	
	
	}

}
