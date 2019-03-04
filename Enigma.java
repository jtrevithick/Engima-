//James Trevithick          TrevithickP5                jtrevithick2@cnm.edu
//Enigma.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trevithickp5;

import java.util.Random;

/**
 *
 * @author james
 */
public class Enigma implements EnigmaInterface {
private int key = 1;
private String message= "", codedMessage = "";
private Random rand;

public Enigma(){
rand=new Random();
}
//encode and decode methods
private void Encode(){
    codedMessage = "";
	for (int i = 0; i < message.length(); i++) {
		int c = (int)message.charAt(i);//message.indexOf(i);//?
		int offset;
		if (c + key > 126)
		{
			offset = c + key - 126;

                        c = 31 + offset;
		}
		
		else {
			c = c + key;
		}
		codedMessage+=(char)(c);//?
	}
}
private void Decode(){
    	message = "";
	for (int i = 0; i < codedMessage.length(); i++) {
		int c = (int)codedMessage.charAt(i);
		int offset;
		if (c - key < 32) 
		{

			offset = c - key + 126;
			c =  offset - 31;
		}
	
		else {
			c = c - key;
		}
		message+=(char)(c);
	}
}

//methods override for enigma interface class
    @Override
    public void SetMessage(String msg) {
       key = rand.nextInt()%50+1;


	message = msg;
	Encode();
    }



    @Override
    public void SetMessage(String msg, int key) {
       if (key < 1 || key > 50) {
		key = 1;
	}
	this.key = key;
	message = msg;
	Encode();
    }

    @Override
    public void SetCodedMessage(String codedMsg, int key) {
       codedMessage = codedMsg;
	this.key = key;
	Decode();
    }

    @Override
    public String GetCodedMessage() {
       return codedMessage;
    }

    @Override
    public String GetDecodedMessage() {
       return message;
    }

    @Override
    public int GetKey() {
        return key;
    }

    
}
