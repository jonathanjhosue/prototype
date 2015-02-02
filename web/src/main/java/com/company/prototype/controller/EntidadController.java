package com.company.prototype.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.company.prototype.controller.util.JsfUtil;
import com.company.prototype.controller.util.JsfUtil.PersistAction;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.service.EntidadFacade;
import com.company.prototype.util.ControllerUtils;

@Named("entidadController")
@SessionScoped
public class EntidadController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private com.company.prototype.service.EntidadFacade ejbFacade;
    private List<Entidad> items = null;
    private Entidad selected;

    public EntidadController() {
    }

    public Entidad getSelected() {
        return selected;
    }

    public void setSelected(Entidad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EntidadFacade getFacade() {
        return ejbFacade;
    }

    public Entidad prepareCreate() {
        selected = new Entidad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EntidadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EntidadUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EntidadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Entidad> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
        	selected.setAdmUsuario(ControllerUtils.getSessionUser(FacesContext.getCurrentInstance()));
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

    public Entidad getEntidad(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Entidad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Entidad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Entidad.class)
    public static class EntidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntidadController controller = (EntidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entidadController");
            return controller.getEntidad(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = new Long(value);
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Entidad) {
                Entidad o = (Entidad) object;
                return getStringKey(o.getId().toString());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Entidad.class.getName()});
                return null;
            }
        }

    }

}
