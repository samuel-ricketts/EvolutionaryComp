import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        String input1;
        String input2;
        System.out.print("Type in set separated by a comma: ");
        input1 = console.nextLine();
        chromosome chrom = new chromosome(input1);
        chrom.printChromosome();



    }
}
