package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.pc.comm.NXTConnector;

public class BlueConnect {

	private static final BlueConnect INSTANCE = new BlueConnect();
	private NXTConnector conn;
	private DataOutputStream dos;
	private DataInputStream dis;


	private BlueConnect(){

		conn = new NXTConnector();
		boolean connected = conn.connectTo("btspp://");

		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}

		dos = new DataOutputStream(conn.getOutputStream());
		dis = new DataInputStream(conn.getInputStream());

	}

	public static BlueConnect getInstance()
	{
		return INSTANCE;
	}

	public void endConnectio()
	{
		System.out.println("End Connection");
		try {
			dos.writeInt(-6666);
			dos.flush();
			dis.close();
			dos.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void moveMotorA(int value)
	{
		try {
			dos.writeInt(value);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void moveMotorB(int value)
	{
		try {
			dos.writeInt(value);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendBeep2()
	{
		System.out.println("Send Beep 2");
		try {
			dos.writeInt(4000);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendBeep()
	{
		System.out.println("Send Beep");
		try {
			dos.writeInt(4001);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
