package arena;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import lejos.hardware.Button;

public class Camera {
	private static final int BUFFER_LENGTH = 2048;
	
	private DatagramSocket dsocket;
	private DatagramPacket packet;
	private byte[] buffer;
	
	
	
  public Camera() throws SocketException {
		super();
		
		this.buffer = new byte[BUFFER_LENGTH];
		this.dsocket = new DatagramSocket(8888);
		this.packet = new DatagramPacket(buffer, BUFFER_LENGTH);
  }
  
  public ArrayList<Pallet> getPalletCoordinates(ArrayList<Pallet> palletsArg) throws IOException {
	  ArrayList<Pallet> pallets = (ArrayList<Pallet>) palletsArg.clone();
	  
	  dsocket.receive(packet);

      // Convert the contents to a string, and display them
      String msg = new String(buffer, 0, packet.getLength());
      //System.out.println(packet.getAddress().getHostName() + ": " + msg);
      
      String[] palets = msg.split("\n");
      
      for (int i = 0; i < palets.length; i++) 
      {
      	String[] coord = palets[i].split(";");
      	int id = Integer.parseInt(coord[0]);
      	int x = Integer.parseInt(coord[1]);
      	int y = Integer.parseInt(coord[2]);
      	
      	pallets.get(i).setX(x);
      	pallets.get(i).setY(y);
      	
      	//pallets.add(new Pallet(x, y, id));
      	//System.out.println(Integer.toString(x) + " / " + Integer.toString(x) );
      }
      
      // Reset the length of the packet before reusing it.
      packet.setLength(this.BUFFER_LENGTH);

	  
	  return pallets;
  }




public static void main(String args[]) 
  {
    try  (DatagramSocket dsocket = new DatagramSocket(8888))
    {
      // Create a buffer to read datagrams into. If a
      // packet is larger than this buffer, the
      // excess will simply be discarded!
      byte[] buffer = new byte[2048];

      // Create a packet to receive data into the buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      // Now loop forever, waiting to receive packets and printing them.
      while (true) 
      {
    	System.out.println("-------------------");
        // Wait to receive a datagram
        dsocket.receive(packet);

        // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
        //System.out.println(packet.getAddress().getHostName() + ": " + msg);
        
        String[] palets = msg.split("\n");
        
        for (int i = 0; i < palets.length; i++) 
        {
        	String[] coord = palets[i].split(";");
        	int x = Integer.parseInt(coord[1]);
        	int y = Integer.parseInt(coord[2]);
        
        	System.out.println(Integer.toString(x) + " / " + Integer.toString(x) );
        }
        

        // Reset the length of the packet before reusing it.
        packet.setLength(buffer.length);
      }
     
    } 
    catch (Exception e) 
    {
      System.err.println(e);
    }
  }


}
           