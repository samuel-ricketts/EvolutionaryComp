import java.util.Random;
public class SimulatedAnnealing {
    private int[] set;
    private int setSize;
    private int kValue;
    private Random rand = new Random();
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

    public void run(){
        Chromosome mainChrome = createChromosome();
        System.out.println("Initial Chromosome: ");
        printChromosome(mainChrome);
        double alpha = .98;
        double beta = 1.02;
        double t = 10.0;
        double iterations = 1000;
        while(t>1){
            for (int j=0; j<iterations; j++){
                int[] newArray = cycleOfThree(mainChrome);
                Chromosome newChrome = new Chromosome(newArray, subSum(newArray), kValue);
                double randomNum = rand.nextDouble(1);
                double first = mainChrome.getFitness();
                double second = newChrome.getFitness();
                double fitnessSub = first - second;
                if(newChrome.getFitness() < mainChrome.getFitness()  || randomNum < (double) Math.pow(Math.E, (fitnessSub/ t))){
                    mainChrome = newChrome;
                }
                if(mainChrome.getFitness()==0){
                    break;
                }
            }
            if(mainChrome.getFitness()==0){
                break;
            }
            t = t * alpha;
            iterations = iterations * beta;
        }
        System.out.println();
        printChromosome(mainChrome);
        bestString(mainChrome);
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

    private int[] pairwise(Chromosome chrome){
        int[] editArray = chrome.getAlleles().clone();
        int rand1 = rand.nextInt(editArray.length);
        int rand2 = rand.nextInt(editArray.length);
        int temp = editArray[rand1];

        editArray[rand1] = editArray[rand2];
        editArray[rand2] = temp;
        return editArray;
    }

    private int[] cycleOfThree(Chromosome chrome){
        int[] editArray = chrome.getAlleles().clone();
        int rand1 = rand.nextInt(editArray.length);
        int rand2 = rand.nextInt(editArray.length);
        int rand3 = rand.nextInt(editArray.length);
        int temp1 = editArray[rand1];
        int temp2 = editArray[rand2];
        int temp3 = editArray[rand3];

        editArray[rand2] = temp1;
        editArray[rand3] = temp2;
        editArray[rand1] = temp3;
        return editArray;
    }

    public int[] flip(Chromosome chrome){
        int[] editArray = chrome.getAlleles().clone();
        int rand1 = rand.nextInt(editArray.length);
        int rand2 = rand.nextInt(editArray.length);
        if(editArray[rand1]==0){
            editArray[rand1] = 1;
        }
        else{
            editArray[rand1] = 0;
        }
    
        if(editArray[rand2]==0){
            editArray[rand2] = 1;
        }
        else{
            editArray[rand2] = 0;
        }
        return editArray;
    }
    public void printChromosome(Chromosome chrome){
            for(int i=0; i<chrome.getAlleles().length; i++){
                System.out.print(chrome.getAllele(i));
            }
            System.out.print(" "+ "Sum: " + chrome.getSum() + " " + "Fitness: " + chrome.getFitness());
            System.out.println();
        }


    public void bestString(Chromosome chrome){
        int[] allelles = chrome.getAlleles();
        System.out.print("Best Solution: [");
        String midOutput = "";
        for(int i=0; i<allelles.length; i++){
            if(allelles[i]==1){
                if(midOutput.length()!=0){
                    midOutput+=", ";
                }
                midOutput += set[i];
            }
        }
        System.out.print(midOutput);
        System.out.println("]");
    }
    
}

