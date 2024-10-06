import characters.*;

import java.util.Random;

public class PersonajeBuilder{

    public PersonajeBuilder(ClasePersonaje tipo){
        this.tipo = tipo;
    }

    final ClasePersonaje tipo;
    private int fuerza;
    private int inteligencia;
    private int sabiduria;
    private int destreza;
    private int constitucion;
    private int carisma;

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }
    public void setSabiduria(int sabiduria) {
        this.sabiduria = sabiduria;
    }
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
    public void setConstitution(int constitucion) {
        this.constitucion = constitucion;
    }
    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    // retorna la suma de llençar [numDice] daus de [sides] cares
    private int throwDice(int numDice, int sides){
        int res = 0;
        Random rand = new Random();
        for(int i = 0; i < numDice; i++){
            res += rand.nextInt(1, sides + 1);
        }
        return res;
    }

    private void randomize(){
        this.fuerza = throwDice(3, 6);
        this.inteligencia = throwDice(3, 6);
        this.sabiduria = throwDice(3, 6);
        this.destreza = throwDice(3, 6);
        this.constitucion = throwDice(3, 6);
        this.carisma = throwDice(3, 6);
    }

    private boolean verifyRandomization(){
        switch(this.tipo){
            case Clerigo:
            case Guerrero:
            case Ladron:
            case Mago:
                return true;    // no tienen requisitos
            case Elfo:
                return this.inteligencia >= 9;  // el elfo requiere una mínima inteligencia de 9
            case Enano:
                return this.constitucion >= 9;
            case Mediano:
                return this.constitucion >= 9 && this.destreza >= 9;
        }

        return true;
    }

    // crea un personatge aleatori respectant els requisits
    public Personaje buildRandom(){
        do{
            randomize();
        }while(!verifyRandomization());

        return this.build();
    }

    public Personaje build(){
        switch(this.tipo){
            case Clerigo:
                return new Clerigo(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Elfo:
                return new Elfo(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Enano:
                return new Enano(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Guerrero:
                return new Guerrero(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Ladron:
                return new Ladron(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Mago:
                return new Mago(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
            case Mediano:
                return new Mediano(fuerza, inteligencia, sabiduria, destreza, constitucion, carisma);
        }
        return null;
    }
}
