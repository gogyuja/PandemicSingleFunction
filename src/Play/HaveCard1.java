package Play;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class HaveCard1 {
	ArrayList<Card> List = new ArrayList<Card>();
	int count = 0;
	ControlPanel Controlpanel;

	HaveCard1(ControlPanel Controlpanel) {
		this.Controlpanel = Controlpanel;
	}

	public void insertCard(ControlPanel Controlpanel, String CityName, String Color) {// 뒤에 카드 추가.
		Card card = new Card(Controlpanel, CityName, Color);
		List.add(card);
		++count;
		// 만약 카드 숫자가 7장 초과면 버리는 이벤트 추가.
		while (count > 7) {
			ChoiceAbandonedCard();
		}
	}

	public void insertCard(ControlPanel Controlpanel, String CityName) {
		Card card = null;
		if (CityName.equals("PeaceNight"))
			card = new PeaceNightCard(Controlpanel, CityName);
		else if(CityName.equals("AntiBodies"))
			card=new AntiBodiesCard(Controlpanel, CityName);
		else if(CityName.equals("Predict"))
			card=new PredictCard(Controlpanel, CityName);
		else if(CityName.equals("EmergencyAir"))
			card=new EmergencyAirCard(Controlpanel,CityName);
		else if(CityName.equals("GrantOfMoney"))
			card=new GrandOfMoneyCard(Controlpanel,CityName);
		List.add(card);
		++count;
		// 만약 카드 숫자가 7장 초과면 버리는 이벤트 추가.
		while (count > 7) {
			ChoiceAbandonedCard();
		}
	}

	public void ChoiceAbandonedCard() {
		String[] texts = new String[List.size()];
		for (int i = 0; i < List.size(); i++) {
			texts[i] = List.get(i).getCityName();
		}

		String AbandonedCard = (String) JOptionPane.showInputDialog(null, "버릴 카드를 선택하세요", "선택",
				JOptionPane.INFORMATION_MESSAGE, null, texts, texts[0]);
		System.out.println(AbandonedCard);
		removeCard(AbandonedCard);
	}

	public void removeCard(String CityName) {
		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getCityName().equals(CityName)) {
				List.remove(i);
				--count;
			}
		}
	}

	public void removeCards(String[] removecards) {
		for (int i = 0; i < removecards.length; i++)
			removeCard(removecards[i]);
	}

	public void DevelopeCure(String Color) {
		int Cure_Source = 0;
		String[] PreditedRemove = new String[5];
		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getColor().equals(Color)) {
				++Cure_Source;
				PreditedRemove[i] = List.get(i).getCityName();
			}
		}
		if (Cure_Source == 5) {
			if (Color.equals("Red")) {
				Game.RedCure = true;
			} else if (Color.equals("Blue")) {
				Game.BlueCure = true;
			} else if (Color.equals("Yellow")) {
				Game.YellowCure = true;
			} else if (Color.equals("Black")) {
				Game.BlackCure = true;
			}
			removeCards(PreditedRemove);// 5장의 카드를 한번에 삭제
		}
	}

	public void printList() {
		for (int i = 0; i < List.size(); i++) {
			System.out.print(List.get(i).getCityName());
		}
		System.out.println();
	}

	public void BuildLabatory(String CityName) {
		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getCityName().equals(CityName)
					&& (Controlpanel.Mainpanel.citys.returnCity(CityName).Labatory == false)) {
				removeCard(CityName);
				Controlpanel.Mainpanel.citys.returnCity(CityName).setLabatory();
				Controlpanel.Mainpanel.repaint();
			}
		}
	}

	public ArrayList<Card> ReArray() {
		return List;
	}

}
