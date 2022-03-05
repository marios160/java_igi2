/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakpass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Mateusz
 */
public class Lamacz implements Runnable{

	public int id;
	public String Ip;
	public int Port;
	public String Rcon;
	public ArrayList<String> cmd = new ArrayList<String>();
	public Koordynator k;

	public Lamacz(int id,Koordynator kord, String ip, int port, String rcon,
			ArrayList<String> lista) {
		Ip = ip;
		Port = port;
		Rcon = rcon;
		this.k = kord;
		cmd = lista;
		this.id=id;
	}

	@Override
	public void run() {
		
		System.out.println("Rozmiar danych " + cmd.size());

		InetAddress host;
		try {
			host = InetAddress.getByName(Ip);

			// Construct the socket
			DatagramSocket socket = new DatagramSocket();

			// Construct the datagram packet
			int lol=1;
			while (lol==1) {
				String str = "/" + Rcon;
				byte[] data = str.getBytes();
				DatagramPacket packet = new DatagramPacket(data, data.length,
						host, Port);
				socket.send(packet);
				socket.setSoTimeout(300);
				packet.setData(new byte[100]);

				try {
					socket.receive(packet);

				} catch (Exception e) {
					k.timeouty++;
				//	System.out.println("RconTimeout");

				}

				String r = new String(packet.getData());
				// System.out.println(r);
				if ((!r.contains("granted")) && (!r.contains("already"))) {
					//System.out.println("Błedny rcon ");

				} else {

					System.out.println("Rcon OK "+id);
					lol=0;
					

				}
			}

			for (String s : cmd) {
				k.aktualny++;

				String str = "/sv sv" + s;
				byte[] data = str.getBytes();
				DatagramPacket packet = new DatagramPacket(data, data.length,
						host, Port);
				socket.send(packet);
				socket.setSoTimeout(300);
				packet.setData(new byte[100]);

				try {
					socket.receive(packet);

				} catch (Exception e) {
					k.timeouty++;
					// System.out.println("Timeout(" + k.timeouty + "/"
					// + k.aktualny + "): " + str);

				}

				String r = new String(packet.getData());
				// System.out.println(r);
				if (!r.contains("Indian")) {
					// System.out.println("nie");
				} else {

					System.out.println("Znaleziono Komende!: " + s);
					System.out.println(r);
					k.exit();
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
    
}
