package possessiongame;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Graphics;

public class Dialog {

	public Image dialog_background;
	public Graphics text;
	
	public Dialog(Image image) {
			dialog_background = image;
			
	}
	
	public String outputDialog(){
		String message = "This is a message";
		return message;
	}

	public Image getDialog_background() {
		return dialog_background;
	}
	
	//Can be used for "Angry" styled bubbles, etc.
	public void setDialog_background(Image dialog_background) {
		this.dialog_background = dialog_background;
	}
	
	private void Dialog_load(){
		
	}
}
