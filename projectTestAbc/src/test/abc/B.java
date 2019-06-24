package test.abc;

public abstract class B extends A implements I2,I3{

    public B(){
        System.out.println("I am B constructor");
    }
    public void Hello(){
        System.out.println("I am B class boi.");
    }
    public void Show(){
        System.out.println("I am show method");
    }
}
