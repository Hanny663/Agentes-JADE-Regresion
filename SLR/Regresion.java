public class Regresion {

    public Regresion() {
    }

    public double calcularBeta1(Sumatorias datos) {
        double n = datos.x.length;
        double sumaX = datos.sumarX();
        double sumaY = datos.sumarY();
        double sumaXY = datos.sumarXPorY();
        double sumaX2 = datos.sumarXCuadrada();

        double numerador = (n * sumaXY) - (sumaX * sumaY);
        double denominador = (n * sumaX2) - (sumaX * sumaX);

        if (denominador == 0) {
            System.out.println("Error: El denominador es cero.");
            return 0;
        }

        return numerador / denominador;
    }

    public double calcularBeta0(Sumatorias datos) {
        double n = datos.x.length;
        double sumaX = datos.sumarX();
        double sumaY = datos.sumarY();
        double sumaXY = datos.sumarXPorY();
        double sumaX2 = datos.sumarXCuadrada();

        double numerador = (sumaY * sumaX2) - (sumaX * sumaXY);
        double denominador = (n * sumaX2) - (sumaX * sumaX);

        if (denominador == 0) {
            System.out.println("Error: El denominador es cero.");
            return 0;
        }

        return numerador / denominador;
    }

    public double predict(double x, double beta0, double beta1) {
        return beta0 + (beta1 * x);
    }

    public double calcularRSquared(Sumatorias datos, double beta0, double beta1) {
        double sumaY = datos.sumarY();
        double mediaY = sumaY / datos.y.length;

        double ssTot = 0;
        double ssRes = 0;

        for (int i = 0; i < datos.y.length; i++) {
            double yReal = datos.y[i];
            double yPred = predict(datos.x[i], beta0, beta1);

            ssRes += Math.pow(yReal - yPred, 2);
            ssTot += Math.pow(yReal - mediaY, 2);
        }

        return 1.0 - (ssRes / ssTot);
    }
}