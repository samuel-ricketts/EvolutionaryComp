public class population {

private int length;
private int[] listArray;

public population(String list){
    String[] stringArray = list.split(",");
    int len = stringArray.length;
    listArray = new int[len];
    int num=0;
    for (String s : list.split(",")) {
        listArray[num] = Integer.parseInt(s);
        num++;
    }
   
}

public void printOrigSet(){
    for (int x : listArray){
        System.out.print(x);
        System.out.print(" ");
    }
}
}
