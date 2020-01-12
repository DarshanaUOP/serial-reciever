import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by acer on 25-Mar-18.
 */
public class Connection {
    static SerialPort choosenPort;

    public static void CreateConn(String portName) {
        choosenPort = com.fazecast.jSerialComm.SerialPort.getCommPort(portName);
        choosenPort.setComPortTimeouts(com.fazecast.jSerialComm.SerialPort.TIMEOUT_SCANNER, 0, 0);
        choosenPort.setBaudRate(9600);
        choosenPort.setParity(0);
        try {
            if (choosenPort.openPort()) {

            } else {
                JOptionPane.showMessageDialog(null,"Connection not successful.","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Communication Error", "Error", JOptionPane.PLAIN_MESSAGE);
        }

    }
    public static int receive(){
        try {
            int request;
            Scanner scanner = new Scanner(choosenPort.getInputStream());
            while (scanner.hasNextLine()){
                try {
                    request = scanner.nextByte();
                    return request;
                }catch (Exception e){}
            }
            scanner.reset();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(null);
    }
    public static void closeConnection(){
        choosenPort.closePort();
    }
}
