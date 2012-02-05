package org.agoncal.application.petstore.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
 
@Named
@SessionScoped
public class Language implements Serializable{
	
	public void doFr() {
		FacesContext.getCurrentInstance()
		.getViewRoot().setLocale(Locale.FRENCH);
	}
	public void doEn() {
		FacesContext.getCurrentInstance()
		.getViewRoot().setLocale(Locale.ENGLISH);
	}	
}