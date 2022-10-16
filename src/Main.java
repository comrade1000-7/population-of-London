import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> under18 = persons.stream();
        System.out.println(under18.filter(x -> x.getAge() < 18).count());

        Stream<Person> mobility = persons.stream();
        System.out.println(mobility.filter(x -> x.getAge() > 17 && x.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(x -> x.getFamily() + " " + x.getAge())
                .collect(Collectors.toList()));

        Stream<Person> workable = persons.stream();
        System.out.println(workable.filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() < 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(x -> x.getFamily() + " " + x.getSex() + " " + x.getAge())
                .collect(Collectors.toList()));
    }
}