package lioncorps.collectionbdmanager.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import lioncorps.collectionbdmanager.bean.Bd;
import lioncorps.collectionbdmanager.bean.Collection;
import lioncorps.collectionbdmanager.bean.ListItem;
import lioncorps.collectionbdmanager.bean.Serie;


public class SerieUtils {

    static BDComparator bdComparator = new BDComparator();

    public static Serie getBDById(Collection CollectionBDClient, int id) {
        for (Serie serie : CollectionBDClient.getListeSerie()) {
            if (serie.getId() == id) {
                return serie;
            }
        }
        return null;
    }

    public static List<ListItem> convertCollection(Collection listeBD) {
        List<ListItem> toReturn = new ArrayList<>();
        for(Serie serie : listeBD.getListeSerie()){
            if (serie.getListManquante()!= null && serie.getListManquante().size()>0){
                for (Bd bd : serie.getListManquante()) {
                    ListItem item = new ListItem();
                    item.setSerieId(serie.getId());
                    item.setSerieName(serie.getNom());
                    item.setEditeur(serie.getEditeur());
                    item.setBdid(bd.getId());
                    if (!TextUtils.isEmpty(bd.getCouvertureUrl()))
                    {
                        item.setUrlImage(bd.getCouvertureUrl());
                    }else {
                        item.setUrlImage(serie.getImageUrl());
                    }

                    item.setTitre(bd.getTitre());
                    item.setNumero(bd.getNumero());
                    toReturn.add(item);
                }

            }
        }
        return toReturn;
    }
}
