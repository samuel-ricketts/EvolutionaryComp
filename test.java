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
        System.out.println();
        int continueAlgorithm = 1;
        long startTime;
        long endTime;
        long time;
        while(true){
            if(continueAlgorithm == 0){
                break;
            }
            System.out.print("1 for GA, 2 for SimAnnealing, 3 for FoolHillClimb-> ");
            int algorithm = Integer.parseInt(console.nextLine());
            if(algorithm==1){
                System.out.println("Genetic Algorithm:\n");
                GeneticAlgorithm GA = new GeneticAlgorithm();
                startTime = System.nanoTime();
                GA.run(set, k);
                endTime = System.nanoTime();
                time = (endTime - startTime);
                System.out.println("Time: " + time);
            }
            else if(algorithm==2){
                System.out.println("Simulated Annealing\n");
                SimulatedAnnealing SA = new SimulatedAnnealing(set, k);
                startTime = System.nanoTime();
                SA.run();
                endTime = System.nanoTime();
                time = endTime - startTime;
                System.out.println("Time: " + time);
            }
            else{
                System.out.println("Foolish Hill Climbing\n");
                FoolishHillClimbing FHC = new FoolishHillClimbing(set, k);
                startTime = System.nanoTime();
                FHC.run();
                endTime = System.nanoTime();
                time = endTime - startTime;
                System.out.println("Time: " + time);
            }
            System.out.print("0 to exit or 1 to choose another algorithm-> ");
            continueAlgorithm = Integer.parseInt(console.nextLine());
        }
    }
}
