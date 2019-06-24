package main;


import bill.Bill;
import exception.BillException;
import items.Category;
import items.Goods;
import items.Item;
import items.drink.Bottle;
import items.drink.Draft;
import items.food.Food;
import items.food.Fruit;
import items.food.Pastry;

public class Application {
    private static Application app = new Application();
    public static Application getInstance() {
        return app;
    }

    public void example() throws BillException{

        Bill bill = new Bill();
        bill.print();
        Bottle pivo = new Bottle("pivo dobre",0.90,2);
        bill.addItem(pivo);
        Item pizza = new Pastry("Quattro",6.50,420,1);
        bill.addItem(pizza);
        Food cherry = new Fruit("Good cherry",0.01,0.25);
        bill.addItem(cherry);
        Goods pravitko = new Goods("Pravitko velke",1.50,1, Category.SCHOOL);
        bill.addItem(pravitko);
        Draft kofola = new Draft("Kofolka",1,true,0.5);
        bill.end();
        bill.print();
        System.out.println(bill.getCount());
        System.out.println(bill.getFinalPrice());

    }
}
