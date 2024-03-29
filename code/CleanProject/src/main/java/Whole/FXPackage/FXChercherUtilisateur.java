package Whole.FXPackage;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class FXChercherUtilisateur extends FXMenuBarAbstractControleur{
    @FXML
    TextField emailTextField;
    @FXML
    ListView<String> listView;
    @FXML
    public void retourForm(ActionEvent event) {
        ControleurFunctions.changeScene(event, "FxInterfaceMain.fxml");
    }
    @FXML
    protected void chercher(ActionEvent event){
        if(emailTextField.getText()!=null){
            if(!emailTextField.getText().isBlank()){
                ArrayList<String> list = ControleurFunctions.utilisateurDAO.chercher(emailTextField.getText());
                ObservableList observableList = listView.getItems();
                observableList.clear();
                observableList.addAll(list);
                listView.refresh();
            }
        }
    }
    @FXML
    protected void itemCLick(MouseEvent event){
        String email = listView.getSelectionModel().getSelectedItem();
        if(email!=null){
            FXModifierUtilisateur.utilisateur = email;
            ControleurFunctions.changeScene(event,"FxInterfaceModifierUtilisateur.fxml");
        }
    }
}
