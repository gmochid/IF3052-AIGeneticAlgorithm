package model;

import java.util.ArrayList;
import java.util.Random;
import log.Logger;

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
                                        b.isPurchaseable(i)
                                        &&
                                        (mAvailableS[i] > 0)
                                        &&
                                        (builder[k] == '0')) {
//                                    System.out.println();
                                    Iboy.getActiveIboy().useUangS(b.getHarga(), k);
                                    len--;
                                    k++;
                                    mAvailableS[i]--;
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
                                            b.isPurchaseable(i)
                                            &&
                                            (mAvailable[i] > 0)
                                            &&
                                            (builder[k] == '0')
                                            ) {
                                        Iboy.getActiveIboy().useUang(b.getHarga(), k);
                                        builder[k] = b.getBarangID();
                                        b.purchase(k / 10);
                                        k++;
                                        mAvailable[i]--;
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
    
<<<<<<< HEAD
    public Integer crossOver(Arrangement a){
=======
    public void crossOver(Arrangement a){
        Logger.log2(mArrangement);
        Logger.log2("                                              X");
        Logger.log2(a.mArrangement);
        
>>>>>>> origin/gici
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
        
        String lls = "";
        for (int iii = 0; iii < i - 1; iii++) {
            lls += " ";
        }
        lls += "|| (" + i + ")";
        Logger.log2(lls);
        Logger.log2("");
        
        tail1 = this.mArrangement.substring(i);
        tail2 = a.mArrangement.substring(i);
        /* Swapping */
        this.mArrangement = this.mArrangement.substring(0, i) + tail2;
        a.mArrangement = a.mArrangement.substring(0, i) + tail1;
        
        return i;
    }
    
    public void mutation(Integer crossingPoint){
        /*STRATEGY :
         *  1. MODIFY FROM CROSSING POINT TO REPLACE/OMIT ALL UNFEASIBLE DATING CAUSED BY LACK OF ENERGY
         *      a. BY FETCHING IT TO OTHER CANDIDATES WITH LOW ENERGY REQUIREMENT
         *      b. BY SETTING IT TO IDLE IF IT DIDN'T WORK
         *      c. BY RESETTING ALL BUYING PREREQUISITES ACTIVITIES TO IDLE
         *  2. MODIFY FROM CROSSING POINT TO REBUY ALL THE PREREQUISITES
         *      a. BY BUYING THE NOW-REQUIRED PREREQUISITES
         *  3. MODIFY FROM CROSSING POINT TO OMIT ALL UNFEASIBLE DATING CAUSED BY LACK OF PREREQUISITES NEEDED
         *      a. BY OMITING ALL THE CANDIDATES THAT HAVEN'T GOT THEIR REQUIREMENT READY
         */
        Character karakter;
        Integer cEnergy, cEnergyS;
        Integer cMoney, cMoneyS;
        ArrayList<Barang> items = new ArrayList<Barang>();
        ArrayList<Barang> itemsToBuy = new ArrayList<Barang>();
        int keyCewek;
        String mArrangement2, mArrangement3;
        
        /*
         * cari tahu uang sekarang, mulai iterasi dari awal jadwal
         */
        cMoney = 200000;
        for (int i = 0; i < crossingPoint; i++){
            if ((i > 0) && (i % 10 == 0)){  //ganti hari
                cMoney += 50000;
            }
            karakter = this.mArrangement.charAt(i);
            if (karakter - (int)'A' < 0){    //jika nilainya 0-9 (suatu cewek)
                //do nothing
            } else {                         //jika nilainya A-Z (suatu item)    
                cMoney -= Barang.getBarang(karakter).getHarga();
                items.add(Barang.getBarang(karakter));
            }
        }
        System.out.println("Hingga crosspoint, uang yang tersisa: "+cMoney);
        
        /*
         * cari tahu energi sekarang, mulai iterasi dari awal hari terdekat 
         * saat itu energy direset 100, jadi tidak perlu dari awal jadwal
         */
        cEnergy = 100;
        for (int i = (int)(crossingPoint / 10) * 10; i < crossingPoint; i++){
            karakter = this.mArrangement.charAt(i);
            if (karakter == '0'){
                //do nothing
            } else if (karakter - (int)'A' < 0){    //jika nilainya 0-9 (suatu cewek)
                cEnergy -= Cewek.getCewek(karakter - (int)'0').getEnergiHabis();                            //kurangi energi sesuai cewek
                for (int j = 0; j < Cewek.getCewek(karakter - (int)'0').getPrerequisite().size(); j++){     //remove item prereq cewek
                    items.remove(Cewek.getCewek(karakter - (int)'0').getPrerequisite().get(j));
                }
            } else {                                //jika nilainya A-Z (suatu item)    
                //do nothing    
            }
        }
        System.out.println("Hingga crosspoint, energi yang tersisa: "+cEnergy);
        System.out.print("Hingga crosspoint, barang yang tersisa: ");
        for (Barang a : items){
            System.out.print(a.getBarangID()+" ");
        }
        
        /*
         * pada state ini telah didapat :
         * - energi sekarang (cEnergy), 
         * - uang sekarang (cMoney), 
         * - daftar item yang masih dipunyai (items)
         */
        cEnergyS = cEnergy;     //simpan dulu nilainya untuk simulasi
        cMoneyS = cMoney;       //simpan dulu nilainya untuk simulasi
        //cari cewek penyerap energi terendah di antara semua kandidat
        int minSucker = 1; 
        for (int m = 1; m < Cewek.getTotalCewek(); m++){
            if (Cewek.getCewek(m).getEnergiHabis() < Cewek.getCewek(m+1).getEnergiHabis()){
                minSucker = m;
            } else {
                minSucker = m+1;
            }
        }
        System.out.println("Cewek dengan energi paling rendah: "+Cewek.getCewek(minSucker).getCewekID());
            
        //FIRST ATTEMPT : MODIFY CEWEK YANG AKAN DIAPEL
        StringBuilder sbArrangement = new StringBuilder(this.mArrangement);
        for (int i = crossingPoint; i < this.mArrangement.length(); i++){
            if ((i > 0) && (i % 10 == 0)){  //bila ganti hari
                cEnergyS = 100;
            }
            
            karakter = this.mArrangement.charAt(i);
            keyCewek = karakter - (int)'0';
            
            if ((karakter - (int)'A' < 0) && (keyCewek != 0)){   //jika nilainya 1-9 (suatu cewek)
                if (cEnergyS < Cewek.getCewek(keyCewek).getEnergiHabis()){      //tidak bisa nge-date cewek ini karena alasan energy
                    if (cEnergyS < Cewek.getCewek(minSucker).getEnergiHabis()){ //tidak bisa nge-date cewek penyerap energy terendah juga, ckck
                        sbArrangement.setCharAt(i, '0');                        //batal dating
                    } else {
                        sbArrangement.setCharAt(i, (char)(minSucker + 48));       //ubah cewekID nya jadi cewek penyerap energi terendah
                        //kumpulkan prerequisite cewek ini kalau belum ada
                        for (int j = 0; j < Cewek.getCewek(minSucker).getPrerequisite().size(); j++){
                            if (!items.contains(Cewek.getCewek(minSucker).getPrerequisite().get(j))){
                                itemsToBuy.add(Cewek.getCewek(minSucker).getPrerequisite().get(j)); //masukkan ke daftar yang harus dibeli
                            }
                        }
                        cEnergyS -= Cewek.getCewek(minSucker).getEnergiHabis(); //kurangi energi sekarang
                    }
                } else {    
                    //energinya cukup buat nge date, sekarang kumpulkan prerequisite cewek ini kalau belum ada
                    for (int j = 0; j < Cewek.getCewek(keyCewek).getPrerequisite().size(); j++){
                        if (!items.contains(Cewek.getCewek(keyCewek).getPrerequisite().get(j))){
                            itemsToBuy.add(Cewek.getCewek(keyCewek).getPrerequisite().get(j));  //masukkan ke daftar yang harus dibeli
                        }
                    }
                    cEnergyS -= Cewek.getCewek(keyCewek).getEnergiHabis(); //kurangi energi sekarang
                }
            } else {    //jika nilainya A-Z (suatu item)    
                sbArrangement.setCharAt(i, '0');    //aktivitas membeli barang diubah menjadi aktivitas idle    
            }
        }
        //setelah selesai dimodifikasi mArrangement nya sampai karakter terakhir
        mArrangement2 = sbArrangement.toString();
        System.out.println("Hasil first attempt: "+mArrangement2);
        
        //SECOND ATTEMPT : BELI BARANG2 UNTUK SUSUNAN CEWEK BARU
        StringBuilder sbArrangement2 = new StringBuilder(mArrangement2);
        int idx = 0;
        while (idx < itemsToBuy.size()){                                    //selama masih ada barang yang perlu dibeli
            for (int i = crossingPoint; i < mArrangement2.length(); i++){   //lakukan iterasi jam
                if ((i > 0) && (i % 10 == 0)){                              //jika ganti hari
                    cMoneyS += 50000; 
                    for (int j = 0; j < Barang.getTotalBarang(); j++){      //restock semua barang
                        Barang.getBarang(j).reset();
                    }
                }
                //mau beli barang sesuai aturan FIFO
                if ((mArrangement2.charAt(i) == '0') && (itemsToBuy.get(idx).isPurchaseable(i%10))){    //jika ada yang kosong dan barang bisa dibeli
                    if (cMoneyS < Barang.getBarang(itemsToBuy.get(idx).getBarangID()).getHarga()){  //barang tidak bisa dibeli
                        //do nothing
                    } else {
                        sbArrangement2.setCharAt(i, itemsToBuy.get(idx).getBarangID());                  //set karakternya 
                        itemsToBuy.get(idx).purchase(i%10);
                        cMoneyS -= Barang.getBarang(itemsToBuy.get(idx).getBarangID()).getHarga();
                        idx++;
                    }
                } else {    //entah dia lagi ga nganggur atau barangnya belum bisa dibeli
                    //do nothing
                }
            }
        }
        mArrangement3 = sbArrangement2.toString();
        System.out.println("Hasil second attempt: "+mArrangement3);
        
        //THIRD ATTEMPT : CEK ULANG APA SAAT NGAPEL CEWEK BARANG SUDAH ADA
        StringBuilder sbArrangement3 = new StringBuilder(mArrangement3);
        boolean isPrerequisiteExist;
        for (int i = crossingPoint; i < mArrangement3.length(); i++){
            if ((i > 0) && (i % 10 == 0)){  //bila ganti hari
                cMoney += 50000;
                cEnergy = 100;
                Cewek.resetAll();               //reset mCurrent waktu semua cewek
            }
            
            karakter = this.mArrangement.charAt(i);
            keyCewek = karakter - (int)'0';
            isPrerequisiteExist = true;
                    
            if ((karakter - (int)'A' < 0) && (keyCewek != 0)){   //jika nilainya 0-9 (suatu cewek)
                for (int j = 0; j < Cewek.getCewek(keyCewek).getPrerequisite().size(); j++){
                    if (!items.contains(Cewek.getCewek(keyCewek).getPrerequisite().get(j))){    
                        isPrerequisiteExist = false;    //bila ada barang yang belum tersedia
                    }
                }
                if (!isPrerequisiteExist){   
                    //prerequisite belum ada, kemungkinan karena baru mau dibeli sesudah apel
                    //artinya tidak ada cukup waktu luang untuk beli sebelum ngapel cewek nya tadi
                    //kebelinya sesudah apel
                    sbArrangement3.setCharAt(i, '0');   //batalkan dating
                } else {
                    if (Cewek.getCewek(keyCewek).isDateable(i)){    //cek seandainya dia mCurrentWaktu nya sudah habis
                       Cewek.getCewek(keyCewek).dateIboy();         //kurangi mCurrentWaktu
                       cEnergy -= Cewek.getCewek(keyCewek).getEnergiHabis();
                    } else {
                       sbArrangement3.setCharAt(i, '0');            //batalkan dating
                    }
                }
            } else {    //jika nilainya A-Z (suatu item)
                //do nothing
                //barang yang sudah terlanjur dibeli biarkan saja, siapa tahu bisa buat yang lain
            }
        }
        mArrangement = sbArrangement3.toString();
        System.out.println("Hasil third attempt: "+mArrangement);
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
                                        b.isPurchaseable(i)
                                        &&
                                        (mAvailableS[i] > 0)
                                        &&
                                        (builder[k] == '0')) {
                                    Iboy.getActiveIboy().useUangS(b.getHarga(), k);
                                    len--;
                                    k++;
                                    mAvailableS[i]--;
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
                                            b.isPurchaseable(i)
                                            &&
                                            (mAvailable[i] > 0)
                                            &&
                                            (builder[k] == '0')
                                            ) {
                                        Iboy.getActiveIboy().useUang(b.getHarga(), k);
                                        builder[k] = b.getBarangID();
                                        b.purchase(k / 10);
                                        k++;
                                        mAvailable[i]--;
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
