package possessiongame.framework;

import java.awt.Image;

public class PersonAnim {
	
    private Animation frontAnim, backAnim, leftAnim, rightAnim;
    
	public PersonAnim(Image front, Image front2, Image front3,
            Image back, Image back2, Image back3,
            Image left, Image left2, Image left3,
            Image right, Image right2, Image right3){

	        frontAnim = new Animation();
	        frontAnim.addFrame(front, 75);
	        frontAnim.addFrame(front2, 75);
	        frontAnim.addFrame(front, 75);
	        frontAnim.addFrame(front3, 75);

	        rightAnim = new Animation();
	        rightAnim.addFrame(right, 75);
	        rightAnim.addFrame(right2, 75);
	        rightAnim.addFrame(right, 75);
	        rightAnim.addFrame(right3, 75);

	        backAnim = new Animation();
	        backAnim.addFrame(back, 75);
	        backAnim.addFrame(back2, 75);
	        backAnim.addFrame(back, 75);
	        backAnim.addFrame(back3, 75);

	        leftAnim = new Animation();
	        leftAnim.addFrame(left, 75);
	        leftAnim.addFrame(left2, 75);
	        leftAnim.addFrame(left, 75);
	        leftAnim.addFrame(left3, 75);
	}
	public Animation getFront(){
		return frontAnim;
	}
	public Animation getBack(){
		return backAnim;
	}
	public Animation getLeft(){
		return leftAnim;
	}
	public Animation getRight(){
		return rightAnim;
	}
}
