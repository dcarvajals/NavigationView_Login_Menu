package Model;

public class Usuario {

    private static String nombres = "";
    private static String apellidos = "";
    private static String email = "";
    private static String ruta_img = "";
    private static String rol = "";

    public Usuario() { }

    public static String getNombres() {
        return Usuario.nombres;
    }

    public static void setNombres(String nombres) {
        Usuario.nombres = nombres;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public static void setApellidos(String apellidos) {
        Usuario.apellidos = apellidos;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Usuario.email = email;
    }

    public static String getRuta_img() {
        return ruta_img;
    }

    public static void setRuta_img(String ruta_img) {
        Usuario.ruta_img = ruta_img;
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        Usuario.rol = rol;
    }
}
