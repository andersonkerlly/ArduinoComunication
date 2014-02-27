package br.edu.ifpb.pos.arduinocomunication;


import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;


public class ArduinoComunication {

	public static String temperature;
	
    

	public static String getTemperature() {		
		return temperature;
	}

	public static void run() throws IOException, InterruptedException, NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("COM3");
        RXTXPort port = (RXTXPort) portId.open("PortaSerial", 1000);
        port.setSerialPortParams(9600, 
                SerialPort.DATABITS_8, 
                SerialPort.STOPBITS_1, 
                SerialPort.PARITY_NONE
        );
        port.setFlowControlMode(
                SerialPort.FLOWCONTROL_NONE);
        //
        byte[] b = new byte[13];
        InputStream in = port.getInputStream();
        while (in.read(b) != -1){
            temperature= new String(b);
            System.out.println(temperature.trim());
            Thread.sleep(1000);
        }
        //
       
        port.close();
	}

}
