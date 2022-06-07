package ra5.eurovision.modelo;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Un objeto de esta clase guarda en un map (festival) las puntuaciones obtenidas
 * en el festival de Eurovisi�n por una serie de pa�ses
 * Las puntuaciones se leen de un fichero de texto (puntuaciones.txt)
 *
 * El map festival asocia nombre del pa�s con la puntuaci�n total obtenida
 * Importa el orden de las claves
 */
public class Festival {

    private static final String SALIDA = "resultados.txt";
    private Map<String, Integer> festival;

    /**
     * Constructor de la clase FestivalEurovision
     */
    public Festival() {
       festival = new TreeMap();

    }

    /**
     * A�ade al map los puntos dados a un pa�s
     * El nombre del pa�s siempre se a�ade en may�sculas
     * Si el pa�s no existe se crea una nueva entrada en el map,
     * si existe el pa�s se a�aden los puntos
     */
    public void addPuntos(String pais, int puntos) {
        pais = pais.toUpperCase();
        if (festival.containsKey(pais)){
            int puntosActuales = festival.get(pais);
            festival.put(pais, puntosActuales + puntos);
        }
        else {
            festival.put(pais, puntos);
        }
    }

    /**
     * Lee del fichero de texto ENTRADA las puntuaciones dadas
     * a los pa�ses
     * Se capturar�n todas las posibles excepciones
     * Si al leer el fichero hay alguna l�nea err�nea se
     * contabiliza pero se sigue leyendo
     * Se devuelve el n� de l�neas incorrectas
     * Se har� uso del m�todo tratarLinea()
     *
     * Usar try-with-resources
     */
    public int leerPuntuaciones(String nombre) {
        File f = new File(nombre);
        int errores = 0;
        try (BufferedReader entrada = new BufferedReader(new FileReader(f))) {
            String linea = entrada.readLine();
            while (linea != null){
                try {
                    tratarLinea(linea);
                } catch (IllegalArgumentException e) {
                    errores++;
                }
                entrada.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe o no se ha encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero: " + e.getMessage());
        }
        return errores;
    }


    /**
     * A partir de una l�nea extrae los puntos dados a cada uno de los pa�ses indicados
     * y a�ade al map esas puntuaciones
     *
     * El formato de la l�nea es
     * pais:puntos:pais:puntos:pais:puntos:pais:puntos
     *
     * Se propagan las posibles excepciones
     */
    private void tratarLinea(String linea) throws NumberFormatException, IllegalArgumentException {
        String[] trozos = linea.split(":");
        String nomPais;
        int puntosPais;
        for (int i = 0; i < trozos.length; i+=2) {
            nomPais = trozos[i];
            puntosPais = Integer.parseInt(trozos[i+1]);
            addPuntos(nomPais, puntosPais);
        }
    }


    /**
     * Dado un pa�s devuelve su puntuaci�n
     * Si el pa�s no existe se lanza la excepci�n personalizada
     * PaisExcepcion con el mensaje "Pa�s XXX no existe"
     *
     * Se propagan las posibles excepciones
     */
    public int puntuacionDe(String pais) throws PaisExcepcion {
        int puntuacion = 0;
        if (festival.containsKey(pais.toUpperCase())){
            puntuacion = festival.get(pais);
        } else {
            throw new PaisExcepcion("El pa�s " + pais + " no existe");
        }
        return puntuacion;
    }

    /**
     * Devuelve el nombre del pais ganador
     * (el primero encontrado)
     */
    public String ganador() {
        int max = 0;
        String nomGanador = "";
        for (String pais : festival.keySet()) {
            if (festival.get(pais) > max)
                max = festival.get(pais);
                nomGanador = pais;
        }
        return nomGanador;
    }

    /**
     * Guarda en el fichero SALIDA el nombre de cada pa�s y la puntuaci�n
     * total obtenida
     * Se propagan todas las excepciones
     * Usar try-with-resources
     */
    public void guardarResultados() throws IOException {
        File f = new File(SALIDA);
        try (PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            for (String pais : festival.keySet()) {
                salida.println(pais);
                salida.print(" --> ");
                salida.print(festival.get(pais));
            }
        }
    }

}
