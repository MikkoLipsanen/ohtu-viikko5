
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int ALOITUSKOKO = 5; 
    public final static int OLETUSLISAYS = 5;  
   
    private int lisays;    
    private int[] luvut;      
    private int alkioidenLkm;     

    public IntJoukko() {
        luvut = new int[ALOITUSKOKO];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.lisays = OLETUSLISAYS;
    }

    public IntJoukko(int koko) {
        if (koko < 0) {
            throw new IndexOutOfBoundsException("Aloituskoko väärin");
        }
        luvut = new int[koko];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.lisays = OLETUSLISAYS;
    }
       
    public IntJoukko(int koko, int lisays) {
        if (koko < 0) {
            throw new IndexOutOfBoundsException("Aloituskoko väärin");
        }
        if (lisays < 0) {
            throw new IndexOutOfBoundsException("Lisäys väärin");
        }
        luvut = new int[koko];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.lisays = lisays;

    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            luvut[0] = luku;
            alkioidenLkm++;
            return true;
        } 
        if (!lukuTaulukossa(luku)) {
            lisaaUusiLuku(luku);
            return true;
        }
        return false;
    }

    public boolean lisaaUusiLuku(int luku){
        luvut[alkioidenLkm] = luku;
        alkioidenLkm++;
        
        if (alkioidenLkm % luvut.length == 0) {
            kasvataTaulukkoa();
        }
        return true;
    }
    
    public boolean kasvataTaulukkoa(){
        int[] apuTaulukko;
        apuTaulukko = luvut;
        kopioiTaulukko(luvut, apuTaulukko);
        luvut = new int[alkioidenLkm + lisays];
        kopioiTaulukko(apuTaulukko, luvut);
        return true;
    }
    
    public boolean lukuTaulukossa(int luku) {
        int lukuTaulukossa = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                lukuTaulukossa++;
            }
        }
        if (lukuTaulukossa > 0) {
            return true;
        } 
        return false;
        }
    

    public boolean poista(int luku) {
        int poistoKohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                poistoKohta = i; 
                luvut[poistoKohta] = 0;
                break;
            }
        }
        if (poistoKohta != -1) {
            lyhennaTaulukko(poistoKohta);
            return true;
        }
        return false;
    }
    
    public boolean lyhennaTaulukko(int poistoKohta){
        int apu;
        for (int j = poistoKohta; j < alkioidenLkm - 1; j++) {
            apu = luvut[j];
            luvut[j] = luvut[j + 1];
            luvut[j + 1] = apu;
        }
        alkioidenLkm--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + luvut[0] + "}";
            default:
                String tuotos = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuotos += luvut[i] + ", ";
                }
                tuotos += luvut[alkioidenLkm - 1] + "}";
                return tuotos;
        }
    }

    public int[] luoTaulukko() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.luoTaulukko();
        int[] bTaulu = b.luoTaulukko();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.luoTaulukko();
        int[] bTaulu = b.luoTaulukko();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.luoTaulukko();
        int[] bTaulu = b.luoTaulukko();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
 
        return z;
    }
        
}