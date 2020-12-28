package services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DocgiaModel;
import model.MuontraBean;
import model.MuontraModel;
import model.SachModel;
import model.ThuthuModel;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.MainQLTV;

public class ThaotacFile {	
	/*********************
	 * GHI FILE 
	 *********************/
	public static void ghiFile(String sourceFileName, ObservableList data) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
              //1. Luu path
              String pathToNewFile = file.toString();

              //2.1. Copy file to path
              makeCopy(sourceFileName, pathToNewFile);
              
              //2.2 Sua file da copy
              File filewb = new File(pathToNewFile);
              FileInputStream is = new FileInputStream(filewb);
              XSSFWorkbook wb = new XSSFWorkbook(is);
              XSSFSheet sheet = wb.getSheetAt(0);
              
              switch(sourceFileName) {
              	case "bangsach": {
              		//3. In thong tin ra fileCopy
                    for(int i=0; i<data.size(); i++) {
                  	  XSSFRow row = sheet.createRow(i+1);
                  	  SachModel dataforcell = (SachModel) data.get(i);
                  	  printSach(row, dataforcell);            	  
                    }
                    //4. In thong tin xuat, ngay gio xuat
                    printInfo(sheet, 8);
                    break;
              	}
              	case "bangMT": {
              		//3. In thong tin ra fileCopy
                    for(int i=0; i<data.size(); i++) {
                  	  XSSFRow row = sheet.createRow(i+1);
                  	  MuontraBean dataforcell = (MuontraBean) data.get(i);
                  	  printMT(row, dataforcell);            	  
                    }
                    //4. In thong tin xuat, ngay gio xuat
                    printInfo(sheet, 8);
                    break;
              	}
              	case "bangDG": {
              		//3. In thong tin ra fileCopy
                    for(int i=0; i<data.size(); i++) {
                  	  XSSFRow row = sheet.createRow(i+1);
                  	  DocgiaModel dataforcell = (DocgiaModel) data.get(i);
                  	  printDG(row, dataforcell);            	  
                    }
                    //4. In thong tin xuat, ngay gio xuat
                    printInfo(sheet, 8);
                    break;
              	}
              	case "bangTT": {
              		//3. In thong tin ra fileCopy
                    for(int i=0; i<data.size(); i++) {
                  	  XSSFRow row = sheet.createRow(i+1);
                  	  ThuthuModel dataforcell = (ThuthuModel) data.get(i);
                  	  printTT(row, dataforcell);            	  
                    }
                    //4. In thong tin xuat, ngay gio xuat
                    printInfo(sheet, 7);
                    break;
              	}
              }
              
              //5. Ghi va dong file
              is.close();
              FileOutputStream os = new FileOutputStream(filewb);
              wb.write(os);
              wb.close();
              os.close();
              
              //6. Thong bao thanh cong
              UtilService.success("Xuất ra file đã xong");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	
	
