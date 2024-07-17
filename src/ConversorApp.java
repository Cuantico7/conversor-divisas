import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApp {

    private static final String API_KEY = "2b91ecdb76d0dc1328cfa456";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        int opcion = 0;


        System.out.println("**********************************************************");
        System.out.println("\nBienvenido al conversor de monedas\n");

        String menu = """
                1) Dólar --> Peso Argentino
                2) Peso Argentino --> Dólar
                3) Dólar --> Real Brasilero
                4) Real Brasilero --> Dólar
                5) Dólar --> Peso colombiano
                6) Peso colombiano --> Dólar  
                7) Salir 
                """;


        System.out.println("**********************************************************\n");

        Scanner teclado = new Scanner(System.in);
        while (opcion != 7){
            // Muestra opciones al usuario
            System.out.println(menu);
            System.out.println("\nElija una opción:");
            System.out.println("\n**********************************************************");
            opcion = teclado.nextInt();
            // Agregamos una condicion con un mensaje cuando el usuario elija la opcion 7
            if (opcion == 7){
                System.out.println("Gracias por utilizar la aplicación...");
                break;
            }

            // Pedimos el valor a convertir
            System.out.println("Ingrese el monto a convertir: ");
            double valor = teclado.nextDouble();

           try{
               switch (opcion){
                   case 1:
                       convertirMoneda("USD", "ARS", valor);
                       break;
                   case 2:
                       convertirMoneda("ARS", "USD", valor);
                       break;
                   case 3:
                       convertirMoneda("USD", "BRL", valor);
                       break;
                   case 4:
                       convertirMoneda("BRL", "USD", valor);
                       break;
                   case 5:
                       convertirMoneda("USD", "COP", valor);
                       break;
                   case 6:
                       convertirMoneda("COP", "USD", valor);
                       break;
                   default:
                       System.out.println("Opcion no valida");
               }
           } catch (IOException | InterruptedException e){
               System.out.println("Error al realizar la conversion: " + e.getMessage());
           }
        }
        teclado.close();
    }

    public static void convertirMoneda (String moneda, String a, double valor) throws IOException, InterruptedException {
        String url = API_URL + moneda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        MonedaUtil monedaUtil = new MonedaUtil();
        double tasa = monedaUtil.obtenerTasaConversion(response.body(), a);
        if (tasa == 0){
            System.out.println("");
        }else{
            double resultado = valor * tasa;
            System.out.println("El monto " + valor + " (" + moneda + ")" +" equivale a --> " + resultado + " (" + a + ")\n");

        }

    }

}
