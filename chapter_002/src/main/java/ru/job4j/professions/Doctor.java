package ru.job4j.professions;

public class Doctor extends Profession {
    public Doctor(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public Diagnose treatThePatient(Patient patient) {
        return null;
    }
}
