import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main extends JFrame {
	JTextArea area;
	ArrayList<Character> list = new ArrayList<>();
	String data;
	public Main(String str) {
		super(str);
		setSize(750, 500);
		setLocationRelativeTo(this);
		setResizable(true);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Font font = new Font("Lucida Sans Typewriter", Font.BOLD, 18);
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(font);
		
		area.setBounds(1, 1, 748, 740);
		
		add(area);
	}
	class Sender{
		public void send() {
			int i=0;
			while(list.get(i)!='$') {
				area.append(list.get(i++)+"");
				if(i%65==0)
					area.append('\n'+"");
				if(list.get(i)=='.' || list.get(i)=='\t' || list.get(i)=='#')
					try {
						Thread.sleep(50);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				if(list.get(i)==' ')
					try {
						Thread.sleep(5);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				else
					try {
						Thread.sleep(200);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}
	class MainThread extends Thread{
		Sender sender=new Sender();;
		public void run() {
			synchronized(sender) {
				sender.send();
			}
		}
	}
	void prepare() throws IOException{
		FileReader fileReader = new FileReader("src/Data.txt");
		BufferedReader br = new BufferedReader(fileReader);
		while((data=br.readLine())!=null) {
			for(int i=0;i<data.length();i++)
				list.add(data.charAt(i));
		}
		list.add('$');
		br.close();
		fileReader.close();
	}
	public static void main(String[] args) {
		Main obj = new Main("#JUSTICE");
		try {
			obj.prepare();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		obj.setVisible(true);
		obj.new MainThread().run();
	}
}