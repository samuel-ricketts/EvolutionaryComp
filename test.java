import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        String set;
        int k;
        System.out.print("Type in set separated by a comma: ");
        set = console.nextLine();
        System.out.println();
        System.out.print("Type in Sum value: ");
        k = Integer.parseInt(console.nextLine());
        GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.run(set, k);
    }
}
