/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * MovingCharacterProxy class
 * 	  design patter to move character 
 */

public class MovingCharacterProxy {
	//character variable
	private Avatar character;
	
	//constructor
	public MovingCharacterProxy(Avatar charact) {
		this.character = charact;
	}
	
	//setter
	public void setCharacter(Avatar charact) {
		this.character = charact;
	}
	
	//move methods
	  public void moveDown() {
		  character.moveDown();
	  }
	  public void moveUp() {
		  character.moveUp();
	 }
}
