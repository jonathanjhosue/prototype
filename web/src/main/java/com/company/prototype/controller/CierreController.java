package com.company.prototype.controller;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.controller.util.JsfUtil;
import com.company.prototype.controller.util.JsfUtil.PersistAction;
import com.company.prototype.service.CierreFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("cierreController")
@SessionScoped
public class CierreController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private com.company.prototype.service.CierreFacade ejbFacade;
    private List<Cierre> items = null;
    private Cierre selected;

    public CierreController() {
    }

    public Cierre getSelected() {
        return selected;
    }

    public void setSelected(Cierre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CierreFacade getFacade() {
        return ejbFacade;
    }

    public Cierre prepareCreate() {
        selected = new Cierre();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CierreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CierreUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CierreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cierre> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cierre getCierre(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Cierre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cierre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cierre.class)
    public static class CierreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CierreController controller = (CierreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cierreController");
            return controller.getCierre(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cierre) {
                Cierre o = (Cierre) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cierre.class.getName()});
                return null;
            }
        }

    }

}
