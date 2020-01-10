package br.ufop.main;

import java.rmi.*;
import java.util.*;
 
public interface ChatServerInt extends Remote{	
	public boolean login (ChatClientInt a)throws RemoteException ;
	public boolean logout (ChatClientInt a)throws RemoteException ;
	public void publish (String s)throws RemoteException ;
	public Vector<ChatClientInt> getConnected() throws RemoteException ;
}