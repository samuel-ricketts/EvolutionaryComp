
class Chromosome{
private int[] chrom;
private int fitness;
private int chromSum;
private int kValue;
private int numberOfOnes;
public Chromosome(int[] inChrom, int sum, int k){
    chrom = inChrom;
    chromSum = sum;
    kValue = k;
    assignFit();
}
public int[] getAlleles(){
    return chrom;
}
public int getAllele(int index){
    return chrom[index];
}

public int getSum(){
    return chromSum;
}

public int getFitness(){
    return fitness;
}

private void assignFit(){
    if (chromSum<=kValue){
        fitness = kValue-chromSum;
    }
    else{
        fitness = (chromSum-kValue) + (kValue/10);
    }
}



}
