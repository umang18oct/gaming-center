/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author umang18oct
 */
public class Kbc {
    
   private JLabel nameLabel; 
   private JTextField nameText;
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JButton quit;
   private JButton submit;
   private JPanel optionPanel2;
   private JPanel questionPanel;
   private JPanel optionPanel1;
   private JPanel statusPanel;
   private JPanel buttonPanel;
   private JPanel headerPanel;
   private int[] counter= new int[4];
   private static final int PADDING = 50; 
   ArrayList<String> questions, options, answers, money;
   private int round=1;
   private int rupees=0;
   private int totalRounds = 0;
   static String data1="";
   static int data2;
    
   public Kbc(String fName){
      prepareGUI(); 
      questions = new ArrayList<>();
      options = new ArrayList<>();
      answers = new ArrayList<>();
      money = new ArrayList<>();
      try{
          InputStream ips=new FileInputStream(fName); 
          InputStreamReader ipsr=new InputStreamReader(ips);
          try (BufferedReader br = new BufferedReader(ipsr)) {
              String line;
              int count=0;
              while ((line=br.readLine())!=null) {
                  if(count==0){
                      totalRounds+=1;
                      questions.add(line);
                  }
                  else if(count==1){
                      String[] option = line.split(",");
                      options.add(option[0]);
                      options.add(option[1]);
                      options.add(option[2]);
                      options.add(option[3]);
                  }
                  else if(count==2){
                      answers.add(line);
                  }
                  else{
                      money.add(line.trim());
                  }
                  count+=1;
                  count=count%4;
              }
          } catch (IOException ex) {
              Logger.getLogger(Kbc.class.getName()).log(Level.SEVERE, null, ex);
          }
        }       
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
   @SuppressWarnings("null")
   public static void main(String[] args) throws SQLException{
      Kbc mainObject = new Kbc("C://Users/umang18oct/Documents/NetBeansProjects/kbc/src/kbc/ques_ans.txt");      
      mainObject.mainApp();
      
	
   }
   
   public static void updateDB(){
       
      Statement stmt = null;
       try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			return;
		}
      System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://mdakram28-pc:5432/funWorld", "postgres",
					"admin");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

