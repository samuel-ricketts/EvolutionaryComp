public class GeneticAlgorithm {
    

    public void run(String set, int k ){
        Population p = new Population(set, k);
        System.out.println("Initial Chromosomes: ");
        // p.printChromosomes();
        p.sortList(p.getChromosomes());
            

        for(int i=0; i<100; i++){
                // System.out.println("Iteration: " + i);
                p.uniformCrossover(p.rank());
                if(p.getChromosomes().get(0).getFitness()==0){
                    break;
                }
            }
        System.out.println("Done Chromosomes: ");
        p.sortList(p.getChromosomes());
        p.printChromosomes();
        p.bestString();
    }
}
