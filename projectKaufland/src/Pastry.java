public class Pastry extends Food implements pcs{
    private int amount;

    public Pastry(String name, double price, int calories, int amount) {
        super(name, price, calories);
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    public int getTotalPrice(){
        return amount*getPrice;
    }
}
