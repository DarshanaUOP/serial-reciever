import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by acer on 25-Mar-18.
 */
public class GUI extends JFrame {
    private JPanel back;
    private JTextArea txtRecived;
    private JButton btCon;
    private JLabel lbProductName,lblCompany;
    private JComboBox cmbCOM;


    GridBagConstraints g=new GridBagConstraints();

    public GUI(){
        back=new JPanel(new GridBagLayout());
        add(back);

        lbProductName=new JLabel("Serial Reciever");
        lbProductName.setFont(new Font(null,Font.BOLD,30));
        g.gridx =0;
        g.gridy=0;
        g.weightx=3;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=3;
        g.fill=GridBagConstraints.HORIZONTAL;
        back.add(lbProductName,g);

        txtRecived=new JTextArea("Data");
        txtRecived.setVisible(true);
        txtRecived.setFont(new Font(null,Font.BOLD,20));
        txtRecived.setForeground(Color.green);
        txtRecived.setBackground(Color.black);
        txtRecived.setSize(100,400);
        g.gridx =0;
        g.gridy=1;
        g.weightx=4;
        g.weighty=2;
        g.gridheight=1;
        g.gridwidth=4;
        g.fill=GridBagConstraints.BOTH;
        back.add(txtRecived,g);

        cmbCOM=new JComboBox();
        g.gridx =0;
        g.gridy=4;
        g.weightx=2;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL;
        back.add(cmbCOM,g);

        btCon=new JButton("Connect");
        g.gridx =2;
        g.gridy=4;
        g.weightx=2;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=2;
        g.fill=GridBagConstraints.HORIZONTAL;
        back.add(btCon,g);

        lblCompany=new JLabel("© Darshana Ariyaratna | darshana.uop@gmail.com | +94774901245");
        lblCompany.setFont(new Font(null,Font.BOLD,16));
        g.gridx =0;
        g.gridy=5;
        g.weightx=1;
        g.weighty=1;
        g.gridheight=1;
        g.gridwidth=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        back.add(lblCompany,g);

        //TODO new

        try{

            SerialPort[] portNames = SerialPort.getCommPorts();
            for (int i = 0; i < portNames.length; i++)
                cmbCOM.addItem(portNames[i].getSystemPortName());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error Loading COM Ports:\n"+e.getCause(),"Error",JOptionPane.ERROR_MESSAGE);
        }

        btCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btCon.getText().equals("Connect")){
                    try{
                        Connection.CreateConn(cmbCOM.getSelectedItem().toString());
                    }catch (Exception e1){}
                    btCon.setText("Disconnect");
                    cmbCOM.setEnabled(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (btCon.getText().equals("Disconnect")){
                                int recived=Connection.receive();
                                //System.out.println(recived);
                                txtRecived.setText(String.valueOf(recived));
                            }
                        }
                    }).start();


                }else if (btCon.getText().equals("Disconnect")){
                    Connection.closeConnection();
                    btCon.setText("Connect");
                    cmbCOM.setEnabled(true);
                }
            }
        });
    }
}
