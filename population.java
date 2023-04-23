import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public class Population {

private int[] listArray;
private int listSize;
private ArrayList<Chromosome> chromosomes = new ArrayList<>();
private int kValue;
public int populationSize = 100;
Random rand = new Random();
public Population(String list, int k){
    String[] stringArray = list.split(",");
    kValue = k;
    int len = stringArray.length;
    listArray = new int[len];
    int num=0;
    for (String s : list.split(",")) {
        listArray[num] = Integer.parseInt(s);
        num++;
    }
    listSize = listArray.length;
    initPopulation();
}

private void initPopulation(){
    for (int i=0; i<populationSize; i++){ //can change later
        int[] chromeArr = new int[listSize];
        for(int j=0; j<listSize; j++){
            chromeArr[j] = (1 + (int)(Math.random() * 100)) % 2;
        }
        int chromeSum = subSum(chromeArr);
        Chromosome chrome = new Chromosome(chromeArr, chromeSum, kValue);
        chromosomes.add(chrome);
    }
}

public void printOrigSet(){
    for (int x : listArray){
        System.out.print(x);
        System.out.print(" ");
    }
    System.out.println();
}

public void printChromosomes(){
    for( Chromosome c : chromosomes){
        for(int i=0; i<listSize; i++){
            System.out.print(c.getAllele(i));
        }
        System.out.print(" "+ "Sum: " + c.getSum() + " " + "Fitness: " + c.getFitness());
        System.out.println();
    }
}

public int subSum(int[] set){
    int sum=0;
    for(int i=0; i<set.length; i++){
        if(set[i]==1){
            sum+=listArray[i];
        }
    }
    return sum;
}


public ArrayList<Chromosome> rank(){
    ArrayList<Chromosome> list = new ArrayList();
    double[] rankPerList = new double[populationSize];
    HashMap<Integer,Chromosome> hm = new HashMap();
    double summedRank = (populationSize/2) * (1+populationSize);
    sortList(chromosomes);
    double sum =0;
    for(int i=0; i<populationSize; i++ ){
        rankPerList[i] = ( (populationSize-i) / summedRank);
        sum+=rankPerList[i];
    }

    for(int j=0; j<populationSize; j++){
        double randomNum = rand.nextDouble(1);
        for(int k=0; k<populationSize+1; k++){
            if(randomNum<=0){
                list.add(chromosomes.get(k-1));
                break;
            }
            else{
                randomNum-=rankPerList[k];
            }
        }
    }
    return list;
}

public ArrayList<Chromosome> tournament(){
    ArrayList<Chromosome> list = new ArrayList();
    double kReq = 0.75;
    for(int i=0; i<populationSize; i++){
        Chromosome c1 = chromosomes.get(rand.nextInt(populationSize));
        Chromosome c2 = chromosomes.get(rand.nextInt(populationSize));
        double randomDouble = rand.nextDouble(1);
        if(randomDouble < kReq){
            if(c1.getFitness() < c2.getFitness()){
                list.add(c1);
            }
            else{
                list.add(c2);
            }
        }
        else{
            if(c1.getFitness() < c2.getFitness()){
                list.add(c2);
            }
            else{
                list.add(c1);
            }
        }
    }
    return list;
}

public void sortList(ArrayList<Chromosome> chrom){
    int n = chrom.size();
    for (int i=0; i<n-1; i++)
        for (int j=0; j<n-i-1; j++) {
            Chromosome c1 = chrom.get(j);
            Chromosome c2 = chrom.get(j+1);
            if(c1.getFitness() > c2.getFitness()){
                Chromosome temp = chrom.get(j+1);
                chrom.remove(j+1);
                chrom.add(j, temp);
            }
        }
}

public void uniformCrossover(ArrayList<Chromosome> selectedChrom){
    ArrayList<Chromosome> chrom = new ArrayList<>();
    for(int i=0; i<populationSize; i+=2){
        int[] uniformString = new int[listSize];
        for(int j=0; j<listSize; j++){
            uniformString[j] = (1 + (int)(Math.random() * 100)) % 2;
        }
        Chromosome c1 = selectedChrom.get(i);
        Chromosome c2 = selectedChrom.get(i+1);
        int[] child1Array = new int[listSize];
        int[] child2Array = new int[listSize];
        for(int l=0; l<listSize; l++){
            if(uniformString[l]==1){ //child 1
                child1Array[l] = c1.getAllele(l);
            }
            else{
                child1Array[l] = c2.getAllele(l);
            }

            if(uniformString[l]==1){ //child2
                child2Array[l] = c2.getAllele(l);
            }
            else{
                child2Array[l] = c1.getAllele(l);
            }
        }
        int mutate1 = rand.nextInt(100);
        int mutate2 = rand.nextInt(100);
        if(mutate1<=5){
            mutate(child1Array);
        }
        if(mutate2<=5){
            mutate(child2Array);
        }
        chrom.add(new Chromosome(child1Array, subSum(child1Array), kValue));
        chrom.add(new Chromosome(child2Array, subSum(child2Array), kValue));
    }
    elitism(chrom);
    chromosomes = chrom;

}

public void doublePointCrossover(ArrayList<Chromosome> selectedChrom){
    ArrayList<Chromosome> chrom = new ArrayList<>();
    for(int i=0; i<populationSize; i+=2){
        int firstCut = listSize/3;
        int secondCut = firstCut*2;
        Chromosome c1 = selectedChrom.get(i);
        Chromosome c2 = selectedChrom.get(i+1);
        int[] child1Array = new int[listSize];
        int[] child2Array = new int[listSize];
        for(int j=0; j<listSize; j++){
            if(j<firstCut || j>= secondCut){
                child1Array[j] = c1.getAllele(j);
            }
            else if(j>=firstCut){
                child1Array[j] = c2.getAllele(j);
            }
        }

        for(int k=0; k<listSize; k++){
            if(k<firstCut || k>= secondCut){
                child2Array[k] = c2.getAllele(k);
            }
            else if(k>=firstCut){
                child2Array[k] = c1.getAllele(k);
            }
        }


        int mutate1 = rand.nextInt(100);
        int mutate2 = rand.nextInt(100);
        if(mutate1<=5){
            mutate(child1Array);
        }
        if(mutate2<=5){
            mutate(child2Array);
        }
        chrom.add(new Chromosome(child1Array, subSum(child1Array), kValue));
        chrom.add(new Chromosome(child2Array, subSum(child2Array), kValue));
    }
    elitism(chrom);
    chromosomes = chrom;

}


public ArrayList<Chromosome> getChromosomes(){
    return chromosomes;
}



public void elitism(ArrayList<Chromosome> chroms){
    Chromosome c1 = chromosomes.get(0);
    Chromosome c2 = chromosomes.get(1);
    chroms.remove(0);
    chroms.remove(1);
    chroms.add(0, c2);
    chroms.add(0, c1);
}

public void mutate(int[] allelles){
    int rand1 = rand.nextInt(listSize);
    int rand2 = rand.nextInt(listSize);
    if(allelles[rand1]==0){
        allelles[rand1] = 1;
    }
    else{
        allelles[rand1] = 0;
    }

    if(allelles[rand2]==0){
        allelles[rand2] = 1;
    }
    else{
        allelles[rand2] = 0;
    }
}

private int[] pairwiseMutate(int[] allelles){
    int rand1 = rand.nextInt(allelles.length);
    int rand2 = rand.nextInt(allelles.length);
    int temp = allelles[rand1];

    allelles[rand1] = allelles[rand2];
    allelles[rand2] = temp;
    return allelles;
}

public void bestString(){
    int[] allelles = chromosomes.get(0).getAlleles();
    System.out.print("Best Solution: [");
    String midOutput = "";
    for(int i=0; i<listSize; i++){
        if(allelles[i]==1){
            if(midOutput.length()!=0){
                midOutput+=", ";
            }
            midOutput += listArray[i];
        }
    }
    System.out.print(midOutput);
    System.out.println("]");
}
}

