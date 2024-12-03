package J2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Exercice1 {

    public static void main(String[] args) {

        Exercice1 exercice = new Exercice1();

        exercice.chargeDoc("src/J2/input.txt");

    }

    public void chargeDoc (String nomFichier){

        int nbSafe = 0;
        int nbSafeErreur = 0;

        // Lecture du fichier
        try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;

            // Lecture ligne par ligne
            while ((ligne = lecteur.readLine()) != null) {

                // Découper la ligne en morceaux (nombres sous forme de chaînes)
                String[] morceaux = ligne.trim().split("\\s+");

                // Convertir chaque morceau en entier et les ajouter dans une liste
                ArrayList<Integer> nombres = new ArrayList<>();
                for (String morceau : morceaux) {
                    nombres.add(Integer.parseInt(morceau));
                }

                // Appel de la fonction verif
                if (verif(nombres)){
                    nbSafe += 1;
                }

                if (verifDampener(nombres)){
                    nbSafeErreur += 1;
                }

            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format numérique dans le fichier : " + e.getMessage());
        }

        System.out.println("Nombre de lignes safe : " + nbSafe);
        System.out.println("\nNombre de lignes avec un mauvais niveau mais bonne : " + nbSafeErreur);
    }

    public boolean verif(ArrayList<Integer> nombres) {
        // Si le nombre précédent est plus petit que le nombre actuel
        if (nombres.getFirst() < nombres.getLast()){
            for (int i = 1; i < nombres.size(); i++) {
                if ((nombres.get(i - 1) + 1 != nombres.get(i)) && (nombres.get(i - 1) + 2 != nombres.get(i)) && (nombres.get(i - 1) + 3 != nombres.get(i)) ) {
                    return false;
                }
            }
        // Si le nombre précédent est plus grand que le nombre actuel
        } else {
            for (int i = 1; i < nombres.size(); i++) {
                if ((nombres.get(i - 1) != nombres.get(i) + 1) && (nombres.get(i - 1) != nombres.get(i) + 2) && (nombres.get(i - 1) != nombres.get(i) + 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifDampener(ArrayList<Integer> nombres){

        ArrayList<Integer> nombresTemp = new ArrayList<>(nombres);

        // Si le nombre précédent est plus petit que le nombre actuel
        if (nombres.getFirst() < nombres.getLast()){
            for (int i = 1; i < nombres.size(); i++) {

                if ((nombres.get(i - 1) + 1 != nombres.get(i)) && (nombres.get(i - 1) + 2 != nombres.get(i)) && (nombres.get(i - 1) + 3 != nombres.get(i)) ) {

                    //supprime le nombre actuel
                    nombresTemp.remove(i);
                    boolean premiercas = verif(nombresTemp);

                    //retire le nombre d'avant
                    nombres.remove(i-1);

                    return (premiercas || verif(nombres));
                }
            }
            // Si le nombre précédent est plus grand que le nombre actuel
        } else {
            for (int i = 1; i < nombres.size(); i++) {
                if ((nombres.get(i - 1) != nombres.get(i) + 1) && (nombres.get(i - 1) != nombres.get(i) + 2) && (nombres.get(i - 1) != nombres.get(i) + 3)) {

                    //supprime le nombre actuel
                    nombresTemp.remove(i);
                    boolean premiercas = verif(nombresTemp);

                    //retire le nombre d'avant
                    nombres.remove(i-1);

                    return (premiercas || verif(nombres));
                }
            }
        }
        return true;
    }
}