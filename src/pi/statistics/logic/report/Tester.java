package pi.statistics.logic.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO REMOVE

		//TODO Include missing JARs:
		//http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.5.0/
		//http://stackoverflow.com/questions/12178615/eclipse-jasper-report-not-compiling-java-lang-noclassdeffounderror-org-apach
		try {
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
			JasperDesign jasperDesign = null;
			Map parameters = new HashMap();
			File file = new File("report3.jrxml");
			jasperDesign = JRXmlLoader.load(file);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(
					jasperReport,
					parameters,
					new JRBeanCollectionDataSource(ChannelStatistic
							.getChannelStatistics()));
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"FirstSpecimenReport.pdf");
			JasperViewer.viewReport(jasperPrint);
		} catch (Exception ex) {
			System.out.println("EXCEPTION: " + ex);
		}
	}

}
