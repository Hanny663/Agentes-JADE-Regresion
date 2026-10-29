import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SLRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("-> [INICIO] Agente " + getLocalName() + " iniciado.");
        addBehaviour(new SLRBehaviour());
    }

    private class SLRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            // 4.a: Inicializar dataset
            Sumatorias sum1 = new Sumatorias();
            Regresion reg = new Regresion();

            // 4.b: Calcular valores óptimos de beta0 y beta1
            double b0 = reg.calcularBeta0(sum1);
            double b1 = reg.calcularBeta1(sum1);

            // 4.c: Imprimir la ecuación de regresión simple
            System.out.println("\n=======================================================");
            System.out.println("   ECUACION DE REGRESION LINEAL SIMPLE (SLR)");
            System.out.println("=======================================================");
            System.out.printf("y = %.4f + (%.4f)x\n", b0, b1);
            System.out.printf("Machine Efficiency = (%.4f) + (%.4f)*BatchSize\n", b0, b1);
            System.out.println("-------------------------------------------------------");

            // 4.d y 4.e: Realizar e imprimir 3 predicciones
            double[] valoresPrueba = {40.0, 75.0, 120.0};
            System.out.println("Predicciones de Eficiencia:");
            for (double xVal : valoresPrueba) {
                double prediccion = reg.predict(xVal, b0, b1);
                System.out.printf("  Para Batch Size = %.1f  -->  Eficiencia Estimada = %.2f%%\n", xVal, prediccion);
            }
            System.out.println("-------------------------------------------------------");

            // 4.f: Calcular e imprimir R^2
            double r2 = reg.calcularRSquared(sum1, b0, b1);
            System.out.printf("Coeficiente de determinacion R^2: %.4f (Ajuste del %.2f%%)\n", r2, r2 * 100);
            System.out.println("=======================================================\n");

            // 5: El agente muere al terminar sus tareas
            myAgent.doDelete();
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("-> [FINALIZADO] Agente " + getLocalName() + " ha muerto tras concluir su ejecucion.");
    }
}