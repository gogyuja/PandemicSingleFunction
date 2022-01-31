package Play;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

class MainPanel extends JLayeredPane implements KeyListener, MouseListener {
	// JLayerdPane이기 때문에 겹쳐서 패널을 올려넣을수가 있다.
	MainPanel Mainpanel = this;// 하위 클래스들에게 Mainpanel의 자원을 쓸 수 있도록 하기 위해서
	
	Map map = new Map();// 지도 단순히 이미지만 그린다.
	Characters characters = new Characters();// C키를 눌렀을 때 유저들이 어떤 카드를 가지고 있는지 확인하기 위해

	Chat chat = new Chat();// 투명 채팅창
	Citys citys = new Citys();// 도시 그래프
	Character character = new Character(Mainpanel);// 캐릭터말 캐릭터 좌표등
	ControlPanel Controlpanel = new ControlPanel(Mainpanel);// 밑에 컨트롤을 위해 필요한 패널
	static Music_Back mu=new Music_Back("Let it be.mp3", true);
	
	public MainPanel() {
		mu.start();
		this.setPreferredSize(new Dimension(1920, 1080));
		this.add(map, new Integer(0));// 맵은 단순히 이미지만 그려주기때문에 최하위
		map.setBounds(0, 0, 3000, 2000);

		this.add(characters, new Integer(10));// C키를 눌렀을 때 활성화 되는 현재 캐릭터들의 현황창.
		characters.setBounds(1520, 0, 400, 1080);

		this.add(chat, new Integer(10));// 반 투명 채팅창
		chat.setBounds(600, 500, 400, 250);

		this.add(Controlpanel, new Integer(20));// 이 컨트롤 패널에서 유저들의 행동 모든 것을 처리한다
		Controlpanel.setBounds(0, 840, 1920, 240);
		Controlpanel.setOpaque(false);
		// addFocusListener(new MyFocuseListener());//현재 패널이 키보드 포커싱을 알아먹는지 못 알아 먹는지
		// 알아보기 위하여
		this.setFocusable(true);
		this.addKeyListener(this);
		this.requestFocusInWindow();// 키포커싱 준것
	//

	}

	public ArrayList returnCity() {// 현재 캐릭터가 위치한 인접도시들을 다 반환하는 메소드다.
		ArrayList<String> list = citys.AdjacencyCitys(character.CurrentPositon);
		// Citys의 character.CurrentPosition String으로 현재 내가 위치한 도시를 알려준다.
		// AdjacencyCitys인접한 도시들을 ArraysList로 반환하는 메소드
		return list;
	}

	class Map extends JPanel {// 맵을 그려주는 패널, 맵만 그려줄뿐만아니라 현재 게임의 진행상황동 여기 그려준다.
		Image background = new ImageIcon(Map.class.getResource("../Image/map.png")).getImage();

		public Map() {
			setSize(3000, 2000);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, 3000, 2000, null, null);
			citys.draw(g);// 도시들의 원및 지도에서 보여지는 각종 이벤트을 그려준다.
			character.draw(g);// 캐릭터를 그린다
		}
	}

	class Characters extends JPanel {// 캐릭터들 모든 유저들의 현재상태를 보여주는 창 (단축키C)
		// 아직 구현안함
		public Characters() {
			setSize(new Dimension(400, 1080));
		}
	}

	class Chat extends JPanel {
		JTextArea textArea;
		JTextField textField;

		public Chat() {
			this.setOpaque(false);
			textArea = new JTextArea(12, 35);
			textField = new JTextField(35);
			textArea.setEditable(false);
			textArea.setFocusable(false);
			addFocusListener(new MyFocuseListener());

			JScrollPane scrollPane = new JScrollPane(textArea) {
				@Override
				protected void paintComponent(Graphics g) {
					try {
						Composite composite = ((Graphics2D) g).getComposite();

						((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
						g.setColor(getBackground());
						g.fillRect(0, 0, getWidth(), getHeight());

						((Graphics2D) g).setComposite(composite);
						paintChildren(g);
					} catch (IndexOutOfBoundsException e) {
						super.paintComponent(g);
					}
				}
			};
			scrollPane.addFocusListener(new MyFocuseListener());

			textArea.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			scrollPane.setOpaque(false);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			add(scrollPane, BorderLayout.CENTER);
			add(textField, BorderLayout.SOUTH);
			textField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					int key = e.getKeyCode();
					switch (key) {
					case KeyEvent.VK_ESCAPE:
						textField.setFocusable(false);

						break;
					case KeyEvent.VK_ENTER:
						textArea.append(textField.getText() + "\n");
						textField.setText("");

					}
				}
			});
		}
	}

	// 포커스 확인

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			map.setLocation(map.getLocation().x, map.getLocation().y + 30);
			break;

		case KeyEvent.VK_S:
			map.setLocation(map.getLocation().x, map.getLocation().y - 30);
			break;

		case KeyEvent.VK_D:
			map.setLocation(map.getLocation().x - 30, map.getLocation().y);
			break;

		case KeyEvent.VK_A:
			map.setLocation(map.getLocation().x + 30, map.getLocation().y);
			break;
		case KeyEvent.VK_C:

			if (characters.isVisible()) {
				characters.setVisible(false);
			} else {
				characters.setVisible(true);
			}
			break;
		case KeyEvent.VK_ENTER:
			chat.textField.requestFocus();
			chat.textField.setFocusable(true);
			break;

		case KeyEvent.VK_J:
			System.out.println("J눌러짐");
			character.setXY(500, 500);
			repaint();
			revalidate();
			break;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocus();
		this.setFocusable(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}