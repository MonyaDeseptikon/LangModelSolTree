package deseptikon.monya;

public class Test {
    public static void main(String[] args) {
        String resultSet = "Горьковская ж/д";
        System.out.println(resultSet.matches(".*[а-яА-Я].*"));
    }
}
