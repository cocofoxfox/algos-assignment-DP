/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    //Base case 1, when floors equal 1, only one trials.
    if(floors == 0 || floors == 1){
      return floors;
    }
    //Base case 2, if we only have 1 sheet, we have to traverse all the floors.
    if(sheets == 1){
      return floors;
    }
    
    //
    int minTrials = Integer.MAX_VALUE;
    int temp;

    // We don't know which floor we should drop the sheet, so use iteration to get all the possibilities.
    for(int i = 1; i <= floors; i++){
      temp = Math.max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
      minTrials = Math.min(temp, minTrials);
    }


    return minTrials + 1;
  }

  

  public int glassFallingMemoized(int floors, int sheets, int [][] trials) {
    
    //Base case 1, when floors equal 1, only one trials.
    if(floors == 0 || floors == 1){
      trials[floors][sheets] = floors;
      return floors;
    }
    //Base case 2, if we only have 1 sheet, we have to traverse all the floors.
    
    if(sheets == 1){
      trials[floors][sheets] = floors;
      return floors;
    }
    //Base case3, if memorized array's item alreay has been calculated, get the value from cache directly.
    //For java primary type array, default value is 0 or 0.0
    if(trials[floors][sheets] != 0){
      return trials[floors][sheets];
    }
    
    //
    int minTrials = Integer.MAX_VALUE;
    int temp;

    // We don't know which floor we should drop the sheet, so use iteration to get all the possibilities.
    for(int i = 1; i <= floors; i++){
      temp = Math.max(glassFallingMemoized(i - 1, sheets - 1, trials), glassFallingMemoized(floors - i, sheets,trials));
      minTrials = Math.min(temp, minTrials);
    }

    trials[floors][sheets] = minTrials + 1;
    return trials[floors][sheets];
  }


  public int glassFallingBottomUp(int floors, int sheets) {
    int[][] trials = new int[floors + 1][sheets + 1];

    //Base case1, if floors is 0, no trials. if floors is 1, only 1 trials.
    for(int i = 1; i <= sheets; i++){
      trials[0][i] = 0;
      trials[1][i] = 1;

    }

    //Base case2, if sheets = 1, minimum trials = floors;
    for(int i = 1; i <= floors; i++){
      trials[i][1] = i;
    }

    for(int i = 2; i <= floors; i++){
      for(int j = 2; j <= sheets; j++){
        trials[i][j] = Integer.MAX_VALUE;
        int temp;
        for(int x = 1; x <= i; x++){
          temp = Math.max(trials[x - 1][j - 1],trials[i - x][j]) + 1;
          trials[i][j] = Math.min(temp, trials[i][j]);
        }
      }
    }

    return trials[floors][sheets];
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();
      int[][] trials = new int[300][50];

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3, trials);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
