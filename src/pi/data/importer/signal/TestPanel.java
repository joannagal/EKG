package pi.data.importer.signal;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TestPanel extends JPanel {
	
	public TestPanel(){
	this.setBorder((Border) new TitledBorder(new EtchedBorder(),
			"Display Area"));

	// create the middle panel components
	JTextArea display = new JTextArea(1, 25);
	display.setEditable(false); // set textArea non-editable
	JScrollPane scroll = new JScrollPane(display);
	display.setText("dkjlkd dckdjsckldc slkdjlkdscjds dkjldsjflkds dkjfdslkfjdlf sdsdsa dsafdsaf");
	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

	// Add Textarea in to middle panel
	this.add(scroll);
	}
}
