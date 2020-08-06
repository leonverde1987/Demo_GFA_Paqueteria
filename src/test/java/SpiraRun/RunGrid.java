/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpiraRun;

import com.inflectra.spiratest.addons.junitextension.SpiraTestListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 *
 * @author TestingIT
 */
public class RunGrid {
    
    @Test
    public void TestBuscarGoogleGrid() throws InterruptedException, FileNotFoundException, IOException {
        Properties Config = new generic.genericGrid().getPropetiesFile("configuracion\\configuracion.properties");
        JUnitCore core = new JUnitCore();
        core.addListener(new SpiraTestListener());
        //new generic.genericGrid().leventarNodosGrid();
//        Config.setProperty("Navegador","motorolaOneVision");
//        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a motorolaOneVision");
//        core.run (TestCases.Test_Paqueteria_Buscar_Guia.class);
//        
        Config.setProperty("Navegador","chrome");
        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a Chrome");
        core.run (TestCases.PO_Solicitudes_Compra_Leo.class);
        
        Config.setProperty("Navegador","firefox");
        Config.store(new FileWriter("configuracion\\configuracion.properties"),"Cambio de Navegador a firefox");
        core.run (TestCases.PO_Solicitudes_Compra_Leo.class);
        //new generic.genericGrid().cierraNodosGrid();

    }
}