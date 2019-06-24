package sk.itsovy.projectexception;

public class Example {
    public void arrayTestException(){
        int[] arr = {5,19,95};
        try{
        for (int i =0;i<3;i++){
            System.out.println(arr[i]+"");

        }
        double v=8/0;
            System.out.println(v);
        v=Math.log10(-56);
        int x = -56;
        if (x<=0)throw new ArithmeticException("log cannot be neggative");
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        System.out.println("hello");
    }
}
