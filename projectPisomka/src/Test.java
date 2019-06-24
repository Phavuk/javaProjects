public class Test {
    // funkcia prijme cele cislo a vrati true / false, ci vstupne cislo je prvocislo
// zaporne prvocisla nie su
    public boolean isPrimeNumber(int x){
        if(x<2) return false;
        int p=0;
        if(x%2==0 && x>3) return false;
        for(int i=2;i<Math.sqrt(x);i++)
            if(x%i==0)
                p++;
        return p==0?true:false;
    }

    // funkcia prijme test a vrati text bez samohlasok
    public String removeVowels(String text){
        if(text==null) return text;
        String ret="";
        for(int i=0;i<text.length();i++){
            char y;
            char z=y=Character.toUpperCase(text.charAt(i));
            if(z!='A' &&z!='E' && z!='I' && z!='O' && z!='U' && z!='Y' ){
                ret=ret+String.valueOf(y);
            }
        }
        return ret;
    }

    // funkcia prijme 2 cisla, lavu a pravu hranicu intervalu a najde najvacsie prvocislo v tomto inetrvale
// ak je zly vstup, napr, ze v intervale su zaporne cisla, vrati -1
    public int maxPrimeNumberBetweenAandB(int a, int b){
        if(b<1)
            return -1;
        int i=b;
        if(i%2==0) i--;
        while(i>=a) {
            if (isPrimeNumber(i))
                return i;
        }
        return -1;
    }

    // funkcia skonvertuje cas v hod a min na hod tvar desatinneho cisla
//    presnost na 4 desatinne miesta
// 1 hod 30 min = 1,5 hod    ; 2 hod 9 min = 2,15
    public double convertHHMMtoHH(int hh, int mm){
        double ret;
        ret=hh+mm/60f;
        return ret;
    }
}
