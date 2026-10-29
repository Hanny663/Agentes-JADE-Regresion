public class Sumatorias {

    int[] x = {108, 115, 106, 97, 95, 91, 97, 83, 83, 78, 54, 67, 56,
               53, 61, 115, 81, 78, 30, 45, 99, 32, 25, 28, 90, 89};

    int[] y = {95, 96, 95, 97, 93, 94, 95, 93, 92, 86, 73, 80, 65, 69,
               77, 96, 87, 89, 60, 63, 95, 61, 55, 56, 94, 93};

    public Sumatorias() {
    }

    public int cantidadX() {
        return x.length;
    }

    public int sumarX() {
        int suma = 0;
        for (int valor : x) {
            suma += valor;
        }
        return suma;
    }

    public int sumarY() {
        int suma = 0;
        for (int valor : y) {
            suma += valor;
        }
        return suma;
    }

    public int[] XCuadrada() {
        int[] resultado = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            resultado[i] = x[i] * x[i];
        }
        return resultado;
    }

    public int sumarXCuadrada() {
        int sumaTotal = 0;
        for (int valor : x) {
            sumaTotal += (valor * valor);
        }
        return sumaTotal;
    }

    public int sumarXPorY() {
        int sumaTotal = 0;
        for (int i = 0; i < x.length; i++) {
            sumaTotal += (x[i] * y[i]);
        }
        return sumaTotal;
    }
}