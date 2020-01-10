package br.ufop.main;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
 
public class StartServer {
	public static void main(String[] args) {
		try {
				// Para usar <System.setSecurityManager(new SecurityManager());> é necessário definir o "security.policy" e os VM Arguments.  
			 	LocateRegistry.createRegistry(1099);
			 	
				ChatServerInt b=new ChatServer();
				// Trocar 127.0.0.1 pelo IP atual do servidor
				Naming.rebind("rmi://127.0.0.1/MeuChat", b);
				System.out.println("[System] Servidor de Chat está pronto.");
			}catch (Exception e) {
					System.out.println("Servidor de Chat falhou: " + e);
			}
	}
}