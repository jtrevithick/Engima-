//James Trevithick             TrevithickP5                jtrevithick2@cnm.edu
//FXMLDocumentController.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevithickp5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author james
 */
public class FXMLDocumentController implements Initializable {
   Enigma enig;
    
   @FXML
   private Button buttonEncode;
   @FXML
   private Button buttonDecode;
   @FXML
   private Button buttonClear;
   @FXML
   private TextField tMess;
   @FXML
   private RadioButton rEG;
   @FXML
   private RadioButton rKey;
   @FXML
   private TextField tKey;
   @FXML
   private TextArea taCMess;
   @FXML
   private TextArea taKey;
   @FXML
   private MenuItem menuSave;
   @FXML
   private MenuItem menuOpen;
   @FXML
   private MenuItem aboutEnigma;
   
   
   //save menuItem
   @FXML
    private void MenuSaveAction(ActionEvent event){
        String codedmess=taCMess.getText();
        String key=taKey.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
        
        //Show the Save File Dialog
        File file = fileChooser.showSaveDialog(null); 
        
        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(myFile);
                outputFile.println(codedmess);
                outputFile.println(key);               
               
                outputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }
    //Open menu item
    @FXML
    private void MenuOpenAction(ActionEvent event){
    // TODO add your handling code here:
        //Open the file and read two lines
        //Create Filechooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
        
        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);  
        
        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath(); 
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
        	   
               String codedMessage = inputFile.nextLine();
               int key = inputFile.nextInt();
              //String skey = inputFile.nextLine();  
               //key = Integer.parseInt(skey);
               taCMess.setText(codedMessage);
               taKey.setText(key + "");
               inputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    //button for about enigma action
    @FXML
    private void AboutEnigmaClicked(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Enigma");
        alert.setHeaderText(null);
        alert.setContentText("Enigma is based on a machine to code secret messages! However enigma is not perfect and is not gurentee to work with all messages. ");

        alert.showAndWait();
    
    }
   //encode button handler
   @FXML
    private void buttonEncodeOnAction(ActionEvent event)
    {
        String mess ;
        enig = new Enigma();
        mess=tMess.getText();
        try {
            
            //check the radio buttons
            if(rEG.isSelected()){
                enig.SetMessage(mess);
            }
            else{ 
                int key= Integer.parseInt(tKey.getText());
               
                enig.SetMessage(mess, key);
            }
            String codedMess=enig.GetCodedMessage();
            taCMess.setText(codedMess);//show coded Message to user

            taKey.setText("" + enig.GetKey());//show key to user


        } catch(NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //Decode handler
    @FXML
    private void buttonDecodeOnAction(ActionEvent event){
        String codedMess;
        enig=new Enigma();
        codedMess=taCMess.getText();
        int k=Integer.parseInt(taKey.getText());
        enig.SetCodedMessage(codedMess, k);
        String mess=enig.GetDecodedMessage();
        tMess.setText(mess);
    
    }
    //clear button handler
    @FXML
    private void buttonClearAction(ActionEvent event){
        tMess.clear();
        taKey.clear();
        taCMess.clear();
        tKey.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
