package sk.itsovy.thread;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main is running boi");
        MyThread t1 = new MyThread("T1");
        t1.Start();

        System.out.println("Main ended right now boi");

    }
}