	/*********************
	 * DOC FILE 
	 *********************/
	public static void docFile(String chidinh, String pathToFile) {
		try {
			   //Chuỗi dùng để lưu lại các dòng bị lỗi
			   String error = "" ;
			   
			   // Đọc file, lấy ra wb và sheet
		       FileInputStream is = new FileInputStream(new File(pathToFile));
		       XSSFWorkbook wb = new XSSFWorkbook(is);
		       XSSFSheet sheet = wb.getSheetAt(0);
		       XSSFRow row;
		       int id=1;
		       
		       while (sheet.getRow(id).getCell(0) != null) {
		    	   row = sheet.getRow(id);
		           createSach(row);		           
		           id++;
		       }
		       
//		       UtilService.success();
//		       wb.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}   
	
	
	
	/********************
	 * BONUS METHOD
	 ********************/
	
	//Bonus 1. In thong tin chinh (for ghiFile):
	//Bonus 1a. Sach
	public static void printSach(XSSFRow row, SachModel dataforcell) {
	  XSSFCell cell = row.createCell(0, CellType.STRING);
  	  cell.setCellValue(dataforcell.getMasach_20183955());
  	  
  	  cell = row.createCell(1, CellType.STRING);
  	  cell.setCellValue(dataforcell.getTensach_20183955());
  	  
  	  cell = row.createCell(2, CellType.STRING);
  	  cell.setCellValue(dataforcell.getTacgia_20183955());
  	  
  	  cell = row.createCell(3, CellType.STRING);
  	  cell.setCellValue(dataforcell.getNhaXB_20183955());
  	  
  	  cell = row.createCell(4, CellType.NUMERIC);
  	  cell.setCellValue(dataforcell.getNamXB_20183955());
  	  
  	  cell = row.createCell(5, CellType.NUMERIC);
  	  cell.setCellValue(dataforcell.getDongia_20183955());
  	  
  	  cell = row.createCell(6, CellType.STRING);
  	  cell.setCellValue(dataforcell.getTrangthaisach_20183955());
  	  
  	  cell = row.createCell(7, CellType.STRING);
  	  cell.setCellValue(dataforcell.getGioithieu_20183955());
	}
	//Bonus 1b. MT
	public static void printMT(XSSFRow row, MuontraBean dataforcell) {
		  MuontraModel model = dataforcell.getMtModel();  
		  
		  XSSFCell cell = row.createCell(0, CellType.STRING);
    	  cell.setCellValue(model.getMaMT_20183955());
    	  
    	  cell = row.createCell(1, CellType.STRING);
    	  cell.setCellValue(model.getMaDG_20183955());
    	  
    	  cell = row.createCell(2, CellType.STRING);
    	  cell.setCellValue(dataforcell.getTenDG_20183955());
    	  
    	  cell = row.createCell(3, CellType.STRING);
    	  cell.setCellValue(model.getMaTT_20183955());
    	  
    	  cell = row.createCell(4,CellType.STRING);
    	  cell.setCellValue(dataforcell.getTenTT_20183955());
    	  
    	  cell = row.createCell(5, CellType.STRING);
    	  cell.setCellValue(model.getNgaymuon_20183955().toString());
    	  
    	  cell = row.createCell(6, CellType.STRING);
    	  cell.setCellValue(model.getNgayhentra_20183955().toString());
    	  
    	  cell = row.createCell(7, CellType.NUMERIC);
    	  cell.setCellValue(model.getTiencoc_20183955());
	}
	//Bonus 1c. DG
	public static void printDG(XSSFRow row, DocgiaModel dataforcell) {
		  XSSFCell cell = row.createCell(0, CellType.STRING);
    	  cell.setCellValue(dataforcell.getMaDG_20183955());
    	  
    	  cell = row.createCell(1, CellType.STRING);
    	  cell.setCellValue(dataforcell.getTenDG_20183955());
    	  
    	  cell = row.createCell(2, CellType.STRING);
    	  cell.setCellValue(dataforcell.getGioitinh_20183955());
    	  
    	  cell = row.createCell(3, CellType.STRING);
    	  cell.setCellValue(dataforcell.getDiachi_20183955());
    	  
    	  cell = row.createCell(4, CellType.NUMERIC);
    	  cell.setCellValue(dataforcell.getNamsinh_20183955());
    	  
    	  cell = row.createCell(5, CellType.STRING);
    	  cell.setCellValue(dataforcell.getCMND_20183955());
    	  
    	  cell = row.createCell(6, CellType.STRING);
    	  cell.setCellValue(dataforcell.getEmail_20183955());
    	  
    	  cell = row.createCell(7, CellType.STRING);
    	  cell.setCellValue(dataforcell.getDthoai_20183955());
	}
	//Bonus 1d. TT
	public static void printTT(XSSFRow row, ThuthuModel dataforcell) {
		  XSSFCell cell = row.createCell(0, CellType.STRING);
    	  cell.setCellValue(dataforcell.getMaTT_20183955());
    	  
    	  cell = row.createCell(1, CellType.STRING);
    	  cell.setCellValue(dataforcell.getTen_20183955());
    	  
    	  cell = row.createCell(2, CellType.STRING);
    	  cell.setCellValue(dataforcell.getGioitinh_20183955());
    	  
    	  cell = row.createCell(3, CellType.NUMERIC);
    	  cell.setCellValue(dataforcell.getNamsinh_20183955());
    	  
    	  cell = row.createCell(4, CellType.STRING);
    	  cell.setCellValue(dataforcell.getCMND_20183955());
    	  
    	  cell = row.createCell(5, CellType.STRING);
    	  cell.setCellValue(dataforcell.getEmail_20183955());
    	  
    	  cell = row.createCell(6, CellType.STRING);
    	  cell.setCellValue(dataforcell.getDthoai_20183955());
	}
	
	//Bonus 2. In thong tin xuat: nguoi xuat, ngay gio
	public static void printInfo(XSSFSheet sheet, int pos) {
		XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.createCell(pos);
        cell.setCellValue("Mã thủ thư: " + MainQLTV.maTT);
        
        row = sheet.getRow(2);
        cell = row.createCell(pos);
        cell.setCellValue("Tên thủ thư: " + MainQLTV.tenTT);
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        row = sheet.getRow(3);
        cell = row.createCell(pos);
        cell.setCellValue("Ngày giờ tạo: " + formatter.format(date));
	}
	
	//Bonus 3. Copy 1 file ra duong dan khac
	public static void makeCopy(String sourceFileName, String pathToNewFile) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir") + "/res/" + sourceFileName + ".xlsx"));
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        FileOutputStream outputStream = new FileOutputStream(pathToNewFile);
	       workbook.write(outputStream);
	        outputStream.close();
	        workbook.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Bonus 4. Them doi tuong tu file (for docFile)
	//4a. Sach
	public static void createSach(XSSFRow row) {
		SachModel model = new SachModel();
		
		Cell cell = row.getCell(0);
		model.setMasach_20183955(cell.getStringCellValue());
		
		cell = row.getCell(1);
		model.setTensach_20183955(cell.getStringCellValue());
		
		cell = row.getCell(2);
		model.setTacgia_20183955(cell.getStringCellValue());
		
		cell = row.getCell(3);
		model.setNhaXB_20183955(cell.getStringCellValue());
		
		cell = row.getCell(4); 
		model.setNamXB_20183955(String.valueOf((int)cell.getNumericCellValue()));
		
		cell = row.getCell(5);
		model.setDongia_20183955(String.valueOf((int)cell.getNumericCellValue()));
		
		cell = row.getCell(6);
		model.setTrangthaisach_20183955(cell.getStringCellValue());
		
		if(row.getCell(7)==null) { model.setGioithieu_20183955(null); }
		else { model.setGioithieu_20183955(cell.getStringCellValue()); }
	}
	
	//4a. MT
	
	//4a. DG
	
	//4a. TT
}
