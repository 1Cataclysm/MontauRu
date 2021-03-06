package com.example.montauru;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametre;
    private String ret = null;
    public AsyncResponse delegate = null;

    public AccesHTTP (){
        parametre = new ArrayList<NameValuePair>();
    }

    public void addParam(String nom, String valeur){
        parametre.add(new BasicNameValuePair(nom, valeur));
    }

    // Connexion en tache de fond dans un thread séparé
    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            // encodage des parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametre));
            //connexion et envoi des parametres et attente de réponse
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            // transformation de la reponse
            ret = EntityUtils.toString(reponse.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage", "*********************"+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur protocole", "*********************"+e.toString());
        } catch (IOException e) {
            Log.d("Erreur input/output", "*********************"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish((ret.toString()));
    }

}
