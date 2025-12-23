package deseptikon.monya;

public class Fruits {
    public static void main(String[] args) {
        int quantityWatermelon; //количество арбузов
        int quantityApple; //количество яблок
        int quantityPlum; //количество слив
        int quantityFruits = 100; //количество фруктов
        int priceOfOneWatermelon = 50; //цена одного арбуза
        int priceOfOneApple = 10; //цена одного яблока
        int priceOfOnePlum = 1; //цена одной сливы
        int money = 500; //сколько всего у нас денег в копейках
int count = 0;
        for (quantityWatermelon = 0; quantityWatermelon <= 10; quantityWatermelon++) {

            for (quantityApple = 0; quantityApple <= 50; quantityApple++) {

                for (quantityPlum = 0; quantityPlum <= 500; quantityPlum++) {

                    if (quantityWatermelon + quantityApple + quantityPlum == quantityFruits && priceOfOneWatermelon * quantityWatermelon + priceOfOneApple * quantityApple + priceOfOnePlum * quantityPlum == money) {
//                    if (priceOfOneWatermelon * quantityWatermelon + priceOfOneApple * quantityApple + priceOfOnePlum * quantityPlum == money) {
                        System.out.println(quantityWatermelon + "; " + quantityApple + "; " + quantityPlum+'\n');
                        count++;

                    }

                }

            }

        }
        System.out.println(count);
    }
}


