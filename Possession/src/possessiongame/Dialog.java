package possessiongame;


import java.awt.Image;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Dialog {
	private ArrayList<String> dialog;
	
	public Dialog(ArrayList<String> d) {
			dialog = d;
	}
	
	public String outputMessage(){
		String message = dialog.get(0);
		return message;
	}
}
