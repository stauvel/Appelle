package fr.appelle.entity;

/**
 * Created by Seb on 08/05/2016.
 */
public class Name {

    /*For tests*/
    public Name(String label, Sexe sexe) {
        this.label = label;
        this.sexe = sexe;
    }

    public Name() {
    }

    private String label;
    private Sexe sexe;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Name{" +
                "label='" + label + '\'' +
                ", sexe=" + sexe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (!label.equals(name.label)) return false;
        if (sexe != name.sexe) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + sexe.hashCode();
        return result;
    }
}
