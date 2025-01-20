
public class PizzaBill {

	private double Price;
	private boolean veg;
	private int extraCheesePrice=70;
	private int extraToppingsPrice=50;
	private int parsalPrice=10;
	private int basePizzaPrice;

	private boolean isExtraCheeseAdded =false;
	private boolean isExtraToppingAdded = false;
	private boolean isParsaled =false;

	public PizzaBill(boolean veg) {
		super();
		this.veg = veg;
		if(this.veg) {
			this.Price=200;
		}
		else {
			this.Price=300;
		}
		basePizzaPrice=(int) this.Price;
	}
	
	public void addExtraCheese() {
		isExtraCheeseAdded=true;
		this.Price+=extraCheesePrice;
		
	}
	public void addExtraToppings() {
		isExtraToppingAdded=true;
		this.Price+=extraToppingsPrice;

	}
	public void parsal() {
		isParsaled=true;
		this.Price+=parsalPrice;

	}
	public void getBill() {
		String bill="";
		System.out.println("Pizza price is :"+this.basePizzaPrice);
		if(isExtraCheeseAdded) {
			bill+="For Extra Cheese: "+extraCheesePrice+ "\n";
		}
		if(isExtraToppingAdded) {
			bill+="For Extra Topping: "+extraToppingsPrice+ "\n";
		}
		if(isParsaled) {
			bill+="For parsal: "+parsalPrice+ "\n";
		}
		bill+="Total price: "+this.Price + "\n";
		System.out.println(bill);
	}
	
}
