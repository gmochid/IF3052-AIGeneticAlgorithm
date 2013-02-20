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
                int pos = i*10 + j;
                Integer cewekID = Integer.valueOf(mArrangement.substring(pos, pos));
                if(cewekID == 0) {
                    mAvailable[i]++;
                    builder.append('0');
                } else {
                    if(Iboy.getActiveIboy().isCewekDateable(cewekID, pos)) {
                        Iboy.getActiveIboy().dateCewek(cewekID, pos);
                        /*
                         * TODO : Susun2 jadwal membeli barang
                         */
                        builder.append(cewekID);
                    } else {
                        mAvailable[i]++;
                        builder.append('0');
                    }
                }
            }
            Iboy.getActiveIboy().nextDay();
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
        Iboy.getActiveIboy().nextDay();
        for (int i = 0; i < Cewek.getTotalCewek(); i++) {
            Cewek.getCewek(i).reset();
        }
        for (int i = 0; i < Barang.getTotalBarang(); i++) {
            Barang.getBarang(0).reset();
        }
    }

    private Integer[] mAvailable;
    private Integer mDays;
    private String mArrangement;
}
