package Play;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Play.MainPanel.Map;

//컨트롤 패널은 캐릭터의 모든 행동을 제어하는 행동이 있는 패널이다.
public class ControlPanel extends ControlShape {// 컨트롤 패널
	ImageIcon back = new ImageIcon(Map.class.getResource("../Image/back.png"));// 뒤로가기버튼
	MainPanel Mainpanel;// 상위자원 MainPanel을 사용하기 위해 선언
	ControlPanel Controlpanel = this;// 자기 자신의 자원을 넘겨주기 위해
	HaveCard1 Havecard = new HaveCard1(Controlpanel);// 손패 카드

	ControlPanel(MainPanel Mainpanel) {
		this.Mainpanel = Mainpanel;// 상위자원을 연결해서 쓸수있게

		Havecard.insertCard(Controlpanel, "로스앤젤레스", "Yellow");
		Havecard.insertCard(Controlpanel, "카라치", "Black");
		Havecard.insertCard(Controlpanel, "런던", "Blue");
		Havecard.insertCard(Controlpanel, "GrantOfMoney");
		// setSize(new Dimension(1920, 200));
		setLayout(new BorderLayout()); // 컨트롤 패널의 기본
		add(new BasicSelect(Controlpanel));
	}

	public static int CheckType(String Card) {
		String array[] = { "평온한 하룻밤", "긴급공중수송", "항체보유자", "예측", "정부보조금" };
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(Card))
				return 2;
		}
		if (Card.equals("전염"))
			return 1;
		else
			return 3;
	}
}
