
package chatat.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Server implements ActionListener{
    
     JPanel panel1;
     JTextField textfield1;
     JButton button1;
     static JPanel textarea1;
     static JFrame frame1 = new JFrame();
     
     static ServerSocket sersocket1;
     static Socket socket1;
     static DataInputStream dinput;
     static DataOutputStream doutput;
     
     static Box verticalbox = Box.createVerticalBox();
     
     Boolean typing;
     
     Server(){
         frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         panel1 = new JPanel();
         panel1.setLayout(null);
          panel1.setBackground(new Color(0, 247, 255));
          panel1.setBounds(0,0,450,70);
           frame1.add(panel1);
           
//         ImageIcon imageicon1 = new ImageIcon(ClassLoader.getSystemResource("chatat/application/icons/3.png"));
//         Image image1 = imageicon1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//         ImageIcon imageicon2 = new ImageIcon(image1);
//         JLabel label1 = new JLabel(imageicon2);
//         label1.setBounds(5,5,30,30);
//         panel1.add(label1);

//          lab1.addMouseListner (new MouseAdapter(){
//              public void mouseClicked(MouseEvent ae){
//                  System.exit(0);
//              }
//        
//             });
         

//         ImageIcon imageicon2 = new ImageIcon(ClassLoader.getSystemResource("chatat/application/icons/1.png"));
//         Image image2 = imageicon2.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//         ImageIcon imageicon3 = new ImageIcon(image2);
//         JLabel label2 = new JLabel(imageicon3);
//         label2.setBounds(40,5,60,60);
//         panel1.add(label2);

//         ImageIcon imageicon3 = new ImageIcon(ClassLoader.getSystemResource("chatat/application/icons/video.png"));
//         Image image3 = imageicon3.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//         ImageIcon imageicon4 = new ImageIcon(image3);
//         JLabel label5 = new JLabel(imageicon4);
//         label5.setBounds(40,5,60,60);
//         panel1.add(label5);

//         ImageIcon imageicon4 = new ImageIcon(ClassLoader.getSystemResource("chatat/application/icons/phone.png"));
//         Image image5 = imageicon4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//         ImageIcon imageicon5 = new ImageIcon(image3);
//         JLabel label6 = new JLabel(imageicon5);
//         label6.setBounds(40,5,60,60);
//         panel1.add(label6);

//         ImageIcon imageicon5 = new ImageIcon(ClassLoader.getSystemResource("chatat/application/icons/3icon.png"));
//         Image image6 = imageicon5.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//         ImageIcon imageicon6 = new ImageIcon(image6);
//         JLabel label6 = new JLabel(imageicon6);
//         label6.setBounds(40,5,60,60);
//         panel1.add(label6);



      JLabel label30 = new JLabel("ChatAt");
        label30.setFont(new Font ("DIALOG",Font.LAYOUT_RIGHT_TO_LEFT,30));
       // label3.setForeground(Color.WHITE);
        label30.setBounds(15,10,300,50); 
        panel1.add(label30);
       

        JLabel label3 = new JLabel("SERVER (H)");
        label3.setFont(new Font ("SANS_SERIF",Font.BOLD,18));
       // label3.setForeground(Color.WHITE);
        label3.setBounds(150,23,150,20); 
        panel1.add(label3);
        
        JLabel label4 = new JLabel("Active Now");
        label4.setFont(new Font ("SANS_SERIF",Font.BOLD,14));
      //  label4.setForeground(Color.WHITE);
        label4.setBounds(150,40,100,20); 
        panel1.add(label4);
        
        Timer time = new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!typing){
                    label4.setText("Active Now");
                }
            }
        });
        time.setInitialDelay(2000);
        
        textarea1 = new JPanel();
        //textarea1.setBounds(6,75,422,540);
        textarea1.setFont(new Font("SANS-SERIF",Font.PLAIN,18));
      // frame1. add(textarea1);
       
       JScrollPane scrollbar = new JScrollPane(textarea1);
      scrollbar .setBounds(6,75,422,540);
      scrollbar.setBorder(BorderFactory.createEmptyBorder());
      
      
      ScrollBarUI ui = new BasicScrollBarUI(){
          protected JButton createDecreaseButton(int orientation){
              JButton button = super.createDecreaseButton(orientation);
              button.setBackground(new  Color(0, 247, 255));
               button.setForeground(Color.WHITE);
                 this.thumbColor = new Color(0, 247, 255);
                 this.trackColor = new Color(153,255,255);
               return button;
          }
          
           protected JButton createIncreaseButton(int orientation){
              JButton button = super.createDecreaseButton(orientation);
              button.setBackground(new  Color(0, 247, 255));
               button.setForeground(Color.WHITE);
               this.thumbColor = new Color(0, 247, 255);
               this.trackColor = new Color(153,255,255);
               return button;
          }
          
      };
        scrollbar.getVerticalScrollBar().setUI(ui);
        frame1. add(scrollbar);
        
        textfield1 = new JTextField();
        textfield1.setBounds(5,620,320,35);
        textfield1.setFont(new Font ("SANS_SERIF",Font.PLAIN,18));
        frame1.add(textfield1);
        
        textfield1.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
                label4.setText("typing...");
                time.stop();
                typing = true;
            }
            
            public void keyReleased(KeyEvent ke){
                typing = false;
                if(!time.isRunning()){
                    time.start();
                }
            }
        });
        
         button1 = new JButton("Send");
         button1.setBounds(338,620,88,35);
         button1.setBackground(new Color (0, 247, 255));
      // button1.setForeground(Color.WHITE);
         button1.setFont(new Font ("SANS_SERIF",Font.PLAIN,18));
         button1.addActionListener(this);
         frame1.add(button1);

         frame1.getContentPane().setBackground(new Color(0,204,204));
         frame1.setLayout(null);
         frame1.setSize(450,700);
         frame1.setLocation(200,100);
         //  setUndecorated(true);
         frame1.setVisible(true);
     }
     public void actionPerformed(ActionEvent ae){
    
         
        try{
            String out = textfield1.getText();
            saveMessageToFile(out);
        JPanel panel2 = formatLabel(out);
        
        textarea1.setLayout(new BorderLayout());
        
        JPanel rightpanel = new JPanel(new BorderLayout());
        rightpanel.add(panel2,BorderLayout.LINE_END);
        verticalbox.add(rightpanel);
        textarea1.add(verticalbox,BorderLayout.PAGE_START);
        verticalbox.add(Box.createVerticalStrut(15));
       // textarea1.add(panel2);
        
        doutput.writeUTF(out);
        textfield1.setText("");
          }catch(Exception e){
          System.out.println(e);
 }
    }
 
      public void saveMessageToFile(String message) throws FileNotFoundException{
          try(FileWriter f1 = new FileWriter("backup.txt");
                  PrintWriter p = new PrintWriter(new BufferedWriter(f1));){
            p.println("Server:: "+ message);
          }catch(Exception e){
          e.printStackTrace();
          }
      }
     
     public static JPanel formatLabel(String out){
         
         JPanel panel3 = new JPanel();
         panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
         JLabel label5 = new JLabel("<html><p style =\"width:150px\">"+out+"</p></html>");
         label5.setFont(new Font("Tahoma",Font.PLAIN,18));
         label5.setBackground(new Color (68,218,255));
         label5.setOpaque(true);
         label5.setBorder(new EmptyBorder(15,15,15,25));
         
         Calendar cal1 = Calendar.getInstance();
         SimpleDateFormat sdateformat = new SimpleDateFormat("HH:mm");
         
         JLabel label6 = new JLabel();
         label6.setText(sdateformat.format(cal1.getTime()));
         
         panel3.add(label5);
          panel3.add(label6);
         return panel3;
     }
     public static  void main(String[] args){
          new Server().frame1.setVisible(true);
        
          String msginput="";
         
          try{
          
            sersocket1 = new ServerSocket(1117);
            while(true){
            socket1 = sersocket1.accept();
            dinput = new DataInputStream(socket1.getInputStream());
            doutput = new DataOutputStream(socket1.getOutputStream());
            while(true){
            msginput = dinput.readUTF();
            JPanel panel7 =formatLabel(msginput);
            
            JPanel leftpanel = new JPanel(new BorderLayout());
            leftpanel.add(panel7,BorderLayout.LINE_START);
            verticalbox.add(leftpanel);
            frame1.validate();
            
//            sersocket1.close();
//            socket1.close();
            }
            }
        }catch(Exception exc){}

     }
}
