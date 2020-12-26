package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MuontraBean;
import model.MuontraModel;

public class MuontraBeanService {
	public ObservableList<MuontraBean> getAll(){
		ObservableList<MuontraBean> mtBeanList = FXCollections.observableArrayList();
		try {
			MuontraModelService mtModelService = new MuontraModelService();
			DocgiaModelService dgModelService = new DocgiaModelService();
			ThuthuModelService ttModelService = new ThuthuModelService();
			
			ObservableList<MuontraModel> mtModelList = mtModelService.getAll();
			for(int i=0; i< mtModelList.size(); i++) {
				MuontraModel mtModel = mtModelList.get(i);
				String tenDG = dgModelService.getNameById(mtModel.getMaDG_29183955());
				String tenTT = ttModelService.getNameById(mtModel.getMaTT_29183955());
				MuontraBean mtBean = new MuontraBean(mtModel, tenDG, tenTT);
				mtBeanList.add(mtBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mtBeanList;
	}
}
