
public class Pizza2 {

	private double price;
	private boolean veg;
	
	
		
	public Pizza2(boolean veg) {
		this.veg=veg;
		if(this.veg=true) {
			this.price=100;
		}
		else {
			this.price=200;
		}
	}
}
