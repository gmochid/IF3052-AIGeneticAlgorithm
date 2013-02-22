package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gmochid
 */
public class Arrangement {
    public Arrangement(Integer days) {
        this(days, new Integer(new Random().nextInt(5) + 1).toString().charAt(0));
    }

    public Arrangement(Integer days, char x) {
        mDays = days;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (mDays * 10); i++) {
            builder.append(x);
        }
        mArrangement = builder.toString();
    }
    
    public Arrangement(String arrangement) {
        mArrangement = arrangement;
        mDays = mArrangement.length() / 10;
    }

    public void validate() {
        resetAll();
        char[] builder = new char[mDays * 10];
        for (int i = 0; i < builder.length; i++) {
            builder[i] = '0';
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mDays; i++) {
            for (int j = 0; j < 10; j++) {
                int pos = i*10 + j;

                Integer cewekID = (int) mArrangement.charAt(pos) - (int) '0';
                if(cewekID == 0) {
                    mAvailable[i]++;
                    sb.append('0');
                } else {
                    if(Iboy.getActiveIboy().isCewekDateable(cewekID, pos)) {
                        ArrayList<Barang> prereq = Cewek.getCewek(cewekID).getPrerequisite();

                        // simulate purchasing barang
                        resetSimulation();
                        Iboy.getActiveIboy().resetSimulation();
                        int len = prereq.size();
                        int k = 0;
                        for (Barang b: prereq) {
                            for (; k < pos; k++) {
  //                              System.out.print(k);
                                if(Iboy.getActiveIboy().isUangSAvailable(b.getHarga(), k)
                                        &&
                                        b.isPurchaseable(k/10)
                                        &&
                                        (mAvailableS[k/10] > 0)
                                        &&
                                        (builder[k] == '0')) {
//                                    System.out.println();
                                    Iboy.getActiveIboy().useUangS(b.getHarga(), k);
                                    len--;
                                    k++;
                                    mAvailableS[k/10]--;
                                    break;
                                }
                            }
                        }

                        if(len == 0) {
                            // purchasing barang
                            k = 0;
                            for (Barang b: prereq) {
                                for (; k < pos; k++) {
                                    if(Iboy.getActiveIboy().isUangAvailable(b.getHarga(), k)
                                            &&
                                            b.isPurchaseable(k/10)
                                            &&
                                            (mAvailable[k/10] > 0)
                                            &&
                                            (builder[k] == '0')
                                            ) {
                                        Iboy.getActiveIboy().useUang(b.getHarga(), k);
                                        builder[k] = b.getBarangID();
                                        b.purchase(k / 10);
                                        k++;
                                        mAvailable[k/10]--;
                                        break;
                                    }
                                }
                            }

                            Iboy.getActiveIboy().dateCewek(cewekID, pos);
                            builder[pos] = (char) (cewekID + (int) '0');
                            sb.append(cewekID);

                            
                        } else {
                            mAvailable[i]++;
                            sb.append('0');
                        }
                    } else {
                        mAvailable[i]++;
                        sb.append('0');
                    }
                }
                //for (int z = 0; z < mDays; z++) { System.out.print(mAvailable[z] + " ");}
                //for (int z = 0; z < mDays; z++) {   Iboy.getActiveIboy().printDays(z);     }
                //System.out.println(pos);
            }
            
            //Iboy.getActiveIboy().printDays(7);
            nextDay();
        }
        
        
        mArrangement = sb.toString();
    }
    
    public Integer calculateTotalEnlightenment(){
        int total = 0;
        int key;
        for (int i = 0; i < mArrangement.length(); i++){
            key = mArrangement.charAt(i) - (int)'0';
            if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                total += Cewek.getCewek(key).getEnlightenment();
            }
        }
        return total;
    }
    
    public void crossOver(Arrangement a){
        int cuttingValue1 = (int)(this.calculateTotalEnlightenment() * crossFactor);
        int cuttingValue2 = (int)(a.calculateTotalEnlightenment() * crossFactor);
        int subtotal = 0;
        int i = 0;
        int key;
        String tail1, tail2;
        
        if (cuttingValue1 >= cuttingValue2){
            while ((i < this.mArrangement.length()) && (subtotal <= cuttingValue1)){
                key = this.mArrangement.charAt(i) - (int)'0';
                if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                    subtotal += Cewek.getCewek(key).getEnlightenment();
                }
                i++;
            }
        } else {
            while ((i < a.mArrangement.length()) && (subtotal <= cuttingValue1)){
                key = a.mArrangement.charAt(i) - (int)'0';
                if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                    subtotal += Cewek.getCewek(key).getEnlightenment();
                }
                i++;
            }
        }
        tail1 = this.mArrangement.substring(i);
        tail2 = a.mArrangement.substring(i);
        /* Swapping */
        this.mArrangement = this.mArrangement.substring(0, i) + tail2;
        a.mArrangement = a.mArrangement.substring(0, i) + tail1;
    }

    private void resetSimulation() {
        mAvailableS = new int[mDays];
        System.arraycopy(mAvailable, 0, mAvailableS, 0, mDays);
    }

    private void resetAll() {
        mAvailable = new int[mDays];
        for (int i = 0; i < mDays; i++) {
            mAvailable[i] = 0;
        }

        Iboy.getActiveIboy().reset();
        for (int i = 1; i <= Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).reset();
        }
        for (int i = 0; i < Barang.getTotalBarang(); i++) {
            Barang.getBarang(i).reset();
        }
    }

    private void nextDay() {
        Iboy.getActiveIboy().nextDay();
        for (int i = 1; i <= Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).reset();
        }
        for (int i = 0; i < Barang.getTotalBarang(); i++) {
            Barang.getBarang(0).reset();
        }
    }

    public String getFinalArrangement() {
        resetAll();
        char[] builder = new char[mDays * 10];

        for (int i = 0; i < mDays; i++) {

            for (int j = 0; j < 10; j++) {
                int pos = i*10 + j;

                Integer cewekID = (int) mArrangement.charAt(pos) - (int) '0';
                if(cewekID == 0) {
                    mAvailable[i]++;
                    builder[pos] = '0';
                } else {
                    if(Iboy.getActiveIboy().isCewekDateable(cewekID, pos)) {
                        ArrayList<Barang> prereq = Cewek.getCewek(cewekID).getPrerequisite();

                        // simulate purchasing barang
                        resetSimulation();
                        Iboy.getActiveIboy().resetSimulation();
                        int len = prereq.size();
                        int k = 0;
                        for (Barang b: prereq) {
                            for (; k < pos; k++) {
                                if(Iboy.getActiveIboy().isUangSAvailable(b.getHarga(), k)
                                        &&
                                        b.isPurchaseable(k/10)
                                        &&
                                        (mAvailableS[k/10] > 0)
                                        &&
                                        (builder[k] == '0')) {
                                    Iboy.getActiveIboy().useUangS(b.getHarga(), k);
                                    len--;
                                    k++;
                                    mAvailableS[k/10]--;
                                    break;
                                }
                            }
                        }

                        if(len == 0) {
                            // purchasing barang
                            k = 0;
                            for (Barang b: prereq) {
                                for (; k < pos; k++) {
                                    if(Iboy.getActiveIboy().isUangAvailable(b.getHarga(), k)
                                            &&
                                            b.isPurchaseable(k/10)
                                            &&
                                            (mAvailable[k/10] > 0)
                                            &&
                                            (builder[k] == '0')
                                            ) {
                                        Iboy.getActiveIboy().useUang(b.getHarga(), k);
                                        builder[k] = b.getBarangID();
                                        b.purchase(k / 10);
                                        k++;
                                        mAvailable[k/10]--;
                                        break;
                                    }
                                }
                            }

                            Iboy.getActiveIboy().dateCewek(cewekID, pos);
                            builder[pos] = (char) (cewekID + (int) '0');
                        } else {
                            mAvailable[i]++;
                            builder[pos] = '0';
                        }
                    } else {
                        mAvailable[i]++;
                        builder[pos] = '0';
                    }
                }
            }
            nextDay();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < builder.length; i++) {
            sb.append(builder[i]);
        }
        //mArrangement = sb.toString();
        return sb.toString();
    }

    public String getArrangement() {
        return mArrangement;
    }

    private int[] mAvailable;
    private int[] mAvailableS;
    private Integer mDays;
    private String mArrangement;
    private static final double crossFactor = 0.5;
}
