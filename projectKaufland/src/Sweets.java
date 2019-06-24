public class Sweets extends Food {

    public Sweets(String name, double price, int calories, double weight) {
        super(name, price, calories);
        this.weight = weight;
    }

    public double getTotalPrice(){
        return 0;
    }
}
