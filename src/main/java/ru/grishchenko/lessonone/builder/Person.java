package ru.grishchenko.lessonone.builder;

public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(){};

    public static PersonBuilder getBuilder() {
        return new Person().new PersonBuilder();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public class PersonBuilder {

        private PersonBuilder(){};

        public PersonBuilder setFirstName(String value) {
            Person.this.firstName = value;
            return this;
        }

        public PersonBuilder setLastName(String value) {
            Person.this.lastName = value;
            return this;
        }

        public PersonBuilder setMiddleName(String value) {
            Person.this.middleName = value;
            return this;
        }

        public PersonBuilder setCountry(String value) {
            Person.this.country = value;
            return this;
        }

        public PersonBuilder setAddress(String value) {
            Person.this.address = value;
            return this;
        }

        public PersonBuilder setPhone(String value) {
            Person.this.phone = value;
            return this;
        }

        public PersonBuilder setAge(int value) {
            Person.this.age = value;
            return this;
        }

        public PersonBuilder setGender(String value) {
            Person.this.gender = value;
            return this;
        }

        public Person build() {
            return Person.this;
        }

    }
}
