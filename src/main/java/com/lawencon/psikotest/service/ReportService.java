package com.lawencon.psikotest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.psikotest.dao.DetailApplicantAnswerDao;
import com.lawencon.psikotest.dao.PackageDetailDao;
import com.lawencon.psikotest.entity.DetailApplicantAnswer;
import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.ReportResult;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired
	private DetailApplicantAnswerDao daaDao;
	
	@Autowired
	private PackageDetailDao pdDao;
	
	
	public String exportReport(String reportFormat, String id) throws FileNotFoundException, JRException {
		String path = "E:\\Rizal\\Boothcamp\\psikotest\\";
		List<DetailApplicantAnswer> daa = daaDao.findByHAA(id);
		List<ReportResult> report = new ArrayList<ReportResult>();
		for (DetailApplicantAnswer d : daa) {
			ReportResult rr = new ReportResult();
			rr.setQuestionTitle(d.getPackageQuestion().getQuestion().getQuestionTitle());;
			rr.setQuestionType(d.getPackageQuestion().getQuestion().getQuestionType().getQuestionTypeTitle());
			rr.setPoint(d.getPoint());
			rr.setTotalPoint(d.getHeaderApplicantAnswer().getTotalPoint());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:hasil-psikotest.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\psikotestResult.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\psikotestResult.pdf");
		}
		return "report generated";
	}
	
	public List<POJOStats> questionPerPackage(){
		List<POJOStats> cs = pdDao.questionPerPackage();
		return cs;
	}

}
