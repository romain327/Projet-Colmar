package Whole.FXPackage;

import Whole.ccmsPackage.Tag;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class FXTagRechercheControleur {
    @FXML
    TextField nomTextField;
    @FXML
    ListView<Tag> listView;
    @FXML
    protected void chercher(ActionEvent event){
        Tag t = new Tag();
        if(!nomTextField.getText().isBlank()){
            t.setNom(nomTextField.getText());
        }
        ArrayList<Tag> list = ControleurFunctions.tagDAO.chercher(t);
        ObservableList observableList = listView.getItems();
        observableList.clear();
        observableList.addAll(list);
        listView.refresh();
    }
    @FXML
    protected void itemCLick(ActionEvent event){
        Tag t = listView.getSelectionModel().getSelectedItem();
        FXPageTagControleur.tag=t;
        ControleurFunctions.changeScene(event,"FxInterfacePageTag.fxml");
    }
    @FXML
    public void retourForm(ActionEvent event) {
        ControleurFunctions.changeScene(event, "FxInterfaceTags.fxml");
    }
}
