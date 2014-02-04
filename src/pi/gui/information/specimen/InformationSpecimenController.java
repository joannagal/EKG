package pi.gui.information.specimen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InformationSpecimenController implements ActionListener {

	private InformationSpecimenView view;
	private Date date;
	private boolean hiv;
	private String methadoneTreatment;
	private String methadone;
	private String birth;

	public InformationSpecimenController(InformationSpecimenView view) {
		this.setView(view);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("OK")) {

			birth = this.view.getBirthField().getText();
			setMethadone(this.view.getMethadoneField().getText());
			setMethadoneTreatment(this.view.getMethadoneTreatmentField()
					.getText());
			setHiv(this.view.getHivCheckBox().isSelected());

			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			try {
				setDate(dateFormat.parse(birth));
				this.view.getSpecimen().setBirth(birth);
			} catch (ParseException e1) {

				this.view.getSpecimen().setBirth(null);
			}
			
			// Pattern pattern = Pattern.compile("[^1-9][0-9]*");
			 

		}
		if (action.equals("CANCEL")) {
			this.view.dispose();
		}
	}

	public InformationSpecimenView getView() {
		return view;
	}

	public void setView(InformationSpecimenView view) {
		this.view = view;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isHiv() {
		return hiv;
	}

	public void setHiv(boolean hiv) {
		this.hiv = hiv;
	}

	public String getMethadoneTreatment() {
		return methadoneTreatment;
	}

	public void setMethadoneTreatment(String methadoneTreatment) {
		this.methadoneTreatment = methadoneTreatment;
	}

	public String getMethadone() {
		return methadone;
	}

	public void setMethadone(String methadone) {
		this.methadone = methadone;
	}

}