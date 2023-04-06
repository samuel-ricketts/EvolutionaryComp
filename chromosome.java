
class Chromosome{
public int[] chrom;

public Chromosome(int[] inChrom){
    chrom = inChrom;
}

public int returnAllele(int index){
    return chrom[index];
}
}