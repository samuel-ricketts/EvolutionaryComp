import java.util.ArrayList;

public class Population {

private int[] listArray;
private int listSize;
private ArrayList<Chromosome> chromosomes = new ArrayList<>();
public Population(String list){
    String[] stringArray = list.split(",");
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
    for (int i=0; i<100; i++){
        int[] chromeArr = new int[listSize];
        for(int j=0; j<listSize; j++){
            chromeArr[j] = (1 + (int)(Math.random() * 100)) % 2;
        }
        Chromosome chrome = new Chromosome(chromeArr);
        chromosomes.add(chrome);
    }
}

public void printOrigSet(){
    for (int x : listArray){
        System.out.print(x);
        System.out.print(" ");
    }
}

public void printChromosomes(){
    for( Chromosome c : chromosomes){
        for(int i=0; i<listSize; i++){
            System.out.print(c.returnAllele(i));
            System.out.print("");
        }
        System.out.println();
    }
}
}
