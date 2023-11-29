import java.util.Random;

public class GaussRandom {
    private Random random;
    private boolean hasNextGauss;
    private double nextGauss;

    public GaussRandom() {
        this.random = new Random();
        this.hasNextGauss = false;
    }

    public GaussRandom(long seed) {
        this.random = new Random(seed);
        this.hasNextGauss = false;
    }

    //стандартная функция Random nextGaussian() генерирует гаусово распределение
    //со средним 0.0 и отклонением 1.0 по формуле второго варианта преобразования Бокса-Мюллера
    public double nextGaussian() {
        if (!hasNextGauss) {
            hasNextGauss = true;
            // r и phi - независимые случайные величины, равномерно распределённые на интервале (0,1]
            double r = random.nextDouble();
            double phi = random.nextDouble();
            double sqrt = Math.sqrt(-2 * Math.log(r));

            //по формуле первого варианта преобразования Бокса-Мюллера
            nextGauss = 2 * Math.sin(2 * Math.PI * phi) * sqrt;
            return 2 * Math.cos(2 * Math.PI * phi) * sqrt;
        }
        hasNextGauss = false;
        return nextGauss;
    }

    //После получения стандартной нормальной случайной величины можно легко перейти к величине
    //распределённой нормально с математическим ожиданием m и стандартным отклонением sigma
    public double nextGaussian(double m, double sigma) {
        return m + nextGaussian() * sigma;
    }
}
