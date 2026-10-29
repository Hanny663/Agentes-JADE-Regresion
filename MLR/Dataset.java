import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Dataset {
    private double[][] x;
    private double[] y;

    public Dataset() {
        x = new double[0][3];
        y = new double[0];
    }

    public void cargarDatos(String archivo) throws Exception {
        ArrayList<double[]> datosX = new ArrayList<double[]>();
        ArrayList<Double> datosY = new ArrayList<Double>();

        BufferedReader lector = new BufferedReader(
            new FileReader(archivo)
        );

        try {
            String linea = lector.readLine();

            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] valores = linea.split(",");

                if (valores.length < 5) {
                    throw new Exception(
                        "Una fila del archivo tiene menos de 5 columnas."
                    );
                }

                double tv = Double.parseDouble(valores[1].trim());
                double radio = Double.parseDouble(valores[2].trim());
                double newspaper = Double.parseDouble(valores[3].trim());
                double sales = Double.parseDouble(valores[4].trim());

                datosX.add(new double[]{tv, radio, newspaper});
                datosY.add(sales);
            }
        } finally {
            lector.close();
        }

        x = new double[datosX.size()][3];
        y = new double[datosY.size()];

        for (int i = 0; i < datosX.size(); i++) {
            x[i] = datosX.get(i);
            y[i] = datosY.get(i);
        }

        if (y.length == 0) {
            throw new Exception("El archivo no contiene registros.");
        }
    }

    public double[][] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public int getNumeroRegistros() {
        return y.length;
    }
}