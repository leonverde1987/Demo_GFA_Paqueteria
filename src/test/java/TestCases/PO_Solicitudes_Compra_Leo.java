package TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import com.inflectra.spiratest.addons.junitextension.*;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import steps.steps_PO_Solicitudes_Compras_Leo;


public class PO_Solicitudes_Compra_Leo extends steps_PO_Solicitudes_Compras_Leo{
    public Properties Config = null;
    public Properties Datos = null;
    public Properties Elementos = null;
    private RemoteWebDriver driver = null;
    public List<String> Pasos = new ArrayList<String>();
    public int contador = 0;
    public String Resultado = "";
    public String Escenario = "";
    public String RutaEvidencia = "";
    public String Navegador="";
    
    
    @Before
    public void PrepararEjecucion() throws FileNotFoundException, MalformedURLException, InterruptedException{
        Config = this.getPropetiesFile("configuracion\\configuracion.properties");
        Datos = this.getPropetiesFile("configuracion\\datosPaqueteriaBuscarGuia.properties");
        Elementos = this.getPropetiesFile("configuracion\\pagePaqueteriaBuscarGuia.properties");
        contador = 1;
        RutaEvidencia = Config.getProperty("rutaEvidencia");
        Resultado = "Fallido";
        Navegador = Config.getProperty("Navegador");
        driver = this.openGridBrowser(Navegador, Config);
        
    }
    
    @Test
    public void Test_Buscar_Guia() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "PAQ_Home_Buscar_Guia_que_no_Existe.";
            
            //Paso 1
            Pasos.add(contador+".- Abrir navegador en la URL: "+Config.getProperty("urlOracle"));
            this.ingresar_A_URL(driver, contador, Config, Escenario, Navegador);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Seleccionar Prefijo: "+Datos.getProperty("cboPrefijo"));
            this.Login_Oracle(driver, Datos.getProperty("usuarioCompras"), Datos.getProperty("contrasenaCompras"), contador, Config, Elementos, Escenario, Navegador);
            /*this.seleccionar_prefijo(driver, Datos.getProperty("cboPrefijo"), contador, Config, Elementos, Escenario, Navegador);
            
            //Paso 3
            contador++;
            Pasos.add(contador+".- Ingresar Número de Guía: "+Datos.getProperty("txtGuiaNum"));
            this.ingresar_guia(driver, Datos.getProperty("txtGuiaNum"), contador, Config, Elementos, Escenario, Navegador);
            
            //Paso 4
            contador++;
            Pasos.add(contador+".- Presionar el botón con signo: +.");
            this.presionar_btn_mas(driver, contador, Config, Elementos, Escenario, Navegador);
            
            //Paso 5
            contador++;
            Pasos.add(contador+".- Presionar el botón: Consultar.");
            this.presionar_Consultar(driver, contador, Config, Elementos, Escenario, Navegador);
            
            //Paso 6
            contador++;
            Pasos.add(contador+".- Validar nos muestre el mensaje: "+Datos.getProperty("mensajeAssert")+" Para una guía que no existe.");
            Resultado=this.validar_Mensaje(driver, Datos, Config, Elementos, contador, Escenario, Navegador);
            */
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
        }finally{
            this.finalizarTestCase(driver, Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"), Navegador);
            if(!"Exitoso".equals(Resultado.substring(0, 7))){
                throw new Exception("Navegador: "+Navegador + "\n Resultado: " + Resultado);
            }
            
        }
    }
    
    
    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
    }

    

    
}
