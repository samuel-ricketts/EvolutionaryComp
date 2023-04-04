class chromosome{
private int length;
private int[] listArray;

public chromosome(String list){
    String[] stringArray = list.split(",");
    int len = stringArray.length;
    listArray = new int[len];
    int num=0;
    for (String s : list.split(",")) {
        listArray[num] = Integer.parseInt(s);
        num++;
    }
   
}



public int[] getChromosome(){
    return listArray;
}


public void printChromosome(){
    for (int x : listArray){
        System.out.print(x);
        System.out.print(" ");
    }
}



}