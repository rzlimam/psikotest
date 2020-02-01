package com.lawencon.psikotest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.psikotest.dao.DetailApplicantAnswerDao;
import com.lawencon.psikotest.dao.PackageDetailDao;
import com.lawencon.psikotest.entity.DetailApplicantAnswer;
import com.lawencon.psikotest.entity.POJOStats;
import com.lawencon.psikotest.entity.POJOStats1;
import com.lawencon.psikotest.entity.POJOStats2;
import com.lawencon.psikotest.entity.ReportResult;
import com.lawencon.psikotest.exception.FileStorageException;
import com.lawencon.psikotest.exception.MyFileNotFoundException;

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
	
	@Value("${report-dir}")
	private Path fileStorage;
	
	 public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = fileStorage.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new MyFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new MyFileNotFoundException("File not found " + fileName, ex);
	        }
	    }
	
	
	public String exportReport(String reportFormat, String id) throws FileNotFoundException, JRException {
		
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
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/psikotestResult.html");
			fileName = "psikotestResult.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/psikotestResult.pdf");
			fileName = "psikotestResult.pdf";
		}
		return fileName;
	}
	
	public String correctPerPackage(String reportFormat) throws FileNotFoundException, JRException {
		//create directory
//		Path p = Paths.get(fileStorage.toString());
//		if(!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		List<POJOStats> packages = pdDao.correctPerPackage();
		List<POJOStats> report = new ArrayList<POJOStats>();
		for (POJOStats pack : packages) {
			POJOStats rr = new POJOStats();
			rr.setPackageName(pack.getPackageName());
			rr.setQuestion(pack.getQuestion());
			rr.setCorrect(pack.getCorrect());
			rr.setPercentage(pack.getPercentage());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:correctPerPackage.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/correctPerPackage.html");
			fileName = "correctPerPackage.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/correctPerPackage.pdf");
			fileName = "correctPerPackage.pdf";
		}
		return fileName;
	}
	
	public String falsePerPackage(String reportFormat) throws FileNotFoundException, JRException {
		
		List<POJOStats> packages = pdDao.falsePerPackage();
		List<POJOStats> report = new ArrayList<POJOStats>();
		for (POJOStats pack : packages) {
			POJOStats rr = new POJOStats();
			rr.setPackageName(pack.getPackageName());
			rr.setQuestion(pack.getQuestion());
			rr.setCorrect(pack.getCorrect());
			rr.setPercentage(pack.getPercentage());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:falsePerPackage.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/falsePerPackage.html");
			fileName = "falsePerPackage.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/falsePerPackage.pdf");
			fileName = "falsePerPackage.pdf";
		}
		
		return fileName;
	}
	
	public String mostCorrect(String reportFormat) throws FileNotFoundException, JRException {
//		String path = "E:\\Rizal\\Boothcamp\\psikotest\\";
		List<POJOStats1> packages = pdDao.mostCorrect();
		List<POJOStats1> report = new ArrayList<POJOStats1>();
		for (POJOStats1 pack : packages) {
			POJOStats1 rr = new POJOStats1();
			rr.setQuestion(pack.getQuestion());
			rr.setCorrect(pack.getCorrect());
			rr.setPercentage(pack.getPercentage());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:easiestQuestion.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/easiestQuestion.html");
			fileName = "easiestQuestion.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/easiestQuestion.pdf");
			fileName = "easiestQuestion.pdf";
		}
		return fileName;
	}
	
	public String mostFalse(String reportFormat) throws FileNotFoundException, JRException {
//		String path = "E:\\Rizal\\Boothcamp\\psikotest\\";
		List<POJOStats1> packages = pdDao.mostFalse();
		List<POJOStats1> report = new ArrayList<POJOStats1>();
		for (POJOStats1 pack : packages) {
			POJOStats1 rr = new POJOStats1();
			rr.setQuestion(pack.getQuestion());
			rr.setCorrect(pack.getCorrect());
			rr.setPercentage(pack.getPercentage());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:easiestQuestion.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/hardestQuestion.html");
			fileName = "hardestQuestion.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/hardestQuestion.pdf");
			fileName = "hardestQuestion.pdf";
		}
		return fileName;
	}
	
	public String easiestPackage(String reportFormat) throws FileNotFoundException, JRException {
//		String path = "E:\\Rizal\\Boothcamp\\psikotest\\";
		List<POJOStats2> packages = pdDao.easiestPackage();
		List<POJOStats2> report = new ArrayList<POJOStats2>();
		for (POJOStats2 pack : packages) {
			POJOStats2 rr = new POJOStats2();
			rr.setPackageName(pack.getPackageName());
			rr.setCorrect(pack.getCorrect());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:easiestPackage.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/easiestPackage.html");
			fileName = "easiestPackage.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/easiestPackage.pdf");
			fileName = "easiestPackage.pdf";
		}
		return fileName;
	}
	
	public String hardestPackage(String reportFormat) throws FileNotFoundException, JRException {
//		String path = "E:\\Rizal\\Boothcamp\\psikotest\\";
		List<POJOStats2> packages = pdDao.hardestPackage();
		List<POJOStats2> report = new ArrayList<POJOStats2>();
		for (POJOStats2 pack : packages) {
			POJOStats2 rr = new POJOStats2();
			rr.setPackageName(pack.getPackageName());
			rr.setCorrect(pack.getCorrect());
			report.add(rr);
		}
		//load file and compile it
		File file = ResourceUtils.getFile("classpath:hardestPackage.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString()+"/hardestPackage.html");
			fileName = "hardestPackage.html";
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString()+"/hardestPackage.pdf");
			fileName = "hardestPackage.pdf";
		}
		return fileName;
	}

}
