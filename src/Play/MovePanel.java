package Play;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Play.MainPanel.Map;

class MovePanel extends JPanel {
	ArrayList<JLabel> labels = new ArrayList<JLabel>();// 선택라벨
	ArrayList<String> CityTexts = new ArrayList<String>();// MainPanel에 returncity가 String 이기 때문에 일단 String 으로저장
	ControlPanel Controlpanel;
	ImageIcon back = new ImageIcon(Map.class.getResource("../Image/back.png"));// 뒤로가기버튼

	MovePanel(ControlPanel Controlpanel) {
		this.Controlpanel = Controlpanel;
		CityTexts.addAll(Controlpanel.Mainpanel.returnCity());// 텍스트를 다 추가

		setSize(new Dimension(1920, 300));// 공간

		// 임시 맵그래프에서 인접한도시들의 정보를 가지고와서 label에 넣어서 출력해야함
		for (int i = 0; i < CityTexts.size(); i++) {
			labels.add(new JLabel(back));// 라벨에 이미지
			labels.get(i).setText((CityTexts.get(i)));// 라벨에 텍스트
			labels.get(i).setVerticalTextPosition(JLabel.BOTTOM);
			labels.get(i).setHorizontalTextPosition(JLabel.CENTER);
			// 라벨 중에서 텍스틑 아래 중간
			add(labels.get(i));// 라벨을 창에 붙이기
			String temp = CityTexts.get(i);// 내 현재 위치를 정확하게 주기 위해서 따로 텍스트를 뽑아냈다.
			Point pos = Controlpanel.Mainpanel.citys.CityPosition(CityTexts.get(i));
			String Color=Controlpanel.Mainpanel.citys.returnCity(temp).color;
			
			labels.get(i).addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
			//		Controlpanel.Mainpanel.character.setCurrentposition(temp);// 캐릭터의 현재 위치를 이동시키기 위해 이동도시(String)를 바꾸고
					Controlpanel.Mainpanel.character.setCC(temp, Color);
					Controlpanel.Mainpanel.character.setXY(pos.getX(), pos.getY());// 캐릭터의 좌표를 바꾼다.(xy)
					// System.out.println(pos);//도시좌표 출력
					Controlpanel.invalidate();
					Controlpanel.removeAll();
					Controlpanel.add(new BasicSelect(Controlpanel));
					Controlpanel.revalidate();
					Controlpanel.repaint();
				}

				public void mouseReleased(MouseEvent e) {

				}
			});
		}

		add(new BackLabel(Controlpanel));
	}
}
