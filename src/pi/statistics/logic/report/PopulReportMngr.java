package pi.statistics.logic.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class PopulReportMngr {

	JasperReport jasperReport = null;
	JasperPrint jasperPrint = null;
	JasperDesign jasperDesign = null;
	JRBeanCollectionDataSource dataSource = null;

	public PopulReportMngr() throws JRException {
		initReport();
	}

	public void refreshReport() throws JRException {
		initReport();
	}

	@SuppressWarnings("unchecked")
	private void initReport() throws JRException {
		long start = System.currentTimeMillis();
		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();

		dataSource = new JRBeanCollectionDataSource(
				PopulStatistic.getPopulStatistics());

		File file = new File("report4.jasper");
		jasperReport = (JasperReport) JRLoader.loadObject(file);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				dataSource);

		long time = System.currentTimeMillis() - start;

	}

	public void viewRaport() throws JRException {
		JasperViewer.viewReport(jasperPrint, false);
	}

	public void saveRaportAsPdf(String path) throws JRException {
		if (path == null || path == "") {
			path = "newReport.pdf";
		} else if (!path.endsWith(".pdf")) {
			path += ".pdf";
		}
		JasperExportManager.exportReportToPdfFile(jasperPrint, path);
	}

	@SuppressWarnings("unchecked")
	public void saveReportAsHtml(String path) throws JRException {
		if (path == null || path == "") {
			path = "newReport.html";
		} else if (!path.endsWith(".html")) {
			path += ".html";
		}
		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
		JasperPrint htmlReport = JasperFillManager.fillReport(jasperReport,
				parameters, dataSource.cloneDataSource());
		JasperExportManager.exportReportToHtmlFile(htmlReport, path);
	}

	public static void main(String[] args) {

		try {
			PopulReportMngr rm = new PopulReportMngr();
			rm.viewRaport();

		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
