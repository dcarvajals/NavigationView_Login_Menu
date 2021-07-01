package WebServicies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navigationview_login_menu.Home;
import com.example.navigationview_login_menu.MainActivity;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;

import Model.Usuario;
import StaticClass.Methods;

public class WsUsuario extends AppCompatActivity {

    private String url = "";
    private Context ctx;
    private Class class_home;
    private RequestQueue queue;

    public WsUsuario (String url, Context ctx, Class class_home) {
        this.url = url;
        this.ctx = ctx;
        this.class_home = class_home;
    }

    public void login(String usuario, String contrasenia){
        String data = String.format("{\"usr\":\"%s\", \"pwd\":\"%s\"}",
                usuario, contrasenia);
        queue = Volley.newRequestQueue(this.ctx);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "https://aplicaciones.uteq.edu.ec/RepositorioUTEQ/webresources/" +this.url,
                new com.android.volley.Response.Listener<String>()  {
                    @Override
                    public void onResponse(String response) {
                        Log.d("","Ws ejecutado con exito");
                        Log.d("",response);
                        JsonObject jso = Methods.stringToJSON(response);
                        Log.d("","size del json" + jso.size());
                        if(jso.size() > 0){
                            int status = Methods.JsonToInteger(jso, "status", 4);
                            if(status == 2){
                                JsonObject datosPersonales = Methods.JsonToSubJSON(jso,"data");
                                Log.d("", datosPersonales.toString());
                                try {
                                    Log.d("", jso.toString());
                                    Usuario.setNombres(Methods.JsonToString(datosPersonales, "names_user", ""));
                                    Usuario.setApellidos(Methods.JsonToString(datosPersonales, "lastname_user", ""));
                                    Usuario.setEmail(Methods.JsonToString(datosPersonales, "email_user", ""));
                                    Usuario.setRuta_img(Methods.JsonToString(datosPersonales, "img_user", ""));
                                    Usuario.setRol(Methods.JsonToString(datosPersonales, "rol_user", ""));

                                    Log.d("", getClass_home().toString());
                                    Intent intent = new Intent(getCtx(), Home.class);
                                    ctx.startActivity(intent);
                                }catch (Exception e){
                                    Log.d("",e.getMessage());
                                }
                            }else{
                                Log.d("","No se puso acceder al ws");
                            }
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                //los datos que se envian deben ir bajo este formato
                try {
                    return data == null ? "{}".getBytes("utf-8") : data.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Log.d("","Error al momento de codificar la solicitud");
                    return null;
                }
            }
        };
        queue.add(request);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public Class getClass_home() {
        return class_home;
    }

    public void setClass_home(Class class_home) {
        this.class_home = class_home;
    }
}
