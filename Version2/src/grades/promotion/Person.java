package grades.promotion;

/*
 *
 * Q1) : Chaque professeur et chaque élève ont comme propriétés communes le prénom et le
 * nom. On a donc crée une classe Person qui représentera un individu quelconque, dont les classes Student et Teacher
 * hériteront; le concept associé est donc l'héritage.
 *
 */

public class Person {

    protected String surname;
    protected String name;

    public Person(){

    }

    public Person(String surname, String name) {

        this.name = name;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return "(" + surname + ", " + name + ")";
    }
}
