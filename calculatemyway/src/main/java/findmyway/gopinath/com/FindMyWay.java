package findmyway.gopinath.com;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FindMyWay{


  public  static double findDistance(final Context context, final double origin_latitude, final double origin_longitude, final double destination_latitude, final double destination_longitude) {

        final Calculate c = new Calculate();
        class RetrieveFeedTask extends AsyncTask<Void, Void, Double> {


            @Override
            protected Double doInBackground(Void... voids) {
                StringBuilder stringBuilder = new StringBuilder();
                Double dist = 0.0;
                try {

                    if (origin_latitude != 0.0 && destination_latitude != 0.0) {
                        //destinationAddress = destinationAddress.replaceAll(" ","%20");
                        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + origin_latitude + "," + origin_longitude + "&destination=" + destination_latitude + "," + destination_longitude + "&mode=driving&sensor=false";

                        HttpPost httppost = new HttpPost(url);

                        DefaultHttpClient client = new DefaultHttpClient();
                        HttpResponse response;
                        stringBuilder = new StringBuilder();


                        response = client.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        InputStream stream = entity.getContent();
                        int b;
                        while ((b = stream.read()) != -1) {
                            stringBuilder.append((char) b);
                        }
                    } else {

                        Toast.makeText(context, "Try to delete the customer request which has given for this particular driver", Toast.LENGTH_LONG).show();
                    }
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }


                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject = new JSONObject(stringBuilder.toString());

                    JSONArray array = jsonObject.getJSONArray("routes");

                    JSONObject routes = array.getJSONObject(0);

                    JSONArray legs = routes.getJSONArray("legs");

                    JSONObject steps = legs.getJSONObject(0);

                    String duration = steps.getJSONObject("duration").getString("text");

                    JSONObject distance = steps.getJSONObject("distance");


                    Log.i("Distance", distance.toString());
                    dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]", ""));
                    c.setDistance(dist);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return dist;
            }

            @Override
            protected void onPostExecute(Double aDouble) {
                super.onPostExecute(aDouble);
            }
        }
        return c.getDistance();
    }

    public static String findDuration(final Context context, final double origin_latitude, final double origin_longitude, final double destination_latitude, final double destination_longitude) {

        final Calculate c = new Calculate();
        class RetrieveFeedTask extends AsyncTask<Void, Void, Double> {


            @Override
            protected Double doInBackground(Void... voids) {
                StringBuilder stringBuilder = new StringBuilder();
                Double dist = 0.0;
                try {

                    if (origin_latitude != 0.0 && destination_latitude != 0.0) {
                        //destinationAddress = destinationAddress.replaceAll(" ","%20");
                        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + origin_latitude + "," + origin_longitude + "&destination=" + destination_latitude + "," + destination_longitude + "&mode=driving&sensor=false";

                        HttpPost httppost = new HttpPost(url);

                        DefaultHttpClient client = new DefaultHttpClient();
                        HttpResponse response;
                        stringBuilder = new StringBuilder();


                        response = client.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        InputStream stream = entity.getContent();
                        int b;
                        while ((b = stream.read()) != -1) {
                            stringBuilder.append((char) b);
                        }
                    } else {

                        Toast.makeText(context, "Try to delete the customer request which has given for this particular driver", Toast.LENGTH_LONG).show();
                    }
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }


                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject = new JSONObject(stringBuilder.toString());

                    JSONArray array = jsonObject.getJSONArray("routes");

                    JSONObject routes = array.getJSONObject(0);

                    JSONArray legs = routes.getJSONArray("legs");

                    JSONObject steps = legs.getJSONObject(0);

                    String duration = steps.getJSONObject("duration").getString("text");

                    c.setDuration(duration);
                    JSONObject distance = steps.getJSONObject("distance");


                    Log.i("Distance", distance.toString());
                    dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]", ""));

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return dist;
            }

            @Override
            protected void onPostExecute(Double aDouble) {
                super.onPostExecute(aDouble);
            }
        }
        return c.getDuration();
    }

}
