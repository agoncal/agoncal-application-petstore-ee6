package org.agoncal.application.petstore.web;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class Language implements Serializable {

    // ======================================
    // =          Business methods          =
    // ======================================

    public void doFr() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.FRENCH);
    }

    public void doEn() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
    }
}