       try {
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
                stmt = connection.createStatement();
                String sql = "INSERT INTO kbc_leaderboard values ('"+data1+"','"+ data2+"')";
           stmt.executeUpdate(sql);
       } catch (SQLException ex) {
           Logger.getLogger(Kbc.class.getName()).log(Level.SEVERE, null, ex);
       }
                
   }

   private void prepareGUI(){
      mainFrame = new JFrame("KBC - Kaun Banega Crorepati");
      mainFrame.setSize(1050,500);
      mainFrame.setLayout(new GridLayout(7, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      
      headerPanel = new JPanel();
      headerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      nameLabel = new JLabel("", JLabel.CENTER);
      headerLabel = new JLabel("", JLabel.CENTER);   
      statusLabel = new JLabel("",JLabel.CENTER);    

      optionPanel2 = new JPanel();
      optionPanel2.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      statusPanel = new JPanel();
      statusPanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      questionPanel = new JPanel();
      questionPanel.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      optionPanel1 = new JPanel();
      optionPanel1.setLayout(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
      
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());

      mainFrame.add(headerPanel);
      mainFrame.add(statusPanel);
      mainFrame.add(questionPanel);
      mainFrame.add(optionPanel1);
      mainFrame.add(optionPanel2);
      mainFrame.add(buttonPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void mainApp(){                                    
      headerLabel.setText("Welcome!");
      nameLabel.setText("Enter name");
      JTextField nameText = new JTextField(6);
      
      JLabel roundLabel = new JLabel("",JLabel.LEFT);
      roundLabel.setText("Round : ");
      JTextField roundText = new JTextField(2);
      roundText.setText(Integer.toString(round));
      roundText.setEditable(false);
      JLabel priceLabel = new JLabel("",JLabel.CENTER);
      priceLabel.setText("Price for Question : ");
      JTextField priceText = new JTextField(4);
      priceText.setText(money.get(0));
      priceText.setEditable(false);
      JLabel moneyLabel = new JLabel("",JLabel.RIGHT);
      moneyLabel.setText("Money Earned : ");
      JTextField moneyText = new JTextField(4);
      moneyText.setText(Integer.toString(rupees));
      moneyText.setEditable(false);
      
      JLabel quesLabel = new JLabel("");
      quesLabel.setText("Question : "); 
      JTextField questionText = new JTextField(50);
      questionText.setText(questions.get(0));
      questionText.setEditable(false);
      
      
      JRadioButton optionA = new JRadioButton(options.get(0));
      JRadioButton optionB = new JRadioButton(options.get(1));
      JRadioButton optionC = new JRadioButton(options.get(2));
      JRadioButton optionD = new JRadioButton(options.get(3));
      

      optionA.setMnemonic(KeyEvent.VK_A);
      optionB.setMnemonic(KeyEvent.VK_B);
      optionC.setMnemonic(KeyEvent.VK_C);
      optionD.setMnemonic(KeyEvent.VK_D);
      
      counter[0]=-1;
      counter[1]=-1;
      counter[2]=-1;
      counter[3]=-1;

      optionA.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {   
             if(e.getStateChange()==1){
             counter[0]=0;
             System.out.println("YES");
             }
             else{
             counter[0]=-1;
             }
         }           
      });
      optionB.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {                             
             if(e.getStateChange()==1){
             counter[1]=1;
             }
             else{
             counter[1]=-1;
             }   
         }           
      });
      optionC.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
             if(e.getStateChange()==1){
             counter[2]=2;
             }
             else{
             counter[2]=-1;
             }
         }           
      });
      optionD.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {             
            if(e.getStateChange()==1){
             counter[3]=3;
             }
             else{
             counter[3]=-1;
             }
         }           
      });   
      //Grouping the radio buttons.
      ButtonGroup group = new ButtonGroup(); 
      group.add(optionA);
      group.add(optionB);
      group.add(optionC);
      group.add(optionD);
      
      System.out.println(answers);
      System.out.println(money);
      System.out.println(questions);
      System.out.println(options);
      
      quit = new JButton("QUIT");
      quit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.exit(0);
          }
      });      
      submit = new JButton("SUBMIT");
      submit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              int flag = 0;
              for(int i=0;i<4;i++){
                  if(counter[i]!=-1){
                      System.out.print("Wohoo");
                      if(options.get(((round-1)*4)+i).equals(answers.get(round-1))){
                        rupees+=Integer.parseInt(money.get(round-1));
                        round++;
                        if(round<=totalRounds)
                        {roundText.setText(Integer.toString(round));
                        questionText.setText(questions.get(round-1));
                        priceText.setText(money.get(round-1));
                        moneyText.setText(Integer.toString(rupees));
                        optionA.setText(options.get((round-1)*4));
                        optionB.setText(options.get((round-1)*4+1));
                        optionC.setText(options.get((round-1)*4+2));
                        optionD.setText(options.get((round-1)*4+3));
                        counter[i]=-1;
                        flag = 1;
                      }
                      }
                      break;
                  }
              }
              if(flag==0 || round>totalRounds){
                  submit.disable();
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(Kbc.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(Kbc.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  data1=nameText.getText();
                  data2=rupees;
                  
                  System.out.println(data1 + data2);
                  Kbc.updateDB();
                  System.exit(0);
              }
              System.out.println(flag);
         }           
      });
      
      statusLabel.setText("Hope you liked it!"); 
      
      headerPanel.add(Box.createRigidArea(new Dimension(250,0)));
      headerPanel.add(headerLabel);
      headerPanel.add(nameLabel);
      headerPanel.add(nameText);
      
      statusPanel.add(Box.createRigidArea(new Dimension(20,0)));
      statusPanel.add(roundLabel);
      statusPanel.add(roundText);
      statusPanel.add(priceLabel);
      statusPanel.add(priceText);
      statusPanel.add(moneyLabel);
      statusPanel.add(moneyText);          
      
      
      questionPanel.add(Box.createRigidArea(new Dimension(100,0)));
      questionPanel.add(quesLabel);
      questionPanel.add(questionText);
      
      optionPanel1.add(Box.createRigidArea(new Dimension(250,0)));
      optionPanel1.add(optionA);
      optionPanel1.add(optionB);
      
      optionPanel2.add(Box.createRigidArea(new Dimension(250,0)));
      optionPanel2.add(optionC);
      optionPanel2.add(optionD);
      
      buttonPanel.add(quit);
      buttonPanel.add(Box.createRigidArea(new Dimension(50,0)));
      buttonPanel.add(submit);
   
      mainFrame.setVisible(true);             
   }
}

    
