package sk.itsovy.thread;

import java.util.Random;

public class MyThread implements Runnable{
    private String name;
    private Thread t;

    public MyThread(String name) {
        this.name = name;
        System.out.println("Thread "+name+" welcome onboard");


    }

    @Override
    public void run() {
        System.out.println(name+" Is running");


        try{
            for(int i=1;i<=10;i++) {
                Thread.sleep(getTimeMs());
                System.out.println(""+i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(name+" Just finished");
    }

    public void Start(){
        System.out.println(name+" Started doing his work");
        if(t==null) {
            t = new Thread(this, name);
            t.start();
        }
    }

    public int getTimeMs(){
        Random rnd = new Random();
        return rnd.nextInt(3000);

    }
}
