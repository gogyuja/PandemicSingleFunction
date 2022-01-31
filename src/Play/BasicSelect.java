package Play;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Play.MainPanel.Map;

public class BasicSelect extends JPanel {// 기본선택 사항
	ImageIcon temp = new ImageIcon(Map.class.getResource("../Image/temp.png"));// 임시이미지아이콘
	ImageIcon Protection = new ImageIcon(Map.class.getResource("../Image/Protection.png"));// 치료 이미지
	String[] texts = { "이동", "치료", "건설", "공유", "개발", "카드", "능력" };
	JLabel[] labels = new JLabel[8];
	ControlPanel Controlpanel;

	public BasicSelect(ControlPanel Controlpanel) {
		this.Controlpanel = Controlpanel;
		setSize(new Dimension(1920, 300));
		labels[0] = new JLabel("이동", temp, JLabel.CENTER);
		labels[1] = new JLabel("치료", Protection, JLabel.CENTER);
		labels[2] = new JLabel("건설", temp, JLabel.CENTER);
		labels[3] = new JLabel("공유", temp, JLabel.CENTER);
		labels[4] = new JLabel("개발", temp, JLabel.CENTER);
		labels[5] = new JLabel("카드", temp, JLabel.CENTER);
		labels[6] = new JLabel("항공기이동", temp, JLabel.CENTER);
		labels[7] = new JLabel("연구소 이동", temp, JLabel.CENTER);
		for (int i = 0; i < 8; i++) {
			labels[i].setVerticalTextPosition(JLabel.BOTTOM);
			labels[i].setHorizontalTextPosition(JLabel.CENTER);
			// 라벨내에서 글자를 맨 밑 텍스트는 정중앙으로
			add(labels[i]);
		}

		labels[0].addMouseListener(new MouseAdapter() {// 이동 라벨을 눌렀을 때 Move 패널로 이동
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				// 이전에 있었던 모든 자원 해제
				Controlpanel.add(new MovePanel(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
				// 새로 그려주기
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		labels[1].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				Controlpanel.add(new ProtectionPanel(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
			}
		});
		labels[2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String Pos = Controlpanel.Mainpanel.character.getCurrentposition();
				Controlpanel.Havecard.BuildLabatory(Pos);
			}
		});

		labels[4].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// Controlpanel.Havecard.DevelopeCure(Controlpanel.Mainpanel.character.getColor());
				if (Controlpanel.Mainpanel.citys
						.returnCity(Controlpanel.Mainpanel.character.getCurrentposition()).Labatory) {
					Controlpanel.invalidate();
					Controlpanel.removeAll();
					Controlpanel.add(new CurePanel(Controlpanel));
					Controlpanel.revalidate();
					Controlpanel.repaint();
				}
			}
		});

		labels[5].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				Controlpanel.add(new HandPanel(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
			}
		});
		labels[6].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				Controlpanel.add(new AirplanePanel(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
			}
		});
		labels[7].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				Controlpanel.add(new MoveLabatoryPanel(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
			}
		});
	}
}