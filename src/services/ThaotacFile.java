package services;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import model.Model;
import model.MuontraBean;
import model.MuontraModel;
import model.SachModel;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.MainQLTV;

public class ThaotacFile {	
	public static void ghiFileSach(String sourceFileName, ObservableList<Model> data) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
             //1. Luu path
              String pathToNewFile = file.toString();

              //2. Copy file to path
              makeCopy(sourceFileName, pathToNewFile);
              
              //3. In thong tin ra fileCopy
              File filewb = new File(pathToNewFile);
              FileInputStream is = new FileInputStream(filewb);
              XSSFWorkbook wb = new XSSFWorkbook(is);
              XSSFSheet sheet = wb.getSheetAt(0);
              XSSFRow row; XSSFCell cell;
              
              for(int i=0; i<data.size(); i++) {
            	  row = sheet.createRow(i+1);
            	  SachModel dataforcell = (SachModel) data.get(i);
            	  
            	  cell = row.createCell(0);
            	  cell.setCellValue(dataforcell.getMasach_20183955());
            	  
            	  cell = row.createCell(1);
            	  cell.setCellValue(dataforcell.getTensach_20183955());
            	  
            	  cell = row.createCell(2);
            	  cell.setCellValue(dataforcell.getTacgia_20183955());
            	  
            	  cell = row.createCell(3);
            	  cell.setCellValue(dataforcell.getNhaXB_20183955());
            	  
            	  cell = row.createCell(4, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getNamXB_20183955());
            	  
            	  cell = row.createCell(5, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getDongia_20183955());
            	  
            	  cell = row.createCell(6);
            	  cell.setCellValue(dataforcell.getTrangthaisach_20183955());
            	  
            	  cell = row.createCell(7);
            	  cell.setCellValue(dataforcell.getGioithieu_20183955());
              }
              
              //4. In thong tin xuat, ngay gio xuat
              row = sheet.getRow(1);
              cell = row.createCell(8);
              cell.setCellValue("Mã thủ thư: " + MainQLTV.maTT);
              
              row = sheet.getRow(2);
              cell = row.createCell(8);
              cell.setCellValue("Tên thủ thư: " + MainQLTV.tenTT);
              
              SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
              Date date = new Date(System.currentTimeMillis());
              row = sheet.getRow(3);
              cell = row.createCell(8);
              cell.setCellValue("Ngày giờ tạo: " + formatter.format(date));
              
              //5. Ghi va dong file
              is.close();
              FileOutputStream os = new FileOutputStream(filewb);
              wb.write(os);
              os.close();
              
