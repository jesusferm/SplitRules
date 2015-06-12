/*
 * Clase: MethodsSplitRules.java
 * Descripción: Clase en la que se declaran los métodos utilizado en el
 *              programa que extrae la reglas del archivo de salida de
 *              algoritmos de árboles clasificadores.
 * Fecha de inicio: 25 mayo 2015.
 */
package splitrulesclassifiertree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Merino Merino Jesús Fernando
 * @author Santiago López José Luís
 */
public class MethodsSplitRules {
    
    public void separarReglas(JTextArea jTexArea, String nomArchSalida){
        String str;
        int lin=0;
        BufferedReader reader = new BufferedReader(
                new StringReader(jTexArea.getText()));
        File f;
	f = new File(nomArchSalida);
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            while ((str = reader.readLine()) != null) {
                if (str.length() > 0 && lin <= 10){
                    wr.write("R"+(lin+1)+":\n"+str);
                }
                lin++;
            }
            wr.close(); /*Cerrando archivo de escritura*/
            bw.close(); /*Cerrando apertura de archivo*/
            JOptionPane.showMessageDialog(null, "La reglas se han extraído correctamente.", "Reglas extraídas",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch(IOException e) {
            System.err.println("Error econtrado: "+e.getMessage());
        }
    }
    
    public void recorrerTextArea(JTextArea jTexArea, String nomArchSalida){
        String str;
        int lin=0;
        BufferedReader reader = new BufferedReader(
                new StringReader(jTexArea.getText()));
        File f;
	f = new File(nomArchSalida);
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            while ((str = reader.readLine()) != null) {
                if (str.length() > 0 && lin <= 10){
                    wr.write("R"+(lin+1)+":\n"+str);
                }
                lin++;
            }
            wr.close(); /*Cerrando archivo de escritura*/
            bw.close(); /*Cerrando apertura de archivo*/
            JOptionPane.showMessageDialog(null, "La reglas se han extraído correctamente.", "Reglas extraídas",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch(IOException e) {
            System.err.println("Error econtrado: "+e.getMessage());
        }
    }

    /**
     * Método que devuelve la ruta de un archivo, sin el nombre del archivo, es
     * decir, únicamente la ruta.
     * 
     * @param ruta
     * @return String
     */
    public String getPathFile(String ruta){
        /*Método que devuleve el ruta del archivo abierto sin el nombre del
        mismo*/
        int i;
        String rutaArch="";
        for(i=ruta.length()-1;i>=0;i--){
            if(ruta.charAt(i)==System.getProperty("file.separator").charAt(0)){
                rutaArch = ruta.substring(0, i);
                break;
            }
        }
        return rutaArch;
    }
    
    /**
     * Abre una ventana de dialogo para guardar un archivo.
     *
     * @param rutaArch
     * @return String
     */
    public String getNameFileToSave(String rutaArch){
        String nomArchSalida="";
        JFileChooser jFGuardar = new JFileChooser() {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists()) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "¿Reemplazar el archivo existente?",
                            "El archivo ya existe",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            cancelSelection();
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                    }
                }
                super.approveSelection();
            }
        };
        
        jFGuardar.setDialogTitle("Guardar en: "+getPathFile(rutaArch));
        jFGuardar.setCurrentDirectory(new File(getPathFile(rutaArch)));
        jFGuardar.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt).", "txt"));
        jFGuardar.setAcceptAllFileFilterUsed(false);
        jFGuardar.setSelectedFile(new File("Reglas_"+getNameFileFromPath(rutaArch)));
        int actionDialog = jFGuardar.showSaveDialog(null);
        if( actionDialog == JFileChooser.APPROVE_OPTION ){
            nomArchSalida = jFGuardar.getCurrentDirectory()+System.getProperty("file.separator")
                    +getNameFileWithoutExt(jFGuardar.getSelectedFile().getName())+".txt";
        }
        return nomArchSalida;
    }
    
    /**
     * Devuelve el nombre del archivo con extensión, pero sin la ruta del mismo.
     * 
     * @param ruta
     * @return String
     */
    public String getNameFileFromPath(String ruta){
        /*Método que devuleve el nombre del archivo abierto a partir de la ruta
        completa*/
        int i;
        String nomArch="";
        for(i=ruta.length()-1;i>=0;i--){
            if(ruta.charAt(i)==System.getProperty("file.separator").charAt(0)){
                nomArch = ruta.substring(i+1, ruta.length());
                break;
            }
        }
        return nomArch;
    }
    
    /**
     * Devuelve el nombre del archivo ingresado por el usuario, sin contemplar
     * la extensión del mismo.
     * 
     * @param nomArchIngrUser
     * @return String
     */
    public String getNameFileWithoutExt(String nomArchIngrUser){
        /*Método que devuleve el nombre del archivo a guardar sin extensión*/
        int i;
        boolean sinExt = false;
        String nomArch="";
        for(i=nomArchIngrUser.length()-1;i>=0;i--){
            if(nomArchIngrUser.charAt(i)=='.'){
                nomArch = nomArchIngrUser.substring(0, i);
                sinExt = true;
                break;
            }
        }
        if(!sinExt){
            nomArch = nomArchIngrUser;
        }
        return nomArch;
    }
    
    /**
     * Abre una ventana de dialogo para abrir un archivo, devulve la ruta y 
     * nombre del archivo que se haya seleccionado.
     *
     * @param jFchAbrirArch
     * @param rutaArch
     * @return String
     */
    public String openFileWithDialogue(javax.swing.JFileChooser jFchAbrirArch, String rutaArch){
        String rutaActualArch = "";
        if(rutaArch.equals("")){
            jFchAbrirArch.setCurrentDirectory(new File(System.getProperty("user.home")));
            jFchAbrirArch.setDialogTitle("Abrir en: "+System.getProperty("user.home"));
        }else{
            rutaActualArch =  rutaArch;
            jFchAbrirArch.setCurrentDirectory(new File(getPathFile(rutaArch)));
            jFchAbrirArch.setDialogTitle("Abrir en: "+getPathFile(rutaArch));
        }
        jFchAbrirArch.setAcceptAllFileFilterUsed(false);
        int returnVal = jFchAbrirArch.showOpenDialog(null);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFchAbrirArch.getSelectedFile();
            rutaActualArch = file.getAbsolutePath();
        }
        return rutaActualArch;
    }

    /**
     * Visualiza el contenido del archivo que recibe, en un JTextArea.
     *
     * @param mostrarTex
     * @param rutaArch
     */
    public void addTextToJTextArea(javax.swing.JTextArea mostrarTex, String rutaArch){
        File file = new File(rutaArch);
        try {
            mostrarTex.read( new FileReader(file.getAbsolutePath()), null);
        } catch (IOException ex) {
            System.out.println("Problemas al acceder al archivo "+ex.getMessage());
        }
    }
    
    /**
     * Devuelve el número de líneas que contiene el archivo que recibe.
     *
     * @param nombreArch
     * @return Int
     */
    public int countNumLineFile(String nombreArch){
        File fichero = new File(nombreArch);
        int numLine = 0;
        try {
            BufferedReader fich = new BufferedReader(new FileReader(nombreArch));
            //Usamos la clase BufferReadeader para tener acceso a un metodo propio (readLine()) y asi mediante un contador contar las lineas.
            String linea;
            try {
                //En este caso la condicion final del while corresponde a null, para indicar el final de linea
                while((linea = fich.readLine()) != null){
                    numLine++;
                }
                System.out.println("El número de líneas :" + numLine);
            } catch (IOException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return numLine;
    }
    
    /**
     * Revisa cada línea del archivo de reglas separada, para encontrar el nivel
     * máximo que contenga.
     *
     * @param archivo
     * @return Int
     */
    public int getMaxLevelFile(String archivo){
        int nivel = 0;
        try (BufferedReader lee1 = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while((linea = lee1.readLine())!=null){
                i++;
                if (linea.charAt(0)=='|'){
                    if(getLevelString(linea) > nivel){
                        nivel = getLevelString(linea);
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Ha ocurrido un error:");
        }
        return nivel;
    }
    
    /**
     * Obtiene el nivel desplazado por una regla, contando el número de barras
     * que contenga la cadena.
     * 
     * @param cadena
     * @return Int
     */
    public int getLevelString(String cadena){
        int j, nivel=0;
        for(j=0;j<cadena.length();j++){
            if(cadena.charAt(j)=='|'){
                nivel++;
            }
        }
        return nivel;
    }
    
    /**
     * Método que devuelve el número de la línea donde comienzan las reglas en
     * el archivo.
     *
     * @param archivo
     * @return String
     */
    public int getBeginRuleFile(String archivo){
        int numLin=0;
        try (BufferedReader lee1 = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while((linea = lee1.readLine())!=null){
                if(linea.length()>0){
                    if (linea.charAt(0)=='|'){
                        break;
                    }
                }
                numLin++;
            }
        }catch(IOException e){
            System.err.println("Ha ocurrido un error: "+e.getMessage());
        }
        return numLin-1;
    }
    
    
    public void deleteFile(String archEliminar){
        try {
            File archAEliminar = new File(archEliminar);
            archAEliminar.delete();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        } // end try
    } // end Eliminar
    
    /**
     * Método que devuelve una variable boolean verdadera, cuando el archivo a
     * cumple con ciertas características que se pide para ser un archivo de
     * resultados de algoritmos clasificadores.
     *
     * @param directorio
     * @return boolean verdadero si el archivo es válido, falso en caso contrario
     */
    public boolean validateFileBeforeOpen(String directorio) {
        String anterior;
        String actual="";
        int cont=0;
        boolean band=false;
        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (directorio);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null && band!=true){
              if(cont==0){
                  actual=linea;
                  cont++;
              }else{
                  anterior=actual;
                  actual=linea;
                  if(anterior.length()!=0 && actual.length()!=0){
                        band = compareStrings(anterior, actual);
                  }
              }
           }
        }
        catch(Exception e){
           System.err.println("Error: "+e.getMessage());
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();
              }                  
           }catch (Exception e2){ 
               System.err.println("Error: "+e2.getMessage());
           }
        }
        return band;
    }
    
    /**
     * Método que compara dos cadenas, la primera cadena en su posición 0 debe ser
     * letra y la segunda en su posición 0 debe ser barra '|' para que el método
     * retorne verdadero.
     *
     * @param c1
     * @param c2
     * @return booleano
     */
    public boolean compareStrings(String c1, String c2){
        boolean band=false;
        int s1, s2;
        s1=c1.charAt(0);
        s2=c2.charAt(0);
        if(Character.isLetter(s1)){
            if(s2=='|'){
                band=true;
            }
        }
        return band;
    }
    
    /**
     * Método que separa el conjunto de reglas del archivo original, creando un
     * archivo temporal en la misma ruta en la que se encuentra el archivo
     * original.
     *
     * @param rutaArchOrig
     * @param numLine
     */
    public void createTempFileRules(String rutaArchOrig, int numLine) {
        int cont=0;
        boolean band=true;
        String cadena="";
        File archivo;
        FileReader fr;
        BufferedReader br;
        File archTemp;
	archTemp = new File(getPathFile(rutaArchOrig)+System.getProperty("file.separator")+"tempRules.txt");
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(rutaArchOrig);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            FileWriter fwArchTemp = new FileWriter(archTemp);
            BufferedWriter bwArchTemp;
            bwArchTemp = new BufferedWriter(fwArchTemp);
            try (PrintWriter wrArchTemp = new PrintWriter(bwArchTemp)) {
                while((linea=br.readLine())!=null && band!=false){
                    //Creación del archivo temporal
                    if(cont>=numLine){
                        if(linea.length()!=0){
                            wrArchTemp.println(""+linea);
                        }else{
                            band=false;
                        }
                    }
                    cont++;
                }
                wrArchTemp.close(); /*Cerrando archivo de escritura*/
            }
            bwArchTemp.close(); /*Cerrando apertura de archivo*/
            fr.close();
            br.close();
            System.out.println("Archivo temporal creado...");
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }
    
    /**
     * Define arreglos para almacenar los niveles, el nombre, signo terminal y
     * el estado de cada línea del archivo.
     *
     * @param fileRuta
     * @param pathFileOpen
     * @param nomArchSalida
     */
    public void crearMatrizNivel(String fileRuta, String pathFileOpen, String nomArchSalida){
        //NUMERO DE LINEAS DEL ARCHIVO
        int longitud = countNumLineFile(fileRuta);
        //INDICE DE LINEA ACTUAL
        int cont = 0;
        //VECTOR DE NIVEL DE LINEA 0 - 1 - 2 - 3 -...-N
        int[] nivel = new int[longitud];
        //VECTOR DE STRING DE LINEAS DEL ARCHIVO SIN "|"
        String[] cadena = new String[longitud];
        //VECTOR DE STRING "NUM" O ")" SEGÚN SEA EL CASO
        String[] signoTerminal = new String[longitud];
        //VECTOR QUE LLEVA EL CONTROL DE CONDICIONES ACOMPLETADAS
        int[] control = new int[longitud];
        //BANDERA PARA EL CONTROL DE LINEAS NO VACIAS
        boolean band = false;
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File (fileRuta);
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);
           // Lectura del fichero
           String linea;
           while((linea=br.readLine())!=null && band!=true){
               if(linea.length()!=0){
                    asignarValor(linea, nivel, cadena, signoTerminal, control, cont);
                    cont++;
               }else{
                   band=true;
               }
           }
        }
        catch(Exception e){
           System.err.println(e.getMessage());
        }finally{
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              System.err.println(e2.getMessage());
           }
        }
        //MÉTODO QUE OBTIENE LAS CONDICIONES 
        obtenerCondiciones(nivel, cadena, signoTerminal, control, longitud, nomArchSalida, pathFileOpen);
    }
    
    /**
     * Método que inicia el proceso de invoación a otros vectores.
     *
     * @param line
     * @param control
     * @param vec
     * @param cad
     * @param signo
     * @param posicion
     */
    public void asignarValor(String line, int vec[], String cad[], String signo[], int control[], int posicion){
        //SE DEFINE EL NIVEL DE LINEA
        vec[posicion]=getLevelString(line);
        //SE ELIMINAN LAS "|" DE LA LINEA
        cad[posicion]=quitarBarra(line);
        //SE DEFINE SI ES NUMERO O PARENTESIS
        signo[posicion]=signoFinal(line);
        //SE ASIGNA UN 0 AL ARRAY CONTROL, PUESTO QUE NO 
        //A SIDO UTILIZADA DICHA POSICIÓN DEL ARCHIVO
        control[posicion]=0;
    }
    
    /**
     * Elimina las barras de cada línea y asigna la cadena a un arreglo de
     * cadenas.
     *
     * @param cad
     * @return String
     */
    public String quitarBarra(String cad){
        String newCad="";
        for(int i=0; i<cad.length(); i++){
            if(cad.charAt(i)!='|' && cad.charAt(i)!=' '){
                newCad=newCad+cad.charAt(i);
            }
        }
        return newCad;
    }
    
    /**
     * Asigna un símbolo "N" o ")" al arreglo de cadenas para indicar.
     *
     * @param cad
     * @return String
     */
    public String signoFinal(String cad){
        String signo = "";
        if(cad.charAt(cad.length()-1)==')'){
            signo = ")";
        }else{
            signo = "N";
        }
        return signo;
    }
    
    /**
     * Análisis y obtención de condiciones
     *
     * @param nivel
     * @param cadena
     * @param signoTerminal
     * @param control
     * @param longitud
     * @param pathFileOpen
     * @param nomArchSalida
     */
    public void obtenerCondiciones(int nivel[], String cadena[], String signoTerminal[], 
            int control[], int longitud, String nomArchSalida, String pathFileOpen){
        //VARIABLES PARA CREAR Y ESCRIBIR EN UN ARCHIVO
        FileWriter fichero = null;
        PrintWriter pw;
        //CADENA QUE CONCATENA LA CONDICION, LA GUARDA EN EL ARCHIVO
        //Y LIMPIA SU CONTENIDO
        String condicion;
        //CONTROL DEL NIVEL DONDE SE ENCONTRO EL SIGNO ")"
        int nivelCondition;
        //SI SE LLEGA AL NIVEL 0, ENTONCES LA BANDERA CAMBIARÁ A TRUE Y 
        //TERMINA EL CICLO DE REGRESIÓN
        boolean band=false;
        //INDICE DEL NÚMERO DE REGLA
        int rule=0;
        //INTENTA CREAR Y ESCRIBIR EN EL ARCHIVO
        try {
            //CREA UN NUEVO ARCHIVO CON EL NOMBRE ENCONTRADO EN LA VARIABLE
            //nonArchSalida
            fichero = new FileWriter(nomArchSalida);
            //CREA UN OBJETO PARA REALIZAR LA ESCRITURA EN EL ARCHIVO
            pw = new PrintWriter(fichero);
            //SE ESCRIBE UN TÍTULO DENTRO DEL ARCHIVO
            pw.println("== Run Information ==\n" + "Rules get from file: "+pathFileOpen+"\n" 
                    + "Num of rules: "+getNumberRules(signoTerminal, longitud));
            pw.println("\n*** Set of conditions ***\n");
            //RECORRE EL ARREGLO signoTerminal[i]
            for(int i=0; i<longitud; i++){
                //VERIFICA SI EXISTE UN SIGNO TERMINAL ")"
                if(signoTerminal[i].equals(")")){
                    //INICIALIZA EN CADENA VACIA LA VARIABLE condicion
                    condicion = "";
                    //SI LA POSICIÓN i DEL VECTOR SIGNO ")" ES 0
                    //QUIERE DECIR QUE NO SE HA HECHO LA CONDICIÓN
                    if(control[i]!=1){
                        //INCREMENTA EN 1 EL NÚMERO DE REGLA
                        rule++;
                        //CAMBIA A UNO LA POSICION i DEL VECTOR CONTROL
                        //PARA DAR A CONOCER QUE LA CONDICION HA SIDO REALIZADA ANTERIORMENTE
                        control[i]=1;
                        //RESTA 1 AL NIVEL DE DONDE SE ENCONTRO EL SIGNO ")" Y ESTA NUEVA
                        //POSICIÓN SERA EL PADRE DE DICHA CONDICIÓN
                        nivelCondition = nivel[i]-1;
                        //CONCATENA LA CADENA CON LA CLASE OBTENIDA DEL METODO getClase
                        condicion="\nTHEN "+getClase(cadena[i]+"\n\n");
                        //CONCATENA LA CADENA CON LA CONDICION OBTENIDA DEL METODO getCondicion
                        //LA CUAL SEPARA LA CLASE DE LA CONDICIÓN A PARTIR DE  ":"
                        condicion=" AND \n"+getCondicion(cadena[i])+condicion +"\n";
                        //EN ESTE CICLO SE REALIZA EL RETROCESO DEL ARREGLO nivel
                        //DONDE SE BUSCA EL PADRE DE CADA CONDICIÓN
                        for(int j=i; j>=0 && band!=true; j--){
                            //SI EL NIVEL ES IGUAL A 0, QUIERE DECIR QUE SE A LLEGADO A LA RAIZ
                            if(nivel[j]==0){
                                //SI LA BANDERA ES TRUE, ENTONCES SE TERMINA EL CICLO FOR
                                band=true;
                                //AÑADE "IF", LA CONDICION RAIZ Y EL CONTENIDO ANTERIOR DE 
                                //LA VARIABLE condicion
                                condicion="R"+rule+": "+"IF \n"+cadena[j]+condicion;
                                //IMPRIME TODA LA CONDICIÓN GENERADA
                                //ALMACENA LA CONDICIÓN DENTRO DEL ARCHIVO
                                pw.println(condicion);
                            }else{
                                //SI EL NIVEL AUN NO ES 0, ENTONCES SE VERIFICA SI LA POSICIÓN
                                //j, DEL ARRAY NIVEL, COINCIDE CON EL NIVEL (PADRE DE LA CONDICIÓN ACTUAL)
                                if(nivel[j]==nivelCondition){
                                    //SI COINCIDE, ENTONCES SE AÑADE "AND" Y EL CONTENIDO DE
                                    //LA VARIABLE DE TIPO STRING condicion
                                    condicion=" AND \n"+cadena[j]+condicion;
                                    //SE DECREMENTA EN 1 EL NIVEL QUE REPRESENTA AL PADRE DE 
                                    //LA CONDICION ACTUAL
                                    nivelCondition=nivelCondition-1;
                                }
                            }
                        }
                        //REINICIALIZA LA VARIABLE BAND EN FALSE, PARA LA SIGUIENTE CONDICIÓN
                        band=false;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "El archivo de reglas ha sido exportado satisfactoriamente.", "Reglas exportadas", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
           try {
                if (null != fichero)
                    fichero.close();
           } catch (Exception e2) {
                System.out.println(e2.getMessage());
           }
        }
    }
    
    /**
     * Método para obtener el número de condiciones del archivo.
     *
     * @param signo
     * @param longitud
     * @return Int
     */
    public int getNumberRules(String signo[], int longitud){
        int numConditions=0;
        for(int i=0; i<longitud; i++){
            if(signo[i].equals(")")){
                numConditions++;
            }
        }
        return numConditions;
    }
    
    /**
     * Método para obtener solo la condicion y no la clase X
     *
     * @param cadena
     * @return String
     */
    public String getCondicion(String cadena){
        String condicion="";
        boolean band=false;
        for(int i=0; i<cadena.length() && band!=true; i++){
            if(cadena.charAt(i)!=':'){
                condicion=condicion+cadena.charAt(i);
            }else{
                band=true;
            }
        }
        return condicion;
    }
    
    /**
     * Método para obtener solamente la clase X y no la condición.
     *
     * @param cadena
     * @return String
     */
    public String getClase(String cadena){
        String clase="";
        boolean bandCp = true;
        //+(2.56)
        if(getPosCharacter(cadena, "/")==-1){
            String antCad = cadena.substring(getPosCharacter(cadena, ":")+1, getPosCharacter(cadena, "(")+1);
            String contCad = cadena.substring(getPosCharacter(cadena, "(")+1, cadena.length()-3);
            double entero = Double.parseDouble(contCad);
            int ent = (int) entero;
            clase = antCad + Integer.toString(ent) + ")";
        }else{
            String antCad = cadena.substring(getPosCharacter(cadena, ":")+1, getPosCharacter(cadena, "(")+1);
            String priNum = cadena.substring(getPosCharacter(cadena, "(")+1, getPosCharacter(cadena, "/"));
            String segNum = cadena.substring(getPosCharacter(cadena, "/")+1, cadena.length()-3);
            
            double num1 = Double.parseDouble(priNum);
            double num2 = Double.parseDouble(segNum);
            int nu1 = (int) num1;
            int nu2 = (int) num2;
            
            System.out.println("Numero redondeado a cero decimales : "+getCifraRedon(num1, 1) + "/" +getCifraRedon(num2, 1));
            clase = antCad+Integer.toString(nu1)+"/"+Integer.toString(nu2)+")";
        }
        
        /*
        for(int i=0; i<cadena.length(); i++){
            if(cadena.charAt(i)=='.'){
                bandCp = false;
            }
            if(cadena.charAt(i)=='/' || cadena.charAt(i)==')' ){
                bandCp = true;
            }
            if(bandCp){
                clase=clase+cadena.charAt(i);
            }
        }*/
        return clase;
    }
    
    public int getConvDouToInt(double numero){
        return (int) numero;
    }
    
    public double getCifraRedon(double numero, int deciRed){
        int cifras=(int) Math.pow(10,0);
        return  Math.rint(numero*cifras)/cifras;
    }
    
    public int getPosCharacter(String cadena, String sim){
        int i, pos=-1;
        for(i=0;i<cadena.length();i++){
            if(cadena.charAt(i)==sim.charAt(0)){
                pos = i;
                break;
            }
        }
        return pos;
    }
}