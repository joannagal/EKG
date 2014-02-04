package pi.gui.information.specimen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class InformationSpecimenController implements ActionListener {

	private InformationSpecimenView view;
	private Date date;
	private boolean hiv;
	private String methadoneTreatment;
	private String weight;
	private String methadone;
	private String birth;
	String regex = "[0-9]*";
	private String errorText = "";
	private boolean error = false;

	public InformationSpecimenController(InformationSpecimenView view) {
		this.setView(view);

	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		okStatement: if (action.equals("OK")) {

			birth = this.view.getBirthField().getText();
			setMethadone(this.view.getMethadoneField().getText());
			setMethadoneTreatment(this.view.getMethadoneTreatmentField()
					.getText());
			setHiv(this.view.getHivCheckBox().isSelected());
			setWeight(this.view.getWeightField().getText());

			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			try {
				setDate(dateFormat.parse(birth));
				this.view.getSpecimen().setBirth(birth);
			} catch (ParseException e1) {
				this.view.getSpecimen().setBirth(null);
			}

			if (methadone.matches(regex)) {
				this.view.getSpecimen().setMethadone(
						Integer.parseInt(methadone));
			} else {
				error = true;
				setErrorText(getErrorText()
						+ "Amount of methadone must be an integer");
			}

			if (methadoneTreatment.matches(regex)) {
				this.view.getSpecimen().setMetadonTimeApplication(
						Integer.parseInt(methadoneTreatment));
			} else {
				error = true;
				setErrorText(getErrorText()
						+ " \nAmount of months of treatment methadone must be an integer");
			}

			if (weight.matches(regex) == true) {
				this.view.getSpecimen().setWeight(Integer.parseInt(weight));
			} else {
				error = true;
				setErrorText(getErrorText() + "\nWeight must be an integer");

			}

			if (error == true) {
				JOptionPane.showMessageDialog(null, getErrorText());
				error = false;
				break okStatement;
			}
			
			this.view.dispose();

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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}