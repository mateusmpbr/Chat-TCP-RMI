package br.ufop.main;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
 
public class ChatUI{
  private ChatClient client;
  private ChatServerInt server;
  public void doConnect(){
	    if (connect.getText().equals("Conectar")){
	    	if (name.getText().length()<2){JOptionPane.showMessageDialog(frame, "Você precisa de digitar um nome."); return;}
	    	if (ip.getText().length()<2){JOptionPane.showMessageDialog(frame, "Você precisa de digitar um IP"); return;}	    	
	    	try{
				client=new ChatClient(name.getText());
	    		client.setGUI(this);
				server=(ChatServerInt)Naming.lookup("rmi://"+ip.getText()+"/MeuChat");
				server.login(client);
				updateUsers(server.getConnected());
			    connect.setText("Desconectar");			    
	    	}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(frame, "ERRO, Não foi possivel se conectar....");}		  
	      }else{
	    	  	updateUsers(null);
	    	  	connect.setText("Desconectar");
	    	  	try {
					server.logout(client);
					System.exit(0);
				} catch (RemoteException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, "ERRO, Não foi possivel se desconectar....");
				}
		}
	  }  
  
  public void sendText(){
    if (connect.getText().equals("Conectar")){
    	JOptionPane.showMessageDialog(frame, "Você precisa primeiro se conectar"); return;	
    }
      String st=tf.getText();
      st="["+name.getText()+"] "+st;
      tf.setText("");
      try{
    	  	server.publish(st);
  	  	}catch(Exception e){e.printStackTrace();}
  }

  public void writeMsg(String st){  tx.append(st + "\n");  }
 
  public void updateUsers(Vector<ChatClientInt> v){
      DefaultListModel<String> listModel = new DefaultListModel<>();
      if(v!=null) for (int i=0;i<v.size();i++){
    	  try{  String tmp=((ChatClientInt)v.get(i)).getName();
    	  		listModel.addElement(tmp);
    	  }catch(Exception e){e.printStackTrace();}
      }
      lst.setModel(listModel);
  }
  
  public static void main(String [] args){
	System.out.println("Estou me conectando...");
	new ChatUI();
  }  
  
  //UI
  public ChatUI(){
	    frame=new JFrame("Meu Chat");
	    JPanel main =new JPanel();
	    JPanel top =new JPanel();
	    JPanel cn =new JPanel();
	    JPanel bottom =new JPanel();
	    ip=new JTextField();
	    tf=new JTextField();
	    name=new JTextField();
	    tx=new JTextArea();
	    connect=new JButton("Conectar");
	    JButton bt=new JButton("Enviar");
	    lst=new JList<String>();        
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    top.add(new JLabel("Seu nome: "));top.add(name);    
	    top.add(new JLabel("Endereço do servidor: "));top.add(ip);
	    top.add(connect);
	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
	    cn.add(lst, BorderLayout.EAST);    
	    bottom.add(tf, BorderLayout.CENTER);    
	    bottom.add(bt, BorderLayout.EAST);
	    main.add(top, BorderLayout.NORTH);
	    main.add(cn, BorderLayout.CENTER);
	    main.add(bottom, BorderLayout.SOUTH);
	    main.setBorder(new EmptyBorder(10, 10, 10, 10));
	    //Events
	    connect.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ doConnect();   } });
	    ip.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){ doConnect();   } });
	    bt.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ sendText();   }  });
	    tf.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){ sendText();   }  });
	    
        //Faz scrolling automaticamente quando novas mensagens são enviadas
        DefaultCaret caret = (DefaultCaret)tx.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    tx.setEditable(false);
        
        frame.setContentPane(main);
	    frame.setSize(800,600);
	    frame.setVisible(true);
	    
        frame.setLocationRelativeTo(null);
	  }
	  
  	  JTextArea tx;
	  JTextField tf,ip, name;
	  JButton connect;
	  JList<String> lst;
	  JFrame frame;
}