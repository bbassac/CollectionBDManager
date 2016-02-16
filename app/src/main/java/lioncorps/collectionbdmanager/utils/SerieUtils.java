package lioncorps.collectionbdmanager.utils;

import java.util.Collections;

import lioncorps.collectionbdmanager.bean.Bd;
import lioncorps.collectionbdmanager.bean.Collection;
import lioncorps.collectionbdmanager.bean.Serie;


public class SerieUtils {

    static BDComparator bdComparator = new BDComparator();

    public static String getStringPossede(Serie s) {
        StringBuilder builder = new StringBuilder();
        Collections.sort(s.getListPossede(), bdComparator);
        for (Bd bd : s.getListPossede()) {
            builder.append(bd.getNumero()).append(" ").append(bd.getTitre()).append("\n");
        }
        return builder.toString();
    }

    public static String getStringManquant(Serie s) {
        StringBuilder builder = new StringBuilder();
        Collections.sort(s.getListManquante(), bdComparator);
        for (Bd bd : s.getListManquante()) {
            builder.append(bd.getNumero()).append(" ").append(bd.getTitre()).append("\n");
        }
        return builder.toString();
    }

    public static Serie getBDById(Collection CollectionBDClient, int id) {
        for (Serie serie : CollectionBDClient.getListeSerie()) {
            if (serie.getId() == id) {
                return serie;
            }
        }
        return null;
    }

    public static String getNbBdPossede(Serie serie) {
        return String.valueOf(serie.getListPossede() == null ? "--" : serie.getListPossede().size());
    }

    public static String getNbTotalBd(Serie serie) {
        return String.valueOf(serie.getListPossede() == null || serie.getListManquante() == null ? "--" : serie.getListManquante().size() + serie.getListPossede().size());
    }

}
