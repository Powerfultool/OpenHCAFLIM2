/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HCAFLIM;

import javax.swing.JFrame;
import org.micromanager.api.ScriptInterface;
import javax.swing.JOptionPane;
import mmcorej.CMMCore;
import org.micromanager.MMStudio;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.acquisition.AcquisitionWrapperEngine;
import org.micromanager.api.MMPlugin;


/**
 *
 * @author Fogim
 */
public class HCAFLIM_base implements org.micromanager.api.MMPlugin {
    public static final String menuName = "New_HCAFLIM";
    public static final String tooltipDescription = "Controller for HCA FLIM acquisitions";
    // Access to Acquisition engine
    private AcquisitionEngine acq_;
    // Access to the MM GUI
    private MMStudio gui_;
    // Provides access to the Micro-Manager Java API (for GUI control and high-level functions).
    private ScriptInterface app_;
    // Provides access to the Micro-Manager Core API (for direct hardware control)
    private CMMCore core_;

    // This will be the front end frame that can contain front end JPanels and whatnot
    public static JFrame frame_;
    
    public static AcquisitionWrapperEngine getAcquisitionWrapperEngine() 
    {
        AcquisitionWrapperEngine engineWrapper = (AcquisitionWrapperEngine) MMStudio.getInstance().getAcquisitionEngine();
        return engineWrapper;
    }
    
    @Override
    public void dispose() {
        //Don't need this yet
    }

    @Override
    public void setApp(ScriptInterface app) {
        app_ = app;
        core_ = app.getMMCore();
        gui_ = (MMStudio) app;
        acq_ = gui_.getAcquisitionEngine();        
        
        frame_ = new HCAFLIM_frontend(core_);
        frame_.pack();
    }

    @Override
    public void show() {
        if (frame_ == null) {
            frame_ = new HCAFLIM_frontend(core_);
            gui_.addMMBackgroundListener(frame_);
        }
        frame_.setVisible(true);
    }

    @Override
    public String getDescription() {
         return tooltipDescription;
    }

    @Override
    public String getInfo() {
        return "Runs the new HCA FLIM plugin";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public String getCopyright() {
        return "Imperial College, 2017";
    }
    
}
