import java.util.ArrayList;

/**
 * Created by ayushjha on 4/11/18.
 */
public class NKvsUS {
    public static void main(String[] args){
        ArrayList<String> resultArray = new ArrayList<>();
        double oneC = 0;
        double oneD = 0;
        double twoD = 0;
        double twoC = 0;
        double threeD = 0;
        double threeC = 0;
        double fourC = 0;
        double fourD = 0;
        double fiveD = 0;
        double fiveC = 0;
        double sixD = 0;
        double sevenD = 0;
        double eightD = 0;
        double nineD = 0;
        double tenD = 0;
        double elevenD = 0;
        double twelveD = 0;
        double oneCTotal = 0;
        double oneDTotal = 0;
        double twoCTotal = 0;
        double twoDTotal = 0;
        double threeDTotal = 0;
        double threeCTotal = 0;
        double fourDTotal = 0;
        double fourCTotal = 0;
        double fiveDTotal = 0;
        double fiveCTotal = 0;
        double sixDTotal = 0;
        double sevenDTotal = 0;
        double eightDTotal = 0;
        double nineDTotal = 0;
        double tenDTotal = 0;
        double elevenDTotal = 0;
        double twelveDTotal = 0;
        int absD = 0;
        int absC = 0;
        String firstMove = null;
        String lastMove = null;
        double flips = 0;
        double usDefects = 0;
        int maxCoopPeriods = 0;
        int minCoopPeriods = Integer.MAX_VALUE;
        int maxDesPeriods = 0;
        int minDesPeriods = Integer.MAX_VALUE;
        double avgTech = 0.0;
        double coopTech = 0.0;
        double defcoops = 0.0;
        int count = 0;
        int count2 = 0;
        double avgcoops = 0.0;
        double avgcooperate = 0.0;
        double avgdefect = 0.0;
        double avgperiods = 0.0;
        double avgcoopperiods = 0.0;
        double avgdefectperiods = 0.0;
        for(int j = 0; j < 1000; j++){
            int cooperate = 0;
            int defect = 0;
        for(int i = 0; i < 1000; i++) {
            resultArray.clear();
            int periods = 0;
            double[] nkProbs = {0.5, 0.5}; //cooperate and defect
            double coopFac = 0.0;
            double techFac = 0.0;
            double numCoops = 0.0;
            String nkResp = null;
            String usResp = null;
            double nkRan = 0.0;
            while (nkProbs[0] <= 1.0 && nkProbs[1] <= 1.0) {
                periods++;
                if (periods == 1) {
                    String userResponse = null;
                    if(i%2 == 0) {
                        userResponse = "c";
                        absC++;
                    }
                    else{
                        userResponse = "d";
                        absD++;
                    }
                    resultArray.add(userResponse);
                    firstMove = userResponse;
                    lastMove = userResponse;
                    nkResp = userResponse;
                    if (userResponse.equals("c")) {
                        numCoops++;
                        coopFac = calcCoopFac(coopFac, true, numCoops);
                    } else {
                        coopFac = calcCoopFac(coopFac, false, numCoops);
                    }
                    techFac = calcTechFac(techFac, periods);
                    nkProbs[0] = nkProbs[0] + coopFac - techFac;
                    nkProbs[1] = nkProbs[1] - coopFac + techFac;
                    usResp = "C"; //in first period, either case, US will play cooperate
                } else {
                    double cdiff = nkProbs[0];
                    double ddiff = nkProbs[1];
                    nkRan = Math.random();
                    cdiff = Math.abs(nkRan - cdiff);
                    ddiff = Math.abs(nkRan - ddiff);
                    if(techFac >= 0.17){
                        usResp = "D";
                        lastMove = "d";
                        usDefects++;
                        absD++;
                        resultArray.add(lastMove);
                        break;
                    }
                    if (cdiff < ddiff) { //cdiff is closer to generated random value, so cooperate
                        nkResp = "c";
                        usResp = "C";
                        absC++;
                        numCoops++;
                        coopFac = calcCoopFac(coopFac, true, numCoops);
                    } else { //else defect
                        nkResp = "d";
                        usResp = "C";
                        absD++;
                        if (numCoops != 0) {
                            numCoops--;
                        }
                        coopFac = calcCoopFac(coopFac, false, numCoops);
                    }
                    lastMove = nkResp;
                    resultArray.add(lastMove);
                    techFac = calcTechFac(techFac, periods);
                    nkProbs[0] = nkProbs[0] + coopFac - techFac;
                    nkProbs[1] = nkProbs[1] - coopFac + techFac;
                }
            }
            if(nkProbs[0] >= 1){
                lastMove = "c";
            }
            else if(nkProbs[1] >= 1){
                lastMove = "d";
            }
            for(int k = 0; k < resultArray.size(); k++) {
                boolean done = false;
                if(k < resultArray.size()) {
                    if (resultArray.get(k).equals("c")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            oneC++;
                            done = true;
                        }
                        oneCTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+1 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("c") && resultArray.get(k + 1).equals("c")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            twoC++;
                            done = true;
                        }
                        twoCTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+2 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("c") && resultArray.get(k + 1).equals("c") && resultArray.get(k + 2).equals("c")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            threeC++;
                            done = true;
                        }
                        threeCTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+3 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("c") && resultArray.get(k + 1).equals("c") && resultArray.get(k + 2).equals("c") && resultArray.get(k + 3).equals("c")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            fourC++;
                            done = true;
                        }
                        fourCTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+4 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("c") && resultArray.get(k + 1).equals("c") && resultArray.get(k + 2).equals("c") && resultArray.get(k + 3).equals("c") && resultArray.get(k + 4).equals("c")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            fiveC++;
                            done = true;
                        }
                        fiveCTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }

