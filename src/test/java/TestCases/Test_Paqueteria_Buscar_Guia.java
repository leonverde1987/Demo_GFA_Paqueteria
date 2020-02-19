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
import steps.steps_Paqueteria_Buscar_Guia;

@SpiraTestConfiguration(
	    url="https://testing-it.spiraservice.net",
	    login="automationQA",
	    password="test1234", 
	    projectId=67,
	    testSetId=981
	)

public class Test_Paqueteria_Buscar_Guia extends steps_Paqueteria_Buscar_Guia{
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
        Navegador = Config.getProperty("NavegadorPaq");
        driver = this.openGridBrowser(Navegador, Config);
        
    }
    
    @Test
    @SpiraTestCase(testCaseId=7683)
    public void Test_Buscar_Guia() throws InterruptedException, DocumentException, BadElementException, IOException, Exception {
        try{
            Escenario = "PAQ_Home_Buscar_Guia_que_no_Existe.";
            Navegador = this.navegador(driver.toString());
            
            //Paso 1
            Pasos.add(contador+".- Abrimos navegador en la URL: "+Config.getProperty("urlAppPaqueteria"));
            this.ingresar_A_URL(driver, contador, Config, Escenario, Navegador);
            
            //Paso 2
            contador++;
            Pasos.add(contador+".- Seleccionar Prefijo: "+Datos.getProperty("cboPrefijo"));
            this.seleccionar_prefijo(driver, Datos.getProperty("cboPrefijo"), contador, Config, Elementos, Escenario, Navegador);
            
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
            
        }catch(NoSuchElementException s){
            Resultado = "Ejecución Fallida, No se encontró elemento: "+s;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
            System.out.println(Resultado);
        }catch(InterruptedException e){
            Resultado = "Ejecución Fallida: "+e;
            this.capturarEvidencia(driver, Config, contador, Escenario, Navegador);
            System.out.println(Resultado);
        }finally{
            this.finalizarTestCase(driver, Escenario, Resultado, contador, Pasos, RutaEvidencia, Config.getProperty("Modulo"), Config.getProperty("Version"), Navegador);
        }
    }
    
    
    @After
    public void cerrarTest(){
        this.cerrar_Navegador(driver);
    }

    

    
}
