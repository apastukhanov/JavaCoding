import java.util.Scanner;

public class TimeCalc {
    public static double find_time_difference (double v1,
                                               double v2,
                                               double a1,
                                               double a2,
                                               double S){

        double time_diff;
        time_diff = ( -(v1+v2) + Math.sqrt(Math.pow((v1+v2),2) + (a1+a2)*2*S) ) / (a1+a2);
        return time_diff;
    }

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Укажите скорость 1го автомобиля, V1:");
        double v1 = in.nextDouble();
        System.out.print("Укажите ускорение 1го автомобиля, a1:");
        double a1 = in.nextDouble();
        System.out.print("Укажите скорость 2го автомобиля, V2:");
        double v2 = in.nextDouble();
        System.out.print("Укажите ускорение 2го автомобиля, a2:");
        double a2 = in.nextDouble();

        System.out.print("Укажите начальное расстояние, S:");
        double S = in.nextDouble();

        System.out.println(new StringBuilder()
                .append("Время, за которое встретятся автомобили ")
                .append(find_time_difference(v1, v2, a1, a2, S)).toString());
    }
}
