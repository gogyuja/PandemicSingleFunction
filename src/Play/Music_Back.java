package Play;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
//�ڿ��� ���ֵǴ� ����. ������ �Ϲݹ���� Ȥ�� ���ӹ�������� ư��. Scene�� Letitbe�� �̰� ���� ư��.
public class Music_Back extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music_Back(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Game.class.getResource("../Muisc_Back/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	

	@Override
	public void run() {
		try {
			do {
			
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				player.play();
				
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
}
