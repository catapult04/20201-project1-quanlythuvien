package services;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThaotacFile {
	public static String pathToNewFile;
	
	public static void ghiFile(String sourceFile) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
             //1. Luu path
              pathToNewFile = file.toString();

              //2. Copy file to path
              makeCopy(sourceFile);
              
              //3. Sua thong tin fileCopy
              
              
              //4. Thong bao thanh cong
              Alert a = new Alert(AlertType.INFORMATION);
              a.setHeaderText("Xuất ra file thành công");
              a.showAndWait();
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
	public static void makeCopy(String sourceFile) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(System.getProperty("user.dir") + "/res/" + sourceFile + ".xlsx"));
	        Workbook workbook = new XSSFWorkbook(excelFile);
	        FileOutputStream outputStream = new FileOutputStream(pathToNewFile);
	        workbook.write(outputStream);
	        workbook.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
