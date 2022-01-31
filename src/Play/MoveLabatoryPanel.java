package Play;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MoveLabatoryPanel extends ControlShape {
	ControlPanel Controlpanel;
	ImageIcon button = new ImageIcon(AirplanePanel.class.getResource("../Image/button.png"));

	public MoveLabatoryPanel(ControlPanel Controlpanel) {
		this.Controlpanel = Controlpanel;
		String[] text = Controlpanel.Mainpanel.citys.returntext();
		String CurrentyCityText = Controlpanel.Mainpanel.character.getCurrentposition();
		if (Controlpanel.Mainpanel.citys.returnCity(CurrentyCityText).getLabatory()) {
			for (int i = 1; i < 49; i++) {
				if (Controlpanel.Mainpanel.citys.returnCity(text[i]).getLabatory()) {
					JLabel t=new JLabel("", button, JLabel.CENTER);
					t.setText(text[i]);
					t.setVerticalTextPosition(JLabel.CENTER);
					t.setHorizontalTextPosition(JLabel.CENTER);
					t.addMouseListener(new MoveLabatoryLabel());
					add(t);
				}
			}
		}
		
		JLabel Back = new JLabel("Back", button, JLabel.CENTER);
		Back.setVerticalTextPosition(JLabel.CENTER);
		Back.setHorizontalTextPosition(JLabel.CENTER);
		Back.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Controlpanel.invalidate();
				Controlpanel.removeAll();
				Controlpanel.add(new BasicSelect(Controlpanel));
				Controlpanel.revalidate();
				Controlpanel.repaint();
				Controlpanel.Mainpanel.Controlpanel.setBounds(0, 840, 1920, 240);
			}
		});
		add(Back);
	}
	
	class MoveLabatoryLabel extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			String Choicecity = label.getText();
			Point ChoicePoint = Controlpanel.Mainpanel.citys.CityPosition(Choicecity);
			Controlpanel.Mainpanel.character.setXY(ChoicePoint.x, ChoicePoint.y);
			Controlpanel.Mainpanel.character.setCC(Choicecity, Controlpanel.Mainpanel.citys.returnCity(Choicecity).getColor());
			
			Controlpanel.invalidate();
			Controlpanel.removeAll();
			Controlpanel.add(new BasicSelect(Controlpanel));
			Controlpanel.revalidate();
			Controlpanel.repaint();
			Controlpanel.Mainpanel.repaint();
		}
	}
}
