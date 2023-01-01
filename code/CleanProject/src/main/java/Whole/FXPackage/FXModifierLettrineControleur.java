package Whole.FXPackage;

import Whole.Metadonnee;
import Whole.ccmsPackage.Lettrine;
import Whole.ccmsPackage.Tag;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXModifierLettrineControleur implements Initializable {

    public static Lettrine lettrine;

    @FXML
    Label labelModifier;
    @FXML
    TextField pageTextField;
    @FXML
    TextField lienTextField;
    @FXML
    TextField ouvrageTextField;
    @FXML
    TextField createurTextField;
    @FXML
    TextField plagiatTextField;
    @FXML
    Button annulerBtn;
    @FXML
    Button confirmerBtn;
    @FXML
    TextField tagTextField;
    @FXML
    Button ajouterTagBtn;
    @FXML
    TextField nomMetaTextField;
    @FXML
    TextField valeurMetaTextField;
    @FXML
    TextField uniteValeurMetaTextField;
    @FXML
    TextField descriptionMetaTextField;
    @FXML
    Button ajouterMetaBtn;
    @FXML
    CheckBox suppresionMeta;
    @FXML
    CheckBox suppresionTag;
    @FXML
    ListView<Metadonnee> metaListView;
    @FXML
    ListView<Tag> tagListView;

    Boolean tagSupression;
    Boolean metaSupression;

    ArrayList<Tag> newTag = new ArrayList<>();
    ArrayList<Metadonnee> newMeta = new ArrayList<>();

    ArrayList<Tag> removeTag = new ArrayList<>();
    ArrayList<Metadonnee> removeMeta = new ArrayList<>();

    ObservableList<Tag> listTag = FXCollections.observableArrayList();
    ObservableList<Metadonnee> listMeta = FXCollections.observableArrayList();
    @FXML
    Button supprimerBtn;

    @FXML
    public void valider(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lettrine Modifiée");
        alert.setHeaderText(null);
        alert.setContentText("La lettrine à été modifiée.");

        alert.showAndWait();
        ControleurFunctions.changeScene(event, "FxInterfacePageLettrine.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageTextField.setText(""+lettrine.getNbPage());
        lienTextField.setText(lettrine.getLien());
        if(lettrine.getOuvrage()!=null){
            ouvrageTextField.setText(""+lettrine.getOuvrage().getId());
        }
        if(lettrine.getCreateur()!=null){
            createurTextField.setText(""+lettrine.getCreateur().getId());
        }
        plagiatTextField.setText(""+lettrine.getIdentique());
        labelModifier.setText("Modification - Lettrine n°"+lettrine.getId());

        for(Metadonnee meta:lettrine.getMetadonnees()){
            listMeta.add(meta);
        }
        metaListView.setItems(listMeta);
        for(Tag tag:lettrine.getTags()){
            listTag.add(tag);
        }
        tagListView.setItems(listTag);
    }
    @FXML
    protected void setMetaSupression(ActionEvent event){
        metaSupression = suppresionMeta.isSelected();
    }
    @FXML
    protected void setTagSupression(ActionEvent event){
        tagSupression = suppresionTag.isSelected();
    }
    @FXML
    protected void ajouterTag(ActionEvent event){
        if(tagTextField.getText()!=null){
            Tag t = new Tag(Integer.parseInt(tagTextField.getText()));
            newTag.add(t);
            listTag.add(t);
            tagListView.refresh();
        }
    }
    @FXML
    protected void ajouterMeta(ActionEvent event){
        if(nomMetaTextField.getText()!=null){
            Metadonnee m = new Metadonnee(nomMetaTextField.getText(),valeurMetaTextField.getText(),uniteValeurMetaTextField.getText(),descriptionMetaTextField.getText());
            newMeta.add(m);
            listMeta.add(m);
            metaListView.refresh();
        }
    }
    @FXML
    protected void clickListViewTag(ActionEvent event){
        if(tagSupression){
            Tag t = tagListView.getSelectionModel().getSelectedItem();
            newTag.remove(t);
            listTag.remove(t);
            removeTag.add(t);
            tagListView.refresh();
        }
    }
    @FXML
    protected void clickListViewMeta(ActionEvent event){
        if(metaSupression){
            Metadonnee m = metaListView.getSelectionModel().getSelectedItem();
            newMeta.remove(m);
            listMeta.remove(m);
            removeMeta.add(m);
            metaListView.refresh();
        }
    }

}