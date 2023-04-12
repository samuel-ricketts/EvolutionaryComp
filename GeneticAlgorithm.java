public class GeneticAlgorithm {
    

    public void run(String set, int k ){
        Population p = new Population(set, k);
        p.printChromosomes();
        p.sortList(p.getChromosomes());
            

        for(int i=0; i<50; i++){
                System.out.println("Iteration: " + i);
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
