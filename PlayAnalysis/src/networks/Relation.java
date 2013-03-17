package networks;
import character.Character; 

public class Relation {
	private Character object;
	private Character subject;
	private double weight; 
	
	public Relation(Character object, Character subject) {
		this.object = object; 
		this.subject = subject; 
	}
	
	public Character getSubject() {
		return subject; 
	}
	
	public Character getObject() {
		return object; 
	}
	
	public void setWeight(double weight) {
		this.weight = weight; 
	}
	
	public double getWeight() {
		return weight; 
	}
}
