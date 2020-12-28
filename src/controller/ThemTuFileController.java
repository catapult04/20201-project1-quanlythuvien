package controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ThaotacFile;
import services.UtilService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ThemTuFileController implements Initializable{
	@FXML private Button copyBtn;
	@FXML private Button uploadBtn ;
	@FXML private ComboBox<String> comboBox;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildComboBox();
	}
	
	public void buildComboBox() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("bangsach", "bangMT_add", "bangDG");
		comboBox.setItems(list);
	}
	
	public void onCopyBtn() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu bảng dữ liệu thành file xlxs");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            String pathToNewFile = file.toString();
            if(comboBox.getValue() != null) {
            	ThaotacFile.makeCopy(comboBox.getValue(), pathToNewFile);
                UtilService.success("Copy xong");
            } else {
            	UtilService.fail("Chưa chọn loại file trong combo box");
            }
        } else {
        	UtilService.fail("Chưa chọn đường dẫn mới");
        }
	}
	
	public void onUploadBtn() {
		if(comboBox.getValue() != null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX file (*.xlsx)", "*.xlsx"));
			Stage stage = new Stage();
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {
				String pathToFile = file.toString();
            	ThaotacFile.docFile(comboBox.getValue(), pathToFile);
                UtilService.success();
            } else {
            	UtilService.fail("Chưa chọn đường dẫn");
            } 
		} else {
            	UtilService.fail("Hãy chọn loại file nguồn ở Bước 1 trước khi tiếp tục!");
		}
	}

}
