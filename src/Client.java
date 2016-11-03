import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 运用界面实现全双工
 */

public class Client extends JFrame implements ActionListener,KeyListener{
	private JTextField jtf;
	JButton btn;
	JScrollPane scrollPane;
	JTextArea jta;
	PrintWriter pw;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c=new Client();
	}
	public Client(){
		setTitle("客户端聊天室");
		getContentPane().setLayout(null);
		
		jtf = new JTextField();
		jtf.addKeyListener(this);
		jtf.setBounds(10, 230, 242, 28);
		getContentPane().add(jtf);
		jtf.setColumns(10);
		
		btn = new JButton("Send");
		btn.addActionListener(this);
		btn.setBounds(271, 230, 109, 30);
		getContentPane().add(btn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 414, 210);
		getContentPane().add(scrollPane);
		
		jta = new JTextArea();
		scrollPane.setViewportView(jta);
		
		this.setSize(450,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			Socket s=new Socket("127.0.0.1",1600);
			InputStreamReader isr=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(isr);
			pw=new  PrintWriter(s.getOutputStream(),true);
			while(true){
				String info=br.readLine();
				jta.append("服务器说：     "+info+"\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn)){
			pw.println(jtf.getText());
			jta.append("我说：      "+jtf.getText()+"\r\n");
			jtf.setText("");
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jtf)){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				pw.println(jtf.getText());
				jta.append("我说：      "+jtf.getText()+"\r\n");
				jtf.setText("");
			}
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
