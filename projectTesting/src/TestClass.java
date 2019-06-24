public class TestClass {

    public Float Triangle(float num1,float num2,float num3){
        if((num1>0 && num2>0 && num3>0) && (num1+num2>num3 && num2+num3>num1 && num3+num1>num2)){
            return num1+num2+num3;
        }
        else
        {
            float numError = -1;
            return numError;
        }
    }

    public String FormatText(String text){

        if(text=="" || text==null){
            return null;
        }
        else
        {
            String rslt = text.substring(0, 1).toUpperCase() + text.substring(1);
            return rslt;
        }

    }

}