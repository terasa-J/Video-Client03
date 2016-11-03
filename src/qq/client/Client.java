package qq.client;
import java.io.*;
import java.net.*;

import qq.common.User;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c=new Client();
	}
	public Client(){
		try {
			Socket s=new Socket("127.0.0.1",1212);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			User user=new User();
			user.setName("Tersa");
			user.setPassword("123456");
			oos.writeObject(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