            for(int k = 0; k < resultArray.size(); k++) {
                boolean done = false;
                if(k < resultArray.size()) {
                    if (resultArray.get(k).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            oneD++;
                            done = true;
                        }
                        oneDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }

            for(int k = 0; k < resultArray.size(); k++) {
                if(k+1 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            twoD++;
                            done = true;
                        }
                        twoDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }

            for(int k = 0; k < resultArray.size(); k++) {
                if(k+2 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            threeD++;
                            done = true;
                        }
                        threeDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }

            for(int k = 0; k < resultArray.size(); k++) {
                if(k+3 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            fourD++;
                            done = true;
                        }
                        fourDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+4 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            fiveD++;
                            done = true;
                        }
                        fiveDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+5 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d") && resultArray.get(k+5).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            sixD++;
                            done = true;
                        }
                        sixDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+6 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d") && resultArray.get(k+5).equals("d") && resultArray.get(k+6).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            sevenD++;
                            done = true;
                        }
                        sevenDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+7 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d") && resultArray.get(k+5).equals("d") && resultArray.get(k+6).equals("d") && resultArray.get(k+7).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            eightD++;
                            done = true;
                        }
                        eightDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+8 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d") && resultArray.get(k+5).equals("d") && resultArray.get(k+6).equals("d") && resultArray.get(k+7).equals("d") && resultArray.get(k+8).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            nineD++;
                            done = true;
                        }
                        nineDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            for(int k = 0; k < resultArray.size(); k++) {
                if(k+9 < resultArray.size()) {
                    boolean done = false;
                    if (resultArray.get(k).equals("d") && resultArray.get(k + 1).equals("d") && resultArray.get(k + 2).equals("d") && resultArray.get(k + 3).equals("d") && resultArray.get(k+4).equals("d") && resultArray.get(k+5).equals("d") && resultArray.get(k+6).equals("d") && resultArray.get(k+7).equals("d") && resultArray.get(k+8).equals("d") && resultArray.get(k+9).equals("d")) {
                        if (resultArray.get(k).equals(lastMove)) {
                            tenD++;
                            done = true;
                        }
                        tenDTotal++;
                        if(done){
                            break;
                        }
                    }
                }
            }
            if(!firstMove.equals(lastMove)){
                flips++;
            }
            if (nkProbs[0] >= 1 && techFac < .20) {
                coopTech += techFac;
                cooperate++;
                avgcoops += numCoops;
                count++;
                avgcoopperiods += periods;
                if(periods > maxCoopPeriods){
                    maxCoopPeriods = periods;
                }
                if(periods < minCoopPeriods){
                    minCoopPeriods = periods;
                }
            } else {
                avgTech += techFac;
                defect++;
                defcoops += numCoops;
                count2++;
                avgdefectperiods+= periods;
                if(periods > maxDesPeriods){
                    maxDesPeriods = periods;
                }
                if(periods < minDesPeriods) {
                    minDesPeriods = periods;
                }
            }
            avgperiods += periods;
        }
            avgcooperate += cooperate;
            avgdefect += defect;
    }
        coopTech = coopTech/count;
        avgcooperate= avgcooperate/10000;
        avgdefect = avgdefect/10000;
        avgperiods = avgperiods/1000000;
        avgcoops = avgcoops/count;
        defcoops = defcoops/count2;
        avgTech = avgTech/(count2);
        avgcoopperiods = avgcoopperiods/count;
        avgdefectperiods = avgdefectperiods/count2;
        flips = flips/10000;
        usDefects = usDefects/10000;
        maxCoopPeriods -=1;
        maxDesPeriods -=1;
        System.out.println("Cooperate: " + avgcooperate + " (took an average of " + avgcoops + " Net Cooperates per Game) at tech level " + coopTech +
                         "\nDefect: " + avgdefect + " (took an average of " + defcoops + " Net Cooperates per Game) at tech level " + avgTech);
        System.out.println("Average Periods: " + avgperiods);
        System.out.println("Took an Average of " + avgcoopperiods + " periods for cooperate and " +  avgdefectperiods + " periods for defect");
        System.out.println("Min Coop Periods: " +minCoopPeriods + ", Max Coop Periods " +maxCoopPeriods);
        System.out.println("Min Destruction Periods: " +minDesPeriods + ", Max Destruction Periods " +maxDesPeriods);
        System.out.println(flips + " Percent of games had final outcomes different than their initial outcomes");
        System.out.println("Destructive outcome occurred " + usDefects + " Percent of the time due to US Defecting");
        System.out.println("Total number of Cooperates : " + absC + ", Total number of Defects : " + absD);
        System.out.println(oneC/oneCTotal);
        System.out.println(twoC/twoCTotal);
        System.out.println(threeC/threeCTotal);
        System.out.println(fourC/fourCTotal);
        System.out.println(fiveC/fiveCTotal);
        System.out.println(oneD/oneDTotal);
        System.out.println(twoD/twoDTotal);
        System.out.println(threeD/threeDTotal);
        System.out.println(fourD/fourDTotal);
        System.out.println(fiveD/fiveDTotal);
        System.out.println(sixD/sixDTotal);
        System.out.println(sevenD/sevenDTotal);
        System.out.println(eightD/eightDTotal);
        System.out.println(nineD/nineDTotal);
        //System.out.println(tenD/tenDTotal);
    }

    public static double calcCoopFac(double coopFac, boolean coop, double numCoops){
        double temp = Math.pow(Math.E, numCoops)/100;
        if(coop){
            coopFac = temp;
        }
        else{
            double temp2 = Math.pow(Math.E, numCoops+1)/100;
            temp = temp2-temp;
            coopFac =  -temp;
        }
        return coopFac;
    }

    public static double calcTechFac(double techFac, int periods) {
        if(periods % 8 == 0) {
            techFac += .04;
        }
        if(periods % 2 == 0) {
            techFac += .01;
        }
        return techFac;
    }
}

