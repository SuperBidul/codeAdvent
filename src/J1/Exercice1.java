package J1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Exercice1 {

    private ArrayList<Integer> liste1;
    private ArrayList<Integer> liste2;

    public Exercice1() {
        this.liste1 = new ArrayList<>();
        this.liste2 = new ArrayList<>();
    }

    public static void main(String[] args) {

        Exercice1 exercice = new Exercice1();

        exercice.chargeListe("src/J1/input.txt");

        // Trier les listes
        exercice.sortLists();

        // Une fois les listes remplies on peut les comparer

        int distance = exercice.calculeDistance();

        System.out.println("La distance est : " + distance);
    }

    public void chargeListe (String nomFichier){
        // Lecture du fichier
        try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;

            // Lecture ligne par ligne
            while ((ligne = lecteur.readLine()) != null) {

                // Découpage de la ligne en deux nombres
                String[] parties = ligne.trim().split("\\s+"); // Découpe sur un ou plusieurs espaces
                int nombre1 = Integer.parseInt(parties[0]);
                int nombre2 = Integer.parseInt(parties[1]);

                //Création des deux listes :
                liste1.add(nombre1);
                liste2.add(nombre2);

            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format numérique dans le fichier : " + e.getMessage());
        }
    }

    public void sortLists() {
        Collections.sort(liste1);
        Collections.sort(liste2);
    }

    public int calculeDistance() {
        int somme = 0;
        for (int i = 0; i < liste1.size(); i++) {
            if (liste1.get(i) > liste2.get(i)) {
                somme += liste1.get(i) - liste2.get(i);
            } else {
                somme += liste2.get(i) - liste1.get(i);
            }
        }
        return somme;
    }
}