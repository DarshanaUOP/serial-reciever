import javax.swing.*;

/**
 * Created by acer on 25-Mar-18.
 */
public class Reciever {

    public static void main(String[] args) {
        GUI gui=new GUI();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(600,250);
        gui.setVisible(true);
        gui.setTitle("Serial Receiver");

    }
}
