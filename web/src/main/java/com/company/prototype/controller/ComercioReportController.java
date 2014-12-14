package com.company.prototype.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.service.ComercioFacade;
import com.company.prototype.service.TransaccionFacade;
import com.company.prototype.util.ApplicationConfiguration.ExportTypeFormat;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * Un ejemplo simple del uso de JasperReport
 * @author jsanchez
 */
//@Named("comercioReportController")
@ManagedBean
@SessionScoped
public class ComercioReportController {
	
		
	@EJB 
	private ComercioFacade comercioFacade;
	
	@EJB 
	private TransaccionFacade transaccionFacade;
    private List<Transaccion> transactionsList;
    private String date= "";
    
    private String entityId="";
    private String merchandId="";
    private String formatType=ExportTypeFormat.PDF.getValue();
   
  
    
    
    public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getMerchandId() {
		return merchandId;
	}

	public void setMerchandId(String merchandId) {
		this.merchandId = merchandId;
	}
	

    /*public List<Transaccion> getListOfUser() {
    	transactionsList=transaccionFacade.findAll();
        return transactionsList;
    }*/
    /*
    public void setListOfUser(List<User> listOfUser) {
        this.transactionsList = listOfUser;
    }*/
    private JasperPrint jasperPrint;
    
    
    public String generateReport(){
    	try{
    		
        	Comercio merchand=comercioFacade.findByIdentification(merchandId);
        	if(merchand==null){
        		return "error";
        	}
        	init(merchand);
    		if(ExportTypeFormat.PDF.getValue().equals(formatType)){
        		generatePDF();
        	}else if(ExportTypeFormat.ODT.getValue().equals(formatType)){
        		generateODT();
        	}else if(ExportTypeFormat.XLSX.getValue().equals(formatType)){
        		generateXLSX();
        	}else if(ExportTypeFormat.DOCX.getValue().equals(formatType)){
        		generateDOCX();
        	}else if(ExportTypeFormat.PPT.getValue().equals(formatType)){
        		generatePPT();
        	}else{
        		return "error";
        	}    		
    		 FacesContext facesContext = FacesContext.getCurrentInstance();
    		HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        	ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        	facesContext.responseComplete();
        	servletOutputStream.flush();
            servletOutputStream.close();
    	}catch(Exception e){
    		e.printStackTrace();
    		return "error";
    	}    	
    	
    	return "response";
    	
    }    
    
    
    private void init(Comercio merchand) throws JRException{
    	
    	Map<String,Object> paramters=new HashMap<String,Object>();
        paramters.put("merchandId", merchand.getIdentificacion());
        paramters.put("merchandName", merchand.getNombre());
    	    	
    	transactionsList=transaccionFacade.findByComercio(merchand);
    	date= new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(transactionsList);
        jasperPrint=JasperFillManager.fillReport(this.getClass().getResource("/jasperreports/TransaccionesComercios.jasper").getPath(), 
        											paramters,beanCollectionDataSource);       
    }
    
   private void generatePDF() throws JRException, IOException{
	   
      
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=TC"+date+".pdf");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
       
       
   }
   private void generateDOCX() throws JRException, IOException{
    	
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=TC"+date+".docx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRDocxExporter docxExporter=new JRDocxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
   private void generateXLSX() throws JRException, IOException{

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=TC"+date+".xlsx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRXlsxExporter docxExporter=new JRXlsxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
   private void generateODT() throws JRException, IOException{

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=TC"+date+".odt");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JROdtExporter docxExporter=new JROdtExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
   private void generatePPT() throws JRException, IOException{

       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=TC"+date+".pptx");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JRPptxExporter docxExporter=new JRPptxExporter();
       docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
       docxExporter.exportReport();
   }
    
}
