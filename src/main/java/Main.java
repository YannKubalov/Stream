import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        var lst1 = List.of(1,2,1,2,1,2,3,2,1,3);
        System.out.println("Удаление из листа всех дубликатов: " + lst1.stream().distinct().collect(Collectors.toList()));

        var lst2 = List.of(5,2,10,9,4,3,10,1,13);
        System.out.println("Найдите в списке целых чисел 3-е наибольшее число: " +
                lst2.stream().sorted(Comparator.reverseOrder()).limit(3).skip(2).findFirst().orElseThrow());

        System.out.println("Найдите в списке целых чисел 3-е наибольшее уникальное число: " +
                lst2.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).skip(2).findFirst().orElseThrow());

        var engineer ="Инженер";
        var lst3 = List.of(new Worker("Имя1", 25, engineer),
                new Worker("Имя2", 40, engineer),
                new Worker("Имя3", 72, engineer),
                new Worker("Имя4", 55, "Должность1"),
                new Worker("Имя5", 18, "Должность2"),
                new Worker("Имя6", 30, engineer));

        var result = lst3.stream()
                .filter(x -> engineer.equals(x.getPosition()))
                .sorted(Comparator.comparing(Worker::getAge).reversed())
                .limit(3)
                .map(Worker::getName)
                .collect(Collectors.toList());
        System.out.println("Три самых старших инженера: " + result);

       var result2 = lst3.stream()
                .filter(x -> engineer.equals(x.getPosition()))
                .mapToInt(Worker::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Средний возраст инженера: " + result2);

        var lst4 = List.of("слово", "длинноеслово", "оченьсамоедлинноеслово", "самоедлинноеслово", "оченьдлинноеслово");
        System.out.println("Самое длинное слово: " +
                lst4.stream().max(Comparator.comparing(String::length)).orElse(null));

        var str = "имеется строка с набором слов в нижнем регистре разделенных пробелом " +
                "постройте хеш-мапы в которой будут хранится пары слово сколько раз оно встречается во входной строке " +
                "имеется массив строк в каждой из которых лежит набор из 5 строк разделенных пробелом" +
                " найдите среди всех слов самое длинное если таких слов несколько получите любое из них";
        var lst5 = Arrays.stream(str.split("\\s+")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Хеш-мап слово - колличество: " + lst5);

        System.out.println("Строки в порядке увеличения длины:");
        lst4.stream().sorted(Comparator.comparing(String::length).reversed().thenComparing(Comparator.naturalOrder())).forEach(System.out::println);

        var lst6 = List.of(
                "яблоко банан апельсин виноград киви",
                "машина поезд корабль велосипед самолет",
                "красный синий желтый зеленый фиолетовый",
                "собака кошка корова жираф слон",
                "компьтер телефон наушники клавиатура монитор"
                );
        var result3 = lst6.stream()
                .flatMap(w -> Arrays.stream(w.split("\\s+")))
                .max(Comparator.comparing(String::length))
                .orElse(null);
        System.out.println("Самое длинное слово: " + result3);


    }


}