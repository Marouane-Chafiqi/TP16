package TP16;

public class CalculateurImpl implements Calculateur {

    @Override
    public int additionner(int a, int b) {
        return a + b;
    }

    @Override
    public int soustraire(int a, int b) {
        return a - b;
    }
}
