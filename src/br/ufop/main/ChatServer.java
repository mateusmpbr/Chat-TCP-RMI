package br.ufop.main;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
 
public class ChatServer extends UnicastRemoteObject implements ChatServerInt{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4956884019683835669L;
	private Vector<ChatClientInt> v=new Vector<>();	
	public ChatServer() throws RemoteException{}
		
	public boolean login(ChatClientInt a) throws RemoteException{	
		System.out.println(a.getName() + "  se conectou....");	
		a.tell("Você se conectou com sucesso!");
		publish(a.getName()+ " acabou de se conectar.");
		v.add(a);
		return true;		
	}
	
	public boolean logout(ChatClientInt a) throws RemoteException{	
		System.out.println(a.getName() + "  se desconectou....");	
		a.tell("Você se desconectou com sucesso!");
		publish(a.getName()+ " acabou de se desconectar.");
		v.remove(a);
		return true;		
	}
	
	public void publish(String s) throws RemoteException{
	    System.out.println(s);
		for(int i=0;i<v.size();i++){
		    try{
		    	ChatClientInt tmp=(ChatClientInt)v.get(i);
			tmp.tell(s);
		    }catch(Exception e){
		    	//Problema com cliente não conectado.
		    }
		}
	}
 
	public Vector<ChatClientInt> getConnected() throws RemoteException{
		return v;
	}
}