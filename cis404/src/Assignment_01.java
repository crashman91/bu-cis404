import javax.swing.*;
import java.awt.*;

class Assignment_01 extends JFrame{

  JButton buttonPrev=new JButton("Prev");

  JLabel labelHeader=new JLabel("Database Browser", JLabel.CENTER);
  JLabel labelName=new JLabel("Name");

  JTextField textFieldName=new JTextField();

  public Assignment_01(String title){

    super(title);

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    JPanel cp=(JPanel)getContentPane();

    labelHeader.setFont(new Font("TimesRoman",Font.BOLD,24));
    labelHeader.setBounds(40,10,300,50);

    buttonPrev.setBounds(30,250,80,25);

    labelName.setBounds(10,80,80,25);

    textFieldName.setBounds(120,80,250,25);

    cp.setLayout(null);
    cp.add(labelHeader);
    cp.add(buttonPrev);
    cp.add(labelName);
    cp.add(textFieldName);

    addWindowListener(new java.awt.event.WindowAdapter() {

      public void windowClosing(java.awt.event.WindowEvent evt) {

        shutDown();
      }
    });
  }

  public void shutDown(){

    int returnVal=JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?");

    if(returnVal==JOptionPane.YES_OPTION){

      System.exit(0);
    }
  }

  public static void main(String args[]){

    Assignment_01 assignment_01 = new Assignment_01("Database Browser");

    assignment_01.setSize(400,350);
    assignment_01.setVisible(true);
  }
}