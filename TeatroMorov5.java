/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromorov5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Marco
 */
public class TeatroMorov5 {

    /**
     * @param args the command line arguments
     */
    static int asientosVendidosPalco = 0;   // Acumulador de asientos vendidos
    static int asientosVendidosPlatea = 0;  // en las distintas
    static int asientosVendidosGaleria = 0; // zonas del teatro
    static final int AFORO = 82; // Constante de aforo
    static final int CAPACIDAD_PALCO = 14;   //Total asientos
    static final int CAPACIDAD_PLATEA = 47;  //en las distintas
    static final int CAPACIDAD_GALERIA = 21; //zonas del teatro
    static final int VALOR_PALCO = 30000;   //Total asientos
    static final int VALOR_PLATEA = 15000;  //en las distintas
    static final int VALOR_GALERIA = 13000; //zonas del teatro
    static int totalVentas = 0; // Acumulador de cantidad de ventas
    static double ingresoTotal = 0; // Acumulador de dinero recaudado
    static final double DESCUENTO_ESTUDIANTE = 0.1; // Constante para el descuento de miembros (12%)
    static final double DESCUENTO_TERCERA_EDAD = 0.15;     // Constante para el descuento de clientes VIP (20%)
    static ArrayList<String> nombreClientes = new ArrayList<>();   // Lista para nombres de producto
    static ArrayList<Integer> edadClientes = new ArrayList<>();    // Lista para edad de clientes
    static ArrayList<String> tipoClientes = new ArrayList<>();      // Lista para montos finales de las ventas
    static ArrayList<Integer> costoBase = new ArrayList<>();  // Lista para categoría del cliente
    static ArrayList<Integer> Descuentos = new ArrayList<>();  // Lista para zonas donde el cliente compro el asiento
    static ArrayList<String> clienteZona = new ArrayList<>();   // Lista para nombres de producto
    static ArrayList<Integer> asientoClientes = new ArrayList<>();    // Lista para el asiento del clientes
    double Vdescuento = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Variables Globales
        int asientosReservados = 0;
        int VectorPalco[] = new int[15], VectorPlatea[] = new int[48], VectorGaleria[] = new int[22];
        int VectorAsientosReservados[] = new int[6], VectorUbicacionesReservados[] = new int[6], VectorIdReservados[] = new int[6];
        int VasientoReservado = 0;
        double VdescuentoReserva = 0;
        int VectorTipoReservado[] = new int[6];
        int asientosComprados = 0, ComprarOtraEntrada = 0, estudiante = 0;
        int SeleccionZona = 0, asiento = 0, edad = 0;
        int VPalco = 30000, VPlatea = 15000, VGaleria = 13000;
        int VectorAsientosComprados[] = new int[6], VectorUbicaciones[] = new int[6], VectorTipo[] = new int[6];
        int azar = 0;
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechas = fecha.format(formato);
        //inicializar Vectores como true (disponibles)  
        int palcoLargo = VectorPalco.length;
        int plateaLargo = VectorPlatea.length;
        int galeriaLargo = VectorGaleria.length;
        int asientoLargo = VectorAsientosComprados.length;
        for (int i = 0; i < palcoLargo; i++) {
            VectorPalco[i] = 1;
        }
        for (int i = 0; i < plateaLargo; i++) {
            VectorPlatea[i] = 1;
        }
        for (int i = 0; i < galeriaLargo; i++) {
            VectorGaleria[i] = 1;
        }
        for (int i = 0; i < asientoLargo; i++) {
            VectorAsientosComprados[i] = 0;
        }
        int edadCliente = 0, seleccionZona = 0, VdescSinDecimales;
        String tipoCliente = "", ubicacion = "", cliente = "";
        // int valorEntrada=0, 
        boolean salir = false;
        System.out.println("\033[34m*---------------------------*");
        System.out.println("\033[34m| Bienvenido al Teatro Moro |");
        System.out.println("\033[34m*---------------------------*");
        int TotalAPagar = 0;
        while (!salir) { // Menú principal
            System.out.println("\033[34m*-----  Menú Principal -----*");
            System.out.println("\033[34m|1.  Venta de entradas      |");//listo
            System.out.println("\033[34m|2.  Resumen de ventas      |");//listo
            System.out.println("\033[34m|3.  Reimprimir boleta      |");//listo
            System.out.println("\033[34m|4.  Reserva de entradas    |");//listo
            System.out.println("\033[34m|5.  Cancelar reserva       |");//listo
            System.out.println("\033[34m|6.  Listar reservas        |");//listo
            System.out.println("\033[34m|7.  Salir                  |");//listo
            System.out.println("\033[34m*---------------------------*");
            System.out.println("\u001B[0m");
            System.out.print("Seleccione una opción: ");
            String opcionMenu = scanner.nextLine(); // Leemos la opción como texto
            int seleccion = 0;
            try {  // Validamos que la opción sea un número entre 1 y 7
                seleccion = Integer.parseInt(opcionMenu); // Convertimos texto a número
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número del 1 al 7.\n");
                continue;
            }
            if (seleccion < 1 || seleccion > 7) {
                System.out.println("Opción fuera de rango. Intente de nuevo.\n");
                continue;
            }
            if (seleccion == 1 || seleccion == 4) {
                ComprarOtraEntrada = 1;
                int reservarOtraEntrada = 1;
                System.out.print("Ingrese el nombre del cliente: ");
                cliente = scanner.nextLine();
                while (cliente.isEmpty() || cliente.trim().length() < 3) {
                    System.out.print("Nombre inválido. Intente nuevamente: ");
                    cliente = scanner.nextLine();
                }
                System.out.print("Hola " + cliente + ", ¿usted es Estudiante?(1-si o 2- no)");
                boolean esEstudiante;
                int respuestaEstudiante = 0;
                do { //corrector de ingreso de caracteres distintos a los que se pide
                    do {
                        try {
                            esEstudiante = false;
                            respuestaEstudiante = scanner.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("Intente Nuevamente");
                            scanner.next();
                            esEstudiante = true;
                        }
                    } while (esEstudiante);
                } while (respuestaEstudiante != 1 && respuestaEstudiante != 2);
                if (respuestaEstudiante == 1) {
                    System.out.println("Como estudiante tienes un descuento del " + (DESCUENTO_ESTUDIANTE * 100) + "%");
                    tipoCliente = "Estudiante";
                    esEstudiante = true;
                }
                boolean correctorEdad;
                do { //corrector de ingreso de caracteres distintos a los que se pide
                    do {
                        try {
                            correctorEdad = false;
                            System.out.print("Ingrese su edad (5 a 120): ");
                            edadCliente = scanner.nextInt();
                        } catch (InputMismatchException ex) {
                            System.out.println("intente nuevamente");
                            scanner.next();
                            correctorEdad = true;
                        }
                    } while (correctorEdad);
                } while (edadCliente < 5 || edadCliente > 120);
                if (edadCliente > 64) {
                    System.out.println("al pertenecer a la tercera edad tiene un descuento del " + (DESCUENTO_TERCERA_EDAD * 100) + "%");
                    tipoCliente = "Tercera Edad";
                } else if (tipoCliente != "Estudiante" && tipoCliente != "Tercera Edad") {
                    tipoCliente = "General";
                }
            }

            int porcentajeDescuento = 0,
                    pagoBase = 0;
            int reservarOtraEntrada = 1;

            switch (seleccion) {
                case 1:

                    if (ComprarOtraEntrada == 1) {
                        do {
                            if (ComprarOtraEntrada == 1) {
                                asientosComprados += 1; //bucle para repetir Compra
                                System.out.println(" ");
                                System.out.println("\033[34m*-- Ubicacion de Asientos --*");
                                System.out.println("\033[34m|  1.  Palco   ($30.000.-)  |");
                                System.out.println("\033[34m|  2.  Platea  ($15.000.-)  |");
                                System.out.println("\033[34m|  3.  Galeria ($13.000.-)  |");
                                System.out.println("\033[34m*---------------------------*");
                                System.out.println("\u001B[0m");
                                boolean correctorzona;
                                do { //corrector de ingreso de caracteres distintos a los que se pide
                                    do {
                                        try {
                                            correctorzona = false;
                                            System.out.print("Seleccione una ubicacion (1- Palco, 2- Platea o 3 - Galeria):");
                                            seleccionZona = scanner.nextInt();
                                        } catch (InputMismatchException ex) {
                                            System.out.println("Intente Nuevamente");
                                            scanner.next();
                                            correctorzona = true;
                                        }
                                    } while (correctorzona);
                                } while (seleccionZona != 1 && seleccionZona != 2 && seleccionZona != 3);
                                if (seleccionZona == 1) {
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Palco  $30.000     |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < palcoLargo; i++) {
                                        if (i < 10) {
                                            if (VectorPalco[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorPalco[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorPalco[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            if (VectorPalco[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorPalco[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorPalco[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println();
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Palco");
                                    do {      //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-14)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 14 || VectorPalco[asiento] == 2 || VectorPalco[asiento] == 3);
                                    VectorPalco[asiento] = 3;
                                    VectorAsientosComprados[asientosComprados] = asiento;
                                    VectorUbicaciones[asientosComprados] = 1;
                                    pagoBase = VALOR_PALCO;
                                    ubicacion = "Palco";
                                } else if (seleccionZona == 2) {
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Platea  $15.000    |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < plateaLargo; i++) {
                                        if (i < 10) {
                                            if (VectorPlatea[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorPlatea[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorPlatea[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            if (VectorPlatea[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorPlatea[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorPlatea[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println();
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Platea");
                                    do {          //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-47)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 47 || VectorPlatea[asiento] == 2 || VectorPlatea[asiento] == 3);
                                    VectorPlatea[asiento] = 3;
                                    VectorAsientosComprados[asientosComprados] = asiento;
                                    VectorUbicaciones[asientosComprados] = 2;
                                    pagoBase = VALOR_PLATEA;
                                    ubicacion = "Platea";
                                } else if (seleccionZona == 3) {
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Galeria  $13.000   |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < galeriaLargo; i++) {
                                        if (i < 10) {
                                            if (VectorGaleria[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorGaleria[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorGaleria[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            if (VectorGaleria[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorGaleria[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorGaleria[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println();
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Galeria");
                                    do {         //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-21)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 21 || VectorGaleria[asiento] == 2 || VectorGaleria[asiento] == 3);
                                    VectorGaleria[asiento] = 3;
                                    VectorAsientosComprados[asientosComprados] = asiento;
                                    VectorUbicaciones[asientosComprados] = 3;
                                    pagoBase = VALOR_GALERIA;
                                    ubicacion = "Galeria";
                                }
                                if (asientosComprados < 5) {
                                    boolean correctorOtraEntrada;
                                    do { //corrector de ingreso de caracteres distintos a los que se pide
                                        do {
                                            try {
                                                correctorOtraEntrada = false;
                                                System.out.print("¿Desea Comprar otra entrada? (1-Si o 2-No)");
                                                ComprarOtraEntrada = scanner.nextInt();
                                            } catch (InputMismatchException ex) {
                                                System.out.println("intente nuevamente");
                                                scanner.next();
                                                correctorOtraEntrada = true;
                                            }
                                        } while (correctorOtraEntrada);
                                    } while (ComprarOtraEntrada != 1 && ComprarOtraEntrada != 2);

                                    if (ComprarOtraEntrada == 1) {
                                        System.out.println("Debido al Exito de la Obra, solo podra Comprar un máximo de 5 entradas");
                                        ComprarOtraEntrada = 1;
                                    }
                                } else {
                                    ComprarOtraEntrada = 2;
                                }
                            }
                            nombreClientes.add(cliente);
                            edadClientes.add(edadCliente);
                            tipoClientes.add(tipoCliente);
                            costoBase.add(pagoBase);
                            Descuentos.add(porcentajeDescuento);
                            clienteZona.add(ubicacion);
                            asientoClientes.add(asiento);
                        } while (ComprarOtraEntrada == 1);
                        System.out.println("\033[34m*---------------------------*");
                        System.out.println("|        Teatro Moro        |");
                        System.out.println("\033[34m|---------------------------|");
                        System.out.println("|     Resumen de Compra     |");
                        System.out.println("\033[34m*---------------------------*");
                        System.out.println("\u001B[0m");
                        if (nombreClientes.isEmpty()) {
                            System.out.println("Aun no existen registros.");
                        } else {
                            System.out.println("Ventas realizadas: ");
                            int size = nombreClientes.size();
                            for (int i = 0; i < size; i++) {
                                System.out.println("\033[34m*----Entrada nro " + (i + 1) + "----*");
                                System.out.println("nombre " + nombreClientes.get(i) + ", edad " + edadClientes.get(i));
                                System.out.println("la entrada en " + clienteZona.get(i) + " tiene un costo de $" + costoBase.get(i));
                                System.out.println("al ser " + tipoClientes.get(i) + " tiene un descuento de " + Descuentos.get(i) + "%");
                                System.out.println("su asiento es el " + asientoClientes.get(i));
                                double desc = Descuentos.get(i);
                                double desc1 = desc / 100;
                                double baseCosto = costoBase.get(i);
                                double montoDescuento = baseCosto * desc1;
                                double subTotal = baseCosto - montoDescuento;
                                VdescSinDecimales = (int) subTotal;
                                System.out.println("Valor con Descuento $ " + VdescSinDecimales);
                                TotalAPagar += VdescSinDecimales;
                            }
                            System.out.println();
                            System.out.println("*\033[34m---------------------------*");
                            System.out.println("Total de entradas registradas: " + size + " entrada(s).");
                            System.out.println("Total A Pagar con Descuentos $" + TotalAPagar);
                            System.out.println("Disfrute de la Obra");
                            System.out.println("Fecha y hora de compra: " + fechas);
                            System.out.println("*\033[34m---------------------------*");
                            System.out.println();
                        }
                    }
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("\033[34m*---------------------------*");
                    System.out.println("|        Teatro Moro        |");
                    System.out.println("\033[34m|---------------------------|");
                    System.out.println("|     Resumen de ventas     |");
                    System.out.println("\033[34m*---------------------------*");
                    System.out.println("\u001B[0m");
                    if (nombreClientes.isEmpty()) {
                        System.out.println("Aun no existen registros.");
                        break;
                    } else {
                        int contadorEstudiante = 0, contadorTerceraEdad = 0, contadorGeneral = 0;
                        int ventaEstudiante = 0, ventaTerceraEdad = 0, ventaGeneral = 0;
                        System.out.println("Ventas realizadas: ");
                        int size = nombreClientes.size();
                        for (int i = 0; i < size; i++) {
                            double desc = Descuentos.get(i);
                            double desc1 = desc / 100;
                            double baseCosto = costoBase.get(i);
                            double montoDescuento = baseCosto * desc1;
                            double subTotal = baseCosto - montoDescuento;
                            VdescSinDecimales = (int) subTotal;
                            TotalAPagar += VdescSinDecimales;
                            if (tipoClientes.get(i) == "Estudiante") {
                                contadorEstudiante += 1;
                                ventaEstudiante += VdescSinDecimales;
                            } else if (tipoClientes.get(i) == "Tercera Edad") {
                                contadorTerceraEdad += 1;
                                ventaTerceraEdad += VdescSinDecimales;
                            } else if (tipoClientes.get(i) == "General") {
                                contadorGeneral += 1;
                                ventaGeneral += VdescSinDecimales;
                            }
                            System.out.println("entradas de Estudiantes " + contadorEstudiante);
                            System.out.println("total venta a estudiantes $ " + ventaEstudiante + " pesos");
                            System.out.println("entradas de Tercera Edad " + contadorTerceraEdad);
                            System.out.println("total venta a Tercera Edad $ " + ventaTerceraEdad + " pesos");
                            System.out.println("entradas General " + contadorGeneral);
                            System.out.println("total venta a estudiantes $ " + ventaGeneral + " pesos");
                        }
                        System.out.println("Total de entradas registradas: " + size + " entrada(s).");
                        //Final del detalle individual e inicio del monto total y despedida
                        System.out.println();
                        System.out.println("Total haber  $" + TotalAPagar);
                        System.out.println("*\033[34m---------------------------*");
                        System.out.println("Fecha y hora del resumen: " + fechas);
                        System.out.println();
                    }
                    scanner.nextLine();
                    break;
                case 3:
                    if (nombreClientes.isEmpty()) {
                        System.out.println("Aun no existen regsitros.");
                        break;
                    } else {
                        System.out.println("Ingrese el numero de boleta a imprimir: ");
                        int boleta = scanner.nextInt();
                        int size = nombreClientes.size();
                        if (boleta > size) {
                            System.out.println("boleta no exite, seleccione entre 1 y " + size);
                        } else {
                            System.out.println("\033[34m*----Entrada nro " + boleta + "----*");
                            System.out.println("nombre " + nombreClientes.get(boleta - 1) + ", edad " + edadClientes.get(boleta - 1));
                            System.out.println("al ser " + tipoClientes.get(boleta - 1) + " tiene un descuento de " + Descuentos.get(boleta - 1) + "%");
                            System.out.println("la entrada en " + clienteZona.get(boleta - 1) + " tiene un costo de $" + costoBase.get(boleta - 1));
                            System.out.println("su asiento es el " + asientoClientes.get(boleta - 1));
                            double desc = Descuentos.get(boleta - 1);
                            double desc1 = desc / 100;
                            double baseCosto = costoBase.get(boleta - 1);
                            double montoDescuento = baseCosto * desc1;
                            double subTotal = baseCosto - montoDescuento;
                            VdescSinDecimales = (int) subTotal;
                            System.out.println("Valor con Descuento $ " + VdescSinDecimales);
                            System.out.println("Total de entradas registradas: " + size + " entrada(s).");
                            System.out.println();
                            System.out.println("Total A Pagar con Descuentos $" + VdescSinDecimales);
                            System.out.println("Disfrute de la Obra");
                            System.out.println("*\033[34m---------------------------*");
                            System.out.println("Fecha y hora de compra: " + fechas);
                            System.out.println();
                        }
                    }
                    scanner.nextLine();
                    break;
                case 4:
                    if (reservarOtraEntrada == 1) {
                        do {
                            if (reservarOtraEntrada == 1) {
                                asientosReservados++;
                                System.out.println(" ");
                                System.out.println("\033[34m*--  Reserva de Entradas  --*");
                                System.out.println("\033[34m|  1.  Palco                |");
                                System.out.println("\033[34m|  2.  Platea               |");
                                System.out.println("\033[34m|  3.  Galeria              |");
                                System.out.println("\033[34m*---------------------------*");
                                System.out.println("\u001B[0m");
                                // System.out.print("Seleccione una zona (1- Palco, 2- Platea o 3 - Galeria):");
                                boolean correctorzona;
                                do { //corrector de ingreso de caracteres distintos a los que se pide
                                    do {
                                        try {
                                            correctorzona = false;
                                            System.out.print("Seleccione una zona (1- Palco, 2- Platea o 3 - Galeria):");
                                            seleccionZona = scanner.nextInt();
                                        } catch (InputMismatchException ex) {
                                            System.out.println("Intente Nuevamente");
                                            scanner.next();
                                            correctorzona = true;
                                        }
                                    } while (correctorzona);
                                } while (seleccionZona != 1 && seleccionZona != 2 && seleccionZona != 3);
                                if (seleccionZona == 1) {
                                    // Mostrar Asientos Disponibles en Palco
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Palco  $30.000     |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < palcoLargo; i++) {
                                        if (i < 10) {
                                            if (VectorPalco[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorPalco[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorPalco[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            if (VectorPalco[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorPalco[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorPalco[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println(); // Salto de linea cada 10 asientos
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Palco");
                                    do {      //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-14)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 14 || VectorPalco[asiento] == 2 || VectorPalco[asiento] == 3);
                                    VectorPalco[asiento] = 2;
                                    VectorAsientosReservados[asientosReservados] = asiento;
                                    VectorUbicaciones[asientosReservados] = 1;
                                } else if (seleccionZona == 2) {
                                    // Mostrar Asientos Disponibles en Platea
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Platea  $15.000    |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < plateaLargo; i++) {
                                        if (i < 10) {
                                            //pinto en verde(42) los disponibles, gris(47) reservados y rojo(41) vendidos
                                            if (VectorPlatea[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorPlatea[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorPlatea[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            //pinto en verde los disponibles si no en rojo
                                            //pinto en verde(42) los disponibles, gris(47) reservados y rojo(41) vendidos
                                            if (VectorPlatea[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorPlatea[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorPlatea[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println(); // Salto de linea cada 10 asientos
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Platea");
                                    do {          //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-47)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 47 || VectorPlatea[asiento] == 2 || VectorPlatea[asiento] == 3);
                                    VectorPlatea[asiento] = 2;
                                    VectorAsientosReservados[asientosReservados] = asiento;
                                    VectorUbicaciones[asientosReservados] = 2;
                                } else if (seleccionZona == 3) {
                                    // Mostrar Asientos Disponibles en Galeria
                                    System.out.println("\033[34m*-------------------------------*");
                                    System.out.println("|Asientos en Galeria  $13.000   |");
                                    System.out.println("\033[34m*-------------------------------*\u001B[0m");
                                    for (int i = 1; i < galeriaLargo; i++) {
                                        if (i < 10) {
                                            //pinto en verde(42) los disponibles, gris(47) reservados y rojo(41) vendidos
                                            if (VectorGaleria[i] == 1) {
                                                System.out.print("\033[42m0" + i + "-D ");
                                            } else if (VectorGaleria[i] == 2) {
                                                System.out.print("\033[47m0" + i + "-R ");
                                            } else if (VectorGaleria[i] == 3) {
                                                System.out.print("\033[41m0" + i + "-V ");
                                            }
                                        } else {
                                            //pinto en verde los disponibles si no en rojo
                                            //pinto en verde(42) los disponibles, gris(47) reservados y rojo(41) vendidos
                                            if (VectorGaleria[i] == 1) {
                                                System.out.print("\033[42m" + i + "-D ");
                                            } else if (VectorGaleria[i] == 2) {
                                                System.out.print("\033[47m" + i + "-R ");
                                            } else if (VectorGaleria[i] == 3) {
                                                System.out.print("\033[41m" + i + "-V ");
                                            }
                                        }
                                        if (i % 10 == 0) {
                                            System.out.println(); // Salto de linea cada 10 asientos
                                        }
                                    }
                                    System.out.println("\u001B[0m");
                                    System.out.println("Ha seleccionado Galeria");
                                    do {         //verifica que el asiento este disponible
                                        System.out.print("Favor seleccione un asiento  (1-21)");
                                        asiento = scanner.nextInt();
                                    } while (asiento < 1 || asiento > 21 || VectorGaleria[asiento] == 2 || VectorGaleria[asiento] == 3);
                                    VectorGaleria[asiento] = 2;
                                    VectorAsientosReservados[asientosReservados] = asiento;
                                    VectorUbicaciones[asientosReservados] = 3;
                                }
                                if (asientosReservados < 5) {

                                    boolean correctorOtraEntrada;
                                    do { //corrector de ingreso de caracteres distintos a los que se pide
                                        do {
                                            try {
                                                correctorOtraEntrada = false;
                                                System.out.print("¿Desea reservar otra entrada? (1-Si o 2-No)");
                                                reservarOtraEntrada = scanner.nextInt();
                                            } catch (InputMismatchException ex) {
                                                System.out.println("intente nuevamente");
                                                scanner.next();
                                                correctorOtraEntrada = true;
                                            }
                                        } while (correctorOtraEntrada);
                                    } while (reservarOtraEntrada != 1 && reservarOtraEntrada != 2);

                                    if (reservarOtraEntrada == 1) {
                                        System.out.println("Debido al Exito de la Obra, solo podra reservar un máximo de 5 entradas");
                                        reservarOtraEntrada = 1;
                                    }
                                } else {
                                    reservarOtraEntrada = 2;
                                }
                                VectorIdReservados[asientosReservados] = asientosReservados;
                            }
                            nombreClientes.add(cliente);
                            edadClientes.add(edadCliente);
                            tipoClientes.add(tipoCliente);
                            costoBase.add(pagoBase);
                            Descuentos.add(porcentajeDescuento);
                            clienteZona.add(ubicacion);
                            asientoClientes.add(asiento);
                        } while (reservarOtraEntrada == 1);
                        System.out.println("\033[34m*---------------------------*");
                        System.out.println("|        Teatro Moro        |");
                        System.out.println("\033[34m|---------------------------|");
                        System.out.println("|     Resumen de Reserva    |");
                        System.out.println("\033[34m*---------------------------*");
                        System.out.println("\u001B[0m");
                        for (int i = 1; i <= asientosReservados; i++) {

                            System.out.println("\033[46m Entrada " + i + "\u001B[0m");
                            System.out.println("ID de Reserva " + VectorIdReservados[i]);
                            if (VectorUbicaciones[i] == 1) {
                                System.out.println("Asiento Reservado en Palco");
                                System.out.println("Valor Asiento $ " + VPalco);
                                VasientoReservado = VPalco;
                            }
                            if (VectorUbicaciones[i] == 2) {
                                System.out.println("Asiento Reservado en Platea");
                                System.out.println("Valor Asiento $ " + VPlatea);
                                VasientoReservado = VPlatea;
                            }
                            if (VectorUbicaciones[i] == 3) {
                                System.out.println("Asiento Reservado en Galeria");
                                System.out.println("Valor Asiento $ " + VGaleria);
                                VasientoReservado = VGaleria;
                            }
                            System.out.println("Asiento Reservado n-" + VectorAsientosReservados[i]);
                            if (VectorTipo[i] == 1) {
                                System.out.println("Descuento para estudiantes del 10%");
                                VdescuentoReserva = VasientoReservado * 0.90;
                                VdescSinDecimales = (int) VdescuentoReserva;
                                System.out.println("Valor con Descuento $ " + VdescSinDecimales);

                                TotalAPagar += VdescSinDecimales;
                            }
                            if (VectorTipo[i] == 2) {
                                System.out.println("Descuento para Tercera Edad del 15%");
                                VdescuentoReserva = VasientoReservado * 0.85;
                                VdescSinDecimales = (int) VdescuentoReserva;
                                System.out.println("Valor con Descuento $ " + VdescSinDecimales);
                                TotalAPagar += VdescSinDecimales;
                            }
                            if (VectorTipo[i] == 3) {
                                System.out.println("No Tiene Descuentos");
                                System.out.println("Valor a Pagar $ " + VasientoReservado);
                                TotalAPagar += VasientoReservado;
                            }
                            System.out.println("*\033[34m---------------------------*");
                        }
                        //Final del detalle individual e inicio del monto total y despedida
                        System.out.println();
                        System.out.println("Total A Pagar con Descuentos $" + TotalAPagar);
                        System.out.println("Al pasar Por Boleteria debera pagar las entradas");
                        System.out.println("Disfrute de la Obra");
                        System.out.println("*\033[34m---------------------------*");
                        System.out.println("Fecha y hora de reserva: " + fechas);
                        System.out.println();
                    }
                    scanner.nextLine();
                    break;
                case 5:
                    if (nombreClientes.isEmpty()) {
                        System.out.println("Aun no existen registros.");
                        break;
                    } else {
                        boolean modificado = false;
                        System.out.println("\n");
                        System.out.println("\033[34m5. Eliminación de Reservas");
                        System.out.print("Ingrese el Id de Reserva: ");
                        int idReserva = scanner.nextInt();
                        if (VectorIdReservados[idReserva] != 0 && VectorIdReservados[idReserva] == idReserva) {
                            VectorIdReservados[idReserva] = 0;
                            int zonaEliminar = VectorUbicaciones[idReserva];
                            int asientoEliminar = VectorAsientosReservados[idReserva];
                            if (zonaEliminar == 1) {
                                VectorPalco[asientoEliminar] = 1;
                            } else if (zonaEliminar == 2) {
                                VectorPlatea[asientoEliminar] = 1;
                            } else if (zonaEliminar == 3) {
                                VectorGaleria[asientoEliminar] = 1;
                            }
                            VectorTipo[idReserva] = 0;
                            VectorUbicaciones[idReserva] = 0;
                            VectorAsientosReservados[idReserva] = 0;
                            System.out.println("Reserva " + idReserva + " Eliminada");
                            modificado = true;
                            scanner.nextLine();
                            break;
                        }
                        if (!modificado) {
                            System.out.println("No se encontró la reserva.\n");
                        }
                    }
                    scanner.nextLine();
                    break;
                case 6:
                    System.out.println("asientosReservados " + asientosReservados);
                    if (nombreClientes.isEmpty()) {
                        System.out.println("Aun no existen registros.");
                        break;
                    } else {
                        boolean modificado = false;
                        System.out.println("\n");
                        System.out.println("\033[34m6. Listar Reservas");
                        for (int i = 1; i <= asientosReservados; i++) {
                            if (VectorIdReservados[i] != 0) {
                                System.out.println("\033[46m Entrada " + i + "\u001B[0m");
                                System.out.println("ID de Reserva " + VectorIdReservados[i]);
                                if (VectorUbicaciones[i] == 1) {
                                    System.out.println("Asiento Reservado en Palco");
                                    System.out.println("Valor Asiento $ " + VPalco);
                                    VasientoReservado = VPalco;
                                }
                                if (VectorUbicaciones[i] == 2) {
                                    System.out.println("Asiento Reservado en Platea");
                                    System.out.println("Valor Asiento $ " + VPlatea);
                                    VasientoReservado = VPlatea;
                                }
                                if (VectorUbicaciones[i] == 3) {
                                    System.out.println("Asiento Reservado en Galeria");
                                    System.out.println("Valor Asiento $ " + VGaleria);
                                    VasientoReservado = VGaleria;
                                }
                                System.out.println("Asiento Reservado n-" + VectorAsientosReservados[i]);
                                if (VectorTipo[i] == 1) {
                                    System.out.println("Descuento para estudiantes del 10%");
                                    VdescuentoReserva = VasientoReservado * 0.90;
                                    VdescSinDecimales = (int) VdescuentoReserva;
                                    System.out.println("Valor con Descuento $ " + VdescSinDecimales);
                                    TotalAPagar += VdescSinDecimales;
                                }
                                if (VectorTipo[i] == 2) {
                                    System.out.println("Descuento para Tercera Edad del 15%");
                                    VdescuentoReserva = VasientoReservado * 0.85;
                                    VdescSinDecimales = (int) VdescuentoReserva;
                                    System.out.println("Valor con Descuento $ " + VdescSinDecimales);
                                    TotalAPagar += VdescSinDecimales;
                                }
                                if (VectorTipo[i] == 3) {
                                    System.out.println("No Tiene Descuentos");
                                    System.out.println("Valor a Pagar $ " + VasientoReservado);
                                    TotalAPagar += VasientoReservado;
                                }
                                System.out.println("*\033[34m---------------------------*");
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("\n");
                    System.out.println("\033[34m7. Salir");
                    System.out.println("¿Realmente desea Salir?(1-si o 2- no)");
                    boolean respuestaSalir;
                    int opcionSalir = 0;
                    do { //corrector de ingreso de caracteres distintos a los que se pide
                        do {
                            try {
                                respuestaSalir = false;
                                opcionSalir = scanner.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Intente Nuevamente");
                                scanner.next();
                                respuestaSalir = true;
                            }
                        } while (respuestaSalir);
                    } while (opcionSalir != 1 && opcionSalir != 2);
                    if (opcionSalir == 1) {
                        System.out.println("Saliendo del Sistema");
                        System.out.println("Gracias por preferir Teatro Moro");
                        salir = true;
                    } else if (opcionSalir == 2) {
                        System.out.println("Volviendo al menu");
                        scanner.nextLine();
                    }
                    break;
            }
        }
    }
}
