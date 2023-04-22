public class SimulatedAnnealing {
    private int[] set;
    private int setSize;
    private int kValue;
    public SimulatedAnnealing(String list, int k){
        String[] stringArray = list.split(",");
        kValue = k;
        int len = stringArray.length;
        set = new int[len];
        int num=0;
        for (String s : list.split(",")) {
            set[num] = Integer.parseInt(s);
            num++;
        }
        setSize = set.length;
    }

    public void run(String set, int k ){
        Chromosome initialChrome = createChromosome();
        double alpha = 0.85;
        double beta = 0.75;
        
        
    }

    private Chromosome createChromosome(){
        int[] chromeArr = new int[setSize];
        for(int j=0; j<setSize; j++){
            chromeArr[j] = (1 + (int)(Math.random() * 100)) % 2;
        }
        int chromeSum = subSum(chromeArr);
        return new Chromosome(chromeArr, chromeSum, kValue);
    }

    private int subSum(int[] chromeArray){
        int sum=0;
        for(int i=0; i<set.length; i++){
            if(chromeArray[i]==1){
                sum+=set[i];
            }
        }
        return sum;
    }
}
