/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mario.mautobannet;

import org.jnetpcap.packet.PcapPacket;
import static pl.mario.mautobannet.StartSniffer.a;

/**
 *
 * @author Mateusz
 */
public class PacketData {

    static int dos = 0;
    String ip;
    String data;
    PacketData p;
    PcapPacket packet;

    public PacketData(String ip, String data, PacketData p, PcapPacket packet) {
        this.ip = ip;
        this.data = data;
        this.p = p;
        this.packet = packet;
    }

    public void check() {
        if (this.data.equals(p.data)) {
            if (this.ip.equals(p.ip)) {
                if (this.packet.getCaptureHeader().timestampInMillis() + 25
                        > p.packet.getCaptureHeader().timestampInMillis()) {
                    dos++;
                }
            } else {
                dos = 0;
            }
        } else {
            dos = 0;
        }
        if (dos >= 20) {
            a.ban(ip, 4);
            dos = 0;
        }
    }

}
