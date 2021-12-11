

public class Main {
    //1ое задание
    public static float perimeter (float a, float b) {
        return 2*(a+b);
    }
    //2ое задание
    public static double getSum(){
        float x = 20;
        float temp_x;
        double res = 0;
        for(int i = 1; i<11; i++) {
            temp_x=x;
            for(int a1=1; a1<i; a1++) {
                temp_x*=x;
            };
            res = (double) (res+temp_x/(x-i));
        }
        return res;
    }
    // 3ье задание
    public static int getNNumber(int n){
        //индексация начинается с 0
        n++;
        int res = 1;
        for (int i=1;i < n; i++) {
            res*=2;
        }
        return res;
    }

    // 4ое задание
    public static void printN(int n) {
        char a = (char) 97;
        char[] chars = new char[122-96];
        String out = "";
        int k=0;
        for (int i=97; i<=122;i++){
            chars[k]=(char)i;
            k++;
        }
        for(int j = 0; j <= 25; j++) {
            for (int c=j; c >=0; c--) {
                  out+=chars[c];
            }
        }
        System.out.println(out.substring(0,n));
    }

    //5ое задание
    public static void printFigure(int h){
        int depth=1;
        String[] chars = new String[h*2-1];

        for (int i = 1; i<=h; i++) {
            for(int i2=0; i2 < chars.length; i2++) {chars[i2]=" ";};
            if (depth==1) {
                for(int k=0;k<h*2-1;k++) {
                    chars[k]="*";
                }
            }
            else if (depth==h) {
                    chars[h-1]="*";
                }
            else if (depth%2!=0) {
                for(int k=depth-1;k<h*2-depth;k++) {
                    chars[k]="*";
                }
            }
            else if (depth%2==0) {
                    chars[depth-1]="*";
                    chars[chars.length-depth]="*";
            }

            for(String c: chars) {System.out.print(c);};
            System.out.println();
            depth++;
        }
    }


    public static void main(String[] args) {
//        printN(20);
        printFigure(7);
        System.out.println("");
    }

}
