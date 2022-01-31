package Play;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Play.MainPanel.Map;

public class ProtectionPanel extends ControlShape {
	// 방역 패널 바이러스를 없애는 데 사용되는 패널이다.
	ControlPanel Controlpanel;
	ImageIcon RedVirus = new ImageIcon(Map.class.getResource("../Image/RedVirus.png"));
	ImageIcon BlueVirus = new ImageIcon(Map.class.getResource("../Image/BlueVirus.png"));
	ImageIcon YellowVirus = new ImageIcon(Map.class.getResource("../Image/YellowVirus.png"));
	ImageIcon BlackVirus = new ImageIcon(Map.class.getResource("../Image/BlackVirus.png"));

	ProtectionPanel(ControlPanel Controlpanel) {
		ArrayList<String> VirusLists = new ArrayList<>();// 현재 그 도시가 어떤 바이러스들이 있는 지 알아내기 위한 리스트
		this.Controlpanel = Controlpanel;
		String CurrentCity = Controlpanel.Mainpanel.character.getCurrentposition();// 현재 캐릭터의 위치를 확인
		VirusLists = Controlpanel.Mainpanel.citys.WhatVirus(CurrentCity);// 현재 그 도시에 어떤 색의 바이러스가 있는 지 알려준다.ㄴ

		for (int i = 0; i < VirusLists.size(); i++) {
			String temp = VirusLists.get(i);
			add(CreateRemoveLabel(CurrentCity, temp));
		} // 현재 그 도시에 위치한 바이러스들을 제거하기 위해 해당하는 바이러스제거 라벨을 만들어서 붙은다.
		add(new BackLabel(Controlpanel));
	}

	public JLabel CreateRemoveLabel(String Currentcity, String color) {
		// 해당되는 도시의 바이러스를 제거하기 위한 이벤트 라벨 Currentcity는
		JLabel e = new JLabel(new ImageIcon(Map.class.getResource("../Image/" + color + "Virus.png")));
		e.setText(color + "제거");
		e.setVerticalTextPosition(JLabel.BOTTOM);
		e.setHorizontalTextPosition(JLabel.CENTER);
		e.addMouseListener(new RemoveVirus(Currentcity, color));
		return e;
	}

	class RemoveVirus extends MouseAdapter {
		City city;
		String virus;

		public RemoveVirus(String Currentcity, String color) {
			city = Controlpanel.Mainpanel.citys.returnCity(Currentcity);
			// returnCity는 텍스트를 매개변수로 받아서 해당되는 도시의 객체를 반환하는 메소드
			this.virus = color;// 해당되는 색의 바이러스를 설정한다.
		}

		public void mousePressed(MouseEvent e) {// 마우스가 눌러지면 해당색깔의 바이러스가 제거된다. 만약 치료제가 개발되었다면 바이러스숫자는 0이된다
			if (virus.equals("Red") && !Game.RedCure)
				--city.Red;
			else if (virus.equals("Red") && Game.RedCure)
				city.Red = 0;
			else if (virus.equals("Black") && !Game.BlackCure)
				--city.Black;
			else if (virus.equals("Black") && Game.BlackCure)
				city.Black = 0;
			else if (virus.equals("Blue") && !Game.BlueCure)
				--city.Blue;
			else if (virus.equals("Blue") && Game.BlueCure)
				city.Blue = 0;
			else if (virus.equals("Yellow") && !Game.YellowCure)
				--city.Yellow;
			else if (virus.equals("Yellow") && Game.YellowCure)
				city.Yellow = 0;
			Controlpanel.invalidate();
			Controlpanel.removeAll();
			Controlpanel.add(new BasicSelect(Controlpanel));
			Controlpanel.revalidate();
			Controlpanel.repaint();
			Controlpanel.Mainpanel.repaint();
		}
	}
}
