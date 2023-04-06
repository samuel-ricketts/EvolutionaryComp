import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        String input;
        System.out.print("Type in set separated by a comma: ");
        input = console.nextLine();
        Population p = new Population(input);
        p.printOrigSet();
        p.printChromosomes();
    }
}
