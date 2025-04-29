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
        double Vdescuento=0;
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
     //Variables Globales
         
       int VectorPalco[]=new int[15];
       int VectorPlatea[]=new int[48];
       int VectorGaleria[]=new int[22];
       int asientosComprados=0; 
       int ComprarOtraEntrada=0, estudiante=0;
       int SeleccionZona=0, asiento=0, edad=0;
       int VPalco=30000, VPlatea=15000, VGaleria=13000;
       
     
       int VectorAsientosComprados[]=new int[6];
       int VectorUbicaciones[]=new int[6];
       int VectorTipo[]=new int[6];
      
       //Random r = new Random();
       int azar = 0;
       // int VectorDesc[]=new int[6];
        LocalDateTime fecha = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    String fechas = fecha.format(formato);
     //inicializar Vectores como true (disponibles)  
     for(int i=0;i<VectorPalco.length ;i++) {
            VectorPalco[i]=1;
        }
     for(int i=0;i<VectorPlatea.length ;i++) {
            VectorPlatea[i]=1;
        }
     for(int i=0;i<VectorGaleria.length ;i++) {
            VectorGaleria[i]=1;
        }
     for(int i=0;i<VectorAsientosComprados.length ;i++) {
            VectorAsientosComprados[i]=0;
        }    
      int edadCliente=0;
    int seleccionZona=0;
    String tipoCliente="";
    String ubicacion="";

    int VdescSinDecimales;
    String cliente;
   // int valorEntrada=0, 
    
        boolean salir = false;
        System.out.println("\033[34m*---------------------------*");
        System.out.println("\033[34m| Bienvenido al Teatro Moro |");
        System.out.println("\033[34m*---------------------------*");
        
       int TotalAPagar=0; 
        while (!salir) { // Menú principal
            System.out.println("\033[34m*-----  Menú Principal -----*");
            System.out.println("\033[34m|1.  Venta de entradas      |");//listo
            System.out.println("\033[34m|2.  Resumen de ventas      |");//listo
            System.out.println("\033[34m|3.  Generar boleta         |");//listo
            System.out.println("\033[34m|4.  Ingresos Totales       |");
            System.out.println("\033[34m|5.  Salir                  |");//listo
            System.out.println("\033[34m*---------------------------*");
            System.out.println("\u001B[0m");  
            System.out.print("Seleccione una opción: ");
           
            String opcionMenu = scanner.nextLine(); // Leemos la opción como texto
            int seleccion = 0;

            // Validamos que la opción sea un número entre 1 y 5
            try {
                seleccion = Integer.parseInt(opcionMenu); // Convertimos texto a número
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número del 1 al 10.\n");
                continue;
            }
         if (seleccion < 1 || seleccion > 5) {
                System.out.println("Opción fuera de rango. Intente de nuevo.\n");
                continue;
            }
    switch (seleccion) {
     case 1:
                           ComprarOtraEntrada=1;
                    int porcentajeDescuento=0, pagoBase=0;       
         //solicitar datos del Cliente
         System.out.print("Ingrese el nombre del cliente: ");
                cliente = scanner.nextLine();

                // Validación de nombre vacío o muy corto
                while (cliente.isEmpty() || cliente.trim().length() < 3) {
                    System.out.print("Nombre inválido. Intente nuevamente: ");
                    cliente = scanner.nextLine();
                }
            System.out.print("Hola "+ cliente + ", ¿usted es Estudiante?(1-si o 2- no)");
               boolean esEstudiante;
               int respuestaEstudiante=0;
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
        }while(respuestaEstudiante !=1 &&  respuestaEstudiante !=2);
            if(respuestaEstudiante==1){  
             System.out.println("Como estudiante tienes un descuento del "+ (DESCUENTO_ESTUDIANTE*100)+ "%");
             tipoCliente="Estudiante";
             porcentajeDescuento=10;
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
        }while(edadCliente < 5 || edadCliente > 120);
         
            if (edadCliente>64){
                System.out.println("al pertenecer a la tercera edad tiene un descuento del "+ (DESCUENTO_TERCERA_EDAD*100)+"%");
                tipoCliente="Tercera Edad";
                porcentajeDescuento=15;
            }else if (tipoCliente!="Estudiante" && tipoCliente!="Tercera Edad")
            {
             tipoCliente="General";
             porcentajeDescuento=0;
            }
if(ComprarOtraEntrada==1){
   do{
     if (ComprarOtraEntrada==1) {
         asientosComprados+=1;
    //buble para repetir Compra


System.out.println(" ");
            System.out.println("\033[34m*--  Compra de Entradas  --*");
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
        }while(seleccionZona !=1 &&  seleccionZona !=2 &&  seleccionZona !=3);
                if (seleccionZona==1){
                            // Mostrar Asientos Disponibles en Palco
         System.out.println("\033[34m*-------------------------------*");
                 System.out.println("|Asientos en Palco  $30.000     |");
         System.out.println("\033[34m*-------------------------------*\u001B[0m");
         for(int i=1;i<VectorPalco.length ;i++) {
             if (i<10) {
             //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorPalco[i]==1){
                 System.out.print("\033[42m0" + i + "-D ");
            }else if(VectorPalco[i]==2){
                 System.out.print("\033[47m0" + i + "-R ");
            }else if(VectorPalco[i]==3){
                 System.out.print("\033[41m0" + i + "-V ");
            }
             }else {
         //pinto en verde los disponibles si no en rojo
               //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorPalco[i]==1){
                 System.out.print("\033[42m" + i + "-D ");
            }else if(VectorPalco[i]==2){
                 System.out.print("\033[47m" + i + "-R ");
            }else if(VectorPalco[i]==3){
                 System.out.print("\033[41m" + i + "-V ");
            }
             }      
         if (i % 10 == 0) {
            System.out.println(); // Salto de linea cada 10 asientos
         }
         }
          System.out.println("\u001B[0m");  
          System.out.println("Ha seleccionado Palco"); 
            do{      //verifica que el asiento este disponible
                   System.out.print("Favor seleccione un asiento  (1-14)");
                   asiento=scanner.nextInt();
               }while(asiento <1 || asiento >14 || VectorPalco[asiento]==2 || VectorPalco[asiento]==3);
            VectorPalco[asiento]=3;
            VectorAsientosComprados[asientosComprados]=asiento;
            VectorUbicaciones[asientosComprados]=1;
                    pagoBase=VALOR_PALCO;
                    ubicacion="Palco";
                 }else if (seleccionZona==2){
                // Mostrar Asientos Disponibles en Platea
         System.out.println("\033[34m*-------------------------------*");
                 System.out.println("|Asientos en Platea  $15.000    |");
         System.out.println("\033[34m*-------------------------------*\u001B[0m");
          for(int i=1;i<VectorPlatea.length ;i++) {
             if (i<10) {
             //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorPlatea[i]==1){
                 System.out.print("\033[42m0" + i + "-D ");
            }else if(VectorPlatea[i]==2){
                 System.out.print("\033[47m0" + i + "-R ");
            }else if(VectorPlatea[i]==3){
                 System.out.print("\033[41m0" + i + "-V ");
            }
             }else {
         //pinto en verde los disponibles si no en rojo
               //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorPlatea[i]==1){
                 System.out.print("\033[42m" + i + "-D ");
            }else if(VectorPlatea[i]==2){
                 System.out.print("\033[47m" + i + "-R ");
            }else if(VectorPlatea[i]==3){
                 System.out.print("\033[41m" + i + "-V ");
            }
             }      
         if (i % 10 == 0) {
            System.out.println(); // Salto de linea cada 10 asientos
         }
         }
          System.out.println("\u001B[0m");
          System.out.println("Ha seleccionado Platea"); 
            do{          //verifica que el asiento este disponible
                   System.out.print("Favor seleccione un asiento  (1-47)");
                   asiento=scanner.nextInt();
               }while(asiento <1 || asiento >47|| VectorPlatea[asiento]==2 || VectorPlatea[asiento]==3);
            VectorPlatea[asiento]=3;
            VectorAsientosComprados[asientosComprados]=asiento;
            VectorUbicaciones[asientosComprados]=2;
            pagoBase=VALOR_PLATEA; 
            ubicacion="Platea";
                 } else if (seleccionZona==3){
               // Mostrar Asientos Disponibles en Galeria
         System.out.println("\033[34m*-------------------------------*");
                 System.out.println("|Asientos en Galeria  $13.000   |");
         System.out.println("\033[34m*-------------------------------*\u001B[0m");
          for(int i=1;i<VectorGaleria.length ;i++) {
             if (i<10) {
             //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorGaleria[i]==1){
                 System.out.print("\033[42m0" + i + "-D ");
            }else if(VectorGaleria[i]==2){
                 System.out.print("\033[47m0" + i + "-R ");
            }else if(VectorGaleria[i]==3){
                 System.out.print("\033[41m0" + i + "-V ");
            }
             }else {
         //pinto en verde los disponibles si no en rojo
               //pinto en verde(42) los disponibles, gris(47) s y rojo(41) vendidos
                if (VectorGaleria[i]==1){
                 System.out.print("\033[42m" + i + "-D ");
            }else if(VectorGaleria[i]==2){
                 System.out.print("\033[47m" + i + "-R ");
            }else if(VectorGaleria[i]==3){
                 System.out.print("\033[41m" + i + "-V ");
            }
             }      
         if (i % 10 == 0) {
            System.out.println(); // Salto de linea cada 10 asientos
         }
         }
          System.out.println("\u001B[0m");  
          System.out.println("Ha seleccionado Galeria"); 
            do{         //verifica que el asiento este disponible
                   System.out.print("Favor seleccione un asiento  (1-21)");
                   asiento=scanner.nextInt();
               }while(asiento <1 || asiento >21 || VectorGaleria[asiento]==2 || VectorGaleria[asiento]==3);
            VectorGaleria[asiento]=3;
            VectorAsientosComprados[asientosComprados]=asiento;
            VectorUbicaciones[asientosComprados]=3;      
            pagoBase=VALOR_GALERIA;
            ubicacion="Galeria";
                 }
     if (asientosComprados<5){
         
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
        }while(ComprarOtraEntrada !=1 &&  ComprarOtraEntrada !=2);

         
         if (ComprarOtraEntrada==1){
          System.out.println("Debido al Exito de la Obra, solo podra Comprar un máximo de 5 entradas");
          ComprarOtraEntrada=1;
     }
     }else {
         ComprarOtraEntrada=2;
     }
     }
       nombreClientes.add(cliente);// guardamos el nombre
       edadClientes.add(edadCliente);// guardamos la edad del cliente
       tipoClientes.add(tipoCliente); //guardamos el tipo cliente
       costoBase.add(pagoBase); //guardamos el monto sin descuento
       Descuentos.add(porcentajeDescuento); //guardamos el descuento aplicado
       clienteZona.add(ubicacion);//guardamos la zona donde compro
       asientoClientes.add(asiento);//guardamos el asiento
         }while(ComprarOtraEntrada ==1);
   
   //inicio mensaje de resumen de Compra
        System.out.println("\033[34m*---------------------------*");
                System.out.println("|        Teatro Moro        |");
        System.out.println("\033[34m|---------------------------|");
                System.out.println("|     Resumen de Compra     |");
        System.out.println("\033[34m*---------------------------*");
        System.out.println("\u001B[0m");   
    //detalles de las Compras recorriendo los vectores
 
            if(nombreClientes.isEmpty()){
                    System.out.println("Aun no existen regsitros.");
                }else{
                    System.out.println("Ventas realizadas: ");
                    for (int i = 0; i < nombreClientes.size(); i++){
                        System.out.println("\033[34m*----Entrada nro "+ (i+1) + "----*");
                        System.out.println("nombre "+ nombreClientes.get(i)+", edad "+ edadClientes.get(i));
                        System.out.println("al ser "+ tipoClientes.get(i)+" tiene un descuento de "+ Descuentos.get(i)+"%");
                        System.out.println("la entrada en "+ clienteZona.get(i)+" tiene un costo de $"+ costoBase.get(i));
                        System.out.println("su asiento es el "+ asientoClientes.get(i));
                        double desc=Descuentos.get(i);
                        double desc1=desc / 100;
                        double baseCosto=costoBase.get(i);
                        double montoDescuento=baseCosto*desc1;
                        double subTotal=baseCosto-montoDescuento;
                        VdescSinDecimales= (int)subTotal;
                       System.out.println("Valor con Descuento $ "+ VdescSinDecimales);
                       TotalAPagar=TotalAPagar+VdescSinDecimales;
                    System.out.println("Total de entradas registradas: "+ nombreClientes.size() +" entrada(s).");
   //Final del detalle individual e inicio del monto total y despedida
   System.out.println();
             System.out.println("Total A Pagar con Descuentos $" +TotalAPagar);
             System.out.println("Disfrute de la Obra");
              System.out.println("*\033[34m---------------------------*");
              System.out.println("Fecha y hora de compra: " + fechas);
          System.out.println();  
}
}
}
                 scanner.nextLine();
                 break;  
        case 2: 
   //inicio mensaje de resumen de ventas
        System.out.println("\033[34m*---------------------------*");
                System.out.println("|        Teatro Moro        |");
        System.out.println("\033[34m|---------------------------|");
                System.out.println("|     Resumen de ventas     |");
        System.out.println("\033[34m*---------------------------*");
        System.out.println("\u001B[0m");   
    //detalles de las Compras recorriendo los vectores
 
            if(nombreClientes.isEmpty()){
                    System.out.println("Aun no existen regsitros.");
                }else{
                 int contadorEstudiante=0, contadorTerceraEdad=0,contadorGeneral=0;
                 int ventaEstudiante=0, ventaTerceraEdad=0,ventaGeneral=0;
                    System.out.println("Ventas realizadas: ");
                    for (int i = 0; i < nombreClientes.size(); i++){
                        double desc=Descuentos.get(i);
                        double desc1=desc / 100;
                        double baseCosto=costoBase.get(i);
                        double montoDescuento=baseCosto*desc1;
                        double subTotal=baseCosto-montoDescuento;
                        VdescSinDecimales= (int)subTotal;
                       TotalAPagar=TotalAPagar+VdescSinDecimales;
                               if (tipoClientes.get(i)=="Estudiante"){
                            contadorEstudiante+=1;
                            ventaEstudiante+=VdescSinDecimales;
                        }else if(tipoClientes.get(i)=="Tercera Edad"){
                            contadorTerceraEdad+=1;
                            ventaTerceraEdad+=VdescSinDecimales;
                        }else if(tipoClientes.get(i)=="General"){
                            contadorGeneral+=1;
                            ventaGeneral+=VdescSinDecimales;
                        } 
                        System.out.println("entradas de Estudiantes "+contadorEstudiante);
                        System.out.println("total venta a estudiantes $ "+ventaEstudiante+" pesos" );
                        System.out.println("entradas de Tercera Edad "+contadorTerceraEdad);
                        System.out.println("total venta a Tercera Edad $ "+ventaTerceraEdad+" pesos" );
                        System.out.println("entradas General "+contadorGeneral);
                        System.out.println("total venta a estudiantes $ "+ventaGeneral+" pesos" );
}                       
                    System.out.println("Total de entradas registradas: "+ nombreClientes.size() +" entrada(s).");
   //Final del detalle individual e inicio del monto total y despedida
   System.out.println();
             System.out.println("Total haber  $" +TotalAPagar);
              System.out.println("*\033[34m---------------------------*");
              System.out.println("Fecha y hora del resumen: " + fechas);
          System.out.println();  
}
            scanner.nextLine();     
            break;
        case 3:
                        if(nombreClientes.isEmpty()){
                    System.out.println("Aun no existen regsitros.");
                }else{
                    System.out.println("Ingrese el numero de boleta a imprimir: ");
                   int boleta=scanner.nextInt();
                    if (boleta> nombreClientes.size()){
                        System.out.println("boleta no exite, seleccione entre 1 y "+nombreClientes.size());
                    }else{
                        System.out.println("\033[34m*----Entrada nro "+ boleta + "----*");
                        System.out.println("nombre "+ nombreClientes.get(boleta-1)+", edad "+ edadClientes.get(boleta-1));
                        System.out.println("al ser "+ tipoClientes.get(boleta-1)+" tiene un descuento de "+ Descuentos.get(boleta-1)+"%");
                        System.out.println("la entrada en "+ clienteZona.get(boleta-1)+" tiene un costo de $"+ costoBase.get(boleta-1));
                        System.out.println("su asiento es el "+ asientoClientes.get(boleta-1));
                        double desc=Descuentos.get(boleta-1);
                        double desc1=desc / 100;
                        double baseCosto=costoBase.get(boleta-1);
                        double montoDescuento=baseCosto*desc1;
                        double subTotal=baseCosto-montoDescuento;
                        VdescSinDecimales= (int)subTotal;
                       System.out.println("Valor con Descuento $ "+ VdescSinDecimales);
                       TotalAPagar=TotalAPagar+VdescSinDecimales;
                    System.out.println("Total de entradas registradas: "+ nombreClientes.size() +" entrada(s).");
   //Final del detalle individual e inicio del monto total y despedida
   System.out.println();
             System.out.println("Total A Pagar con Descuentos $" +TotalAPagar);
             System.out.println("Disfrute de la Obra");
              System.out.println("*\033[34m---------------------------*");
              System.out.println("Fecha y hora de compra: " + fechas);
          System.out.println();  
}
}
      scanner.nextLine();     
            break;     
        case 5: 
            System.out.println("\n");
            System.out.println("\033[34m5. Salir");
                        System.out.println("¿Realmente desea Salir?(1-si o 2- no)"); 
               boolean respuestaSalir;
               int opcionSalir=0;
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
        }while(opcionSalir !=1 &&  opcionSalir !=2);
         if(opcionSalir==1){  
             System.out.println("Saliendo del Sistema");
             System.out.println("Gracias por preferir Teatro Moro");
            salir = true; 
         } 
         else if(opcionSalir==2){  
             System.out.println("Volviendo al menu");
             scanner.nextLine();
         }
                 break;
           
                 
    }
        
}
}
}

