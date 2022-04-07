package com.example.montauru;

import static android.content.ContentValues.TAG;

import android.net.MailTo;
import android.provider.CalendarContract;
import android.util.Log;

import androidx.annotation.LongDef;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class DateOutil {
    private static String[] moisAnnee, moisAnneeAnglais, jourSemaine, jourSemaineAnglais;
    private static String jour, mois;

    public static List<String> dateSemaineProchaine (){
        moisAnnee = new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre"};
        moisAnneeAnglais = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
        jourSemaine = new String[] {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
        jourSemaineAnglais = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri"};
        List<String> dateSemaineProchaine = new ArrayList<String>();

        // Mois

        Calendar instanceDate = Calendar.getInstance();
        mois = moisAnnee[instanceDate.get(Calendar.MONTH)];
        GregorianCalendar calendar =new GregorianCalendar();
        calendar.setTime(new Date());
        int today =calendar.get(calendar.DAY_OF_WEEK);
        Log.d(TAG, "LE JOUR ENFIN ? " + today);
        GregorianCalendar cal = new GregorianCalendar();
        switch (today) {
            case GregorianCalendar.MONDAY:
                //on est lundi
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,0);
                java.util.Date dateLundiSiLundi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiLundi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,1);
                java.util.Date dateMardiSiLundi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiLundi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,2);
                java.util.Date dateMercrediSiLundi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiLundi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,3);
                java.util.Date dateJeudiSiLundi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiLundi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,4);
                java.util.Date dateVendrediSiLundi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiLundi));

                break;
            case GregorianCalendar.TUESDAY:
                //on est mardi
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-1);
                java.util.Date dateLundiSiMardi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiMardi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,0);
                java.util.Date dateMardiSiMardi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiMardi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,1);
                java.util.Date dateMercrediSiMardi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiMardi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,2);
                java.util.Date dateJeudiSiMardi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiMardi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,3);
                java.util.Date dateVendrediSiMardi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiMardi));

                break;
            case GregorianCalendar.WEDNESDAY:
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-2);
                java.util.Date dateLundiSiMercredi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiMercredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-1);
                java.util.Date dateMardiSiMercredi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiMercredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,0);
                java.util.Date dateMercrediSiMercredi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiMercredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,1);
                java.util.Date dateJeudiSiMercredi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiMercredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,2);
                java.util.Date dateVendrediSiMercredi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiMercredi));
                break;
            case GregorianCalendar.THURSDAY:
                cal.add(Calendar.DAY_OF_MONTH,-3);
                java.util.Date dateLundiSiJeudi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiJeudi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-2);
                java.util.Date dateMardiSiJeudi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiJeudi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-1);
                java.util.Date dateMercrediSiJeudi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiJeudi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,0);
                java.util.Date dateJeudiSiJeudi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiJeudi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,1);
                java.util.Date dateVendrediSiJeudi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiJeudi));
                break;
            case GregorianCalendar.FRIDAY:
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-4);
                java.util.Date dateLundiSiVendredi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiVendredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-3);
                java.util.Date dateMardiSiVendredi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiVendredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-2);
                java.util.Date dateMercrediSiVendredi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiVendredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,-1);
                java.util.Date dateJeudiSiVendredi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiVendredi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,0);
                java.util.Date dateVendrediSiVendredi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiVendredi));
                break;
            case GregorianCalendar.SATURDAY:
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,2);
                java.util.Date dateLundiSiSamedi = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiSamedi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,3);
                java.util.Date dateMardiSiSamedi = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiSamedi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,4);
                java.util.Date dateMercrediSiSamedi = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiSamedi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,5);
                java.util.Date dateJeudiSiSamedi = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiSamedi));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,6);
                java.util.Date dateVendrediSiSamedi = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiSamedi));
                break;
            case GregorianCalendar.SUNDAY:
                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,1);
                java.util.Date dateLundiSiDimanche = cal.getTime();
                dateSemaineProchaine.add(0, jourTest(dateLundiSiDimanche));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,2);
                java.util.Date dateMardiSiDimanche = cal.getTime();
                dateSemaineProchaine.add(1, jourTest(dateMardiSiDimanche));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,3);
                java.util.Date dateMercrediSiDimanche = cal.getTime();
                dateSemaineProchaine.add(2,jourTest(dateMercrediSiDimanche));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,4);
                java.util.Date dateJeudiSiDimanche = cal.getTime();
                dateSemaineProchaine.add(3, jourTest(dateJeudiSiDimanche));

                cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH,5);
                java.util.Date dateVendrediSiDimanche = cal.getTime();
                dateSemaineProchaine.add(4, jourTest(dateVendrediSiDimanche));
                break;
            default:
                //ça devrait pas erreur
                break;
        }
        Log.d ("semaine pro" , dateSemaineProchaine.toString());
        return dateSemaineProchaine;
    }

    private static String jourTest (Date jourSi){
        String jourAnglais = jourSi.toString().substring(0,3);
        String moisAnglais = jourSi.toString().substring(4,7);
        String nJourAnglais = jourSi.toString().substring(8,10);
        for (int i = 0; i < moisAnneeAnglais.length; i++){
            if (moisAnneeAnglais[i].equals(moisAnglais)){
                mois = moisAnnee[i];
                break;
            }
        }
        for (int i = 0; i < jourSemaineAnglais.length; i++){
            if (jourSemaineAnglais[i].equals(jourAnglais)){
                jour = jourSemaine[i];
                break;
            }
        }
        return ""+jour+" "+nJourAnglais+" "+mois;
    }
}
