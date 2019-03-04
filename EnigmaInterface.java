//James Trevithick          TrevithickP5            jtrevithick2@cnm.edu
//EnigmaInterface.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevithickp5;


/**
 *
 * @author james
 */
public interface EnigmaInterface {
    
//Methods
public void SetMessage(String msg);
    
public void SetMessage(String msg, int key);
   
   
public void SetCodedMessage(String codedMsg, int key);

public String GetCodedMessage();

public String GetDecodedMessage();

public int GetKey();
    
}