              //6. Thong bao thanh cong
              UtilService.success("Xuất ra file đã xong");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	public static void docFile() {
		try {
			//1. Copy file cu thanh file moi va luu luon tai cho
			
			//2. Mo file copy do ra
			
			//3. Doc du lieu va add vao table+CSDL
			
			//4. Xoa file copy di
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Copy ra duong dan khac
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
	
	
	public static void ghiFileMT(String sourceFileName, ObservableList<MuontraBean> data) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
             //1. Luu path
              String pathToNewFile = file.toString();

              //2. Copy file to path
              makeCopy(sourceFileName, pathToNewFile);
              
              //3. In thong tin ra fileCopy
              File filewb = new File(pathToNewFile);
              FileInputStream is = new FileInputStream(filewb);
              XSSFWorkbook wb = new XSSFWorkbook(is);
              XSSFSheet sheet = wb.getSheetAt(0);
              
              for(int i=0; i<data.size(); i++) {
            	  XSSFRow row = sheet.createRow(i+1);
            	  MuontraBean dataforcell = data.get(i);
            	  MuontraModel model = dataforcell.getMtModel();
            	  
            	  XSSFCell cell = row.createCell(0);
            	  cell.setCellValue(model.getMaMT_20183955());
            	  
            	  cell = row.createCell(1);
            	  cell.setCellValue(model.get
            	  
            	  cell = row.createCell(2);
            	  cell.setCellValue(dataforcell.getTacgia_20183955());
            	  
            	  cell = row.createCell(3);
            	  cell.setCellValue(dataforcell.getNhaXB_20183955());
            	  
            	  cell = row.createCell(4, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getNamXB_20183955());
            	  
            	  cell = row.createCell(5, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getDongia_20183955());
            	  
            	  cell = row.createCell(6);
            	  cell.setCellValue(dataforcell.getTrangthaisach_20183955());
            	  
            	  cell = row.createCell(7);
            	  cell.setCellValue(dataforcell.getGioithieu_20183955());
              }
              
              //4. In thong tin xuat, ngay gio xuat
              
              
              //5. Ghi va dong file
              is.close();
              FileOutputStream os = new FileOutputStream(filewb);
              wb.write(os);
              os.close();
              
              //6. Thong bao thanh cong
              UtilService.success("Xuất ra file đã xong");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	public static void ghiFileDG(String sourceFileName, ObservableList<Model> data) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
             //1. Luu path
              String pathToNewFile = file.toString();

              //2. Copy file to path
              makeCopy(sourceFileName, pathToNewFile);
              
              //3. In thong tin ra fileCopy
              File filewb = new File(pathToNewFile);
              FileInputStream is = new FileInputStream(filewb);
              XSSFWorkbook wb = new XSSFWorkbook(is);
              XSSFSheet sheet = wb.getSheetAt(0);
              
              for(int i=0; i<data.size(); i++) {
            	  XSSFRow row = sheet.createRow(i+1);
            	  SachModel dataforcell = (SachModel) data.get(i);
            	  
            	  XSSFCell cell = row.createCell(0);
            	  cell.setCellValue(dataforcell.getMasach_20183955());
            	  
            	  cell = row.createCell(1);
            	  cell.setCellValue(dataforcell.getTensach_20183955());
            	  
            	  cell = row.createCell(2);
            	  cell.setCellValue(dataforcell.getTacgia_20183955());
            	  
            	  cell = row.createCell(3);
            	  cell.setCellValue(dataforcell.getNhaXB_20183955());
            	  
            	  cell = row.createCell(4, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getNamXB_20183955());
            	  
            	  cell = row.createCell(5, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getDongia_20183955());
            	  
            	  cell = row.createCell(6);
            	  cell.setCellValue(dataforcell.getTrangthaisach_20183955());
            	  
            	  cell = row.createCell(7);
            	  cell.setCellValue(dataforcell.getGioithieu_20183955());
              }
              
              //4. In thong tin xuat, ngay gio xuat
              
              
              //5. Ghi va dong file
              is.close();
              FileOutputStream os = new FileOutputStream(filewb);
              wb.write(os);
              os.close();
              
              //6. Thong bao thanh cong
              UtilService.success("Xuất ra file đã xong");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	public static void ghiFileTT(String sourceFileName, ObservableList<Model> data) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            try {
             //1. Luu path
              String pathToNewFile = file.toString();

              //2. Copy file to path
              makeCopy(sourceFileName, pathToNewFile);
              
              //3. In thong tin ra fileCopy
              File filewb = new File(pathToNewFile);
              FileInputStream is = new FileInputStream(filewb);
              XSSFWorkbook wb = new XSSFWorkbook(is);
              XSSFSheet sheet = wb.getSheetAt(0);
              
              for(int i=0; i<data.size(); i++) {
            	  XSSFRow row = sheet.createRow(i+1);
            	  SachModel dataforcell = (SachModel) data.get(i);
            	  
            	  XSSFCell cell = row.createCell(0);
            	  cell.setCellValue(dataforcell.getMasach_20183955());
            	  
            	  cell = row.createCell(1);
            	  cell.setCellValue(dataforcell.getTensach_20183955());
            	  
            	  cell = row.createCell(2);
            	  cell.setCellValue(dataforcell.getTacgia_20183955());
            	  
            	  cell = row.createCell(3);
            	  cell.setCellValue(dataforcell.getNhaXB_20183955());
            	  
            	  cell = row.createCell(4, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getNamXB_20183955());
            	  
            	  cell = row.createCell(5, CellType.NUMERIC);
            	  cell.setCellValue(dataforcell.getDongia_20183955());
            	  
            	  cell = row.createCell(6);
            	  cell.setCellValue(dataforcell.getTrangthaisach_20183955());
            	  
            	  cell = row.createCell(7);
            	  cell.setCellValue(dataforcell.getGioithieu_20183955());
              }
              
              //4. In thong tin xuat, ngay gio xuat
              
              
              //5. Ghi va dong file
              is.close();
              FileOutputStream os = new FileOutputStream(filewb);
              wb.write(os);
              os.close();
              
              //6. Thong bao thanh cong
              UtilService.success("Xuất ra file đã xong");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
}
