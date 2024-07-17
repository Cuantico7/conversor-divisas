import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorApp {

    private static final String API_KEY = "2b91ecdb76d0dc1328cfa456";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private static final Map<Integer, String[]> conversionOptions = new HashMap<>();

    static {
        conversionOptions.put(1, new String[]{"USD", "ARS"});
        conversionOptions.put(2, new String[]{"ARS", "USD"});
        conversionOptions.put(3, new String[]{"USD", "BRL"});
        conversionOptions.put(4, new String[]{"BRL", "USD"});
        conversionOptions.put(5, new String[]{"USD", "COP"});
        conversionOptions.put(6, new String[]{"COP", "USD"});
    }

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
            System.out.println(menu);
            System.out.println("\nElija una opción:");
            System.out.println("\n**********************************************************");
            opcion = teclado.nextInt();
            if (opcion == 7){
                System.out.println("Gracias por utilizar la aplicación...");
                break;
            }

            System.out.println("Ingrese el monto a convertir: ");
            double valor = teclado.nextDouble();

            try{
                if (conversionOptions.containsKey(opcion)) {
                    String[] currencies = conversionOptions.get(opcion);
                    convertirMoneda(currencies[0], currencies[1], valor);
                } else {
                    System.out.println("Opción no válida");
                }
            } catch (IOException | InterruptedException e){
                System.out.println("Error al realizar la conversión: " + e.getMessage());
            }
        }
        teclado.close();
    }

    public static void convertirMoneda (String from, String to, double valor) throws IOException, InterruptedException {
        String url = API_URL + from;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        MonedaUtil monedaUtil = new MonedaUtil();
        double tasa = monedaUtil.obtenerTasaConversion(response.body(), to);
        if (tasa == 0){
            System.out.println("No se pudo obtener la tasa de conversión");
        }else{
            double resultado = valor * tasa;
            System.out.println("El monto " + valor + " (" + from + ")" +" equivale a --> " + resultado + " (" + to + ")\n");
        }
    }

}
