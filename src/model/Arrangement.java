package model;

/**
 *
 * @author gmochid
 */
public class Arrangement {
    public Arrangement(Integer time) {
        mDays = time;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (mDays * 7); i++) {
            builder.append('0');
        }
        mArrangement = builder.toString();
    }
    
    public Arrangement(String arrangement) {
        mArrangement = arrangement;
        mDays = mArrangement.length() / 7;
    }

    public Integer validate() {
        resetAll();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mDays; i++) {
            for (int j = 0; j < 10; j++) {
                int pos = i*7 + j;
                Integer cewekID = Integer.valueOf(mArrangement.substring(pos, pos));
                if(cewekID == 0) {
                    mAvailable[i]++;
                    builder.append('0');
                } else {
                    if(Iboy.getActiveIboy().isCewekDateable(cewekID, pos)) {
                        Barang[] prereq = Cewek.getCewek(cewekID).getPrerequisite();

                        // simulate purchasing barang
                        Iboy.getActiveIboy().resetSimulation();
                        int len = prereq.length;
                        for (Barang b: prereq) {
                            for (int k = 0; k < pos; k++) {
                                if(Iboy.getActiveIboy().isUangSAvailable(b.getHarga(), k)
                                        &&
                                        b.isPurchaseable(k / 10)) {
                                    Iboy.getActiveIboy().useUangS(b.getHarga(), k);
                                    len--;
                                    break;
                                }
                            }
                        }

                        if(len == 0) {
                            // purchasing barang
                            for (Barang b: prereq) {
                                for (int k = 0; k < pos; k++) {
                                    if(Iboy.getActiveIboy().isUangAvailable(b.getHarga(), k)
                                            &&
                                            b.isPurchaseable(k / 10)) {
                                        Iboy.getActiveIboy().useUang(b.getHarga(), k);
                                        b.purchase(k / 10);
                                        break;
                                    }
                                }
                            }

                            Iboy.getActiveIboy().dateCewek(cewekID, pos);
                            builder.append(cewekID);
                        } else {
                            builder.append('0');
                        }
                    } else {
                        mAvailable[i]++;
                        builder.append('0');
                    }
                }
            }
            nextDay();
        }
        mArrangement = builder.toString();
        return 0;
    }

    private void resetAll() {
        mAvailable = new Integer[mDays];
        for (int i = 0; i < mDays; i++) {
            mAvailable[i] = 0;
        }

        Iboy.getActiveIboy().reset();
        for (int i = 0; i < Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).reset();
        }
        for (int i = 0; i < Barang.getTotalBarang(); i++) {
            Barang.getBarang(0).reset();
        }
    }

    private void nextDay() {
        Iboy.getActiveIboy().reset();
        for (int i = 0; i < Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).reset();
        }
        for (int i = 0; i < Barang.getTotalBarang(); i++) {
            Barang.getBarang(0).reset();
        }
    }
    
    public Integer calculateTotalEnlightenment(){
        int total = 0;
        int key;
        for (int i = 0; i < mArrangement.length(); i++){
            key = mArrangement.charAt(i) - (int)'A' + 1;
            if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                total += Cewek.getCewek(key).getEnlightenment();
            }
        }
        return total;
    }
    
    private void crossOver(Arrangement a){
        int cuttingValue1 = (int)(this.calculateTotalEnlightenment() * crossFactor);
        int cuttingValue2 = (int)(a.calculateTotalEnlightenment() * crossFactor);
        int subtotal = 0;
        int i = 0;
        int key;
        boolean flag = false; 
        String tail1, tail2;
        if (cuttingValue1 >= cuttingValue2){
            while ((i < this.mArrangement.length()) && (i % 10 == 0) && !flag){
                key = this.mArrangement.charAt(i) - (int)'A' + 1;
                if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                    subtotal += Cewek.getCewek(key).getEnlightenment();
                    if (subtotal >= cuttingValue1){
                        flag = true;
                    }
                }
                i++;
            }
        } else {
            while ((i < a.mArrangement.length()) && (i % 10 == 0) && !flag){
                key = a.mArrangement.charAt(i) - (int)'A' + 1;
                if ((key >= 1) && (key <= Cewek.getTotalCewek())){
                    subtotal += Cewek.getCewek(key).getEnlightenment();
                    if (subtotal >= cuttingValue2){
                        flag = true;
                    }
                }
                i++;
            }
        }
        tail1 = this.mArrangement.substring(i, this.mArrangement.length()-1);
        tail2 = a.mArrangement.substring(i, a.mArrangement.length()-1);
        /* Swapping */
        this.mArrangement = this.mArrangement.substring(0, i-1) + tail2;
        a.mArrangement = a.mArrangement.substring(0, i-1) + tail1;
    }

    private Integer[] mAvailable;
    private Integer mDays;
    private String mArrangement;
    private static final double crossFactor = 0.5;
}
